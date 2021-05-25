package GUI;

import GameDriver.Driver;
import GameDriver.LevelDetermination;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FlyingLeaves
{
	/*��Ҷ�׷��㷨
	 * �����ڻ��Ʒ׷ɵ���Ҷ
	 * ������ԭ��
	 * 2020��4��16��*/
	
	RectangleShape picture_leaf1;
	RectangleShape picture_leaf2;
	RectangleShape picture_leaf3;
	
	
	int leafNumbers=10;//��Ҷ������
	
	/*��Ҷ�ĳ�ʼ������*/
	int leaf_x0[]= new int[leafNumbers];
	int leaf_y0[]= new int[leafNumbers];
	/*��Ҷ�ĺ��������飨���ϸ��£�*/
	int leaf_x[]= new int[leafNumbers];
	int leaf_y[]= new int[leafNumbers];
	
	//��Ҷ�����ٶ����飨��ʱʹ�ã�
	int Vx_leaf[]=new int[leafNumbers];
	int Vy_leaf[]=new int[leafNumbers];
	
	//���챾������ʱ��
	long startTime;
	
	public FlyingLeaves()
	/*���캯��
	 * 1.������ҶͼƬ��������ߴ�
	 * 2.��������Ҷ�ĳ�ʼλ��
	 * 3.��ȡ��������ʱ��*/
	{
		picture_leaf1=createImage("myImage/leaves/leaf2.png", 100, 100, 0, 0);//("myImage/Rain/raindrop1.png", 10, 10, 0, 0);
		
		for(int i=0;i<=leafNumbers-1;i++)
		{
			leaf_x0[i]=i*150;
			leaf_y0[i]=(int) (800*Math.random());
			leaf_x[i]=leaf_x0[i];
			leaf_y[i]=leaf_y0[i];
			
		}
		startTime=System.currentTimeMillis();
	}
	
	int N_50=50;//����ʱ��50-0ѭ������ִ��һ��drawFlyingLeaves������N_50--�������ڿ�����Ҷ�ٶȵĶ��ڸ��£�
	int N_100=100;//����ʱ��100-0��ѭ�������ڿ�����ص���Ҷ������ʧ��
	int N_360=360;//��360-1�����ڿ��ƿ�����Ҷ����ת�Ƕ�
	
	//����Ҷ������
	ArrayList<LeafOnGround> leavesOnGround=new ArrayList<LeafOnGround>();
	
	public void drawFlyingLeaves(RenderWindow window)
	/*����һϵ�з׷ɵ���Ҷ*/
	{
		//�ж��Ƿ����˳�����Ҷ
		boolean leavesOccur= (LevelDetermination.level==8)
				||(LevelDetermination.level==1&& Driver.playerRelativeToBg_x>2541&& Driver.playerRelativeToBg_x<3170&& Driver.playerRelativeToBg_y<1657)
				||(LevelDetermination.level==1&& Driver.playerRelativeToBg_x>4235&& Driver.playerRelativeToBg_x<6791&& Driver.playerRelativeToBg_y>3045&& Driver.playerRelativeToBg_y<4731)
				;

		//2020.5.25
		if(LevelDetermination.level==1)
		{
			for(int k=0;k<leavesOnGround.size();k++)//���ݵ���Ҷ��������Ƶ����ϵ�Ҷ��
			{
				picture_leaf1.setPosition(leavesOnGround.get(k).x,leavesOnGround.get(k).y);


				picture_leaf1.setScale(1,1);//�ָ�ԭ���ű���

				if(leavesOnGround.get(k).i%3==0)
				{
					picture_leaf1.setScale((float)0.5, (float)0.5);
				}
				if(leavesOnGround.get(k).i%3==1)
				{
					picture_leaf1.setScale((float)0.7, (float)0.7);
				}

				//����ص�Ҷ����ת�̶��Ƕ�
				picture_leaf1.setRotation(30+k);

				window.draw(picture_leaf1);
			}
		}

		if(leavesOccur)
		{
			if(true)//(System.currentTimeMillis()-startTime)>1000*20&&(System.currentTimeMillis()-startTime)<1000*100)
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

					//*********************������Ҷ���ƿ�ʼ**************************************
					/*����Ҷλ�ô������Ҷ������*/
					//if(N_100%10==9)//(N_100==99)
					//{
					if(leaf_y[i]>=620&&leaf_x[i]<=1000&&((i%3==2)||(i%3==1)))
					{
						leavesOnGround.add(new LeafOnGround(leaf_x[i],leaf_y[i],i));
						leaf_y[i]=-200;//ʹҶ����غ󲻼�������
					}
					if(leavesOnGround.size()>100)//�����ϵ�Ҷ�����һ��Ƭ
					{
						leavesOnGround.remove(0);
					}
					//}

					/*2020.5.25
					��Ϊ�ں��ʵĹؿ�����������
					for(int k=0;k<leavesOnGround.size();k++)//���ݵ���Ҷ��������Ƶ����ϵ�Ҷ��
					{
						picture_leaf1.setPosition(leavesOnGround.get(k).x,leavesOnGround.get(k).y);


						picture_leaf1.setScale(1,1);//�ָ�ԭ���ű���

						if(leavesOnGround.get(k).i%3==0)
						{
							picture_leaf1.setScale((float)0.5, (float)0.5);
						}
						if(leavesOnGround.get(k).i%3==1)
						{
							picture_leaf1.setScale((float)0.7, (float)0.7);
						}

						//����ص�Ҷ����ת�̶��Ƕ�
						picture_leaf1.setRotation(30+k);

						window.draw(picture_leaf1);
					}*/
					//*********************������Ҷ���ƽ���**************************************

					/*������Ҷ�ٶ�*/
					if(N_50==49)//���ڸ����˶��ٶ�
					{
						Vy_leaf[i]=(int) (5+5*(Math.random()-0.5));
						Vx_leaf[i]=(int) (-10*(Math.random()-0.5));//-2;
						//picture_leaf1.setRotation(360*(float) Math.random());
					}

					//��ת���е���Ҷ
					picture_leaf1.setRotation(N_360*2);

					//�ƶ���Ҷ
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

					//����������Ҷ�ĳߴ�
					picture_leaf1.setScale(1,1);//�ָ�ԭ���ű���
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

		}
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
