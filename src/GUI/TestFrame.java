package GUI;

import Shop.Shop;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

public class TestFrame
{
    RenderWindow window;
    Mouse mouse = null;
    Victory victory = new Victory();
    public TestFrame()
    {
        window = new RenderWindow(new VideoMode(1500, 800), "Warriors Expedition", Window.DEFAULT);
        window.setPosition(new Vector2i(0,0));
        window.setFramerateLimit(30);
        mainLoop();
    }

    public void mainLoop()
    {
        while (window.isOpen())
        {
            for (Event event : window.pollEvents())
            {
                if (event.type == Event.Type.CLOSED)
                {
                    window.close();
                    System.exit(0);
                    break;
                }
                if (event.type == Event.Type.MOUSE_BUTTON_RELEASED)
                {

                }
            }
            window.clear(Color.WHITE);
            victory.victory(window, mouse, 1);
            window.display();
        }
    }
}
