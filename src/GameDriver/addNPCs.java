package GameDriver;

/*
This class aims to add NPCs for different levels.
Each level the type and action of NPCs are different.
 */

import Actors.NPCs;
import org.jsfml.graphics.RenderWindow;

import java.util.ArrayList;
import java.util.List;

public class addNPCs
{
    //Level number
    private int level;
    //List of NPCs for a certain level.
    private List<NPCs> allNPCs = new ArrayList<NPCs>();
    //Window for all NPCs
    private RenderWindow window;
    //Position of NPCs for different level.
    private List<Integer> positionX = new ArrayList<Integer>();
    private List<Integer> positionY = new ArrayList<Integer>();
    //The mission list panel.
    private subMission missions = new subMission();


    //Set game level.
    public void setLevel(int level)
    {
        allNPCs = new ArrayList<NPCs>();
        positionX = new ArrayList<Integer>();
        positionY = new ArrayList<Integer>();
        this.level = level;
        setNPCs(level);
    }

    public void setWindow(RenderWindow window)
    {
        this.window = window;
        missions.setWindow(window);
    }

    public void setNPCs(int level)
    {
        //System.out.println(level);
        //NPCs' position
        setPositions(level);
        if(level == 1)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(1, 1, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            //one.setWindow(this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(1, 2, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            //one.setWindow(this.window);
            this.allNPCs.add(two);
            NPCs three  = new NPCs(1, 3, 3, this.positionX.get(2), this.positionY.get(2), this.window);
            //one.setWindow(this.window);
            this.allNPCs.add(three);
        }
        else if(level == 3)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(3, 4, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(3, 5, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            this.allNPCs.add(two);
        }
        else if(level == 4)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(4, 2, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(4, 6, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            this.allNPCs.add(two);
        }
        else if(level == 5)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(5, 3, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(5, 4, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            this.allNPCs.add(two);
        }
        else if(level == 7)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(7, 5, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(7, 7, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            this.allNPCs.add(two);
        }
        else if(level == 8)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(8, 4, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(8, 5, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            this.allNPCs.add(two);
        }
        else if(level == 9)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(9, 8, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(9, 7, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            this.allNPCs.add(two);
        }
        else if(level == 10)
        {
            //This part can choose different type of NPCs.
            NPCs one  = new NPCs(10, 9, 1, this.positionX.get(0), this.positionY.get(0), this.window);
            this.allNPCs.add(one);
            NPCs two  = new NPCs(10, 10, 2, this.positionX.get(1), this.positionY.get(1), this.window);
            this.allNPCs.add(two);
        }
    }

    //This class aims to add NPCs of game in different levels
    public void setPositions(int level)
    {
        //Level 1 positions of NPCs
        if(level == 1)
        {
            //Position of NPC 1
            positionX.add(1900);
            positionY.add(300);
            //Position of NPC 2
            positionX.add(1300);
            positionY.add(2000);
            //Position of NPC 3
            positionX.add(3200);
            positionY.add(3000);
        }
        else if(level == 3)
        {
            //Position of NPC 1
            positionX.add(900);
            positionY.add(-680);
            //Position of NPC 2
            positionX.add(2500);
            positionY.add(-600);
        }
        else if(level == 4)
        {
            //Position of NPC 1
            positionX.add(500);
            positionY.add(-700);
            //Position of NPC 2
            positionX.add(300);
            positionY.add(3000);
        }
        else if(level == 5)
        {
            //Position of NPC 1
            positionX.add(0);
            positionY.add(-700);
            //Position of NPC 2
            positionX.add(1500);
            positionY.add(300);
        }
        else if(level == 7)
        {
            //Position of NPC 1
            positionX.add(6177);
            positionY.add(-504);
            //Position of NPC 2
            positionX.add(-102);
            positionY.add(4704);
        }
        else if(level == 8)
        {
            //Position of NPC 1
            positionX.add(2049);
            positionY.add(99);
            //Position of NPC 2
            positionX.add(1344);
            positionY.add(2190);
        }
        else if(level == 9)
        {
            //Position of NPC 1
            positionX.add(504);
            positionY.add(636);
            //Position of NPC 2
            positionX.add(1257);
            positionY.add(660);
        }
        else if(level == 10)
        {
            //Position of NPC 1
            positionX.add(-201);
            positionY.add(-411);
            //Position of NPC 2
            positionX.add(1299);
            positionY.add(3426);
        }
    }

    public List<NPCs> getAllNPCs()
    {
        return this.allNPCs;
    }

    public void onDraw()
    {
        int level = this.level;
        int[] numbers = new int[allNPCs.size()];
        boolean[] missionStatus = new boolean[allNPCs.size()];
        boolean[] missionFinished = new boolean[allNPCs.size()];

        for(int i = 0; i < allNPCs.size(); i++)
        {
            allNPCs.get(i).onDraw();
            missionStatus[i] = allNPCs.get(i).curDia.getMissionStatus();
            missionFinished[i] = allNPCs.get(i).curDia.getMissionFinished();
        }
        this.missions.onDraw(level, missionStatus, missionFinished);
    }

    public void setX(int x)
    {
        for(int i = 0; i < allNPCs.size(); i++)
        {
            allNPCs.get(i).setX(allNPCs.get(i).getX() + x);
            allNPCs.get(i).thisItem.setX(allNPCs.get(i).thisItem.getX() + x);
        }
    }

    public void setY(int y)
    {
        for(int i = 0; i < allNPCs.size(); i++)
        {
            allNPCs.get(i).setY(allNPCs.get(i).getY() + y);
            allNPCs.get(i).thisItem.setY(allNPCs.get(i).thisItem.getY() + y);
        }
    }
}
