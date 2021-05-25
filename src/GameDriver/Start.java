package GameDriver;

import GUI.LevelChoose;
import org.jsfml.audio.Music;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.*;
import java.io.IOException;
import java.nio.file.Paths;
import org.jsfml.graphics.*;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Texture;
import org.jsfml.graphics.IntRect;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;


public class Start {
    public static boolean gameOpen = false;//truegame����Ŀ���״̬��trueΪ����falseΪ��
    public static boolean optionOpen = false;//option����Ŀ���״̬��trueΪ����falseΪ��
    public static boolean loginRegisterOpen = false;//login&register��������Ŀ���״̬��trueΪ����falseΪ��
    public static boolean LROpen = true;   //��¼&ע���ӽ���Ŀ���
    public static boolean chaChooOpen = false;//character choose����Ŀ���״̬��trueΪ����falseΪ��
    //���ݵ�ǰ��ʾ�Ľ�ɫ������Ϣ���жϲ��ŵ���ƵƬ��
    public static boolean consAbbrOpen = false;
    public static boolean palaAbbrOpen = false;
    public static boolean swordAbbrOpen = false;
    public static boolean consDetailOpen = false;
    public static boolean palaDetailOpen = false;
    public static boolean swordDetailOpen = false;
    public static boolean archMusicOpen = true;
    public static boolean consMusicOpen = false;
    public static boolean palaMusicOpen = false;
    public static boolean swordMusicOpen = false;
    public static boolean levelChooOpen = true;

    public static int start1IndexX=0; public static int start1IndexY=0;
    public static int start2IndexX=0; public static int start2IndexY=0;
    public static int start3IndexX=0; public static int start3IndexY=0;
    public static int start4IndexX=0; public static int start4IndexY=0;
    public static int start5IndexX=0; public static int start5IndexY=0;
    public static int start6IndexX=0; public static int start6IndexY=0;
    public static int start7IndexX=0; public static int start7IndexY=0;
    public static int start8IndexX=0; public static int start8IndexY=0;


    public static RectangleShape createImage(String image, int Image_x, int Image_y, int position_x, int position_y){
        //���ر���ͼƬ: ��Ϸ��ʼ����ͼ
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(image));//�ӱ��ؼ���ͼƬ
        } catch (IOException e) {
            e.printStackTrace(); }
        imgTexture.setSmooth(true); //һ��Ҫ�ӣ�����ǿ���ݵķ�����Ҳ���ǽ�ͼƬ���ƽ���ķ���

        //����������Ϸ��ʼ����ͼͼƬ�� ����ͼƬ��������СЭ��������ͼƬ����ʾ��С�ĳ��Ϳ�
        RectangleShape img = new RectangleShape(new Vector2f(Image_x, Image_y));
        img.setTexture(imgTexture);
        img.setPosition(position_x, position_y); //����������������꣬���Ͻ�Ϊ��׼
        return img;

    }

    public static Text creatText( String content, int fontSize, Color c, int text_x, int text_y){
        Font font=new Font();
        try {
            font.loadFromFile(Paths.get("font/1000hurt.ttf"));//String fontType,
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Text text = new Text (content, font,fontSize);//���ݣ��������С
        text.setColor(c);//���õ�ǰ�ı���������ɫΪ͸��TRANSPARENT
        text.setPosition(text_x, text_y);//�����ı����λ��
        return text;
    }

    public static Sprite createSp(String image, int a1, int a2, float x1, float x2){
        String imagePath = System.getProperty("user.dir");
        Texture imgTexture= new Texture();
        try {
            imgTexture.loadFromFile(Paths.get(imagePath+image));//�ӱ��ؼ���ͼƬ
        } catch (IOException e) {
            e.printStackTrace(); }
        imgTexture.setSmooth(true); //һ��Ҫ�ӣ�����ǿ���ݵķ�����Ҳ���ǽ�ͼƬ���ƽ���ķ���

        Sprite Sp = new Sprite();
        Sp.setTexture(imgTexture);
        //Sp.setOrigin(0,0);  //20 150
        Sp.setPosition(a1,a2); //����������������꣬���Ͻ�Ϊ��׼ 270, 10
        Sp.setScale(x1, x2);//���������ţ��ⲻ��ı�ͼƬ�Ĵ�С��ʹ����ԭ����Ϊ�������ġ�
        return Sp;
    }

    public static Music music(String musicPath) throws IOException {
        Music music = new Music();
        music.openFromFile(Paths.get(musicPath));
        return music;
    }

    public static void vodio()
    {

    }
}