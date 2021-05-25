package GUI;

import java.io.IOException;
import java.nio.file.Paths;

import Actors.Actors;
import Actors.Blood;
import GameDriver.staticVariable;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class MainWindowDuringPlaying_icons
//main函数中一个实例
{
    /*主打斗界面各图标绘制算法
     * 刘嘉奕
     * 2020年4月15日修改并封装
     */

    RectangleShape picture_lowerLeft;
    RectangleShape picture_packet_icon;
    //RectangleShape picture_pause;
    //RectangleShape picture_enemy_hp;
    //RectangleShape picture_mapBase;
    //RectangleShape picture_sordman;
    public MainWindowDuringPlaying_icons(RenderWindow window)
        /*构造函数
         * 2020.4.16
         * 用于加载主打斗界面各图标并根据jsfml主窗口尺寸和初始位置设置其初始大小和位置*/
    {
        int jsfmlWindow_width=window.getSize().x;
        int jsfmlWindow_height=window.getSize().y;
        picture_lowerLeft=createImage("myImage/BattleUI/lowerLeft.png", jsfmlWindow_width-400, (jsfmlWindow_width-400)/6, 0, jsfmlWindow_height-(jsfmlWindow_width-400)/6);
        picture_packet_icon=createImage("myImage/BattleUI/packet_icon.png", 150, 200, jsfmlWindow_width-400, jsfmlWindow_height-200);
        //picture_pause=createImage("myImage/BattleUI/pause.png", 150, 200, jsfmlWindow_width-200, jsfmlWindow_height-200);
        //picture_enemy_hp=createImage("myImage/BattleUI/enemy_hp.png", 700, 100, 0, 50);
        //picture_mapBase=createImage("myImage/BattleUI/mapBase.png", 400, 200, jsfmlWindow_width-500, 0);
        //picture_sordman=createImage("myImage/BattleUI/sordman.png", 50, 50, 20, 60);
    }


    public void drawIconsInMainWindowDuringPlaying(RenderWindow window,int jsfmlWindow_width,int jsfmlWindow_height)
    {
        /*根据jsfml主窗口尺寸更新打斗主界面的各图标位置（尺寸目前不更新）
         * 2020年4月15日修改
         * 4.16 再次修改
         *可以适应的窗口尺寸：宽度>=1000,高度>=600
         */
        picture_lowerLeft.setPosition(0, jsfmlWindow_height-(jsfmlWindow_width-400)/6);
        picture_packet_icon.setPosition( jsfmlWindow_width-400, jsfmlWindow_height-200);
        //picture_pause.setPosition(jsfmlWindow_width-200, jsfmlWindow_height-200);
        //picture_enemy_hp.setPosition(0, 50);
        //picture_mapBase.setPosition(jsfmlWindow_width-500, 0);
        //picture_sordman.setPosition(20, 60);

        RectangleShape rectangular2=new RectangleShape();
        rectangular2.setSize(new Vector2f(125*Blood.GethpLength_hero()/Actors.hp,10));
        rectangular2.setFillColor(new Color(220,20,60,250));
        rectangular2.setPosition(159,717);
        RectangleShape rectangular=new RectangleShape();
        rectangular.setSize(new Vector2f((95* Blood.GetmanaLength_hero()/Actors.mana),7));
        rectangular.setFillColor(new Color(100,149,237,250));
        rectangular.setPosition(80,750);
        
       //the level string       

		Text level=new Text("", staticVariable.dialogFont,20);
	    level.setString(Actors.l);
		level.setColor(Color.YELLOW);
		level.setPosition(39,724);
	
        window.draw(picture_lowerLeft);
        window.draw(picture_packet_icon);
        //window.draw(picture_pause);

        //window.draw(picture_enemy_hp);
        //        //window.draw(picture_mapBase);
        //        //window.draw(picture_sordman);
        window.draw(rectangular2);
        window.draw(rectangular);
        window.draw(level);
    }

    public static RectangleShape createImage( String image, int Image_x, int Image_y, int position_x, int position_y)
    {   //作者：肖文伟
        //加载本地图片
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(image));//从本地加载图片
        } catch (IOException e) {
            e.printStackTrace(); }
        imgTexture.setSmooth(true); //一定要加，这个是抗锯齿的方法，也就是将图片变得平滑的方法

        //容器放置游戏开始背景图图片； 放缩图片与容器大小协调：设置图片的显示大小的长和宽
        RectangleShape img = new RectangleShape(new Vector2f(Image_x, Image_y));
        img.setTexture(imgTexture);
        img.setPosition(position_x, position_y); //设置这个容器的坐标，左上角为基准
        return img;
    }
}

