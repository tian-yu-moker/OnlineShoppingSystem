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
/*�����㷨
 * ���ڻ�������
 * ������ԭ��
 * 2020.5.17*/
{
	
	public Lightning()
	{
		startTime=System.currentTimeMillis();
		picture_lightning1=createImage("myImage/Lightning/lightning1.png",900,900,300,0);//650,650,300,0);
		
		//��������
		Sound_thunder1 = TDLoadMusic.startMusic("myImage/Lightning/thunder1.wav");
		Sound_thunder1.setVolume(20.0f);
	}
	RectangleShape picture_lightning1;
	int LightningPosition_x0=500;//����ĳ�ʼ����
	int LightningPosition_x=500;//���������
	int LightningPosition_y=-50;
	int N_50=50;//50-0ѭ�������ڿ���������ֵ�ʱ��
	
	//���챾������ʱ��
	long startTime;
	
	//����
	Sound Sound_thunder1;
	int N_1_soundPlay=1;//���ڿ�������������һ�Σ�ÿ��������֣���==1,���ţ�
	
	public boolean drawLlightning(RenderWindow window)
	/*��ĳ��ʱ���������*/
	{
		//�ж��Ƿ����˳�������(��һ��ÿT�����һ�����磬����ʱ��0.3�룩
		int T=4;//21;
		boolean lightningOccur= (LevelDetermination.level==1)&&
                                 (System.currentTimeMillis())%(1000*T)>0
                                  &&(System.currentTimeMillis())%(1000*T)<0.3*1000
				                  && Driver.playerRelativeToBg_y<=2778
				                  && Driver.playerRelativeToBg_x>5000
                               ;
		//��ʱ�����˳�������
		if(lightningOccur)//((System.currentTimeMillis()-startTime)>1000*2&&(System.currentTimeMillis()-startTime)<1000*3)
		{
		    if(N_1_soundPlay==1)//����������ոտ�ʼ����
		    {
		    	Sound_thunder1.play();
		    	LightningPosition_x=(int) (LightningPosition_x0*Math.random());//������������������

				//LightningPosition_x  ~ LightningPosition_x+550*Math.random()
				if(LightningPosition_x<150&&LightningPosition_x>50)//�����׻���
				{
					if(Actors.hpHero>0)
						Actors.hpHero -= 20;

				}
		    }
		    N_1_soundPlay=0;
		    
		    //��������Ч��
			picture_lightning1.setPosition((float) (LightningPosition_x+550*Math.random()), LightningPosition_y);
			
		    window.draw(picture_lightning1);
		    

		    return true;
		}
		//��ʱ�䲻���˳�������
		N_1_soundPlay=1;
		return false;
	}
	
	
	public static RectangleShape createImage(String image, int Image_x, int Image_y, int position_x, int position_y)
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
