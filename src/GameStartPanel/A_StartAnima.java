package GameStartPanel;

import org.jsfml.graphics.IntRect;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;

public class A_StartAnima {
    public static int start1IndexX, start1IndexY=0;
    public static int start2IndexX, start2IndexY=0;
    public static int start3IndexX, start3IndexY=0;
    public static int start4IndexX, start4IndexY=0;
    public static int start5IndexX, start5IndexY=0;
    public static int start6IndexX, start6IndexY=0;
    public static int start7IndexX, start7IndexY=0;
    public static int start8IndexX, start8IndexY=0;
    //boolean start1=true;//是否开始播放第一张图片
    //boolean start2=false;//是否开始播放第二张图片
    boolean start3=true;//是否开始播放第三张图片
    boolean start4=false;//是否开始播放第四张图片
    boolean start5=false;//是否开始播放第五张图片
    boolean start6=false;//是否开始播放第六张图片
    boolean start7=false;//是否开始播放第七张图片
    boolean start8=false;//是否开始播放第八张图片

    Sprite[] sp= new Sprite[8];

    public A_StartAnima()
    {
        for(int i=2;i<8;i++)
        {
            int b=i+1;
            sp[i]= A_Start.createSp("/Images/gameIF/"+b+".jpg", 0,0,1f,1f);
        }
    }


    public void run(RenderWindow window)
    {

        if(start3){ //animation4(window);
            start3IndexX++;
            if(start3IndexX==9 && start3IndexY!=3){
                start3IndexX=0;
                start3IndexY++;
            }
            if(start3IndexX==9 && start3IndexY==3){
                //start3IndexX--;
                start3=false;
                start4=true;
            }
            sp[2].setTextureRect(new IntRect(1500*start3IndexX, 800*start3IndexY, 1500, 800));
            //a.clear();
            window.draw(sp[2]);
        }
        if(start4){ //animation5(window);
            start4IndexX++;
            if(start4IndexX==9 && start4IndexY!=3){
                start4IndexX=0;
                start4IndexY++;
            }
            if(start4IndexX==9 && start4IndexY==3){
                //start4IndexX--;
                start4=false;
                start5=true;
            }
            sp[3].setTextureRect(new IntRect(1500*start4IndexX, 800*start4IndexY, 1500, 800));
            //a.clear();
            window.draw(sp[3]);
        }
        if(start5){ //animation6(window);
            start5IndexX++;
            if(start5IndexX==9 && start5IndexY!=3){
                start5IndexX=0;
                start5IndexY++;
            }
            if(start5IndexX==9 && start5IndexY==3){
                //start5IndexX--;
                start5=false;
                start6=true;
                //System.out.println("5");
            }
            sp[4].setTextureRect(new IntRect(1500*start5IndexX, 800*start5IndexY, 1500, 800));
            //a.clear();
            window.draw(sp[4]);
        }
        if(start6){ //animation7(window);
            start6IndexX++;
            if(start6IndexX==9 && start6IndexY!=3){
                start6IndexX=0;
                start6IndexY++;
            }
            if(start6IndexX==9 && start6IndexY==3){
                //start6IndexX--;
                start6=false;
                start7=true;
            }
            sp[5].setTextureRect(new IntRect(1500*start6IndexX, 800*start6IndexY, 1500, 800));
            //a.clear();
            window.draw(sp[5]);
        }
        if(start7){ //animation8(window);
            start7IndexX++;
            if(start7IndexX==9 && start7IndexY!=3){
                start7IndexX=0;
                start7IndexY++;
            }
            if(start7IndexX==9 && start7IndexY==3){
                //start7IndexX--;
                start7=false;
                start8=true;
            }
            sp[6].setTextureRect(new IntRect(1500*start7IndexX, 800*start7IndexY, 1500, 800));
            //a.clear();
            window.draw(sp[6]);
        }/**/
        if(start8){
            start8IndexX++;
            if(start8IndexX==9 && start8IndexY!=1){
                start8IndexX=0;
                start8IndexY++;
            }
            if(start8IndexX==9 && start8IndexY==1){
                //start8IndexX--;
                start8=false;
                A_Start.gameOpen=true;
            }
            sp[7].setTextureRect(new IntRect(1500*start8IndexX, 800*start8IndexY, 1500, 800));
            window.draw(sp[7]);
        }
    }

    /*if(start1){
            start1IndexX++;
            if (start1IndexX == 9 && start1IndexY != 3) {
                start1IndexX = 0;
                start1IndexY++;
                System.out.println("11111111111111");
            }
            if (start1IndexX == 9 && start1IndexY == 3) {
                start1=false;
                start2 = true;
                System.out.println("1");
            }
            sp[0].setTextureRect(new IntRect(1500 * start1IndexX, 800 * start1IndexY, 1500, 800));
            window.draw(sp[0]);
        }*/
        /*if(start2){ //animation3(window);
            start2IndexX++;
            if(start2IndexX==9 && start2IndexY!=3){
                start2IndexX=0;
                start2IndexY++;
                System.out.println("22222222222222222");
            }
            if(start2IndexX==9 && start2IndexY==3){
                //start2IndexX--;
                start2=false;
                start3=true;
                System.out.println("2");
            }
            sp[1].setTextureRect(new IntRect(1500*start2IndexX, 800*start2IndexY, 1500, 800));
            window.draw(sp[1]);
        }*/

}
