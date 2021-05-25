package TowerDenfense;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Monsters implements Runnable
{
    private Thread thread;
    //Position of the monsters
    public int x = 1530;
    public int y;
    public int i;
    public int j;
    //Size of the images.
    private int width;
    private int height;
    private int maxX;
    //Index of pic
    public int index = 0;
    public int index1 = 0;
    public int index2 = 0;
    public int index3 = 0;

    //State of the enemies.
    public boolean isDead = false;
    public boolean isDeadFinish = false;
    public boolean isAttack = false;
    public boolean isMove = true;
    public boolean isEnter = false;
    public boolean attackingTowers = false;


    //Only attack once.
    public int attackOnce = 0;
    //Basic attributions of the monster
    public int type;
    public int hp;
    public int attack;
    public int speed;
    //Drawable window, pics & containers
    private RenderWindow window;
    private ConstTexture attackPic;
    private ConstTexture movePic;
    private ConstTexture deadPic;
    private Sprite container = new Sprite();

    public boolean isEnd = false;

    public int test = 0;

    public Monsters(int type, int i, RenderWindow window)
    {
        this.type = type;
        this.i = i;
        this.window = window;
        setY();
        setImages();
        thread  = new Thread(this);
        thread.start();
    }

    //Set which line is the monster
    public void setY()
    {
        this.y = 150 + i * 120;
    }

    public void setImages()
    {
        width = 300;
        height = 300;
        this.movePic = TDPictures.monsterMove.get(type - 1);
        this.attackPic = TDPictures.monsterAttack.get(type - 1);
        this.deadPic = TDPictures.monsterDead.get(type - 1);

        this.container.setTexture(movePic);
        this.container.setOrigin(150, 150);
        this.maxX = movePic.getSize().x;
        this.container.setTextureRect(new IntRect(width*index, 0, width, height));
        if(type == 1)
        {
            this.hp = 20;
            this.attack = 1;
            this.speed = 5;
        }
        else if(type == 2)
        {
            this.hp = 13;
            this.attack = 2;
            this.speed = 8;
        }
        else if(type == 3)
        {
            this.hp = 20;
            this.attack = 2;
            this.speed = 7;
        }
        else if(type == 4)
        {
            this.hp = 25;
            this.attack = 3;
            this.speed = 7;
        }
        else if(type == 5)
        {
            this.hp = 70;
            this.attack = 1;
            this.speed = 5;
        }
        else if(type == 6)
        {
            this.hp = 30;
            this.attack = 4;
            this.speed = 10;
        }
        else if(type == 7)
        {
            this.hp = 25;
            this.attack = 3;
            this.speed = 15;
        }
        else if(type == 8)
        {
            this.hp = 30;
            this.attack = 3;
            this.speed = 10;
        }
        else if(type == 9)
        {
            this.hp = 40;
            this.attack = 4;
            this.speed = 12;
        }
        else if(type == 10)
        {
            this.hp = 50;
            this.attack = 8;
            this.speed = 10;
        }
    }

    public void onDraw()
    {
        this.container.setPosition(x, y);
        this.container.setTextureRect(new IntRect(width*index, 0, width, height));
        this.window.draw(container);
    }

    //Main controller of the monster
    public void controller()
    {
        if(isAttack)
        {
            this.maxX = this.attackPic.getSize().x;
            this.container.setTexture(this.attackPic);
            index1++;
            if(index1 == maxX / width)
                index1 = 0;
            index = index1;
        }
        else if(isDead)
        {
            this.maxX = this.deadPic.getSize().x;
            this.container.setTexture(this.deadPic);
            index3 = 0;
            index1 = 0;
            index2++;
            if(index2 == maxX / width)
                isDeadFinish = true;
            index = index2;
        }
        //Moving
        else if(isMove)
        {
            this.maxX = this.movePic.getSize().x;
            this.container.setTexture(this.movePic);
            index1 = 0;
            index2 = 0;
            index3++;
            if(index3 == maxX / width)
                index3 = 0;
            index = index3;
        }
        move();
        dead();
    }

    //Control the move of the monster
    public void move()
    {
        if(!isAttack && !isDead && !isEnter)
            x -= speed;
        if(x < 500)
            isEnter = true;
    }

    //The death of enemies
    public void dead()
    {
        if(this.hp <= 0)
        {
            isDead = true;
            isAttack = false;
            isMove = false;
        }
    }


    @Override
    public void run()
    {
        while(!isDeadFinish && !isEnter && !isEnd)
        {
            controller();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
