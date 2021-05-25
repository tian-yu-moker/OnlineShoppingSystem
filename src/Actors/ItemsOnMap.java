package Actors;

/*
This class is the entity class of the items on the map.
@para: type-> type of the item
Use t
 */

import DatabaseManager.OperateDatabase;
import GameDriver.PackFileModifier;
import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.*;
import org.jsfml.window.Mouse;

import java.util.ArrayList;


public class ItemsOnMap
{
    //Type of the item
    private int type;
    //Position of the item
    private int x;
    private int y;
    //The shown image of the item->in package
    private ConstTexture shownPic;
    private RectangleShape container = new RectangleShape();
    private ConstTexture unOpened;
    private ConstTexture opened;
    private Sprite box = new Sprite();
    //Texts
    private Font font = staticVariable.dialogFont;
    private Text remind = new Text("", font, 12);
    //Reminder
    private ConstTexture reminderPic;
    private Sprite reminderBox = new Sprite();
    private ConstTexture itemRemind;
    private Sprite itemRemindBox = new Sprite();
    //Box shadow
    private ConstTexture shadowPic;
    private Sprite shadow = new Sprite();
    //Size of the items
    private int width = 80;
    private int height = 80;
    //Size of the box on the ground
    private int boxWidth = 120;
    private int boxHeight = 50;
    //Drawable window
    private RenderWindow window;
    //Whether the box is opened
    private boolean isOpened = false;

    private boolean soundOpen = false;
    private boolean enterSound = false;

    private int flag = 0;

    public ItemsOnMap(int type, int x, int y, RenderWindow window)
    {
        this.type = type;
        this.x = x;
        this.y = y;
        this.window = window;
        //Set images of the shown texture
        this.shownPic = staticVariable.items.get(type - 1);
        this.itemRemind = staticVariable.items.get(type - 1);
        this.unOpened = staticVariable.box.get(0);
        this.opened = staticVariable.box.get(1);
        this.reminderPic = staticVariable.itemReminder;
        this.shadowPic = staticVariable.boxShadow;
        //Set the texture of the Sprites
        this.container.setTexture(this.shownPic);
        this.itemRemindBox.setTexture(this.itemRemind);
        this.box.setTexture(this.unOpened);
        this.reminderBox.setTexture(this.reminderPic);
        this.shadow.setTexture(this.shadowPic);
        //Set the origin of the Sprites
        this.container.setOrigin(40, 40);
        this.itemRemindBox.setOrigin(40, 40);
        this.box.setOrigin(60, 25);
        this.reminderBox.setPosition(this.reminderPic.getSize().x / 2, this.reminderPic.getSize().y / 2);
        this.shadow.setOrigin(this.shadowPic.getSize().x / 2, this.shadowPic.getSize().y / 2);
        //Set the scale of the Sprites
        this.box.setScale((float) 0.7, (float) 0.7);
        this.reminderBox.setScale((float) 0.6, (float) 0.6);
        this.itemRemindBox.setScale((float) 0.7, (float) 0.7);
        this.remind.setString("Click to pick up.");
    }

    public void onDraw()
    {
        boolean isClosed = Math.abs(600 - this.x) <= 120 && Math.abs(300 - this.y) <= 100;
        if(isClosed)
            mouseListener();
        this.box.setPosition(this.x, this.y);
        this.shadow.setPosition(this.x - 8, this.y + 40);
        if(!isOpened)
            this.box.setTexture(this.unOpened);
        else if(isOpened)
            this.box.setTexture(this.opened);
        this.window.draw(this.shadow);
        this.window.draw(this.box);
    }

    public void drawOthers(int timer)
    {
        if(isOpened)
        {
            if(timer < 200 && flag == 0)
            {
                this.reminderBox.setPosition(500,350);
                this.itemRemindBox.setPosition(this.reminderBox.getPosition().x + 370,
                        this.reminderBox.getPosition().y + 30);
                if(timer == 199)
                    flag = 1;
                this.window.draw(this.reminderBox);
                this.window.draw(this.itemRemindBox);
            }
            this.box.setTexture(this.opened);
        }
    }

    public void mouseListener()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        boolean isEnter = Math.abs(this.x - mouseX) <= 30 && Math.abs(this.y - mouseY) <= 30;

        if(isEnter)
        {
            if(!enterSound)
            {
                enterSound = true;
                TDMusicPlay.buttonMusic.play();
            }
        }
        else
            enterSound = false;

        if(isEnter && !isOpened)
        {
            this.remind.setPosition(this.x - 60, this.y - 30);
            this.remind.setColor(Color.RED);
            this.window.draw(remind);
        }
        else if(isEnter && isOpened)
        {
            this.remind.setPosition(this.x - 60, this.y - 30);
            this.remind.setColor(Color.RED);
            this.remind.setString("Treasure Box Opened");
            this.window.draw(remind);
        }
        if(isEnter && mouse.isButtonPressed(Mouse.Button.LEFT) && !isOpened)
        {
            isOpened = true;
            OperateDatabase.updateItems(this.type, 1, "Add");
            TDMusicPlay.openTreasure.play();
        }
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public boolean getIsOpened()
    {
        return isOpened;
    }

    public void setOpened(boolean opened)
    {
        isOpened = opened;
    }

    //Get the two variables of this item.
    public ArrayList getTypeAndOpen()
    {
        ArrayList threeVars = new ArrayList();
        threeVars.add(this.type);
        threeVars.add(this.isOpened);
        threeVars.add(this.container);
        return threeVars;
    }
}
