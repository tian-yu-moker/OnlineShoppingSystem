package TowerDenfense;

/*
Some itmes on the map
 */

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;


public class MapItems implements Runnable
{
    public ConstTexture shownPic;
    public ConstTexture shownPic2;

    private int type;
    private int width;
    private int height;
    private int maxX;
    private int x;
    private int y;
    public int index = 0;

    public Sprite container = new Sprite();
    private Sprite container2 = new Sprite();
    private RenderWindow window;

    private Thread thread = new Thread(this);
    public boolean isEnd = false;

    public MapItems(int type, RenderWindow window)
    {
        this.type = type;
        this.window = window;
        setImages();
        thread.start();
    }

    public void setImages()
    {
        shownPic =TDPictures.mapItems.get(type - 1);
        container.setTexture(shownPic);
        if(type == 1)
        {
            width = 170;
            height = 80;
            maxX = 2890;
            x = 580;
            y = 750;
            container.setOrigin(85, 40);
            this.container.setTextureRect(new IntRect(width*index, 0, width, height));
        }
        if(type == 2)
        {
            width = 86;
            height = 219;
            maxX = 430;
            x = 460;
            y = 150;
            container.setOrigin(43, 109);
            this.container.setTextureRect(new IntRect(width * index, 0, width, height));
        }
        if(type == 3)
        {
            width = 82;
            height = 216;
            maxX = 430;
            x = 460;
            y = 500;
            container.setOrigin(41, 108);
            this.container.setTextureRect(new IntRect(width*index, 0, width, height));
        }
        if(type == 4)
        {
            shownPic2 = TDPictures.mapItems.get(type - 1);
            container2.setTexture(shownPic);
            width = 140;
            height = 211;
            maxX = 2380;
            x = 210;
            y = 470;
            container.setOrigin(70, 105);
            container.setScale((float) 1.5, (float) 1.5);
            container2.setOrigin(70, 105);
            container2.setScale((float) 1.5, (float) 1.5);
            this.container.setTextureRect(new IntRect(width*index, 0, width, height));
            this.container2.setTextureRect(new IntRect(width*index, 0, width, height));
        }
    }

    public void onDraw()
    {
        this.container.setTextureRect(new IntRect(width*index, 0, width, height));
        this.container.setPosition(x, y);
        if(type == 4)
        {
            this.container2.setTextureRect(new IntRect(width*index, 0, width, height));
            this.container2.setPosition(x, y - 450);
            window.draw(this.container2);
        }
        window.draw(this.container);
    }

    @Override
    public void run()
    {
        while(!isEnd)
        {
            index++;
            if(index == maxX / width)
                index = 0;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setEnd()
    {
        isEnd = true;
    }
}
