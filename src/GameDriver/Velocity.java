package GameDriver;

import java.lang.Math;
public class Velocity
        /*�ٶȿ�����
         * ֻ��һ��ʵ������main������ͷ�����̹߳���
         * ���룺����˫����ǰ������
         * ������з���ǰ���ٶ�*/
{
    PositionQueue positionQ_hero;
    PositionQueue positionQ_enemy;
    float Xa,Xb,Ya,Yb;//��ǰ����˫���ĺ������꣨a�����ҷ���b����з���
    float Xa0,Xb0,Ya0,Yb0;//��Ӧʱ��֮ǰǰ����˫���ĺ������꣨a�����ҷ���b����з���
    float Nbx=0;//��һ˲�䣨��һѭ�����з��ٶȵ�λ������x�᷽��ķ�������������
    float Nby=0;//��һ˲�䣨��һѭ�����з��ٶȵ�λ������y�᷽��ķ�������������
    float V=0;//�з��ٶȴ�С���Ǹ���
    float BA0max=800;//�з�ʶ��Χ�뾶����������򣬵з���ʼ׷���ҷ���
    float Vmax=5;//�з��������
    float collisionD=200;//����ǡ����ײ�����˿�ʼ���й����˶���ʱ�ľ��루���°�Ϊ60��
    float Nbx_inertia=0;//�з��Ĺ����ٶȣ��з�������Ӧʱ��֮ǰ�ҷ�����λ��֮�󱣳ֵ��ٶȣ���λ������x�᷽��ķ�������������
    float Nby_inertia=0;//�з��Ĺ����ٶȣ��з�������Ӧʱ��֮ǰ�ҷ�����λ��֮�󱣳ֵ��ٶȣ���λ������x�᷽��ķ�������������
    int N_30=30;
    int N_100=100;
    int N_100_x=100;//���ڱ��ֹ����ٶ�ʱ����ʱ��3�°�Ϊ30��
    int N_100_y=100;//���ڱ��ֹ����ٶ�ʱ����ʱ��3�°�Ϊ30��
    Velocity()
    {
        positionQ_hero = new PositionQueue("hero");
        positionQ_enemy = new PositionQueue("enemy");
    }

    public void RefreshThePositions(float XofTheHero,float YofTheHero,float XofTheEnemy,float YofTheEnemy)
        /*1.Refresh all the positions and put them into the position queues
         * 2.���� ��Ӧʱ��֮ǰ�ҷ��ĺ�����*/
    {
        Xa=XofTheHero;
        Ya=YofTheHero;
        Xb=XofTheEnemy;
        Yb=YofTheEnemy;
        Xa0=positionQ_hero.getXFromQ();//���� ��Ӧʱ��֮ǰ�ҷ��ĺ�����
        Ya0=positionQ_hero.getYFromQ();
        positionQ_hero.addToQ(XofTheHero, YofTheHero);
        positionQ_enemy.addToQ(XofTheEnemy, YofTheEnemy);
    }

    public float VxOfTheEnemy()//���㲢������һ˲�䣨��һѭ�����з��ٶ���x�᷽��ķ�������������
    {
        float BA0=(float) Math.sqrt(Math.pow((Xa0-Xb),2)+Math.pow((Ya0-Yb),2));
        //�з��˿�λ���뷴Ӧʱ��֮ǰ�ҷ�λ��֮��ľ���BA0�Ĵ�С
        if(BA0<0.5)//�����Ҿ��뼫����������ʱֹͣ�˶������������Ϊ����ַ�ĸΪ���״����
        {
            return 0;
        }

        //V=-(Vmax/(BA0max+10))*BA0+Vmax;//�õ����˵����ʣ�����һ�����ž���ļ�С�������ӣ�
        V=speed_Enemy(BA0,2);

        Nbx=(Xa0-Xb)/BA0;

        if((BA0<=collisionD))//���ез��ͷ�Ӧʱ��֮ǰ���ҷ�����ײ��������ʮ�ֽӽ���
        {
            N_100_x--;
            if(N_100_x==99)//���ո�����
            {
                Nbx_inertia=Nbx;//�з��ġ������ٶȡ��ķ���=��ײ����ǰ��˲ʱ�ٶȵķ���
            }
            return Nbx_inertia*V;//�з����ٶȷ�����һ��ʱ���ڲ���
        }
        else
        {
            if(N_100_x<100&&N_100_x>0)
            {
                N_100_x--;
                return Nbx_inertia*V;//
            }
            else
            {
                N_100_x=100;
                return Nbx*V;
            }

        }
    }
    public float VyOfTheEnemy()//���㲢������һ˲�䣨��һѭ�����з��ٶ���y�᷽��ķ�������������
    {
        float BA0=(float) Math.sqrt(Math.pow((Xa0-Xb),2)+Math.pow((Ya0-Yb),2));
        //�з��˿�λ���뷴Ӧʱ��֮ǰ�ҷ�λ��֮��ľ���BA0�Ĵ�С
        if(BA0<=0.5)//�����Ҿ��뼫����������ʱֹͣ�˶������������Ϊ����ַ�ĸΪ���״����
        {
            return 0;
        }

        //V=-(Vmax/(BA0max+10))*BA0+Vmax;//�õ����˵�����
        V=speed_Enemy(BA0,2);
        //System.out.println(BA0);
        Nby=(Ya0-Yb)/BA0;
		/*//method1
		if((BA0<=collisionD))//���ез��ͷ�Ӧʱ��֮ǰ���ҷ�����ײ��������ʮ�ֽӽ���
		{
			N_10--;
			if(N_10==9)//���ո�����
			{
				initializePositionQ_hero(1000*(Xa0-Xb)+Xb,1000*(Ya0-Yb)+Yb);//��A0'�����ꡰ��ʼ�����ҷ������������
			}
		}
		else
		{
			N_10=10;
		}*/
        if((BA0<=collisionD))//���ез��ͷ�Ӧʱ��֮ǰ���ҷ�����ײ��������ʮ�ֽӽ���
        {
            N_100_y--;
            if(N_100_y==99)//���ո�����
            {
                Nby_inertia=Nby;//�з��ġ������ٶȡ��ķ���=��ײ����ǰ��˲ʱ�ٶȵķ���
            }
            return Nby_inertia*V;//�з����ٶȷ�����һ��ʱ���ڲ���
        }
        else
        {
            if(N_100_y<100&&N_100_y>0)
            {
                N_100_y--;
                return Nby_inertia*V;//
            }
            else
            {
                N_100_y=100;
                return Nby*V;
            }

        }

    }

    private float speed_Enemy(float D,int speedMode)
    //calculate the speed of enemy depending on the distance D and the speedMode
    {
        if(speedMode==1)//ģʽһ���������ž���ļ�С��������
        {
            if(D<=BA0max)
            {
                return -(Vmax/(BA0max+10))*D+Vmax;
            }
            else
                return 0;//�����Ҿ�����ڵз���������Χ�뾶����з���ֹ
        }
        if(speedMode==2)//ģʽ�����������ž���ļ�С���������ӣ����Σ�
        {
            if(D<=BA0max)
            {
                return (float) Math.pow((D-BA0max-10)/(BA0max+10), 2)*Vmax;
            }
            else return 0;
        }
        else
            return 0;
    }

    public void initializePositionQ_hero(float x,float y)//���ҷ���Ӧ������������е�ÿ�����궼��ʼ��Ϊʵ�ʳ�ʼ����
    {
        positionQ_hero.initializeQ(x, y);
    }
    public void initializePositionQ_enemy(float x2,float y2)
    {
        positionQ_enemy.initializeQ(x2, y2);
    }

}
