package TowerDenfense;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.*;
import org.jsfml.window.Mouse;

import java.util.concurrent.Semaphore;


public class Buttons
{
    //Images of buttons
    public ConstTexture shownPic[] = new ConstTexture[5];
    public ConstTexture buttonLight[] = new ConstTexture[5];
    public ConstTexture shown[] = new ConstTexture[5];
    public ConstTexture showPanel;
    //Containers
    public Sprite container[] = new Sprite[5];
    //The description panel & shown images
    private Sprite panel = new Sprite();
    //In description panel
    private Sprite shownContainer[] = new Sprite[5];
    private int index[] = new int[5];
    private int width = 512;
    private int max[] = new  int[5];
    private boolean isEnter[] = new boolean[5];
    private int animation[] = new int[5];
    private Font font;
    private Text texts[] = new Text[5];
    public RenderWindow window;
    public int type;
    public int selectedType = 0;
    //Gold & money
    private ConstTexture goldPic;
    private Sprite gold = new Sprite();
    private int widthGold = 186;
    private int heightGold = 150;
    private ConstTexture moneyNum;
    private Sprite moneyPanel = new Sprite();
    private Text moneyPanelText;
    private ConstTexture morePic;
    private Sprite moreGolds = new Sprite();
    //Life Volume
    private ConstTexture lifePic;
    private Sprite life = new Sprite();
    private ConstTexture lifeNum;
    private Sprite lifePanel = new Sprite();
    private Text lifeText;
    private Text lifePanelText;
    //Each second adds one
    public int money = 25;
    public Semaphore mutex = new Semaphore(1);
    private Font moneyFont;
    private Text moneyText;
    private int timer = 0;
    private int timerFlag = 0;
    public int lifeVolume = 30;
    //Bug the solider, only for one time
    public boolean isCost = false;
    public boolean isCancel = false;
    private Sound buttonSound = new Sound();
    private Sound selectSound = new Sound();
    private int oneTimeSound[] = new int[5];
    public int oneTimeSelect[] = new int[5];
    private int up1 = 0;
    private int up2 = 0;

    public Buttons(RenderWindow window)
    {
        this.type = type;
        this.window = window;
        font = TDPictures.font;
        moneyFont = TDPictures.money;
        moneyText = new Text("", moneyFont, 35);
        moneyText.setColor(Color.RED);
        moneyText.setStyle(TextStyle.BOLD);
        moneyPanelText = new Text("", font, 18);
        moneyPanelText.setColor(Color.RED);
        moneyPanelText.setStyle(TextStyle.BOLD);
        lifeText = new Text("", moneyFont, 35);
        lifeText.setStyle(TextStyle.BOLD);
        lifeText.setColor(Color.RED);
        lifePanelText = new Text("", font, 18);
        lifePanelText.setColor(Color.GREEN);
        lifePanelText.setStyle(TextStyle.BOLD);
        setPics();
    }

    public void setPics()
    {
        for(int i = 0; i < 5; i++)
        {
            shownPic[i] = TDPictures.buttons.get(i);
            buttonLight[i] = TDPictures.buttonsLight.get(i);
            shown[i] = TDPictures.shownButtons.get(i);
            isEnter[i] = false;
            animation[i] = 0;
            index[i] = 0;
            max[i] = shown[i].getSize().x;
            container[i] = new Sprite();
            shownContainer[i] = new Sprite();
            shownContainer[i].setTexture(shown[i]);
            container[i].setOrigin(150, 150);
            container[i].setTexture(shownPic[i]);
            if(i == 2)
            {
                container[i].setScale((float) 1.3, (float) 1.3);
                shownContainer[i].setScale((float) 1.2, (float) 1.2);
            }
            else
                container[i].setScale((float) 1.15, (float) 1.15);
            if(i == 3)
                shownContainer[i].setScale((float) 0.9, (float) 0.9);
        }
        //Set text and font
        for(int i = 0; i < 5; i++)
        {
            texts[i] = new Text("", font, 18);
            texts[i].setColor(Color.RED);
            texts[i].setStyle(TextStyle.BOLD);
            if(i == 0)
                texts[i].setString("Fire Caster\nRemote\nAttack 2\nHP 10\nSpeed 10\nCost 1");
            else if(i == 1)
                texts[i].setString("Wizard\nRemote\nAttack 3\nHP 15\nSpeed 10\nCost 3");
            else if(i == 2)
                texts[i].setString("Archer\nRemote\nAttack 5\nHP 15\nSpeed 10\nCost 5");
            else if(i == 3)
                texts[i].setString("Fatal Solider\nClose\nAttack 1\nHP 40\nSpeed 10\nCost 3");
            else if(i == 4)
                texts[i].setString("Fire Archer\nClose\nAttack 8\nHP 20\nSpeed 10\nCost 7");

            //Music sounds
            oneTimeSound[i] = 0;
            oneTimeSelect[i] = 0;
        }

        //Music
        buttonSound = TDMusicPlay.buttonMusic;
        selectSound = TDMusicPlay.selectMusic;

        //Used for display money
        goldPic = TDPictures.golds.get(0);
        morePic = TDPictures.moreGold;
        moreGolds.setTexture(morePic);
        moreGolds.setOrigin(360, 55);
        moreGolds.setScale((float) 0.7, (float) 0.7);
        gold.setTexture(goldPic);
        gold.setOrigin(widthGold / 2, heightGold / 2);
        moneyNum = TDPictures.panel;
        moneyPanel.setTexture(moneyNum);
        moneyPanel.setOrigin(110, 80);
        moneyPanel.setScale((float) 0.8, (float) 0.7);
        //Used for display life volume
        lifePic = TDPictures.life.get(0);
        life.setTexture(lifePic);
        life.setOrigin(53, 54);
        lifeNum = TDPictures.panel;
        lifePanel.setTexture(moneyNum);
        lifePanel.setOrigin(110, 80);
        lifePanel.setScale((float) 0.75, (float) 0.5);
        //Bottom soldiers cards
        this.showPanel = TDPictures.panel;
        this.panel.setOrigin(220, 160);
        this.panel.setTexture(this.showPanel);
    }

    //Draw the button
    public void onDraw()
    {
        for(int i = 0; i < 5; i++)
        {
            container[i].setPosition(720 + i * 120, 730);
            window.draw(container[i]);
            mouseListener(i);
        }
        upperListener();
        //Money
        this.gold.setPosition(700, 60);
        window.draw(this.gold);
        //Auto adding money, 1 / 8sec
        this.addMoney();
        moneyText.setString(String.valueOf(money));
        moneyText.setPosition(710, 40);
        window.draw(moneyText);
        //Life
        this.life.setPosition(1100, 50);
        window.draw(this.life);
        this.lifeText.setString(String.valueOf(lifeVolume));
        this.lifeText.setPosition(1125, 40);
        window.draw(this.lifeText);
    }

    //Get the current money and current life volume
    public void upperListener()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        boolean isEnter1 = Math.abs(gold.getPosition().x - mouseX) <= 50 &&
                Math.abs(gold.getPosition().y - mouseY) <= 50;

        boolean isEnter2 = Math.abs(life.getPosition().x - mouseX) <= 50 &&
                Math.abs(life.getPosition().y - mouseY) <= 50;

        if(isEnter1)
        {
            if(up1 == 0)
            {
                up1++;
                buttonSound.play();
            }
            goldPic = TDPictures.golds.get(1);
            gold.setTexture(goldPic);
            moneyPanel.setPosition(830, 30);
            window.draw(moneyPanel);
            moneyPanelText.setString("Current Money: " + money + "\n" +
                    "add 1 per 5 sec");
            moneyPanelText.setPosition(760, 20);
            window.draw(moneyPanelText);
        }
        else if(!isEnter1)
        {
            goldPic = TDPictures.golds.get(0);
            gold.setTexture(goldPic);
            up1 = 0;
        }

        if(isEnter2)
        {
            if(up2 == 0)
            {
                up2++;
                buttonSound.play();
            }
            lifePic = TDPictures.life.get(1);
            life.setTexture(lifePic);
            lifePanel.setPosition(1230, 40);
            window.draw(lifePanel);
            lifePanelText.setString("Current life: " + lifeVolume);
            lifePanelText.setPosition(1165, 20);
            window.draw(lifePanelText);

        }
        else if(!isEnter2)
        {
            up2 = 0;
            lifePic = TDPictures.life.get(0);
            life.setTexture(lifePic);
        }

    }

    //Use to build the tower
    public void mouseListener(int i)
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        this.isEnter[i] =  Math.abs(container[i].getPosition().x - mouseX) <= 30
                && Math.abs(container[i].getPosition().y - mouseY) <= 30;

        if(this.isEnter[i])
        {
            this.container[i].setTexture(this.buttonLight[i]);
            this.panel.setPosition(container[i].getPosition().x, container[i].getPosition().y);

            if(oneTimeSound[i] == 0)
            {
                oneTimeSound[i]++;
                buttonSound.play();
            }
            //buttonSound[i].play();
            if(i == 0)
            {
                this.texts[i].setPosition(container[i].getPosition().x - 110, container[i].getPosition().y - 140);
                this.shownContainer[i].setPosition(container[i].getPosition().x - 410, container[i].getPosition().y - 335);
            }
            else if(i == 1)
            {
                this.texts[i].setPosition(container[i].getPosition().x - 90, container[i].getPosition().y - 140);
                this.shownContainer[i].setPosition(container[i].getPosition().x - 430, container[i].getPosition().y - 360);
            }
            else if(i == 2)
            {
                this.texts[i].setPosition(container[i].getPosition().x - 90, container[i].getPosition().y - 140);
                this.shownContainer[i].setPosition(container[i].getPosition().x - 460, container[i].getPosition().y - 410);
            }
            else if(i == 3)
            {
                this.texts[i].setPosition(container[i].getPosition().x - 120, container[i].getPosition().y - 140);
                this.shownContainer[i].setPosition(container[i].getPosition().x - 400, container[i].getPosition().y - 320);
            }
            else if(i == 4)
            {
                this.texts[i].setPosition(container[i].getPosition().x - 110, container[i].getPosition().y - 140);
                this.shownContainer[i].setPosition(container[i].getPosition().x - 415, container[i].getPosition().y - 340);
            }
            changeIndex(i);

            this.shownContainer[i].setTextureRect(new IntRect(width*index[i], 0, width,512));
            this.window.draw(this.panel);
            this.window.draw(this.shownContainer[i]);
            this.window.draw(this.texts[i]);

        }
        else if(!isEnter[i])
        {
            animation[i] = 0;
            this.container[i].setTexture(this.shownPic[i]);
            oneTimeSound[i] = 0;
            //buttonSound[i].stop();
        }

        if(isEnter[i] && mouse.isButtonPressed(Mouse.Button.LEFT) && selectedType == 0)
        {
            //Type 1, cost 1 gold
            if(i == 0)
            {
                if(money >= 1 && !isCost)
                {
                    isCost = true;
                    isCancel = false;
                    money--;
                    selectedType = i + 1;
                    if(oneTimeSelect[i] == 0)
                    {
                        selectSound.play();
                        oneTimeSelect[i]++;
                    }
                }
                else
                {
                    moreGolds.setPosition(1000, 400);
                    window.draw(moreGolds);
                }
            }
            else if(i == 1)
            {
                if(money >= 3 && !isCost)
                {
                    isCost = true;
                    isCancel = false;
                    money -= 3;
                    selectedType = i + 1;
                    if(oneTimeSelect[i] == 0)
                    {
                        selectSound.play();
                        oneTimeSelect[i]++;
                    }
                }
                else
                {
                    moreGolds.setPosition(1000, 400);
                    window.draw(moreGolds);
                }
            }
            else if(i == 2)
            {
                if(money >= 5 && !isCost)
                {
                    isCost = true;
                    isCancel = false;
                    money -= 5;
                    selectedType = i + 1;
                    if(oneTimeSelect[i] == 0)
                    {
                        selectSound.play();
                        oneTimeSelect[i]++;
                    }
                }
                else
                {
                    moreGolds.setPosition(1000, 400);
                    window.draw(moreGolds);
                }
            }
            else if(i == 3)
            {
                if(money >= 3 && !isCost)
                {
                    isCost = true;
                    isCancel = false;
                    money -= 3;
                    selectedType = i + 1;
                    if(oneTimeSelect[i] == 0)
                    {
                        selectSound.play();
                        oneTimeSelect[i]++;
                    }
                }
                else
                {
                    moreGolds.setPosition(1000, 400);
                    window.draw(moreGolds);
                }
            }
            else if(i == 4)
            {
                if(money >= 7 && !isCost)
                {
                    isCost = true;
                    isCancel = false;
                    money -= 7;
                    selectedType = i + 1;
                    if(oneTimeSelect[i] == 0)
                    {
                        selectSound.play();
                        oneTimeSelect[i]++;
                    }
                }
                else
                {
                    moreGolds.setPosition(1000, 400);
                    window.draw(moreGolds);
                }
            }
        }

        //If cancel, money back.
        if(mouse.isButtonPressed(Mouse.Button.RIGHT))
        {
            if(!isCancel && selectedType == 1)
            {
                isCancel = true;
                isCost = false;
                money++;
                selectedType = 0;
            }
            else if(!isCancel && selectedType == 2)
            {
                isCancel = true;
                isCost = false;
                money += 3;
                selectedType = 0;
            }
            else if(!isCancel && selectedType == 3)
            {
                isCancel = true;
                isCost = false;
                money += 5;
                selectedType = 0;
            }
            else if(!isCancel && selectedType == 4)
            {
                isCancel = true;
                isCost = false;
                money += 3;
                selectedType = 0;
            }
            else if(!isCancel && selectedType == 5)
            {
                isCancel = true;
                isCost = false;
                money += 7;
                selectedType = 0;
            }

        }
    }

    public void changeIndex(int i)
    {
        if(animation[i] == 0)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    animation[i]++;
                    while(isEnter[i])
                    {
                        index[i]++;
                        if(index[i] == max[i] / width)
                            index[i] = 0;
                        try {
                            Thread.sleep(150);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    //Auto adding money evey 5 seconds
    private void addMoney()
    {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(timerFlag == 0)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    timerFlag++;
                    while(true)
                    {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        timer++;
                        if(timer == 8)
                        {
                            money++;
                            timerFlag = 0;
                            timer = 0;
                            break;
                        }
                    }
                }
            }).start();
        }
        mutex.release();
    }
}
