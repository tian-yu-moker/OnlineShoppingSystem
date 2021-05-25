package TowerDenfense;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;


public class TD_Maps
{
    public Sprite container = new Sprite();
    public RenderWindow window;

    public TD_Maps(RenderWindow window, int level)
    {
        this.window = window;
        if(level == 2)
            container.setTexture(TDPictures.map.get(0));
        else if(level == 6)
            container.setTexture(TDPictures.map.get(1));
        container.setPosition(0, 0);
    }


    public void onDraw()
    {
        window.draw(container);
    }
}
