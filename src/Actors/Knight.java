package Actors;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;

import GameDriver.Musiccall;
import GameDriver.staticVariable;

/*
 * 这个类是Knight相关的行为类
 * 控制Knight的移动，攻击，被攻击，释放技能等所有的行为
 * 不包括碰撞检测
 */

public class Knight extends Actors implements Runnable
{
	private Thread thread;
	private float sleepTime;
	Musiccall musica=new Musiccall();
	public boolean isMagicExist;
	private boolean isDeadFinish = false;

	public Knight()
	{
		//判断技能是否存在
		isMagicExist=false;
		//初始状态向右，图片的index，即在整个大图里面的某一个小块
		this.indexX = 7;
		this.indexY = 0;
		//单个帧的大小
		this.width = 500;
		this.height = 500;
		//攻击范围
		this.floatRectAttack= new FloatRect(x-150,y-100,300,200);
		//伤害范围
		this.floatRectHarm= new FloatRect(x-80,y-80,160,160);
		//从键盘监听得到的指令
		this.order = null;
		this.isSet = false;
		this.direction = "right";
		this.attackCount = -1;
		this.attackNumber = 3;
		this.heroType = "Knight";
		//Primary action: right, ready
		this.shownPic = staticVariable.HeroReady.get(1);
		this.maxX = shownPic.getSize().x;
		this.maxY = shownPic.getSize().y;
		this.container.setTexture(this.shownPic);
		this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
		//Set the center of the character.
		this.container.setOrigin(280, 300);
		//the initial level,hp ,mana,damage of hero
		this.level=1;
		this.exp=0;
		this.hp=170;
		this.mana=80;		
		this.hpHero=170;
		this.manaHero=80;
		this.m=2;
		this.n=20;
		this.p=10;
		this.t=1;
		this.s=skillNumber;
		this.damageHero=15;
		this.damageHeroSkill1=20;
		this.damageHeroSkill2=25;
		this.damageHeroSkill3=30;
		this.damageHeroSkill4=35;        
		thread = new Thread(this);
		thread.start();
	}

	//主要的控制类，控制角色的所有行为
	public void controller()
	{
		//攻击范围
		this.floatRectAttack= new FloatRect(x-150,y-100,300,200);
		//伤害范围
		this.floatRectHarm= new FloatRect(x-80,y-80,160,160);
		if(this.isDead)
			this.state = "dead";
		else if(!this.isDead)
		{
			if(this.order != null)
			{
				move(this.order);
				attack(this.order);
				skill(this.order);
			}
		}
		this.setMusic();
		setImages();
		changeIndex();
		this.container.setTexture(this.shownPic);
		this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
	}


	public void move(String order)
	{
		if((this.up || this.down || this.left || this.right) && !isAttack && this.state != "attacking")
		{

			this.state = "moving";
			moveX(order);
			moveY(order);
		}

		//如果不移动，且不攻击，则此时为站立状态
		if(order.equals("standing") && !this.up && !this.down && !this.left && !this.right && !isAttack && !isSkill)
		{
			this.heroIdle();
			musica.run.stop();
		}
	}

	//左右改变图片，上下不改变图片，坐标可以不管（我是用来测试的）
	public void moveX(String movingState)
	{
		if((movingState.equals("left_moving") || movingState.equals("right_moving"))
				&& (this.left || this.right))
			this.direction = movingState.split("_")[0];
//		if(this.left == true && this.right == false)
//			this.x -= 4;
//		else if(this.right == true && this.left == false)
//			this.x += 4;
	}


	public void moveY(String movingState)
	{
//		if(this.up)
//			this.y -= 4;
//		else if(this.down)
//			this.y += 4;
	}


	//Start and stop attacking.
	public void attack(String attackState)
	{
		//Start to attack
		if(isAttack && attackState.equals("attack_normal"))
			this.state = "attacking";
		//Decide whether stop attack or not.
		if(isAttackFinish && !isAttack && this.state == "attacking")
			this.heroIdle();
	}

	//Use the skill, and the end of skill.
	public void skill(String skillState)
	{
		//如果按下了技能键
		if(skillState.equals("skill") && isSkill && this.skillNumber != 0)
			this.state = "skilling";
		if(isSkillFinish && !isSkill && this.state == "skilling")
			this.heroIdle();
	}

    //add music
	public void setMusic() {
		if(!isSet)
		{
			if((this.right||this.left||this.up||this.down)&&(!this.isAttack)&&(!this.isSkill)&&this.state=="moving")
			{
				musica.run.play();
			}

			else if(!this.down && !this.up && !this.left && !this.right && !this.isSkill && this.isAttack)
			{
				musica.Knight_sound.play();
				musica.Knight_attack.play();
			}
			else if(!this.up && !this.down && !this.left && !this.right && !this.isAttack && this.isSkill)
			{
				if(this.skillNumber == 1)
				{
					Blood.Attack_Enemy(damageHeroSkill1);
					musica.Knight_skill1.play();
				}
				else if(this.skillNumber == 2)
				{
					musica.Knight_skill2.play();
					hpHero=hpHero+damageHeroSkill2;
					if(hpHero>170) 
						hpHero=170;
					Blood.GethpLength_hero();
					Blood.Attack_Enemy(damageHeroSkill2);	
				}
				else if(this.skillNumber == 3)
				{
					Blood.Attack_Enemy(damageHeroSkill3);
					musica.Knight_skill3.play();
				}
				else if(this.skillNumber == 4)
				{
					Blood.Attack_Enemy(damageHeroSkill4);
					musica.Knight_skill4.play();
				}
			}else if(this.isDead) {
				musica.Knight_died.play();
			}
		}
	}

	//添加技能特效
	public void effect()
	{
		if(skillNumber==1&&indexY>1){
			if(!isMagicExist) {
				allMagic.add(new Magic(600,400));
				allMagic.get(0).skillNumber=1;
				allMagic.get(0).direction=this.direction;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(250,450);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(700,450);
				}
				//allMagic.add(magic);
				isMagicExist = true;
			}
			else
			{
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("销毁");
						allMagic.get(0).direction = "";
						//isMagicExist=false;
						allMagic.clear();
					}
				}
			}
		}
		if(skillNumber==2&&indexY>1){
			if(!isMagicExist) {
				allMagic.add(new Magic(500,500));
				allMagic.get(0).skillNumber=2;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(500,450);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(500,450);
				}
				allMagic.get(0).direction=this.direction;
				//allMagic.add(magic);
				isMagicExist = true;
			}
			else {
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("销毁");
						allMagic.get(0).direction = "";
						//isMagicExist=false;
						allMagic.clear();
					}
				}
			}
		}
		if(skillNumber==3&&indexY>=1){
			if(!isMagicExist) {
				allMagic.add(new Magic(400,400));
				allMagic.get(0).skillNumber=3;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(350,450);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(800,450);
				}
				allMagic.get(0).direction=this.direction;
				//allMagic.add(magic);
				isMagicExist = true;
			}
			else {
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("销毁");
						allMagic.get(0).direction = "";
						//isMagicExist=false;
						allMagic.clear();
					}
				}
			}
		}
		if(skillNumber==4&&indexY>2){
			if(!isMagicExist) {
				//System.out.println("存在");
				allMagic.add(new Magic(512,512));
				allMagic.get(0).skillNumber=4;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(300,350);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(670,350);
				}
				allMagic.get(0).direction=this.direction;
				isMagicExist = true;
			}
			else {
				if (allMagic.size() != 0) {
					if (allMagic.get(0).isFinish) {
						//System.out.println("销毁");
						allMagic.get(0).direction = "";
						allMagic.clear();
					}
				}
			}
		}
	}

	/*
	Change the index of the texture
	Some of the actions may have different ending-index
	So need to consider them correctly.
	 */
	public void changeIndex()
	{
		if(this.direction.equals("left"))
		{
			effect();
			indexX++;

			if(isDead && this.indexX == 2 && this.indexY == this.maxY / this.height - 1)
			{
				indexX--;
				indexY = indexY;
				isDeadFinish = true;
			}

			if(indexX == this.maxX / this.width)
			{
				indexX = 0;
				indexY++;
			}

			if(indexY==this.maxY/this.height-1)
			{
				if( indexX == 2 && this.state == "standing")
					indexY++;
				if(indexX == 3 && this.state == "moving")
					indexY++;
				//Change he attack images
				if(isAttack && !isAttackFinish&&!isSkill)
				{
					if(indexX == 1 && (this.attackCount == 0|| this.attackCount==1||this.attackCount==2))
					{
						indexY++;
						isAttackFinish = true;
						isAttack = false;
						isSet = false;

					}
				}
				//切换技能攻击的图片
				if(isSkill && !isSkillFinish && !isAttack)
				{
					if(indexX == 3 && (this.skillNumber==1|| this.skillNumber == 4||this.skillNumber == 3)
							||(indexX==2&&(this.skillNumber==2)))
					{
						indexY++;
						isSkillFinish = true;
						isSkill = false;
						isSet = false;
						skillNumber = 0;
					}
				}
			}
			//End of the frame, then begin from start again.
			if(indexY == this.maxY / this.height)
			{
				indexY = 0;
				indexX = 0;
			}
		}
		else if(this.direction.equals("right"))
		{
			effect();
			indexX--;

			if(isDead && this.indexX == 5 && this.indexY == this.maxY / this.height - 1)
			{
				indexX++;
				indexY = indexY;
				isDeadFinish = true;
			}

			if(indexX == -1)
			{
				indexX = 7;
				indexY++;
			}

			if(indexY == this.maxY / this.height - 1)
			{
				if( indexX == 4 && this.state == "standing")
					indexY++;
				if(indexX == 3 && this.state == "moving")
					indexY++;
				//切换攻击图片
				if(isAttack && !isAttackFinish&&!isSkill)
				{
					if((indexX == 7 && (this.attackCount == 0|| this.attackCount == 1||this.attackCount == 2)))
					{
						indexY++;
						isAttackFinish = true;
						isAttack = false;
						isSet = false;
					}
				}
				if(isSkill && !isSkillFinish && !isAttack)
				{
					if(indexX == 4 &&( this.skillNumber == 1 ||this.skillNumber == 3|| this.skillNumber == 4)
							||(indexX==5 && this.skillNumber==2))

					{
						indexY++;
						isSkillFinish = true;
						isSkill = false;
						isSet = false;
						skillNumber = 0;
					}
				}
			}
			if(indexY == this.maxY / this.height)
			{
				indexY = 0;
				indexX = 7;
			}
		}
		if(!isSkill)
		{
			isMagicExist = false;
			if (allMagic.size() != 0) {
				if (allMagic.get(0).isFinish) {
					System.out.println("销毁aaaaaaaaaaaa");
					allMagic.get(0).direction = "";
					allMagic.clear();
				}
			}
		}
	}

	@Override
	public void run()
	{
		while(!isDeadFinish)
		{
			this.controller();
			if(this.state == "moving")
				this.sleepTime =  movespeed;
			else if(this.state == "standing")
				this.sleepTime = 50;
			else if(this.state == "attacking")
				this.sleepTime = attackspeed;
			else if(this.state == "skilling")
				this.sleepTime = skillspeed;
			else if(this.state == "dead")
				this.sleepTime = 50;
			try {
				Thread.sleep((long) sleepTime);
			} catch (InterruptedException e) {
			}
		}
	}
}
