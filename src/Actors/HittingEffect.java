package Actors;

import GameDriver.staticVariable;
import TowerDenfense.TDPictures;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

public class HittingEffect implements Runnable{
    //在大图片中图片的位置
    public int indexX;
    public int indexY;
    //单帧的长和宽
    public int width;
    public int height;
    public ConstTexture shownPic;
    public Sprite container = new Sprite();
    //整张图片的长宽
    public int maxX;
    public int maxY;
    //技能是否释放结束
    public boolean isFinish;
    //记录初始化了哪种英雄
    public int type;
    //技能种类
    public int skillNumber;
    private Thread thread;
    //Whether exit the thread
    private boolean isEnd = false;

    public HittingEffect(int width,int height)
    {
        isFinish=false;
        this.width =width;
        this.height =height;
        this.shownPic = TDPictures.bulletsReach.get(3);
        this.maxX = shownPic.getSize().x;
        this.maxY = shownPic.getSize().y;
        this.indexX = -1;
        this.indexY = 0;
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        //设置容器的中心点，lancer和knight这个的值应该是（250，300）左右，具体的你们看
        this.container.setOrigin(108, 140);
        thread = new Thread(this);
        thread.start();
    }

    public void controller()
    {
        //setImages();
        changeIndex();
    }

    private void changeIndex() {
        indexX++;
        if(indexX == this.maxX / this.width)
        {
            this.isFinish = true;
            indexX = -1;
        }
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
    }

    private void setImages() {

    }

    @Override
    public void run() {
        while(!isEnd)
        {
            this.controller();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
