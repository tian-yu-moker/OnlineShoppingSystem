package GUI;

/*
This class is the equipments that the use wears
Display on the screen
When the space is full, if wants to wear new ones,
must drop one to free a space.
 */

import DatabaseManager.OperateDatabase;
import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.*;
import org.jsfml.window.Mouse;

import java.io.*;
import java.util.ArrayList;

public class HeroEquipments
{
    //The number of items that the player wears
    private boolean isFull = false;
    private int itemCount = 0;
    //The coordination of each blank
    private int x[] = new int[6];
    private int y[] = new int[6];
    private boolean[] isEnter = new boolean[6];
    private boolean[] isDraw = new boolean[6];
    private boolean[] isFilled = new boolean[6];
    //When the mouse move to that blank, this panel will appears.
    private ConstTexture describePic;
    private Sprite describePanel = new Sprite();
    private ConstTexture close;
    private ConstTexture closeLight;
    private ConstTexture drop;
    private ConstTexture dropLight;
    private Sprite closeButton = new Sprite();
    private Sprite dropButton = new Sprite();
    private ConstTexture[] sixBlanksPic = new ConstTexture[6];
    private Sprite[] sixBlanks = new Sprite[6];

    //Font & Text
    private Font font;
    private Text text;
    //Drawable window
    private RenderWindow window;
    //Type & number of the equipment
    private int[] type = new int[6];
    private int[] number = new int[6];
    //The text of the description
    private String descriptionText = "No Weapons";
    String path ="EquipmentsDescription/test.txt";
    File file = new File(path);

    private float totalBlood[] = new float[6];
    private float totalAttack[] = new float[6];
    private float totalSpeed[] = new float[6];
    private float totalMP[] = new float[6];
    private String myTexts[] = new String[6];

    private boolean soundClose = false;
    private boolean soundOpen = false;


    public HeroEquipments(RenderWindow window)
    {
        this.window = window;
        decidePosition();
        this.font = staticVariable.dialogFont;
        this.text = new Text("", font, 12);
        //Description panel
        this.describePic = staticVariable.weaponPanel;
        this.close = staticVariable.equipButtons.get(1);
        this.closeLight = staticVariable.equipButtons.get(0);
        this.drop = staticVariable.equipButtons.get(2);
        this.dropLight = staticVariable.equipButtons.get(3);

        this.closeButton.setTexture(this.close);
        this.dropButton.setTexture(this.drop);
        this.describePanel.setTexture(this.describePic);

        this.describePanel.setOrigin(this.describePic.getSize().x / 2, this.describePanel.getScale().y / 2);
        this.closeButton.setOrigin(60, 35);
        this.dropButton.setOrigin(60, 35);
        this.describePanel.setScale((float) 0.7, (float) 0.7);
        this.closeButton.setScale((float) 0.5, (float) 0.6);
        this.dropButton.setScale((float) 0.5, (float) 0.6);
    }

    public void decidePosition()
    {
        for(int i = 0; i < 6; i++)
        {
            x[i] = 698 + (i * 66);
            y[i] = 723;
            totalBlood[i] = 0;
            totalAttack[i] = 0;
            totalSpeed[i] = 0;
            totalMP[i] = 0;
            isEnter[i] = false;
            isDraw[i] = false;
            isFilled[i] = false;
            myTexts[i] = "Empty";
            type[i] = 0;
            number[i] = 0;
            sixBlanks[i] = new Sprite();
            sixBlanks[i].setOrigin(40, 40);
            sixBlanks[i].setPosition(x[i] + 1, y[i] + 2);
        }
    }

    public void onDraw()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        mouseListener();
        for(int i = 0; i < 6; i++)
        {
            readDatabase();
            getDescription(i);
            if(isDraw[i])
            {
                this.window.draw(describePanel);
                if(!isFilled[i])
                    descriptionText = "No Weapons";
                text.setString(descriptionText);
                text.setPosition(x[i] - 135, y[i] - 140);
                this.window.draw(this.text);
                boolean isEnter1 = Math.abs(mouseX - this.closeButton.getPosition().x) <= 10
                        && Math.abs(mouseY - this.closeButton.getPosition().y) <= 10;
                boolean isEnter2 = Math.abs(mouseX - this.dropButton.getPosition().x) <= 10
                        && Math.abs(mouseY - this.dropButton.getPosition().y) <= 10;
                if(isEnter1)
                    this.closeButton.setTexture(this.closeLight);
                else
                    this.closeButton.setTexture(this.close);
                if(isEnter2)
                    this.dropButton.setTexture(this.dropLight);
                else
                    this.dropButton.setTexture(this.drop);
                twoButtonsListener(isEnter1, isEnter2, i);
                this.window.draw(this.closeButton);
                this.window.draw(this.dropButton);
            }
            if(isFilled[i])
            {
                sixBlanks[i].setScale((float) 0.53, (float) 0.5);
                this.sixBlanks[i].setTexture(this.sixBlanksPic[i]);
                this.window.draw(this.sixBlanks[i]);
            }

        }
        int count = 0;
        for(int i = 0; i < 6; i++)
        {
            if(isFilled[i])
                count++;
        }
        if(count == 6)
            isFull = true;
        else if(count != 6)
            isFull = false;
        //this.getAttributions();
        //System.out.println(this.getAttributions());
    }

    public void mouseListener()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        boolean onlyOne = false;
        for(int i = 0; i < 6; i++)
        {
            if(isDraw[i])
            {
                onlyOne = true;
                break;
            }
        }
        if(!onlyOne)
        {
            for(int i = 0; i < 6; i++)
            {
                isEnter[i] = Math.abs(this.x[i] - mouseX) <=20 && Math.abs(this.y[i] - mouseY) <= 20;
                if(isEnter[i] && mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    isDraw[i] = true;
                    this.describePanel.setPosition(x[i] - 80, y[i] - 150);
                    this.closeButton.setPosition(x[i] - 117 ,y[i] - 70);
                    this.dropButton.setPosition(x[i] - 40,y[i] - 70);
                }
            }
        }
    }

    public void twoButtonsListener(boolean isEnter1, boolean isEnter2, int i)
    {
        Mouse mouse = null;
        //Close the panel
        if(isEnter1)
        {
            if(!soundClose)
            {
                soundClose = true;
                TDMusicPlay.buttonMusic.play();
            }
        }
        else if(!isEnter1)
            soundClose = false;

        if(isEnter2)
        {
            if(!soundOpen)
            {
                soundOpen = true;
                TDMusicPlay.buttonMusic.play();
            }
        }
        else if(!isEnter2)
            soundOpen = false;

        if(isEnter1 && mouse.isButtonPressed(Mouse.Button.LEFT))
            isDraw[i] = false;
        //Drop the item in the blank
        if(isEnter2 && mouse.isButtonPressed(Mouse.Button.LEFT) && this.isFilled[i])
        {
            isDraw[i] = false;
            //String oldStr = "filled," + this.type[i] + "," + this.number[i];
            //String newStr = "empty";
            //alterStringToCreateNewFile(oldStr, newStr, i);
            OperateDatabase.updateEquip(this.type[i], this.number[i], i + 1, "Drop");
            this.isFilled[i] = false;
            this.descriptionText = "No Weapons";
            myTexts[i] = "Empty";
            this.itemCount--;
        }
    }

    //Read the txt file and get the description of this item.
    public void getDescription(int i)
    {
        String filename = null;
        if(isFilled[i])
        {
            //System.out.println(i + " aaaaaaaaaaaaaaaaaaaaaa");
            if(type[i] == 1)
                filename = "shoes.txt";
            else if(type[i] == 2)
                filename = "suits.txt";
            else if(type[i] == 3)
                filename = "weapons.txt";
            File ctoFile = new File( "EquipmentsDescription/" + filename);
            InputStreamReader readingContent = null;
            try {
                readingContent = new InputStreamReader(new FileInputStream(ctoFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            BufferedReader counting = new BufferedReader(readingContent);
            String txtline = null;
            try {
                while ((txtline = counting.readLine()) != null)
                {
                    String splitStr[] = txtline.split(",");
                    int num = Integer.parseInt(splitStr[0]);
                    if(num == this.number[i])
                    {
                        this.descriptionText = splitStr[1] + "\n  " + splitStr[2];
                        myTexts[i] = splitStr[2];
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readDatabase()
    {
        ArrayList info = OperateDatabase.getEquipsInfo();
        //System.out.println(info);
        if(info.size() != 0)
        {
            ArrayList types = (ArrayList) info.get(0);
            ArrayList numbers = (ArrayList) info.get(1);
            ArrayList blanks = (ArrayList) info.get(2);
            for(int i = 0; i < types.size(); i++)
            {
                //System.out.println("aaaaaaaaaa" + i);
                this.isFilled[(int) blanks.get(i) - 1] = true;
                this.type[(int) blanks.get(i) - 1] = (int) types.get(i);
                this.number[(int) blanks.get(i) - 1] = (int) numbers.get(i);
                int equipType = (int) types.get(i);
                int num = (int) blanks.get(i) - 1;
                if(equipType == 1)
                    this.sixBlanksPic[num] = staticVariable.shoes.get(this.number[num] - 1);
                else if(equipType == 2)
                    this.sixBlanksPic[num] = staticVariable.suits.get(this.number[num] - 1);
                else if(equipType == 3)
                    this.sixBlanksPic[num] = staticVariable.weapons.get(this.number[num] - 1);
            }
        }
    }

    public ArrayList getAttributions()
    {
        getTotal();
        ArrayList list = new ArrayList();
        float hp = 0;
        float mp = 0;
        float sp = 0;
        float at = 0;
        for (int i = 0; i < 6; i++)
        {
            hp += totalBlood[i];
            mp += totalMP[i];
            sp += totalSpeed[i];
            at += totalAttack[i];
        }
        list.add(hp);
        list.add(mp);
        list.add(sp);
        list.add(at);
        return list;
    }

    public void getTotal()
    {
        for(int i = 0; i < 6; i++)
        {


            if(!myTexts[i].equals("Empty"))
            {
                String con[] = myTexts[i].split(" ");
                //System.out.println(con.length);
                if(con[0].substring(0, 2).equals("HP"))
                {
                    String hp = con[0].split("\\+")[1];
                    totalBlood[i] = Float.parseFloat(hp);
                }
                if(con.length > 1)
                {
                    if(con[1].substring(0, 2).equals("MP"))
                    {
                        String mp = con[1].split("\\+")[1];
                        totalMP[i] = Float.parseFloat(mp);
                    }
                    else if(con[1].substring(0, 2).equals("HP"))
                    {
                        String hp = con[1].split("\\+")[1];
                        totalBlood[i] = Float.parseFloat(hp);
                    }
                }
                else if(con[0].substring(0, 2).equals("sp"))
                {
                    String speed = con[0].split("\\+")[1];
                    totalSpeed[i] = Float.parseFloat(speed);
                }
                else if(con[0].substring(0, 2).equals("at"))
                {
                    String speed = con[0].split("\\+")[1];
                    totalAttack[i] = Float.parseFloat(speed);
                }
            }
            else if(myTexts[i].equals("Empty"))
            {
                totalAttack[i] = 0;
                totalSpeed[i] = 0;
                totalMP[i] = 0;
                totalBlood[i] = 0;
            }
        }
    }


    public boolean getIsFull()
    {
        return isFull;
    }
}
