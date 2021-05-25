package TowerDenfense;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class Soliders implements Runnable
{
    private Thread thread = new Thread(this);

    private ConstTexture attackPic;
    private ConstTexture idlePic;
    private Sprite container = new Sprite();
    //The start position of bullets
    private int bulletX;
    private int bulletY;
    //If the tower if attacking
    public boolean isAttack = false;
    public boolean isDead = false;
    public int attackOnce = 0;
    public int blood_volume;
    public int attackVolume;
    public int type;
    //Decide whether which index can reduce the blood of monsters
    public int index = 0;
    private int indexIdle = 0;
    private int indexAttack = 0;
    private int maxIdle;
    private int maxAttack;
    //Basic information of the solider
    private RenderWindow window;
    public int x;
    public int y;
    public int i;
    public int j;
    private int sleepTime;

    public boolean isEnd = false;


    public Soliders(int type, int bulletX, int bulletY, int x, int y, int i, int j, RenderWindow window)
    {
        this.type = type;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
        this.window = window;
        setAttribute(this.type);
        thread.start();
    }

    public void setAttribute(int type)
    {
        if(type == 1)
        {
            blood_volume = 10;
            attackVolume = 2;
            maxAttack = 3300;
            maxIdle = 2400;
        }
        else if(type == 2)
        {
            blood_volume = 15;
            attackVolume = 2;
            maxAttack = 3600;
            maxIdle = 2100;
        }
        else if(type == 3)
        {
            blood_volume = 16;
            attackVolume = 2;
            maxAttack = 2400;
            maxIdle = 2400;
        }
        else if(type == 4)
        {
            blood_volume = 40;
            attackVolume = 1;
            maxAttack = 1800;
            maxIdle = 1800;
        }
        else if(type == 5)
        {
            blood_volume = 30;
            attackVolume = 8;
            maxAttack = 5400;
            maxIdle = 1800;
        }


        this.attackPic = TDPictures.towerAttack.get(type - 1);
        this.idlePic = TDPictures.towerIdle.get(type - 1);
        this.container.setTexture(this.idlePic);
        this.container.setTextureRect(new IntRect(300*index, 0, 300, 300));
        this.container.setOrigin(150, 150);
        if(type == 3)
            this.container.setScale((float) 1.5, (float) 1.5);
        else
            this.container.setScale((float) 1.15, (float) 1.15);
    }

    //Change the index of the character
    public void changeIndex()
    {
        if(isAttack)
            this.container.setTexture(this.attackPic);
        else if(!isAttack)
            this.container.setTexture(this.idlePic);
        if(!isDead)
        {
            if(isAttack)
            {
                indexAttack++;
                indexIdle = 0;
                if(indexAttack == maxAttack / 300)
                    indexAttack = 0;
                index = indexAttack;
            }
            else if(!isAttack)
            {
                indexIdle++;
                indexAttack = 0;
                if(indexIdle == maxIdle / 300)
                    indexIdle = 0;
                index = indexIdle;
            }
        }
        this.container.setTextureRect(new IntRect(300*index, 0, 300, 300));
    }

    public void controller()
    {
        changeIndex();
        dead();
    }

    public void dead()
    {
        if(this.blood_volume <= 0)
            isDead = true;
    }

    public void onDraw()
    {
        this.container.setPosition(x, y);
        this.window.draw(container);
    }

    @Override
    public void run()
    {
        while(!isDead && !isEnd)
        {
            controller();
            if(type == 1)
            {
                if(isAttack)
                    sleepTime = 240;
                else
                    sleepTime = 200;
            }
            else if(type == 2)
            {
                if(isAttack)
                    sleepTime = 230;
                else
                    sleepTime = 200;
            }
            else if(type == 3)
            {
                if(isAttack)
                    sleepTime = 220;
                else
                    sleepTime = 200;
            }
            else if(type == 4)
            {
                if(isAttack)
                    sleepTime = 330;
                else
                    sleepTime = 200;
            }
            else if(type == 5)
            {
                if(isAttack)
                    sleepTime = 220;
                else
                    sleepTime = 200;
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
