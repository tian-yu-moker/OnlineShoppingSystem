package WeatherAlgorithms;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;

import GameDriver.LevelDetermination;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

public class Snowing
{
	/*降雪算法，基于落叶纷飞算法
	 * 用于绘制雪花
	 * 刘嘉奕原创
	 * 2020年5月19日*/
	
	RectangleShape picture_leaf1;
	RectangleShape picture_leaf2;
	RectangleShape picture_leaf3;
	
	
	int leafNumbers=10;//落叶的数量
	
	/*雪片的初始坐标组*/
	int leaf_x0[]= new int[leafNumbers];
	int leaf_y0[]= new int[leafNumbers];
	/*雪片的横纵坐标组（不断更新）*/
	int leaf_x[]= new int[leafNumbers];
	int leaf_y[]= new int[leafNumbers];
	
	//落叶飞行速度数组（临时使用）
	int Vx_leaf[]=new int[leafNumbers];
	int Vy_leaf[]=new int[leafNumbers];
	
	//构造本类对象的时刻
	long startTime;
	
	public Snowing()
	/*构造函数
	 * 1.加载雪片图片并设置其尺寸
	 * 2.设置众雪片的初始位置
	 * 3.获取构造对象的时刻*/
	{
		picture_leaf1=createImage("myImage/Rain/snow1.png", 50, 50, 0, 0);//("myImage/Rain/raindrop1.png", 10, 10, 0, 0);
		
		for(int i=0;i<=leafNumbers-1;i++)
		{
			leaf_x0[i]=i*150;
			leaf_y0[i]=(int) (800*Math.random());
			leaf_x[i]=leaf_x0[i];
			leaf_y[i]=leaf_y0[i];
			
		}
		startTime=System.currentTimeMillis();
	}
	
	int N_50=50;//倒计时（50-0循环）（执行一次drawFlyingLeaves函数，N_50--）（用于控制雪片速度的定期更新）
	int N_100=100;//倒计时（100-0）循环（用于控制落地的雪片定期消失）
	int N_360=360;//（360-1）用于控制空中雪片的旋转角度
	
	//地面雪片链表
	ArrayList<LeafOnGround> leavesOnGround=new ArrayList<LeafOnGround>();
	
	public boolean drawFlyingLeaves(RenderWindow window)
	/*绘制一系列纷飞的雪片（若本关适宜降雪）*/
	{
		if(LevelDetermination.level==10||LevelDetermination.level==4)//若本关适宜降雪
		{
			if(true);//(System.currentTimeMillis()-startTime)>1000*3&&(System.currentTimeMillis()-startTime)<1000*100)
			{
				N_50--;
				N_100--;
				N_360--;
				if(N_50==0)
					N_50=50;
				if(N_100==0)
					N_100=100;
				if(N_360==1)
					N_360=360;

				for(int i=0;i<=leafNumbers-1;i++)
				{

					//*********************地面落叶绘制开始**************************************
					/*将落叶位置传入地面雪片链表*/
					//if(N_100%10==9)//(N_100==99)
					//{
						if(leaf_y[i]>=650&&leaf_x[i]<=1000&&((i%3==0)||(i%3==1)))
						{
							leavesOnGround.add(new LeafOnGround(leaf_x[i],leaf_y[i],i));
							leaf_y[i]=-200;//使雪片落地后不继续下落
						}
						if(leavesOnGround.size()>100)//地面上的雪片最多一百片
						{
							leavesOnGround.remove(0);
						}
					//}
					for(int k=0;k<leavesOnGround.size();k++)//根据地面雪片链表绘制地面上的雪片
					{
						picture_leaf1.setPosition(leavesOnGround.get(k).x,leavesOnGround.get(k).y);


						picture_leaf1.setScale(1,1);//恢复原缩放比例

						if(leavesOnGround.get(k).i%3==0)
						{
							picture_leaf1.setScale((float)0.5, (float)0.5);
						}
						if(leavesOnGround.get(k).i%3==1)
						{
							picture_leaf1.setScale((float)0.7, (float)0.7);
						}

						//将落地的雪片旋转固定角度
						picture_leaf1.setRotation(30+k);

						window.draw(picture_leaf1);
					}
					//*********************地面落叶绘制结束**************************************

		            /*设置雪片速度*/
					if(N_50==49)//定期更新运动速度
					{
						Vy_leaf[i]=(int) (5+5*(Math.random()-0.5));
						Vx_leaf[i]=(int) (-10*(Math.random()-0.5));//-2;
						//picture_leaf1.setRotation(360*(float) Math.random());
					}

					//旋转空中的雪片
					picture_leaf1.setRotation(N_360*2);

					//移动雪片
					leaf_x[i]=leaf_x[i]+Vx_leaf[i];
				    leaf_y[i]=leaf_y[i]+Vy_leaf[i];
				    if(leaf_y[i]>(window.getSize().y+100))
					{
						leaf_y[i]=-200;
						leaf_x[i]=leaf_x0[i];
					}
					if(leaf_x[i]<0)
					{

					}

					picture_leaf1.setPosition( leaf_x[i], leaf_y[i]);
					//picture_leaf2.setPosition( leaf_x[i], leaf_y[i]+50);
					//picture_leaf3.setPosition( leaf_x[i], leaf_y[i]+10);

					//调整部分雪片的尺寸
					picture_leaf1.setScale(1,1);//恢复原缩放比例
					if(i%3==0)
					{
						picture_leaf1.setScale((float)0.5, (float)0.5);
					}
					if(i%3==1)
					{
						picture_leaf1.setScale((float)0.7, (float)0.7);
					}

					window.draw(picture_leaf1);


					//window.draw(picture_leaf2);
					//window.draw(picture_leaf3);

				}

			}
			return true;
		}

		return false;//若 本关不适宜降雪
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
