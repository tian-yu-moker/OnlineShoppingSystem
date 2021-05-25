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
	/*�����㷨
	 * �����ڻ��ƽ�������
	 * ������ԭ��
	 * 2020��4��16��*/
	
	RectangleShape picture_leaf1;
	RectangleShape picture_raindrop_big;//�Ӵ����Ϸ�����Ĵ�ˮ��
	RectangleShape picture_leaf3;
	
	
	int leafNumbers=100;//��Ҷ������
	
	/*��Ҷ�ĳ�ʼ������*/
	int leaf_x0[]= new int[leafNumbers];
	int leaf_y0[]= new int[leafNumbers];
	/*��Ҷ�ĺ��������飨���ϸ��£�*/
	int leaf_x[]= new int[leafNumbers];
	int leaf_y[]= new int[leafNumbers];
	
	//��Ҷ�����ٶ����飨��ʱʹ�ã�
	int Vx_leaf[]=new int[leafNumbers];
	int Vy_leaf[]=new int[leafNumbers];
	
	public Raining()
	/*���캯��
	 * 1.������ҶͼƬ��������ߴ�
	 * 2.��������Ҷ�ĳ�ʼλ��*/
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
	/*����һϵ�н�������*/
	{
		//�ж��Ƿ����˳��ֽ���
		boolean RainOccur= (LevelDetermination.level==1||LevelDetermination.level==3);
		//��ʱ�����˳�������
		if(RainOccur)
		{
			/*****************���ƴ�ˮ��****************/
			if(raindrop_big_y<125+40)//ˮ���γ�ʱ�½�����
				raindrop_big_Vy=(float) 0.6;
			else
				raindrop_big_Vy+=1;//�ȼ���ֱ���˶����������壩
			
			raindrop_big_y+=raindrop_big_Vy;
			if(raindrop_big_y>(window.getSize().y))
			{
				raindrop_big_Vy=0;
				raindrop_big_y=100;
			}
			picture_raindrop_big.setPosition(raindrop_big_x, raindrop_big_y);
			window.draw(picture_raindrop_big);
			/**********���ƴ�ˮ�����**************************************/
			for(int i=0;i<=leafNumbers-1;i++)	
			{
				/*������Ҷ�ٶ�*/
				Vy_leaf[i]=10+leaf_y[i]/40;//33;
				Vx_leaf[i]=0;//-20;
				
				//�ƶ���Ҷ
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
	{   //���ߣ�Ф��ΰ
        //���ر���ͼƬ
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(image));//�ӱ��ؼ���ͼƬ
        } catch (IOException e) {
            e.printStackTrace(); }
        imgTexture.setSmooth(true); //һ��Ҫ�ӣ�����ǿ���ݵķ�����Ҳ���ǽ�ͼƬ���ƽ���ķ���

        //����������Ϸ��ʼ����ͼͼƬ�� ����ͼƬ��������СЭ��������ͼƬ����ʾ��С�ĳ��Ϳ�
        RectangleShape img = new RectangleShape(new Vector2f(Image_x, Image_y));
        img.setTexture(imgTexture);
        img.setPosition(position_x, position_y); //����������������꣬���Ͻ�Ϊ��׼
        return img;
    }
}
