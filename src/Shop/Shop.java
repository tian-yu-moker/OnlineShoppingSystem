package Shop;

import DatabaseManager.OperateDatabase;
import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import TowerDenfense.TDPictures;
import org.jsfml.graphics.*;
import org.jsfml.window.Mouse;


public class Shop
{
    private ConstTexture[] add = new ConstTexture[3];
    private ConstTexture[] reduce = new ConstTexture[3];

    private ConstTexture[] buy = new ConstTexture[3];


    private Sprite addButton = new Sprite();
    private Sprite reduceButton = new Sprite();
    private Sprite buyButton = new Sprite();
    private Sprite moneyButton = new Sprite();
    private Sprite equipments[] = new Sprite[11];
    //Shop open
    private Sprite shop = new Sprite();
    private ConstTexture animation;
    private int width = 1500;
    private int height = 800;
    private int indexX = 0;
    private int indexY = 0;
    private boolean startAnimation = false;
    private boolean finishAnimation = false;
    public boolean isOpen = false;
    //Window
    private RenderWindow window;
    //Buy number
    private Text buyNumber = new Text("0", TDPictures.font, 30);
    private Text moneyGot = new Text("0", TDPictures.font, 25);
    //Sound flags of the buttons.
    private boolean sound1 = false;
    private boolean sound2 = false;
    private boolean sound3 = false;
    private boolean sound4 = false;

    //Only click once
    public boolean isClick = false;
    private int number = 0;
    public boolean clickSound = false;
    private boolean[] soundItems = new boolean[11];
    private Sprite itemContainer = new Sprite();
    //The function & price of each items
    private String itemDescription[] = new String[11];
    private String price[] = new String[11];
    private Text items = new Text("      No\n    Selection", TDPictures.font, 18);
    private Text priceText[] = new Text[11];
    private int money = 0;
    private int needMoney = 0;
    private int selectCost = 0;
    private int selectType = 0;
    //Close the
    private Text curMoney = new Text("20", staticVariable.rankFont, 20);




    public Shop(RenderWindow window)
    {
        this.window = window;
        this.buyNumber.setColor(Color.RED);
        items.setColor(Color.RED);
        items.setStyle(TextStyle.BOLD);
        animation = LoadPics.shopAnimation;

        this.moneyButton.setTexture(LoadPics.money);

        this.moneyButton.setOrigin(80, 41);
        this.shop.setTexture(animation);
        this.shop.setOrigin(750, 400);
        this.shop.setScale((float) 0.9, (float) 0.9);
        this.shop.setTextureRect(new IntRect(width * indexX, height * indexY, width, height));
        this.curMoney.setPosition(785, 510);
        this.curMoney.setColor(Color.RED);
        this.curMoney.setStyle(TextStyle.BOLD);
        loadImages();
    }

    public void loadImages()
    {
        setString();
        for(int i = 0; i < 11; i++)
        {
            soundItems[i] = false;
            equipments[i] = new Sprite();
            equipments[i].setTexture(staticVariable.items.get(i));
            equipments[i].setOrigin(40, 40);
            equipments[i].setScale((float) 0.6, (float) 0.6);
            priceText[i] = new Text(price[i], TDPictures.font, 10);
            priceText[i].setStyle(TextStyle.BOLD);
        }

        for(int i = 0; i < 3; i++)
        {
            add[i] = LoadPics.addButton.get(i);
            reduce[i] = LoadPics.reduceButton.get(i);
            buy[i] = LoadPics.buy.get(i);
        }
        this.addButton.setTexture(this.add[0]);
        this.reduceButton.setTexture(this.reduce[0]);
        this.buyButton.setTexture(this.buy[0]);
        this.addButton.setOrigin(80, 41);
        this.reduceButton.setOrigin(80, 41);
        this.buyButton.setOrigin(80, 41);
    }

    //Drawable if open the shop
    public void onDraw()
    {
        if(isOpen)
        {
            changIndex();
            this.shop.setTextureRect(new IntRect(width * indexX, height * indexY, width, height));
            this.shop.setPosition(700,300);
            this.window.draw(shop);
            //If animation finished
            if(finishAnimation)
            {
                mouseListener();
                itemListener();
                this.addButton.setPosition(945, 430);
                this.window.draw(addButton);
                this.reduceButton.setPosition(795, 430);
                this.window.draw(reduceButton);
                this.buyNumber.setPosition(865, 405);
                this.window.draw(buyNumber);
                this.moneyButton.setPosition(865, 380);
                this.window.draw(moneyButton);
                this.moneyGot.setPosition(885, 370);
                if(number == 0)
                    moneyGot.setString("0");
                this.window.draw(moneyGot);
                this.buyButton.setPosition(870 ,470);
                this.window.draw(buyButton);
                drawItems();
                this.itemContainer.setPosition(830, 210);
                this.window.draw(itemContainer);
                this.items.setPosition(800, 300);
                this.window.draw(items);

                String myMoney = String.valueOf(OperateDatabase.getMoney());
                this.curMoney.setString("Your Money: " + myMoney);
                this.window.draw(curMoney);
            }
        }
    }

    //Draw all the items in the shop
    public void drawItems()
    {
        int line = 0;
        int y = 250;
        int x = 0;
        for(int i = 0; i < 11; i++)
        {
            this.priceText[i].setPosition(410 + x * 82, y + 27);
            this.equipments[i].setPosition(410 + x * 82, y);
            this.window.draw(equipments[i]);
            this.window.draw(priceText[i]);
            x++;
            line++;
            if(line == 4)
            {
                y += 120;
                line = 0;
                x = 0;
            }
        }
    }

    public void mouseListener()
    {
        Mouse mouse = null;

        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;

        boolean isEnterAdd = Math.abs(mouseX - this.addButton.getPosition().x) < 30 &&
                Math.abs(mouseY - this.addButton.getPosition().y) < 30;
        boolean isEnterReduce = Math.abs(mouseX - this.reduceButton.getPosition().x) < 30 &&
                Math.abs(mouseY - this.reduceButton.getPosition().y) < 30;
        boolean isEnterBuy = Math.abs(mouseX - this.buyButton.getPosition().x) < 50 &&
                Math.abs(mouseY - this.buyButton.getPosition().y) < 50;
        boolean isEnterClose = Math.abs(mouseX - 1055) < 30 &&
                Math.abs(mouseY - 217) < 30;
        //If mouse enters
        if(isEnterAdd)
        {
            if(!sound1)
            {
                TDMusicPlay.buttonMusic.play();
                sound1 = true;
            }
            this.addButton.setTexture(add[1]);
        }
        else
        {
            sound1 = false;
            this.addButton.setTexture(add[0]);
        }

        if(isEnterReduce)
        {
            if(!sound2)
            {
                TDMusicPlay.buttonMusic.play();
                sound2 = true;
            }
            this.reduceButton.setTexture(reduce[1]);
        }
        else
        {
            sound2 = false;
            this.reduceButton.setTexture(reduce[0]);
        }

        if(isEnterBuy)
        {
            if(!sound3)
            {
                TDMusicPlay.buttonMusic.play();
                sound3 = true;
            }
            this.buyButton.setTexture(buy[1]);
        }
        else
        {
            sound3 = false;
            this.buyButton.setTexture(buy[0]);
        }

        if(isEnterClose)
        {
            if(!sound4)
            {
                TDMusicPlay.buttonMusic.play();
                sound4 = true;
            }
        }
        else
        {
            sound4 = false;
        }

        if(mouse.isButtonPressed(Mouse.Button.LEFT))
        {
            if(isEnterAdd && !isClick)
            {
                if(!clickSound)
                {
                    clickSound = true;
                    TDMusicPlay.selectMusic.play();
                }
                isClick = true;
                number++;
                buyNumber.setString(String.valueOf(number));
                moneyGot.setString(String.valueOf(number * selectCost));
            }

            if(isEnterReduce && !isClick)
            {
                if(!clickSound)
                {
                    clickSound = true;
                    TDMusicPlay.selectMusic.play();
                }
                isClick = true;
                if(number > 0)
                    number--;
                buyNumber.setString(String.valueOf(number));
                moneyGot.setString(String.valueOf(number * selectCost));
            }

            if(isEnterBuy && !isClick)
            {
                isClick = true;

                needMoney = number * selectCost;

                money = OperateDatabase.getMoney();

                boolean isEnough = money - needMoney >= 0;

                //Add purchase behaviors
                //Update the file, money...
                if(!clickSound && isEnough)
                {
                    clickSound = true;
                    TDMusicPlay.addMoney.play();
                    //Update the money
                    OperateDatabase.updateMoney(-needMoney);
                    OperateDatabase.updateItems(selectType, Math.abs(number), "Add");
                }
            }


            if(isEnterClose && !isClick)
            {
                isClick = true;
                if(!clickSound)
                {
                    clickSound = true;
                    TDMusicPlay.buildMusic.play();
                }
                isOpen = false;
                this.buyNumber.setString("0");
                this.needMoney = 0;
                this.selectType = 0;
                this.number = 0;
                this.selectCost = 0;
                this.startAnimation = false;
                this.finishAnimation = false;
                this.indexX = 0;
                this.indexY = 0;
            }
        }
    }


    public void itemListener()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;

        boolean isEnter[] = new boolean[11];

        for(int i = 0; i < 11; i++)
        {
            isEnter[i] = Math.abs(mouseX - this.equipments[i].getPosition().x) < 30 &&
                    Math.abs(mouseY - this.equipments[i].getPosition().y) < 30;

            //If enter the items, play the sounds
            if(isEnter[i])
            {
                if(!soundItems[i])
                {
                    TDMusicPlay.buttonMusic.play();
                    soundItems[i] = true;
                }
            }
            else if(!isEnter[i])
                soundItems[i] = false;

            if(isEnter[i] && mouse.isButtonPressed(Mouse.Button.LEFT) && !isClick)
            {
                if(!clickSound)
                {
                    clickSound = true;
                    TDMusicPlay.selectMusic.play();
                }
                isClick = true;
                selectCost = Integer.parseInt(price[i]);
                selectType = i + 1;
                //System.out.println(selectType);
                itemContainer.setTexture(staticVariable.items.get(i));
                items.setString(itemDescription[i]);
            }
        }
    }


    //Open animation of the shop
    public void changIndex()
    {
        if(!startAnimation)
        {
            startAnimation = true;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while (!finishAnimation)
                    {
                        indexX++;
                        if (indexX == 15000 / width)
                        {
                            indexX = 0;
                            indexY++;
                        }
                        if (indexX == 4 && indexY == 2)
                        {
                            finishAnimation = true;
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    private void setString()
    {
        itemDescription[0] = "Recover HP 80 \n   10 golds";
        itemDescription[1] = "Recover HP 120 \n   15 golds";
        itemDescription[2] = "Recover MP 20 \n   5 golds";
        itemDescription[3] = "Recover HP 40 \n   8 golds";
        itemDescription[4] = "Recover HP 50 \n   10 golds";
        itemDescription[5] = "Hero level add 1 \n   20 golds";
        itemDescription[6] = "Hero level add 2 \n   30 golds";
        itemDescription[7] = "Attack add 10 forever \n   30 golds";
        itemDescription[8] = "Attack add 20 forever \n   40 golds";
        itemDescription[9] = "MP add 30 forever \n   30 golds";
        itemDescription[10] = "HP add 50 forever \n   30 golds";

        price[0] = "10";
        price[1] = "15";
        price[2] = "5";
        price[3] = "8";
        price[4] = "10";
        price[5] = "20";
        price[6] = "30";
        price[7] = "30";
        price[8] = "40";
        price[9] = "30";
        price[10] = "30";
    }
}
