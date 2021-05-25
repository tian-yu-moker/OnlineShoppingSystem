package GameDriver;

/*
This class aims to control the required items needed by NPCs.
Use the mouse and click the item, then they will be carried
Then if the item is carried and the player goes back to talk with that npc,
the mission will be finished.
 */

import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.*;
import org.jsfml.window.Mouse;

public class npcItems
{
    //The position of items.
    private int x;
    private int y;
    //For which level and which NPC.
    private int level;
    private int number;
    //Picture and container of the item.
    private ConstTexture unOpened;
    private ConstTexture opened;
    private Sprite container = new Sprite();
    private Text warn = new Text("",staticVariable.dialogFont, 12);
    private ConstTexture reminder;
    private Sprite reminderContianer = new Sprite();
    private int width;
    private int height;
    //Drawable window and mouse listener
    private RenderWindow window;
    private Mouse mouse = null;
    private boolean isTaken = false;
    private boolean isRemind = false;
    private boolean missionState = false;
    private boolean able = false;
    //Sound controller
    private boolean enterSound = false;

    public npcItems(int level, int number, RenderWindow window)
    {
        this.level = level;
        this.number = number;
        this.window = window;
        this.unOpened = staticVariable.npcItems.get(0);
        this.opened = staticVariable.npcItems.get(1);
        this.reminder = staticVariable.hand;
        this.container.setTexture(this.unOpened);
        this.reminderContianer.setTexture(this.reminder);
        this.width = 120;
        this.height = 100;
        this.container.setOrigin(60, 50);
        this.reminderContianer.setOrigin(25, 25);
        this.container.setScale((float) 0.7, (float) 0.7);
        this.reminderContianer.setScale((float) 0.7, (float) 0.7);
        this.warn.setColor(Color.RED);
        this.warn.setString("     Cannot open." + "\n" + "Mission Unaccepted");
        this.loadSettings();
    }

    //Decide the position of the item.
    public void loadSettings()
    {
        if(level == 1)
        {
            if(number == 1)
            {
                this.x = 3200;
                this.y = 3300;
            }
            if(number == 2)
            {
                this.x = 1900;
                this.y = 1300;
            }
            if(number == 3)
            {
                this.x = 4650;
                this.y = 2100;
            }
        }
        else if(level == 3)
        {
            if(number == 1)
            {
                this.x = 3500;
                this.y = -600;
            }
            if(number == 2)
            {
                this.x = 750;
                this.y = 1800;
            }
        }
        else if(level == 4)
        {
            if(number == 1)
            {
                this.x = 600;
                this.y = 2000;
            }
            if(number == 2)
            {
                this.x = 2500;
                this.y = -300;
            }
        }
        else if(level == 5)
        {
            if(number == 1)
            {
                this.x = 950;
                this.y = -600;
            }
            if(number == 2)
            {
                this.x = 150;
                this.y = 2600;
            }
        }
        else if(level == 7)
        {
            if(number == 1)
            {
                this.x = 7305;
                this.y = 5127;
            }
            if(number == 2)
            {
                this.x = 2037;
                this.y = 4173;
            }
        }
        else if(level == 8)
        {
            if(number == 1)
            {
                this.x = 1653;
                this.y = 1623;
            }
            if(number == 2)
            {
                this.x = 3063;
                this.y = 3315;
            }
        }
        else if(level == 9)
        {
            if(number == 1)
            {
                this.x = 981;
                this.y = 2154;
            }
            if(number == 2)
            {
                this.x = 3708;
                this.y = -822;
            }
        }
        else if(level == 10)
        {
            if(number == 1)
            {
                this.x = 2895;
                this.y = 597;
            }
            if(number == 2)
            {
                this.x = 2634;
                this.y = 3429;
            }
        }
    }    

    //Draw the item on the window
    public void onDraw()
    {
        // mouseListener();
        this.reminderContianer.setPosition(this.x, this.y + 30);
        if(isTaken && able)
            this.container.setTexture(this.opened);
        this.container.setPosition(this.x, this.y);
        this.window.draw(this.container);
        if(isRemind)
            this.window.draw(this.reminderContianer);
    }

    public void mouseListener()
    {
        int mouseX = this.mouse.getPosition(this.window).x;
        int mouseY = this.mouse.getPosition(this.window).y;
        boolean isEnter = Math.abs(this.x - mouseX) <= 30 && Math.abs(this.y - mouseY) <= 30;
        if(isEnter)
        {
            if(!enterSound)
            {
                enterSound = true;
                TDMusicPlay.buildMusic.play();
            }
            isRemind = true;
        }
        else if(!isEnter)
        {
            enterSound = false;
            isRemind = false;
        }
        if(isEnter && this.mouse.isButtonPressed(Mouse.Button.LEFT) && missionState)
        {
            TDMusicPlay.openTreasure.play();
            this.isTaken = true;
            able = true;
        }
        else if(isEnter && !missionState)
        {
            this.warn.setPosition(this.x - 60, this.y - 70);
            this.window.draw(this.warn);
        }
    }

    public boolean getIsTaken()
    {
        return this.isTaken;
    }

    public int getX()
    {
        return this.x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void getMissionState(boolean missionState)
    {
        this.missionState = missionState;
    }
}
