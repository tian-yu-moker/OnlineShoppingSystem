package Actors;

/*
This class aims to control the behaviors and actions of NPC in this game.
 */

import GUI.Dialog;
import GUI.dialogTxtReader;
import GameDriver.npcItems;
import GameDriver.staticVariable;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

public class NPCs implements Runnable
{
    //The position of NPCs
    private int x;
    private int y;
    //Type of NPC
    private int type;
    private int level;
    private int number;

    //Width & height of picture.
    private int width;
    private int height;

    //Clicked range of the NPCs, only the mouse enter this range, the NPC can be clicked.
    private int clickX;
    private int clickY;

    //The container & shown picture of the NPCs
    private Sprite container = new Sprite();

    private Sprite icon = new Sprite();
    private ConstTexture iconPic;
    //The index of shown picture
    private int indexX;
    private int indexY;
    //Size of shown picture.
    private int maxX;
    private int maxY;
    //The drawable window.
    private RenderWindow window;
    //The mouse listener of NPC
    private Mouse mouse = null;
    private boolean isEnter = false;
    //Direction is left

    //Threads
    private Thread thread;
    //Load dialog content & GUI
    private dialogTxtReader dialogContents = new dialogTxtReader();
    public Dialog curDia;
    private boolean isDraw = false;
    //The corresponding item of this npc.
    public npcItems thisItem;

    private boolean isEnd = false;


    public NPCs(int level, int type, int number, int x, int y, RenderWindow window)
    {
        this.type = type;
        this.level = level;
        this.number = number;
        this.window = window;
        this.indexX = 0;
        this.indexY = 0;
        this.x = x;
        this.y = y;

        decideSize(type, level, number);
        loadImage(type);

        this.thisItem = new npcItems(level, number, window);

        curDia = new Dialog(this.level, this.number, this.window, this.dialogContents);

        thread = new Thread(this);
        thread.start();
    }

    public void decideSize(int type, int level, int number)
    {
        if(level == 1)
        {
            if(type == 1)
            {
                this.width = 99;
                this.height = 122;
            }
            else if(type == 2)
            {
                this.width = 82;
                this.height = 130;
            }
            else if(type == 3)
            {
                this.width = 156;
                this.height = 180;
            }
        }
        else if(level == 3)
        {
            if(type == 4)
            {
                this.width = 166;
                this.height = 200;
            }
            else if(type == 5)
            {
                this.width = 216;
                this.height = 200;
            }
        }
        else if(level == 4)
        {
            if(type == 2)
            {
                this.width = 82;
                this.height = 130;
            }
            else if(type == 6)
            {
                this.width = 168;
                this.height = 159;
            }
        }
        else if(level == 5)
        {
            if(type == 4)
            {
                this.width = 166;
                this.height = 200;
            }
            else if(type == 3)
            {
                this.width = 156;
                this.height = 180;
            }
        }
        else if(level == 7)
        {
            if(type == 7)
            {
                this.width = 148;
                this.height = 187;
            }
            else if(type == 5)
            {
                this.width = 216;
                this.height = 200;
            }
        }
        else if(level == 8)
        {
            if(type == 4)
            {
                this.width = 166;
                this.height = 200;
            }
            else if(type == 5)
            {
                this.width = 216;
                this.height = 200;
            }
        }
        else if(level == 9)
        {
            if(type == 8)
            {
                this.width = 180;
                this.height = 163;
            }
            else if(type == 7)
            {
                this.width = 148;
                this.height = 187;
            }
        }
        else if(level == 10)
        {
            if(type == 9)
            {
                this.width = 141;
                this.height = 180;
            }
            else if(type == 10)
            {
                this.width = 164;
                this.height = 160;
            }
        }

        this.clickX = 50;
        this.clickY = 40;
        this.dialogContents.readContent(level, number);
    }

    //Load picture and set attribution of NPC
    public void loadImage(int type)
    {
        this.iconPic = staticVariable.dialogIcon;
        this.maxX = staticVariable.NPCs.get(type - 1).getSize().x;
        this.maxY = staticVariable.NPCs.get(type - 1).getSize().y;
        this.container.setTexture(staticVariable.NPCs.get(type - 1));
        this.icon.setTexture(this.iconPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        this.container.setOrigin(this.width / 2, this.height / 2);
        this.icon.setOrigin(20, 20);
        this.icon.setScale((float) 0.6, (float) 0.6);
    }

    //Change the index of the picture
    public void changIndex()
    {
        indexX++;
        if(indexX == this.maxX / this.width)
        {
            indexX = 0;
            indexY++;
        }

        if(indexY == this.maxY / this.height)
        {
            indexY = 0;
            indexX = 0;
        }
    }

    //Draw the NPCs, can be invoked outside.
    public void onDraw()
    {
        this.container.setPosition(this.x, this.y);
        mouseListener();
        this.window.draw(this.container);
    }

    public void controller()
    {
        changIndex();
        this.container.setTexture(staticVariable.NPCs.get(type - 1));
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
    }

    //Listen the action of the mouse, if clicked the mouse, the start to talk with player
    public void mouseListener()
    {
        int mouseX = this.mouse.getPosition(this.window).x;
        int mouseY = this.mouse.getPosition(this.window).y;
        isEnter = (this.x + this.clickX  >= mouseX && this.x - clickX <= mouseX) &&
                (this.y + clickY >= mouseY && this.y - clickY <= mouseY);

        if(isEnter)
        {
            this.icon.setPosition(this.x, this.y - 80);
            this.window.draw(icon);
        }
        //This part adds the behaviors when clicking the NPCs.
        if(isEnter && mouse.isButtonPressed(Mouse.Button.LEFT)
                && Math.abs(this.x - 600) < 150 && Math.abs(this.y - 300) < 100 && !isDraw)
        {
            //The variable dialogContents stores all the dialog contents between player.
            //Here create the dialog GUI and start talking with each other.
            isDraw = true;
            curDia.setIsStart(true);
        }

        this.thisItem.mouseListener();
        this.thisItem.getMissionState(curDia.getMissionStatus());
        boolean isCarried = this.thisItem.getIsTaken();

        boolean isFinish = curDia.isFinish();

        if (isDraw & !isFinish)
        {
            if(isCarried)
                curDia.setMissionFinished(true);
            curDia.onDraw(this.window);
            curDia.mouseListener();
        }

        if(isFinish)
        {
            isDraw = false;
            this.curDia.setIsFinish(false);
        }
        isEnter = false;
    }


    public void setWindow(RenderWindow window)
    {
        this.window = window;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setEnd()
    {
        this.isEnd = true;
    }
    @Override
    public void run()
    {
        while(!isEnd)
        {
            controller();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
