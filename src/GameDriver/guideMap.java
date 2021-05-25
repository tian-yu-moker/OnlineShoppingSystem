package GameDriver;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

import java.io.IOException;
import java.nio.file.Paths;

/*
This map is used for guide map
Locating the position of player in the whole map.
 */

public class guideMap
{
    private RectangleShape mark = new RectangleShape();
    private RenderWindow window;
    public double x = 0.0;
    public double y = 0.0;
    private Sprite map = new Sprite();
    private Sprite pointer = new Sprite();
    private int width;
    private int height;
    private int level;

    public guideMap(RenderWindow window, int level)
    {
        this.window = window;
        this.level = level;
        determineSize(level);
        setImages(level);

        mark.setSize(new Vector2f(7,7));
        mark.setFillColor(new Color(220,20,60));
        Texture imgTexture1 = new Texture();

        try {
            imgTexture1.loadFromFile(Paths.get("myImage/BattleUI/Pointer.png"));//从本地加载图片
        } catch (IOException e) {
            e.printStackTrace(); }
        imgTexture1.setSmooth(true);
        pointer.setTexture(imgTexture1);

        pointer.setOrigin(69, 70);
        pointer.scale(new Vector2f((float) 0.2, (float) 0.2));
        pointer.setOrigin(69, 140);
    }

    public void determineSize(int level)
    {
        switch(level)
        {
            case 1:
                this.width = 7168;
                this.height = 5120;
                break;
            case 3:
                this.width = 5228;
                this.height = 3583;
                break;
            case 4:
                this.width = 5000;
                this.height = 5000;
                break;
            case 5:
                this.width = 5375;
                this.height = 5634;
                break;
            case 7:
                this.width = 9000;
                this.height = 9000;
                break;
            case 8:
                this.width = 6600;
                this.height = 6400;
                break;
            case 9:
                this.width = 5400;
                this.height = 4500;
                break;
            case 10:
                this.width = 5632;
                this.height = 5120;
                break;
            default:
                break;
        }
    }

    public void setImages(int level)
    {
        if(level == 1)
            map.setTexture(staticVariable.guideMaps.get(0));
        else if(level == 3)
            map.setTexture(staticVariable.guideMaps.get(1));
        else if(level == 4)
            map.setTexture(staticVariable.guideMaps.get(2));
        else if(level == 5)
            map.setTexture(staticVariable.guideMaps.get(3));
        else if(level == 7)
            map.setTexture(staticVariable.guideMaps.get(4));
        else if(level == 8)
            map.setTexture(staticVariable.guideMaps.get(5));
        else if(level == 9)
            map.setTexture(staticVariable.guideMaps.get(6));
        else if(level == 10)
            map.setTexture(staticVariable.guideMaps.get(7));
    }

    public void onDraw()
    {
        /*RectangleShape rectangular = new RectangleShape();
        rectangular.setSize(new Vector2f(7,7));
        rectangular.setFillColor(new Color(220,20,60));
        rectangular.setPosition(1230, 60);*/
        map.setPosition((float) 1200, (float) 37);
        window.draw(map);
        pointer.setPosition(new Vector2f((float) this.x, (float) this.y));
        window.draw(pointer);
        //window.draw(rectangular);
    }

    public void setCoor(int x, int y)
    {
        this.x = (double) x / (this.width / 194) + 1230.0;
        this.y = (double) y / (this.height / 146)+ 60.0;
    }

    public void setWindow(RenderWindow window)
    {
        this.window = window;
    }
}
