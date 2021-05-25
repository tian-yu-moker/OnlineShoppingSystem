package GUI;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;

import Actors.Actors;
import Actors.Blood;
import DatabaseManager.OperateDatabase;
import GameDriver.staticVariable;
import TowerDenfense.TDMusicPlay;
import TowerDenfense.TDPictures;
import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Mouse;

import static GameDriver.staticVariable.HeroReady;

public class Backpack
//main函数中有且仅有一个实例
//每次需要弹出一个弹窗时，调用一次drawSmallWindow函数
{
	/*弹窗弹出与关闭算法
	 * 刘嘉奕原创
	 * 2020.4.4 */
	int backpack_width=1000;//弹窗的宽度
	int backpack_height=700;//弹窗的高度
	int backpack_x=0;//弹窗左上角横坐标（相对于jsfml窗口）
	int backpack_y=0;
	int exit_x=backpack_x+backpack_width-200;//叉号左上角横坐标（相对于jsfml窗口）
	int exit_y=backpack_y;



	RectangleShape picture_backpack=createImage("myImage/backpack.png", backpack_width, backpack_height, backpack_x, backpack_y);
	RectangleShape picture_exit=createImage("myImage/exit.png", 200, 200, exit_x, exit_y);
	RectangleShape picture_exitLight=createImage("myImage/exitLight.png", 200, 200, exit_x, exit_y);

	//光标移动至小背包按钮处时，按钮尺寸变大
	//RectangleShape picture_packet_icon_Bigger=createImage("myImage/BattleUI/packet_icon.png", 150*2, 200*2, 1100-150, 600-200);//右下角和原图片的右下角重合
	//光标移动至小背包按钮处时，按钮变亮
	RectangleShape picture_packet_icon_Light=createImage("myImage/BattleUI/backPackLight.png", 150, 200, 1100, 600);//右下角和原图片的右下角重合

	boolean backpackOpen=false;//背包是否打开
	boolean backpackClosed=false;//背包已经被关闭（由打开状态被关闭。和未被打开不一样）

	//GridPosition[] gridPositions=new GridPosition[5*4];//存储背包内装备位置(5行4列）
	int gridD=90;//每个方格的边长
	int gridX0=504;//第一个装备图片左上角的坐标
	int gridY0=147;
	int weaponInGrid_D=70;//包中装备（正方形）的边长

	//用于在背包中显示数字（装备数量）
	Text text1=new Text("0", TDPictures.font, 40);
	//用于在背包中显示英雄名字
	Text text2=new Text("0", staticVariable.dialogFont, 35);
	Sprite heroInfo = new Sprite();
	//用于在背包中显示速度、攻击力
	Text text_speed=new Text("0", staticVariable.dialogFont, 30);
	Text text_attack=new Text("0", staticVariable.dialogFont, 30);


	//用于在背包中绘制英雄动图
	Sprite Sprite_heroInBackpack = new Sprite();
	private int indexX = 0;
	private int indexY = 0;
	private int width = 0;
	private int height = 0;
	private int maxX = 0;
	private int maxY = 0;
	private String nameHero_="";
	private boolean enterClose = false;
	private boolean clickSound = false;

	int heroInBackpack_col=8;//列数
	int heroInBackpack_row=3;//行数

	//构造函数
	public Backpack()
	{
		/*for(int i=0;i<5*4;i++)
		{
			gridPositions[i].x=gridX0+(i%4)*gridD;
			gridPositions[i].y=gridY0+(i/4)*gridD;
		}*/
		/*for(int i=1;i<=5;i++)
		{
			picture_numbers[i]=createImage("myImage/Number/Num_"+String.valueOf(i)+".png", 40, 40, 0,0);
		}*/

		text1.setColor(Color.RED);//设置装备右下角数字的颜色
		text2.setColor(Color.WHITE);//英雄名字
		heroInfo.setTexture(staticVariable.heroInfoInBackPack);
		heroInfo.setOrigin(staticVariable.heroInfoInBackPack.getSize().x / 2, staticVariable.heroInfoInBackPack.getSize().y / 2);
		heroInfo.setScale((float) 0.9, (float) 0.9);
		text_speed.setColor(Color.RED);
		text_attack.setColor(Color.RED);
		text2.setStyle(TextStyle.BOLD);

		//加载需要填入格子中的各装备的图片
		for(int i=1;i<=11;i++)
		{
			picture_weapons[i]=createImage("myImage/Items/"+String.valueOf(i)+".png",100,100,0,0);

		}

		//确定背包中血条的位置和颜色470  458   506
		Rectangle_HP.setPosition(190,461);
		Rectangle_HP.setFillColor(new Color(220,20,60,252));
		Rectangle_MP.setPosition(190,511);
		Rectangle_MP.setFillColor(new Color(220,20,60,250));
		Rectangle_EXP.setPosition(190,565);
		Rectangle_EXP.setFillColor(Color.YELLOW);

		//英雄动图
		Sprite_heroInBackpack.setTexture(HeroReady.get(0));//图片加载
		nameHero_ = OperateDatabase.getHeroType();
		maxX = HeroReady.get(0).getSize().x;
		maxY = HeroReady.get(0).getSize().y;
		if(nameHero_.equals("Lancer") || nameHero_.equals("Knight"))
		{
			width = 500;
			height = 500;
		}
		else if(nameHero_.equals("Saber"))
		{
			width = 400;
			height = 400;
		}
		Sprite_heroInBackpack.setOrigin(width / 2, height / 2);
		if(nameHero_.equals("Lancer"))
		{
			Sprite_heroInBackpack.setScale((float) 1.5, (float) 1.5);
			Sprite_heroInBackpack.setPosition(280,190);
		}
		else if(nameHero_.equals("Knight"))
		{
			Sprite_heroInBackpack.setPosition(275,250);
		}
		else if(nameHero_.equals("Saber"))
		{
			Sprite_heroInBackpack.setScale((float) 1.5, (float) 1.5);
			Sprite_heroInBackpack.setPosition(280,190);
		}
	}

	/*绘制血条第一步*/
	RectangleShape Rectangle_HP = new RectangleShape();
	RectangleShape Rectangle_MP = new RectangleShape();
	RectangleShape Rectangle_EXP = new RectangleShape();



	public void drawSmallWindow(RenderWindow window,Mouse mouse, int openBackpack,int jsfmlWindow_x,int jsfmlWindow_y)//绘制弹窗
	{
		/*弹窗（背包）弹出与关闭算法*/

		//屏幕下方小背包按钮左上角的坐标
		int backpackIcon_x=window.getSize().x-400;
		int backpackIcon_y= window.getSize().y-200;

		//得到鼠标相对于jsfml主窗口左上角的横纵坐标
		int mousePositionInJsfmlWindow_x=mouse.getPosition(window).x;//-jsfmlWindow_x;
		int mousePositionInJsfmlWindow_y=mouse.getPosition(window).y;//-jsfmlWindow_y;

		//判断光标是否移动到了屏幕下方小背包按钮（点击按钮，背包弹窗弹出）
		boolean curcorMoveToPacket_icon=mousePositionInJsfmlWindow_x>backpackIcon_x&&mousePositionInJsfmlWindow_x<backpackIcon_x+150&&mousePositionInJsfmlWindow_y>backpackIcon_y&&mousePositionInJsfmlWindow_y<backpackIcon_y+200;

		//光标移动至小背包按钮处时，按钮变亮（原为尺寸变大）
		if(curcorMoveToPacket_icon)
		{
			//picture_packet_icon_Bigger.setPosition(backpackIcon_x-150, backpackIcon_y-200);//右下角和原图片的右下角重合
			//window.draw(picture_packet_icon_Bigger);//尺寸变大

			window.draw(picture_packet_icon_Light);//变亮（覆盖不亮的图标）

		}

		//若光标是否移动到了屏幕下方小背包按钮且左键被按下，则弹出背包弹窗（2020.4.15 添加此功能）
		if(mouse.isButtonPressed(Mouse.Button.LEFT)&&curcorMoveToPacket_icon)
		{
			openBackpack=0;
		}



		//判断光标是否移动至叉号位置 （true:移动到了）
		boolean curcorMoveToExitIcon=mousePositionInJsfmlWindow_x>exit_x&&mousePositionInJsfmlWindow_x<exit_x+200&&mousePositionInJsfmlWindow_y>exit_y&&mousePositionInJsfmlWindow_y<exit_y+200;

		if(openBackpack==0)//打开背包的条件(打开背包的瞬间满足此条件）
		{
			backpackOpen=true;
			backpackClosed=false;//使之前的关闭无效
		}
		if(backpackClosed||(curcorMoveToExitIcon&&mouse.isButtonPressed(Mouse.Button.LEFT)))//关闭背包的条件
		{//一旦背包被点击关闭，则一直保持关闭状态
			backpackOpen=false;
			backpackClosed=true;
		}
		if(backpackOpen)
		//显示背包窗口
		{
			window.draw(picture_backpack);

			text_speed.setPosition(225,390);
			text_speed.setString(String.valueOf((int)(70 - Actors.movespeed + 70)));

			window.draw(text_speed);
			text_attack.setPosition(360, 390);
			text_attack.setString(String.valueOf((int)Actors.damageHero));
			window.draw(text_attack);


			/*绘制英雄动画*/
			if(nameHero_.equals("Lancer"))
			{
				indexX++;
				if(indexX == this.maxX / this.width)
				{
					indexX = 0;
					indexY++;
				}

				if(indexY ==  this.maxY / this.height - 1)
				{
					if(indexX == 1)
						indexY++;
				}
				if(indexY == this.maxY / this.height)
				{
					indexY = 0;
					indexX = 0;
				}
				Sprite_heroInBackpack.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
				window.draw(Sprite_heroInBackpack);
			}
			else if(nameHero_.equals("Knight"))
			{
				indexX++;

				if(indexX == this.maxX / this.width)
				{
					indexX = 0;
					indexY++;
				}

				if(indexY == this.maxY / this.height-1)
				{
					if( indexX == 2)
						indexY++;
				}
				//End of the frame, then begin from start again.
				if(indexY == this.maxY / this.height)
				{
					indexY = 0;
					indexX = 0;
				}
				Sprite_heroInBackpack.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
				window.draw(Sprite_heroInBackpack);
			}
			else if(nameHero_.equals("Saber"))
			{

				indexX++;
				if(indexX == this.maxX / this.width)
				{
					indexX = 0;
					indexY++;
				}
				if(indexY ==  this.maxY / this.height - 1)
				{
					if(indexX == 8)
						indexY++;
				}
				//End of the frame, then begin from start again.
				if(indexY == this.maxY / this.height)
				{
					indexY = 0;
					indexX = 0;
				}

				Sprite_heroInBackpack.setTextureRect(new IntRect(width*indexX, height*indexY, width, height));
				window.draw(Sprite_heroInBackpack);
			}



			/*显示英雄名字*/
			heroInfo.setPosition(290, 145);
			window.draw(heroInfo);
			/*显示。。*/


			/*绘制血条第二步*/
			int redRectangleLength_Max=240;//血条长度最大值
			Rectangle_HP.setSize(new Vector2f(redRectangleLength_Max* Actors.hpHero/Actors.hp,18));
			Rectangle_MP.setSize(new Vector2f(redRectangleLength_Max* Actors.manaHero/Actors.mana,18));
			if(Actors.level <= 10)
				Rectangle_EXP.setSize(new Vector2f(redRectangleLength_Max * (Actors.exp - (Actors.level - 1) * 100) / 100,18));
			else if(Actors.level > 10 && Actors.level <= 15)
				Rectangle_EXP.setSize(new Vector2f(redRectangleLength_Max * (Actors.exp - 1000 - (Actors.level - 11) * 200) / 200,18));
			else if(Actors.level == 16 || Actors.level == 19)
			{
				if(Actors.level == 16)
					Rectangle_EXP.setSize(new Vector2f(redRectangleLength_Max * (Actors.exp - 2000) / 300,18));
				else if(Actors.level == 19)
					Rectangle_EXP.setSize(new Vector2f(redRectangleLength_Max * (Actors.exp - 2700) / 300,18));
			}
			else if(Actors.level == 17 || Actors.level == 18)
			{
				if(Actors.level == 17)
					Rectangle_EXP.setSize(new Vector2f(redRectangleLength_Max * (Actors.exp - 2300) / 200,18));
				else if(Actors.level == 18)
					Rectangle_EXP.setSize(new Vector2f(redRectangleLength_Max * (Actors.exp - 2500) / 200,18));
			}
			window.draw(Rectangle_HP);
			window.draw(Rectangle_MP);
			window.draw(Rectangle_EXP);

			//System.out.println("x="+mouse.getPosition(window).x+" y="+mouse.getPosition(window).y);


			/************************嵌入装备捡拾算法1/2********************
			 * 用于在背包中显示捡到的装备*/

			/****装备捡拾算法3
			 * 执行本行代码后，装备捡拾算法1失效,包中显示的装备完全由文件决定
			 * （注释掉本行代码后，算法1/2生效）
			 * 2020.5.7
			 * 与田宇合作**/
			addWeaponFromFileToLinkedList_WeaponInBackpack();

			/*********装备捡拾算法3结束************/

			//使用背包中的装备（点击）
			useWeaponInPacket(window,mouse);


			for(int i=0;i<LinkedList_WeaponInBackpack.size();i++)//遍历背包中各装备的属性并依次绘制
			{
				LinkedList_WeaponInBackpack.get(i).rectangleShape.setPosition(gridX0+(i%4)*gridD, gridY0+(i/4)*gridD);
				//LinkedList_WeaponInBackpack.get(i).rectangleShape.setPosition(gridPositions[i].x,gridPositions[i].y);

				//调整装备图片尺寸并在背包中绘制
				Vector2f Vector2f1=new Vector2f(weaponInGrid_D, weaponInGrid_D);
				LinkedList_WeaponInBackpack.get(i).rectangleShape.setSize(Vector2f1);
				window.draw(LinkedList_WeaponInBackpack.get(i).rectangleShape);


				//在装备右下角显示装备数量 方法一，通过显示图片
				/*for(int k=1;k<=5;k++)
				{
					//若数量为k
					if(LinkedList_WeaponInBackpack.get(i).amount==k)
					{
						picture_numbers[k].setPosition(gridX0+(i%4)*gridD+40, gridY0+(i/4)*gridD+40);
						window.draw(picture_numbers[k]);

					}
				}*/
				//在装备右下角显示装备数量 方法二，通过文本显示 2020.5.18
				text1.setPosition(gridX0+(i%4)*gridD+30, gridY0+(i/4)*gridD+35);
				text1.setString(String.valueOf(LinkedList_WeaponInBackpack.get(i).amount));
				window.draw(text1);


			}
			/***************嵌入装备捡拾算法结束**************************/

			if(curcorMoveToExitIcon)
			{//If the mouse move to the exit icon, lighten the icon
				if(!enterClose)
				{
					enterClose = true;
					TDMusicPlay.buttonMusic.play();
				}
				window.draw(picture_exitLight);
			}
			else
			{
				enterClose = false;
				window.draw(picture_exit);
			}
		}
	}
	/*************************弹窗（背包）弹出与关闭算法结束*******************/





	/*装备捡拾算法
	 * 刘嘉奕原创
	 * 2020年4月23日
	 * 功能：将地面上的装备（武器等）捡入背包
	 * 触发此功能的用户行为：鼠标单击地面上的装备*/

	//地面装备属性链表，即可以被捡起的装备的属性（坐标、尺寸、编号）链表（相对于jsfml窗口）（实时更新）
	LinkedList <WeaponOnGround> LinkedList_WeaponOnGround=new LinkedList();
	//包中装备属性链表(依次显示在格子中）
	LinkedList <WeaponOnGround> LinkedList_WeaponInBackpack=new LinkedList();

	public void addWeaponOnGround(int weapon_width,int weapon_height,int weapon_x,int weapon_y,int weapon_num,RectangleShape rectangleShape)
		/*将目前地面上编号为weapon_num的装备的图片尺寸、初始坐标（左上角）和编号（在整个游戏进行过程中，每个装备有一个独立的编号）、图片对应的RectangleShape对象输入地面装备属性链表
		 * 注：每件装备有一个唯一的编号（两件同种装备分别有两个编号）
		 * 每个装备的属性占二维数组的一行
		 * 每个装备只在刚刚出现时执行一次本函数*/
	{
		WeaponOnGround newWeaponOnGround=new WeaponOnGround(weapon_x,weapon_y,weapon_width,weapon_height,weapon_num,rectangleShape);
		LinkedList_WeaponOnGround.add(newWeaponOnGround);
	}

	public boolean  drawAWeaponOnGround(RenderWindow window,Mouse mouse,RectangleShape rectangleShape,int weaponNum,int weapon_x,int weapon_y)
		/* 可能需要在地面上某位置显示编号为weaponNum的装备时，执行此函数，是否真正显示此装备由本函数负责（处理捡装备问题）
		 * 若此装备被点击，则将此装备从地面装备链表中删除、将其加入包中装备链表、不在地面显示此装备并返回false
		 * 2020年4月23日*/
	{
		//System.out.println("WeaponInBackpack size:"+LinkedList_WeaponInBackpack.size());
		//System.out.println("WeaponOnGround size:"+LinkedList_WeaponOnGround.size());

		int j=0;
		for(int i=0;i<LinkedList_WeaponOnGround.size();i++)//遍历地面上各装备的属性
		{
			j++;
			if(LinkedList_WeaponOnGround.get(i).Number==weaponNum)
			{
				//System.out.println("No");
				break;
			}
			//若遍历至最后一个节点依然没有发现编号为weaponNum的装备（此装备已经从地面装备属性链表删除）
			if((i==LinkedList_WeaponOnGround.size()-1)&&LinkedList_WeaponOnGround.get(i).Number!=weaponNum)
			{
				return false;//不在地面显示此装备
			}
		}
		if(j==0)//若地面装备属性链表长度为0
		{
			return false;//不在地面显示此装备
		}


		//得到鼠标相对于jsfml主窗口左上角的横纵坐标
		int mousePositionInJsfmlWindow_x=mouse.getPosition(window).x;//-window.getPosition().x;
		int mousePositionInJsfmlWindow_y=mouse.getPosition(window).y;//-window.getPosition().y;

		//若鼠标左键被按下（有可能捡起装备），开始遍历地面上各装备的属性
		if(mouse.isButtonPressed(Mouse.Button.LEFT))
		{
			for(int i=0;i<LinkedList_WeaponOnGround.size();i++)//遍历地面上各装备的属性
			{
				//若遍历至编号为weaponNum的一行属性且本装备还未被捡起
				if(LinkedList_WeaponOnGround.get(i).Number==weaponNum)//&&(attributes_weaponOnGround[i][5]!=1))
				{
					//判断光标是否移动至地面上编号为weaponNum的武器的位置 （true:移动到了）
					boolean curcorMoveToWeapon_weaponNum
							=(mousePositionInJsfmlWindow_x>weapon_x)
							&&(mousePositionInJsfmlWindow_x<weapon_x+LinkedList_WeaponOnGround.get(i).width)
							&&(mousePositionInJsfmlWindow_y>weapon_y)
							&&(mousePositionInJsfmlWindow_y<weapon_y+LinkedList_WeaponOnGround.get(i).height);

					//若满足捡起编号为weaponNum的装备的条件，则捡起此装备放入背包（不在地面上显示此装备）
					if(curcorMoveToWeapon_weaponNum)
					{
						//将此装备信息加入包中装备链表
						LinkedList_WeaponInBackpack.add(LinkedList_WeaponOnGround.get(i));

						LinkedList_WeaponOnGround.remove(i);
						//System.out.println("@@");

						return false;
					}
				}
			}
		}
		//rectangleShape.setPosition(weapon_x, weapon_y);
		//window.draw(rectangleShape);//若此装备不应该消失，则显示
		//System.out.println("**");
		return true;
	}

	/*装备捡拾算法02
	 * 2020年5月5日
	 * 功能：将应该放入背包的田宇装备（武器等）在背包中显示*/

	int[] TianYuWeapons =new int[100];//每个变量代表田宇地面装备数组的一件的装备是否已经被捡起(0: 未被捡起）

	//RectangleShape[] picture_numbers=new RectangleShape[10];

	public boolean addWeaponOfTianYuToLinkedList_WeaponInBackpack(ArrayList tianYuArrayListForWeapons)
		/*从田宇提供的ArrayList对象获取应该在背包中显示的图片编号与RectangleShape对象并输入包中装备属性链表
		 * 2020.5.5*/
	{
		for(int i=0;i<tianYuArrayListForWeapons.size();i++)
		{
			//田宇ArrayList第i个节点
			ArrayList arrayList_i=(ArrayList) tianYuArrayListForWeapons.get(i);

			if((boolean) arrayList_i.get(1)&&(TianYuWeapons[i]==0))//若应该放入背包
			{
				//与背包中现有的装备一一比对。
				//若新加入背包的装备与背包中现有的装备同类（编号相同），则不加入包中装备属性链表，只将对应节点的装备数量属性加1
				for(int k=0;k<LinkedList_WeaponInBackpack.size();k++)
				{
					if((int) arrayList_i.get(0)==LinkedList_WeaponInBackpack.get(k).Number)
					{
						LinkedList_WeaponInBackpack.get(k).amount++;
						TianYuWeapons[i]=1;
						return true;
					}
				}

				RectangleShape RectangleShape_i=(RectangleShape)arrayList_i.get(2);
				RectangleShape_i.setOrigin(0,0);//将图片的原点设置为图片左上角
				//在链表的末尾插入此图片的参数
				LinkedList_WeaponInBackpack.add(new WeaponOnGround(0,0,100,100,(int) arrayList_i.get(0),RectangleShape_i ));
				TianYuWeapons[i]=1;
				return true;
			}
		}
		return false;
	}

	/*装备捡拾算法03
	 * 从文件中读取背包中应显示的装备编号与数量并在背包中显示
	 * 2020.5.7
	 * 点击背包中的装备，则使用一件此装备，人物属性改变
	 * 5.16 改为从数据库读取数据*/

	//各装备（1-11）（构造函数中加载）
	RectangleShape[] picture_weapons=new RectangleShape[100];

	public void addWeaponFromFileToLinkedList_WeaponInBackpack()
	//从数据库（原为文件）中读取背包中应显示的装备编号与数量并输入包中装备链表
	{
		//每个字符串代表一个装备
		if(OperateDatabase.getPackInfo().length()>0&&!OperateDatabase.getPackInfo().equals("empty"))//(PackFileModifier.readFile().length()>0)
		{
			String weaponInBackpack_string[]= OperateDatabase.getPackInfo().split("\n"); //PackFileModifier.readFile().split("\n");
			//将链表清空
			LinkedList_WeaponInBackpack.clear();

			for(int i=0;i<weaponInBackpack_string.length;i++)
			{
				//在链表的末尾插入此装备的参数

				//判断装备对应的RectangleShape对象
				RectangleShape RectangleShape1=null;

				switch (Integer.valueOf(weaponInBackpack_string[i].split(",")[0]))
				{
					case 1:
						RectangleShape1=picture_weapons[1];
						break;
					case 2:
						RectangleShape1=picture_weapons[2];
						break;
					case 3:
						RectangleShape1=picture_weapons[3];
						break;
					case 4:
						RectangleShape1=picture_weapons[4];
						break;
					case 5:
						RectangleShape1=picture_weapons[5];
						break;
					case 6:
						RectangleShape1=picture_weapons[6];
						break;
					case 7:
						RectangleShape1=picture_weapons[7];
						break;
					case 8:
						RectangleShape1=picture_weapons[8];
						break;
					case 9:
						RectangleShape1=picture_weapons[9];
						break;
					case 10:
						RectangleShape1=picture_weapons[10];
						break;
					case 11:
						RectangleShape1=picture_weapons[11];
						break;

				}

				WeaponOnGround WeaponOnGround1=new WeaponOnGround
						( Integer.valueOf(weaponInBackpack_string[i].split(",")[0]) ,
								Integer.valueOf(weaponInBackpack_string[i].split(",")[1]),
								RectangleShape1);
				LinkedList_WeaponInBackpack.add(WeaponOnGround1);
			}
		}

	}


	//人物的属性
	int HP=0;
	int MP=0;
	int EXP=0;

	/*刚刚被使用的装备的编号（从点击某件装备开始维持此装备编号，直到鼠标左键释放）
	 * 其他时间weaponNumustUsed=-1*/
	public int weaponNum_JustUsed =-1;

	private boolean releaseMouse=true;//true:代表鼠标左键在点击一件装备后释放过，用于防止按下鼠标后捡起多个装备
	public int useWeaponInPacket(RenderWindow window,Mouse mouse)
		/*点击背包中的装备，一件装备被使用（更改包中装备属性链表、包中装备属性文件、Actors类中控制人物属性的静态变量）
		 * 返回刚刚被使用的装备的编号
		 * 若未点击包中装备，返回-1
		 * 5.16将文件操作改为数据库操作*/
	{
		//System.out.println("@"+ weaponNum_JustUsed);
		if(!mouse.isButtonPressed(Mouse.Button.LEFT))//若左键释放
		{
			releaseMouse=true;
			weaponNum_JustUsed =-1;
			clickSound = false;
		}
		if((backpackOpen==true)&&mouse.isButtonPressed(Mouse.Button.LEFT)&&releaseMouse==true)
		{
			for(int i=0;i<LinkedList_WeaponInBackpack.size();i++)//遍历背包中各装备的位置并依次绘制
			{
				//若点击第i格(点击一次，仅执行一次下方代码）
				if((mouse.getPosition(window).x>gridX0+(i%4)*gridD)
						&&(mouse.getPosition(window).x<gridX0+(i%4)*gridD+weaponInGrid_D)
						&&(mouse.getPosition(window).y>gridY0+(i/4)*gridD)
						&&(mouse.getPosition(window).y<gridY0+(i/4)*gridD+weaponInGrid_D))
				{
					releaseMouse=false;//释放鼠标前，无法再次使用装备
					LinkedList_WeaponInBackpack.get(i).amount--;//数量-1
					weaponNum_JustUsed =LinkedList_WeaponInBackpack.get(i).Number;//缓存刚刚被使用的装备的编号
					OperateDatabase.updateItems(LinkedList_WeaponInBackpack.get(i).Number,0,"Use");//PackFileModifier.update("Use", LinkedList_WeaponInBackpack.get(i).Number);//更新文件
					if(LinkedList_WeaponInBackpack.get(i).amount==0)
					{
						LinkedList_WeaponInBackpack.remove(i);//若数量为0，删除此装备
					}
					if(!clickSound)
					{
						clickSound = true;
						TDMusicPlay.clickSound.play();
					}
					//根据使用的装备的编号更改Actors类中控制人物属性的静态变量
					switch(weaponNum_JustUsed)
					{

						case 1:
							if((Actors.hpHero+80)<Actors.hp)//若未加满，则正常加
								Actors.hpHero+=80;
							else
								Actors.hpHero=Actors.hp;//否则加满
							break;
						case 2:
							if((Actors.hpHero+120)<Actors.hp)
								Actors.hpHero+=120;
							else
								Actors.hpHero=Actors.hp;//否则加满
							break;
						case 3:
							if((Actors.manaHero+20)<Actors.mana)
								Actors.manaHero+=20;
							else
								Actors.manaHero=Actors.mana;//否则加满
							break;
						case 4:
							if((Actors.manaHero+40)<Actors.mana)
								Actors.manaHero+=40;
							else
								Actors.manaHero=Actors.mana;//否则加满
							break;
						case 5:
							if((Actors.manaHero+50)<Actors.mana)
								Actors.manaHero+=50;
							else
								Actors.manaHero=Actors.mana;//否则加满
							break;

						case 6:
							if((Actors.level+1) < 20)
							{
								Actors.level+=1;
								if(Actors.level == 2)
									Actors.exp = 100;
								else if(Actors.level == 3)
									Actors.exp = 200;
								else if(Actors.level == 4)
									Actors.exp = 300;
								else if(Actors.level == 5)
									Actors.exp = 400;
								else if(Actors.level == 6)
									Actors.exp = 500;
								else if(Actors.level == 7)
									Actors.exp = 600;
								else if(Actors.level == 8)
									Actors.exp = 700;
								else if(Actors.level == 9)
									Actors.exp = 800;
								else if(Actors.level == 10)
									Actors.exp = 900;
								else if(Actors.level == 11)
									Actors.exp = 1000;
								else if(Actors.level == 12)
									Actors.exp = 1200;
								else if(Actors.level == 13)
									Actors.exp = 1400;
								else if(Actors.level == 14)
									Actors.exp = 1600;
								else if(Actors.level == 15)
									Actors.exp = 1800;
								else if(Actors.level == 16)
									Actors.exp = 2000;
								else if(Actors.level == 17)
									Actors.exp = 2300;
								else if(Actors.level == 18)
									Actors.exp = 2500;
								else if(Actors.level == 19)
									Actors.exp = 2700;
							}
							else
							{
								Actors.level = 20;//否则加满
								Actors.exp = 3000;
							}
							Blood.update();
							Actors.level();
							break;
						case 7:
							if((Actors.level+2)<20)
							{
								Actors.level += 2;

								if(Actors.level == 2)
									Actors.exp = 100;
								else if(Actors.level == 3)
									Actors.exp = 200;
								else if(Actors.level == 4)
									Actors.exp = 300;
								else if(Actors.level == 5)
									Actors.exp = 400;
								else if(Actors.level == 6)
									Actors.exp = 500;
								else if(Actors.level == 7)
									Actors.exp = 600;
								else if(Actors.level == 8)
									Actors.exp = 700;
								else if(Actors.level == 9)
									Actors.exp = 800;
								else if(Actors.level == 10)
									Actors.exp = 900;
								else if(Actors.level == 11)
									Actors.exp = 1000;
								else if(Actors.level == 12)
									Actors.exp = 1200;
								else if(Actors.level == 13)
									Actors.exp = 1400;
								else if(Actors.level == 14)
									Actors.exp = 1600;
								else if(Actors.level == 15)
									Actors.exp = 1800;
								else if(Actors.level == 16)
									Actors.exp = 2000;
								else if(Actors.level == 17)
									Actors.exp = 2300;
								else if(Actors.level == 18)
									Actors.exp = 2500;
								else if(Actors.level == 19)
									Actors.exp = 2700;
							}
							else
							{
								Actors.level = 20;//否则加满
								Actors.exp = 3000;
							}
							Blood.update();
							Actors.level();
							break;
						case 8:
							Actors.damageHero+=10;
							Blood.update();
							break;
						case 9:
							Actors.damageHero+=20;
							Blood.update();
							break;
						case 10:
							Actors.mana+=30;//提高容量
							Blood.update();
							break;
						case 11:
							Actors.hp+=50;//提高容量
							Blood.update();
							break;



					}
					return weaponNum_JustUsed;//点击一个装备，只返回一次装备编号
				}
			}
		}
		return -1;
	}

	public static RectangleShape createImage( String image, int Image_x, int Image_y, int position_x, int position_y)
	{   //作者：肖文伟
		//加载本地图片
		Texture imgTexture= new Texture();
		try {
			imgTexture.loadFromFile(Paths.get(image));//从本地加载图片
		} catch (IOException e) {
			e.printStackTrace(); }
		imgTexture.setSmooth(true); //一定要加，这个是抗锯齿的方法，也就是将图片变得平滑的方法

		//容器放置游戏开始背景图图片； 放缩图片与容器大小协调：设置图片的显示大小的长和宽
		RectangleShape img = new RectangleShape(new Vector2f(Image_x, Image_y));
		img.setTexture(imgTexture);
		img.setPosition(position_x, position_y); //设置这个容器的坐标，左上角为基准
		return img;
	}

	/*public static Text creatText( String content,int fontType, int fontSize, Color c, int text_x, int text_y)
	{   //作者：肖文伟
		//
		Font font=new Font();
		try {
			if(fontType==1){font.loadFromFile(Paths.get("font/1000hurt.ttf"));}
			else if(fontType==2){font.loadFromFile(Paths.get("font/Arial00.ttf"));}
			//String fontType,
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Text text = new Text (content, font,fontSize);//内容；；字体大小
		text.setColor(c);//设置当前文本框的填充颜色为透明TRANSPARENT
		text.setPosition(text_x, text_y);//设置文本框的位置
		return text;
	}*/
}
