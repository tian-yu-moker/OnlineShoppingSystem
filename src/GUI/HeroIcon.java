package GUI;

import GameDriver.staticVariable;
import TowerDenfense.TDPictures;
import org.jsfml.graphics.*;

public class HeroIcon
{
    private Sprite hero = new Sprite();
    private Text name = new Text("", TDPictures.font, 28);
    private RenderWindow window;

    public HeroIcon(String type, RenderWindow window)
    {
        this.window = window;
        name.setStyle(TextStyle.BOLD);
        name.setColor(Color.BLACK);
        if(type.equals("Saber"))
        {
            name.setString("Costantine");
            hero.setTexture(staticVariable.heroIcons.get(1));
        }
        else if(type.equals("Lancer"))
        {
            name.setString("Palandin");
            hero.setTexture(staticVariable.heroIcons.get(0));
        }
        else if(type.equals("Knight"))
        {
            name.setString("Swordman");
            hero.setTexture(staticVariable.heroIcons.get(2));
        }
    }

    public void onDraw()
    {
        this.hero.setPosition(33, 635);
        this.name.setPosition(195, 666);
        window.draw(this.hero);
        window.draw(name);
    }
}
