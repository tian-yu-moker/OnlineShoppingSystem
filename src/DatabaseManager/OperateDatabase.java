package DatabaseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class OperateDatabase
{
    //Get connection with the database
    public static String user;

    private static Semaphore mutex = new Semaphore(1);


    //Update the current money, add or reduce money
    public static void updateMoney(int money)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int total = getMoney() + money;
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        myDbUser.updateMoney(total);
        myDbUser.close();
        mutex.release();
    }

    public static int getMoney()
    {
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        int money = myDbUser.getMoney();
        myDbUser.close();
        return money;
    }

    //Write into database
    //LJY use, Operation: "Use"
    public static void updateItems(int type, int num, String operation)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        myDbUser.updatePack(type, num, operation);
        myDbUser.close();
        mutex.release();
    }

    //Get the info, return pack items info
    public static String getPackInfo()
    {
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        String packInfo = myDbUser.getPackInfo();
        myDbUser.close();
        return packInfo;
    }

    public static ArrayList getEquipsInfo()
    {
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        ArrayList equipInfo = myDbUser.getEquipInfo();
        myDbUser.close();
        return equipInfo;
    }

    public static void updateEquip(int type, int number, int blank, String operation)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        myDbUser.updateEquip(type, number, blank, operation);
        myDbUser.close();
        mutex.release();
    }

  //get the heroinfo
    public static ArrayList getHeroInfo()
    {
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        ArrayList HeroInfo = myDbUser.getHeroInfo();
        myDbUser.close();
        return HeroInfo;
    }
    public static void updateHero(int Level, float Blood, float Attack, float MP, float Speed, int Exp )
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        myDbUser.updateHero(Level,Blood,Attack,MP,Speed,Exp);
        myDbUser.close();
        mutex.release();
    }


    public static void setUserInfor1(String UserId, String Password)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db");//, user
        myDbUser.setUserInfor1(UserId, Password);
        myDbUser.close();
        mutex.release();
    }
    public static void setUserInfor2(String HeroType, String u)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db");//, user
        myDbUser.setUserInfor2(HeroType,u);
        myDbUser.close();
        mutex.release();
    }

    public static int judgeUserId(String id)
    {//String n
        DbUser myDbUser = new DbUser("Database/HeroInfo.db",user);//Database/
        int u = myDbUser.judgeUserId(id);
        myDbUser.close();
        return u;
    }

    public static String getPassword(String nabd)
    {
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);//Database/
        String password = myDbUser.getPassword(nabd);
        myDbUser.close();
        return password;
    }

    public static int judgeHeroType(String id)
    {//String n
        DbUser myDbUser = new DbUser("Database/HeroInfo.db",user);//Database/
        int u = myDbUser.judgeHeroType(id);
        myDbUser.close();
        return u;
    }

    public static String getHeroType()
    {
        String heroType = "";
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        heroType = myDbUser.getHeroType(user);
        myDbUser.close();
        return heroType;
    }

    public static void addMark(String reUserName)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db");//, user
        myDbUser.addMark(reUserName);
        myDbUser.close();
        mutex.release();
    }

    public static void updateMark(int level, int star)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);//, user
        myDbUser.updateMark(level, star);
        myDbUser.close();
        mutex.release();
    }

    public static void calculateTotalStar()
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);//, user
        myDbUser.calculateTotalStar();
        myDbUser.close();
        mutex.release();
    }

    public static int getLevelRecord()
    {
        int level = 1;
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        level = myDbUser.getLevelRecord(user);
        myDbUser.close();
        return level;
    }

    public static void updateLevelRecord(int level)
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        myDbUser.updateLevelRecord(user, level);
        myDbUser.close();
        mutex.release();
    }

    public static List getEndInfo()
    {
        List info = new ArrayList();
        DbUser myDbUser = new DbUser("Database/HeroInfo.db");
        info = myDbUser.getEndInfo();
        myDbUser.close();
        return info;
    }

    public static String getOrder()
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        String str= myDbUser. getOrder();
        myDbUser.close();
        mutex.release();
        return str;
    }

    public static int getEndSum()
    {
        int sum = 0;
        DbUser myDbUser = new DbUser("Database/HeroInfo.db", user);
        sum = myDbUser.getSum(user);
        myDbUser.close();
        return sum;
    }

    public static void main(String args[])
    {
        //updateMoney(100);
        updateItems(2, 3,"Add");
        updateItems(1, 2,"Add");
        updateItems(6, 1,"Add");
        updateItems(8, 5,"Add");
        updateItems(3, 3,"Add");
        updateEquip(1,1, 1, "Add");
        updateEquip(2,2, 2, "Add");
        updateEquip(3,3, 3, "Add");
       // updateLevelRecord(6);
       //System.out.println(getHeroType());
    }
}
