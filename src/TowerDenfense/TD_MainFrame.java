package TowerDenfense;

import GameDriver.staticVariable;
import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TD_MainFrame
{
    private int level;
    private RenderWindow window;
    private TD_Maps map;
    private Buttons buttons;
    private List<Soliders> towers = new ArrayList<Soliders>();
    private List<MapItems> items = new ArrayList<MapItems>();
    private List<Monsters> monsters = new ArrayList<Monsters>();
    private List<Bullets> bullets = new ArrayList<Bullets>();
    private boolean[][] isSet = new boolean[5][8];

    //Determine defeated or victory
    public boolean isOver = false;
    public boolean isSuccess = false;
    //Sounds
    private Sound build;
    private Sound addMoney;
    private Sound heroMusic[] = new Sound[5];
    private Sound heroHit[] = new Sound[5];
    //private Sound background;
    private Sound monsterDeath;
    //One level, with several waves of monsters
    private int waves = 1;
    private int fullWaves = 20;
    private Text wavesRecord = new Text("1", staticVariable.rankFont, 30);
    private int count = 0;
    private boolean nextDraw = false;
    private ConstTexture nextPic;
    private Sprite next = new Sprite();
    private boolean startMusic = false;

    public TD_MainFrame(RenderWindow window, int level)
    {
        this.window = window;
        AddMonsters.waves = waves;
        this.level = level;
        map = new TD_Maps(window, level);
        buttons = new Buttons(window);
        loadSounds();
        setItems();
        nextPic = TDPictures.nextNum;
        next.setTexture(nextPic);
        next.setOrigin(500, 131 / 2);
        next.setScale((float) 0.7, (float) 0.7);
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 8; j++)
                isSet[i][j] = false;
        }
        wavesRecord.setStyle(TextStyle.BOLD);
        wavesRecord.setPosition(1350, 28);
        wavesRecord.setColor(Color.RED);
        AddMonsters.fullWaves = fullWaves;
    }

    public void loadSounds()
    {
        build = TDMusicPlay.buildMusic;
        addMoney = TDMusicPlay.addMoney;
        //background = TDMusicPlay.backgroundMusic;
        for(int i = 0; i < 5; i++)
        {
            heroMusic[i] = TDMusicPlay.heroMusic.get(i);
            heroHit[i] = TDMusicPlay.hit.get(i);
        }
        monsterDeath = TDMusicPlay.monsterDead;
    }

    public void setItems()
    {
        items.add(new MapItems(1, window));
        items.add(new MapItems(2, window));
        items.add(new MapItems(3, window));
        if(level == 2)
            items.add(new MapItems(4, window));
    }

    public void mainLoop()
    {
        while(window.isOpen())
        {
            for(Event event : window.pollEvents())
            {
                if(event.type == Event.Type.CLOSED)
                {
                    window.close();
                    System.exit(0);
                    break;
                }
            }
            window.clear(Color.WHITE);
            onDraw();
            window.display();
        }
    }

    //Aims to build towers
    public void mouseLisnter()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        int x = mouseX - 500;
        int y = mouseY - 100;

        //The tower is selected
        if(buttons.selectedType != 0)
        {
            if(mouse.isButtonPressed(Mouse.Button.LEFT))
            {
                buttons.isCost = false;
                buttons.oneTimeSelect[buttons.selectedType - 1] = 0;
                build.play();
                int i = decideY(y);
                int j = decideX(x);
                if(i != -1 && j != -1)
                {
                    heroMusic[buttons.selectedType - 1].play();
                    if(!isSet[i][j])
                    {
                        int posX = 60 + j * 120 + 500;
                        int posY = 50 + i * 120 + 100;
                        if(buttons.selectedType == 3)
                            posY = 40 + i * 120 + 100;
                        towers.add(new Soliders(buttons.selectedType, posX, posY, posX, posY, i, j, window));
                        isSet[i][j] = true;
                        buttons.selectedType = 0;
                    }
                }
            }
        }
    }

    //The main controller of the TD
    public void mainController()
    {
        if(waves == 20 && AddMonsters.curNum == 20 && AddMonsters.curNum == 0 && buttons.lifeVolume > 0)
            isSuccess = true;
        if(buttons.lifeVolume <= 0)
            isOver = true;

        if(!isSuccess && !isOver)
        {
            addMonstersForLevel();
            //The towers attack the monsters
            attackMonster();
            //Determine whether the bullet hits the monster
            bulletsHitted();
            //Decide whether the monster is dead, if so, remove it.
            monsterDead();
            //The monsters attack the towers
            monsterAttack();
            monsterMoving();
            //Determine whether the monster enters the city, if so, remove it.
            monsterEntered();
        }
    }

    //Aims to attack enemies
    public void attackMonster()
    {
        for(int i = 0; i < towers.size(); i++)
        {
            Iterator<Monsters> iterator = monsters.iterator();
            while(iterator.hasNext())
            {
                Monsters one = iterator.next();
                //In the same line, and cannot attack the monsters behind
                //For the remote types of soldiers
                if(towers.get(i).i == one.i && one.x > towers.get(i).x && towers.get(i).type != 4)
                {
                    towers.get(i).isAttack = true;
                    if (towers.get(i).index == 1 && towers.get(i).isAttack)
                        towers.get(i).attackOnce = 0;
                        //Type 5 can attack 5 times
                    else if(towers.get(i).type == 5 && towers.get(i).isAttack && towers.get(i).index == 10)
                        towers.get(i).attackOnce = 0;
                    //Only one bullet.
                    if (towers.get(i).attackOnce == 0)
                    {
                        if(towers.get(i).type == 1)
                        {
                            if(towers.get(i).index == 6)
                            {
                                towers.get(i).attackOnce = 1;
                                this.bullets.add(new Bullets(towers.get(i).type, towers.get(i).i, towers.get(i).j, window));
                            }
                        }
                        else if(towers.get(i).type == 2)
                        {
                            if(towers.get(i).index == 6)
                            {
                                towers.get(i).attackOnce = 1;
                                this.bullets.add(new Bullets(towers.get(i).type, towers.get(i).i, towers.get(i).j, window));
                            }
                        }
                        else if(towers.get(i).type == 3)
                        {
                            if(towers.get(i).index == 7)
                            {
                                towers.get(i).attackOnce = 1;
                                this.bullets.add(new Bullets(towers.get(i).type, towers.get(i).i, towers.get(i).j, window));
                            }
                        }
                        else if(towers.get(i).type == 5)
                        {
                            if(towers.get(i).index == 8 || towers.get(i).index == 14)
                            {
                                towers.get(i).attackOnce = 1;
                                this.bullets.add(new Bullets(towers.get(i).type, towers.get(i).i, towers.get(i).j, window));
                            }
                        }
                    }
                }
                //For type 4, which is a short distance solider
                else if(towers.get(i).i == one.i && towers.get(i).type == 4 && one.x - towers.get(i).x < 100 &&
                        one.x > towers.get(i).x)
                {
                    towers.get(i).isAttack = true;
                    if (towers.get(i).index == 1 && towers.get(i).isAttack)
                        towers.get(i).attackOnce = 0;
                    if (towers.get(i).attackOnce == 0 && towers.get(i).index == 3)
                    {
                        heroHit[3].play();
                        towers.get(i).attackOnce = 1;
                        one.hp -= towers.get(i).attackVolume;
                    }
                }
            }
        }
    }


    //If the bullets for the remote solider hitted the monsters
    public void bulletsHitted()
    {
        for(int i = 0; i < bullets.size(); i++)
        {
            //Traverse all the bullets & monsters
            Iterator<Monsters> iterator = monsters.iterator();
            while(iterator.hasNext())
            {
                Monsters one = iterator.next();
                //Same line & distance < 10
                if(one.i == bullets.get(i).i && one.x - bullets.get(i).x < 90 && !bullets.get(i).isReached)
                {
                    if(bullets.get(i).type == 3 || bullets.get(i).type == 5)
                    {
                        bullets.get(i).x -= 50;
                    }

                    heroHit[bullets.get(i).type - 1].setVolume(15.0f);
                    heroHit[bullets.get(i).type - 1].play();
                    bullets.get(i).isReached = true;
                    bullets.get(i).isMove = false;

                    if(bullets.get(i).type == 1 && bullets.get(i).attackOnce == 0)
                    {
                        bullets.get(i).attackOnce++;
                        one.hp -= bullets.get(i).attackVolume;
                    }
                    else if(bullets.get(i).type == 2 && bullets.get(i).attackOnce == 0)
                    {
                        bullets.get(i).attackOnce++;
                        one.hp -= bullets.get(i).attackVolume;
                    }
                    else if(bullets.get(i).type == 3 && bullets.get(i).attackOnce == 0)
                    {
                        bullets.get(i).attackOnce++;
                        one.hp -= bullets.get(i).attackVolume;
                    }
                    else if(bullets.get(i).type == 5 && bullets.get(i).attackOnce == 0)
                    {
                        bullets.get(i).attackOnce++;
                        one.hp -= bullets.get(i).attackVolume;
                    }

                    if(bullets.get(i).isReachFinished)
                    {
                        bullets.remove(i);
                        i--;
                        if(i == -1)
                            break;
                    }
                }
            }
        }
    }

    //The death of monsters, then the tower can stop attack
    public void monsterDead()
    {
        Iterator<Monsters> iterator = monsters.iterator();
        while(iterator.hasNext())
        {
            Monsters one = iterator.next();
            if(one.hp <= 0 && one.isDeadFinish)
            {
                monsterDeath.setVolume(15.0f);
                monsterDeath.play();
                for(int i = 0; i < towers.size(); i++)
                    if(one.i == towers.get(i).i)
                        towers.get(i).isAttack = false;
                iterator.remove();
                addMoney.play();
                AddMonsters.curNum--;
                try {
                    buttons.mutex.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                buttons.money++;
                buttons.mutex.release();
            }
        }
    }

    //If the monsters enter the city, then remove & reduce life volume
    public void monsterEntered()
    {
        Iterator<Monsters> iterator = monsters.iterator();
        while(iterator.hasNext())
        {
            Monsters one = iterator.next();
            if(one.isEnter && !one.isDead)
            {
                buttons.lifeVolume--;
                AddMonsters.curNum--;
                iterator.remove();
            }
        }
    }

    //The monsters attack the tower
    public void monsterAttack()
    {
        for(int i = 0; i < monsters.size(); i++)
        {
            Iterator<Soliders> iterator = towers.iterator();
            while(iterator.hasNext())
            {
                Soliders one = iterator.next();
                if(one.i == monsters.get(i).i)
                {
                    if (monsters.get(i).x - one.x < 100 && monsters.get(i).x - one.x > 0
                            && monsters.get(i).isDead == false)
                    {
                        monsters.get(i).isAttack = true;
                        monsters.get(i).isMove = false;

                        if (monsters.get(i).index == 1 && monsters.get(i).isAttack)
                            monsters.get(i).attackOnce = 0;

                        if (monsters.get(i).attackOnce == 0 && monsters.get(i).index == 3)
                        {
                            monsters.get(i).attackOnce = 1;
                            one.blood_volume -= 1;
                        }
                    }
                }
                //Dead
                if(one.blood_volume <= 0)
                {
                    one.isDead = true;
                    isSet[one.i][one.j] = false;
                    iterator.remove();
                }
            }
        }

        for(int i = 0; i < monsters.size(); i++)
        {
            if(towers.size() == 0)
                monsters.get(i).isAttack = false;
            monsters.get(i).isMove = true;
        }
    }

    public void monsterMoving()
    {
        for(int i = 0; i < monsters.size(); i++)
            decideMoving(i);
    }

    public void decideMoving(int index)
    {
        boolean stopAttack = true;
        for(int i = 0; i < towers.size(); i++)
        {
            if(towers.get(i).i != monsters.get(index).i)
                continue;
            else if(towers.get(i).i == monsters.get(index).i)
            {
                if(monsters.get(index).x - towers.get(i).x >= 100)
                    continue;
                else if(monsters.get(index).x - towers.get(i).x < 100 && monsters.get(index).x > towers.get(i).x)
                {
                    stopAttack = false;
                    break;
                }
            }
        }

        if(stopAttack)
        {
            monsters.get(index).isAttack = false;
            monsters.get(index).isMove = true;
        }
    }

    //This aims to add monsters for different levels with different types
    public void addMonstersForLevel()
    {
        boolean flag = AddMonsters.addMons();
        if(AddMonsters.number == 20 && AddMonsters.curNum == 0)
        {
            waves++;
            AddMonsters.waves = waves;
            AddMonsters.number = 0;
            nextDraw = true;
        }
        if(flag && waves <= 20)
        {
            int lineRandom = (int) (Math.random() * 5);
            int type = -1;
            //Different waves, different monsters
            if(this.waves <= 3)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 1;
                else if(typeRandom == 2)
                    type = 2;
                else if(typeRandom == 3)
                    type = 3;
                else if(typeRandom == 4)
                    type = 4;
                else if(typeRandom == 5)
                    type = 5;
                else if(typeRandom == 6)
                    type = 6;
                else if(typeRandom == 7)
                    type = 7;
                else if(typeRandom == 8)
                    type = 8;
                else if(typeRandom == 9)
                    type = 9;
                else if(typeRandom == 10)
                    type = 10;

            }
            else if(this.waves > 3 && this.waves <= 5)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom >= 1 && typeRandom <= 2)
                    type = 1;
                else if(typeRandom >= 3 && typeRandom <= 4)
                    type = 2;
                else if(typeRandom >= 5 && typeRandom <= 6)
                    type = 3;
                else if(typeRandom >= 7 && typeRandom <= 8)
                    type = 4;
                else if(typeRandom >= 9 && typeRandom <= 10)
                    type = 5;
            }
            else if(this.waves > 5 && this.waves <= 7)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 1;
                else if(typeRandom >= 2 && typeRandom <= 3)
                    type = 2;
                else if(typeRandom == 4 || typeRandom == 5)
                    type = 3;
                else if(typeRandom >= 6 && typeRandom <= 8)
                    type = 4;
                else if(typeRandom >= 9 && typeRandom <= 10)
                    type = 5;
            }
            else if(this.waves > 7 && this.waves <= 9)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 2;
                else if(typeRandom == 2 || typeRandom == 3 || typeRandom == 4)
                    type = 3;
                else if(typeRandom == 5 || typeRandom == 6)
                    type = 4;
                else if(typeRandom == 7 || typeRandom == 8)
                    type = 5;
                else if(typeRandom == 9 || typeRandom == 10)
                    type = 6;
            }
            else if(this.waves == 10)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 2;
                else if(typeRandom == 2 || typeRandom == 3)
                    type = 3;
                else if(typeRandom == 4 || typeRandom == 5)
                    type = 4;
                else if(typeRandom >= 6 && typeRandom <= 8)
                    type = 5;
                else if(typeRandom == 9 || typeRandom == 10)
                    type = 6;
            }
            else if(this.waves == 11 || this.waves == 12)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 2;
                else if(typeRandom == 2)
                    type = 3;
                else if(typeRandom == 3)
                    type = 4;
                else if(typeRandom == 4)
                    type = 5;
                else if(typeRandom >= 5 && typeRandom <= 7)
                    type = 6;
                else if(typeRandom >= 8 && typeRandom <= 10)
                    type = 7;
            }
            else if(this.waves > 12 && this.waves < 15)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 3;
                else if(typeRandom == 2 || typeRandom == 3 || typeRandom == 4)
                    type = 4;
                else if(typeRandom == 5)
                    type = 5;
                else if(typeRandom == 6)
                    type = 6;
                else if(typeRandom >= 7 && typeRandom <= 9)
                    type = 7;
                else if(typeRandom == 10)
                    type = 8;
            }
            else if(this.waves >= 15 && this.waves <= 17)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1 || typeRandom == 2)
                    type = 4;
                else if(typeRandom == 3 || typeRandom == 4)
                    type = 5;
                else if(typeRandom == 5)
                    type = 6;
                else if(typeRandom == 6 || typeRandom == 7)
                    type = 7;
                else if(typeRandom == 8 || typeRandom == 9)
                    type = 8;
                else if(typeRandom == 10)
                    type = 9;
            }
            else if(this.waves == 18 || this.waves == 19)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 4;
                else if(typeRandom == 2)
                    type = 5;
                else if(typeRandom == 3)
                    type = 6;
                else if(typeRandom == 4 || typeRandom == 5)
                    type = 7;
                else if(typeRandom == 6 || typeRandom == 7)
                    type = 8;
                else if(typeRandom == 8 || typeRandom == 9)
                    type = 9;
                else if(typeRandom == 10)
                    type = 10;
            }
            else if(this.waves == 20)
            {
                int typeRandom = (int) (Math.random() * 10) + 1;
                if(typeRandom == 1)
                    type = 6;
                else if(typeRandom == 2 || typeRandom == 3)
                    type = 7;
                else if(typeRandom == 4 || typeRandom == 5 ||
                        typeRandom == 6)
                    type = 8;
                else if(typeRandom == 7)
                    type = 9;
                else if(typeRandom == 8 || typeRandom == 9 || typeRandom == 10)
                    type = 10;
            }


            monsters.add(new Monsters(type, lineRandom, window));
            AddMonsters.once = 0;
            AddMonsters.isAdd = false;
        }
    }

    //Draw all the pictures
    public void onDraw()
    {
        mouseLisnter();
        if(buttons.lifeVolume > 0 && waves == 3 && AddMonsters.number == 20 && AddMonsters.curNum == 0)
            isSuccess = true;
        if(!isSuccess && !isOver)
            mainController();
        if(buttons.lifeVolume <= 0)
            isOver = true;
        map.onDraw();
        if(!isSuccess && !isOver)
        {
            if(!startMusic)
            {
                TDMusicPlay.backgroundMusic.play();
                startMusic = true;
            }
            for(int i = 0; i < towers.size(); i++)
                towers.get(i).onDraw();
            for(int i = 0; i < items.size(); i++)
                items.get(i).onDraw();
            for(int i = 0; i < monsters.size(); i++)
            {
                if(!monsters.get(i).isDeadFinish && !monsters.get(i).isEnter)
                    monsters.get(i).onDraw();
            }
            //System.out.println(monsters.size());
            for(int i = 0; i < bullets.size(); i++)
            {
                if(!bullets.get(i).isReachFinished)
                    bullets.get(i).onDraw();
            }
            buttons.onDraw();
            //Next number of enemies
            if(nextDraw)
            {
                count++;
                this.next.setPosition(950, 400);
                window.draw(this.next);
                if(count == 100)
                {
                    count = 0;
                    nextDraw = false;
                }
            }

            wavesRecord.setString(waves + " / " + fullWaves);
            window.draw(wavesRecord);
        }
        else if(isOver)
        {
            for(int i = 0; i < items.size(); i++)
                items.get(i).isEnd = true;
            for(int i = 0; i < monsters.size(); i++)
                monsters.get(i).isEnd  = true;
            for(int i = 0; i < towers.size(); i++)
                towers.get(i).isEnd = true;
            for(int i = 0; i < bullets.size(); i++)
                bullets.get(i).isEnd = true;
            TDMusicPlay.backgroundMusic.stop();
        }
        else if(isSuccess)
        {
            for(int i = 0; i < items.size(); i++)
                items.get(i).isEnd = true;
            for(int i = 0; i < monsters.size(); i++)
                monsters.get(i).isEnd  = true;
            for(int i = 0; i < towers.size(); i++)
                towers.get(i).isEnd = true;
            for(int i = 0; i < bullets.size(); i++)
                bullets.get(i).isEnd = true;
            TDMusicPlay.backgroundMusic.stop();
        }
    }

    //X conforms to index j
    public int decideX(int x)
    {
        int j = -1;
        if(x > 0 && x <= 120)
            j = 0;
        else if(x > 120 && x <= 240)
            j = 1;
        else if(x > 240 && x <= 360)
            j = 2;
        else if(x > 360 && x <= 480)
            j = 3;
        else if(x > 480 && x <= 600)
            j = 4;
        else if(x > 600 && x <= 720)
            j = 5;
        else if(x > 720 && x <= 840)
            j = 6;
        else if(x > 840 && x <= 960)
            j = 7;
        return j;
    }

    //Y conforms to index i
    public int decideY(int y)
    {
        int i = -1;
        if(y <= 120 && y > 0)
            i = 0;
        else if(y <=240 && y > 120)
            i = 1;
        else if(y <= 360 && y > 240)
            i = 2;
        else if(y <= 480 && y > 360)
            i = 3;
        else if(y <= 600 && y > 480)
            i = 4;
        return i;
    }
}