package GameStartPanel;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

import java.io.IOException;

public class Test {
    //public static Sound backMusic = Start.startMusic("/Images/optionIF/music.wav");

    public static void main(String[] args) throws IOException {
        RenderWindow window = new RenderWindow(new VideoMode(1500, 800), "Warriors Expedtion");
        window.setFramerateLimit(30); //设置窗口的刷新率——这个值小了的话会不是很流畅

        //RectangleShape gameBG_RS = Start.createImage("Images/gameIF/gameBG.png", 1450, 800, 0, 0);


        A_Start s = new A_Start();
        //C_LoginAndRegister lr = new C_LoginAndRegister();
        //LevelChoose lc = new LevelChoose();
        //Victory v = new Victory();
        //Shop s =new Shop();
        //StartAnima sa = new StartAnima();
        //D_ChaChoose cc = new D_ChaChoose();
        //IntroLevel il =new IntroLevel();
        //B_Option o = new B_Option();

        while (window.isOpen()) {
            if (Keyboard.isKeyPressed(org.jsfml.window.Keyboard.Key.ESCAPE)){
                window.close();
            }
            for (org.jsfml.window.event.Event event : window.pollEvents()) {//遍历所有在这个window上发生的事件
                if (event.type == Event.Type.CLOSED)
                    window.close();
            }
            //window.draw(gameBG_RS);

            //lr.Create();
            s.run(window);
            //lc.run(window, mouse, keyboard);
            //v.victory(window, mouse,5);
            //s.shop(window, mouse);
            //sa.run(window);
            //cc.run(window);
            //il.BgAniStart(window);
            //o.run(window);



            window.display();
        } //

    }


}
