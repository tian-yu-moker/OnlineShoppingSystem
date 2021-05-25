package GameDriver;

/*
This class aims to put some treasures on the map.
Where uses can find them and click them into package
 */

import Actors.ItemsOnMap;
import org.jsfml.graphics.RenderWindow;

import java.util.ArrayList;

public class addItemsOnMap
{
    //Level of the game.
    private int level;
    //The number of the items, depends on the level.
    private int numbers;
    //
    private RenderWindow window;
    //The position of all the items.
    private ArrayList<Integer> positionX = new ArrayList<Integer>();
    private ArrayList<Integer> positionY = new ArrayList<Integer>();
    private ArrayList<ItemsOnMap> allItems = new ArrayList<ItemsOnMap>();
    private int[] timer;

    public addItemsOnMap(int level, RenderWindow window) {
        this.level = level;
        this.window = window;
        determineNumber(level);
        setItemsOnMap(level);
    }

    //Decide how many items in each level.
    public void determineNumber(int level) {
        if (level == 1)
            numbers = 10;
        else if (level == 3)
            numbers = 8;
        else if(level == 4)
            numbers = 8;
        else if(level == 5)
            numbers = 8;
        else if(level == 7)
            numbers = 10;
        else if(level == 8)
            numbers = 10;
        else if(level == 9)
            numbers = 10;
        else if(level == 10)
            numbers = 10;
        setItemPositions();
    }

    //Set where are the items
    public void setItemPositions()
    {
        if (level == 1)
        {
            positionX.add(2000);
            positionY.add(180);

            positionX.add(1100);
            positionY.add(950);

            positionX.add(2600);
            positionY.add(1500);

            positionX.add(4200);
            positionY.add(1300);

            positionX.add(5200);
            positionY.add(1600);

            positionX.add(5000);
            positionY.add(2000);

            positionX.add(4200);
            positionY.add(2300);

            positionX.add(230);
            positionY.add(100);

            positionX.add(350);
            positionY.add(-200);

            positionX.add(1100);
            positionY.add(-100);
        }
        else if(level == 3)
        {
            positionX.add(300);
            positionY.add(-400);

            positionX.add(1100);
            positionY.add(-300);

            positionX.add(0);
            positionY.add(350);

            positionX.add(600);
            positionY.add(1700);

            positionX.add(2300);
            positionY.add(-600);

            positionX.add(2300);
            positionY.add(600);

            positionX.add(2000);
            positionY.add(1000);

            positionX.add(3500);
            positionY.add(200);
        }
        else if(level == 4)
        {
            positionX.add(-100);
            positionY.add(500);

            positionX.add(1400);
            positionY.add(200);

            positionX.add(700);
            positionY.add(-700);

            positionX.add(1600);
            positionY.add(-1000);

            positionX.add(2500);
            positionY.add(-500);

            positionX.add(600);
            positionY.add(1800);

            positionX.add(300);
            positionY.add(3200);

            positionX.add(0);
            positionY.add(2800);
        }
        else if(level == 5)
        {
            positionX.add(500);
            positionY.add(-500);

            positionX.add(-100);
            positionY.add(-700);

            positionX.add(1100);
            positionY.add(-800);

            positionX.add(1400);
            positionY.add(300);

            positionX.add(2000);
            positionY.add(300);

            positionX.add(3300);
            positionY.add(1700);

            positionX.add(4000);
            positionY.add(900);

            positionX.add(4000);
            positionY.add(0);
        }
        else if(level == 7)
        {
            positionX.add(1140);
            positionY.add(2046);

            positionX.add(1245);
            positionY.add(-96);

            positionX.add(3288);
            positionY.add(639);

            positionX.add(2742);
            positionY.add(1539);

            positionX.add(1932);
            positionY.add(3495);

            positionX.add(1038);
            positionY.add(6042);

            positionX.add(3165);
            positionY.add(5952);

            positionX.add(6246);
            positionY.add(4992);

            positionX.add(6972);
            positionY.add(4326);

            positionX.add(6087);
            positionY.add(3237);
        }
        else if(level == 8)
        {
            positionX.add(2928);
            positionY.add(597);

            positionX.add(2733);
            positionY.add(1536);

            positionX.add(1155);
            positionY.add(1290);

            positionX.add(-201);
            positionY.add(1212);

            positionX.add(3435);
            positionY.add(2373);

            positionX.add(2991);
            positionY.add(4140);

            positionX.add(4437);
            positionY.add(4704);

            positionX.add(2748);
            positionY.add(-9);

            positionX.add(657);
            positionY.add(3456);

            positionX.add(4902);
            positionY.add(4581);
        }
        else if(level == 9)
        {
            positionX.add(177);
            positionY.add(-33);

            positionX.add(1972);
            positionY.add(561);

            positionX.add(1569);
            positionY.add(1458);

            positionX.add(-201);
            positionY.add(1122);

            positionX.add(1602);
            positionY.add(2412);

            positionX.add(2349);
            positionY.add(2304);

            positionX.add(3411);
            positionY.add(771);

            positionX.add(3693);
            positionY.add(1899);

            positionX.add(426);
            positionY.add(2805);

            positionX.add(432);
            positionY.add(1890);
        }
        else if(level == 10)
        {
            positionX.add(828);
            positionY.add(648);

            positionX.add(702);
            positionY.add(-510);

            positionX.add(2205);
            positionY.add(1620);

            positionX.add(3942);
            positionY.add(234);

            positionX.add(3084);
            positionY.add(-771);

            positionX.add(2757);
            positionY.add(2718);

            positionX.add(3954);
            positionY.add(2724);

            positionX.add(159);
            positionY.add(3096);

            positionX.add(723);
            positionY.add(2142);

            positionX.add(3396);
            positionY.add(1983);
        }
        timer = new int[numbers];
        for(int i = 0; i < numbers; i++)
            timer[i] = 0;
    }

    //Set instances of items
    public void setItemsOnMap(int level)
    {
        for (int i = 0; i < numbers; i++)
        {
            int num = (int) (Math.random() * (5 - 1 + 1) + 1);
            int random = (int) (Math.random() * (10 - 1 + 1) + 1);
            if(random > 9)
                num = (int) (Math.random() * (11 - 8 + 1) + 8);
            allItems.add(new ItemsOnMap(num, positionX.get(i), positionY.get(i), this.window));
        }
    }

    public void onDraw()
    {
        //System.out.println(timer);
        for (int i = 0; i < numbers; i++)
            this.allItems.get(i).onDraw();
    }

    public void drawOthers()
    {
        for (int i = 0; i < numbers; i++)
        {
            if(this.allItems.get(i).getIsOpened())
            {
                timer[i]++;
                if(timer[i] == 201)
                    timer[i] = 0;
            }
            this.allItems.get(i).drawOthers(timer[i]);
        }
    }

    public void setPositionX(int moveX)
    {
        for (int i = 0; i < numbers; i++)
            this.allItems.get(i).setX(this.allItems.get(i).getX() + moveX);
    }

    public void setPositionY(int moveY)
    {
        for (int i = 0; i < numbers; i++)
            this.allItems.get(i).setY(this.allItems.get(i).getY() + moveY);
    }

    public ArrayList getThreeVariable()
    {
        ArrayList allVars = new ArrayList();
        for (int i = 0; i < numbers; i++)
            allVars.add(this.allItems.get(i).getTypeAndOpen());
        return allVars;
    }

}
