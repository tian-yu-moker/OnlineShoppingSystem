package GameDriver;

import java.lang.Math;
public class Velocity
        /*速度控制类
         * 只有一个实例，在main函数开头。各线程共用
         * 输入：敌我双方当前的坐标
         * 输出：敌方当前的速度*/
{
    PositionQueue positionQ_hero;
    PositionQueue positionQ_enemy;
    float Xa,Xb,Ya,Yb;//当前敌我双方的横纵坐标（a代表我方，b代表敌方）
    float Xa0,Xb0,Ya0,Yb0;//反应时间之前前敌我双方的横纵坐标（a代表我方，b代表敌方）
    float Nbx=0;//下一瞬间（下一循环）敌方速度单位向量在x轴方向的分量（有正负）
    float Nby=0;//下一瞬间（下一循环）敌方速度单位向量在y轴方向的分量（有正负）
    float V=0;//敌方速度大小（非负）
    float BA0max=800;//敌方识别范围半径（进入此区域，敌方开始追击我方）
    float Vmax=5;//敌方最大速率
    float collisionD=200;//敌我恰好相撞（敌人开始进行惯性运动）时的距离（三月版为60）
    float Nbx_inertia=0;//敌方的惯性速度（敌方触及反应时间之前我方所在位置之后保持的速度）单位向量在x轴方向的分量（有正负）
    float Nby_inertia=0;//敌方的惯性速度（敌方触及反应时间之前我方所在位置之后保持的速度）单位向量在x轴方向的分量（有正负）
    int N_30=30;
    int N_100=100;
    int N_100_x=100;//用于保持惯性速度时倒计时（3月版为30）
    int N_100_y=100;//用于保持惯性速度时倒计时（3月版为30）
    Velocity()
    {
        positionQ_hero = new PositionQueue("hero");
        positionQ_enemy = new PositionQueue("enemy");
    }

    public void RefreshThePositions(float XofTheHero,float YofTheHero,float XofTheEnemy,float YofTheEnemy)
        /*1.Refresh all the positions and put them into the position queues
         * 2.更新 反应时间之前我方的横坐标*/
    {
        Xa=XofTheHero;
        Ya=YofTheHero;
        Xb=XofTheEnemy;
        Yb=YofTheEnemy;
        Xa0=positionQ_hero.getXFromQ();//更新 反应时间之前我方的横坐标
        Ya0=positionQ_hero.getYFromQ();
        positionQ_hero.addToQ(XofTheHero, YofTheHero);
        positionQ_enemy.addToQ(XofTheEnemy, YofTheEnemy);
    }

    public float VxOfTheEnemy()//计算并返回下一瞬间（下一循环）敌方速度在x轴方向的分量（有正负）
    {
        float BA0=(float) Math.sqrt(Math.pow((Xa0-Xb),2)+Math.pow((Ya0-Yb),2));
        //敌方此刻位置与反应时间之前我方位置之间的距离BA0的大小
        if(BA0<0.5)//若敌我距离极近，敌人暂时停止运动（避免因距离为零出现分母为零的状况）
        {
            return 0;
        }

        //V=-(Vmax/(BA0max+10))*BA0+Vmax;//得到敌人的速率（方案一，随着距离的减小线性增加）
        V=speed_Enemy(BA0,2);

        Nbx=(Xa0-Xb)/BA0;

        if((BA0<=collisionD))//若敌敌方和反应时间之前的我方“相撞”（距离十分接近）
        {
            N_100_x--;
            if(N_100_x==99)//若刚刚碰到
            {
                Nbx_inertia=Nbx;//敌方的“惯性速度”的方向=碰撞发生前的瞬时速度的方向
            }
            return Nbx_inertia*V;//敌方的速度方向在一段时间内不变
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
    public float VyOfTheEnemy()//计算并返回下一瞬间（下一循环）敌方速度在y轴方向的分量（有正负）
    {
        float BA0=(float) Math.sqrt(Math.pow((Xa0-Xb),2)+Math.pow((Ya0-Yb),2));
        //敌方此刻位置与反应时间之前我方位置之间的距离BA0的大小
        if(BA0<=0.5)//若敌我距离极近，敌人暂时停止运动（避免因距离为零出现分母为零的状况）
        {
            return 0;
        }

        //V=-(Vmax/(BA0max+10))*BA0+Vmax;//得到敌人的速率
        V=speed_Enemy(BA0,2);
        //System.out.println(BA0);
        Nby=(Ya0-Yb)/BA0;
		/*//method1
		if((BA0<=collisionD))//若敌敌方和反应时间之前的我方“相撞”（距离十分接近）
		{
			N_10--;
			if(N_10==9)//若刚刚碰到
			{
				initializePositionQ_hero(1000*(Xa0-Xb)+Xb,1000*(Ya0-Yb)+Yb);//用A0'的坐标“初始化”我方的坐标队列组
			}
		}
		else
		{
			N_10=10;
		}*/
        if((BA0<=collisionD))//若敌敌方和反应时间之前的我方“相撞”（距离十分接近）
        {
            N_100_y--;
            if(N_100_y==99)//若刚刚碰到
            {
                Nby_inertia=Nby;//敌方的“惯性速度”的方向=碰撞发生前的瞬时速度的方向
            }
            return Nby_inertia*V;//敌方的速度方向在一段时间内不变
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
        if(speedMode==1)//模式一，速率随着距离的减小线性增加
        {
            if(D<=BA0max)
            {
                return -(Vmax/(BA0max+10))*D+Vmax;
            }
            else
                return 0;//若敌我距离大于敌方的势力范围半径，则敌方静止
        }
        if(speedMode==2)//模式二，速率随着距离的减小非线性增加（二次）
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

    public void initializePositionQ_hero(float x,float y)//将我方对应的坐标队列组中的每个坐标都初始化为实际初始坐标
    {
        positionQ_hero.initializeQ(x, y);
    }
    public void initializePositionQ_enemy(float x2,float y2)
    {
        positionQ_enemy.initializeQ(x2, y2);
    }

}
