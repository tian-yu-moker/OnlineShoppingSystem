package GUI;

/*
This class aims to process the conditions that the player is dead
As long as the player is dead, stop all the behaviours and draw the game over panel
*/

import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.window.Mouse;


public class Gameover
{
    //For other variables
    private RenderWindow window;
    //The main panel
    private ConstTexture mainPic;
    private Sprite mainPanel;
    private ConstTexture textPic;
    private Sprite textContainer = new Sprite();
    private ConstTexture[] pics = new ConstTexture[4];
    private Sprite[] containers = new Sprite[2];
    private int width;
    private int height;
    private int indexX;
    private int indexY;
    private int animation = 0;
    private boolean animationFinished = false;
    /****Determine whether the player restarts the current level****/
    private boolean isRestarted = false;
    private boolean isDraw = false;
    private boolean flag = false;
    public boolean shopOpen = false;
    private boolean sound1 = false;
    private boolean sound2 = false;

    public Gameover(RenderWindow window)
    {
        //Initialize the variables
        this.window = window;
        mainPic = staticVariable.overPanel;
        textPic = staticVariable.overText;
        mainPanel = new Sprite();
        mainPanel.setTexture(this.mainPic);
        textContainer.setTexture(this.textPic);
        mainPanel.setOrigin(750, 400);
        textContainer.setOrigin(this.textPic.getSize().x / 2, this.textPic.getSize().y / 2);
        mainPanel.scale((float) 1.3, (float) 1.2);
        this.width = 1500;
        this.height = 800;
        this.indexX = 0;
        this.indexY = 0;
        mainPanel.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        this.loadPics();
    }

    public void loadPics()
    {
        for(int i = 0; i < 4; i++)
            pics[i] = staticVariable.overButtons.get(i);
        for(int i = 0; i < 2; i++)
            containers[i] = new Sprite();
        containers[0].setTexture(pics[0]);
        containers[0].setOrigin(pics[0].getSize().x / 2, pics[0].getSize().y / 2);
        containers[1].setTexture(pics[2]);
        containers[1].setOrigin(pics[1].getSize().x / 2, pics[1].getSize().y / 2);

        containers[0].setScale((float) 0.8, (float) 0.8);
        containers[1].setScale((float) 1.1, (float) 1.1);

    }

    //Draw the panel and components on the screen
    public void onDraw(boolean isDead)
    {
        this.isDraw = isDead;
        if(isDraw && !shopOpen)
        {
            mouseListener();
            changeIndex();
            mainPanel.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
            mainPanel.setPosition(700,350);
            textContainer.setPosition(700, 250);
            this.window.draw(mainPanel);
            if(animationFinished)
            {
                this.window.draw(textContainer);
                for(int i = 0; i < 2; i++)
                {
                    containers[i].setPosition(590 + i * 230, 450);
                    listener(i);
                    this.window.draw(containers[i]);
                }
            }
        }
    }

    //The mouse listener of the game.
    public void mouseListener()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        if(mouse.isButtonPressed(Mouse.Button.LEFT))
            flag = true;
    }

    //The mouse listener of the game over panel
    public void listener(int i)
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        boolean isEnter = Math.abs(containers[i].getPosition().x - mouseX) <=30
                && Math.abs(containers[i].getPosition().y - mouseY) <= 30;
        if(isEnter)
        {
            this.containers[i].setTexture(this.pics[i * 2 + 1]);
            if(i == 0)
            {
                if(!sound1)
                {
                    sound1 = true;
                    TDMusicPlay.buttonMusic.play();
                }
            }
            if(i == 1)
            {
                if(!sound2)
                {
                    sound2 = true;
                    TDMusicPlay.buttonMusic.play();
                }
            }
        }
        else if(!isEnter)
        {
            this.containers[i].setTexture(this.pics[i * 2]);
            if(i == 0)
                sound1 = false;
            if(i == 1)
                sound2 = false;
        }
        if(isEnter && mouse.isButtonPressed(Mouse.Button.LEFT))
        {
            //Back to map
            if(i == 0)
            {
                this.shopOpen = true;
            }
            //Replay this level
            else if(i == 1)
            {
                animationFinished = false;
                animation = 0;
                isRestarted = true;
            }
        }
    }

    //Change the index of the animation.
    public void changeIndex()
    {
        if(animation == 0)
        {
            animation++;
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    while(true)
                    {
                        indexX++;
                        if(indexX == 15000 / width)
                        {
                            indexX = 0;
                            indexY++;
                        }

                        if(indexX == 4 && indexY == 2)
                        {
                            indexX--;
                            break;
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    animationFinished = true;
                }
            }).start();
        }
    }

    public void setWindow(RenderWindow window)
    {
        this.window = window;
    }

    public boolean getRestart()
    {
        return isRestarted;
    }

    public void setRestart()
    {
        this.isRestarted = false;
    }
}
