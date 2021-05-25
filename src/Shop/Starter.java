package Shop;

import GUI.TestFrame;
import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import TowerDenfense.TDPictures;

public class Starter
{
    public static void main(String args[])
    {
        TDPictures.loadImgs();
        TDMusicPlay.loading();
        //LoadPics.loadShop();
        //staticVariable.loadBackground();
        //staticVariable.loadEnemy();
        staticVariable.loadNPCs();
        //staticVariable.loadBoss();
       // new TestFrame();
        new TestMain();
        //System.out.println(OperateDatabase.getMoney());
    }
}
