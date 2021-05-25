package GUI;

/*
Mouse point in the game
 */

import GameDriver.staticVariable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.window.Mouse;

public class WindowMouse
{
    private Sprite container = new Sprite();
    private RenderWindow window;

    public WindowMouse(RenderWindow window)
    {
        this.window = window;
        container.setTexture(staticVariable.mousePic);
        container.setOrigin(7, 3);
    }

    public void onDraw()
    {
        Mouse mouse = null;
        container.setPosition(mouse.getPosition(window).x, mouse.getPosition(window).y);
        window.draw(container);
    }
}
