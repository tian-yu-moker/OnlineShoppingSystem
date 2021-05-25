package TowerDenfense;

public class AddMonsters
{
    public static int once = 0;
    public static int timer = 0;
    public static boolean isAdd = false;
    public static int waves;
    public static int fullWaves;
    public static int number;
    public static int curNum = 0;
    public static boolean addOnce = true;

    public static boolean addMons()
    {
        if(once == 0 && number < 20)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    once++;
                    isAdd = false;
                    while(true)
                    {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        timer++;
                        //System.out.println(timer);
                        //1.5~2.5 second add a monster
                        if(timer >= 3)
                        {
                            if(timer == 5)
                            {
                                timer = 0;
                                isAdd = true;
                                addOnce = false;
                                number++;
                                curNum++;
                                break;
                            }
                            int random = (int) (Math.random() * 3);
                            if(random <= 1)
                                continue;
                            else if(random > 1)
                            {
                                timer = 0;
                                isAdd = true;
                                number++;
                                curNum++;
                                break;
                            }
                        }
                    }
                }
            }).start();
        }

        return isAdd;
    }

    public static void reseting()
    {
        once = 0;
        timer = 0;
        isAdd = false;
        waves = 0;

        number = 0;
        curNum = 0;
        addOnce = true;
    }

}
