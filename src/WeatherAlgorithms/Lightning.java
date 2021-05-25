package WeatherAlgorithms;

import Actors.Actors;
import GameDriver.Driver;
import GameDriver.LevelDetermination;
import TowerDenfense.TDLoadMusic;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

public class Lightning 
/*闪电算法
 * 用于绘制闪电
 * 刘嘉奕原创
 * 2020.5.17*/
{
	
	public Lightning()
	{
		startTime=System.currentTimeMillis();
		picture_lightning1=createImage("myImage/Lightning/lightning1.png",900,900,300,0);//650,650,300,0);
		
		//加载雷声
		Sound_thunder1 = TDLoadMusic.startMusic("myImage/Lightning/thunder1.wav");
		Sound_thunder1.setVolume(20.0f);
	}
	RectangleShape picture_lightning1;
	int LightningPosition_x0=500;//闪电的初始坐标
	int LightningPosition_x=500;//闪电的坐标
	int LightningPosition_y=-50;
	int N_50=50;//50-0循环，用于控制闪电出现的时长
	
	//构造本类对象的时刻
	long startTime;
	
	//雷声
	Sound Sound_thunder1;
	int N_1_soundPlay=1;//用于控制雷声仅播放一次（每次闪电出现）（==1,播放）
	
	public boolean drawLlightning(RenderWindow window)
	/*在某段时间绘制闪电*/
	{
		//判断是否适宜出现闪电(第一关每T秒出现一次闪电，持续时间0.3秒）
		int T=4;//21;
		boolean lightningOccur= (LevelDetermination.level==1)&&
                                 (System.currentTimeMillis())%(1000*T)>0
                                  &&(System.currentTimeMillis())%(1000*T)<0.3*1000
				                  && Driver.playerRelativeToBg_y<=2778
				                  && Driver.playerRelativeToBg_x>5000
                               ;
		//若时间适宜出现闪电
		if(lightningOccur)//((System.currentTimeMillis()-startTime)>1000*2&&(System.currentTimeMillis()-startTime)<1000*3)
		{
		    if(N_1_soundPlay==1)//若本次闪电刚刚开始出现
		    {
		    	Sound_thunder1.play();
		    	LightningPosition_x=(int) (LightningPosition_x0*Math.random());//调整闪电的最左端坐标

				//LightningPosition_x  ~ LightningPosition_x+550*Math.random()
				if(LightningPosition_x<150&&LightningPosition_x>50)//若被雷击中
				{
					if(Actors.hpHero>0)
						Actors.hpHero -= 20;

				}
		    }
		    N_1_soundPlay=0;
		    
		    //制造闪动效果
			picture_lightning1.setPosition((float) (LightningPosition_x+550*Math.random()), LightningPosition_y);
			
		    window.draw(picture_lightning1);
		    

		    return true;
		}
		//若时间不适宜出现闪电
		N_1_soundPlay=1;
		return false;
	}
	
	
	public static RectangleShape createImage(String image, int Image_x, int Image_y, int position_x, int position_y)
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
