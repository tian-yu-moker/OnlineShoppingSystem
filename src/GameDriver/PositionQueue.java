package GameDriver;

public class PositionQueue
        /*2020.2.29 刘嘉奕
         * 每个对象代表一个坐标队列组，用于获得若干毫秒之前某个游戏人物（包括敌方）的横纵坐标数据
         * 每个游戏人物（包括敌方）对应一个
         * 队列长度 queueLength  = 30*/
{
    PositionQueue(String s)
    {
        System.out.println("Position Queue"+s+" is constructed");
    }
    int queueLength=30;// 坐标队列的长度
    float[] xQ= new float[queueLength];//横坐标队列
    float[] yQ= new float[queueLength];//纵坐标队列
    public void addToQ(float x, float y)//Put the current x,y positions into the two queue
    {
        for(int i=queueLength-1;i>=1;i--)
        {
            xQ[i]=xQ[i-1];
            yQ[i]=yQ[i-1];
        }
        xQ[0]=x;
        yQ[0]=y;


    }
    public float getXFromQ()//Get x position from the font of the queue
    {
        return xQ[queueLength-1];
    }
    public float getYFromQ()//Get y position from the font of the queue
    {
        return yQ[queueLength-1];
    }

    public void initializeQ(float x,float y)//将本坐标队列组初始化为对应人物的实际初始坐标
    {
        for(int i=0;i<=queueLength-1;i++)
        {
            xQ[i]=x;
            yQ[i]=y;
        }
    }


}
