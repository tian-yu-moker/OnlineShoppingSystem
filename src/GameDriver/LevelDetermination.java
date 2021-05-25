package GameDriver;


import Actors.Blood;
import DatabaseManager.OperateDatabase;
import GUI.*;
import Shop.Shop;
import TowerDenfense.AddMonsters;
import TowerDenfense.TDLoadMusic;
import TowerDenfense.TDMusicPlay;
import TowerDenfense.TD_MainFrame;
import WeatherAlgorithms.Lightning;
import WeatherAlgorithms.Raining;
import WeatherAlgorithms.Sand;
import WeatherAlgorithms.Snowing;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;
import GameStarter.mainFrame;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;


public class LevelDetermination
{
    public static int level = 1;
    public Driver driver;
    public TD_MainFrame towerDefense;
    private String heroType;
    private RenderWindow window;
    private Mouse mouse = null;
    private int attackNumber;
    //Game over and restart
    public Gameover over;
    public Shop shop;
    public boolean shopOpen = false;

    public boolean canOper = true;
    //Victory panel
    public Victory victoryPanel = new Victory();;
    public LevelChoose levelChoose = new LevelChoose(level);

    private Backpack Backpack1;
    private MainWindowDuringPlaying_icons mainWindow;

    private FlyingLeaves FlyingLeaves1;
    /*降雨算法第0步*/
    Raining Raining1;
    /*闪电算法第0步*/
    Lightning Lightning1;
    /*降雪算法第0步*/
    Snowing Snow1;
    /*飞沙算法第0步*/
    Sand Sand1;


    private boolean TDSuccess = false;
    private boolean TDOnce = false;
    private boolean victorySound = false;
    private HeroIcon heroIcon;
    private boolean isIconDisplayed = false;

    public WindowMouse windowMouse;

    public static boolean isMiddleStoryFinished = false;
    //控制最开始的登录界面
    public static boolean isStartPanelFinish = false;

    //Level = 10 & Victory--->进入end界面
    public static boolean totalFinish = false;
    public FinalPanel endPanel;


    public LevelDetermination(String heroType, int attackNumber, RenderWindow window)
    {
        Backpack1 = new Backpack();
        FlyingLeaves1 = new FlyingLeaves();
        /*降雨算法第一步*/
        Raining1 = new Raining();
        /*闪电算法第一步*/
        Lightning1 = new Lightning();
        /*降雪算法第一步*/
        Snow1 = new Snowing();
        /*飞沙算法第一步*/
        Sand1 = new Sand();


        this.heroType = heroType;
        this.attackNumber = attackNumber;
        this.window = window;
        window.setMouseCursorVisible(false);
        if(level != 2 && level != 6)
        {
            driver = new Driver(heroType, attackNumber, window, level);
            driver.setWindow(window, level);
        }
        over = new Gameover(window);
        shop = new Shop(window);
        if(level != 1)
        {
            if(level == 2)
            {
                towerDefense = new TD_MainFrame(window, 2);
                isIconDisplayed = true;
            }
            else if(level == 6)
            {
                towerDefense = new TD_MainFrame(window, 6);
                isIconDisplayed = true;
            }
        }
        else if(level == 1)
            towerDefense = new TD_MainFrame(window, 2);
        else if(level > 2)
            towerDefense = new TD_MainFrame(window, 6);

        mainWindow = new MainWindowDuringPlaying_icons(window);
        windowMouse = new WindowMouse(window);
        heroIcon = new HeroIcon(heroType, window);
        endPanel = new FinalPanel(window);
        mainFrame.il.bgStart=true;
    }

    public void onDraw()
    {
        //level = 10;
        if(level == 10)
        {
            if(driver.victory)
            {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp finishTime = new Timestamp(System.currentTimeMillis());//Gets the time of the current system
                driver.mark.finishTime=finishTime;
                driver.mark.unFinishedMission = driver.totalMission-driver.finishedMission;
                driver.mark.numberOfDeath=driver.numberOfDeath;
                if(!victorySound)
                {
                    TDMusicPlay.victorySound.play();
                    victorySound = true;
                }
                //set the number of star
                if(!driver.mark.isMark)
                {
                    driver.mark.markMethod();
                    driver.mark.isMark=true;
                }
                victoryPanel.victory(window, mouse,driver.mark.star);
                OperateDatabase.updateMark(level,driver.mark.star);
                OperateDatabase.calculateTotalStar();

                if(victoryPanel.shopOpen)
                {
                    this.shopOpen = true;
                    shop.isOpen = true;
                }
                if(victoryPanel.isReplay)
                    replayInVictory();
                if(victoryPanel.vicFinish)
                {
                    totalFinish = true;
                    isIconDisplayed = true;
                    endPanel.setContainers(OperateDatabase.getEndSum());
                    endPanel.setTexts(OperateDatabase.getEndInfo());
                    endPanel.onDraw();
                }
            }
        }

        //System.out.println(level);
        if(level != 2 && level != 6 && !totalFinish)
        {
            driver.onDraw();

            /*落叶纷飞算法第二步*/
            FlyingLeaves1.drawFlyingLeaves(window);
            /*闪电算法第二步*/
            if(!driver.victory)
                Lightning1.drawLlightning(window);
            /*降雨算法第二步*/
            Raining1.drawRaindrops(window);
            /*降雪算法第二步*/
            Snow1.drawFlyingLeaves(window);
            /*飞沙算法第二步*/
            Sand1.drawSand(window);


            restartGame();

            int jsfmlWindow_x = window.getPosition().x;//220;
            int jsfmlWindow_y = window.getPosition().y;//176;
            int jsfmlWindow_width = window.getSize().x;
            int jsfmlWindow_height = window.getSize().y;


            this.Backpack1.drawSmallWindow(window, mouse,-1, jsfmlWindow_x, jsfmlWindow_y);
            mainWindow.drawIconsInMainWindowDuringPlaying(window, jsfmlWindow_width, jsfmlWindow_height);
            driver.cards.onDraw();
            driver.equipments.onDraw();

            if(driver.victory)
            {
                isIconDisplayed = true;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Timestamp finishTime = new Timestamp(System.currentTimeMillis());//Gets the time of the current system
                driver.mark.finishTime=finishTime;
                driver.mark.unFinishedMission = driver.totalMission-driver.finishedMission;
                driver.mark.numberOfDeath=driver.numberOfDeath;
                if(!victorySound)
                {
                    TDMusicPlay.victorySound.play();
                    victorySound = true;
                }
                //set the number of star
                if(!driver.mark.isMark) {
                    driver.mark.markMethod();
                    driver.mark.isMark=true;
                }
                victoryPanel.victory(window, mouse,driver.mark.star);
                OperateDatabase.updateMark(level,driver.mark.star);
                OperateDatabase.calculateTotalStar();
                if(victoryPanel.shopOpen)
                {
                    this.shopOpen = true;
                    shop.isOpen = true;
                }
                if(victoryPanel.isReplay)
                    replayInVictory();

                if (isMiddleStoryFinished)
                    levelChoose.Level(window, mouse);
                else if (victoryPanel.vicFinish) {
                    System.out.println(level + 1);
                    System.out.println( mainFrame.il.bgStart+" "+mainFrame.il.cha);
                    mainFrame.il.all(window, level + 1);
                }

//                if(victoryPanel.vicFinish)
//                    levelChoose.Level(window, mouse);
                if(levelChoose.changeToAnotherLevel)
                    driver.changeToAnotherLevel = true;
            }

            if(driver.isClearFinished)
            {
                level = levelChoose.passedLevelNum;
                OperateDatabase.updateLevelRecord(level);
                if(level == 2 || level == 6)
                    changeToTD();
                else
                    changeLevel(levelChoose.passedLevelNum);

            }
        }
        else if(level == 2 || level == 6)
        {
        	Musiccall.backMusic1.stop();
        	Musiccall.backMusic3.stop();
        	Musiccall.backMusic4.stop();
        	Musiccall.backMusic5.stop();
            towerDefense.onDraw();
            if(level == 2 || level == 6)
            {
                if(towerDefense.isOver)
                    TDRestart();
            }
            if(towerDefense.isSuccess)
                TDSuccess = true;
        }

        if(TDSuccess)
        {
            if(!victorySound)
            {
                TDMusicPlay.victorySound.play();
                victorySound = true;
            }
            victoryPanel.victory(window, mouse, 4);

            if(victoryPanel.shopOpen)
            {
                this.shopOpen = true;
                shop.isOpen = true;
            }

            if (isMiddleStoryFinished)
                levelChoose.Level(window, mouse);

            else if (victoryPanel.vicFinish)
                mainFrame.il.all(window, level + 1);
//            if(victoryPanel.vicFinish)
//                levelChoose.Level(window, mouse);
            if(victoryPanel.isReplay)
                TDReplayInVictory();
            if(levelChoose.changeToAnotherLevel)
            {
                towerDefense = null;
                TDSuccess = false;
                level = levelChoose.passedLevelNum;
                OperateDatabase.updateLevelRecord(level);
                changeLevel(levelChoose.passedLevelNum);
            }
        }
            shop.onDraw();
            this.shopOpen = shop.isOpen;
            over.shopOpen = this.shopOpen;
            victoryPanel.shopOpen = this.shopOpen;
            if(!isIconDisplayed)
                heroIcon.onDraw();
            windowMouse.onDraw();
    }

    private void changeLevel(int levelNumber)
    {
        System.out.println("changetoAnotherLevel");
        driver = null;
        driver = new Driver(heroType, attackNumber, window, levelNumber);
        driver.setWindow(window, levelNumber);
        mainFrame.il.bgStart=true;
        mainFrame.il.bgFinish=false;
        isMiddleStoryFinished=false;
        levelChoose.changeToAnotherLevel = false;
        victoryPanel.vicFinish = false;
        victorySound = false;
        victoryPanel.resetVariables();
        levelChoose.isStart[level - 1] = false;
        isIconDisplayed = false;
    }

    private void changeToTD()
    {
        driver = null;
        if(level == 6 && !TDOnce)
        {
            AddMonsters.reseting();
            towerDefense = null;
            towerDefense = new TD_MainFrame(window, 6);
            TDOnce = true;
        }
        mainFrame.il.bgStart=true;
        mainFrame.il.bgFinish=false;
        isMiddleStoryFinished=false;
        levelChoose.changeToAnotherLevel = false;
        victoryPanel.vicFinish = false;
        victorySound = false;
        victoryPanel.resetVariables();
        levelChoose.isStart[level - 1] = false;
    }

    public void TDReplayInVictory()
    {
        if(victoryPanel.isReplay)
        {
            canOper = false;
            AddMonsters.reseting();
            towerDefense = null;
            towerDefense = new TD_MainFrame(window, level);
            TDSuccess = false;
            victoryPanel.isReplay = false;
            canOper = true;
            victorySound = false;
            victoryPanel.resetVariables();
        }
    }

    public void replayInVictory()
    {
        if(victoryPanel.isReplay)
        {
            canOper = false;
            Blood.setFullBlood();
            Blood.setDeathState();
            driver = null;
            driver = new Driver(heroType, attackNumber, window, level);
            driver.setWindow(window, level);
            victoryPanel.isReplay = false;
            canOper = true;
            victorySound = false;
            victoryPanel.resetVariables();
        }
    }

    //Method of drawing the game over panel, support restart function
    public void restartGame()
    {
        if(driver.player.isDead)
        {
            over.onDraw(driver.player.isDead);
            if(over.shopOpen)
            {
                this.shopOpen = true;
                shop.isOpen = true;
            }
            if(over.getRestart() && Blood.getDeathState())
            {
                canOper = false;
                over.setRestart();
                Blood.setFullBlood();
                Blood.setDeathState();
                driver.player.isDead = false;
                driver = null;
                driver = new Driver(heroType, attackNumber, window, level);
                driver.setWindow(window, level);
                canOper = true;
                victorySound = false;
            }
        }
    }

    public void TDRestart()
    {
        over.onDraw(true);

        if(over.shopOpen)
        {
            this.shopOpen = true;
            shop.isOpen = true;
        }

        if(over.getRestart())
        {
            canOper = false;
            over.setRestart();
            AddMonsters.reseting();
            towerDefense = null;
            towerDefense = new TD_MainFrame(window, level);
            over.shopOpen = false;
            canOper = true;
            victorySound = false;
        }
    }
}
