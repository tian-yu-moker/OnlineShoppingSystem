package Actors;

import GameDriver.staticVariable;

public class Background extends Actors
{
    //场景顺序
    public int sort;
    //是否为最后一个场景
    public boolean flag;
    public Background(int sort,int x,int y)
    {
        this.sort=sort;
        this.x=x;
        this.y=y;
        if(sort==1)
        {
            this.width = 7168;
            this.height = 5120;
            // shownPic=staticVariable.allBackgrounds.get(0);
            this.container.setTexture(staticVariable.allBackgrounds.get(0));
        }
        else if(sort==3)
        {
            this.width = 5228;
            this.height = 3584;
            //shownPic=staticVariable.allBackgrounds.get(2);
            this.container.setTexture(staticVariable.allBackgrounds.get(1));
        }
        else if(sort==4)
        {
            this.width = 5000;
            this.height = 5000;
            //shownPic=staticVariable.allBackgrounds.get(3);
            this.container.setTexture(staticVariable.allBackgrounds.get(2));
        }
        else if(sort==5)
        {
            this.width = 5376;
            this.height = 5634;
            //shownPic=staticVariable.allBackgrounds.get(3);
            this.container.setTexture(staticVariable.allBackgrounds.get(3));
        }
        else if(sort==7)
        {
            this.width = 9000;
            this.height = 9000;
            //shownPic=staticVariable.allBackgrounds.get(1);
            this.container.setTexture(staticVariable.allBackgrounds.get(4));
        }
        else if(sort==8)
        {
            this.width = 6600;
            this.height = 6400;
            //shownPic=staticVariable.allBackgrounds.get(1);
            this.container.setTexture(staticVariable.allBackgrounds.get(5));
        }
        else if(sort==9)
        {
            this.width = 5400;
            this.height = 4500;
            //shownPic=staticVariable.allBackgrounds.get(1);
            this.container.setTexture(staticVariable.allBackgrounds.get(6));
        }
        else if(sort==10)
        {
            this.width = 5632;
            this.height = 5120;
            //shownPic=staticVariable.allBackgrounds.get(1);
            this.container.setTexture(staticVariable.allBackgrounds.get(7));
        }
        else if(sort == -1)
            this.container.setTexture(staticVariable.covers.get(0));
        else if(sort == -3)
            this.container.setTexture(staticVariable.covers.get(1));
        else if(sort == -4)
            this.container.setTexture(staticVariable.covers.get(2));
        else if(sort == -5)
            this.container.setTexture(staticVariable.covers.get(3));
        else if(sort == -7)
            this.container.setTexture(staticVariable.covers.get(4));
        else if(sort == -8)
            this.container.setTexture(staticVariable.covers.get(5));
        else if(sort == -9)
            this.container.setTexture(staticVariable.covers.get(6));
        else if(sort == -10)
            this.container.setTexture(staticVariable.covers.get(7));
    }


    @Override
    public void moveX(String state) {
        // TODO Auto-generated method stub

    }
    @Override
    public void moveY(String state) {
        // TODO Auto-generated method stub

    }
    @Override
    public void controller() {
        // TODO Auto-generated method stub

    }



}