package GameDriver;

public class PositionQueue
        /*2020.2.29 ������
         * ÿ���������һ����������飬���ڻ�����ɺ���֮ǰĳ����Ϸ��������з����ĺ�����������
         * ÿ����Ϸ��������з�����Ӧһ��
         * ���г��� queueLength  = 30*/
{
    PositionQueue(String s)
    {
        System.out.println("Position Queue"+s+" is constructed");
    }
    int queueLength=30;// ������еĳ���
    float[] xQ= new float[queueLength];//���������
    float[] yQ= new float[queueLength];//���������
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

    public void initializeQ(float x,float y)//��������������ʼ��Ϊ��Ӧ�����ʵ�ʳ�ʼ����
    {
        for(int i=0;i<=queueLength-1;i++)
        {
            xQ[i]=x;
            yQ[i]=y;
        }
    }


}
