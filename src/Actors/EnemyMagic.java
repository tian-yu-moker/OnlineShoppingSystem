package Actors;

import GameDriver.staticVariable;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.Sprite;

public class EnemyMagic {
    public int x;
    public int y;
    private int width;
    private int height;
    private ConstTexture showImage;
    private int type;
    public int distanceX;
    public int distanceY;
    //在大图片中图片的位置
    public int indexX;
    public int indexY;
    public Sprite container = new Sprite();
    public EnemyMagic(int x,int y,int type) {
        this.x=x;
        this.y=y;
        this.type=type;
        distanceX=0;
        distanceY=0;
        if(type==1) {
            width=115;
            height=64;
            this.showImage= staticVariable.allSkeletonMagicImage.get(0);
            this.container.setTexture(this.showImage);
            this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
            this.container.setOrigin(84, 33);
        }
    }
}
