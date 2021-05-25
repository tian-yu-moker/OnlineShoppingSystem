package GameDriver;

import GUI.LevelChoose;
import org.jsfml.audio.Music;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.*;
import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Start {
    public static boolean gameOpen = false;//truegame界面的开闭状态，true为开，false为闭
    public static boolean optionOpen = false;//option界面的开闭状态，true为开，false为闭
    public static boolean loginRegisterOpen = false;//login&register背景界面的开闭状态，true为开，false为闭
    public static boolean LROpen = true;   //登录&注册子界面的开闭
    public static boolean chaChooOpen = false;//character choose界面的开闭状态，true为开，false为闭
    //根据当前显示的角色基本信息来判断播放的音频片段
    public static boolean consAbbrOpen = false;
    public static boolean palaAbbrOpen = false;
    public static boolean swordAbbrOpen = false;
    public static boolean consDetailOpen = false;
    public static boolean palaDetailOpen = false;
    public static boolean swordDetailOpen = false;
    public static boolean archMusicOpen = true;
    public static boolean consMusicOpen = false;
    public static boolean palaMusicOpen = false;
    public static boolean swordMusicOpen = false;
    public static boolean levelChooOpen = true;

    public static int start1IndexX=0; public static int start1IndexY=0;
    public static int start2IndexX=0; public static int start2IndexY=0;
    public static int start3IndexX=0; public static int start3IndexY=0;
    public static int start4IndexX=0; public static int start4IndexY=0;
    public static int start5IndexX=0; public static int start5IndexY=0;
    public static int start6IndexX=0; public static int start6IndexY=0;
    public static int start7IndexX=0; public static int start7IndexY=0;
    public static int start8IndexX=0; public static int start8IndexY=0;


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

    public static Text creatText( String content, int fontSize, Color c, int text_x, int text_y){
        Font font=new Font();
        try {
            font.loadFromFile(Paths.get("font/1000hurt.ttf"));//String fontType,
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Text text = new Text (content, font,fontSize);//内容；；字体大小
        text.setColor(c);//设置当前文本框的填充颜色为透明TRANSPARENT
        text.setPosition(text_x, text_y);//设置文本框的位置
        return text;
    }

    public static Sprite createSp(String image, int a1, int a2, float x1, float x2){
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

    public static Music music(String musicPath) throws IOException {
        Music music = new Music();
        music.openFromFile(Paths.get(musicPath));
        return music;
    }

    public static void vodio()
    {

    }
}