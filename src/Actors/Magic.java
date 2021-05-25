package Actors;

import GameDriver.staticVariable;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

public class Magic implements Runnable
{
    //�ڴ�ͼƬ��ͼƬ��λ��
    public int indexX;
    public int indexY;
    //��֡�ĳ��Ϳ�
    public int width;
    public int height;
    public String direction;
    public ConstTexture shownPic;
    public Sprite container = new Sprite();
    //����ͼƬ�ĳ���
    public int maxX;
    public int maxY;
    //�����Ƿ��ͷŽ���
    public boolean isFinish;
    //��¼��ʼ��������Ӣ��
    public String type;
    //��������
    public int skillNumber;
    private Thread thread;
    //Whether exit the thread
    private boolean isEnd = false;

    public Magic(int width,int height)
    {
        //����֡�Ĵ�С
        this.width =width;
        this.height =height;
        this.isFinish= false;
        //����
        this.direction="";
        //��ʼ���������ң�ready
        this.shownPic = staticVariable.effectSkill1.get(0);
        this.maxX = shownPic.getSize().x;
        this.maxY = shownPic.getSize().y;
        //ͼƬ����
        this.indexX = -1;
        this.indexY = 0;
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        //�������������ĵ㣬lancer��knight�����ֵӦ���ǣ�250��300�����ң���������ǿ�
        this.container.setOrigin(180, 350);
        thread = new Thread(this);
        thread.start();
    }

    public void setImages(){
        if(this.skillNumber==1) {
            if(this.direction.equals("left"))
            {this.shownPic = staticVariable.effectSkill1.get(0);}
            else if(this.direction.equals("right"))
            {this.shownPic = staticVariable.effectSkill1.get(1);}
        }
        if(this.skillNumber==2)
        {
            if(this.direction.equals("left"))
            {this.shownPic = staticVariable.effectSkill2.get(0);}
            else if(this.direction.equals("right"))
            {this.shownPic = staticVariable.effectSkill2.get(1);}
        }
        if(this.skillNumber==3)
        {
            if(this.direction.equals("left"))
            {this.shownPic = staticVariable.effectSkill3.get(0);}
            else if(this.direction.equals("right"))
            {this.shownPic = staticVariable.effectSkill3.get(1);}
        }
        if(this.skillNumber==4)
        {
            if(this.direction.equals("left"))
            {this.shownPic = staticVariable.effectSkill4.get(0);}
            else if(this.direction.equals("right"))
            {this.shownPic = staticVariable.effectSkill4.get(1);}
        }
        this.maxX = shownPic.getSize().x;
        this.maxY = shownPic.getSize().y;
    }

    public void changeIndex(){
        if(direction.equals("left"))
        {
            indexX++;
            if(indexX == this.maxX / this.width)
            {
                this.isFinish = true;
                indexX = -1;
            }
            this.container.setTexture(this.shownPic);
            this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        }
        else if(direction.equals("right"))
        {
            indexX++;
            if(indexX == this.maxX /this.width)
            {
                this.isFinish = true;
                indexX = -1;
            }
            this.container.setTexture(this.shownPic);
            this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        }

    }

    public void controller()
    {
        setImages();
        changeIndex();
    }

    public void setEnd()
    {
        isEnd = true;
    }

    @Override
    public void run()
    {
        while(!isEnd)
        {
            this.controller();
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
            }
        }
    }
}
