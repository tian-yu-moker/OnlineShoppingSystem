package Actors;

import GameDriver.staticVariable;
import TowerDenfense.TDPictures;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

public class HittingEffect implements Runnable{
    //�ڴ�ͼƬ��ͼƬ��λ��
    public int indexX;
    public int indexY;
    //��֡�ĳ��Ϳ�
    public int width;
    public int height;
    public ConstTexture shownPic;
    public Sprite container = new Sprite();
    //����ͼƬ�ĳ���
    public int maxX;
    public int maxY;
    //�����Ƿ��ͷŽ���
    public boolean isFinish;
    //��¼��ʼ��������Ӣ��
    public int type;
    //��������
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
        //�������������ĵ㣬lancer��knight�����ֵӦ���ǣ�250��300�����ң���������ǿ�
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
