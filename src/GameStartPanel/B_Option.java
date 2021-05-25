package GameStartPanel;

import GameStarter.mainFrame;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class B_Option {
    public static boolean optionAll=true;
    private boolean play=false;
    public static boolean story=false;
    public static int storyTime =0;//story按钮的点击次数

    private static boolean bgMusic = true;
    private static int time=0;//option界面音乐开关的点击次数
    private static boolean a=true;//背景音乐只播放一遍
    private static int cnum=0;//线程循环次数
    private static boolean btn=true;//option界面音乐开关能不能转换

    RectangleShape optionBG_RS, dim_Rs, play_RS, story_Rs;
    RectangleShape closeSMALL_Rs, closeBIG_Rs;
    RectangleShape[] musicBtn_Rs = new RectangleShape[4];
    RectangleShape[] backBtn_Rs = new RectangleShape[2];
    public static RectangleShape[] playBtn_Rs = new RectangleShape[2];
    RectangleShape[] storyBtn_Rs = new RectangleShape[2];
    Sprite howtoplay_Sp, showStory_Sp;//play和story的弹窗动画
    Text muText;

    //角色动画的参数
    private static int storyIndexX = 0;//横向序号0-7,第6行0-0;
    private static int storyIndexY = 0;//纵向序号0-5
    private static boolean storyFinish = false;
    private static int playIndexX = 0;//横向序号0-7;
    private static int playIndexY = 0;//纵向序号0-5
    private static boolean playFinish = false;

    private boolean soundStory = false;
    private boolean soundHowToPlay = false;
    private boolean soundBack = false;
    private boolean soundCloseButton = false;

    private boolean clickSoundBack = false;
    private boolean clickSoundHow = false;
    private boolean clickSoundStory = false;
    private boolean clickSoundButton = false;



    public B_Option()
    {
        optionBG_RS = A_Start.createImage("Images/optionIF/optionBG.png", 1500, 800, 0, 0);
        dim_Rs = A_Start.createImage("Images/optionIF/dim.png", 1500, 800, 0, 0);

        //play的动画和内容
        howtoplay_Sp = A_Start.createSp("/Images/optionIF/howtoplay.png", 0, 0, 1f,1f);
        play_RS = A_Start.createImage("Images/optionIF/play.png", 420, 280, 542, 260);

        closeSMALL_Rs = A_Start.createImage("Images/optionIF/close.png",140,70,930,115);
        closeBIG_Rs = A_Start.createImage("Images/optionIF/close.png",150,80,925,110);

        //按钮
        for(int i=0;i<4;i++){
            int a=i+1;
            musicBtn_Rs[i]= A_Start.createImage("Images/optionIF/bg"+a+".png", 430,120,535,160);
        }
        for(int q=0; q<2;q++){
            int p = q + 1;
            storyBtn_Rs[q] = A_Start.createImage("Images/optionIF/story"+p+".png", 430,120,535,285);
            playBtn_Rs[q] = A_Start.createImage("Images/optionIF/play"+p+".png", 430,120,535,410);
            backBtn_Rs[q] = A_Start.createImage("Images/optionIF/back"+p+".png", 400, 150, 1035, 645);
        }
        muText = A_Start.creatText("             PS: Number 1, 2, 3 also control music. \n                      1: Close 2: Lower 3: Larger",2,13, Color.WHITE,572,257);
    }

    public void storyAnima(RenderWindow a){
        storyIndexX++;
        if(storyIndexX == 3 && storyIndexY!=5) {
            storyIndexX = 0;
            storyIndexY++;
        }
        if(storyIndexX == 3 && storyIndexY == 5) {
            storyIndexX--;
            storyFinish=true;
        }
        showStory_Sp.setTextureRect(new IntRect(1500*storyIndexX, 800*storyIndexY, 1500, 800));
        a.draw(howtoplay_Sp);
    }

    public void playAnima(RenderWindow a)
    {
        playIndexX++;
        if(playIndexX == 3 && playIndexY != 5)
        {
            playIndexX = 0;
            playIndexY++;
        }
        if(playIndexX == 3 && playIndexY == 5)
        {
            playIndexX--;
            playFinish = true;
        }
        howtoplay_Sp.setTextureRect(new IntRect(1500*playIndexX, 800*playIndexY, 1500, 800));
        a.draw(howtoplay_Sp);
    }

    public void run(RenderWindow window)
    {
        Vector2i position = Mouse.getPosition(window);
        int x = position.x;//获取当前鼠标的横坐标
        int y = position.y;//获取当前鼠标的纵坐标

        boolean optionBackArea = x>=1000 && x<=1400 && y>=650 && y<=800;//设置back to menu单词的所在区域
        boolean musicBtnArea = x>=535 && x<=935 && y>=180 && y<=250;
        boolean storyBtnArea = x>=535 && x<=935 && y>=300 && y<=370;
        boolean playBtnArea = x>=535 && x<=935 && y>=430 && y<=500;
        boolean closeArea = x>=950 && x<=1090 && y>=115 && y<=185;//140,70,930,115


        if(optionAll){
            window.draw(optionBG_RS);//进行切换界面操作，显示option界面
            window.draw(muText);
            if (optionBackArea) { window.draw(backBtn_Rs[1]); }
            else { window.draw(backBtn_Rs[0]); }


            //背景音乐播放设置判断
            cnum++;
            if(cnum%30==0){
                btn=true;
                System.out.println("can change open/close state");
            }
            if(musicBtnArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                time++;
                System.out.println(time);
                btn=false;
                a=true;
                if(bgMusic){ bgMusic=false; }
                else{ bgMusic=true; }
            }
            if(bgMusic){
                //初始状态已默认播放，显示close
                if(musicBtnArea){window.draw(musicBtn_Rs[1]);}
                else{window.draw(musicBtn_Rs[0]);}

                if(time>0){ //非初始状态，打开音乐并显示关闭字样
                    if(a){//确保只调用一遍
                        A_Start.backMusic.play();//Test.
                        a=false;
                    }
                }
            }
            else{//关闭音乐并显示open字样
                if(musicBtnArea){window.draw(musicBtn_Rs[3]); }
                else {window.draw(musicBtn_Rs[2]);}

                A_Start.backMusic.stop();//Test
            }


            //story按钮
            if(storyBtnArea)
            {
                if(!soundStory)
                {
                    soundStory = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(storyBtn_Rs[1]);
                if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    if(!clickSoundStory)
                    {
                        clickSoundStory = true;
                        TDMusicPlay.clickSound.play();
                    }
                    storyTime++;
                    optionAll=false;
                    story=true;
                    System.out.println("storypress:"+story);
                }
            }
            else
            {
                soundStory = false;
                window.draw(storyBtn_Rs[0]);
            }


            //play按钮
            if(playBtnArea)
            {
                if(!soundHowToPlay)
                {
                    soundHowToPlay = true;
                    TDMusicPlay.buttonMusic.play();
                }
                window.draw(playBtn_Rs[1]);
                if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    if(!clickSoundHow)
                    {
                        clickSoundHow = true;
                        TDMusicPlay.clickSound.play();
                    }
                    play=true;
                    optionAll=false;
                }
            }
            else
            {
                soundHowToPlay = false;
                window.draw(playBtn_Rs[0]);
            }
        }


        if(story){
            //System.out.println("run");
            //IntroLevel.bgStart=true;
            mainFrame.il.all(window,0);
        }


        if(play)
        {//弹窗动画
            window.draw(dim_Rs);
            playAnima(window);

            //弹窗动画播放完成之后，游戏指导图片
            if(playFinish){
                playFinish=false;
                window.draw(play_RS);

                if(closeArea)
                {
                    if(!soundCloseButton)
                    {
                        soundCloseButton = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(closeBIG_Rs);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                    {
                        if(!clickSoundButton)
                        {
                            clickSoundButton = true;
                            TDMusicPlay.clickSound.play();
                        }
                        play = false;
                        playFinish = false;
                        playIndexX=0;
                        playIndexY=0;
                        optionAll = true;
                    }
                }
                else
                {
                    soundCloseButton = false;
                    window.draw(closeSMALL_Rs);
                }
            }
        }


        if(optionBackArea)
        {
            if(!soundBack)
            {
                soundBack = true;
                TDMusicPlay.buttonMusic.play();
            }
        }
        else if(!optionBackArea)
            soundBack = false;

        //option→game：当鼠标到达back to menu单词所在区域并按左键时， game界面应该打开，而option界面应该关闭
        if(A_Start.optionOpen && optionBackArea && Mouse.isButtonPressed(Mouse.Button.LEFT))
        {
            A_Start.gameOpen = true;
            A_Start.optionOpen = false;
            if(!clickSoundBack)
            {
                TDMusicPlay.clickSound.play();
                clickSoundBack = true;
            }
        }
        else if(!Mouse.isButtonPressed(Mouse.Button.LEFT))
        {
            clickSoundHow = false;
            clickSoundStory = false;
            clickSoundBack = false;
            clickSoundButton = false;
        }
    }


}
