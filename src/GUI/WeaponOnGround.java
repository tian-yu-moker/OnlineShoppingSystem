package GUI;
import org.jsfml.graphics.RectangleShape;

public class WeaponOnGround 
/*�����ÿ������洢�����ϵ�һ��װ���Ŀռ����
 * 2020.5.2
 * Backpack���������ࣨ�õ��˴�����������ͣ����߼�ͷ��*/
{
	WeaponOnGround(int x1,int y1,int width1,int height1,int Number1,RectangleShape rectangleShape1)
	{
		
		x=x1;
		y=y1;
		width=width1;
		height =height1;
		Number=Number1;
		rectangleShape=rectangleShape1;
		amount=1;//Ĭ�ϱ�װ������Ϊ1
	}

	//����
	WeaponOnGround(int Number1,int amount1,RectangleShape RectangleShape1)
	{
		Number=Number1;
		amount=amount1;
		rectangleShape=RectangleShape1;
	}
	
	int x=0;//װ���ĺ����꣨ͼƬ�����Ͻǣ������jsfml���ڣ�
	int y=0;//װ����������
	int width=100;//װ��ͼƬ�Ŀ��
	int height=100;//װ��ͼƬ�ĸ߶�
	int Number;//װ���ı�ţ���ͼƬһһ��Ӧ��
	RectangleShape rectangleShape=null;

	int amount=1;//��װ�������������б����ͬ������װ������һ��WeaponOnGround����

}
