package Actors;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.IntRect;

import GameDriver.Musiccall;
import GameDriver.staticVariable;


/*
 * @para state: 即角色此时的状态，是一个总的状态，目前可以设置几个值：standing, moving, attacking, underattacking
 * 分别表示：站立，移动，攻击，被攻击
 * @para movingState: 上下左右移动
 * @para isSet 是否整改图片->确保每次循环时候，只要不释放按键，保证不从头开始
 * @para sleepTime: 现成的睡眠时间
 * @notice: 默认图片设置的是ready，即图片是
 */

public class Lancer extends Actors implements Runnable
{
	private Thread thread;
	private float sleepTime;
	Musiccall musica=new Musiccall();
	public boolean isMagicExist;
	private boolean isDeadFinish = false;

	public Lancer()
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
		this.floatRectAttack = new FloatRect(x-130,y-70,260,140);
		//伤害范围
		this.floatRectHarm = new FloatRect(x-80,y-40,160,80);
		//从键盘监听得到的指令
		this.order = null;
		this.isSet = false;
		this.direction = "right";
		this.attackCount = -1;
		this.attackNumber = 3;
		this.heroType = "Lancer";
		//Primary action: right, ready
		this.shownPic = staticVariable.HeroReady.get(1);
		this.maxX = shownPic.getSize().x;
		this.maxY = shownPic.getSize().y;
		this.container.setTexture(this.shownPic);
		this.container.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
		//Set the center of the character.
		this.container.setOrigin(250, 300);		
		//the initial level,hp ,mana,damage of hero
		this.level=1;
		this.exp=0;
		this.hp=150;
		this.mana=100;	
		this.hpHero=150;
		this.manaHero=100;
		this.m=3;
		this.n=15;
		this.p=10;
		this.t=2;
		this.s=skillNumber;
		this.damageHero=20;
		this.damageHeroSkill1=20;
		this.damageHeroSkill2=30;
		this.damageHeroSkill3=40;
		this.damageHeroSkill4=45;

		thread = new Thread(this);
		thread.start();
	}

	//主要的控制类，控制角色的所有行为
	public void controller()
	{
		//攻击范围
		this.floatRectAttack = new FloatRect(x-100,y-50,200,100);
		//伤害范围
		this.floatRectHarm = new FloatRect(x-80,y-40,160,80);
		if(isDead)
			this.state = "dead";
		else if(!isDead)
		{
			if(this.order != null)
			{
				//Here add the behaviours, such as attacking, moving .etc
				//Moving
				move(this.order);
				//Attacking
				attack(this.order);
				//Skill
				skill(this.order);
			}
		}
		this.setMusic();
		if(isDead)
		{
			musica.run.stop();
			musica.Lancer_attack.stop();
			musica.Lancer_skill1.stop();
			musica.Lancer_skill2.stop();
			musica.Lancer_skill3.stop();
			musica.Lancer_skill4.stop();
		}
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
		if(skillState.equals("skill") && this.isSkill && this.skillNumber != 0)
			this.state = "skilling";
		if(isSkillFinish && !isSkill && this.state == "skilling")
			this.heroIdle();
	}

    //add music
	public void setMusic() {
		if(!isSet)
		{
			if ((this.right || this.left || this.up || this.down) && (!this.isAttack) && (!this.isSkill) && this.state == "moving") {
				musica.run.play();
			} else if (!this.down && !this.up && !this.left && !this.right && !this.isSkill && this.isAttack) {
				musica.Lancer_sound.play();
				musica.Lancer_attack.play();
			} else if (!this.up && !this.down && !this.left && !this.right && !this.isAttack && this.isSkill) {
				if (this.skillNumber == 1) {
					musica.Lancer_skill1.play();
					Blood.Attack_Enemy(damageHeroSkill1);
				} else if (this.skillNumber == 2) {
					musica.Lancer_skill2.play();
					Blood.Attack_Enemy(damageHeroSkill2);
				} else if (this.skillNumber == 3) {
					musica.Lancer_skill3.play();
					Blood.Attack_Enemy(damageHeroSkill3);
				} else if (this.skillNumber == 4) {
					musica.Lancer_skill4.play();
					Blood.Attack_Enemy(damageHeroSkill4);
				}
			}else if(this.isDead) {
				musica.Lancer_died.play();
			}
		}
	}

	//添加技能特效
	public void effect()
	{
		if(skillNumber==1&&indexY>1)
		{
			if(!isMagicExist) {
				allMagic.add(new Magic(700,700));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=1;
				//System.out.println(this.direction);
				allMagic.get(0).direction=this.direction;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(this.x-300,this.y);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(this.x,this.y);
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
				allMagic.add(new Magic(300,400));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=2;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(this.x-50,this.y+100);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(this.x+150,this.y+100);
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
				allMagic.add(new Magic(512,512));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=3;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(this.x-200,this.y+100);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(this.x+100,this.y+100);
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
				allMagic.add(new Magic(600,400));
				allMagic.get(0).type="saber";
				allMagic.get(0).skillNumber=4;
				if(direction.equals("left"))
				{
					allMagic.get(0).container.setPosition(this.x-300,this.y+200);
				}
				else if(direction.equals("right"))
				{
					allMagic.get(0).container.setPosition(this.y+30,this.y+200);
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

			if(isDead && this.indexX == 5 && this.indexY == this.maxY / this.height - 1)
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
			if(indexY ==  this.maxY / this.height - 1)
			{
				if(indexX == 1 && this.state == "standing")
					indexY++;
				if(indexX == 4 && this.state == "moving")
					indexY++;
				//切换普通攻击的图片
				if(isAttack && !isAttackFinish && !isSkill)
				{
					if(indexX == 1 && (this.attackCount == 0 ||this.attackCount == 1 || this.attackCount == 2))
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
					if((indexX == 1 && this.skillNumber == 4) ||
							(indexX == 7 && (this.skillNumber == 1 || this.skillNumber == 2)) ||
							(indexX == 6 && this.skillNumber == 3))
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

			if(isDead && this.indexX == 2 && this.indexY == this.maxY / this.height - 1)
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
				if(indexX == 6 && this.state == "standing")
					indexY++;
				if(indexX == 3 && this.state == "moving")
					indexY++;
				//切换攻击图片
				if(isAttack && !isAttackFinish && !isSkill)
				{
					if(indexX == 6 && (this.attackCount == 0 ||this.attackCount == 1 || this.attackCount == 2))
					{
						indexY++;
						isAttackFinish = true;
						isAttack = false;
						isSet = false;
					}
				}

				if(isSkill && !isSkillFinish && !isAttack)
				{
					if((indexX == 6 && this.skillNumber == 4) ||
							(indexX == 0 && (this.skillNumber == 1 || this.skillNumber == 2)) ||
							(indexX == 1 && this.skillNumber == 3))
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
				if (allMagic.get(0).isFinish)
				{
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
			//Thread.currentThread().setName("Thread-0");
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
		System.out.println(allMagic.size() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
}