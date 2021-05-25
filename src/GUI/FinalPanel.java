package GUI;

/*
This class is used to summarize the behavior of the player at the end of the game
With 3 different endings
And the rank of scores (stars) for all the players get
 */

import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.*;
import org.jsfml.window.Mouse;

import java.util.List;

public class FinalPanel
{
    private Sprite container1 = new Sprite();
    private Sprite container2 = new Sprite();
    private Sprite container3 = new Sprite();
    private Sprite container4 = new Sprite();

    private Sprite button = new Sprite();
    private RenderWindow window;
    private boolean isMusicPlayed = false;
    private int picNum = 1;
    private boolean enterSound = false;
    private boolean clickSound = false;

    //private
    private Text rankName = new Text("", staticVariable.rankFont, 25);
    private Text rankHero = new Text("", staticVariable.rankFont, 25);
    private Text rankStars = new Text("", staticVariable.rankFont, 25);

    public FinalPanel(RenderWindow window)
    {
        this.window = window;
        this.container1.setTexture(staticVariable.finalPanel.get(0));
        this.container1.setPosition(0, 0);
        this.container3.setTexture(staticVariable.finalPanel.get(4));
        this.container4.setTexture(staticVariable.finalPanel.get(5));
        container3.setPosition(0, 0);

        this.button.setTexture(staticVariable.buttons.get(0));
        this.button.setOrigin(173 /2 , 189 / 2);
        this.button.setScale((float) 0.8, (float) 0.8);
        this.button.setPosition(1350, 720);
        rankName.setStyle(TextStyle.BOLD);
        rankName.setColor(Color.BLACK);
        rankHero.setStyle(TextStyle.BOLD);
        rankHero.setColor(Color.BLACK);
        rankStars.setStyle(TextStyle.BOLD);
        rankStars.setColor(Color.BLACK);
        setContainers(42);
    }

    public void setContainers(int stars)
    {
        if(stars > 40)
            container2.setTexture(staticVariable.finalPanel.get(1));
        else if(stars > 30 && stars <= 40)
            container2.setTexture(staticVariable.finalPanel.get(2));
        else if(stars <= 30)
            container2.setTexture(staticVariable.finalPanel.get(3));
        container2.setPosition(0, 0);
    }

    public void setTexts(List info)
    {
        rankName.setString(info.get(0).toString());
        rankHero.setString(info.get(1).toString());
        rankStars.setString(info.get(2).toString());
    }

    public void onDraw()
    {
        if(!isMusicPlayed)
        {
            TDMusicPlay.endingMusic.setVolume(10.0f);
            TDMusicPlay.endingMusic.play();
            isMusicPlayed = true;
        }
        mouseListener();
        //Description
        if(picNum == 1)
            window.draw(container1);
            //Different endings
        else if(picNum == 2)
            window.draw(container2);
            //Rank info
        else if(picNum == 3)
        {
            window.draw(container3);
            rankName.setPosition(490, 210);
            rankHero.setPosition(730, 210);
            rankStars.setPosition(970, 210);
            /*rankName.setString("Tian Yu\nXia Runze\nXiao Wenwei\nLiu Jiayi\nZhang Yunqi");
            rankHero.setString("Lancer\nKinght\nConstantine\nConstantine\nLancer");
            rankStars.setString("50\n49\n47\n46\n45");*/
            window.draw(rankName);
            window.draw(rankHero);
            window.draw(rankStars);
        }
        //The end panel
        else if(picNum == 4)
            window.draw(container4);
        else if(picNum == 5)
            System.exit(0);
        window.draw(button);
    }

    public void mouseListener()
    {
        Mouse mouse = null;
        int mouseX = mouse.getPosition(window).x;
        int mouseY = mouse.getPosition(window).y;
        boolean isEnter = Math.abs(mouseX - button.getPosition().x) <= 50 &&
                Math.abs(mouseY - button.getPosition().y) <= 50;
        //Enter feedback
        if(isEnter)
        {
            if(!enterSound)
            {
                enterSound = true;
                TDMusicPlay.buttonMusic.play();
            }
            this.button.setTexture(staticVariable.buttons.get(1));
        }
        else if(!isEnter)
        {
            enterSound = false;
            this.button.setTexture(staticVariable.buttons.get(0));
        }

        if(!mouse.isButtonPressed(Mouse.Button.LEFT))
            clickSound = false;
        if(isEnter && mouse.isButtonPressed(Mouse.Button.LEFT))
        {
            //Click feedback
            if(!clickSound)
            {
                clickSound = true;
                TDMusicPlay.clickSound.play();
                picNum++;
            }
        }
    }
}
