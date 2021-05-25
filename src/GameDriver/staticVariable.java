package GameDriver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jsfml.graphics.ConstTexture;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.Texture;

/*
 * 这个类是加载所有图片资源的类
 * 将任务角色，背景，子弹等加载进来
 * 具体方法见GameProject4的staticVariable类
 */

public class staticVariable 
{
	public static String imagePath = System.getProperty("user.dir");
	//Map and its covers.
	public static List<ConstTexture> allBackgrounds = new ArrayList<ConstTexture>();
	public static List<ConstTexture> covers = new ArrayList<ConstTexture>();
	public static List<ConstTexture> guideMaps = new ArrayList<ConstTexture>();
	//Pictures for the selected hero.
	public static List<ConstTexture> HeroRun = new ArrayList<ConstTexture>();
	public static List<ConstTexture> HeroAttackLeft = new ArrayList<ConstTexture>();
	public static List<ConstTexture> HeroAttackRight = new ArrayList<ConstTexture>();
	public static List<ConstTexture> HeroReady = new ArrayList<ConstTexture>();
	public static List<ConstTexture> HeroDead = new ArrayList<ConstTexture>();
	//Hero Skills
	public static List<ConstTexture> HeroSkill1 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> HeroSkill2 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> HeroSkill3 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> HeroSkill4 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> effectSkill1 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> effectSkill2 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> effectSkill3 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> effectSkill4 = new ArrayList<ConstTexture>();
	//Hero Icon
	public static List<ConstTexture> heroIcons = new ArrayList<ConstTexture>();
	//Enemies for 500*500
	public static List<ConstTexture> enemyLeftIdle = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyRightIdle = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyLeftMove = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyRightMove = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyLeftAttack = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyRightAttack = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyLeftHurt = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyRightHurt = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyLeftDie = new ArrayList<ConstTexture>();
	public static List<ConstTexture> enemyRightDie = new ArrayList<ConstTexture>();

	//Boss
	public static List<ConstTexture> BossLeftIdle = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightIdle = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossLeftMove = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightMove = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossLeftAttack1 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightAttack1 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossLeftAttack2 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightAttack2 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossLeftAttack3 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightAttack3 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossLeftAttack4 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightAttack4 = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossLeftHurt = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightHurt = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossLeftDie = new ArrayList<ConstTexture>();
	public static List<ConstTexture> BossRightDie = new ArrayList<ConstTexture>();



	//NPCs
	public static List<ConstTexture> NPCs = new ArrayList<ConstTexture>();

	public static ConstTexture mousePic;

	//Dialog pictures
	public static List<ConstTexture> dialogs = new ArrayList<ConstTexture>();
	public static Font dialogFont = new Font();
	public static ConstTexture dialogIcon;
	public static List<ConstTexture> buttons = new ArrayList<ConstTexture>();
	public static ConstTexture missionList;
	public static List<ConstTexture> npcItems = new ArrayList<ConstTexture>();
	public static ConstTexture hand;

	//Items on the map
	public static ArrayList<ConstTexture> items = new ArrayList<ConstTexture>();
	public static ArrayList<ConstTexture> box = new ArrayList<ConstTexture>();
	public static ConstTexture itemReminder;
	public static ConstTexture boxShadow;

	//Hero skills cards
	public static ArrayList<ConstTexture> skillCards = new ArrayList<ConstTexture>();
	public static ConstTexture cd;

	//Shadows of heros
	public static ConstTexture heroShadow;

	//Weapons & Equipments
	public static ArrayList<ConstTexture> shoes = new ArrayList<ConstTexture>();
	public static ArrayList<ConstTexture> suits = new ArrayList<ConstTexture>();
	public static ArrayList<ConstTexture> weapons = new ArrayList<ConstTexture>();
	public static ArrayList<ConstTexture> newBox = new ArrayList<ConstTexture>();
	public static ConstTexture weaponPanel;
	public static ArrayList<ConstTexture> equipButtons = new ArrayList<ConstTexture>();

	//Over Panel and its buttons
	public static ConstTexture overPanel;
	public static ArrayList<ConstTexture> overButtons =  new ArrayList<ConstTexture>();
	public static ConstTexture overText;
	public static ConstTexture loading;

	//EnemyMagic
	public static List<ConstTexture> allSkeletonMagicImage= new ArrayList<ConstTexture>();

	//Final panel
	public static List <ConstTexture>  finalPanel = new ArrayList<ConstTexture>();
	public static Font rankFont = new Font();

	public static ConstTexture heroInfoInBackPack;


	//设置图片
	public static void inti(String heroType,int attackNumber)
	{
		Texture imgs = new Texture();
		try {
			//地图的加载


			for(int i = 1; i <= 3; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get("Images/Info/" + i + ".png"));
				imgs.setSmooth(true);
				heroIcons.add(imgs);
			}

//			imgs = new Texture();
//			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background"+level+".png"));
//			imgs.setSmooth(true);
//			allBackgrounds.add(imgs);
//
//			imgs = new Texture();
//			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/covers1.png"));
//			imgs.setSmooth(true);
//			covers = imgs;

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Shadow/" + heroType + "shadow.png"));
			imgs.setSmooth(true);
			heroShadow = imgs;

			//EnemyMagic
			Texture imgTexture = new Texture();
			imgTexture.loadFromFile(Paths.get(imagePath+"/Images/EnemyMagic/skeletonMagic"+0+".png"));
			imgTexture.setSmooth(true);
			allSkeletonMagicImage.add(imgTexture);


			//角色的加载
			//Run
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Left/" + heroType + "/" + heroType + "Run_1.png"));
			imgs.setSmooth(true);
			HeroRun.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Right/" + heroType + "/" + heroType + "Run_1.png"));
			imgs.setSmooth(true);
			HeroRun.add(imgs);
			//Ready
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Left/" + heroType + "/" + heroType + "Ready_1.png"));
			imgs.setSmooth(true);
			HeroReady.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Right/" + heroType + "/" + heroType + "Ready_1.png"));
			imgs.setSmooth(true);
			HeroReady.add(imgs);
			//Attack
			for(int i = 1; i <= attackNumber; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Left/" + heroType + "/" + heroType + "Attack"+i+"_1.png"));
				imgs.setSmooth(true);
				HeroAttackLeft.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Right/" + heroType + "/" + heroType + "Attack"+i+"_1.png"));
				imgs.setSmooth(true);
				HeroAttackRight.add(imgs);
			}
			//Dead
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Left/" + heroType + "/" + heroType + "Death.png"));
			imgs.setSmooth(true);
			HeroDead.add(imgs);

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Right/" + heroType + "/" + heroType + "Death.png"));
			imgs.setSmooth(true);
			HeroDead.add(imgs);

			for(int i = 1; i <= 4; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/SkillCards/" + heroType + "/" + i + ".png"));
				imgs.setSmooth(true);
				skillCards.add(imgs);
			}

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/SkillCards/CD.png"));
			imgs.setSmooth(true);
			cd = imgs;

			//Skills 1~4
			for(int i = 1; i <= 4; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Left/" + heroType + "/" + heroType + "Skill" +i + "_1.png"));
				imgs.setSmooth(true);
				if(i == 1)
					HeroSkill1.add(imgs);
				else if(i == 2)
					HeroSkill2.add(imgs);
				else if(i == 3)
					HeroSkill3.add(imgs);
				else if(i == 4)
					HeroSkill4.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/ActorsPic/HeroPic/Pic_Right/" + heroType + "/" + heroType + "Skill"+ i + "_1.png"));
				imgs.setSmooth(true);
				if(i == 1)
					HeroSkill1.add(imgs);
				else if(i == 2)
					HeroSkill2.add(imgs);
				else if(i == 3)
					HeroSkill3.add(imgs);
				else if(i == 4)
					HeroSkill4.add(imgs);
			}

			//Skill Effects 1~4
			for(int i = 1; i <= 4; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EffectPic/" + heroType +"_left"+ "_skill" + i + ".png"));
				imgs.setSmooth(true);
				if(i == 1)
					effectSkill1.add(imgs);
				else if(i == 2)
					effectSkill2.add(imgs);
				else if(i == 3)
					effectSkill3.add(imgs);
				else if(i == 4)
					effectSkill4.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EffectPic/" + heroType +"_right"+ "_skill" + i + ".png"));
				imgs.setSmooth(true);
				if(i == 1)
					effectSkill1.add(imgs);
				else if(i == 2)
					effectSkill2.add(imgs);
				else if(i == 3)
					effectSkill3.add(imgs);
				else if(i == 4)
					effectSkill4.add(imgs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	public static void loadBackground() {
		Texture imgs = new Texture();
		try {

			for(int i = 1; i <= 10; i++)
			{
				if(i == 2 || i == 6)
					continue;
				imgs = new Texture();
				imgs.loadFromFile(Paths.get("Images/GuideMaps/" + i +".png"));
				imgs.setSmooth(true);
				guideMaps.add(imgs);
			}

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background1.jpg"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background3.jpg"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background4.jpg"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background5.jpg"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background7.jpg"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background8.png"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background9.jpg"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/background10.png"));
			imgs.setSmooth(true);
			allBackgrounds.add(imgs);

			for(int  i = 1; i <= 10; i++)
			{
				if(i == 2 || i == 6)
					continue;
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BackgroundPic/cover" + i + ".png"));
				imgs.setSmooth(true);
				covers.add(imgs);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Load pictures of enemies.
	public static void loadEnemy()
	{
		Texture imgs = new Texture();
		try {
			//Enemies for 200*200
			for(int i = 1; i <= 10; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Left/pic_left_move" + i + ".png"));
				imgs.setSmooth(true);
				enemyLeftMove.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Right/pic_right_move" + i + ".png"));
				imgs.setSmooth(true);
				enemyRightMove.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Left/pic_left_attack" + i + ".png"));
				imgs.setSmooth(true);
				enemyLeftAttack.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Right/pic_right_attack" + i + ".png"));
				imgs.setSmooth(true);
				enemyRightAttack.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Left/pic_left_hurt" + i + ".png"));
				imgs.setSmooth(true);
				enemyLeftHurt.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Right/pic_right_hurt" + i + ".png"));
				imgs.setSmooth(true);
				enemyRightHurt.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Left/pic_left_die" + i + ".png"));
				imgs.setSmooth(true);
				enemyLeftDie.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/EnemyPic/Right/pic_right_die" + i + ".png"));
				imgs.setSmooth(true);
				enemyRightDie.add(imgs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadBoss()
	{
		Texture imgs = new Texture();
		try {
			for (int i = 1; i <= 8; i++) {
				//Enemies for 200*200
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Left/boss" + i + "_left_move.png"));
				imgs.setSmooth(true);
				BossLeftMove.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Right/boss" + i + "_right_move.png"));
				imgs.setSmooth(true);
				BossRightMove.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Left/boss" + i + "_left_attack1.png"));
				imgs.setSmooth(true);
				BossLeftAttack1.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Right/boss" + i + "_right_attack1.png"));
				imgs.setSmooth(true);
				BossRightAttack1.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Left/boss" + i + "_left_attack2.png"));
				imgs.setSmooth(true);
				BossLeftAttack2.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Right/boss" + i + "_right_attack2.png"));
				imgs.setSmooth(true);
				BossRightAttack2.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Left/boss" + i + "_left_attack3.png"));
				imgs.setSmooth(true);
				BossLeftAttack3.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Right/boss" + i + "_right_attack3.png"));
				imgs.setSmooth(true);
				BossRightAttack3.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Left/boss" + i + "_left_attack4.png"));
				imgs.setSmooth(true);
				BossLeftAttack4.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Right/boss" + i + "_right_attack4.png"));
				imgs.setSmooth(true);
				BossRightAttack4.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Left/boss" + i + "_left_hurt.png"));
				imgs.setSmooth(true);
				BossLeftHurt.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Right/boss" + i + "_right_hurt.png"));
				imgs.setSmooth(true);
				BossRightHurt.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Left/boss" + i + "_left_die.png"));
				imgs.setSmooth(true);
				BossLeftDie.add(imgs);
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/BossPic/Right/boss" + i + "_right_die.png"));
				imgs.setSmooth(true);
				BossRightDie.add(imgs);

			}
			} catch(IOException e){
				e.printStackTrace();
			}
	}

	//Load all pictures of NPCs and load Dialog pictures of the game.
	public static void loadNPCs()
	{
		Texture imgs = new Texture();
		try {

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/MousePoint.png"));
			imgs.setSmooth(true);
			mousePic = imgs;

			for(int i = 1; i <= 10; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/NPCpic/" + i + ".png"));
				imgs.setSmooth(true);
				NPCs.add(imgs);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadDialog();
	}

	public static void loadDialog()
	{
		Texture imgs = new Texture();
		try {

			//Dialogs
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/Dialogs.png"));
			imgs.setSmooth(true);
			dialogs.add(imgs);

			dialogFont.loadFromFile(Paths.get(imagePath + "/Images/Dialog/Arial.ttf"));
			rankFont.loadFromFile(Paths.get(imagePath + "/Images/FinalPanel/LCALLIG.TTF"));

			for(int i = 0; i < 2; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/button" + (i + 1) + ".png"));
				imgs.setSmooth(true);
				buttons.add(imgs);
			}
			for(int i = 1; i <= 4; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/select" + i + ".png"));
				imgs.setSmooth(true);
				buttons.add(imgs);
			}
			for(int i = 0; i < 2; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/npcItem" + (i + 1) + ".png"));
				imgs.setSmooth(true);
				npcItems.add(imgs);
			}

			for(int i = 1; i <= 11; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Items/" + i + ".png"));
				imgs.setSmooth(true);
				items.add(imgs);
			}

			for(int i = 1; i <= 2; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/box" + i + ".png"));
				imgs.setSmooth(true);
				box.add(imgs);
			}

			for(int i = 1; i <= 2; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/newBox" + i + ".png"));
				imgs.setSmooth(true);
				newBox.add(imgs);
			}

			//The final endings panel
			for(int i = 1; i <= 6; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get("Images/FinalPanel/end" + i + ".png"));
				imgs.setSmooth(true);
				finalPanel.add(imgs);
			}


			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/dialogIcon" + ".png"));
			imgs.setSmooth(true);
			dialogIcon = imgs;

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/mission" + ".png"));
			imgs.setSmooth(true);
			missionList = imgs;

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Dialog/hand.png"));
			imgs.setSmooth(true);
			hand = imgs;

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Items/ItemReminder.png"));
			imgs.setSmooth(true);
			itemReminder = imgs;

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Items/boxShadow.png"));
			imgs.setSmooth(true);
			boxShadow = imgs;

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Items/HeroInfo.png"));
			imgs.setSmooth(true);
			heroInfoInBackPack = imgs;

			loadEquipments();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadEquipments()
	{
		Texture imgs = new Texture();
		try {
			//Weapon Panel
			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/Weapons/weaponPanel.png"));
			imgs.setSmooth(true);
			weaponPanel = imgs;

			//Four buttons of weaponPanel
			for(int i = 1; i <= 4; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Weapons/button" + i + ".png"));
				imgs.setSmooth(true);
				equipButtons.add(imgs);
			}

			//Shoes
			for(int i = 1; i <= 6; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Weapons/shoes/shoes" + i + ".png"));
				imgs.setSmooth(true);
				shoes.add(imgs);
			}

			//Suits
			for(int i = 1; i <= 6; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Weapons/suit/suit" + i + ".png"));
				imgs.setSmooth(true);
				suits.add(imgs);
			}

			//Weapons
			for(int i = 1; i <= 10; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/Weapons/weapon/weapon" + i + ".png"));
				imgs.setSmooth(true);
				weapons.add(imgs);
			}

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/OverPanel/OverAnimation.png"));
			imgs.setSmooth(true);
			overPanel = imgs;

			for(int i = 1; i <= 4; i++)
			{
				imgs = new Texture();
				imgs.loadFromFile(Paths.get(imagePath + "/Images/OverPanel/" + i + ".png"));
				imgs.setSmooth(true);
				overButtons.add(imgs);
			}


			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/OverPanel/Loading.png"));
			imgs.setSmooth(true);
			loading = imgs;

			imgs = new Texture();
			imgs.loadFromFile(Paths.get(imagePath + "/Images/OverPanel/Text.png"));
			imgs.setSmooth(true);
			overText = imgs;

		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
