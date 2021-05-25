package TowerDenfense;

/*
This bullets of the towers
 */

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Bullets implements Runnable
{
    //Shown pictures & container
    private ConstTexture bulletMove;
    private ConstTexture bulletReach;
    private Sprite container = new Sprite();
    //Position & type of the bullets
    public int x;
    private int y;
    public int type;
    public int i;
    public int j;
    //Information of the picture
    private int widthMove;
    private int maxMove;
    private int widthReach;
    private int maxReach;
    private int heightMove;
    private int heightReach;
    private int width;
    private int height;
    private int max;
    //Index of pictures
    public int index;
    private int indexMove;
    private int indexReach;

    public int attackOnce = 0;
    public boolean isMove = true;
    public boolean isReached = false;
    public boolean isReachFinished = false;
    private int speed;
    public int attackVolume;
    private RenderWindow window;
    private Thread thread;

    public boolean isEnd = false;

    public Bullets(int type, int i, int j, RenderWindow window)
    {
        this.type = type;
        this.i = i;
        this.j = j;
        this.window = window;
        setImage();
        setPosition();
        thread = new Thread(this);
        thread.start();
    }

    public void setImage()
    {
        if(type == 5)
        {
            this.bulletMove = TDPictures.bulletsMove.get(type - 2);
            this.bulletReach = TDPictures.bulletsReach.get(type - 2);
        }
        else
        {
            this.bulletMove = TDPictures.bulletsMove.get(type - 1);
            this.bulletReach = TDPictures.bulletsReach.get(type - 1);
        }
        if(type == 1)
        {
            this.widthMove = 54;
            this.heightMove = 54;
            this.maxMove = 432;
            this.widthReach = 124;
            this.heightReach = 130;
            this.maxReach = 868;
            this.speed = 10;
            this.attackVolume = 1;
        }
        else if(type == 2)
        {
            this.widthMove = 115;
            this.heightMove = 64;
            this.maxMove = 545;
            this.widthReach = 103;
            this.heightReach = 95;
            this.maxReach = 868;
            this.speed = 12;
            this.attackVolume = 3;
        }
        else if(type == 3)
        {
            this.widthMove = 128;
            this.heightMove = 42;
            this.maxMove = 640;
            this.widthReach = 200;
            this.heightReach = 194;
            this.maxReach = 2400;
            this.speed = 15;
            this.attackVolume = 5;
        }
        else if(type == 5)
        {
            this.widthMove = 118;
            this.heightMove = 60;
            this.maxMove = 590;
            this.widthReach = 261;
            this.heightReach = 238;
            this.maxReach = 2160;
            this.speed = 15;
            this.attackVolume = 8;
        }
        this.container.setTexture(this.bulletMove);
        this.width = this.widthMove;
        this.height = this.heightMove;
        this.max = this.maxMove;
        //isReached = true;
        this.index = 0;
        this.indexMove = 0;
        this.indexReach = 0;
        this.container.setTextureRect(new IntRect(width*index, 0, width, height));
    }

    public void setPosition()
    {
        if(type == 1)
        {
            x = 550 + j * 120;
            y = 120 + i * 120;
        }
        else if(type == 2)
        {
            x = 550 + j * 120;
            y = 130 + i * 120;
        }
        else if(type == 3)
        {
            x = 550 + j * 120;
            y = 135 + i * 120;
        }
        else if(type == 5)
        {
            x = 550 + j * 120;
            y = 130 + i * 120;
        }
    }

    public void controller()
    {
        move();
        changeIndex();
    }

    public void move()
    {
        if(isMove && !isReached)
            this.x += this.speed;
    }

    public void changeIndex()
    {
        if(isMove && !isReached)
        {
            indexReach = 0;
            indexMove++;
            if(indexMove == max / width)
                indexMove = 0;
            index = indexMove;
        }
        else if(isReached && !isMove)
        {
            indexReach++;
            if(indexReach == max / width)
            {
                indexReach = 0;
                isReachFinished = true;
            }
            index = indexReach;
        }
    }

    //Draw the images
    public void onDraw()
    {
        if(isReached)
        {
            this.container.setTexture(this.bulletReach);
            this.width = this.widthReach;
            this.height = this.heightReach;
            this.max = this.maxReach;
        }
        this.container.setTextureRect(new IntRect(width*index, 0, width, height));
        if(type == 1 && isReached)
            y = 100 + i * 120;
        else if(type == 2 && isReached)
            y = 110 + i * 120;
        else if(type == 3 && isReached)
            y = 60 + i * 120;
        else if(type == 5 && isReached)
            y = 45 + i * 120;

        this.container.setPosition(x, y);
        this.window.draw(this.container);
    }

    @Override
    public void run()
    {
        while(!isReachFinished && !isEnd)
        {
            controller();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
