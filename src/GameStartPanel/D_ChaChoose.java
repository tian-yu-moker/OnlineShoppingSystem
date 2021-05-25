package GameStartPanel;

import Actors.Blood;
import DatabaseManager.OperateDatabase;
import GameStarter.mainFrame;
import TowerDenfense.TDMusicPlay;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

public class D_ChaChoose {
    public static boolean a=true;
    public static boolean consAbbrOpen = true;
    public static boolean consDetailOpen = false;
    public static boolean palaAbbrOpen = false;
    public static boolean palaDetailOpen = false;
    public static boolean swordAbbrOpen = false;
    public static boolean swordDetailOpen = false;
    public static String HeroType ="";//当前英雄的值
    public static boolean storyIntro =false;//故事介绍

    private static int num1 =0;//线程数量
    private static boolean btn1 =true;//音乐开关能不能转换
    private static boolean pauseBtn1=true;//音乐暂停播放按钮
    private static boolean rePlBtn1 =false;//音乐再次播放按钮
    private static int num2 =0;//线程数量
    private static boolean btn2 =true;//音乐开关能不能转换
    private static boolean pauseBtn2=true;//音乐暂停播放按钮
    private static boolean rePlBtn2 =false;//音乐再次播放按钮
    private static int num3 =0;//线程数量
    private static boolean btn3 =true;//音乐开关能不能转换
    private static boolean pauseBtn3=true;//音乐暂停播放按钮
    private static boolean rePlBtn3 =false;//音乐再次播放按钮

    //角色动画的参数
    private static int consIndexX = 0;//横向序号0-7;
    private static int consIndexY = 0;//纵向序号0-5
    private static int palaIndexX = 0;//横向序号0-7,第6行0-0;
    private static int palaIndexY = 0;//纵向序号0-5
    private static int swordIndexX = 0;//横向序号0-5,第5行0-3;
    private static int swordIndexY = 0;//纵向序号0-4

    //角色解说音频
    private static Sound consSound = A_Start.startMusic("/Images/chaChooIF/constantine/Cons.wav");
    private static Sound palaSound = A_Start.startMusic("/Images/chaChooIF/palandin/Pala.wav");
    private static Sound swordSound = A_Start.startMusic("/Images/chaChooIF/swordman/Sword.wav");

    private boolean enterMusic1 = false;
    private boolean enterMusic2 = false;
    private boolean enterMusic3 = false;

    //以下为chaChoo界面的背景图及所有部件导入
    //添加chaChoo背景图片
    RectangleShape chaChooBG_RS = A_Start.createImage("Images/chaChooIF/chaChooBG.png", 1500, 800, 0, 0);
    RectangleShape BG_RS = A_Start.createImage("Images/chaChooIF/BG.jpg", 583, 410, 541, 212);
    //chaChoo背景图片上显示的choose单词
    RectangleShape choose_RS = A_Start.createImage("Images/chaChooIF/choose.png", 200, 100, 930, 300);
    RectangleShape chooseLight_RS = A_Start.createImage("Images/chaChooIF/chooseLight.png", 200, 100, 930, 300);
    //chaChoo背景图片上显示的播放和暂停按钮
    RectangleShape plRecod_RS = A_Start.createImage("Images/chaChooIF/plRecord.png", 100, 100, 1020, 550);
    RectangleShape plRecodL_RS = A_Start.createImage("Images/chaChooIF/plRecordL.png", 100, 100, 1020, 550);
    RectangleShape pause_RS = A_Start.createImage("Images/chaChooIF/pause.png", 100, 100, 1020, 550);
    RectangleShape pauseLight_RS = A_Start.createImage("Images/chaChooIF/pauseL.png", 100, 100, 1020, 550);
    //chaChoo背景图片上显示的左侧头像栏内的头像照片
    RectangleShape consHead_RS = A_Start.createImage("Images/chaChooIF/constantine/consHead.png", 100, 84, 390, 225);
    RectangleShape palaHead_RS = A_Start.createImage("Images/chaChooIF/palandin/palaHead.png", 100, 84, 390, 375);
    RectangleShape swordHead_RS = A_Start.createImage("Images/chaChooIF/swordman/swordHead.png", 100, 84, 390, 525);
    RectangleShape consHeadLight_RS = A_Start.createImage("Images/chaChooIF/constantine/consHeadLight.png", 100, 84, 390, 225);
    RectangleShape palaHeadLight_RS = A_Start.createImage("Images/chaChooIF/palandin/palaHeadLight.png", 100, 84, 390, 375);
    RectangleShape swordHeadLight_RS = A_Start.createImage("Images/chaChooIF/swordman/swordHeadLight.png", 100, 84, 390, 525);
    //chachoo背景图片上显示的右侧角色基本信息
    RectangleShape consAbbr_RS = A_Start.createImage("Images/chaChooIF/constantine/consAbbr.png", 583, 413, 541, 210);
    RectangleShape palaAbbr_RS = A_Start.createImage("Images/chaChooIF/palandin/palaAbbr.png", 583, 413, 541, 210);
    RectangleShape swordAbbr_RS = A_Start.createImage("Images/chaChooIF/swordman/swordAbbr.png", 583, 413, 541, 210);
    //chaChoo背景图片上显示的左侧角色数据
    RectangleShape consData_RS = A_Start.createImage("Images/chaChooIF/constantine/consData.png", 148, 445, 370, 195);
    RectangleShape palaData_RS = A_Start.createImage("Images/chaChooIF/palandin/palaData.png", 148, 445, 370, 195);
    RectangleShape swordData_RS = A_Start.createImage("Images/chaChooIF/swordman/swordData.png", 148, 445, 370, 195);
    //chaChoo背景图片上显示的右侧角色形象
    public static Sprite consSp = A_Start.createSp("/Images/chaChooIF/2Cons.png",220,-160,1.5f,1.5f);
    public static Sprite palaSp = A_Start.createSp("/Images/chaChooIF/3Pala.png",220,-160,1.5f,1.5f);
    public static Sprite swordSp = A_Start.createSp("/Images/chaChooIF/4Sword.png",290, 10,1.00f,1.00f);
    //chaChoo背景图片上显示的continue单词
    RectangleShape continue_RS = A_Start.createImage("Images/chaChooIF/continue.png", 285, 80, 545, 550);
    RectangleShape continueLight_RS = A_Start.createImage("Images/chaChooIF/continueLight.png", 285, 80, 545, 550);
    //chaChoo背景图片上显示的右侧角色技能
    RectangleShape consSkills_RS = A_Start.createImage("Images/chaChooIF/constantine/consSkills.png", 277, 390, 835, 220);
    RectangleShape palaSkills_RS = A_Start.createImage("Images/chaChooIF/palandin/palaSkills.png", 277, 390, 835, 220);
    RectangleShape swordSkills_RS = A_Start.createImage("Images/chaChooIF/swordman/swordSkills.png", 277, 390, 835, 220);
    //详细界面上的返回按钮
    RectangleShape Goback_RS = A_Start.createImage("Images/goback.png", 130, 100, 8, 20);
    RectangleShape GobackLight_RS = A_Start.createImage("Images/gobackLight.png", 130, 100, 8, 20);




    public void run(RenderWindow window){
        consSound.setVolume(A_Start.volume + 40);
        palaSound.setVolume(A_Start.volume + 40);
        swordSound.setVolume(A_Start.volume + 40);

        Vector2i position = Mouse.getPosition(window);//得到鼠标光标当前位置（坐标向量），格式如 Vector2i{x=324, y=326}
        int x = position.x;//获取当前鼠标的横坐标
        int y = position.y;//获取当前鼠标的纵坐标
        boolean consHeadArea = x>=380 && x<=480 && y>=225 && y<=310;
        boolean palaHeadArea = x>=380 && x<=480 && y>=375 && y<=460;
        boolean swordHeadArea = x>=380 && x<=480 && y>=525 && y<=610;
        boolean chooseArea = x>=930 && x<=1130 && y>=300 && y<=400;//200, 100, 890, 300
        boolean musicArea = x>=1020 && x<=1120 && y>=550 && y<=650;//100, 100, 990, 550
        boolean continueArea = x>=545 && x<=830 && y>=550 && y<=630;//285, 80, 525, 550
        boolean GobackArea = x>=8 && x<=138 && y>=20 && y<=120;//设置goback图片的所在区域130, 100, 8, 20


        if(a){
            window.draw(chaChooBG_RS);
        }

        //基础界面部分
        //显示人物选择基础信息界面及音乐
        if (consAbbrOpen)
        {
            window.draw(consAbbr_RS);
            num1++;
            if(num1 %30==0){
                btn1 =true;
                System.out.println("can change open/close state");
            }
            if(pauseBtn1)
            {
                if (musicArea)
                {
                    if (!enterMusic1)
                    {
                        enterMusic1 = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(pauseLight_RS);
                    if (Mouse.isButtonPressed(Mouse.Button.LEFT) && btn1) {
                        btn1 = false;
                        consSound.pause();
                        pauseBtn1 = false;
                        rePlBtn1 = true;
                    }
                }
                else
                {
                    enterMusic1 = false;
                    window.draw(pause_RS);
                }
            }
            else if(rePlBtn1)
            {
                if(musicArea)
                {
                    if (!enterMusic1)
                    {
                        enterMusic1 = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(plRecodL_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT) && btn1)
                    {
                        btn1 =false;
                        consSound.play();
                        rePlBtn1=false;
                        pauseBtn1=true;
                    }
                }
                else
                {
                    enterMusic1 = true;
                    window.draw(plRecod_RS);
                }
            }
        }
        else if ( palaAbbrOpen )
        {
            window.draw(palaAbbr_RS);
            num2++;
            if(num2 %30==0){
                btn2 =true;
                System.out.println("can change open/close state");
            }
            if(pauseBtn2) {
                if(musicArea)
                {
                    window.draw(pauseLight_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT) && btn2)
                    {
                        btn2=false;
                        palaSound.pause();
                        pauseBtn2=false;
                        rePlBtn2=true;
                    }
                }
                else {
                    window.draw(pause_RS);
                }
            }
            //继续播放角色解说的rePl按钮
            else if(rePlBtn2) {
                if(musicArea)
                {
                    window.draw(plRecodL_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT) && btn2)
                    {
                        btn2=false;
                        palaSound.play();
                        rePlBtn2=false;
                        pauseBtn2=true;
                    }
                }
                else {
                    window.draw(plRecod_RS);
                }
            }
        }
        else if ( swordAbbrOpen )
        {
            window.draw(swordAbbr_RS);
            num3++;
            if(num3 %30==0){
                btn3 =true;
                System.out.println("can change open/close state");
            }
            //暂停播放角色解说的pause按钮
            if(pauseBtn3)
            {
                if(musicArea)
                {
                    window.draw(pauseLight_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT) && btn3)
                    {
                        btn3=false;
                        swordSound.pause();
                        pauseBtn3=false;
                        rePlBtn3=true;
                    }
                }
                else{
                    window.draw(pause_RS);
                }
            }
            //继续播放角色解说的rePl按钮
            else if(rePlBtn3)
            {
                if(musicArea)
                {
                    window.draw(plRecodL_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT) && btn3)
                    {
                        btn3=false;
                        swordSound.play();
                        rePlBtn3=false;
                        pauseBtn3=true;
                    }
                }
                else {
                    window.draw(plRecod_RS);
                }
            }
        }
        //人物头像按钮   choose单词按钮
        if(consAbbrOpen ||palaAbbrOpen ||swordAbbrOpen)
        {
            //人物头像按钮：基础信息界面的各角色间进行转换
            if (consHeadArea) {
                window.draw(consHeadLight_RS);
                if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    pauseBtn1=true;//音乐暂停播放按钮
                    rePlBtn1 =false;
                    consAbbrOpen = true;
                    palaAbbrOpen = false;
                    swordAbbrOpen = false;
                    consSound.play();
                    palaSound.stop();
                    swordSound.stop();
                }
            }
            else { window.draw(consHead_RS); }

            if (palaHeadArea) {
                window.draw(palaHeadLight_RS);
                if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    pauseBtn2=true;//音乐暂停播放按钮
                    rePlBtn2 =false;
                    consAbbrOpen = false;
                    palaAbbrOpen = true;
                    swordAbbrOpen = false;
                    consSound.stop();
                    palaSound.play();
                    swordSound.stop();
                }
            }
            else { window.draw(palaHead_RS); }

            if (swordHeadArea) {
                window.draw(swordHeadLight_RS);
                if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    pauseBtn3=true;//音乐暂停播放按钮
                    rePlBtn3 =false;
                    consAbbrOpen = false;
                    palaAbbrOpen = false;
                    swordAbbrOpen = true;
                    consSound.stop();
                    palaSound.stop();
                    swordSound.play();
                }
            }
            else { window.draw(swordHead_RS); }

            //choose单词按钮: 角色基础信息界面→角色详细信息界面
            if (chooseArea) {
                window.draw(chooseLight_RS);
                if(Mouse.isButtonPressed(Mouse.Button.LEFT)) {
                    if(consAbbrOpen) {
                        consAbbrOpen =false;
                        consDetailOpen = true;
                    }
                    else if(palaAbbrOpen) {
                        palaAbbrOpen =false;
                        palaDetailOpen = true;
                    }
                    else if(swordAbbrOpen) {
                        swordAbbrOpen =false;
                        swordDetailOpen = true;
                    }
                }
            }
            else { window.draw(choose_RS); }
        }




        //详细界面部分
        //显示人物选择详细信息界面
        if(consDetailOpen)
        {
            //an1(consSp);
            window.draw(chaChooBG_RS);
            window.draw(BG_RS);
            window.draw(consData_RS);
            window.draw(consSkills_RS);
            //window.draw(consSp);
            an1(window);
        }
        else if(palaDetailOpen)
        {
            //an2(palaSp);
            window.draw(chaChooBG_RS);
            window.draw(BG_RS);
            window.draw(palaData_RS);
            window.draw(palaSkills_RS);
            //window.draw(palaSp);
            an2(window);
        }
        else if(swordDetailOpen)
        {
            //an3(swordSp);
            window.draw(chaChooBG_RS);
            window.draw(BG_RS);
            window.draw(swordData_RS);
            window.draw(swordSkills_RS);
            //window.draw(swordSp);
            an3(window);
        }
        //goback按钮 和 choose按钮
        if(consDetailOpen || palaDetailOpen || swordDetailOpen)
        {
            //goback按钮: 详细界面->基础界面
            if(GobackArea)
            {
                window.draw(GobackLight_RS);
                if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    if(consDetailOpen)
                    {
                        consAbbrOpen = true;
                        consDetailOpen = false;
                    }
                    else if(palaDetailOpen)
                    {
                        palaAbbrOpen = true;
                        palaDetailOpen = false;
                    }
                    else if(swordDetailOpen)
                    {
                        swordAbbrOpen = true;
                        swordDetailOpen = false;
                    }
                }
            }
            else { window.draw(Goback_RS); }

            //choose按钮：chaChoo界面->levelChoose界面的
            if(continueArea) {
                window.draw(continueLight_RS);
                if (Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    consSound.stop();
                    palaSound.stop();
                    swordSound.stop();
                    if(consDetailOpen){
                        HeroType ="Saber";
                        System.out.println(HeroType);
                        OperateDatabase.setUserInfor2(HeroType, C_LoginAndRegister.UserId);
                        Blood.getHeroInfo();
                    }
                    else if(palaDetailOpen){
                        HeroType ="Lancer";
                        System.out.println(HeroType);
                        OperateDatabase.setUserInfor2(HeroType, C_LoginAndRegister.UserId);
                        Blood.getHeroInfo();
                    }
                    else if(swordDetailOpen){
                        HeroType ="Knight";
                        System.out.println(HeroType);
                        OperateDatabase.setUserInfor2(HeroType, C_LoginAndRegister.UserId);
                        Blood.getHeroInfo();
                    }

                    storyIntro=true;
                    //consDetailOpen=false;
                    //palaDetailOpen=false;
                    //swordDetailOpen=false;
                    //A_Start.chaChooOpen = false;
                    //A_Start.levelChooOpen = true;

                }
            }
            else{
               // System.out.println("1111");
                window.draw(continue_RS);
            }



        }


        if(storyIntro){
            a = false;
            consDetailOpen=false;
            palaDetailOpen=false;
            swordDetailOpen=false;
            mainFrame.il.all(window,0);//显示游戏介绍动画
        }
    }

    public static void an1(RenderWindow window){//Sprite consSp
        consIndexX++;
        if(consIndexX == 7 && consIndexY!=5)
        {
            consIndexX = 0;
            consIndexY++;
        }
        if(consIndexX == 7 && consIndexY == 5)
        {
            consIndexY = 0;
            consIndexX = 0;
        }
        consSp.setTextureRect(new IntRect(600*consIndexX, 600*consIndexY, 600, 600));
        window.draw(consSp);
    }

    public static void an2(RenderWindow window){
        palaIndexX++;
        if(palaIndexX == 7 && palaIndexY!=5)
        {
            palaIndexX = 0;
            palaIndexY++;
        }
        if(palaIndexY == 5 && palaIndexX==0)
        {
            palaIndexY = 0;
            palaIndexX = 0;
        }
        palaSp.setTextureRect(new IntRect(600*palaIndexX, 600*palaIndexY, 600, 600));
        window.draw(palaSp);
    }

    public static void an3(RenderWindow window){
        swordIndexX++;
        if(swordIndexX == 5 && swordIndexY!=4)
        {
            swordIndexX = 0;
            swordIndexY++;
        }
        if(swordIndexY == 4 && swordIndexX==3)
        {
            swordIndexY = 0;
            swordIndexX = 0;
        }
        swordSp.setTextureRect(new IntRect(750*swordIndexX, 750*swordIndexY, 750, 750));
        window.draw(swordSp);
    }

}




