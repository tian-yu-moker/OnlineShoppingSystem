package GameStartPanel;

import DatabaseManager.OperateDatabase;
import GUI.LevelChoose;
import GameStarter.mainFrame;
import TowerDenfense.TDMusicPlay;
import org.jsfml.audio.Music;
import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

import java.io.IOException;
import java.nio.file.Paths;


public class A_Start
{
    public static float volume=1;//音量
    public static boolean gameOpen = false;//game界面的开闭状态，true为开，false为闭
    public static boolean optionOpen = false;//option界面的开闭状态，true为开，false为闭
    public static boolean loginRegisterOpen = false;//login&register背景界面的开闭状态，true为开，false为闭
    public static boolean LROpen=false;   //登录&注册子界面的开闭
    public static boolean chaChooOpen = false;//character choose界面的开闭状态，true为开，false为闭
    public static boolean levelChooOpen = false;
    public static Sound backMusic = startMusic("/Images/optionIF/music.wav");
    public static boolean isStartFinish = false;

    RectangleShape gameBG_RS;
    RectangleShape loginSMALL_RS, loginBIG_RS;
    RectangleShape optionSMALL_RS, optionBIG_RS;
    RectangleShape loginBG_RS, registerBG_RS;

    A_StartAnima sa = new A_StartAnima();
    B_Option o = new B_Option();
    C_LoginAndRegister t = new C_LoginAndRegister();
    D_ChaChoose cc = new D_ChaChoose();
    LevelChoose lc = new LevelChoose(0);

    public boolean sound1 = false;
    public boolean sound2 = false;
    public boolean clickSound1 = false;
    public boolean clickSound2 = false;

    private boolean backSoundPlay = false;

    public A_Start(){
        //以下为game界面的背景图及所有部件导入
        //添加game背景图片
        gameBG_RS = createImage("Images/gameIF/gameBG.png", 1500, 800, 0, 0);
        //game背景图片上显示的login单词
        loginSMALL_RS = createImage("Images/gameIF/loginSMALL.png", 180, 80, 420, 395);//
        loginBIG_RS = createImage("Images/gameIF/loginBIG.png", 180, 80, 420, 395);//
        //game背景图片上显示的option单词
        optionSMALL_RS = createImage("Images/gameIF/optionSMALL.png", 200, 100, 435, 475); //
        optionBIG_RS = createImage("Images/gameIF/optionBIG.png", 200, 100, 435, 475);

        //以下为login&register界面的背景图及所有部件导入
        //添加login界面&register界面的背景图片
        loginBG_RS = createImage("Images/login&registerIF/loginBG.png", 1500, 800, 0, 0);
        registerBG_RS = createImage("Images/login&registerIF/registerBG.png", 1500, 800, 0, 0);
    }


    public void run(RenderWindow window) throws IOException
    {
        if(!backSoundPlay)
        {
            backSoundPlay = true;
            backMusic.play();
        }

        //while (window.isOpen())
            if(Keyboard.isKeyPressed(Keyboard.Key.ESCAPE)){
                window.close();
                if(LROpen){
                    C_LoginAndRegister.frame.dispose();
                }
                //若进入login&register界面 注册和登录的子界面关不掉
            }

        if(Keyboard.isKeyPressed(Keyboard.Key.NUM1))
        {
            backMusic.setVolume(0);
        }
        if(Keyboard.isKeyPressed(Keyboard.Key.NUM2))
        {
            volume -= 3;
            backMusic.setVolume(volume);
            if(volume <= 0)
                backMusic.setVolume(0);
        }
        if(Keyboard.isKeyPressed(Keyboard.Key.NUM3))
        {
            volume += 3;
            backMusic.setVolume(volume);
        }
        for(Event event : window.pollEvents()) {//遍历所有在这个window上发生的事件
            if (event.type == Event.Type.CLOSED){
                window.close();
            }
        }


            //鼠标
            Vector2i position = Mouse.getPosition(window);//得到鼠标光标当前位置（坐标向量），格式如 Vector2i{x=324, y=326}
            int x = position.x;//获取当前鼠标的横坐标
            int y = position.y;//获取当前鼠标的纵坐标
            //game界面//180, 80, 410, 395;;;;200, 100, 425, 475
            boolean gameLoginArea = x>=410 && x<=610 && y>=400 && y<=460;//设置login单词的所在区域
            boolean gameOptionArea = x>=430 && x<=630 && y>=500 && y<=560;//设置option单词的所在区域


            sa.run(window);



            //game界面(显示)
            if (gameOpen)
            {
                window.draw(gameBG_RS);//显示开始背景图

                if (gameLoginArea)
                {
                    if(!sound1)
                    {
                        sound1 = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(loginBIG_RS);
                }
                else {
                    sound1 = false;
                    window.draw(loginSMALL_RS);
                }

                if (gameOptionArea)
                {
                    if(!sound2)
                    {
                        sound2 = true;
                        TDMusicPlay.buttonMusic.play();
                    }
                    window.draw(optionBIG_RS);
                }
                else {
                    sound2 = false;
                    window.draw(optionSMALL_RS); }

                //界面切换
                if(Mouse.isButtonPressed(Mouse.Button.LEFT))
                {
                    //game→option
                    if (gameOptionArea)
                    {
                        TDMusicPlay.selectMusic.play();
                        gameOpen = false;
                        optionOpen = true;
                    }
                    //game→login&Register
                    else if (gameLoginArea)
                    {
                        TDMusicPlay.selectMusic.play();
                        gameOpen = false;
                        loginRegisterOpen = true;
                        LROpen=true;
                    }
                }
            }
            //option界面(显示)：仅可从game界面→option界面
            else if (optionOpen) {
                o.run(window);
            }
            //login&register界面(显示)
            else if (loginRegisterOpen) {
                //变换login&register的背景
                if(t.loginOpen){ window.draw(loginBG_RS); }
                else if(t.registerOpen){ window.draw(registerBG_RS); }

                if(LROpen){
                    t.Create();
                    LROpen=false;
                }

                //if (GobackArea) { window.draw(GobackLight_RS); }
                //else { window.draw(Goback_RS);}
            }
            //chaChoose界面(显示)
            else if (chaChooOpen) {
                cc.run(window);
            }
            //levelChoose界面(显示)
            else if(levelChooOpen)
            {
                Mouse mouse = null;
                int setLevel = OperateDatabase.getLevelRecord();
                lc.setPassedLevelNum(setLevel - 1);
                lc.Level(window, mouse);
                if(mainFrame.isStartFinish)
                {
                    backMusic.stop();
                    lc = null;
                }
            }

    }



    public static RectangleShape createImag(String image, int Image_x, int Image_y) {
        //加载本地图片: 游戏开始背景图
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(image));//从本地加载图片
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgTexture.setSmooth(true); //一定要加，这个是抗锯齿的方法，也就是将图片变得平滑的方法

        //容器放置游戏开始背景图图片； 放缩图片与容器大小协调：设置图片的显示大小的唱和宽
        RectangleShape img = new RectangleShape(new Vector2f(Image_x, Image_y));
        img.setTexture(imgTexture);
        //img.setPosition(position_x, position_y); //设置这个容器的坐标，左上角为基准
        return img;
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

    public static Text creatText(String content, int fontType, int fontSize, Color c, int text_x, int text_y)
    {
        Font font=new Font();
        try {
            if(fontType==1){font.loadFromFile(Paths.get("font/1000hurt.ttf"));}
            else if(fontType==2){font.loadFromFile(Paths.get("font/Arial.ttf"));}
            //String fontType,
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Text text = new Text(content, font,fontSize);//内容；；字体大小
        text.setColor(c);//设置当前文本框的填充颜色为透明TRANSPARENT
        text.setPosition(text_x, text_y);//设置文本框的位置
        return text;
    }

    public static Sprite createSp(String image, int position_x, int position_y, float x1, float x2){
        String imagePath = System.getProperty("user.dir");
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(imagePath+image));//从本地加载图片
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgTexture.setSmooth(true); //一定要加，这个是抗锯齿的方法，也就是将图片变得平滑的方法

        Sprite Sp = new Sprite();
        Sp.setTexture(imgTexture);
        //Sp.setOrigin(0,0);  //20 150
        Sp.setPosition(position_x,position_y); //设置这个容器的坐标，左上角为基准 270, 10
        Sp.setScale(x1, x2);//将容器缩放，这不会改变图片的大小，使用其原点作为缩放中心。
        return Sp;
    }

    public static Music createMusic(String musicPath) throws IOException {
        //String imagePath = System.getProperty("user.dir");
        Music music = new Music();
        try {
            music.openFromFile(Paths.get(musicPath));//imagePath +
        } catch (IOException e) {
            System.err.println("Failed to load the sound:");
            e.printStackTrace();
        }
        return music;
    }

    public static Sound startMusic(String path) {
        String imagePath = System.getProperty("user.dir");
        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromFile(Paths.get(imagePath + path));
        } catch(IOException ex) {
            //Something went wrong
            System.err.println("Failed to load the sound:");
            ex.printStackTrace();
        }
        //Create a sound and set its buffer
        Sound sound = new Sound();
        sound.setBuffer(soundBuffer);
        sound.setVolume(volume);
        sound.setLoop(true);

        return sound;
    }
}