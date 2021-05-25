package WeatherAlgorithms;
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import GameDriver.LevelDetermination;

public class Raining
{
	/*降雨算法
	 * 用于在绘制降落的雨滴
	 * 刘嘉奕原创
	 * 2020年4月16日*/
	
	RectangleShape picture_leaf1;
	RectangleShape picture_raindrop_big;//从窗口上方滴落的大水滴
	RectangleShape picture_leaf3;
	
	
	int leafNumbers=100;//落叶的数量
	
	/*树叶的初始坐标组*/
	int leaf_x0[]= new int[leafNumbers];
	int leaf_y0[]= new int[leafNumbers];
	/*树叶的横纵坐标组（不断更新）*/
	int leaf_x[]= new int[leafNumbers];
	int leaf_y[]= new int[leafNumbers];
	
	//落叶飞行速度数组（临时使用）
	int Vx_leaf[]=new int[leafNumbers];
	int Vy_leaf[]=new int[leafNumbers];
	
	public Raining()
	/*构造函数
	 * 1.加载树叶图片并设置其尺寸
	 * 2.设置众树叶的初始位置*/
	{
		picture_leaf1=createImage("myImage/Rain/drop.png", 3, 10, 0, 0);//("myImage/Rain/raindrop1.png", 10, 10, 0, 0);
		picture_raindrop_big=createImage("myImage/Rain/drop.png", 15, 40, 0, 0);
		//picture_leaf2=createImage("myImage/leaves/leaf1.png", 50, 50, 0, 0);
		//picture_leaf3=createImage("myImage/leaves/leaf1.png", 50, 50, 0, 0);
		
		for(int i=0;i<=leafNumbers-1;i++)
		{
			leaf_x0[i]=i*15;
			leaf_y0[i]=(int) (600*Math.random());
			leaf_x[i]=leaf_x0[i];
			leaf_y[i]=leaf_y0[i];
			
		}
	}
	
	float raindrop_big_x=1380;
	float raindrop_big_y=125;
	float raindrop_big_Vy=0;
	
	public void drawRaindrops(RenderWindow window)
	/*绘制一系列降落的雨滴*/
	{
		//判断是否适宜出现降雨
		boolean RainOccur= (LevelDetermination.level==1||LevelDetermination.level==3);
		//若时间适宜出现闪电
		if(RainOccur)
		{
			/*****************绘制大水滴****************/
			if(raindrop_big_y<125+40)//水滴形成时下降缓慢
				raindrop_big_Vy=(float) 0.6;
			else
				raindrop_big_Vy+=1;//匀加速直线运动（自由落体）
			
			raindrop_big_y+=raindrop_big_Vy;
			if(raindrop_big_y>(window.getSize().y))
			{
				raindrop_big_Vy=0;
				raindrop_big_y=100;
			}
			picture_raindrop_big.setPosition(raindrop_big_x, raindrop_big_y);
			window.draw(picture_raindrop_big);
			/**********绘制大水滴完成**************************************/
			for(int i=0;i<=leafNumbers-1;i++)	
			{
				/*设置树叶速度*/
				Vy_leaf[i]=10+leaf_y[i]/40;//33;
				Vx_leaf[i]=0;//-20;
				
				//移动树叶
				leaf_x[i]=leaf_x[i]+Vx_leaf[i];
			    leaf_y[i]=leaf_y[i]+Vy_leaf[i];
			    if(leaf_y[i]>(window.getSize().y-100))
				{
					leaf_y[i]=0;
					leaf_x[i]=leaf_x0[i];
				}
				if(leaf_x[i]<0)
				{
					
				}
				
				picture_leaf1.setPosition( leaf_x[i], leaf_y[i]);
				//picture_leaf2.setPosition( leaf_x[i], leaf_y[i]+50);
				//picture_leaf3.setPosition( leaf_x[i], leaf_y[i]+10);
				window.draw(picture_leaf1);
				//window.draw(picture_leaf2);
				//window.draw(picture_leaf3);
				
			}

		}
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
