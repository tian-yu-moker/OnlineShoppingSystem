package Shop;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoadPics
{
    public static ConstTexture shopAnimation;
    public static List<ConstTexture> buy = new ArrayList<ConstTexture>();
    public static List<ConstTexture> addButton = new ArrayList<ConstTexture>();
    public static List<ConstTexture> reduceButton = new ArrayList<ConstTexture>();
    public static ConstTexture money;


    public static void loadShop()
    {
        Texture imgs = new Texture();
        try {
            imgs = new Texture();
            imgs.loadFromFile(Paths.get("Images/shopSubIF/shopAnima.png"));
            imgs.setSmooth(true);
            shopAnimation = imgs;

            for(int i = 1; i <= 3; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get("Images/shopSubIF/buy" + i + ".png"));
                imgs.setSmooth(true);
                buy.add(imgs);
            }

            for(int i = 1; i <= 3; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get("Images/shopSubIF/add" + i + ".png"));
                imgs.setSmooth(true);
                addButton.add(imgs);
            }

            for(int i = 1; i <= 3; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get("Images/shopSubIF/reduce" + i + ".png"));
                imgs.setSmooth(true);
                reduceButton.add(imgs);
            }

            imgs = new Texture();
            imgs.loadFromFile(Paths.get("Images/shopSubIF/money.png"));
            imgs.setSmooth(true);
            money = imgs;




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
