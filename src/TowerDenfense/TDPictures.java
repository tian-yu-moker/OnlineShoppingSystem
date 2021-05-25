package TowerDenfense;

/*
Load the images of the tower defense
 */


import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Texture;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TDPictures
{
    public static List<ConstTexture> towerAttack = new ArrayList<ConstTexture>();
    public static List<ConstTexture> towerIdle = new ArrayList<ConstTexture>();
    public static List<ConstTexture> map = new ArrayList<ConstTexture>();
    public static List<ConstTexture> buttons = new ArrayList<ConstTexture>();
    public static List<ConstTexture> buttonsLight = new ArrayList<ConstTexture>();
    public static ConstTexture panel;
    public static List<ConstTexture> shownButtons = new ArrayList<ConstTexture>();
    public static Font font = new Font();
    public static Font money = new Font();
    //Life Volume & Golds
    public static List<ConstTexture> golds = new ArrayList<ConstTexture>();
    public static ConstTexture moreGold;
    public static List<ConstTexture> life = new ArrayList<ConstTexture>();

    public static List<ConstTexture> mapItems = new ArrayList<ConstTexture>();
    public static ConstTexture nextNum;

    public static List<ConstTexture> monsters = new ArrayList<ConstTexture>();
    public static List<ConstTexture> monsterMove = new ArrayList<ConstTexture>();
    public static List<ConstTexture> monsterAttack = new ArrayList<ConstTexture>();
    public static List<ConstTexture> monsterDead = new ArrayList<ConstTexture>();

    public static List<ConstTexture> bulletsMove = new ArrayList<ConstTexture>();
    public static List<ConstTexture> bulletsReach = new ArrayList<ConstTexture>();


    public static void loadImgs()
    {
        Texture imgs = new Texture();
        try {
            for(int i = 1; i <= 5; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Towers/a" + i + ".png"));
                imgs.setSmooth(true);
                towerAttack.add(imgs);
            }

            for(int i = 1; i <= 5; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Towers/i" + i + ".png"));
                imgs.setSmooth(true);
                towerIdle.add(imgs);
            }

            for(int i = 1; i <= 5; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/b" + i + ".png"));
                imgs.setSmooth(true);
                buttons.add(imgs);
            }

            for(int i = 1; i <= 5; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/shown" + i + ".png"));
                imgs.setSmooth(true);
                shownButtons.add(imgs);
            }

            imgs = new Texture();
            imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/panel.png"));
            imgs.setSmooth(true);
            panel = imgs;

            imgs = new Texture();
            imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/Next.png"));
            imgs.setSmooth(true);
            nextNum = imgs;

            for(int i = 1; i <= 2; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/golds" + i + ".png"));
                imgs.setSmooth(true);
                golds.add(imgs);
            }

            for(int i = 1; i <= 2; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/life" + i + ".png"));
                imgs.setSmooth(true);
                life.add(imgs);
            }

            imgs = new Texture();
            imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/MoreGolds.png"));
            imgs.setSmooth(true);
            moreGold = imgs ;

            font.loadFromFile(Paths.get("Images/TD_Images/Font/description.ttf"));
            money.loadFromFile(Paths.get("Images/TD_Images/Font/moneyFont.TTF"));

            for(int i = 1; i <= 5; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Buttons/b" + i + "_Light.png"));
                imgs.setSmooth(true);
                buttonsLight.add(imgs);
            }

            for(int i = 1; i <= 2; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Maps/map" + i + ".png"));
                imgs.setSmooth(true);
                map.add(imgs);
            }

            for(int i = 1; i <= 4; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/MapCom/c" + i + ".png"));
                imgs.setSmooth(true);
                mapItems.add(imgs);
            }

            for(int i = 1; i <= 3; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Enemies/m1_" + i + ".png"));
                imgs.setSmooth(true);
                monsters.add(imgs);
            }

            //Move
            for(int i = 1; i <= 10; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Enemies/m" + i  + "_1.png"));
                imgs.setSmooth(true);
                monsterMove.add(imgs);
            }

            //Attack
            for(int i = 1; i <= 10; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Enemies/m" + i  + "_2.png"));
                imgs.setSmooth(true);
                monsterAttack.add(imgs);
            }

            //Dead
            for(int i = 1; i <= 10; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Enemies/m" + i  + "_3.png"));
                imgs.setSmooth(true);
                monsterDead.add(imgs);
            }


            for(int i = 1; i <= 4; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Bullets/b" + i + "_move.png"));
                imgs.setSmooth(true);
                bulletsMove.add(imgs);
            }

            for(int i = 1; i <= 4; i++)
            {
                imgs = new Texture();
                imgs.loadFromFile(Paths.get( "Images/TD_Images/Bullets/b" + i + "_reach.png"));
                imgs.setSmooth(true);
                bulletsReach.add(imgs);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
