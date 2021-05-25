package Actors;

/*
This class aims to draw the shadow under the player.
 */

import GameDriver.staticVariable;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class PlayerShadow implements Runnable
{

    private Sprite container;
    private Thread thread;
    private int indexX;
    private int indexY;
    private int width;
    private int height;
    private int maxX;
    private RenderWindow window;
    private String type;
    private String direction = "right";
    public boolean isEnd = false;

    public PlayerShadow(String type, RenderWindow window)
    {
        this.container = new Sprite();
        this.maxX = staticVariable.heroShadow.getSize().x;
        this.window = window;
        this.type = type;
        indexX = 0;
        indexY = 0;
        decideSize();
        this.container.setTexture(staticVariable.heroShadow);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        thread = new Thread(this);
        thread.start();
    }

    public void decideSize()
    {
        if(this.type.equals("Lancer"))
        {
            this.width = 250;
            this.height = 150;
        }
        else if(this.type.equals("Saber"))
        {
            this.width = 200;
            this.height = 200;
        }
        else if(this.type.equals("Knight"))
        {
            this.width = 250;
            this.height = 150;
        }
    }

    public void onDraw(String direction, int x, int y)
    {
        this.direction = direction;
        if(this.direction.equals("right"))
        {
            if(this.type.equals("Lancer"))
                this.container.setPosition(x - 130,  y - 10);
            else if(this.type.equals("Saber"))
                this.container.setPosition(x - 100, y - 50);
            else if(this.type.equals("Knight"))
                this.container.setPosition(x- 170, y - 20);
        }
        else if(this.direction.equals("left"))
        {
            if(this.type.equals("Lancer"))
                this.container.setPosition(x - 120, y - 10);
            else if(this.type.equals("Saber"))
                this.container.setPosition(x - 100, y - 50);
            else if(this.type.equals("Knight"))
                this.container.setPosition(x - 144, y - 20);
        }
        this.window.draw(this.container);
    }

    public void changeIndex()
    {
        indexX++;
        if(indexX == this.maxX / this.width)
            indexX = 0;
        this.container.setTextureRect(new IntRect(width * indexX, height * indexY, width, height));
    }

    @Override
    public void run()
    {
        while(!isEnd)
        {
            changeIndex();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
