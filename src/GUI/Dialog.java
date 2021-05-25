package GUI;

/*
This class is used to create dialog GUI between play and NPCs.
Click button on the dialog can go to next or previous piece of dialog.
Provide select panel, which may lead to different result with that NPC.
 */

import DatabaseManager.OperateDatabase;
import GameDriver.Driver;
import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.window.Mouse;

public class Dialog
{
    //Font for the dialog
    private Font contentFont  = new Font();
    private Font name  = new Font();
    //Texture of the dialog GUI
    //Button & Background
    private ConstTexture shownBck;
    private ConstTexture shownBut;
    private ConstTexture shownButEntered;
    private ConstTexture yes;
    private ConstTexture no;
    private ConstTexture yesEnter;
    private ConstTexture noEnter;
    //Container
    private Sprite background = new Sprite();
    private Sprite button = new Sprite();
    private Sprite yesButton = new Sprite();
    private Sprite noButton = new Sprite();
    //The context of the dialog
    private Text dialogContent;
    private dialogTxtReader txt;
    //Level and number of the NPC.
    private int level;
    private int number;
    //Window & mouse
    private RenderWindow window;
    private Mouse mouse = null;
    //Decide whether the dialog is finished
    private boolean isStart = false;
    private boolean isFinish = false;
    //Decide player or NPC's talk
    private int index = 0;
    private int isClick = 0;
    private int isClickDecide = 0;

    //Selected panel
    //Whether the buttons are created.
    private boolean isCreated = false;
    //Whether the mission is accepted.
    private boolean missionStatus = false;
    //Whether the mission is finished
    private boolean missionFinished = false;
    //Whether the player get rewards.
    private boolean isRewarded = false;
    private int rewardTime = 0;
    //Whether the select is started, if so, the two buttons are disappeared.
    private boolean isSelectBegin = false;
    private boolean isEnd = false;

    //Animation of the picture.
    private int width;
    private int height;
    private int indexX = 0;
    private int indexY = 0;
    private int maxX;
    private int maxY;
    private int animation = 0;
    private boolean isAniFinish = false;

    private boolean sound1 = false;
    private boolean sound2 = false;
    private boolean sound3 = false;

    private boolean clickSound1 = false;
    private boolean clickSound2 = false;
    private boolean dialogOpenSound = false;
    //The reward money of the npc, if the mission is accomplished
    private int rewardMoney = 0;

    public Dialog(int level, int number, RenderWindow window, dialogTxtReader txt)
    {
        this.level = level;
        this.number = number;
        this.window = window;
        this.txt = txt;
        //Texture
        this.shownBck = staticVariable.dialogs.get(0);
        this.shownBut = staticVariable.buttons.get(0);
        this.shownButEntered = staticVariable.buttons.get(1);
        this.yes = staticVariable.buttons.get(2);
        this.yesEnter = staticVariable.buttons.get(3);
        this.no = staticVariable.buttons.get(4);
        this.noEnter = staticVariable.buttons.get(5);
        contentFont = staticVariable.dialogFont;
        dialogContent = new Text("", contentFont, 20);
        dialogContent.setColor(Color.BLACK);
        //Sprites
        background.setTexture(this.shownBck);
        //button1.setTexture(this.shownBut1);
        button.setTexture(this.shownBut);
        yesButton.setTexture(this.yes);
        noButton.setTexture(this.no);

        background.setOrigin(600, 180);

        button.setOrigin(this.shownBut.getSize().x / 2, this.shownBut.getSize().y / 2);
        yesButton.setOrigin(this.yes.getSize().x / 2, this.yes.getSize().y / 2);
        noButton.setOrigin(this.no.getSize().x / 2, this.no.getSize().y / 2);

        button.setScale((float) 0.6, (float) 0.6);
        this.width = 1200;
        this.height = 360;
        this.maxX = 7200;
        this.maxY = 1440;
        background.setTextureRect(new IntRect(width * indexX, height * indexY, width, height));
    }

    public void onDraw(RenderWindow window)
    {
        //Change the index of the panel.
        changIndex();
        background.setTextureRect(new IntRect(width * indexX, height * indexY, width, height));

        background.setPosition(700,500);
        dialogContent.setPosition(250, 440);
        button.setPosition(1000, 550);
        this.window.draw(background);
        if(isAniFinish)
        {
            this.window.draw(dialogContent);
            if(!isCreated)
                this.window.draw(button);
        }

        if(isCreated)
        {
            decideListener();
            this.yesButton.setPosition(600, 550);
            this.noButton.setPosition(800, 550);
            this.window.draw(this.yesButton);
            this.window.draw(this.noButton);
        }
        if(!isCreated)
            isClickDecide = 0;
    }

    public void mouseListener()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        //boolean point for the button.
        boolean isEnter = Math.abs(mouseX - button.getPosition().x) <= 60 &&
                Math.abs(mouseY - button.getPosition().y) <= 60;
        if(isEnter)
        {
            if(!sound1)
            {
                TDMusicPlay.buttonMusic.play();
                sound1 = true;
            }
            this.button.setTexture(this.shownButEntered);
        }
        else if(!isEnter)
        {
            sound1 = false;
            this.button.setTexture(this.shownBut);
        }

        if(isClick == 0)
            clickSound1 = false;

        if(isEnter && !this.isFinish && mouse.isButtonPressed(Mouse.Button.LEFT) && isClick == 0 && !isSelectBegin)
        {
            if(!clickSound1)
            {
                clickSound1 = true;
                TDMusicPlay.selectMusic.play();
            }
            index++;
            isClick++;
            if(index >= txt.getCommunication().length && isRewarded && rewardTime != 0)
                this.isFinish = true;
            if(index > txt.getCommunication().length - 3 && !missionFinished)
            {
                index--;
                this.isFinish = true;
                isStart = false;
                animation = 0;
                isAniFinish = false;
                dialogOpenSound = false;
                indexX = 0;
                indexY = 0;
            }
            //If mission finished, get rewards, only once.
            else if(isRewarded && missionFinished && rewardTime != 0)
                isEnd = true;
            else if(isRewarded && missionFinished)
                rewardTime++;
            else if(missionFinished && !isRewarded)
            {
                rewardMoney = (int) (Math.random() * (80 - 50 + 1) + 50);
                TDMusicPlay.addMoney.play();
                Driver.finishedMission++;
                OperateDatabase.updateMoney(rewardMoney);
                isRewarded = true;
            }
        }


        if(index < txt.getCommunication().length && index >= 0)
        {
            int flag = 0;
            //Start to select whether accept the mission.
            if(this.txt.getCommunication()[index].substring(0, 1).equals("S") && !isCreated)
            {
                isSelectBegin = true;
                flag = index;
                isCreated = true;
            }
            this.dialogContent.setString(txt.getCommunication()[index]);

            //If get the mission, display the following contents.
            if(this.missionStatus && index > flag && index < this.txt.getCommunication().length - 4)
                this.dialogContent.setString(txt.getCommunication()[index]);

            if(this.txt.getCommunication()[index].substring(0, 1).equals("S"))
                this.dialogContent.setString("Choosing: " + txt.getCommunication()[index].substring(3, txt.getCommunication()[index].length() - 1));
        }

        if(isEnd)
            index = txt.getCommunication().length - 1;
    }

    public void decideListener()
    {
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        //System.out.println(mouseX + " " + mouseY);
        boolean isEnterYes = Math.abs(mouseX - this.yesButton.getPosition().x) < 30 &&
                Math.abs(mouseY - this.yesButton.getPosition().y) < 30;
        boolean isEnterNo = Math.abs(mouseX - this.noButton.getPosition().x) < 30 &&
                Math.abs(mouseY - this.noButton.getPosition().y) < 30;

        if(isEnterYes)
        {
            if(!sound2)
            {
                sound2 = true;
                TDMusicPlay.buttonMusic.play();
            }
            this.yesButton.setTexture(this.yesEnter);
        }
        else if(!isEnterYes)
        {
            sound2 = false;
            this.yesButton.setTexture(this.yes);
        }

        if(isEnterNo)
        {
            if(!sound3)
            {
                sound3 = true;
                TDMusicPlay.buttonMusic.play();
            }
            this.noButton.setTexture(this.noEnter);
        }
        else if(!isEnterNo)
        {
            sound3 = false;
            this.noButton.setTexture(this.no);
        }

        //Control the click sound
        if(isClickDecide == 0)
            clickSound2 = false;

        if(mouse.isButtonPressed(Mouse.Button.LEFT) && isEnterYes && isClickDecide == 0)
        {
            if(!clickSound2)
            {
                clickSound2 = true;
                TDMusicPlay.buildMusic.play();
            }
            isSelectBegin = false;
            missionStatus = true;
            index++;
            isCreated = false;
            isClickDecide++;
        }
        else if(mouse.isButtonPressed(Mouse.Button.LEFT) && isEnterNo && isClickDecide == 0)
        {
            if(!clickSound2)
            {
                clickSound2 = true;
                TDMusicPlay.buildMusic.play();
            }
            isSelectBegin = false;
            missionStatus = false;
            isFinish = true;
            isStart = false;
            isCreated = false;
            index = 0;
            isClickDecide++;
            animation = 0;
            isAniFinish = false;
            dialogOpenSound = false;
            indexX = 0;
            indexY = 0;
        }
    }

    public boolean isFinish()
    {
        return isFinish;
    }

    public void setIsFinish(boolean isFinish)
    {
        this.isFinish = isFinish;
    }

    public void setIsStart(boolean isStart)
    {
        this.isStart = isStart;
    }

    public void setMissionFinished(boolean missionFinished)
    {
        this.missionFinished = missionFinished;
    }

    public boolean getStart()
    {
        return this.isStart;
    }

    public void setIsClick(int isClick)
    {
        this.isClick = isClick;
    }

    public int getIsClick()
    {
        return this.isClick;
    }

    //These 4 are used for determine the status of the mission.
    public int getNumber()
    {
        return this.number;
    }

    public int getLevel()
    {
        return this.level;
    }

    public boolean getMissionStatus()
    {
        return this.missionStatus;
    }

    public boolean getMissionFinished()
    {
        return this.missionFinished;
    }

    public void changIndex()
    {
        if(animation == 0)
        {
            if(!dialogOpenSound)
            {
                dialogOpenSound = true;
                TDMusicPlay.dialogOpen.play();
            }
            animation++;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (!isAniFinish)
                    {
                        indexX++;
                        if (indexX == maxX / width)
                        {
                            indexX = 0;
                            indexY++;
                        }
                        if (indexX == 5 && indexY == 3)
                        {
                            isAniFinish = true;
                            break;
                        }
                        try {
                            Thread.sleep(90);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
