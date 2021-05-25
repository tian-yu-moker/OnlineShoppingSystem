package GUI;

/*
This class aims to control the animation of the skills of heros
Including the CD recording of the skills
 */

import GameDriver.staticVariable;
import org.jsfml.graphics.*;

import java.util.Calendar;
import java.util.Date;

public class HeroSkillCards
{
    private ConstTexture[] skills = new ConstTexture[4];
    private Sprite[] containers = new Sprite[4];
    private ConstTexture cdPic;
    private Sprite[] cdSquare = new Sprite[4];

    private Font font;
    private Text[] cdTime = new Text[4];

    private RenderWindow window;
    private int offset = 65;
    private int[] timer = new int[4];

    public boolean[] isUsed = new boolean[4];
    private boolean[] isFinished = new boolean[4];

    public static int time = 10 * 1000;

    public HeroSkillCards(RenderWindow window)
    {
        this.window = window;
        timer[0] = 5 * 1000;
        timer[1] = 5 * 1000;
        timer[2] = 7 * 1000;
        timer[3] = 9 * 1000;
        this.cdPic = staticVariable.cd;
        this.font = staticVariable.dialogFont;
        setImages();
    }

    public void setImages()
    {
        for(int i = 0; i < 4; i++)
        {
            this.skills[i] = staticVariable.skillCards.get(i);
            this.containers[i] = new Sprite();
            this.containers[i].setTexture(this.skills[i]);
            this.containers[i].setOrigin(40, 40);
            this.containers[i].setScale((float) 0.6, (float) 0.55);

            this.cdSquare[i] = new Sprite();
            this.cdSquare[i].setTexture(this.cdPic);
            this.cdSquare[i].setOrigin(40, 40);
            this.cdSquare[i].setScale((float) 0.6, (float) 0.55);

            this.cdTime[i] = new Text("", font, 15);

            isUsed[i] = false;
        }
    }

    public void onDraw()
    {
        for(int i = 0; i < 4; i++)
        {
            if(i == 3)
            {
                this.containers[i].setPosition(388 + i * offset,725);
                this.cdSquare[i].setPosition(388 + i * offset, 725);
                this.cdTime[i].setPosition(375 + i * offset, 715);
                this.window.draw(this.containers[i]);
                if(isUsed[i])
                {
                    window.draw(cdSquare[i]);
                    window.draw(cdTime[i]);
                }
            }
            else
            {
                this.containers[i].setPosition(387 + i * offset,725);
                this.cdSquare[i].setPosition(387 + i * offset, 725);
                this.cdTime[i].setPosition(375 + i * offset, 715);
                this.window.draw(this.containers[i]);
                if(isUsed[i])
                {
                    window.draw(cdSquare[i]);
                    window.draw(cdTime[i]);
                }
            }
        }
    }

    public void startTimer(int number)
    {
        isUsed[number - 1] = true;
        if(number - 1 == 0)
            this.timer[0] = 5 * 1000;
        else if(number - 1 == 1)
            this.timer[1] = 5 * 1000;
        else if(number - 1 == 2)
            this.timer[2] = 7 * 1000;
        else if(number - 1 == 3)
            this.timer[3] = 9 * 1000;
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(timer[number - 1] > 0)
                {
                    timer[number - 1] -= 100;
                    try {
                        Thread.sleep(100);
                        double seconds = timer[number - 1] / 1000;
                        double hunMillSec = (double) timer[number - 1] % 1000 / 1000;
                        //System.out.println(hunMillSec);
                        double time = seconds + hunMillSec;
                       // System.out.println(time);
                        String text = String.valueOf(time);
                        cdTime[number - 1].setString(text);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isUsed[number - 1] = false;
            }
        }).start();
        this.isFinished[number - 1] = true;
    }

    public static void main(String args[])
    {
        time1();
    }

    private static void time1()
    {
        while (time > 0)
        {
            time -= 100;
            try {
                Thread.sleep(100);
                //int min = time / 60 % 60;
                double seconds = time / 1000;
                //0.1 second.
                double hunMillSec = (double)time % 1000 / 1000;
                //double millsec = (double)hunMillSec / 1000;
               // System.out.println((double) seconds + hunMillSec + "s");
                /*int hh = time / 60 / 60 % 60;
                int mm = time / 60 % 60;
                int ss = time % 60;
                System.out.println("还剩" + hh + "小时" + mm + "分钟" + ss + "秒");*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
