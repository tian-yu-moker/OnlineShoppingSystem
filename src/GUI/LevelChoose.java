package GUI;

import GameDriver.Start;
import GameStarter.mainFrame;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.*;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.*;

import javax.swing.table.TableRowSorter;

import static GameDriver.LevelDetermination.isMiddleStoryFinished;

public class LevelChoose
{
    public int passedLevelNum;
    public boolean changeToAnotherLevel;
    public int level;
    public boolean isStart[] = new boolean[10];

    private boolean sound[] = new boolean[10];
    public LevelChoose(int level)
    {
        this.changeToAnotherLevel = false;
        passedLevelNum = level;
        for(int i = 0; i < 10; i++)
        {
            isStart[i] = false;
            sound[i] = false;
        }
    }

    RectangleShape mapBase_RS = Start.createImage("Images/levelChooIF/mapBase.png", 1500, 800, 0, 0);
    //levelChoo背景图片上显示的关卡地图
    RectangleShape level1_RS = Start.createImage("Images/levelChooIF/1.png", 1185, 710, 135, 60);
    RectangleShape level2_RS = Start.createImage("Images/levelChooIF/2.png", 1185, 710, 135, 60);
    RectangleShape level3_RS = Start.createImage("Images/levelChooIF/3.png", 1185, 710, 135, 60);
    RectangleShape level4_RS = Start.createImage("Images/levelChooIF/4.png", 1185, 710, 135, 60);
    RectangleShape level5_RS = Start.createImage("Images/levelChooIF/5.png", 1185, 710, 135, 60);
    RectangleShape level6_RS = Start.createImage("Images/levelChooIF/6.png", 1185, 710, 135, 60);
    RectangleShape level7_RS = Start.createImage("Images/levelChooIF/7.png", 1185, 710, 135, 60);
    RectangleShape level8_RS = Start.createImage("Images/levelChooIF/8.png", 1185, 710, 135, 60);
    RectangleShape level9_RS = Start.createImage("Images/levelChooIF/9.png", 1185, 710, 135, 60);
    RectangleShape level10_RS = Start.createImage("Images/levelChooIF/10.png", 1185, 710, 135, 60);
    RectangleShape level1Light_RS = Start.createImage("Images/levelChooIF/1light.png", 1185, 710, 135, 60);
    RectangleShape level2Light_RS = Start.createImage("Images/levelChooIF/2light.png", 1185, 710, 135, 60);
    RectangleShape level3Light_RS = Start.createImage("Images/levelChooIF/3light.png", 1185, 710, 135, 60);
    RectangleShape level4Light_RS = Start.createImage("Images/levelChooIF/4light.png", 1185, 710, 135, 60);
    RectangleShape level5Light_RS = Start.createImage("Images/levelChooIF/5light.png", 1185, 710, 135, 60);
    RectangleShape level6Light_RS = Start.createImage("Images/levelChooIF/6light.png", 1185, 710, 135, 60);
    RectangleShape level7Light_RS = Start.createImage("Images/levelChooIF/7light.png", 1185, 710, 135, 60);
    RectangleShape level8Light_RS = Start.createImage("Images/levelChooIF/8light.png", 1185, 710, 135, 60);
    RectangleShape level9Light_RS = Start.createImage("Images/levelChooIF/9light.png", 1185, 710, 135, 60);
    RectangleShape level10Light_RS = Start.createImage("Images/levelChooIF/10light.png", 1185, 710, 135, 60);
    //levelChoo背景图片上显示的关卡名字
    RectangleShape level1Na_RS = Start.createImage("Images/levelChooIF/level1Na.png", 250, 100, 315, 500);
    RectangleShape level2Na_RS = Start.createImage("Images/levelChooIF/level2Na.png", 250, 100, 395, 420);
    RectangleShape level3Na_RS = Start.createImage("Images/levelChooIF/level3Na.png", 250, 100, 380, 310);
    RectangleShape level4Na_RS = Start.createImage("Images/levelChooIF/level4Na.png", 250, 100, 485, 150);
    RectangleShape level5Na_RS = Start.createImage("Images/levelChooIF/level5Na.png", 250, 100, 625, 290);
    RectangleShape level6Na_RS = Start.createImage("Images/levelChooIF/level6Na.png", 250, 100, 685, 380);
    RectangleShape level7Na_RS = Start.createImage("Images/levelChooIF/level7Na.png", 250, 100, 580, 530);
    RectangleShape level8Na_RS = Start.createImage("Images/levelChooIF/level8Na.png", 250, 100, 770, 530);
    RectangleShape level9Na_RS = Start.createImage("Images/levelChooIF/level9Na.png", 250, 100, 935, 430);
    RectangleShape level10Na_RS = Start.createImage("Images/levelChooIF/level10Na.png", 250, 100, 920, 160);
    RectangleShape level1NaLight_RS = Start.createImage("Images/levelChooIF/level1NaLight.png", 250, 100, 315, 500);
    RectangleShape level2NaLight_RS = Start.createImage("Images/levelChooIF/level2NaLight.png", 250, 100, 395, 420);
    RectangleShape level3NaLight_RS = Start.createImage("Images/levelChooIF/level3NaLight.png", 250, 100, 380, 310);
    RectangleShape level4NaLight_RS = Start.createImage("Images/levelChooIF/level4NaLight.png", 250, 100, 485, 150);
    RectangleShape level5NaLight_RS = Start.createImage("Images/levelChooIF/level5NaLight.png", 250, 100, 625, 290);
    RectangleShape level6NaLight_RS = Start.createImage("Images/levelChooIF/level6NaLight.png", 250, 100, 685, 380);
    RectangleShape level7NaLight_RS = Start.createImage("Images/levelChooIF/level7NaLight.png", 250, 100, 580, 530);
    RectangleShape level8NaLight_RS = Start.createImage("Images/levelChooIF/level8NaLight.png", 250, 100, 770, 530);
    RectangleShape level9NaLight_RS = Start.createImage("Images/levelChooIF/level9NaLight.png", 250, 100, 935, 430);
    RectangleShape level10NaLight_RS = Start.createImage("Images/levelChooIF/level10NaLight.png", 250, 100, 920, 160);


    public void Level(RenderWindow window, Mouse mouse) {
        //鼠标
        Vector2i position = mouse.getPosition(window);//得到鼠标光标当前位置（坐标向量），格式如 Vector2i{x=324, y=326}
        int x = position.x;//获取当前鼠标的横坐标
        int y = position.y;//获取当前鼠标的纵坐标


        //levelChoo界面
        //  250, 100, 265, 500);
        //  250, 100, 345, 420);
        //  250, 100, 330, 310);
        //  250, 100, 435, 150);
        //  250, 100, 575, 290);
        //  250, 100, 635, 380);
        //  250, 100, 530, 530);
        //  250, 100, 720, 530);
        //  250, 100, 885, 430);
        //  250, 100, 870, 160)
        boolean level1Area = x >= 265 && x <= 510 && y >= 500 && y <= 600; //250, 100
        boolean level2Area = x >= 345 && x <= 600 && y >= 420 && y <= 520;
        boolean level3Area = x >= 330 && x <= 580 && y >= 310 && y <= 410;
        boolean level4Area = x >= 435 && x <= 690 && y >= 150 && y <= 250;
        boolean level5Area = x >= 580 && x <= 830 && y >= 290 && y <= 390;
        boolean level6Area = x >= 635 && x <= 890 && y >= 380 && y <= 480;
        boolean level7Area = x >= 530 && x <= 780 && y >= 530 && y <= 630;
        boolean level8Area = x >= 720 && x <= 970 && y >= 530 && y <= 630;
        boolean level9Area = x >= 885 && x <= 1130 && y >= 430 && y <= 530;
        boolean level10Area = x >= 870 && x <= 1120 && y >= 160 && y <= 260;
        //boolean DDDDDArea = x>=20 && x<= 100 && y>=300 && y<=400;


        window.draw(mapBase_RS);
        //Level one
        if (level1Area)
        {
            if(passedLevelNum == 0)
            {
                //if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {

                }
                if(!sound[0])
                {
                    sound[0] = true;
                    TDMusicPlay.buttonMusic.play();
                }
            }
            window.draw(level1Light_RS);
            if(mouse.isButtonPressed(Mouse.Button.LEFT))
            {
                if(!mainFrame.isStartFinish)
                {
                    mainFrame.isStartFinish = true;
                }
            }
        } else {
            sound[0] = false;
            window.draw(level1_RS);
        }


        if (passedLevelNum == 1)
        {
            if (level2Area)
            {
                if(!sound[1])
                {
                    sound[1] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level2Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                    changeToAnotherLevel = true;
                    isStart[1] = true;
                }
            } else {
                sound[1] = false;
                window.draw(level2_RS);
            }
        } else if (passedLevelNum == 2 && !isStart[1])
        {
            //System.out.println("num = 2" );
            if (level3Area)
            {
                if(!sound[2])
                {
                    sound[2] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level3Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    changeToAnotherLevel = true;
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                    isStart[2] = true;
                }
            } else {
                sound[2] = false;
                window.draw(level3_RS);
            }
        } else if (passedLevelNum == 3 && !isStart[2])
        {
            if (level4Area)
            {
                if(!sound[3])
                {
                    sound[3] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level4Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                    changeToAnotherLevel = true;
                    isStart[3] = true;
                }
            } else {
                sound[3] = false;
                window.draw(level4_RS);
            }
            //System.out.println("num = 3" );
        } else if (passedLevelNum == 4 && !isStart[3])
        {
            if (level5Area)
            {
                if(!sound[4])
                {
                    sound[4] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level5Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                    changeToAnotherLevel = true;
                    isStart[4] = true;
                }
            } else {
                sound[4] = false;
                window.draw(level5_RS);
            }
            //System.out.println("num = 4" );
        } else if (passedLevelNum == 5 && !isStart[4])
        {
            if (level6Area)
            {
                if(!sound[5])
                {
                    sound[5] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level6Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                    changeToAnotherLevel = true;
                    isStart[5] = true;
                }
            } else {
                sound[5] = false;
                window.draw(level6_RS);
            }
            // System.out.println("num = 5" );
        }
        else if (passedLevelNum == 6 && !isStart[5])
        {
            if (level7Area)
            {
                if(!sound[6])
                {
                    sound[6] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level7Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    if(!mainFrame.isStartFinish)
                    {
                        mainFrame.il.all(window, 7);
                        mainFrame.isStartFinish = true;
                    }
                    changeToAnotherLevel = true;
                    isStart[6] = true;
                }
            } else {
                sound[6] = false;
                window.draw(level7_RS);
            }
            //System.out.println("num = 6" );
        } else if (passedLevelNum == 7 && !isStart[6])
        {
            if (level8Area)
            {
                if(!sound[7])
                {
                    sound[7] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level8Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                    changeToAnotherLevel = true;
                    isStart[7] = true;
                }
            } else {
                sound[7] = false;
                window.draw(level8_RS);
            }
            // System.out.println("num = 7" );
        } else if (passedLevelNum == 8)
        {
            if (level9Area)
            {
                if(!sound[8])
                {
                    sound[8] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level9Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    changeToAnotherLevel = true;
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                }
            } else {
                sound[8] = false;
                window.draw(level9_RS);
            }
        }
        else if (passedLevelNum == 9)
        {
            if (level10Area)
            {
                if(!sound[9])
                {
                    sound[9] = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(level10Light_RS);
                if (mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    changeToAnotherLevel = true;
                    passedLevelNum++;
                    System.out.println("next:" + passedLevelNum);
                    mainFrame.isStartFinish = true;
                }
            } else {
                sound[9] = true;
                window.draw(level10_RS);
            }

        }

        if (level1Area) {
            window.draw(level1NaLight_RS);
        } else {
            window.draw(level1Na_RS);
        }

        if (level2Area) {
            window.draw(level2NaLight_RS);
        } else {
            window.draw(level2Na_RS);
        }

        if (level3Area) {
            window.draw(level3NaLight_RS);
        } else {
            window.draw(level3Na_RS);
        }

        if (level4Area) {
            window.draw(level4NaLight_RS);
        } else {
            window.draw(level4Na_RS);
        }

        if (level5Area) {
            window.draw(level5NaLight_RS);
        } else {
            window.draw(level5Na_RS);
        }

        if (level6Area) {
            window.draw(level6NaLight_RS);
        } else {
            window.draw(level6Na_RS);
        }

        if (level7Area) {
            window.draw(level7NaLight_RS);
        } else {
            window.draw(level7Na_RS);
        }

        if (level8Area) {
            window.draw(level8NaLight_RS);
        } else {
            window.draw(level8Na_RS);
        }

        if (level9Area) {
            window.draw(level9NaLight_RS);
        } else {
            window.draw(level9Na_RS);
        }

        if (level10Area) {
            window.draw(level10NaLight_RS);
        } else {
            window.draw(level10Na_RS);
        }

        if(!mainFrame.isStartFinish) {
            mainFrame.il.all(window, passedLevelNum + 1);
            isMiddleStoryFinished=false;
        }

    }

    public void setPassedLevelNum(int levelNum)
    {
        this.passedLevelNum = levelNum;
    }
}