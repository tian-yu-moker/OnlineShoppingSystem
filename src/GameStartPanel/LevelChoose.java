package GameStartPanel;

import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Mouse;

public class LevelChoose
{
    public static int passedLevelNum=0;
    //以下为levelChoo界面的背景图及所有部件导入
    //添加levelChoo背景图片
    RectangleShape mapBase_RS = A_Start.createImage("Images/levelChooIF/mapBase.png", 1500, 800, 0, 0);
    //levelChoo背景图片上显示的关卡地图
    RectangleShape[] level_RS=new RectangleShape[10];
    RectangleShape[] levelL_RS=new RectangleShape[10];
    RectangleShape[] levelNa_RS=new RectangleShape[10];
    RectangleShape[] levelNaL_RS=new RectangleShape[10];

    public LevelChoose(){
        for(int i=0;i<10;i++)
        {
            //levelChoo背景图片上显示的关卡地图
            int b=i+1;
            level_RS[i]= A_Start.createImage("Images/levelChooIF/"+b+".png", 1135, 710, 185, 60);
            levelL_RS[i] = A_Start.createImage("Images/levelChooIF/"+b+"light.png", 1135, 710, 185, 60);
            levelNa_RS[i] = A_Start.createImag("Images/levelChooIF/level"+b+"Na.png", 300, 100);
            levelNaL_RS[i] = A_Start.createImag("Images/levelChooIF/level"+b+"NaLight.png", 300, 100);
        }
    }


    public void run(RenderWindow window){
        //鼠标
        Vector2i position = Mouse.getPosition(window);//得到鼠标光标当前位置（坐标向量），格式如 Vector2i{x=324, y=326}
        int x = position.x;//获取当前鼠标的横坐标
        int y = position.y;//获取当前鼠标的纵坐标

        //levelChoo界面//  250, 100, 265, 500);//  250, 100, 345, 420);//  250, 100, 330, 310);//  250, 100, 435, 150);//  250, 100, 575, 290);//  250, 100, 635, 380);//  250, 100, 530, 530);//  250, 100, 720, 530);//  250, 100, 885, 430);//  250, 100, 870, 160)
        boolean level1Area = x>=265 && x<=510 && y>=525 && y<=600; //250, 100
        boolean level2Area = x>=345 && x<=600 && y>=420 && y<=520;
        boolean level3Area = x>=330 && x<=580 && y>=310 && y<=410;
        boolean level4Area = x>=435 && x<=850 && y>=150 && y<=250;
        boolean level5Area = x>=580 && x<=830 && y>=290 && y<=390;
        boolean level6Area = x>=635 && x<=890 && y>=380 && y<=480;
        boolean level7Area = x>=530 && x<=780 && y>=530 && y<=630;
        boolean level8Area = x>=720 && x<=970 && y>=530 && y<=630;
        boolean level9Area = x>=895 && x<=1130 && y>=400 && y<=530;
        boolean level10Area = x>=870 && x<=1120 && y>=160 && y<=360;

        levelNa_RS[0].setPosition(265, 500);
        levelNa_RS[1].setPosition(345, 420);
        levelNa_RS[2].setPosition(330, 310);
        levelNa_RS[3].setPosition(435, 150);
        levelNa_RS[4].setPosition(575, 290);
        levelNa_RS[5].setPosition(635, 380);
        levelNa_RS[6].setPosition(530, 530);
        levelNa_RS[7].setPosition(720, 530);
        levelNa_RS[8].setPosition(885, 430);
        levelNa_RS[9].setPosition(870, 160);

        levelNaL_RS[0].setPosition(265, 500);
        levelNaL_RS[1].setPosition(345, 420);
        levelNaL_RS[2].setPosition(330, 310);
        levelNaL_RS[3].setPosition(435, 150);
        levelNaL_RS[4].setPosition(575, 290);
        levelNaL_RS[5].setPosition(635, 380);
        levelNaL_RS[6].setPosition(530, 530);
        levelNaL_RS[7].setPosition(720, 530);
        levelNaL_RS[8].setPosition(885, 430);
        levelNaL_RS[9].setPosition(870, 160);

        window.draw(mapBase_RS);
        if(level1Area && passedLevelNum==0){window.draw(levelL_RS[0]);}
        else{window.draw(level_RS[0]);}

        //Text e = creatText("DDDDD",50, Color.RED,20,300);
        //window.draw(e);

        if( Keyboard.isKeyPressed(org.jsfml.window.Keyboard.Key.A) ){
            if(passedLevelNum==0){passedLevelNum =1;}
            else if(passedLevelNum ==1){passedLevelNum =2;}
            else if(passedLevelNum ==2){passedLevelNum =3;}
            else if(passedLevelNum ==3){passedLevelNum =4;}
            else if(passedLevelNum ==4){passedLevelNum =5;}
            else if(passedLevelNum ==5){passedLevelNum =6;}
            else if(passedLevelNum ==6){passedLevelNum =7;}
            else if(passedLevelNum ==7){passedLevelNum =8;}
            else if(passedLevelNum ==8){passedLevelNum =9;}
        }

        if(passedLevelNum == 1){
            System.out.println("num = 1" );
            if(level2Area){window.draw(levelL_RS[1]);}
            else{window.draw(level_RS[1]);}
        }
        else if(passedLevelNum == 2){
            System.out.println("num = 2" );
            if(level3Area){window.draw(levelL_RS[2]);}
            else{window.draw(level_RS[2]);}
        }
        else if(passedLevelNum == 3){
            if(level4Area){window.draw(levelL_RS[3]);}
            else{window.draw(level_RS[3]);}
            System.out.println("num = 3" );
        }
        else if(passedLevelNum == 4){
            if(level5Area){window.draw(levelL_RS[4]);}
            else{window.draw(level_RS[4]);}
            System.out.println("num = 4" );
        }
        else if(passedLevelNum == 5){
            if(level6Area){window.draw(levelL_RS[5]);}
            else{window.draw(level_RS[5]);}
            System.out.println("num = 5" );
        }
        else if(passedLevelNum == 6){
            if(level7Area){window.draw(levelL_RS[6]);}
            else{window.draw(level_RS[6]);}
            System.out.println("num = 6" );
        }
        else if(passedLevelNum == 7){
            if(level8Area){window.draw(levelL_RS[7]);}
            else{window.draw(level_RS[7]);}
            System.out.println("num = 7" );
        }
        else if(passedLevelNum == 8){
            if(level9Area){window.draw(levelL_RS[8]);}
            else{window.draw(level_RS[8]);}
            System.out.println("num = 8" );}
        else if(passedLevelNum == 9){
            if(level10Area){window.draw(levelL_RS[9]);}
            else{window.draw(level_RS[9]);}
            System.out.println("num = 9" );
        }



        if(level1Area){window.draw(levelNaL_RS[0]);}
        else { window.draw(levelNa_RS[0]);}

        if(level2Area){window.draw(levelNaL_RS[1]);}
        else {window.draw(levelNa_RS[1]);}

        if(level3Area){window.draw(levelNaL_RS[2]);}
        else {window.draw(levelNa_RS[2]);}

        if(level4Area){window.draw(levelNaL_RS[3]);}
        else {window.draw(levelNa_RS[3]);}

        if(level5Area){window.draw(levelNaL_RS[4]);}
        else { window.draw(levelNa_RS[4]);}

        if(level6Area){window.draw(levelNaL_RS[5]);}
        else { window.draw(levelNa_RS[5]);}

        if(level7Area){window.draw(levelNaL_RS[6]);}
        else {window.draw(levelNa_RS[6]);}

        if(level8Area){window.draw(levelNaL_RS[7]);}
        else {window.draw(levelNa_RS[7]);}

        if(level9Area){window.draw(levelNaL_RS[8]);}
        else { window.draw(levelNa_RS[8]);}

        if(level10Area){window.draw(levelNaL_RS[9]);}
        else {window.draw(levelNa_RS[9]);}
    }

}/**/
