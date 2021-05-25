package GameDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import GUI.*;
import TowerDenfense.TDLoadMusic;
import TowerDenfense.TDMusicPlay;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import java.util.Map.Entry;
import Actors.*;
import org.jsfml.window.Mouse;

/*
 * This class is a driver class
 * All classes have instances in this class
 */

public class Driver implements Runnable
{
	boolean firstTime = true;
	public boolean decreasehp = true;

	public static String Path = System.getProperty("user.dir");
	public Actors player;

	public PlayerShadow playerShadow;
	public int stateNumber;
	//Used for choosing levels of the game.
	private String type;

	private int level;

	public Musiccall musica;
	private boolean isPlay = false;

	private ConcurrentHashMap<EnemyMagic,Integer> allMagic=new  ConcurrentHashMap<EnemyMagic,Integer>();
	//slower enemies magic speed
	private int[] counter;
	private addEnemies allEnermies;

	public int count = 0;

	public Background curBg = null;
	public Background covers = null;
	//public Background covers = null;
	public static int moveX = 0;
	public static int moveY = 0;
	public boolean canMoveX;
	public boolean canMoveY;
	public Velocity V1;
	public RenderWindow window;

	//Determine whether victory.
	public boolean victory = false;;
	//Before changeLevel, clear all the variables;
	public boolean isClearFinished = false;
	public boolean changeToAnotherLevel = false;

	//Map info
	public int Map[][];
	private guideMap guide;// = new guideMap();;


	//UI
	//private MainWindowDuringPlaying_icons MainWindowDuringPlaying_icons1;
	//Mouse & Equipments panel
	//private Mouse mouse = null;
	public HeroSkillCards cards;
	public HeroEquipments equipments;

	//NPCs
	public addNPCs ones;
	//Items on the ground
	public addItemsOnMap itemsOnMap;

	//When monsters dead,  drop equipments
	private ArrayList<MonstersItems> monstersItems = new ArrayList<MonstersItems>();

	//dealing with blank
	public static int playerRelativeToBg_x = 0;
	public static int playerRelativeToBg_y = 0;
	public static int playerMoveX = 0;
	public static int playerMoveY = 0;

	//Mark
	public Mark mark;
	public int numberOfDeath = 0;
	public static int totalMission = 0;
	public static int finishedMission = 0;


	private Thread thread;

	//Test
	private int myX = 0;
	private int myY = 0;

	public Driver(String type, int attackNumber, RenderWindow window, int level)
	{
		Blood.getHeroInfo();
		Actors.level();
		if(level==1&&level!=2&&level!=6)
			musica.backMusic1.play();
		if(level==2)
			musica.backMusic1.stop();
		if(level==3&&level!=2&&level!=6) {
			musica.backMusic1.stop();
			musica.backMusic3.play();
		}
		if(level==4&&level!=2&&level!=6) {
			musica.backMusic1.stop();
			musica.backMusic3.stop();
			musica.backMusic4.play();
		}
		if(level==5&&level!=2&&level!=6) {
			musica.backMusic1.stop();
			musica.backMusic3.stop();
			musica.backMusic4.stop();
			musica.backMusic5.play();
		}
		if(level==6) {
			musica.backMusic1.stop();
			musica.backMusic3.stop();
			musica.backMusic4.stop();
			musica.backMusic5.stop();
		}
		if(level==7&&level!=2&&level!=6) {
			musica.backMusic1.stop();
			musica.backMusic3.stop();
			musica.backMusic4.stop();
			musica.backMusic5.stop();
			musica.backMusic7.play();
		}
		if(level==8&&level!=2&&level!=6) {
			musica.backMusic1.stop();
			musica.backMusic3.stop();
			musica.backMusic4.stop();
			musica.backMusic5.stop();
			musica.backMusic7.stop();
			musica.backMusic8.play();
		}
		if(level==9&&level!=2&&level!=6) {
			musica.backMusic1.stop();
			musica.backMusic3.stop();
			musica.backMusic4.stop();
			musica.backMusic5.stop();
			musica.backMusic7.stop();
			musica.backMusic8.stop();
			musica.backMusic9.play();
		}
		if(level==10&&level!=2&&level!=6) {
			musica.backMusic1.stop();
			musica.backMusic3.stop();
			musica.backMusic4.stop();
			musica.backMusic5.stop();
			musica.backMusic7.stop();
			musica.backMusic8.stop();
			musica.backMusic9.stop();
			musica.backMusic10.play();
		}

		V1=new Velocity();
		this.type = type;
		this.level = level;
		selectChara(type);
		readMap(level);
		canMoveX = false;
		canMoveY = false;
		decideNumber();
		if(level == 4)
			this.curBg = new Background(level,-800,-1250);
		else
			this.curBg = new Background(level,-800,-1200);
		allEnermies = new addEnemies(curBg.sort);

		player.isDead = false;

		V1.initializePositionQ_hero(player.x, player.y);//灏嗘垜鏂瑰�????��殑鍧愭爣闃熷垪缁勪腑鐨勬瘡涓潗鏍囬�??��濆鍖栦负�?�為檯鍒濆鍧愭�???
		V1.initializePositionQ_enemy(allEnermies.allBoss.get(0).x, allEnermies.allBoss.get(0).y);//灏嗘晫鏂瑰�????��殑鍧愭爣闃熷垪缁勪腑鐨勬瘡涓潗鏍囬�??��濆鍖栦负�?�為檯鍒濆鍧愭�???
		ones = new addNPCs();
		if(level == 4)
			covers = new Background(-4, -800, -1250);
		else
			covers = new Background(-level, -800, -1200);
		//covers = new Background(-level, -800, -1200);
		counter=new int[30];
		for(int i=0; i < counter.length; i++) {
			counter[i] = 0;
		}
		guide = new guideMap(window, level);
		thread = new Thread(this);
		thread.start();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp startTime = new Timestamp(System.currentTimeMillis());//Gets the time of the current system
		mark=new Mark(startTime);
	}

	//Set the drawable window of the game. All the pictures are drawn on it.
	public void setWindow(RenderWindow window,int level)
	{
	
		Blood.getHeroInfo();
		this.window = window;

		guide.setWindow(window);
		ones.setWindow(window);
		ones.setLevel(level);
		itemsOnMap = new addItemsOnMap(level, window);
		cards = new HeroSkillCards(window);
		playerShadow = new PlayerShadow(this.type, window);
		equipments = new HeroEquipments(window);
	}

	public void decideNumber()
	{
		if(level == 1)
			totalMission = 3;
		else
			totalMission = 2;
		finishedMission = 0;
	}

	public void selectChara(String type)
	{
		if(type.equals("Saber"))
			player = new Saber();
		else if(type.equals("Lancer"))
		{
			//System.out.println("hhhhhhhhhhhhhhh");
			player = new Lancer();
		}
		else if(type.equals("Knight"))
			player = new Knight();
	}

	public void rightMove()
	{
		moveX=3;
	}
	public void leftMove()
	{
		moveX=-3;
	}
	public void upMove()
	{
		moveY=3;
	}
	public void downMove()
	{
		moveY=-3;
	}
	public void rightStop()
	{
		moveX=0;
	}
	public void leftStop()
	{
		moveX=0;
	}
	public void upStop()
	{
		moveY=0;
	}
	public void downStop()
	{
		moveY=0;
	}

	public void playerRightMove()
	{
		playerMoveX=3;
	}
	public void playerLeftMove()
	{
		playerMoveX=-3;
	}
	public void playerUpMove()
	{
		playerMoveY=3;
	}
	public void playerDownMove()
	{
		playerMoveY=-3;
	}
	public void playerRightStop()
	{
		playerMoveX=0;
	}
	public void playerLeftStop()
	{
		playerMoveX=0;
	}
	public void playerUpStop()
	{
		playerMoveY=0;
	}
	public void playerDownStop()
	{
		playerMoveY=0;
	}
	public void playerController(String behave)
	{
		player.order = behave;
	}

	/*****************************************************************************************************************************/
	//Draw all the containers on the window
	public void onDraw()
	{
		//System.out.println(finishedMission +  " AAAAAAAAAAAAAAAAAA");
		if(!isPlay)
		{
			//TDMusicPlay.backgroundMain1.play();
			isPlay = true;
		}
		if(allEnermies.allBoss.size() != 0)
			V1.RefreshThePositions((float)player.x, (float)player.y, (float)allEnermies.allBoss.get(0).x, (float)allEnermies.allBoss.get(0).y);

		this.player.container.setPosition(player.x,player.y);
		this.curBg.container.setPosition(this.curBg.x, this.curBg.y);
		this.covers.container.setPosition(this.covers.x, this.covers.y);
		window.clear(Color.WHITE);
		window.draw(this.curBg.container);
		for(int i = 0; i < this.player.allMagic.size(); i++)
		{
			Magic magic = this.player.allMagic.get(i);
			window.draw(magic.container);
		}

		synchronized (this.allEnermies.allEnermy)
		{
			Iterator<Enemies> iter2 = this.allEnermies.allEnermy.iterator();
			while(iter2.hasNext())
			{
				Enemies en = iter2.next();
				en.container.setPosition(en.x,en.y);
				RectangleShape rectangular2=new RectangleShape();
				rectangular2.setSize(new Vector2f((float) en.hpEnemy,6));
				rectangular2.setFillColor(new Color(220,20,60,100));
				rectangular2.setPosition(en.x-50,en.y-100);
				window.draw(en.container);
				window.draw(rectangular2);
				for(int i = 0; i < en.allHittingEffect.size(); i++)
				{
					//System.out.println(this.allEnermies.allBoss.get(0).allHittingEffect.size());
					HittingEffect hittingEffect = en.allHittingEffect.get(i);
					window.draw(hittingEffect.container);
				}

			}
		}
		add(); //the attribution of equipment
		Blood.update();
		//EnemiesMagic
		for(Entry<EnemyMagic, Integer> entry :allMagic.entrySet()){
			EnemyMagic mapKey = entry.getKey();
			mapKey.container.setPosition(mapKey .x,mapKey .y);
			window.draw(mapKey.container);
		}

		synchronized (this.allEnermies.allBoss)
		{
			Iterator<Boss> iter3 = this.allEnermies.allBoss.iterator();
			while(iter3.hasNext())
			{
				Boss boss=iter3.next();
				boss.container.setPosition(boss.x,boss.y);
				RectangleShape rectangular2=new RectangleShape();
				rectangular2.setSize(new Vector2f((float) boss.hpEnemy/2,7));
				rectangular2.setFillColor(new Color(220,20,60,200));
				rectangular2.setPosition(boss.x-50,boss.y-100);
				window.draw(boss.container);
				window.draw(rectangular2);
				for(int i = 0; i < boss.allHittingEffect.size(); i++)
				{
					//System.out.println(this.allEnermies.allBoss.get(0).allHittingEffect.size());
					HittingEffect hittingEffect = boss.allHittingEffect.get(i);
					window.draw(hittingEffect.container);
				}

			}
		}

		playerShadow.onDraw(this.player.direction, player.x, player.y);

		window.draw(this.player.container);
		//Draw the treasure of NPCs, so that these can hide under the tree.
		for(int i = 0; i < ones.getAllNPCs().size(); i++)
			ones.getAllNPCs().get(i).thisItem.onDraw();
		itemsOnMap.onDraw();
		itemsOnMap.getThreeVariable();

		window.draw(this.covers.container);

		itemsOnMap.drawOthers();
		for(int i = 0; i < monstersItems.size(); i++)
			monstersItems.get(i).onDraw(equipments.getIsFull());

		ones.onDraw();

		guide.onDraw();
	}


	public void checkMapCollision()
	{
		canMoveX = false;
		canMoveY = false;
		int i = -curBg.y + player.y;
		int j = -curBg.x + player.x;
		playerRelativeToBg_x=j;
		playerRelativeToBg_y=i;
		//System.out.println(playerRelativeToBg_x+" "+playerRelativeToBg_y);
		//System.out.println((600 + myX) + " " + (myY + 300) + " Xing Ke");
		//down
		if(Map[i-1][j] != 1 && Map[i-2][j] != 1 && Map[i-3][j] != 1 && Map[i-4][j] != 1 &&
				Map[i-5][j] != 1 && moveY>0 && i-50 < Map.length)
		{
			canMoveY = true;
		}
		//up
		else if(Map[i+1][j] != 1 && Map[i+2][j] != 1 && Map[i+3][j] != 1 &&Map[i+4][j] != 1 &&
				Map[i+5][j] != 1 && moveY < 0 && i+50 > 0)
		{
			canMoveY = true;
		}
		//right
		if(Map[i][j-1] != 1 && Map[i][j-2] != 1 &&Map[i][j-3] != 1 &&Map[i][j-4] != 1 &&
				Map[i][j-5] != 1 && moveX>0 && j-50 < Map[0].length )
		{
			canMoveX = true;
		}
		//left
		else if(Map[i][j+1] != 1 && Map[i][j+2] != 1 && Map[i][j+3] != 1 && Map[i][j+4] != 1
				&& Map[i][j+5] != 1 && moveX<0 && j+50>0)
		{
			canMoveX = true;
		}
	}


	private void move()
	{
		for(int i=0;i<allEnermies.allEnermy.size();i++)
		{
			Enemies en=this.allEnermies.allEnermy.get(i);
			if(en.state != "dead")
			{
				if(en.isLeft && en.state.equals("move"))
				{
					en.x = en.x-1;
					en.distanceX = en.distanceX-1;
					en.direction = "left";
				}
				else if(en.isRight && en.state.equals("move"))
				{
					en.x=en.x+1;
					en.distanceX = en.distanceX+1;
					en.direction = "right";
				}
				else if(en.isUp && en.state.equals("move"))
				{
					en.y = en.y-1;
					en.distanceY = en.distanceY-1;
					en.direction = "left";
				}
				else if(en.isDown && en.state.equals("move"))
				{
					en.y = en.y+1;
					en.distanceY = en.distanceY+1;
					en.direction = "right";
				}
			}
		}

		//Boss
		for(int i=0;i<allEnermies.allBoss.size();i++)
		{
			Boss boss=this.allEnermies.allBoss.get(i);
			if(boss.state != "dead")
			{
				if(Math.sqrt((boss.x-player.x)*(boss.x-player.x)+(boss.y-player.y)*(boss.y-player.y))>80
						&&boss.distanceX<=boss.rightLimit&&boss.distanceX>=boss.leftLimit
						&&boss.distanceY<=boss.downLimit&&boss.distanceY>=boss.upLimit
						&&player.x>=boss.originLeftLimit&&player.x<=boss.originRightLimit
						&&player.y>=boss.originUpLimit&&player.y<=boss.originDownLimit){
					//System.out.println("if");
					boss.x=  boss.x+V1.VxOfTheEnemy();
					boss.distanceX= boss.distanceX+V1.VxOfTheEnemy();
					boss.y=  boss.y+V1.VyOfTheEnemy();
					boss.distanceY=boss.distanceY+V1.VyOfTheEnemy();
					if(!boss.state.contains("attack")&&V1.VxOfTheEnemy()>=0)
					{
						boss.isLeft=false;
						boss.isRight=true;
					}
					else if(!boss.state.contains("attack")&&V1.VxOfTheEnemy()<0)
					{
						boss.isLeft=true;
						boss.isRight=false;
					}
				}
				else{
					//System.out.println("else");
					if (boss.isLeft && boss.state.equals("move")) {
						boss.x = boss.x - 1;
						boss.distanceX = boss.distanceX - 1;
					}
					if (boss.isRight && boss.state.equals("move")) {
						boss.x = boss.x + 1;
						boss.distanceX = boss.distanceX + 1;
					}
					if (boss.isUp && boss.state.equals("move")) {
						boss.y = boss.y - 1;
						boss.distanceY = boss.distanceY - 1;
					}
					if (boss.isDown && boss.state.equals("move")) {
						boss.y = boss.y + 1;
						boss.distanceY = boss.distanceY + 1;
					}
				}
			}
		}

		if(canMoveX)
		{
			if(playerRelativeToBg_x-600>=0&&playerRelativeToBg_x+900<=curBg.width){
				curBg.x = curBg.x + moveX;
				for (int i = 0; i < allEnermies.allEnermy.size(); i++)
					allEnermies.allEnermy.get(i).x = allEnermies.allEnermy.get(i).x + moveX;
				for (int i = 0; i < allEnermies.allBoss.size(); i++) {
					allEnermies.allBoss.get(i).x = allEnermies.allBoss.get(i).x + moveX;
					allEnermies.allBoss.get(i).originLeftLimit = allEnermies.allBoss.get(i).originLeftLimit + moveX;
					allEnermies.allBoss.get(i).originRightLimit = allEnermies.allBoss.get(i).originRightLimit + moveX;
				}
				Set<EnemyMagic> keySet = allMagic.keySet();
				for (Iterator<EnemyMagic> it = keySet.iterator(); it.hasNext(); ) {
					EnemyMagic s = it.next();
					s.x = s.x + moveX;
				}
				ones.setX(moveX);
				itemsOnMap.setPositionX(moveX);
				myX -= moveX;
				for (int i = 0; i < monstersItems.size(); i++)
					monstersItems.get(i).setX(moveX);
			}
			else
			{
				if(player.x<1150&&playerMoveX>0||(player.x>50&&playerMoveX<0))
					player.x=player.x+playerMoveX;
			}
		}


		if(canMoveY)
		{
			if(playerRelativeToBg_y-300>=0&&playerRelativeToBg_y+500<=curBg.height){
				curBg.y = curBg.y + moveY;
				for (int i = 0; i < allEnermies.allEnermy.size(); i++) {
					allEnermies.allEnermy.get(i).y = allEnermies.allEnermy.get(i).y + moveY;
				}
				for (int i = 0; i < allEnermies.allBoss.size(); i++) {
					allEnermies.allBoss.get(i).y = allEnermies.allBoss.get(i).y + moveY;
					allEnermies.allBoss.get(i).originUpLimit = allEnermies.allBoss.get(i).originUpLimit + moveY;
					allEnermies.allBoss.get(i).originDownLimit = allEnermies.allBoss.get(i).originDownLimit + moveY;
				}
				Set<EnemyMagic> keySet = allMagic.keySet();
				for (Iterator<EnemyMagic> it = keySet.iterator(); it.hasNext(); ) {
					EnemyMagic s = it.next();
					s.y = s.y + moveY;
				}
				ones.setY(moveY);
				myY -= moveY;
				itemsOnMap.setPositionY(moveY);
				for (int i = 0; i < monstersItems.size(); i++)
					monstersItems.get(i).setY(moveY);
			}
			else
			{
				if((playerMoveY>0&&player.y<550)||(player.y>50&&playerMoveY<0))
					player.y=player.y+playerMoveY;
			}
		}

		covers.x = curBg.x;
		covers.y = curBg.y;
		this.guide.setCoor(-curBg.x + 600, -curBg.y + 300);
	}
	public void add()
	{
		player.equiHp=(float)equipments.getAttributions().get(0);
		player.equiMana=(float)equipments.getAttributions().get(1);
		player.equiSpeed=(float)equipments.getAttributions().get(2);
		player.equiDamage=(float) equipments.getAttributions().get(3);
		//System.out.println(player.equiDamage);
		Blood.equihero();
	}

	public void dead(Enemies e)
	{
		if(level == 1||level==3||level==4||level==5)
		{
			int num = (int) (Math.random() * 5 + 1);
			if(num < 3)
			{
				int random = (int) (Math.random() * 5 + 1);
				if(random == 1)
				{
					int cat = 1;
					monstersItems.add(new MonstersItems(cat, 1, window, e.x, e.y));
				}
				else if(random == 2)
				{
					int cat = 2;
					int number = 0;
					int suit = (int) (Math.random() * 5 + 1);
					if(suit >= 3)
						number = 1;
					else
						number = 2;
					monstersItems.add(new MonstersItems(cat, number, window, e.x, e.y));
				}
				else if(random >= 3)
				{
					int cat = 3;
					int number = 0;
					int weapon = (int) (Math.random() * 5 + 1);
					if(weapon >= 3)
						number = 1;
					else
						number = 2;
					monstersItems.add(new MonstersItems(cat, number, window, e.x, e.y));
				}
			}
		}
		else if(level == 8||level==7||level==9||level==10)
		{
			int num = (int) (Math.random() * 6 + 1);
			if(num < 4)
			{
				int random = (int) (Math.random() * 5 + 1);
				if(random == 1)
				{
					int cat = 1;
					monstersItems.add(new MonstersItems(cat, 1, window, e.x, e.y));
				}
				else if(random == 2)
				{
					int cat = 2;
					int number = 0;
					int suit = (int) (Math.random() * 5 + 1);
					if(suit >= 2)
						number = 1;
					else
						number = 2;
					monstersItems.add(new MonstersItems(cat, number, window, e.x, e.y));
				}
				else if(random >= 3)
				{
					int cat = 3;
					int number = 0;
					int weapon = (int) (Math.random() * 5 + 1);
					if(weapon >= 3)
						number = 1;
					else
						number = 2;
					monstersItems.add(new MonstersItems(cat, number, window, e.x, e.y));
				}
			}
		}
		musica.enemies_died.play();
		allEnermies.allEnermy.remove(e);
		count++;
		//System.out.println("died number is "+count);
		e.AddExp();   //get different experience when enemy died

	}

	public void dead(Boss b)
	{
		musica.boss_died.play();
		allEnermies.allBoss.remove(b);
		b.AddExp();
		victory = true;
	}

	private void collisionDetectionWithBoss(boolean heroInRightAttackRange, boolean heroInLeftAttackRange, int[] bossInRightAttackRange, int[] bossInLeftAttackRange, int[] bossNumber)
	{
		synchronized (this.allEnermies.allBoss)
		{
			for(int i=0; i<allEnermies.allBoss.size(); i++)
			{
				Boss b = this.allEnermies.allBoss.get(i);
				if(b.shootByHero(player) && player.x <= b.x)
				{
					heroInRightAttackRange = true;
					bossNumber[i]=1;
				}
				else if(b.shootByHero(player) && player.x >= b.x)
				{
					heroInLeftAttackRange = true;
					bossNumber[i] = 1;
				}

				//Check if collision succeed
				if((heroInRightAttackRange && bossNumber[i] == 1 &&
						player.direction.equals("right") && player.x <= b.x)||(heroInLeftAttackRange && bossNumber[i] == 1
						&&player.direction.equals("left") && player.x > b.x))
				{
					if(!player.down && !player.up && !player.left && !player.right && !player.isSkill && player.isAttack)
					{
						//Normal Attack of the player
						//if(player.indexY == 1 && en.isHurtFinish==false) {
						if (player.indexX == 0 && player.indexY == 1&&b.isHurtFinish==false) {
							b.state = "hurt";
							if (decreasehp == true) {
								b.attackenemy(player.totalDamage);
							}
							if (player.indexX == 2 && player.indexY == 1)
								decreasehp = false;

							if (b.hpEnemy <= 0)
								break;
						}
					}
					else if (!player.down && !player.up && !player.left && !player.right && player.isSkill && !player.isAttack) {
						if (player.skillNumber == 1)
						{
							if(player.heroType=="Lancer") {
								//if((player.indexY==2 ||player.indexY==1)&& en.isHurtFinish==false) {
								if((player.indexX==1&&player.indexY==1||player.indexX==5&&player.indexY==2)&& b.isHurtFinish==false ){
									b.state="hurt";
									if (decreasehp == true) {
										b.skillattackenemy1(player.damageHeroSkill1);
										b.manaSkill(player.damageHeroSkill1);
										//System.out.println("the mana is "+player.manaHero);
										//System.out.println("the hpene is "+en.hpEnemy);
									}
									if ((player.indexX == 2 && player.indexY == 1) || (player.indexX == 6 && player.indexY == 3))
										decreasehp = false;
									if (b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);
								}
							}

							//Skill1
							else if(player.heroType=="Knight"||player.heroType=="Saber") {
								///if( player.indexY==1 && en.isHurtFinish==false)
								//{
								//en.state="hurt";
								if((player.indexX==0 && player.indexY==1)&& b.isHurtFinish==false) {
									b.state="hurt";
									if (decreasehp == true) {
										b.skillattackenemy1(player.damageHeroSkill1);
										b.manaSkill(player.damageHeroSkill1);
									}

									if (player.indexX == 3 && player.indexY == 1)
										decreasehp = false;
									if (b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);
								}
							}
						}
						else if (player.skillNumber==2)
						{
							if(player.heroType=="Lancer") {
								//Skill2
								//if(player.indexY==6 && en.isHurtFinish==false)
								//{

								if(player.indexX ==0&&player.indexY==6&& b.isHurtFinish==false) {
									b.state="hurt";
									if (decreasehp == true) {
										b.skillattackenemy2(player.damageHeroSkill2);
										b.manaSkill(player.damageHeroSkill2);
									}
									if (player.indexX == 2 && player.indexY == 6)
										decreasehp = false;

									if (b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);
								}
							}

							else if(player.heroType=="Saber") {
								//if(player.indexY==2 && en.isHurtFinish==false)
								//{
								if((player.indexX ==1&&player.indexY==2)&& b.isHurtFinish==false)
								{
									b.state="hurt";
									if(decreasehp==true) {
										b.skillattackenemy2(player.damageHeroSkill2);
										b.manaSkill(player.damageHeroSkill2);
									}
									if(player.indexX==3&&player.indexY==2)
										decreasehp=false;
									if(b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);
								}
							}
						}
						else if (player.skillNumber==3)
						{
							if(player.heroType=="Knight"||player.heroType=="Saber") {
								//if(player.indexY==1 && en.isHurtFinish==false)
								//{
								if(player.indexX==2&&player.indexY==1&& b.isHurtFinish==false) {
									b.state = "hurt";
									if (decreasehp == true) {
										b.skillattackenemy3(player.damageHeroSkill3);
										b.manaSkill(player.damageHeroSkill3);
									}
									if (player.indexX == 3 && player.indexY == 1)
										decreasehp = false;

									if (b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);

								}
							}
							else if(player.heroType=="Lancer") {
								//if(player.indexY==3 && en.isHurtFinish==false)
								//{
								if((player.indexX==0&&player.indexY==3)&& b.isHurtFinish==false)
								{	b.state="hurt";
									if(decreasehp==true) {
										b.skillattackenemy3(player.damageHeroSkill3);
										b.manaSkill(player.damageHeroSkill3);
									}

									if(player.indexX==2&&player.indexY==3)
										decreasehp=false;
									if(b.hpEnemy<=0)
										break;

									//b.manaSkill(player.damageHero);
								}
							}
						}
						else if (player.skillNumber == 4)
						{
							if(player.heroType=="Saber") {
								//if((player.indexY==2|player.indexY==3) && en.isHurtFinish==false) {
								//en.state="hurt";
								if((player.indexX==2&&player.indexY==2||player.indexX==4&&player.indexY==3)&& b.isHurtFinish==false) {
									b.state="hurt";
									if (decreasehp == true) {
										b.skillattackenemy1(player.damageHeroSkill4);
										b.manaSkill(player.damageHeroSkill4);
									}

									if ((player.indexX == 4 && player.indexY == 2) || (player.indexX == 6 && player.indexY == 3))
										decreasehp = false;

									if (b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);

								}
							}
							else if (player.heroType=="Lancer") {
								//if((player.indexY==1|player.indexY==4) && en.isHurtFinish==false) {
								//en.state="hurt";
								if((player.indexX==3&&player.indexY==1||player.indexX==0&&player.indexY==4) && b.isHurtFinish==false) {
									b.state="hurt";
									if(decreasehp==true) {
										b.skillattackenemy1(player.damageHeroSkill4);
										b.manaSkill(player.damageHeroSkill4);
									}

									if((player.indexX==5&&player.indexY==1)||(player.indexX==3&&player.indexY==4))
										decreasehp=false;
									if(b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);
								}
							}
							else if (player.heroType=="Knight") {
								//if(player.indexY==2 && en.isHurtFinish==false)
								//{
								if((player.indexX ==5&&player.indexY==2)&& b.isHurtFinish==false) {
									b.state="hurt";
									if (decreasehp == true) {
										b.skillattackenemy4(player.damageHeroSkill4);
										b.manaSkill(player.damageHeroSkill4);
									}

									if(player.indexX == 7 && player.indexY == 3)
										decreasehp = false;
									if (b.hpEnemy <= 0)
										break;
									//b.manaSkill(player.damageHero);
								}
							}
						}
					}
				}
				if(b.hpEnemy<=0)
				{
					b.hpEnemy=0;
					b.state="dead";
					if(b.isDeadFinish)
						dead(b);
				}

				//杩涘叆鏁屼汉�?诲嚮鑼冨�??
				if((b.floatRectAttack.intersection(player.floatRectHarm))!=null&&player.x>b.x)
				{
					bossInRightAttackRange[i]=1;
					if(!b.state.equals("hurt")&&!b.state.equals("dead"))
					{
						if(b.isAttackFinish==false) {
							//System.out.println("stateNumer???: "+b.isAttackFinish);
							stateNumber = (int) (Math.random() * 10);
							b.isAttackFinish=true;
						}
						//System.out.println("stateNumer: "+stateNumber);
						if(stateNumber==0||stateNumber==1||stateNumber==2||stateNumber==3||stateNumber==4)
						{
							b.state = "right-attack1";
						}
						else if(stateNumber==5||stateNumber==6)
						{
							b.state = "right-attack2";
						}
						else if(stateNumber==7||stateNumber==8)
						{
							b.state = "right-attack3";
						}
						else if(stateNumber==9)
						{
							b.state = "right-attack4";
						}
						b.direction = "right";
					}
				}
				else if((b.floatRectAttack.intersection(player.floatRectHarm))!=null&&player.x<b.x) {
					bossInLeftAttackRange[i]=1;
					if(!b.state.equals("hurt")&&!b.state.equals("dead")) {
						if(b.isAttackFinish==false) {
							//System.out.println("stateNumer???: "+b.isAttackFinish);
							stateNumber = (int) (Math.random() * 10);
							b.isAttackFinish=true;
						}

						//System.out.println("stateNumer: "+stateNumber);
						if(stateNumber==0||stateNumber==1||stateNumber==2||stateNumber==3||stateNumber==4)
						{
							b.state = "left-attack1";
						}
						else if(stateNumber==5||stateNumber==6)
						{
							b.state = "left-attack2";
						}
						else if(stateNumber==7||stateNumber==8)
						{
							b.state = "left-attack3";
						}
						else if(stateNumber==9)
						{
							b.state = "left-attack4";
						}
						b.direction = "left";
					}
				}
				else {
					if(!b.state.equals("hurt")&&!b.state.equals("dead"))
						b.state="move";
				}
			}
		}
	}

	/*
	 * 瀵版晸閺傘�?��?�归柨鐔告灮閹风兘鏁撻崣顐ゎ暜閹风兘鏁撻????�鍖＄秶閹风兘鏁撻弬銈嗗闁跨喎�?????喊澶???�闁跨喎澹欓悮瀛樺闁跨喐鏋婚幏�????芥晸閸撹法娅㈤幏鐑芥晸閺傘�?��?�归柨鐔告灮閹烽??�鎸掗柨鐔告灮閹风兘鏁撻敓锟�?
	 * @param attackNumber
	 * @param enemyInLeftAttackRange
	 * @param enemyInRightAttackRange
	 * @param heroInLeftAttackRange
	 * @param heroInRightAttackRange
	 */

	private void collisionDetection(boolean heroInRightAttackRange, boolean heroInLeftAttackRange, int[] enemyInRightAttackRange, int[] enemyInLeftAttackRange, int[] attackNumber)
	{
		synchronized (allEnermies.allEnermy)
		{
			for(int i=0; i<allEnermies.allEnermy.size(); i++)
			{
				Enemies en = this.allEnermies.allEnermy.get(i);
				//闁跨喐鏋婚幏鐑芥晸閺傘�?��?�归柨鐔告灮閹峰嘲顧�?柨鐔告灮閹风兘鏁撻弬銈嗗婵棝鏁撴潪鍖℃嫹
				if(en.shootByHero(player) && player.x <= en.x && en.state != "dead")
				{
					heroInRightAttackRange = true;
					attackNumber[i]=1;
				}
				else if(en.shootByHero(player) && player.x >= en.x && en.state != "dead")
				{
					heroInLeftAttackRange = true;
					attackNumber[i] = 1;
				}

				//
				if((heroInRightAttackRange && attackNumber[i] == 1 &&
						player.direction.equals("right") && player.x <= en.x)||(heroInLeftAttackRange && attackNumber[i] == 1
						&&player.direction.equals("left") && player.x > en.x))
				{
					if(!player.down && !player.up && !player.left && !player.right && !player.isSkill && player.isAttack)
					{
						//Normal Attack of the player
						//if(player.indexY == 1 && en.isHurtFinish==false) {
						if (player.indexX == 0 && player.indexY == 1&&en.isHurtFinish==false) {
							en.state = "hurt";
							if (decreasehp == true) {
								en.attackenemy(player.totalDamage);
							}
							if (player.indexX == 2 && player.indexY == 1)
								decreasehp = false;

							if (en.hpEnemy <= 0)
								break;
						}
					}
					else if (!player.down && !player.up && !player.left && !player.right && player.isSkill && !player.isAttack) {
						if (player.skillNumber == 1)
						{
							if(player.heroType=="Lancer") {
								//if((player.indexY==2 ||player.indexY==1)&& en.isHurtFinish==false) {
								if((player.indexX==1&&player.indexY==1||player.indexX==5&&player.indexY==2)&& en.isHurtFinish==false ){
									en.state="hurt";
									if (decreasehp == true) {
										en.skillattackenemy1(player.damageHeroSkill1);
										en.manaSkill(player.damageHeroSkill1);
										//System.out.println("the mana is "+player.manaHero);
										//System.out.println("the hpene is "+en.hpEnemy);
									}
									if ((player.indexX == 2 && player.indexY == 1) || (player.indexX == 6 && player.indexY == 3))
										decreasehp = false;
									if (en.hpEnemy <= 0)
										break;
									//en.manaSkill(player.damageHero);
								}
							}

							//Skill1
							else if(player.heroType=="Knight"||player.heroType=="Saber") {
								///if( player.indexY==1 && en.isHurtFinish==false)
								//{
								//en.state="hurt";
								if((player.indexX==0 && player.indexY==1)&& en.isHurtFinish==false) {
									en.state="hurt";
									if (decreasehp == true) {
										en.skillattackenemy1(player.damageHeroSkill1);
										en.manaSkill(player.damageHeroSkill1);
									}

									if (player.indexX == 3 && player.indexY == 1)
										decreasehp = false;
									if (en.hpEnemy <= 0)
										break;
									//en.manaSkill(player.damageHero);
								}
							}
						}
						else if (player.skillNumber==2)
						{
							if(player.heroType=="Lancer") {
								//Skill2
								//if(player.indexY==6 && en.isHurtFinish==false)
								//{

								if(player.indexX ==0&&player.indexY==6&& en.isHurtFinish==false) {
									en.state="hurt";
									if (decreasehp == true) {
										en.skillattackenemy2(player.damageHeroSkill2);
										en.manaSkill(player.damageHeroSkill2);
									}
									if (player.indexX == 2 && player.indexY == 6)
										decreasehp = false;

									if (en.hpEnemy <= 0)
										break;
									//en.manaSkill(player.damageHero);
								}
							}

							else if(player.heroType=="Saber") {
								//if(player.indexY==2 && en.isHurtFinish==false)
								//{
								if((player.indexX ==1&&player.indexY==2)&& en.isHurtFinish==false)
								{
									en.state="hurt";
									if(decreasehp==true) {
										en.skillattackenemy2(player.damageHeroSkill2);
										en.manaSkill(player.damageHeroSkill2);
									}
									if(player.indexX==3&&player.indexY==2)
										decreasehp=false;
									if(en.hpEnemy <= 0)
										break;
									//en.manaSkill(player.damageHero);
								}
							}
						}
						else if (player.skillNumber==3)
						{
							if(player.heroType=="Knight"||player.heroType=="Saber") {
								//if(player.indexY==1 && en.isHurtFinish==false)
								//{
								if(player.indexX==2&&player.indexY==1&& en.isHurtFinish==false) {
									en.state = "hurt";
									if (decreasehp == true) {
										en.skillattackenemy3(player.damageHeroSkill3);
										en.manaSkill(player.damageHeroSkill3);
									}
									if (player.indexX == 3 && player.indexY == 1)
										decreasehp = false;

									if (en.hpEnemy <= 0)
										break;
									en.manaSkill(player.damageHero);

								}
							}
							else if(player.heroType=="Lancer") {
								//if(player.indexY==3 && en.isHurtFinish==false)
								//{
								if((player.indexX==0&&player.indexY==3)&& en.isHurtFinish==false)
								{	en.state="hurt";
									if(decreasehp==true) {
										en.skillattackenemy3(player.damageHeroSkill3);
										en.manaSkill(player.damageHeroSkill3);
									}

									if(player.indexX==2&&player.indexY==3)
										decreasehp=false;
									if(en.hpEnemy<=0)
										break;

									//en.manaSkill(player.damageHero);
								}
							}
						}
						else if (player.skillNumber == 4)
						{
							if(player.heroType=="Saber") {
								//if((player.indexY==2|player.indexY==3) && en.isHurtFinish==false) {
								//en.state="hurt";
								if((player.indexX==2&&player.indexY==2||player.indexX==4&&player.indexY==3)&& en.isHurtFinish==false) {
									en.state="hurt";
									if (decreasehp == true) {
										en.skillattackenemy1(player.damageHeroSkill4);
										en.manaSkill(player.damageHeroSkill4);
									}

									if ((player.indexX == 4 && player.indexY == 2) || (player.indexX == 6 && player.indexY == 3))
										decreasehp = false;

									if (en.hpEnemy <= 0)
										break;
									//en.manaSkill(player.damageHero);

								}
							}
							else if (player.heroType=="Lancer") {
								//if((player.indexY==1|player.indexY==4) && en.isHurtFinish==false) {
								//en.state="hurt";
								if((player.indexX==3&&player.indexY==1||player.indexX==0&&player.indexY==4) && en.isHurtFinish==false) {
									en.state="hurt";
									if(decreasehp==true) {
										en.skillattackenemy1(player.damageHeroSkill4);
										en.manaSkill(player.damageHeroSkill4);
									}

									if((player.indexX==5&&player.indexY==1)||(player.indexX==3&&player.indexY==4))
										decreasehp=false;
									if(en.hpEnemy <= 0)
										break;
									//en.manaSkill(player.damageHero);
								}
							}
							else if (player.heroType=="Knight") {
								//if(player.indexY==2 && en.isHurtFinish==false)
								//{
								if((player.indexX ==5&&player.indexY==2)&& en.isHurtFinish==false) {
									en.state="hurt";
									if (decreasehp == true) {
										en.skillattackenemy4(player.damageHeroSkill4);
										en.manaSkill(player.damageHeroSkill4);
									}

									if(player.indexX == 7 && player.indexY == 3)
										decreasehp = false;
									if (en.hpEnemy <= 0)
										break;
									//en.manaSkill(player.damageHero);
								}
							}
						}
					}
				}

				if(en.hpEnemy <= 0)
				{
					en.hpEnemy = 0;
					en.state = "dead";
					if(en.isDeadFinish)
						dead(en);
				}

				//Enter enemies attack range
				if((en.floatRectAttack.intersection(player.floatRectHarm))!=null&&player.x>en.x)
				{
					enemyInRightAttackRange[i]=1;
					if(!en.state.equals("hurt")&&!en.state.equals("dead"))
					{
						en.state = "right-attack";
						en.direction = "right";
						if(en.type==5||en.type==10)
						{
							EnemyMagic m=shoot(en.x,en.y,1,i);
							magicMove(en.x,en.y,player.x,player.y,i,m);
							shootHero();
							stopShoot();
						}
					}
				}

				//Enter enemies attack range
				else if((en.floatRectAttack.intersection(player.floatRectHarm))!=null&&player.x<en.x) {
					enemyInLeftAttackRange[i]=1;
					if(!en.state.equals("hurt")&&!en.state.equals("dead")) {
						en.state = "left-attack";
						en.direction = "left";
						if(en.type==5||en.type==10)
						{
							EnemyMagic m=shoot(en.x,en.y,1,i);
							magicMove(en.x,en.y,player.x,player.y,i,m);
							shootHero();
							stopShoot();
						}
					}
					else{
						removed(i);
					}

				}

				else {
					if(!en.state.equals("hurt")&&!en.state.equals("dead"))
						en.state="move";
					removed(i);
				}
			}
		}
	}

	public EnemyMagic shoot(int enemyX,int enemyY,int type,int j) {
		counter[j]++;
		if(counter[j]>=30) {
			EnemyMagic enemyMagic =new EnemyMagic(enemyX,enemyY,type);
			allMagic.put(enemyMagic,j);
			counter[j]=0;
			return enemyMagic;
		}
		return null;
	}

	public void magicMove(int enemyX,int enemyY,int heroX,int heroY,int j,EnemyMagic m)
	{
		Set<java.util.Map.Entry<EnemyMagic, Integer>> entrySet = allMagic.entrySet();
		for(Iterator<Entry<EnemyMagic, Integer>> it = entrySet.iterator();it.hasNext();) {
			Entry<EnemyMagic, Integer> s = it.next();
			if(s.getValue()==j) {
				int moveX=(heroX-enemyX)/10;
				int moveY=(heroY-enemyY)/10;
				s.getKey().x+=moveX;
				s.getKey().distanceX+=moveX;
				s.getKey().y+=moveY;
				s.getKey().distanceY+=moveY;
				//System.out.println(s.getKey().getDistanceX()+" "+s.getKey().getDistanceY());
			}
		}

	}

	//Whether shot the hero
	public void shootHero() {
		for(Entry<EnemyMagic, Integer> entry :allMagic.entrySet()){
			EnemyMagic mapKey = entry.getKey();
			if(Math.sqrt((mapKey.x-player.x)*(mapKey.x-player.x)+(mapKey.y-player.y)*(mapKey.y-player.y))<30) {
				allMagic.remove(mapKey);
			}
		}
	}

	public void stopShoot()
	{
		for(Entry<EnemyMagic, Integer> entry :allMagic.entrySet()){
			EnemyMagic mapKey = entry.getKey();
			if(mapKey.distanceX>=200||mapKey.distanceX<=-200||
					mapKey.distanceY>=200||mapKey.distanceY<=-200) {
				allMagic.remove(mapKey);
			}
		}
	}

	public void removed(int i) {
		for(Entry<EnemyMagic, Integer> entry :allMagic.entrySet()){
			EnemyMagic mapKey = entry.getKey();
			int value=entry.getValue();
			if(value==i) {
				allMagic.remove(mapKey);
			}
		}
	}
	public void readMap(int level)
	{
		File ctoFile = new File(Path + "/MapFile/MAPresult" + level);
		InputStreamReader reading_array = null;
		try {
			reading_array = new InputStreamReader(new FileInputStream(ctoFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader counting = new BufferedReader(reading_array);
		String txtline = null;
		if(level == 1)
			Map = new int[5120][7168];
		else if(level == 3)
			Map = new int[3584][5228];
		else if(level == 4)
			Map = new int[5000][5000];
		else if(level == 5)
			Map = new int[5634][5376];
		else if(level == 7)
			Map = new int[9000][9000];
		else if(level == 8)
			Map = new int[6400][6600];
		else if(level == 9)
			Map = new int[4500][5400];
		else if(level == 10)
			Map = new int[5120][5632];
		int num = 0;
		try {
			while ((txtline = counting.readLine()) != null)
			{
				for(int i = 0; i< txtline.length(); i++)
					this.Map[num][i] = Integer.valueOf(txtline.substring(i, i+1));
				num++;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run()
	{
		while(!player.isDead)
		{
			//System.out.println("background: "+curBg.x+" "+curBg.y);
			//System.out.println("player: "+player.x+" "+player.y);
			if(Blood.getDeathState())
			{
				player.isDead = true;
				numberOfDeath++;
				musica.gameover.play();
			}
			//When player is dead, end the threads
			if(player.isDead == true)
			{
				stopShoot();
				this.playerShadow.isEnd = true;
				for(int i = 0; i < allEnermies.allEnermy.size(); i++)
					allEnermies.allEnermy.get(i).setEnd();
				for(int i = 0; i < allEnermies.allBoss.size(); i++)
					allEnermies.allBoss.get(i).setEnd();
				for(int i = 0; i < ones.getAllNPCs().size(); i++)
					ones.getAllNPCs().get(i).setEnd();
			}

			if(victory)
			{
				//TDMusicPlay.backgroundMain1.stop();
				for(int i = 0; i < allEnermies.allEnermy.size(); i++)
					allEnermies.allEnermy.get(i).setEnd();
				for(int i = 0; i < allEnermies.allBoss.size(); i++)
					allEnermies.allBoss.get(i).setEnd();
				for(int i = 0; i < ones.getAllNPCs().size(); i++)
					ones.getAllNPCs().get(i).setEnd();
			}

			//When level is changed, end the threads
			if(changeToAnotherLevel == true)
			{
				this.playerShadow.isEnd = true;
				for(int i = 0; i < allEnermies.allEnermy.size(); i++)
					allEnermies.allEnermy.get(i).setEnd();
				for(int i = 0; i < allEnermies.allBoss.size(); i++)
					allEnermies.allBoss.get(i).setEnd();
				for(int i = 0; i < ones.getAllNPCs().size(); i++)
					ones.getAllNPCs().get(i).setEnd();
				//allEnermies.allEnermy.clear();
				//allEnermies.allBoss.clear();
				this.isClearFinished = true;
				//musica.backMusic.stop();
			}

			if(!Blood.getDeathState() && !victory)
			{
				boolean heroInRightAttackRange=false;
				boolean heroInLeftAttackRange=false;
				int enemyInRightAttackRange[]=new int[30];
				int enemyInLeftAttackRange[]=new int[30];
				//闁跨喐鏋婚幏鐑芥晸閺傘�?��?�归柨鐔告灮閹风�??��撻弬銈嗗闁跨噦鎷�???
				int attackNumber[] = new int[30];
				for(int i=0;i<attackNumber.length;i++)
				{
					enemyInLeftAttackRange[i]=0;
					enemyInRightAttackRange[i]=0;
					attackNumber[i]=0;
				}

				boolean heroInRightAttackRange_boss=false;
				boolean heroInLeftAttackRange_boss=false;
				int bossInRightAttackRange[]=new int[2];
				int bossInLeftAttackRange[]=new int[2];
				//闁跨喐鏋婚幏鐑芥晸閺傘�?��?�归柨鐔告灮閹风�??��撻弬銈嗗闁跨噦鎷�???
				int bossNumber[] = new int[2];
				for(int i=0;i<bossNumber.length;i++)
				{
					enemyInLeftAttackRange[i]=0;
					enemyInRightAttackRange[i]=0;
					bossNumber[i]=0;
				}
				checkMapCollision();
				collisionDetection(heroInRightAttackRange, heroInLeftAttackRange,
						enemyInRightAttackRange, enemyInLeftAttackRange, attackNumber);
				collisionDetectionWithBoss(heroInRightAttackRange_boss, heroInLeftAttackRange_boss,
						bossInRightAttackRange,bossInLeftAttackRange,bossNumber);
				if(!this.player.isDead)
					move();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}
}
