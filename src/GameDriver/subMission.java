package GameDriver;

/*
This class aims to statistic the sub-missions from different NPCs in different levels
 */

import org.jsfml.graphics.*;

public class subMission
{
    //Level number
    private int level;
    //If the mission is accepted.
    private boolean[] isAccept = new boolean[10];
    //Panel of Mission list
    private ConstTexture shownPic;
    private Sprite container = new Sprite();
    private RenderWindow window;
    private Font missionFont = staticVariable.dialogFont;
    private Text missionText;
    private String[] missionDescription = new String[10];
    //Decide whether the mission is finished, if so, stop display.
    private boolean[] isMissionFinished = new boolean[10];


    public subMission()
    {
        this.missionText = new Text("", missionFont, 12);
        this.shownPic = staticVariable.missionList;
        this.container.setTexture(this.shownPic);
        this.container.setOrigin(this.shownPic.getSize().x / 2, this.shownPic.getSize().y / 2);
        this.container.setScale((float)0.8, (float) 0.9);
        missionText.setString("No Missions.");
        for(int i = 0; i < 10; i++)
        {
            this.isAccept[i] = false;
            this.missionDescription[i] = "";
            isMissionFinished[i] = false;
        }
    }

    //Set which level and which NPC's mission is accepted
    public void setText(int level, int index)
    {
        if(level == 1)
        {
            if(index == 0)
                missionDescription[index] = "Task: Ring back";
            else if(index == 1)
                missionDescription[index] = "Task: Toy back";
            else if(index == 2)
                missionDescription[index] = "Task: Life core";
        }
        else if(level == 3)
        {
            if(index == 0)
                missionDescription[index] = "Task: Treasure";
            else if(index == 1)
                missionDescription[index] = "Task: Sword back";
        }
        else if(level == 4)
        {
            if(index == 0)
                missionDescription[index] = "Task: Umbrella";
            else if(index == 1)
                missionDescription[index] = "Task: Treasure";
        }
        else if(level == 5)
        {
            if(index == 0)
                missionDescription[index] = "Task: Treasure";
            else if(index == 1)
                missionDescription[index] = "Task: Money Back";
        }
        else if(level == 7)
        {
            if(index == 0)
                missionDescription[index] = "Task: Ring back.";
            else if(index == 1)
                missionDescription[index] = "Task: Toy back";
        }
        else if(level == 8)
        {
            if(index == 0)
                missionDescription[index] = "Task: Weapon back.";
            else if(index == 1)
                missionDescription[index] = "Task: Find Herbs";
        }
        else if(level == 9)
        {
            if(index == 0)
                missionDescription[index] = "Task: Artifact";
            else if(index == 1)
                missionDescription[index] = "Task: Find Map";
        }
        else if(level == 10)
        {
            if(index == 0)
                missionDescription[index] = "Task: Find Key";
            else if(index == 1)
                missionDescription[index] = "Task: Treasure";
        }
    }

    public void setWindow(RenderWindow window)
    {
        this.window = window;
    }

    public void onDraw(int level, boolean[] missionStatus, boolean[] isMissionFinished)
    {
        //this.isAccept = missionStatus;
        for(int i = 0; i < missionStatus.length; i++)
        {
            this.setIsAccept(i, missionStatus[i]);
            this.setIsMissionFinished(i, isMissionFinished[i]);
            if(isAccept[i])
                setText(level, i);
        }
        String texts = null;
        int count = 0;
        for(int i = 0; i < this.isAccept.length; i++)
        {
            if(isAccept[i] && !this.isMissionFinished[i])
            {
                if(texts == null)
                    texts = this.missionDescription[i] + "\n";
                else
                    texts = texts + this.missionDescription[i] + "\n";
                count++;
            }
        }
        if(count == 0)
            this.missionText.setString("No Missions.");
        else
            this.missionText.setString(texts);
        this.container.setPosition(1130, 135);
        this.missionText.setPosition(this.container.getPosition().x - 58, this.container.getPosition().y - 70);
        this.window.draw(this.container);
        this.window.draw(this.missionText);
    }

    public void setIsAccept(int i, boolean flag)
    {
        this.isAccept[i] = flag;
    }

    public void setIsMissionFinished(int i, boolean flag)
    {
        this.isMissionFinished[i] = flag;
    }
}
