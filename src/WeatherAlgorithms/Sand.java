package WeatherAlgorithms;

import java.io.IOException;
import java.nio.file.Paths;

import GameDriver.LevelDetermination;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import TowerDenfense.TDLoadMusic;

public class Sand
{
	/*降雨算法
	 * 用于在绘制降落的雨滴
	 * 刘嘉奕原创
	 * 2020年4月16日*/
	
	RectangleShape picture_sand1;
	RectangleShape picture_sand2;
	
	int leafNumbers=1;//落叶的数量
	
	/*树叶的初始坐标组*/
	int leaf_x0[]= new int[leafNumbers];
	int leaf_y0[]= new int[leafNumbers];
	/*树叶的横纵坐标组（不断更新）*/
	int leaf_x[]= new int[leafNumbers];
	int leaf_y[]= new int[leafNumbers];
	
	//落叶飞行速度数组（临时使用）
	int Vx_leaf=20;//[]=new int[leafNumbers];
	int Vy_leaf=0;//[]=new int[leafNumbers];
	
	public Sand()
	/*构造函数
	 * 1.加载树叶图片并设置其尺寸
	 * 2.设置众树叶的初始位置*/
	{
		picture_sand1=createImage("myImage/Sand/sand3.png", 672*2, 523*2, 0, 0);//("myImage/Rain/raindrop1.png", 10, 10, 0, 0);
		//picture_sand2=createImage("myImage/Sand/sand4.png", 1170*2, 658*2, 0, 0);
		picture_sand2=createImage("myImage/Sand/sand5.png", 977*2, 535*2, 0, 0);//535, 0, 0);
		
		for(int i=0;i<=leafNumbers-1;i++)
		{
			leaf_x0[i]=(int) (-1600*Math.random());
			leaf_y0[i]=0;//-500+i*100;
			leaf_x[i]=leaf_x0[i];
			leaf_y[i]=leaf_y0[i];
			
		}
		
		//加载风沙声
		Sound_sand1=TDLoadMusic.startMusic("myImage/Sand/sand1.wav");
	}
	
	//风沙声
	Sound Sound_sand1;
	int N_1_soundPlay=1;//用于控制风沙声仅播放一次（每次飞沙出现）（==1,播放）
	

	int N_4_sandType=4;//1-4循环，用于控制飞沙种类
	public void drawSand(RenderWindow window)
	/*绘制一系列降落的雨滴*/
	{
		if(LevelDetermination.level==9)//若本关适宜飞沙
		{
			if(N_1_soundPlay==1)//若本次飞沙刚刚开始出现
			{
				//if(N_4_sandType==1)
				//{
				Sound_sand1.play();//播放声音
				//}
			}
			N_1_soundPlay=0;

			for(int i=0;i<=leafNumbers-1;i++)
			{
				//Vy_leaf=(int) (4*(Math.random()-0.5));
				//移动树叶
				leaf_x[i]=leaf_x[i]+Vx_leaf;
				leaf_y[i]=leaf_y[i]+Vy_leaf;

				//一次飞沙结束
				if(leaf_x[i]>(window.getSize().x+100))
				{
					leaf_x[i]=(int) (-1600*Math.random()-2000);
					leaf_y[i]=(int) (leaf_y0[i]+300*(Math.random()-0.5));

					//每次飞沙的规模不同
					picture_sand1.setScale((float)(Math.random()+0.8), (float)(Math.random()+0.8));

					N_4_sandType--;
					if(N_4_sandType==0)
					{
						N_4_sandType=4;
					}

					N_1_soundPlay=1;
				}

				if(N_4_sandType==1)
				{
					picture_sand1.setPosition( leaf_x[i], leaf_y[i]);
					window.draw(picture_sand1);

				}
				else//if(N_4_sandType==2)
				{
					picture_sand2.setPosition( leaf_x[i], leaf_y[i]);
					window.draw(picture_sand2);
				}
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
