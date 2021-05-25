package Actors;

import GameDriver.Musiccall;
import GameDriver.staticVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;

public class Boss extends Actors implements Runnable{
    private Thread start;
    public float distanceX;
    public float distanceY;
    public float x;
    public float y;
    //For the type of enemies. E.g. 100*100->type = 1
    private int size = 0;
    //Type of enemies in each size. E.g. size = 2, type = 1--->One type of enemy.
    private int type = 0;
    //Category of the enemy, close, far and boss.
    private String category;
    //控制受伤和死亡的换图
    public boolean isHurtFinish;
    public boolean isDeadFinish;
    public boolean isAttack;
    public boolean isHurtEffectExist;
    //Movement range, decided by type of enemy.
    public int rightLimit = 0;
    public int leftLimit = 0;
    public int upLimit = 0;
    public int downLimit = 0;
    //控制移动方向
    public boolean isLeft = true;
    public boolean isRight = true;
    public boolean isUp = true;
    public boolean isDown = true;
    //End the thread
    private boolean isEnd = false;
    //restricted area
    public int originUpLimit;
    public int originDownLimit;
    public int originLeftLimit;
    public int originRightLimit;
    public TDMusicPlay tdMusicPlay;
    public Musiccall musica;
    private static Semaphore mutex = new Semaphore(1);
    public List<HittingEffect> allHittingEffect=new ArrayList<HittingEffect>();

    public Boss(float x, float y,int MoveDirection,int leftLimit,int rightLimit,int upLimit, int downLimit,
                int type, String category,int stateNumber)
    {
        tdMusicPlay =new TDMusicPlay();
        //Type of enemy.
        this.type = type;
        this.category = category;
        this.direction = "left";
        this.x = x;
        this.y = y;
        isHurtEffectExist=false;
        this.originUpLimit=upLimit;
        this.originDownLimit=downLimit;
        this.originLeftLimit=leftLimit;
        this.originRightLimit=rightLimit;
        //??????????
        this.distanceX=x;
        this.distanceY=y;
        if(type==1){
            this.width = 512;
            this.height = 336;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(260, 240);
            this.hpEnemy=320;
            this.armorEnemy=0;
            this.damageEnemy=17;
        }
        else if(type==2)
        {
            this.width = 256;
            this.height = 224;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(139, 124);
            this.hpEnemy=380;
            this.armorEnemy=0;
            this.damageEnemy=18;
        }
        else if(type==3)
        {
            this.width = 512;
            this.height = 336;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(240, 210);
            this.hpEnemy=400;
            this.armorEnemy=0;
            this.damageEnemy=17;
        }
        else if(type==4)
        {
            this.width = 512;
            this.height = 336;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(240, 210);
            this.hpEnemy=450;
            this.armorEnemy=0;
            this.damageEnemy=17;
        }
        else if(type==5)
        {
            this.width = 256;
            this.height = 224;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(116, 115);
            this.hpEnemy=480;
            this.armorEnemy=0;
            this.damageEnemy=17;
        }
        else if(type==6)
        {
            this.width = 500;
            this.height = 400;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(240, 250);
            this.hpEnemy=500;
            this.armorEnemy=0;
            this.damageEnemy=17;
        }
        else if(type==7)
        {
            this.width = 600;
            this.height = 600;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(300, 320);
            this.hpEnemy=500;
            this.armorEnemy=0;
            this.damageEnemy=17;
        }
        else if(type==8)
        {
            this.width = 600;
            this.height = 600;
            this.floatRectAttack= new FloatRect(x-100,y-75,200,150);
            this.floatRectHarm= new FloatRect(x-100,y-70,200,140);
            this.container.setOrigin(273, 323);
            this.hpEnemy=320;
            this.armorEnemy=0;
            this.damageEnemy=16;
        }
        if(stateNumber==0)
            this.state="move";
        else if(stateNumber==1)
            this.state="idle";
        this.isHurtFinish=false;
        this.isDeadFinish=false;
        this.isAttackFinish=false;
        if(MoveDirection==0) {
            this.isLeft=true;this.isRight=false;this.isUp=true;this.isDown=false;
        }
        else if(MoveDirection==1){
            this.isRight=true;this.isLeft=false;this.isUp=true;this.isDown=false;
        }
        else if(MoveDirection==2) {
            this.isUp=false;this.isRight=true;this.isLeft=false;this.isDown=true;
        }
        else if(MoveDirection==3) {
            this.isDown=true;this.isUp=false;this.isRight=false;this.isLeft=true;
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
            tdMusicPlay.enemyHurt.play();
            tdMusicPlay.normalEnemyHurt.play();
            allHittingEffect.add(new HittingEffect(261, 238));
            allHittingEffect.get(0).container.setPosition(this.x,this.y);
            isHurtEffectExist = true;
        }
        else
        {
            if (allHittingEffect.size() != 0) {
                if (allHittingEffect.get(0).isFinish) {
                    //isMagicExist=false;
                    allHittingEffect.clear();
                }
            }
        }
    }
    //The bosses attack the hero
    //Use Semaphore as a mutex lock.
    public void attack()
    {
        //i is the type of enemies
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 8; i++)
        {
            if(i + 1 == type)
            {
                if(this.indexX==0&&this.indexY==0)
                {
                	if(state.equals("left-attack1")||state.equals("left-attack2")||state.equals("left-attack3")||state.equals("left-attack4")
                			  ||state.equals("right-attack1")||state.equals("right-attack2")||state.equals("right-attack3")||state.equals("right-attack4")) {
                		  musica.boss_attack.play();
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
    
    public void AddExp() {   	
 	    if(this.type==1) {
 		    exp+=50;  //The setting is enough large for checking,later we can change to smaller value,like 5
 		   // System.out.println("the exp is "+exp); 		 
 		}
 		else if(this.type==2) {
 			exp+=65;
 			//System.out.println("the exp is "+exp);
 		}
 		else if(this.type==3) {
 			exp+=70;
 			//System.out.println("the exp is "+exp);
 		}
 		else if(this.type==4) {
 			exp+=80;
 			//System.out.println("the exp is "+exp);
 		}
 		else if(this.type==5) {
 			exp+=85;
 			//System.out.println("the exp is "+exp);
 		}
 		else if(this.type==6) {
 			exp+=90;
 			//System.out.println("the exp is "+exp);
 		}
 		else if(this.type==7) {
 			exp+=95;
 			//System.out.println("the exp is "+exp);
 		}
 		else if(this.type==8) {
 			exp+=100;
 			//System.out.println("the exp is "+exp);
 		}
 	   if((level==1&&exp>=100)||(level==2&&exp>=200)||(level==3&&exp>=300)||(level==4&&exp>=400)||(level==5&&exp>=500)||(level==6&&exp>=600)
	    		||(level==7&&exp>=700)||(level==8&&exp>=800)||(level==9&&exp>=900)||(level==10&&exp>=1000)
	    		||(level==11&&exp>=1200)||(level==12&&exp>=1400)||(level==13&&exp>=1600)||(level==14&&exp>=1800)||(level==15&&exp>=2000)
	    		||(level==16&&exp>=2300)||(level==17&&exp>=2500)||(level==18&&exp>=2700)||(level==19&&exp>=3000)) {
		 level=level+1;
		 //System.out.println("the level is"+level);
		 level();
 	    
 	}
 }


    private void setImage() {

        for(int i = 0; i < 8; i++)
        {
            if(i + 1 == type)
            {
                if(i==0) {
                    this.width = 512;
                    this.height = 336;
                    this.container.setOrigin(260, 240);
                }
                else if(i==1) {
                    this.width = 256;
                    this.height = 224;
                    this.container.setOrigin(139, 124);
                }
                else if(i==4) {
                    this.width = 256;
                    this.height = 224;
                    this.container.setOrigin(116, 115);
                }

                if(isLeft&&state.equals("move"))
                {
                    if(i==1) {
                        this.width = 512;
                        this.height = 336;
                        this.container.setOrigin(250, 230);
                    }
                    this.shownPic = staticVariable.BossLeftMove.get(i);
                }
                else if(direction.equals("left")&&state.equals("hurt"))
                {
                    this.shownPic = staticVariable.BossLeftHurt.get(i);
                }
                else if(direction.equals("left")&&state.equals("dead"))
                {
                    this.shownPic = staticVariable.BossLeftDie.get(i);
                }
                else if(state.equals("left-attack1"))
                {
                    if(i==1) {
                        this.width = 558;
                        this.container.setOrigin(310, 130);
                    }
                    this.shownPic = staticVariable.BossLeftAttack1.get(i);
                }
                else if(state.equals("left-attack2"))
                {
                    if(i==0)
                        this.width = 481;
                    else if(i==1) {
                        this.width = 512;
                        this.container.setOrigin(250, 230);
                    }
                    else if(i==4)
                    {
                        this.width = 512;
                        this.height = 336;
                        this.container.setOrigin(240, 220);
                    }
                    this.shownPic = staticVariable.BossLeftAttack2.get(i);
                }
                else if(state.equals("left-attack3"))
                {
                    if(i==1) {
                        this.width = 512;
                        this.height = 336;
                        this.container.setOrigin(250, 230);
                    }
                    this.shownPic = staticVariable.BossLeftAttack3.get(i);
                }
                else if(state.equals("left-attack4"))
                {
                    if(i==1) {
                        this.width = 558;
                        this.container.setOrigin(310, 130);
                    }
                    this.shownPic = staticVariable.BossLeftAttack4.get(i);
                }


                else if(isRight&&state.equals("move"))
                {
                    if(i==1) {
                        this.width = 512;
                        this.height = 336;
                        this.container.setOrigin(250, 230);
                    }
                    this.shownPic = staticVariable.BossRightMove.get(i);
                }
                else if(direction.equals("right")&&state.equals("hurt"))
                {
                    this.shownPic = staticVariable.BossRightHurt.get(i);
                }
                else if(direction.equals("right")&&state.equals("dead"))
                {
                    this.shownPic = staticVariable.BossRightDie.get(i);
                }
                else if(state.equals("right-attack1"))
                {
                    if(i==1)
                        this.width=558;
                    this.shownPic = staticVariable.BossRightAttack1.get(i);
                }
                else if(state.equals("right-attack2"))
                {
                    if(i==0)
                        this.width = 481;
                    else if(i==1) {
                        this.width = 512;
                        this.container.setOrigin(250, 130);
                    }
                    else if(i==4)
                    {
                        this.width = 512;
                        this.height = 336;
                        this.container.setOrigin(240, 220);
                    }
                    this.shownPic = staticVariable.BossRightAttack2.get(i);
                }
                else if(state.equals("right-attack3"))
                {
                    if(i==1) {
                        this.width = 512;
                        this.height = 336;
                        this.container.setOrigin(250, 230);
                    }
                    this.shownPic = staticVariable.BossRightAttack3.get(i);
                }
                else if(state.equals("right-attack4"))
                {
                    if(i==1) {
                        this.width = 558;
                        this.container.setOrigin(310, 130);
                    }
                    this.shownPic = staticVariable.BossRightAttack4.get(i);
                }
            }
        }
        this.maxX = this.shownPic.getSize().x;
        this.maxY = this.shownPic.getSize().y;
    }



    @Override
    public void moveX(String state) {

    }

    @Override
    public void moveY(String state) {

    }

    private void changeDirection() {
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

    private void changeIndex() {
        //If the enemy is idling.
        if(this.state.equals("move"))
        {
            indexX++;
            if(indexX>=10&&type==8)
            {
                indexX=0;
                indexY=0;
            }
            else if(indexX >= 8)
            {
                indexX=0;
                indexY=0;
            }
        }
        else if(this.state.equals("hurt"))
        {
            indexX++;
            if(indexX>=4&&(type==3||type==4||type==5))
            {
                indexX=0;
                indexY=0;
                this.isHurtFinish=true;
            }
            else if(indexX>=5)
            {
                indexX=0;
                indexY=0;
                this.isHurtFinish=true;
            }
        }
        else if(this.state.equals("right-attack1")||this.state.equals("left-attack1"))
        {
            indexX++;
            if(indexX>=10&&(this.type==1||type==4))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=9&&(this.type==2||this.type==5))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=13&&(this.type==3||type==6))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=20&&(this.type==7||this.type==8))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }

        }
        else if(this.state.equals("right-attack2")||this.state.equals("left-attack2"))
        {
            indexX++;
            if(indexX>=10&&(this.type==1||type==5))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=9&&this.type==2)
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=12&&(this.type==3||type==4))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=19&&this.type==6)
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=20&&(this.type==7||this.type==8))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
        }
        else if(this.state.equals("right-attack3")||this.state.equals("left-attack3"))
        {
            indexX++;
            if(indexX>=10&&(type==1||type==2||type==4))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=9&&type==5)
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=12&&type==3)
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=19&&this.type==6)
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=20&&(this.type==7||this.type==8))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
        }
        else if(this.state.equals("right-attack4")||this.state.equals("left-attack4"))
        {
            indexX++;
            if(indexX>=14&&this.type==1)
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=11&&(this.type==2||type==4))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=10&&(this.type==3||type==5))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=19&&this.type==6)
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
            else if(indexX>=22&&(this.type==7||this.type==8))
            {
                indexX=0;
                indexY=0;
                this.isAttackFinish=false;
            }
        }
//            else if(this.state.equals("left-attack1"))
//            {
//                indexX++;
//                if(indexX>=10&&(this.type==1||type==4))
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//                else if(indexX>=9&&this.type==2)
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//                else if(indexX>=13&&this.type==3)
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//            }
//            else if(this.state.equals("left-attack2"))
//            {
//                indexX++;
//                if(indexX>=10)
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//                else if(indexX>=9&&this.type==2)
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//                else if(indexX>=12&&(this.type==3||type==4))
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//            }
//            else if(this.state.equals("left-attack3"))
//            {
//                indexX++;
//                if(indexX>=10&&(type==1||type==2||type==4))
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//                else if(indexX>=12&&type==3)
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//            }
//            else if(this.state.equals("left-attack4"))
//            {
//                indexX++;
//                if(indexX>=14&&this.type==1)
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//                else if(indexX>=11&&(this.type==2||type==4))
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//                else if(indexX>=10&&this.type==3)
//                {
//                    indexX=0;
//                    indexY=0;
//                    this.isAttackFinish=false;
//                }
//            }
        else if(this.state.equals("dead"))
        {
            indexX++;
            if(indexX>=8&&type==3)
            {
                isDeadFinish=true;
            }
            else if(indexX>=9)
            {
                //indexX=0;
                //indexY=0;
                isDeadFinish=true;
            }
        }

        if(indexX == this.maxX / this.width)
        {
            indexX = 0;
        }
        this.container.setTexture(this.shownPic);
        this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
    }


    public boolean shootByHero(Actors h)
    {
        // TODO Auto-generated method stub
        boolean hit = (h.floatRectAttack.intersection(this.floatRectHarm))!=null;
        return hit;
    }

   
    @Override
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

    public void setEnd()
    {
        isEnd = true;
    }

    @Override
    public void run() {
        while(!isEnd) {
           // System.out.println(x+" "+y);
            controller();
            try {
                if (state.equals("hurt")) {
                    Thread.sleep(150);
                    if (isHurtFinish) {
                        state = "";
                        isHurtFinish = false;
                    }
                } else if (state.equals("dead")) {
                    Thread.sleep(150);
                } else {
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
