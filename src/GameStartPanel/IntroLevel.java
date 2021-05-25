package GameStartPanel;

import TowerDenfense.TDMusicPlay;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import static GameDriver.LevelDetermination.isMiddleStoryFinished;

public class IntroLevel
{
    private static Sprite[] introLevelBg_Sp=new Sprite[5];
    public static boolean bgStart = true;//开始动画
    public static boolean bgFinish = false;//结束动画
    public static boolean cha =false;//文字播放
    private static boolean[] bg = {true, false, false, false};//动画播放内部判断
    private static boolean[][] c = new boolean[11][6];//11次文字播放内部判断，默认初始化为false
    private static int cnum=0;//线程循环次数
    private static boolean btn=true;//文字换页开关能不能转换

    private static int[] bgIndexX = new int[5];
    private static int[] bgIndexY = new int[5];

    private static RectangleShape bg_Rs;
    private static RectangleShape c1_Rs, c3_Rs, c6_Rs, c7_Rs, c8_Rs, c10_Rs;
    private static RectangleShape[] c2_Rs = new RectangleShape[2];
    private static RectangleShape[] c4_Rs = new RectangleShape[2];
    private static RectangleShape[] c5_Rs = new RectangleShape[3];
    private static RectangleShape[] c9_Rs = new RectangleShape[2];
    private static RectangleShape[] story_Rs = new RectangleShape[6];
    private static RectangleShape[] leftBtn_Rs = new RectangleShape[2];
    private static RectangleShape[] rightBtn_Rs = new RectangleShape[2];

    private boolean continueSound = false;
    private boolean backSound = false;

    private boolean clickContinue = false;
    private boolean clickBack = false;

    public IntroLevel()
    {
        //动画及背景
        for(int i=0; i<4;i++)
        {
            int b=i+1;
            if(b==1 || b==4){ introLevelBg_Sp[i]=A_Start.createSp("/Images/introLevelIF/"+b+".png", 0, 0, 1f, 1f);}
            else{ introLevelBg_Sp[i]=A_Start.createSp("/Images/introLevelIF/"+b+".jpg", 0, 0, 1f, 1f); }
        }
        bg_Rs = A_Start.createImage("Images/introLevelIF/bg.png",1500,800,0,0);

        //story文字
        for(int i=0;i<6;i++){
            int b=i+1;
            story_Rs[i] = A_Start.createImage("Images/introLevelIF/story"+b+".png",1500,800,0,0);
        }
        //关卡文字
        c1_Rs = A_Start.createImage("Images/introLevelIF/c1.png",1500,800,0,0);
        c3_Rs = A_Start.createImage("Images/introLevelIF/c3.png",1500,800,0,0);
        c6_Rs = A_Start.createImage("Images/introLevelIF/c6.png",1500,800,0,0);
        c7_Rs = A_Start.createImage("Images/introLevelIF/c7.png",1500,800,0,0);
        c8_Rs = A_Start.createImage("Images/introLevelIF/c8.png",1500,800,0,0);
        c10_Rs = A_Start.createImage("Images/introLevelIF/c10.png",1500,800,0,0);
        for(int k=0;k<2;k++)
        {
            int b=k+1;
            c2_Rs[k] = A_Start.createImage("Images/introLevelIF/c2_"+b+".png",1500,800,0,0);
            c4_Rs[k] = A_Start.createImage("Images/introLevelIF/c4_"+b+".png",1500,800,0,0);
            c9_Rs[k] = A_Start.createImage("Images/introLevelIF/c9_"+b+".png",1500,800,0,0);
            //翻页按钮
            leftBtn_Rs[k] = A_Start.createImage("Images/introLevelIF/left"+b+".png",120,90,120,400);
            rightBtn_Rs[k] = A_Start.createImage("Images/introLevelIF/right"+b+".png",120,90,1350,400);
        }
        for(int k=0;k<3;k++){
            int b=k+1;
            c5_Rs[k] = A_Start.createImage("Images/introLevelIF/c5_"+b+".png",1500,800,0,0);
        }

    }

    public void all(RenderWindow window, int level){
        Vector2i position = Mouse.getPosition(window);//得到鼠标光标当前位置（坐标向量），格式如 Vector2i{x=324, y=326}
        int x = position.x;//获取当前鼠标的横坐标
        int y = position.y;//获取当前鼠标的纵坐标
        boolean leftArea = x>=120 && x<=240 && y>=400 && y<=500;
        boolean rightArea = x>=1350 && x<=1470 && y>=400 && y<=500;

        if(bgStart){
            BgAniStart(window,level);
        }
        else if(cha){
            character(window,level,leftArea,rightArea);
        }
        else if(bgFinish){
            BgAniFinish(window);
        }
    }

    public void BgAniStart(RenderWindow window, int level)
    {
        //show the background
        if(bg[0]){
            bgIndexX[0]++;
            if(bgIndexX[0] == 4 && bgIndexY[0] != 1)
            {
                bgIndexX[0] = 0;
                bgIndexY[0]++;
            }
            if(bgIndexX[0] == 3 && bgIndexY[0] == 1)
            {
                bg[0] = false;System.out.println("start1");
                bg[1] = true;

            }
            introLevelBg_Sp[0].setTextureRect(new IntRect(1500*bgIndexX[0],800*bgIndexY[0],1500,800));
            window.draw(introLevelBg_Sp[0]);
        }

        if(bg[1])
        {
            bgIndexX[1]++;
            if(bgIndexX[1] == 4 && bgIndexY[1]!=7)
            {
                bgIndexX[1] = 0;
                bgIndexY[1]++;
            }
            if(bgIndexX[1] == 2 && bgIndexY[1]==7)
            {
                bgIndexX[1]--;
                bg[1] = false;
                bg[2] = true; System.out.println("start2");
            }
            introLevelBg_Sp[1].setTextureRect(new IntRect(1500*bgIndexX[1],800*bgIndexY[1],1500,800));
            window.draw(introLevelBg_Sp[1]);
        }

        if(bg[2])
        {
            bgIndexX[2]++;
            if(bgIndexX[2] == 4 && bgIndexY[2]!=4)
            {
                bgIndexX[2] = 0;
                bgIndexY[2]++;
            }
            if(bgIndexX[2]==4 && bgIndexY[2] == 4)
            {
                bgIndexX[2]--;
                bg[2] = false;
                bg[3] = true;System.out.println("start3");
            }
            introLevelBg_Sp[2].setTextureRect(new IntRect(1500*bgIndexX[2],800*bgIndexY[2],1500,800));
            window.draw(introLevelBg_Sp[2]);
        }

        if(bg[3])
        {
            bgIndexX[3]++;
            if(bgIndexX[3] == 4 && bgIndexY[3] < 4)
            {
                bgIndexX[3] = 0;
                bgIndexY[3]++;
            }
            if(bgIndexX[3] == 3 && bgIndexY[3] == 4)
            {
                bgIndexX[3]--;
                bg[3] = false;
                bgStart =false;
                //System.out.println("start4, bgStart:"+bgStart);
                cha = true;

                if(level==0){c[0][0]=true;}
                else if(level==1){c[1][0]=true;}
                else if(level==2){c[2][0]=true;}
                else if(level==3){c[3][0]=true;}
                else if(level==4){c[4][0]=true;}
                else if(level==5){c[5][0]=true;}
                else if(level==6){c[6][0]=true;}
                else if(level==7){c[7][0]=true;}
                else if(level==8){c[8][0]=true;}
                else if(level==9){c[9][0]=true;}
                else if(level==10){c[10][0]=true;}
            }
            //System.out.println("bgIndexX[3]:"+bgIndexX[3]+" bgIndexY[3]:"+bgIndexY[3]);
            introLevelBg_Sp[3].setTextureRect(new IntRect(1500*bgIndexX[3],800*bgIndexY[3],1500,800));
            window.draw(introLevelBg_Sp[3]);
        }
    }

    public void BgAniFinish(RenderWindow window)
    {
        if(bg[3]){
            //System.out.println("bgIndexX[3]:"+bgIndexX[3]+ " bgIndexY[3]:"+bgIndexY[3]);
            bgIndexX[3]--;
            if(bgIndexX[3]==0 && bgIndexY[3]!=0){
                bgIndexX[3] = 3;
                bgIndexY[3]--;
            }
            if(bgIndexX[3]==0 && bgIndexY[3]==0){
                bg[3] = false;
                bg[2] = true;
                //System.out.println("Finish4");
            }
            introLevelBg_Sp[3].setTextureRect(new IntRect(1500*bgIndexX[3],800*bgIndexY[3],1500,800));
            window.draw(introLevelBg_Sp[3]);
        }

        if(bg[2]){
            bgIndexX[2]--;
            if(bgIndexX[2]==0 && bgIndexY[2]!=0){
                bgIndexX[2] = 3;
                bgIndexY[2]--;
            }
            if(bgIndexX[2]==0 && bgIndexY[2]==0){
                bg[2] = false;
                bg[1] = true;
                //System.out.println("Finish3");
            }
            introLevelBg_Sp[2].setTextureRect(new IntRect(1500*bgIndexX[2],800*bgIndexY[2],1500,800));
            window.draw(introLevelBg_Sp[2]);
        }

        if(bg[1]){
            bgIndexX[1]--;
            if(bgIndexX[1]==0 && bgIndexY[1]!=0){
                bgIndexX[1] = 3;
                bgIndexY[1]--;
            }
            if(bgIndexX[1]==0 && bgIndexY[1]==0){
                bg[1] = false;
                bg[0] = true;
                //System.out.println("Finish2");
            }
            introLevelBg_Sp[1].setTextureRect(new IntRect(1500*bgIndexX[1],800*bgIndexY[1],1500,800));
            window.draw(introLevelBg_Sp[1]);
        }

        if(bg[0]){
            bgIndexX[0]--;
            if(bgIndexX[0]==0 && bgIndexY[0]!=0){//&& bgIndexY[0]!=0
                bgIndexX[0] = 3;
                bgIndexY[0]--;
            }
            if(bgIndexX[0]==0 && bgIndexY[0]==0){
                bgFinish=false;
                //System.out.println("Finish1，bgFinish："+bgFinish);
                isMiddleStoryFinished=true;


                //切换界面
                if (B_Option.story){//option界面
                    B_Option.story =false;
                    B_Option.optionAll = true;
                    if (B_Option.storyTime >= 1) {
                        bgStart=true;
                    }
                }
                else if(D_ChaChoose.storyIntro && A_Start.chaChooOpen){//角色选择界面
                    D_ChaChoose.storyIntro=false;
                    A_Start.chaChooOpen =false;
                    A_Start.levelChooOpen = true;
                }
            }

            introLevelBg_Sp[0].setTextureRect(new IntRect(1500*bgIndexX[0],800*bgIndexY[0],1500,800));
            window.draw(introLevelBg_Sp[0]);
            window.clear();
        }
    }

    public void character(RenderWindow window, int level, boolean leftArea, boolean rightArea)
    {
        cnum++;
        if(cnum%30==0){
            btn=true;
            //System.out.println("can change character");
        }
        window.draw(bg_Rs);

        if(level == 0)
        {
            if(rightArea)
            {
                if(!continueSound)
                {
                    continueSound = true;
                    TDMusicPlay.buttonMusic.play();
                }
            }
            else
                continueSound = false;

            if(leftArea && !c[0][0])
            {
                if(!backSound)
                {
                    backSound = true;
                    TDMusicPlay.buttonMusic.play();
                }
            }
            else
                backSound = false;
            if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn)
            {
                if(!clickContinue)
                {
                    clickContinue = true;
                    TDMusicPlay.buildMusic.play();
                }
            }
            else if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn && !c[0][0])
            {
                if(!clickBack)
                {
                    clickBack = true;
                    TDMusicPlay.buildMusic.play();
                }
            }
            if(!Mouse.isButtonPressed(Mouse.Button.LEFT))
            {
                clickContinue = false;
                clickBack = false;
            }
            //System.out.println("0");
            if(c[0][0]) {
                window.draw(story_Rs[0]);

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                //System.out.println("111111111111111111");
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn)
                {
                    c[0][0]=false;
                    c[0][1]=true;
                    btn=false;
                }
            }
            if(c[0][1]) {
                window.draw(story_Rs[1]);

                if(leftArea)
                {
                    window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                //System.out.println("2222222222222222");
                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][1]=false;
                    c[0][0]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][1]=false;
                    c[0][2]=true;
                    btn=false;
                }
            }
            if(c[0][2]) {
                window.draw(story_Rs[2]);

                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}
                //System.out.println("33333333333333");
                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][2]=false;
                    c[0][1]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][2]=false;
                    c[0][3]=true;btn=false;
                }
            }
            if(c[0][3]) {
                window.draw(story_Rs[3]);
                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}
                //System.out.println("444444444444444444");
                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][3]=false;
                    c[0][2]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][3]=false;
                    c[0][4]=true;
                    btn=false;
                }
            }
            if(c[0][4]) {
                window.draw(story_Rs[4]);
                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}
                //System.out.println("55555555555555");
                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}
                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][4]=false;
                    c[0][3]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][4]=false;
                    c[0][5]=true;
                    btn=false;
                }
            }
            if(c[0][5]) {
                window.draw(story_Rs[5]);
                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}
                //System.out.println("666666666666666");
                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][5]=false;
                    c[0][4]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[0][5]=false;
                    btn=false;
                    System.out.println("cha show");
                    cha=false;
                    bg[3]=true;
                    bgStart=false;
                    bgFinish=true;
                    //System.out.println("bgStart:"+bgStart+" cha:"+cha+ " bgFinish:"+bgFinish);
                }
            }
        }
        else if(level==1)
        {
            window.draw(c1_Rs);
        }
        else if(level==2)
        {
            //System.out.println("2");
            if(c[2][0])
            {
                window.draw(c2_Rs[0]);

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[2][0]=false;
                    c[2][1]=true;
                    btn=false;
                }
            }
            if(c[2][1]){
                window.draw(c2_Rs[1]);
                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[2][1]=false;
                    c[2][0]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[2][1]=false;
                    btn=false;
                    System.out.println("cha show");
                    cha=false;
                    bg[3]=true;
                    bgFinish=true;
                }
            }
        }
        else if(level==3){
            //System.out.println("3");
            window.draw(c3_Rs);
        }
        else if(level==4){
            //System.out.println("4");
            if(c[4][0]){
                window.draw(c4_Rs[0]);

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[4][0]=false;
                    c[4][1]=true;
                    btn=false;
                }
            }
            if(c[4][1]){
                window.draw(c4_Rs[1]);
                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[4][1]=false;
                    c[4][0]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[4][1]=false;
                    btn=false;
                    System.out.println("cha show");
                    cha=false;
                    bg[3]=true;
                    bgFinish=true;
                }
            }
        }
        else if(level==5){
            //System.out.println("5");
            if(c[5][0]){
                window.draw(c5_Rs[0]);
                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[5][0]=false;
                    c[5][1]=true;
                    btn=false;
                }
            }
            if(c[5][1]){
                window.draw(c5_Rs[1]);

                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[5][1]=false;
                    c[5][0]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[5][1]=false;
                    c[5][2]=true;
                    btn=false;
                }
            }
            if(c[5][2]){
                window.draw(c5_Rs[2]);
                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[5][2]=false;
                    c[5][1]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[5][2]=false;
                    btn=false;
                    System.out.println("cha show");
                    cha=false;
                    bg[3]=true;
                    bgFinish=true;
                }
            }
        }
        else if(level==6){
           // System.out.println("6");
            window.draw(c6_Rs);
        }
        else if(level==7){
            //System.out.println("7");
            window.draw(c7_Rs);
        }
        else if(level==8){
          //  System.out.println("8");
            window.draw(c8_Rs);
        }
        else if(level==9){
            //System.out.println("9");
            if(c[9][0]){
                window.draw(c9_Rs[0]);

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}

                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[9][0]=false;
                    c[9][1]=true;
                    btn=false;
                }
            }
            if(c[9][1]){
                window.draw(c9_Rs[1]);
                if(leftArea){window.draw(leftBtn_Rs[1]);}
                else{window.draw(leftBtn_Rs[0]);}

                if(rightArea){ window.draw(rightBtn_Rs[1]);}
                else{window.draw(rightBtn_Rs[0]);}
                if(leftArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[9][1]=false;
                    c[9][0]=true;
                    btn=false;
                }
                if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && btn){
                    c[9][1]=false;
                    btn=false;
                    System.out.println("cha show");
                    cha=false;
                    bg[3]=true;
                    bgFinish=true;
                }
            }
        }
        else if(level==10){
            //System.out.println("10");
            window.draw(c10_Rs);
        }

        if(level==1 || level ==3 || level ==6 || level ==7 || level ==8 || level ==10)
        {
            if(rightArea){ window.draw(rightBtn_Rs[1]);}
            else{window.draw(rightBtn_Rs[0]);}

            if(rightArea && Mouse.isButtonPressed(Mouse.Button.LEFT)){
                System.out.println("cha show");
                cha=false;
                bg[3]=true;
                bgFinish=true;
            }
        }
    }

}

