package GameStarter;

import Actors.Actors;
import Actors.Blood;
import Actors.Music;
import DatabaseManager.OperateDatabase;
import GameDriver.LevelDetermination;
import GameDriver.Musiccall;
import GameDriver.staticVariable;
import GameStartPanel.A_Start;
import GameStartPanel.IntroLevel;
import Shop.LoadPics;
import TowerDenfense.TDLoadMusic;
import TowerDenfense.TDMusicPlay;
import TowerDenfense.TDPictures;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;

import java.io.IOException;


public class mainFrame
{
	//Date
	private RenderWindow window;
	private LevelDetermination levelDetermination;
	private A_Start startPanel = new A_Start();

	public static IntroLevel il =new IntroLevel();

	public static boolean isStartFinish = false;

	private boolean isSet = false;

	public Sprite loading = new Sprite();

	//Constructor
	public mainFrame()
	{
		staticVariable.loadBackground();
		staticVariable.loadEnemy();
		staticVariable.loadNPCs();
		staticVariable.loadBoss();
		TDPictures.loadImgs();
		TDMusicPlay.loading();
		LoadPics.loadShop();


		window = new RenderWindow(new VideoMode(1500, 800), "Warriors Expedition", Window.DEFAULT);
		window.setPosition(new Vector2i(0,0));
		window.setFramerateLimit(50);

		loading.setTexture(staticVariable.loading);
		loading.setPosition(0, 0);
		mainLoop();
	}

	//Main loop of the game.
	public void mainLoop()
	{
		while(window.isOpen())
		{

			if(Keyboard.isKeyPressed(Keyboard.Key.NUM1) && isStartFinish)
			{
				//πÿ±’…˘“Ù
				if(levelDetermination.level == 1)
					Musiccall.backMusic1.setVolume(0);
				else if(levelDetermination.level == 3)
					Musiccall.backMusic3.setVolume(0);
				else if(levelDetermination.level == 4)
					Musiccall.backMusic4.setVolume(0);
				else if(levelDetermination.level == 5)
					Musiccall.backMusic5.setVolume(0);
				else if(levelDetermination.level == 7)
					Musiccall.backMusic7.setVolume(0);
				else if(levelDetermination.level == 8)
					Musiccall.backMusic8.setVolume(0);
				else if(levelDetermination.level == 9)
					Musiccall.backMusic9.setVolume(0);
				else if(levelDetermination.level == 10)
					Musiccall.backMusic10.setVolume(0);
				else if(levelDetermination.level == 2 || levelDetermination.level == 6)
					TDMusicPlay.backgroundMusic.setVolume(0);
			}
			else if(Keyboard.isKeyPressed(Keyboard.Key.NUM2) && isStartFinish)
			{
				//“Ù¡øΩµµÕ
				if(levelDetermination.level != 2 && levelDetermination.level != 6)
				{
					Music.volume -= 3;
					if(Music.volume < 0)
						Music.volume = 0;
					if(levelDetermination.level == 1)
						Musiccall.backMusic1.setVolume(Music.volume);
					else if(levelDetermination.level == 3)
						Musiccall.backMusic3.setVolume(Music.volume);
					else if(levelDetermination.level == 4)
						Musiccall.backMusic4.setVolume(Music.volume);
					else if(levelDetermination.level == 5)
						Musiccall.backMusic5.setVolume(Music.volume);
					else if(levelDetermination.level == 7)
						Musiccall.backMusic7.setVolume(Music.volume);
					else if(levelDetermination.level == 8)
						Musiccall.backMusic8.setVolume(Music.volume);
					else if(levelDetermination.level == 9)
						Musiccall.backMusic9.setVolume(Music.volume);
					else if(levelDetermination.level == 10)
						Musiccall.backMusic10.setVolume(Music.volume);
				}
				else if(levelDetermination.level == 2 || levelDetermination.level == 6)
				{
					TDLoadMusic.volume -= 3;
					if(TDLoadMusic.volume < 0)
						TDLoadMusic.volume = 0;
					TDMusicPlay.backgroundMusic.setVolume(TDLoadMusic.volume);
				}
			}
			else if(Keyboard.isKeyPressed(Keyboard.Key.NUM3) && isStartFinish)
			{

				if(levelDetermination.level != 2 && levelDetermination.level != 6)
				{
					Music.volume += 3;
					if(levelDetermination.level == 1)
						Musiccall.backMusic1.setVolume(Music.volume);
					else if(levelDetermination.level == 3)
						Musiccall.backMusic3.setVolume(Music.volume);
					else if(levelDetermination.level == 4)
						Musiccall.backMusic4.setVolume(Music.volume);
					else if(levelDetermination.level == 5)
						Musiccall.backMusic5.setVolume(Music.volume);
					else if(levelDetermination.level == 7)
						Musiccall.backMusic7.setVolume(Music.volume);
					else if(levelDetermination.level == 8)
						Musiccall.backMusic8.setVolume(Music.volume);
					else if(levelDetermination.level == 9)
						Musiccall.backMusic9.setVolume(Music.volume);
					else if(levelDetermination.level == 10)
						Musiccall.backMusic10.setVolume(Music.volume);
				}
				else if(levelDetermination.level == 2 || levelDetermination.level == 6)
				{
					TDLoadMusic.volume += 3;
					TDMusicPlay.backgroundMusic.setVolume(TDLoadMusic.volume);
				}
			}

			for(Event event : window.pollEvents())
			{
				if(event.type == Event.Type.CLOSED)
				{
					Blood.update();
					window.close();
					System.exit(0);
					break;
				}
				if(event.type == Event.Type.KEY_PRESSED && isStartFinish)
				{
					if(levelDetermination.level != 2  && levelDetermination.level != 6)
						keyPress();
				}

				if(event.type == Event.Type.KEY_RELEASED && isStartFinish)
				{
					if(levelDetermination.level != 2  && levelDetermination.level != 6)
						keyRelease(event);
				}

				if(event.type == Event.Type.MOUSE_BUTTON_RELEASED && isStartFinish)
				{
					if(levelDetermination.level != 2 && levelDetermination.level != 6)
					{
						for(int i = 0; i < levelDetermination.driver.ones.getAllNPCs().size(); i++)
						{
							if(levelDetermination.driver.ones.getAllNPCs().get(i).curDia.getIsClick() != 0
									&& levelDetermination.driver.ones.getAllNPCs().get(i).curDia.getStart())
								levelDetermination.driver.ones.getAllNPCs().get(i).curDia.setIsClick(0);
						}
					}
					if(levelDetermination.shopOpen)
					{
						levelDetermination.shop.isClick = false;
						levelDetermination.shop.clickSound = false;
					}
				}

			}

			window.clear(Color.WHITE);

			if(!isStartFinish)
			{
				try {
					startPanel.run(window);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			//System.out.println(isStartFinish);

			if(isStartFinish)
			{
				//System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
				if(!isSet)
				{
					isSet = true;
					this.startPanel = null;
					window.clear();
					window.draw(loading);
					window.display();
					String type = OperateDatabase.getHeroType();
					//System.out.println(type);
					int attack = 0;
					if(type.equals("Lancer") || type.equals("Knight"))
						attack = 3;
					else if(type.equals("Saber"))
						attack = 6;
					//System.out.println(type + " " + attack);
					staticVariable.inti(type, attack);
					LevelDetermination.level = OperateDatabase.getLevelRecord();
					levelDetermination = new LevelDetermination(type, attack, this.window);
				}
				levelDetermination.onDraw();
			}
			//≤‚ ‘
			/*RectangleShape rectangular = new RectangleShape();
			rectangular.setSize(new Vector2f(7,7));
			rectangular.setFillColor(new Color(220,20,60));
			rectangular.setPosition(levelDetermination.driver.player.x, levelDetermination.driver.player.y);*/
			//window.draw(rectangular);

			window.display();
		}
	}

	//KeyEvent Listener
	public void keyPress()
	{
		if(Keyboard.isKeyPressed(Keyboard.Key.ESCAPE))
		{
			window.close();
			System.exit(0);
		}
		if(Keyboard.isKeyPressed(Keyboard.Key.Z) == false && !levelDetermination.driver.player.isAttack &&
				!levelDetermination.driver.player.isSkill && Keyboard.isKeyPressed(Keyboard.Key.X) == false &&
				Keyboard.isKeyPressed(Keyboard.Key.C) == false &&
				Keyboard.isKeyPressed(Keyboard.Key.V) == false &&
				Keyboard.isKeyPressed(Keyboard.Key.B) == false && !levelDetermination.driver.player.isDead&&
				levelDetermination.canOper)
		{
			if(Keyboard.isKeyPressed(Keyboard.Key.UP) && !levelDetermination.driver.player.down)
			{
				levelDetermination.driver.playerController("up_moving");
				levelDetermination.driver.player.up = true;
				levelDetermination.driver.upMove();
				levelDetermination.driver.playerDownMove();
			}
			else if(Keyboard.isKeyPressed(Keyboard.Key.DOWN) && !levelDetermination.driver.player.up)
			{
				levelDetermination.driver.playerController("down_moving");
				levelDetermination.driver.player.down = true;
				levelDetermination.driver.downMove();
				levelDetermination.driver.playerUpMove();
			}

			if(Keyboard.isKeyPressed(Keyboard.Key.LEFT) && !levelDetermination.driver.player.right)
			{
				levelDetermination.driver.playerController("left_moving");
				levelDetermination.driver.player.left = true;
				levelDetermination.driver.rightMove();
				levelDetermination.driver.playerLeftMove();
			}
			else if(Keyboard.isKeyPressed(Keyboard.Key.RIGHT) && !levelDetermination.driver.player.left)
			{
				levelDetermination.driver.playerController("right_moving");
				levelDetermination.driver.player.right = true;
				levelDetermination.driver.leftMove();
				levelDetermination.driver.playerRightMove();
			}
		}
		//z is the normal attack
		else if(Keyboard.isKeyPressed(Keyboard.Key.Z))
		{
			if(!levelDetermination.driver.player.isAttack && !levelDetermination.driver.player.up
					&& !levelDetermination.driver.player.down && !levelDetermination.driver.player.left && !levelDetermination.driver.player.right
					&& !levelDetermination.driver.player.isSkill && levelDetermination.driver.player.state == "standing" && !levelDetermination.driver.player.isDead
					&& levelDetermination.canOper)
			{
				levelDetermination.driver.playerController("attack_normal");
				levelDetermination.driver.player.isAttack = true;
				levelDetermination.driver.player.isAttackFinish = false;
			}
		}
		//x is skill 1
		else if(Keyboard.isKeyPressed(Keyboard.Key.X) && !levelDetermination.driver.cards.isUsed[0]&&Actors.manaHero>=Actors.damageHeroSkill1/2)
		{
			if(!levelDetermination.driver.player.isSkill && !levelDetermination.driver.player.up
					&& !levelDetermination.driver.player.down && !levelDetermination.driver.player.left && !levelDetermination.driver.player.right
					&& !levelDetermination.driver.player.isAttack && levelDetermination.driver.player.state == "standing" && !levelDetermination.driver.player.isDead)
			{
				levelDetermination.driver.cards.startTimer(1);
				levelDetermination.driver.playerController("skill");
				levelDetermination.driver.player.isSkill = true;
				levelDetermination.driver.player.isSkillFinish = false;
				levelDetermination.driver.player.skillNumber = 1;

			}
		}
		//C  is the skill 2
		else if(Keyboard.isKeyPressed(Keyboard.Key.C) && !levelDetermination.driver.cards.isUsed[1]
				&& Actors.manaHero >= Actors.damageHeroSkill1/2)
		{

			if(!levelDetermination.driver.player.isSkill && !levelDetermination.driver.player.up
					&& !levelDetermination.driver.player.down && !levelDetermination.driver.player.left && !levelDetermination.driver.player.right
					&& !levelDetermination.driver.player.isAttack && !levelDetermination.driver.player.isDead)
			{
				levelDetermination.driver.cards.startTimer(2);
				levelDetermination.driver.playerController("skill");
				levelDetermination.driver.player.isSkill = true;
				levelDetermination.driver.player.isSkillFinish = false;
				levelDetermination.driver.player.skillNumber = 2;

			}
		}
		// V is skill 3
		else if(Keyboard.isKeyPressed(Keyboard.Key.V) && !levelDetermination.driver.cards.isUsed[2]
				&& Actors.manaHero >= Actors.damageHeroSkill1/3)
		{

			if(!levelDetermination.driver.player.isSkill && !levelDetermination.driver.player.up
					&& !levelDetermination.driver.player.down && !levelDetermination.driver.player.left && !levelDetermination.driver.player.right
					&& !levelDetermination.driver.player.isAttack && !levelDetermination.driver.player.isDead)
			{
				levelDetermination.driver.cards.startTimer(3);
				levelDetermination.driver.playerController("skill");
				levelDetermination.driver.player.isSkill = true;
				levelDetermination.driver.player.isSkillFinish = false;
				levelDetermination.driver.player.skillNumber = 3;

			}
		}
		//B is skill 4
		else if(Keyboard.isKeyPressed(Keyboard.Key.B) && !levelDetermination.driver.cards.isUsed[3]
				&& Actors.manaHero >= Actors.damageHeroSkill1/3)
		{

			if(!levelDetermination.driver.player.isSkill && !levelDetermination.driver.player.up
					&& !levelDetermination.driver.player.down && !levelDetermination.driver.player.left && !levelDetermination.driver.player.right
					&& !levelDetermination.driver.player.isAttack && levelDetermination.driver.player.state == "standing" && !levelDetermination.driver.player.isDead)
			{
				levelDetermination.driver.cards.startTimer(4);
				levelDetermination.driver.playerController("skill");
				levelDetermination.driver.player.isSkill = true;
				levelDetermination.driver.player.isSkillFinish = false;
				levelDetermination.driver.player.skillNumber = 4;
			}
		}
	}

	public void keyRelease(Event event)
	{
		switch(event.asKeyEvent().key)
		{
			case UP:
				levelDetermination.driver.player.up = false;
				levelDetermination.driver.playerController("standing");
				levelDetermination.driver.upStop();
				levelDetermination.driver.playerUpStop();
				break;
			case DOWN:
				levelDetermination.driver.player.down = false;
				levelDetermination.driver.playerController("standing");
				levelDetermination.driver.downStop();
				levelDetermination.driver.playerDownStop();
				break;
			case LEFT:
				//System.out.println(this.driver.player.isDead);
				levelDetermination.driver.player.left = false;
				levelDetermination.driver.playerController("standing");
				levelDetermination.driver.rightStop();
				levelDetermination.driver.playerRightStop();
				break;
			case RIGHT:
				//System.out.println(this.driver.player.isDead);
				levelDetermination.driver.player.right = false;
				levelDetermination.driver.playerController("standing");
				levelDetermination.driver.leftStop();
				levelDetermination.driver.playerLeftStop();
				break;
			default:
				break;
		}
	}

}
