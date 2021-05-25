package GameDriver;

/*
This class aims to add enemies for different chapters
 */

import Actors.Background;
import Actors.Boss;
import Actors.Enemies;
import Actors.EnemyMagic;

import java.util.ArrayList;
import java.util.List;

public class addEnemies
{
    public List<Enemies> allEnermy = new ArrayList<Enemies>();
    public List<Boss> allBoss = new ArrayList<>();
    public addEnemies(int level)
    {
        if(level==1) {
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 300;
                int randomNumberY = (int) (Math.random() * 400) + 300;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 250, 800, 300, 700, 5, "close", 0));
            }
            for (int i = 0; i < 5; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 1000;
                int randomNumberY = (int) (Math.random() * 400) + 2000;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1000, 1500, 2000, 2400, 2, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 3000;
                int randomNumberY = (int) (Math.random() * 400) + 3400;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 3000, 3500, 2700, 3100, 3, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 4000;
                int randomNumberY = (int) (Math.random() * 400) + 1500;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 4000, 4500, 1500, 1900, 3, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 4000;
                int randomNumberY = (int) (Math.random() * 400) + 3000;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 4000, 4500, 3000, 3400, 4, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 4500;
                int randomNumberY = (int) (Math.random() * 400) + 200;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 4500, 5000, 200, 600, 5, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 700) + 4900;
            float randomNumberY = (float) (Math.random() * 400) - 150;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            //this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 4900, 5600, -150, 250, 1, "close", 0));
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 4900-600, 5600, -350, 2000, 8, "close", 0));
        }
        else if(level==3)
        {
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 200;
                int randomNumberY = (int) (Math.random() * 400) + 400;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 200, 600, 400, 800, 10, "close", 0));
            }
            for (int i = 0; i < 5; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 1400;
                int randomNumberY = (int) (Math.random() * 350) + 1350;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1400, 1900, 1550, 1750, 9, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 3000;
                int randomNumberY = (int) (Math.random() * 400) + 500;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 3000, 3500, 500, 900, 5, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 600) +2900;
                int randomNumberY = (int) (Math.random() * 500) + 1200;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 2900, 3500, 1200, 1700, 8, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 700) + 2900;
            float randomNumberY = (float) (Math.random() * 600) + 1200;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 2900, 3600, 1200, 1800, 2, "close", 0));
        }
        else if(level==4)
        {
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 200;
                int randomNumberY = (int) (Math.random() * 400) + 700;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 200, 600, 700, 1100, 10, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 400) - 700;
                int randomNumberY = (int) (Math.random() * 400) + 700;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, -700, -300, 700, 1100, 10, "close", 0));
            }
            for (int i = 0; i < 5; i++) {
                int randomNumberX = (int) (Math.random() * 400) - 700;
                int randomNumberY = (int) (Math.random() * 500) + 2500;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, -700, -300, 2500, 3000, 9, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 300) + 2500;
                int randomNumberY = (int) (Math.random() * 500) + 1400;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 2500, 2800, 1400, 1900, 10, "close", 0));
            }
            for (int i = 0; i < 5; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1100;
                int randomNumberY = (int) (Math.random() * 500) + 1800;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1100, 1500, 1800, 2300, 8, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 500) + 2200;
            float randomNumberY = (float) (Math.random() * 500) + 1900;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 2200, 2700, 1900, 2400, 3, "close", 0));
        }
        else if(level==5)
        {
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) - 346;
                int randomNumberY = (int) (Math.random() * 500) + 1641;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, -446, -46, 1641, 2141, 9, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 300;
                int randomNumberY = (int) (Math.random() * 400) + 3460;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 300, 700, 3460, 3860, 1, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 870;
                int randomNumberY = (int) (Math.random() * 300) + 3840;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 870, 1200, 3840, 4140, 3, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1700;
                int randomNumberY = (int) (Math.random() * 200) + 3900;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1700, 2100, 3900, 4100, 4, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 3800;
                int randomNumberY = (int) (Math.random() * 400) + 2860;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 3800, 4200, 2860, 3260, 2, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 3000;
                int randomNumberY = (int) (Math.random() * 400) + 1770;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 3000, 3400, 1770, 2170, 1, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 300) + 1500;
                int randomNumberY = (int) (Math.random() * 400) + 240;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1500, 1800, 240, 640, 5, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 500) - 200;
            float randomNumberY = (float) (Math.random() * 500) + 1500;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, -200, 300, 1500, 2000, 4, "close", 0));
        }
        else if(level==7)
        {
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) - 80 ;
                int randomNumberY = (int) (Math.random() * 400) + 147;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, -80, 320, 147, 547, 3, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 390;
                int randomNumberY = (int) (Math.random() * 400) + 40;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 290, 790, 40, 440, 2, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1020;
                int randomNumberY = (int) (Math.random() * 400) + 1050;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1020, 1420, 1050, 1450, 5, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1980;
                int randomNumberY = (int) (Math.random() * 400) + 1200;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1980, 2380, 1200, 1600, 6, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1935;
                int randomNumberY = (int) (Math.random() * 400) + 2690;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1935, 2335, 2690, 3090, 1, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1435;
                int randomNumberY = (int) (Math.random() * 400) + 5000;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1435, 1835, 5000, 5400, 2, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 600;
                int randomNumberY = (int) (Math.random() * 400) + 6500;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 600, 1000, 6500, 6900, 4, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1566;
                int randomNumberY = (int) (Math.random() * 400) + 5960;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1566, 1966, 5960, 6360, 5, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 3150;
                int randomNumberY = (int) (Math.random() * 400) + 4330;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 3150, 3550, 4330, 4730, 5, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 4840;
                int randomNumberY = (int) (Math.random() * 400) + 4950;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 4840, 5240, 4950, 5350, 1, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 6780;
                int randomNumberY = (int) (Math.random() * 400) + 5090;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 6780, 7180, 5090, 5490, 2, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 700) + 4960;
            float randomNumberY = (float) (Math.random() * 500) + 6560;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 4960, 5660, 6560, 7060, 5, "close", 0));
        }
        else if(level==8)
        {
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 900;
                int randomNumberY = (int) (Math.random() * 250) + 450;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 900, 1400, 450, 700, 7, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 0;
                int randomNumberY = (int) (Math.random() * 400) + 1350;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 0, 500, 1350, 1750, 5, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 300) +1900;
                int randomNumberY = (int) (Math.random() * 300) + 2700;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1900, 2200, 2700, 3000, 6, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 600) +300;
                int randomNumberY = (int) (Math.random() * 500) + 3600;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 300, 900, 3600, 4100, 8, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 600) +1926;
                int randomNumberY = (int) (Math.random() * 500) + 1158;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1926, 2526, 1158, 1658, 8, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 600) + 4560;
            float randomNumberY = (float) (Math.random() * 500) + 4550;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 4560, 5160, 4550, 5050, 6, "close", 0));
        }
        else if(level==9)
        {
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1630;
                int randomNumberY = (int) (Math.random() * 400) - 900;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1630, 2030, -900, -500, 7, "close", 0));
            }
            for (int i = 0; i < 4; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 370;
                int randomNumberY = (int) (Math.random() * 400) + 390;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 370, 770, 390, 790, 8, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 2304;
                int randomNumberY = (int) (Math.random() * 400) + 740;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 2304, 2704, 740, 1140, 9, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 3140;
                int randomNumberY = (int) (Math.random() * 400) + 2200;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 3140, 3540, 2200, 2600, 10, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1540;
                int randomNumberY = (int) (Math.random() * 400) + 2750;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1540, 1940, 2750, 3150, 5, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) - 30;
                int randomNumberY = (int) (Math.random() * 400) + 2220;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, -30, 370, 2220, 2620, 6, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1080;
                int randomNumberY = (int) (Math.random() * 400) + 2320;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                    this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1080, 1480, 2320, 2720, 6, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 500) + 2900;
            float randomNumberY = (float) (Math.random() * 500) + 2260;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 2900, 3400, 2260, 2760, 1, "close", 0));
        }
        else if(level==10)
        {
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 200;
                int randomNumberY = (int) (Math.random() * 400) + 700;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 200, 600, 700, 1100, 10, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 500) + 1520;
                int randomNumberY = (int) (Math.random() * 500) - 1000;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1520, 2020, -1000, -500, 5, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 2300;
                int randomNumberY = (int) (Math.random() * 400) - 800;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 2300, 2700, -800, -400, 6, "close", 0));
            }
            for (int i = 0; i < 2; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 2700;
                int randomNumberY = (int) (Math.random() * 400) - 300;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 2700, 3100, -300, 100, 7, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 3700;
                int randomNumberY = (int) (Math.random() * 400) + 600;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 3700, 4100, 600, 1000, 9, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 400) + 1050;
                int randomNumberY = (int) (Math.random() * 400) + 1690;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1050, 1450, 1690, 2090, 9, "close", 0));
            }
            for (int i = 0; i < 3; i++) {
                int randomNumberX = (int) (Math.random() * 600) + 1800;
                int randomNumberY = (int) (Math.random() * 600) + 2010;
                int randomDeriction = (int) (Math.random() * 4);
                int stateNumber = (int) (Math.random() * 2);
                this.allEnermy.add(new Enemies(randomNumberX, randomNumberY, randomDeriction, 1800, 2400, 2010, 2610, 10, "close", 0));
            }
            float randomNumberX = (float) (Math.random() * 700) + 3180;
            float randomNumberY = (float) (Math.random() * 600) + 2930;
            int randomDeriction = (int) (Math.random() * 4);
            int stateNumber = (int) (Math.random() * 2);
            this.allBoss.add(new Boss(randomNumberX, randomNumberY, randomDeriction, 3180, 3880, 2930, 3530, 7, "close", 0));
        }
    }
}
