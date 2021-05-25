package Actors;

/*
This class aims to control the items which are dropped by the enemies when they dead.
 */

import DatabaseManager.OperateDatabase;
import GameDriver.staticVariable;
import org.jsfml.graphics.*;
import org.jsfml.window.Mouse;

import java.io.*;
import java.util.ArrayList;

public class MonstersItems
{
    //Type of the items: Shoes, Suits or Weapons
    private int category;
    //Number of items
    private int number;
    //Positions of the items
    private int x;
    private int y;
    //Whether the box is opened.
    private boolean isOpened = false;
    private ConstTexture boxUnopened;
    private ConstTexture boxOpened;
    private ConstTexture boxLight;
    private ConstTexture shadowPic;
    private Sprite box = new Sprite();
    private Sprite boxShadow = new Sprite();
    private ConstTexture itemPic;
    private Sprite itemContainer = new Sprite();
    //Drawable window
    private RenderWindow window;
    //Descriptions of the items
    private Font font;
    private Text text;
    private ConstTexture describePic;
    private Sprite describePanel = new Sprite();
    private String itemInfo = null;
    //Write info into file
    private String path ="EquipmentsDescription/test.txt"; //目标文件路径
    private File file = new File(path); //创建目标文件

    public MonstersItems(int category, int number, RenderWindow window, int x, int y)
    {
        //Initialize the basic variables
        this.category = category;
        this.number = number;
        this.window = window;
        this.x = x;
        this.y = y;
        this.font = staticVariable.dialogFont;
        this.text = new Text("", font, 12);

        //Initialize drawable variables
        decideImage();
        getItemInfo();
        this.text.setColor(Color.RED);
        this.text.setString(this.itemInfo);
        this.boxOpened = staticVariable.box.get(1);
        this.boxUnopened = staticVariable.newBox.get(0);
        this.boxLight = staticVariable.newBox.get(1);
        this.shadowPic = staticVariable.boxShadow;
        this.describePic = staticVariable.weaponPanel;
        this.describePanel.setTexture(this.describePic);
        this.box.setTexture(this.boxUnopened);
        this.boxShadow.setTexture(this.shadowPic);
        this.box.setOrigin(65, 62);
        this.boxShadow.setOrigin(this.shadowPic.getSize().x / 2, this.shadowPic.getSize().y / 2);
        this.describePanel.setOrigin(this.describePic.getSize().x / 2, this.describePic.getSize().y / 2);
        this.describePanel.setScale((float) 0.6, (float) 0.6);
        this.box.setScale((float) 0.8, (float) 0.8);
    }

    public void decideImage()
    {
        if(this.category == 1)
            this.itemPic = staticVariable.shoes.get(number - 1);
        else if(this.category == 2)
            this.itemPic = staticVariable.suits.get(number - 1);
        else if(this.category == 3)
            this.itemPic = staticVariable.weapons.get(number - 1);
        this.itemContainer.setTexture(this.itemPic);
        this.itemContainer.setOrigin(40, 40);
        this.itemContainer.setScale((float) 0.4, (float) 0.4);
    }

    public void onDraw(boolean isFull)
    {
        mouseListener(isFull);
        this.box.setPosition(this.x, this.y);
        this.boxShadow.setPosition(box.getPosition().x, box.getPosition().y + 30);
        this.window.draw(this.boxShadow);
        if(isOpened)
        {
            this.box.setPosition(this.x + 10, this.y + 10);
            this.box.setTexture(this.boxOpened);
        }
        this.window.draw(this.box);
    }

    public void mouseListener(boolean isFull)
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        boolean isEnter = Math.abs(this.x - mouseX) <= 30 && Math.abs(this.y - mouseY) <= 30;
        if(isEnter && !isOpened)
        {
            this.box.setTexture(this.boxLight);
            this.describePanel.setPosition(this.x, this.y - 95);
            this.itemContainer.setPosition(this.x - 40, this.y - 116);
            this.text.setPosition(this.x - 50, this.y - 125);
            this.window.draw(this.describePanel);
            this.window.draw(this.itemContainer);
            this.window.draw(this.text);
        }
        else if(!isEnter)
            this.box.setTexture(this.boxUnopened);
        //If try to open the box
        if(isEnter && mouse.isButtonPressed(Mouse.Button.LEFT) && !isOpened)
        {
            if(!isFull)
            {
                isOpened = true;

                ArrayList info = OperateDatabase.getEquipsInfo();
                ArrayList blanks = (ArrayList) info.get(2);

                if(blanks.size() < 6)
                {
                    int target = 1;

                    for(int i = 0; i < blanks.size(); i++)
                    {
                        if(!blanks.contains(target))
                            break;
                        target++;
                    }

                    if(target <= 6)
                        OperateDatabase.updateEquip(this.category, this.number, target, "Add");
                }

                //System.out.println(target + " " + blanks.contains(target));
               // String oldStr = "empty";
               // String newStr = "filled," + this.category + "," + this.number;
               // alterStringToCreateNewFile(oldStr, newStr);
            }
        }
    }

    //Read the txt file and get the description of this item.
    public void getItemInfo()
    {
        String filename = null;
        if(this.category == 1)
        {
            filename = "shoes.txt";
            this.itemInfo = "         Shoes";
        }
        else if(this.category == 2)
        {
            filename = "suits.txt";
            this.itemInfo = "         Suits";
        }
        else if(this.category == 3)
        {
            filename = "weapons.txt";
            this.itemInfo = "         Weapons";
        }
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
                if(num == this.number)
                    this.itemInfo = this.itemInfo + "\n\n" + splitStr[1] + "\n" + splitStr[2];
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Replace the certain blank information in the txt file
    public void alterStringToCreateNewFile(String oldString, String newString)
    {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream( file)));
            File newFile = new File("EquipmentsDescription/buffer");
            if (!newFile.exists()){
                newFile.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(newFile,true)));
            String string = null;
            boolean isReplaceed = false;
            while ((string = br.readLine()) != null){
                if (string.contains(oldString) && !isReplaceed)
                {
                    string = new String(string.replace(oldString,newString));
                    isReplaceed = true;
                }
                bw.write(string);
                bw.newLine();
            }
            br.close();
            bw.close();
            String filePath = file.getPath();
            file.delete();
            newFile.renameTo(new File(filePath));
            System.out.println("Replace successfully");
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x += x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y += y;
    }
}

