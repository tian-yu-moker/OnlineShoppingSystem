package Actors;

import GameDriver.Musiccall;
import GameDriver.staticVariable;

import javax.lang.model.element.Element;

import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Enemies extends Actors implements Runnable
{
    private Thread start;
    //??????????
    public int distanceX;
    public int distanceY;
    //For the type of enemies. E.g. 100*100->type = 1
    private int size = 0;
    //Type of enemies in each size. E.g. size = 2, type = 1--->One type of enemy.
    public int type = 0;
    //Category of the enemy, close, far and boss.
    private String category;
    //控制受伤和死亡的换图
    public boolean isHurtFinish;
    public boolean isDeadFinish;
    //Movement range, decided by type of enemy.
    private int rightLimit = 0;
    private int leftLimit = 0;
    private int upLimit = 0;
    private int downLimit = 0;
    //控制移动方向
    public boolean isLeft = true;
    public boolean isRight = true;
    public boolean isUp = true;
    public boolean isDown = true;
    public boolean isHurtEffectExist;
    private boolean isEnd = false;
    public TDMusicPlay tdMusicPlay;
    public Musiccall musica;
    public List<HittingEffect> allHittingEffect=new ArrayList<HittingEffect>();
    private static Semaphore mutex = new Semaphore(1);
    /*
    The primary state: moving randomly
    The primary direction: right
     */

    public Enemies(int x, int y,int MoveDirection,int leftLimit,int rightLimit,int upLimit, int downLimit,int type, String category,int stateNumber)
    {
        //Type of enemy.
        this.type = type;
        this.category = category;
        this.direction = "left";
        this.x = x;
        this.y = y;
        //??????????
        this.distanceX=x;
        this.distanceY=y;
            if(type==1){
                this.width = 500;
                this.height = 500;
                this.floatRectAttack= new FloatRect(x-100,y-50,200,100);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(250, 330);
                this.hpEnemy=80+(level-1)*2;
                this.damageEnemy=13;
                this.armorEnemy=0;
            }
            else if(type==2)
            {
                this.width = 400;
                this.height = 400;
                this.floatRectAttack= new FloatRect(x-100,y-50,200,100);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(205, 205);
                this.hpEnemy=90+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
            else if(type==3)
            {
                this.width = 400;
                this.height = 400;
                this.floatRectAttack= new FloatRect(x-100,y-50,200,100);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(200, 200);
                this.hpEnemy=95+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
            else if(type==4)
            {
                this.width = 400;
                this.height = 400;
                this.floatRectAttack= new FloatRect(x-100,y-50,200,100);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(200, 210);
                this.hpEnemy=95+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
            else if(type==5)
            {
            	this.width = 400;
                this.height = 400;
                this.floatRectAttack= new FloatRect(x-200,y-100,400,200);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(200, 200);
                this.hpEnemy=100+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;        	
            }
            else if(type==6)
            {
                this.width = 320;
                this.height = 320;
                this.floatRectAttack= new FloatRect(x-80,y-50,160,100);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(172, 165);
                this.hpEnemy=100+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
            else if(type==7)
            {
                this.width = 500;
                this.height = 500;
                this.floatRectAttack= new FloatRect(x-80,y-40,160,80);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(240, 270);
                this.hpEnemy=100+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
            else if(type==8)
            {
                this.width = 500;
                this.height = 500;
                this.floatRectAttack= new FloatRect(x-80,y-50,160,100);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(250, 260);
                this.hpEnemy=100+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
            else if(type==9)
            {
                this.width = 500;
                this.height = 500;
                this.floatRectAttack= new FloatRect(x-80,y-50,160,100);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(240, 210);
                this.hpEnemy=100+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
            else if(type==10)
            {
                this.width = 200;
                this.height = 200;
                this.floatRectAttack= new FloatRect(x-200,y-100,300,400);
                this.floatRectHarm= new FloatRect(x-80,y-40,160,80);
                this.container.setOrigin(113, 107);
                this.hpEnemy=100+(level-1)*2;
                this.damageEnemy=14;
                this.armorEnemy=0;
            }
        //Primary state: standing
        this.isSet = false;
        if(stateNumber==0)
        this.state="move";
        else if(stateNumber==1)
        this.state="idle";
        //控制受伤和死亡的换图
        this.isHurtFinish=false;
        this.isDeadFinish=false;

        //控制移动方向
        if(MoveDirection==0) {
            this.isLeft=true;this.isRight=false;this.isUp=false;this.isDown=false;
        }
        else if(MoveDirection==1){
            this.isRight=true;this.isLeft=false;this.isUp=false;this.isDown=false;
        }
        else if(MoveDirection==2) {
            this.isUp=true;this.isRight=false;this.isLeft=false;this.isDown=false;
        }
        else if(MoveDirection==3) {
            this.isDown=true;this.isUp=false;this.isRight=false;this.isLeft=false;
        }
        this.rightLimit=rightLimit;
        this.leftLimit=leftLimit;
        this.upLimit=upLimit;
        this.downLimit=downLimit;
        this.indexX = 0;
        this.indexY = 0;
        setImage();
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
        start = new Thread(this);
        start.start();
    }

    public void effect() {
        if (!isHurtEffectExist&&state.equals("hurt")) {
            if(type==5||type==10||type==3) {
                tdMusicPlay.birdHurt.play();
            }else
            {
                tdMusicPlay.normalEnemyHurt.play();
            }
           // System.out.println("创建");
            allHittingEffect.add(new HittingEffect(261, 238));
            allHittingEffect.get(0).container.setPosition(this.x,this.y);
            isHurtEffectExist = true;
        }
        else
        {
            if (allHittingEffect.size() != 0) {
                if (allHittingEffect.get(0).isFinish) {
                   // System.out.println("销毁");
                    //isMagicExist=false;
                    allHittingEffect.clear();
                }
            }
        }
    }

    public void changeDirection()
    {
        if(distanceX >= rightLimit)
        {
            isLeft=true;
            isRight=false;
        }
        if(distanceX<=leftLimit)
        {
            isLeft=false;
            isRight=true;
        }
        if(distanceY>=downLimit)
        {
            isUp=true;
            isDown=false;
        }
        if(distanceY<=upLimit)
        {
            isUp=false;
            isDown=true;
        }
    }
    //Initialise the primary setting of the enemy.
    public void setImage()
    {
        //i??????
        for(int i = 0; i < 10; i++)
        {
            if(i + 1 == type)
            {
                if((isLeft||isUp)&&state.equals("idle"))
                {
                    this.shownPic = staticVariable.enemyLeftIdle.get(i);
                }
                else if((isLeft||isUp)&&state.equals("move"))
                {
                    this.shownPic = staticVariable.enemyLeftMove.get(i);
                }
                else if(direction.equals("left")&&state.equals("hurt"))
                {
                    this.shownPic = staticVariable.enemyLeftHurt.get(i);
                }
                else if(direction.equals("left")&&state.equals("dead"))
                {
                    this.shownPic = staticVariable.enemyLeftDie.get(i);
                }
                else if(state.equals("left-attack"))
                {
                    this.shownPic = staticVariable.enemyLeftAttack.get(i);
                }

               else if((isRight||isDown)&&state.equals("idle"))
                {
                    this.shownPic = staticVariable.enemyRightIdle.get(i);
                }
                else if((isRight||isDown)&&state.equals("move"))
                {
                    this.shownPic = staticVariable.enemyRightMove.get(i);
                }
                else if(direction.equals("right")&&state.equals("hurt"))
                {
                    this.shownPic = staticVariable.enemyRightHurt.get(i);
                }
                else if(direction.equals("right")&&state.equals("dead"))
                {
                    this.shownPic = staticVariable.enemyRightDie.get(i);
                }
                else if(state.equals("right-attack"))
                {
                    this.shownPic = staticVariable.enemyRightAttack.get(i);
                }
            }
        }
        this.maxX = this.shownPic.getSize().x;
        this.maxY = this.shownPic.getSize().y;
    }

    //The enemies attacks the hero
    //Use Semaphore as a mutex lock.
    public void attack()
    {
        //i is the type of enemies
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 10; i++)
        {
            if(i + 1 == type)
            {
                if(this.indexX==0&&this.indexY==0)
                {
                    if(this.state.equals("left-attack")||this.state.equals("right-attack"))
                    {
                    	musica.enemies_attack.play(); 
                        Blood.Attack_Hero(damageEnemy,10);
                    }
                }
            }
        }
        mutex.release();
    }

  //common attack
    public float attackEnemy(float damageHero)
    {
        float realDamage = damageHero- armorEnemy;
        hpEnemy = hpEnemy - realDamage;
        return hpEnemy;
    }

    // caculate the mana of hero
    public void manaSkill(float damageHero) {
        Blood.Attack_Enemy(damageHero/25);
    }
    
    public void attackenemy(float att) {
    	this.attackEnemy(att/4);
    }
    
    public void skillattackenemy1(float att) { 	 
    	this.attackEnemy(att/4);   	
    }
    public void skillattackenemy2(float att) { 	 
		this.attackEnemy(att/4);   	
    }
    public void skillattackenemy3(float att) { 	 
		this.attackEnemy(att/4);   	
    }
    public void skillattackenemy4(float att) { 	 
		this.attackEnemy(att/4);   	
    }
    
  //get different experience when enemy died 
    public void AddExp() {   	
    	    if(this.type==1) {
    		    exp+=8;  //The setting is enough large for checking,later we can change to smaller value,like 5
    		    System.out.println("the exp is "+exp);   		   
			}
			else if(this.type==2) {
				exp+=5;
				System.out.println("the exp is "+exp);
			}
			else if(this.type==3) {
				exp+=7;
				System.out.println("the exp is "+exp);
			}
			else if(this.type==4) {
				exp+=9;
				System.out.println("the exp is "+exp);
			}				
			else if(this.type==5) {
				exp+=10;
				System.out.println("the exp is "+exp);
			}
			else if(this.type==6) {
				exp+=13;
				System.out.println("the exp is "+exp);
			}
			else if(this.type==7) {
				exp+=11;
				System.out.println("the exp is "+exp);
			}
			else if(this.type==8) {
				exp+=13;
				System.out.println("the exp is "+exp);
			}
            else if(this.type==9) {
                exp+=12;
                System.out.println("the exp is "+exp);
            }
            else if(this.type==10) {
                exp+=15;
                System.out.println("the exp is "+exp);
            }	
    	    if((level==1&&exp>=100)||(level==2&&exp>=200)||(level==3&&exp>=300)||(level==4&&exp>=400)||(level==5&&exp>=500)||(level==6&&exp>=600)
    	    		||(level==7&&exp>=700)||(level==8&&exp>=800)||(level==9&&exp>=900)||(level==10&&exp>=1000)
    	    		||(level==11&&exp>=1200)||(level==12&&exp>=1400)||(level==13&&exp>=1600)||(level==14&&exp>=1800)||(level==15&&exp>=2000)
    	    		||(level==16&&exp>=2300)||(level==17&&exp>=2500)||(level==18&&exp>=2700)||(level==19&&exp>=3000)) {
			 level=level+1;
			// System.out.println("the level is"+level);
			 level();
    	    
    	}
    }

    public void controller()
    {
        if(!state.equals("hurt"))
        {
            isHurtEffectExist=false;
        }
        this.floatRectAttack= new FloatRect(x-150,y-50,300,100);
        this.floatRectHarm= new FloatRect(x-100,y-100,200,200);
        changeDirection();
        setImage();
        changeIndex();
        effect();
        attack();
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
    }

    public void moveX(String state)
    {

    }

    public void moveY(String state)
    {

    }
    //The collision detection between play and enemies.
    public boolean shootByHero(Actors h)
    {
        // TODO Auto-generated method stub
        boolean hit = (h.floatRectAttack.intersection(this.floatRectHarm))!=null;
        return hit;
    }

    //Change pic of the enemy.
    public void changeIndex()
    {
        //if(this.type == 1 || this.type == 2|| this.type == 3|| this.type == 4||this.type==5)
       // {
            //If the enemy is idling.
            if(this.state.equals("idle"))
            {
                indexX++;
                if(indexX >= 5)
                {
                    indexX = 0;
                    indexY = 0;
                }
            }
            else if(this.state.equals("move"))
            {
                indexX++;               
                if(indexX>=5&&(type==1||type==2||type==9))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=4&&(type==3||type==4||type==9))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=6&&(type==5||type==7||type==8))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=8&&type==6)
                {
                    indexX=0;
                    indexY=0;
                }
            }
            else if(this.state.equals("hurt"))
            {
                indexX++;
                if(indexX>=2)
                {
                    indexX=0;
                    indexY=0;
                    this.isHurtFinish=true;
                }
            }
            else if(this.state.equals("right-attack"))
            {
                indexX++;
                if(indexX>=10&&(type==1||type==2||type==4))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=7&&(type==3||type==9))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=6&&type==5)
                {
                	indexX=0;
                    indexY=0;
                }
                else if(indexX>=8&&(type==6||type==8))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=5&&(type==7||type==9))
                {
                    indexX=0;
                    indexY=0;
                }
            }
            else if(this.state.equals("left-attack"))
            {
                indexX++;
                if(indexX>=10&&(type==1||type==2||type==4))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=7&&(type==3||type==9))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=6&&type==5)
                {
                	indexX=0;
                    indexY=0;
                }
                else if(indexX>=8&&(type==6||type==8))
                {
                    indexX=0;
                    indexY=0;
                }
                else if(indexX>=5&&(type==7||type==9))
                {
                    indexX=0;
                    indexY=0;
                }
            }
            else if(this.state.equals("dead"))
            {
                indexX++;
                if(indexX>=12&&this.type==1)
                {
                    isDeadFinish=true;
//                    indexX=0;
//                    indexY=0;
                }
                else if(indexX>=4&&(this.type==2||this.type==3||this.type==4||this.type==6||this.type==7||this.type==8))
                {
//                    indexX=0;
//                    indexY=0;
                    isDeadFinish=true;
                }
                else if(indexX>=3&&(type==5||type==9))
                {
//                    indexX=0;
//                    indexY=0;
                    isDeadFinish=true;
                }
                else if(indexX>=6&&type==9)
                {
//                    indexX=0;
//                    indexY=0;
                    isDeadFinish=true;
                }
            }
       // }
        if(indexX == this.maxX / this.width&&!state.equals("dead"))
        {
            indexX = 0;
        }
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
    }

    @Override
    public void run()
    {
        while(!isEnd)
        {
            //System.out.println(Thread.currentThread().getName());
            controller();
            try {
                if(state.equals("hurt"))
                {
                    Thread.sleep(200);
                    if(isHurtFinish)
                    {
                        state="";
                        isHurtFinish=false;
                    }
                }
                else if(state.equals("dead"))
                {
                    Thread.sleep(200);
                }
                else if(state.contains("attack")&&type==7)
                {
                    Thread.sleep(150);
                }
                else
                {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public void setEnd()
    {
        isEnd = true;
    }

}
