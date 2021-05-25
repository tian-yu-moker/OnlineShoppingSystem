package Shop;

import GUI.FinalPanel;
import GUI.LevelChoose;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;


public class TestMain
{
    RenderWindow window;
    FinalPanel shop;// = new LevelChoose(0);
    public TestMain()
    {
        window = new RenderWindow(new VideoMode(1500, 800), "Warriors Expedition", Window.DEFAULT);
        window.setPosition(new Vector2i(0,0));
        window.setFramerateLimit(60);

        shop = new FinalPanel(window);
        //shop = new Shop(window);
        //shop.isOpen = true;
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
                    // shop.isClick = false;
                    // shop.clickSound = false;
                }
            }
            window.clear(Color.WHITE);
            Mouse mouse = null;
            shop.onDraw();
            //shop.Level(window, mouse);
            window.display();
        }
    }
}
