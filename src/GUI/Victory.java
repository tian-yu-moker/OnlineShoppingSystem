package GUI;

import DatabaseManager.OperateDatabase;
import TowerDenfense.TDMusicPlay;
import TowerDenfense.TDPictures;
import org.jsfml.graphics.*;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

/*
This class is the victory panel.
Can get money from the panel.
Also can go to shop, replay the current level and go to the map and play the next level.
 */

public class Victory
{
    public static Sprite createSp(String image, int a1, int a2, float x1, float x2)
    {
        String imagePath = System.getProperty("user.dir");
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(imagePath+image));//从本地加载图片
        } catch (IOException e) {
            e.printStackTrace(); }
        imgTexture.setSmooth(true); //一定要加，这个是抗锯齿的方法，也就是将图片变得平滑的方法

        Sprite Sp = new Sprite();
        Sp.setTexture(imgTexture);
        //Sp.setOrigin(0,0);  //20 150
        Sp.setPosition(a1,a2); //设置这个容器的坐标，左上角为基准 270, 10
        Sp.setScale(x1, x2);//将容器缩放，这不会改变图片的大小，使用其原点作为缩放中心。
        return Sp;
    }

    public static RectangleShape createImage(String image, int Image_x, int Image_y, int position_x, int position_y){
        //加载本地图片: 游戏开始背景图
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(image));//从本地加载图片
        } catch (IOException e) {
            e.printStackTrace(); }
        imgTexture.setSmooth(true); //一定要加，这个是抗锯齿的方法，也就是将图片变得平滑的方法

        //容器放置游戏开始背景图图片； 放缩图片与容器大小协调：设置图片的显示大小的唱和宽
        RectangleShape img = new RectangleShape(new Vector2f(Image_x, Image_y));
        img.setTexture(imgTexture);
        img.setPosition(position_x, position_y); //设置这个容器的坐标，左上角为基准
        return img;
    }

    Sprite vic_Sp = createSp("/Images/victorySubIF/vicAnimation.png",0,0,1f,1f);  //pixel: 393x405

    RectangleShape baseDim1_RS = createImage("Images/victorySubIF/basedim1.png", 1500,800,0,0);
    RectangleShape baseDim2_RS = createImage("Images/victorySubIF/basedim2.png", 1500,800,0,0);
    RectangleShape baseDim3_RS = createImage("Images/victorySubIF/basedim3.png", 1500,800,0,0);

    RectangleShape star1_RS = createImage("Images/victorySubIF/star1.png", 30,30,725,390);
    RectangleShape star2_RS = createImage("Images/victorySubIF/star2.png", 70,35,710,385);
    RectangleShape star3_RS = createImage("Images/victorySubIF/star3.png", 90,40,700,385);
    RectangleShape star4_RS = createImage("Images/victorySubIF/star4.png", 120,40,685,385);
    RectangleShape star5_RS = createImage("Images/victorySubIF/star5.png", 150,40,670,385);

    RectangleShape boxClosed1_RS = createImage("Images/victorySubIF/boxClosed.png", 80,63,600,435);
    RectangleShape boxClosedL1_RS = createImage("Images/victorySubIF/boxClosedLight.png", 100,100,580,415);
    RectangleShape boxUnused1_RS = createImage("Images/victorySubIF/boxUnused.png", 100,100,585,420);
    //RectangleShape boxOpened1_RS = createImage("Images/victorySubIF/boxOpened.png", 80,63,600,435);
    RectangleShape boxClosed2_RS = createImage("Images/victorySubIF/boxClosed.png", 80,63,710,435);
    RectangleShape boxClosedL2_RS = createImage("Images/victorySubIF/boxClosedLight.png", 100,100,690,415);
    RectangleShape boxUnused2_RS = createImage("Images/victorySubIF/boxUnused.png", 100,100,695,420);
    //RectangleShape boxOpened2_RS = createImage("Images/victorySubIF/boxOpened.png", 80,63,710,435);
    RectangleShape boxClosed3_RS = createImage("Images/victorySubIF/boxClosed.png", 80,63,820,435);
    RectangleShape boxClosedL3_RS = createImage("Images/victorySubIF/boxClosedLight.png", 100,100,800,415);
    RectangleShape boxUnused3_RS = createImage("Images/victorySubIF/boxUnused.png", 100,100,805,420);
    //RectangleShape boxOpened3_RS = createImage("Images/victorySubIF/boxOpened.png", 80,63,820,435);

    //gift
    RectangleShape money_RS = createImage("Images/victorySubIF/money.png", 90,90,700,350);
    RectangleShape diamond_RS = createImage("Images/victorySubIF/diamond.png", 90,90,700,350);
    RectangleShape tray_RS = createImage("Images/victorySubIF/tray.png", 150,75,670,400);
    RectangleShape get_RS = createImage("Images/victorySubIF/get.png", 200,75,650,480);
    RectangleShape getL_RS = createImage("Images/victorySubIF/getL.png", 210,85,645,475);

    //跳出当前界面的3个按钮
    RectangleShape repl1_RS = createImage("Images/victorySubIF/1replay.png", 130,140,550,475);
    RectangleShape repl1L_RS = createImage("Images/victorySubIF/1replayL.png", 140,150,545,470);
    RectangleShape shop3_RS = createImage("Images/victorySubIF/3shop.png", 90,85,700,510);
    RectangleShape shop3L_RS = createImage("Images/victorySubIF/3shopL.png", 100,95,695,505);
    RectangleShape goon4_RS = createImage("Images/victorySubIF/4goOn.png", 85,100,835,500);
    RectangleShape goon4L_RS = createImage("Images/victorySubIF/4goOnL.png", 95,110,830,495);


    private static int vicStartIndexX=0;//显示的vicStart图片的横向的下标
    private static int vicStartIndexY=0;//显示的vicStart图片的纵向的下标
    private static boolean vicStart=false;//判断开始动画是否播放完成, 播放完则打开界面
    private static int vicFinishIndexX=5;
    private static int vicFinishIndexY=1;
    public static boolean vicFinish = false;//判断是否播放关闭动画


    public boolean boxOpen=false;//盒子是否有打开的，为false时才能显示三个盒子及其动态效果
    public boolean box1Open=false;//具体是哪个盒子打开了
    public boolean box2Open=false;
    public boolean box3Open=false;
    boolean dim=false;//是否模糊
    int giftNum=0;

    Random rand = new Random();
    boolean ranProduce = true;//是否生成随机数
    int a = -1;//随机数初始化

    boolean getBuOpen=false;

    public boolean shopOpen = false;
    public boolean isReplay = false;

    private boolean enterSound1 = false;
    private boolean enterSound2 = false;
    private boolean enterSound3 = false;

    private boolean clickSound1 = false;
    private boolean clickSound2 = false;
    private boolean clickSound3 = false;

    private boolean boxSound1 = false;
    private boolean boxSound2 = false;
    private boolean boxSound3 = false;

    private boolean boxOpenSound1 = false;
    private boolean getButtonSound = false;
    private boolean boxDetails = false;

    private Text moneyVolume = new Text("", TDPictures.font, 30);
    private boolean isGetMoney = false;

    // private boolean boxOpenSound3 = false;

    public Victory()
    {
        moneyVolume.setColor(Color.RED);
        moneyVolume.setStyle(TextStyle.BOLD);
        moneyVolume.setPosition(730, 410);
    }


    public void startAnimation(RenderWindow a)
    {
        //start the animation
        vicStartIndexX++;
        if(vicStartIndexX == 9)
        {
            vicStartIndexX = 0;
            vicStartIndexY++;
        }
        if(vicStartIndexX==5 && vicStartIndexY == 1) {//At the end of the frame, stay.
            vicStartIndexX--;
            vicStart=true;
        }
        //设置载入图片的范围（也就是显示部分图片，控制显示的是哪一块图片），
        //四个参数分别的意义为：要显示原图片起始x坐标，要显示原图片起始y坐标，要显示部分的长和高
        vic_Sp.setTextureRect(new IntRect(1500*vicStartIndexX, 800*vicStartIndexY, 1500, 800));
        a.draw(vic_Sp);
    }

    public void finishAnimation(RenderWindow b){
        vicFinishIndexX--;
        if(vicFinishIndexX == 0){
            vicFinishIndexX = 9;
            vicFinishIndexY--;
        }
        if(vicFinishIndexX==0 && vicFinishIndexY==0){
            vicFinishIndexX++;
        }
        vic_Sp.setTextureRect(new IntRect(1500*vicFinishIndexX,800*vicFinishIndexY,1500,800));
        b.draw(vic_Sp);
    }


    public void victory(RenderWindow window, Mouse mouse, int starNum)
    {
        Vector2i position = mouse.getPosition(window);//得到鼠标光标当前位置（坐标向量），格式如 Vector2i{x=324, y=326}
        int x = position.x;//获取当前鼠标的横坐标
        int y = position.y;//获取当前鼠标的纵坐标


        boolean box1Area = x>=600 && x<=680 && y>=435 && y<=500;//80,63,600,435
        boolean box2Area = x>=710 && x<=790 && y>=435 && y<=500;//80,63,710,435
        boolean box3Area = x>=820 && x<=900 && y>=435 && y<=500;

        boolean getBuArea = x>=650 && x<=850 && y>=480 && y<=535;//200,75,650,480

        boolean replayArea = x>=550 && x<=680 && y>=500 && y<=600;//130,140,550,475
        boolean shopArea = x>=700 && x<=790 && y>=510 && y<=600;//90,85,700,510
        boolean goonArea = x>=830 && x<=925 && y>=500 && y<=600;//95,110,830,495


        startAnimation(window);

        //Music play
        if(replayArea && !boxDetails)
        {
            if(!enterSound1)
            {
                enterSound1 = true;
                TDMusicPlay.buttonMusic.play();
            }
        }
        else if(!replayArea)
            enterSound1 = false;

        if(shopArea && !boxDetails)
        {
            if(!enterSound2)
            {
                enterSound2 = true;
                TDMusicPlay.buttonMusic.play();
            }
        }
        else if(!shopArea)
            enterSound2 = false;

        if(goonArea && !boxDetails)
        {
            if(!enterSound3)
            {
                enterSound3 = true;
                TDMusicPlay.buttonMusic.play();
            }
        }
        else if(!goonArea)
            enterSound3 = false;

        //Components on the victory interface--Evaluation of stars
        if(vicStart)
        {
            //show star number
            if(starNum==1){
                window.draw(star1_RS);}
            else if(starNum==2){
                window.draw(star2_RS);}
            else if(starNum==3){
                window.draw(star3_RS);}
            else if(starNum==4){
                window.draw(star4_RS);}
            else if(starNum==5){
                window.draw(star5_RS);}



            //Show three boxes
            //when all boxes are closed and no one has been opened, they could be lighting.
            if(!boxOpen && giftNum == 0)
            {
                if(box1Area)
                {
                    if(!boxSound1)
                    {
                        boxSound1 = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(boxClosedL1_RS);
                }
                else
                {
                    boxSound1 = false;
                    window.draw(boxClosed1_RS);
                }

                if(box2Area)
                {
                    if(!boxSound2)
                    {
                        boxSound2 = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(boxClosedL2_RS);
                }
                else
                {
                    boxSound2 = false;
                    window.draw(boxClosed2_RS);
                }

                if(box3Area)
                {
                    if(!boxSound3)
                    {
                        boxSound3 = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(boxClosedL3_RS);
                }
                else
                {
                    boxSound3 = false;
                     window.draw(boxClosed3_RS);
                }
            }
            //when click one of the boxes(making it opened) and no box has been opened before, show gift.
            if(Mouse.isButtonPressed(Mouse.Button.LEFT) && !boxOpen && giftNum == 0 && (box1Area||box2Area||box3Area))
            {
                boxDetails = true;
                if(!boxOpenSound1)
                {
                    boxOpenSound1 = true;
                    TDMusicPlay.openTreasure.play();
                }
                boxOpen = true;
                getBuOpen = true;
                if(box1Area)
                {
                    box1Open=true;
                }
                else if(box2Area)
                {
                    box2Open=true;
                }
                else if(box3Area)
                {
                    box3Open=true;
                }
            }
            //When one of the boxes is opened , show the detail of the gift and the get button
            if(boxOpen && getBuOpen && (box1Open||box2Open||box3Open) && !shopOpen)
            {
                //Show the dim background
                dim = true;
                if(box1Open)
                {
                    window.draw(baseDim1_RS); }
                else if(box2Open)
                {
                    window.draw(baseDim2_RS);
                }
                else if(box3Open)
                {
                    window.draw(baseDim3_RS);
                }

                //Produce the random number "a"
                if(ranProduce)
                {
                    a = rand.nextInt(3) + 1;//获取范围为[1, 3]
                    ranProduce = false;
                    //boxMoney = (int) (Math.random() * (80 - 60 + 60) + 1);
                }
                //According to the random number "a", appear different gifts.
                if(a == 1 || a == 2)
                    window.draw(money_RS);
                else if(a == 3)
                    window.draw(diamond_RS);

                if(!isGetMoney)
                {
                    int boxMoney = 0;
                    isGetMoney = true;
                    if(a == 1 || a == 2)
                        boxMoney = (int) (Math.random() * (50 - 30 + 1) + 30);
                    else if(a == 3)
                        boxMoney = (int) (Math.random() * (80 - 50 + 1) + 50);
                    moneyVolume.setString(String.valueOf(boxMoney));
                    OperateDatabase.updateMoney(boxMoney);
                }

                //Button background
                window.draw(tray_RS);
                //Show the get button
                if(getBuArea)
                {
                    if(!getButtonSound)
                    {
                        getButtonSound = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(getL_RS);
                }
                else
                {
                    getButtonSound = false;
                    window.draw(get_RS);
                }

                window.draw(moneyVolume);
            }
            //when clink the "get" button, close the detail of the gift
            if(getBuArea && Mouse.isButtonPressed(Mouse.Button.LEFT) && boxOpen && getBuOpen && !shopOpen)
            {
                boxDetails = false;
                getBuOpen = false;
                a=-1;
                giftNum=1;
                boxOpen=false;
                dim = false;
                if(box1Open){ box1Open=false;}
                else if(box2Open){ box2Open=false;}
                else if(box3Open){ box3Open=false;}
            }
            //When one box has been opened, show three unused boxes.
            if(giftNum == 1)
            {
                window.draw(boxUnused1_RS);
                window.draw(boxUnused2_RS);
                window.draw(boxUnused3_RS);
            }

            //show three operations to leave the victory interface
            //replay button
            if(!dim)
            {
                //If replay the game
                if(replayArea)
                {
                    window.draw(repl1L_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT) && !shopOpen)
                    {
                        //Replay the game
                        if(!clickSound1)
                        {
                            clickSound1 = true;
                            TDMusicPlay.buildMusic.play();
                        }
                        isReplay = true;
                    }

                }
                else
                {
                    clickSound1 = false;
                    window.draw(repl1_RS);
                }
                //If press the shop button.
                if(shopArea)
                {
                    window.draw(shop3L_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                    {
                        //Open the shop panel
                        if(!clickSound2)
                        {
                            clickSound2 = true;
                            TDMusicPlay.buildMusic.play();
                        }
                        shopOpen = true;
                    }
                }
                else
                {
                    clickSound2 = false;
                    window.draw(shop3_RS);
                }
                //Go on button
                if(goonArea && !shopOpen)
                {
                    window.draw(goon4L_RS);
                    if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                    {
                        if(!clickSound3)
                        {
                            clickSound3 = true;
                            TDMusicPlay.buildMusic.play();
                        }
                        vicFinish = true;//进行下一关
                    }
                }
                else
                {
                    clickSound3 = false;
                    window.draw(goon4_RS);
                }
            }

            if(vicFinish)
                finishAnimation(window);
        }
    }

    //Reset some variables
    public void resetVariables()
    {
        boxOpenSound1 = false;
        boxOpen = false;
        box1Open = false;
        box2Open = false;
        box3Open = false;
        dim = false;
        ranProduce = true;
        a = -1;
        giftNum = 0;
        shopOpen = false;

        isGetMoney = false;
        moneyVolume.setString("");
    }
}
