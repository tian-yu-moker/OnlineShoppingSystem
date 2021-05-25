package Actors;

import GameDriver.staticVariable;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

public class Magic implements Runnable
{
    //在大图片中图片的位置
    public int indexX;
    public int indexY;
    //单帧的长和宽
    public int width;
    public int height;
    public String direction;
    public ConstTexture shownPic;
    public Sprite container = new Sprite();
    //整张图片的长宽
    public int maxX;
    public int maxY;
    //技能是否释放结束
    public boolean isFinish;
    //记录初始化了哪种英雄
    public String type;
    //技能种类
    public int skillNumber;
    private Thread thread;
    //Whether exit the thread
    private boolean isEnd = false;

    public Magic(int width,int height)
    {
        //单个帧的大小
        this.width =width;
        this.height =height;
        this.isFinish= false;
        //方向
        this.direction="";
        //初始动作：向右，ready
        this.shownPic = staticVariable.effectSkill1.get(0);
        this.maxX = shownPic.getSize().x;
        this.maxY = shownPic.getSize().y;
        //图片索引
        this.indexX = -1;
        this.indexY = 0;
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        //设置容器的中心点，lancer和knight这个的值应该是（250，300）左右，具体的你们看
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
