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
//main���������ҽ���һ��ʵ��
//ÿ����Ҫ����һ������ʱ������һ��drawSmallWindow����
{
	/*����������ر��㷨
	 * ������ԭ��
	 * 2020.4.4 */
	int backpack_width=1000;//�����Ŀ��
	int backpack_height=700;//�����ĸ߶�
	int backpack_x=0;//�������ϽǺ����꣨�����jsfml���ڣ�
	int backpack_y=0;
	int exit_x=backpack_x+backpack_width-200;//������ϽǺ����꣨�����jsfml���ڣ�
	int exit_y=backpack_y;



	RectangleShape picture_backpack=createImage("myImage/backpack.png", backpack_width, backpack_height, backpack_x, backpack_y);
	RectangleShape picture_exit=createImage("myImage/exit.png", 200, 200, exit_x, exit_y);
	RectangleShape picture_exitLight=createImage("myImage/exitLight.png", 200, 200, exit_x, exit_y);

	//����ƶ���С������ť��ʱ����ť�ߴ���
	//RectangleShape picture_packet_icon_Bigger=createImage("myImage/BattleUI/packet_icon.png", 150*2, 200*2, 1100-150, 600-200);//���½Ǻ�ԭͼƬ�����½��غ�
	//����ƶ���С������ť��ʱ����ť����
	RectangleShape picture_packet_icon_Light=createImage("myImage/BattleUI/backPackLight.png", 150, 200, 1100, 600);//���½Ǻ�ԭͼƬ�����½��غ�

	boolean backpackOpen=false;//�����Ƿ��
	boolean backpackClosed=false;//�����Ѿ����رգ��ɴ�״̬���رա���δ���򿪲�һ����

	//GridPosition[] gridPositions=new GridPosition[5*4];//�洢������װ��λ��(5��4�У�
	int gridD=90;//ÿ������ı߳�
	int gridX0=504;//��һ��װ��ͼƬ���Ͻǵ�����
	int gridY0=147;
	int weaponInGrid_D=70;//����װ���������Σ��ı߳�

	//�����ڱ�������ʾ���֣�װ��������
	Text text1=new Text("0", TDPictures.font, 40);
	//�����ڱ�������ʾӢ������
	Text text2=new Text("0", staticVariable.dialogFont, 35);
	Sprite heroInfo = new Sprite();
	//�����ڱ�������ʾ�ٶȡ�������
	Text text_speed=new Text("0", staticVariable.dialogFont, 30);
	Text text_attack=new Text("0", staticVariable.dialogFont, 30);


	//�����ڱ����л���Ӣ�۶�ͼ
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

	int heroInBackpack_col=8;//����
	int heroInBackpack_row=3;//����

	//���캯��
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

		text1.setColor(Color.RED);//����װ�����½����ֵ���ɫ
		text2.setColor(Color.WHITE);//Ӣ������
		heroInfo.setTexture(staticVariable.heroInfoInBackPack);
		heroInfo.setOrigin(staticVariable.heroInfoInBackPack.getSize().x / 2, staticVariable.heroInfoInBackPack.getSize().y / 2);
		heroInfo.setScale((float) 0.9, (float) 0.9);
		text_speed.setColor(Color.RED);
		text_attack.setColor(Color.RED);
		text2.setStyle(TextStyle.BOLD);

		//������Ҫ��������еĸ�װ����ͼƬ
		for(int i=1;i<=11;i++)
		{
			picture_weapons[i]=createImage("myImage/Items/"+String.valueOf(i)+".png",100,100,0,0);

		}

		//ȷ��������Ѫ����λ�ú���ɫ470  458   506
		Rectangle_HP.setPosition(190,461);
		Rectangle_HP.setFillColor(new Color(220,20,60,252));
		Rectangle_MP.setPosition(190,511);
		Rectangle_MP.setFillColor(new Color(220,20,60,250));
		Rectangle_EXP.setPosition(190,565);
		Rectangle_EXP.setFillColor(Color.YELLOW);

		//Ӣ�۶�ͼ
		Sprite_heroInBackpack.setTexture(HeroReady.get(0));//ͼƬ����
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

	/*����Ѫ����һ��*/
	RectangleShape Rectangle_HP = new RectangleShape();
	RectangleShape Rectangle_MP = new RectangleShape();
	RectangleShape Rectangle_EXP = new RectangleShape();



	public void drawSmallWindow(RenderWindow window,Mouse mouse, int openBackpack,int jsfmlWindow_x,int jsfmlWindow_y)//���Ƶ���
	{
		/*������������������ر��㷨*/

		//��Ļ�·�С������ť���Ͻǵ�����
		int backpackIcon_x=window.getSize().x-400;
		int backpackIcon_y= window.getSize().y-200;

		//�õ���������jsfml���������Ͻǵĺ�������
		int mousePositionInJsfmlWindow_x=mouse.getPosition(window).x;//-jsfmlWindow_x;
		int mousePositionInJsfmlWindow_y=mouse.getPosition(window).y;//-jsfmlWindow_y;

		//�жϹ���Ƿ��ƶ�������Ļ�·�С������ť�������ť����������������
		boolean curcorMoveToPacket_icon=mousePositionInJsfmlWindow_x>backpackIcon_x&&mousePositionInJsfmlWindow_x<backpackIcon_x+150&&mousePositionInJsfmlWindow_y>backpackIcon_y&&mousePositionInJsfmlWindow_y<backpackIcon_y+200;

		//����ƶ���С������ť��ʱ����ť������ԭΪ�ߴ���
		if(curcorMoveToPacket_icon)
		{
			//picture_packet_icon_Bigger.setPosition(backpackIcon_x-150, backpackIcon_y-200);//���½Ǻ�ԭͼƬ�����½��غ�
			//window.draw(picture_packet_icon_Bigger);//�ߴ���

			window.draw(picture_packet_icon_Light);//���������ǲ�����ͼ�꣩

		}

		//������Ƿ��ƶ�������Ļ�·�С������ť����������£��򵯳�����������2020.4.15 ��Ӵ˹��ܣ�
		if(mouse.isButtonPressed(Mouse.Button.LEFT)&&curcorMoveToPacket_icon)
		{
			openBackpack=0;
		}



		//�жϹ���Ƿ��ƶ������λ�� ��true:�ƶ����ˣ�
		boolean curcorMoveToExitIcon=mousePositionInJsfmlWindow_x>exit_x&&mousePositionInJsfmlWindow_x<exit_x+200&&mousePositionInJsfmlWindow_y>exit_y&&mousePositionInJsfmlWindow_y<exit_y+200;

		if(openBackpack==0)//�򿪱���������(�򿪱�����˲�������������
		{
			backpackOpen=true;
			backpackClosed=false;//ʹ֮ǰ�Ĺر���Ч
		}
		if(backpackClosed||(curcorMoveToExitIcon&&mouse.isButtonPressed(Mouse.Button.LEFT)))//�رձ���������
		{//һ������������رգ���һֱ���ֹر�״̬
			backpackOpen=false;
			backpackClosed=true;
		}
		if(backpackOpen)
		//��ʾ��������
		{
			window.draw(picture_backpack);

			text_speed.setPosition(225,390);
			text_speed.setString(String.valueOf((int)(70 - Actors.movespeed + 70)));

			window.draw(text_speed);
			text_attack.setPosition(360, 390);
			text_attack.setString(String.valueOf((int)Actors.damageHero));
			window.draw(text_attack);


			/*����Ӣ�۶���*/
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



			/*��ʾӢ������*/
			heroInfo.setPosition(290, 145);
			window.draw(heroInfo);
			/*��ʾ����*/


			/*����Ѫ���ڶ���*/
			int redRectangleLength_Max=240;//Ѫ���������ֵ
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


			/************************Ƕ��װ����ʰ�㷨1/2********************
			 * �����ڱ�������ʾ�񵽵�װ��*/

			/****װ����ʰ�㷨3
			 * ִ�б��д����װ����ʰ�㷨1ʧЧ,������ʾ��װ����ȫ���ļ�����
			 * ��ע�͵����д�����㷨1/2��Ч��
			 * 2020.5.7
			 * ���������**/
			addWeaponFromFileToLinkedList_WeaponInBackpack();

			/*********װ����ʰ�㷨3����************/

			//ʹ�ñ����е�װ���������
			useWeaponInPacket(window,mouse);


			for(int i=0;i<LinkedList_WeaponInBackpack.size();i++)//���������и�װ�������Բ����λ���
			{
				LinkedList_WeaponInBackpack.get(i).rectangleShape.setPosition(gridX0+(i%4)*gridD, gridY0+(i/4)*gridD);
				//LinkedList_WeaponInBackpack.get(i).rectangleShape.setPosition(gridPositions[i].x,gridPositions[i].y);

				//����װ��ͼƬ�ߴ粢�ڱ����л���
				Vector2f Vector2f1=new Vector2f(weaponInGrid_D, weaponInGrid_D);
				LinkedList_WeaponInBackpack.get(i).rectangleShape.setSize(Vector2f1);
				window.draw(LinkedList_WeaponInBackpack.get(i).rectangleShape);


				//��װ�����½���ʾװ������ ����һ��ͨ����ʾͼƬ
				/*for(int k=1;k<=5;k++)
				{
					//������Ϊk
					if(LinkedList_WeaponInBackpack.get(i).amount==k)
					{
						picture_numbers[k].setPosition(gridX0+(i%4)*gridD+40, gridY0+(i/4)*gridD+40);
						window.draw(picture_numbers[k]);

					}
				}*/
				//��װ�����½���ʾװ������ ��������ͨ���ı���ʾ 2020.5.18
				text1.setPosition(gridX0+(i%4)*gridD+30, gridY0+(i/4)*gridD+35);
				text1.setString(String.valueOf(LinkedList_WeaponInBackpack.get(i).amount));
				window.draw(text1);


			}
			/***************Ƕ��װ����ʰ�㷨����**************************/

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
	/*************************������������������ر��㷨����*******************/





	/*װ����ʰ�㷨
	 * ������ԭ��
	 * 2020��4��23��
	 * ���ܣ��������ϵ�װ���������ȣ����뱳��
	 * �����˹��ܵ��û���Ϊ����굥�������ϵ�װ��*/

	//����װ���������������Ա������װ�������ԣ����ꡢ�ߴ硢��ţ����������jsfml���ڣ���ʵʱ���£�
	LinkedList <WeaponOnGround> LinkedList_WeaponOnGround=new LinkedList();
	//����װ����������(������ʾ�ڸ����У�
	LinkedList <WeaponOnGround> LinkedList_WeaponInBackpack=new LinkedList();

	public void addWeaponOnGround(int weapon_width,int weapon_height,int weapon_x,int weapon_y,int weapon_num,RectangleShape rectangleShape)
		/*��Ŀǰ�����ϱ��Ϊweapon_num��װ����ͼƬ�ߴ硢��ʼ���꣨���Ͻǣ��ͱ�ţ���������Ϸ���й����У�ÿ��װ����һ�������ı�ţ���ͼƬ��Ӧ��RectangleShape�����������װ����������
		 * ע��ÿ��װ����һ��Ψһ�ı�ţ�����ͬ��װ���ֱ���������ţ�
		 * ÿ��װ��������ռ��ά�����һ��
		 * ÿ��װ��ֻ�ڸոճ���ʱִ��һ�α�����*/
	{
		WeaponOnGround newWeaponOnGround=new WeaponOnGround(weapon_x,weapon_y,weapon_width,weapon_height,weapon_num,rectangleShape);
		LinkedList_WeaponOnGround.add(newWeaponOnGround);
	}

	public boolean  drawAWeaponOnGround(RenderWindow window,Mouse mouse,RectangleShape rectangleShape,int weaponNum,int weapon_x,int weapon_y)
		/* ������Ҫ�ڵ�����ĳλ����ʾ���ΪweaponNum��װ��ʱ��ִ�д˺������Ƿ�������ʾ��װ���ɱ��������𣨴����װ�����⣩
		 * ����װ����������򽫴�װ���ӵ���װ��������ɾ��������������װ���������ڵ�����ʾ��װ��������false
		 * 2020��4��23��*/
	{
		//System.out.println("WeaponInBackpack size:"+LinkedList_WeaponInBackpack.size());
		//System.out.println("WeaponOnGround size:"+LinkedList_WeaponOnGround.size());

		int j=0;
		for(int i=0;i<LinkedList_WeaponOnGround.size();i++)//���������ϸ�װ��������
		{
			j++;
			if(LinkedList_WeaponOnGround.get(i).Number==weaponNum)
			{
				//System.out.println("No");
				break;
			}
			//�����������һ���ڵ���Ȼû�з��ֱ��ΪweaponNum��װ������װ���Ѿ��ӵ���װ����������ɾ����
			if((i==LinkedList_WeaponOnGround.size()-1)&&LinkedList_WeaponOnGround.get(i).Number!=weaponNum)
			{
				return false;//���ڵ�����ʾ��װ��
			}
		}
		if(j==0)//������װ������������Ϊ0
		{
			return false;//���ڵ�����ʾ��װ��
		}


		//�õ���������jsfml���������Ͻǵĺ�������
		int mousePositionInJsfmlWindow_x=mouse.getPosition(window).x;//-window.getPosition().x;
		int mousePositionInJsfmlWindow_y=mouse.getPosition(window).y;//-window.getPosition().y;

		//�������������£��п��ܼ���װ��������ʼ���������ϸ�װ��������
		if(mouse.isButtonPressed(Mouse.Button.LEFT))
		{
			for(int i=0;i<LinkedList_WeaponOnGround.size();i++)//���������ϸ�װ��������
			{
				//�����������ΪweaponNum��һ�������ұ�װ����δ������
				if(LinkedList_WeaponOnGround.get(i).Number==weaponNum)//&&(attributes_weaponOnGround[i][5]!=1))
				{
					//�жϹ���Ƿ��ƶ��������ϱ��ΪweaponNum��������λ�� ��true:�ƶ����ˣ�
					boolean curcorMoveToWeapon_weaponNum
							=(mousePositionInJsfmlWindow_x>weapon_x)
							&&(mousePositionInJsfmlWindow_x<weapon_x+LinkedList_WeaponOnGround.get(i).width)
							&&(mousePositionInJsfmlWindow_y>weapon_y)
							&&(mousePositionInJsfmlWindow_y<weapon_y+LinkedList_WeaponOnGround.get(i).height);

					//�����������ΪweaponNum��װ����������������װ�����뱳�������ڵ�������ʾ��װ����
					if(curcorMoveToWeapon_weaponNum)
					{
						//����װ����Ϣ�������װ������
						LinkedList_WeaponInBackpack.add(LinkedList_WeaponOnGround.get(i));

						LinkedList_WeaponOnGround.remove(i);
						//System.out.println("@@");

						return false;
					}
				}
			}
		}
		//rectangleShape.setPosition(weapon_x, weapon_y);
		//window.draw(rectangleShape);//����װ����Ӧ����ʧ������ʾ
		//System.out.println("**");
		return true;
	}

	/*װ����ʰ�㷨02
	 * 2020��5��5��
	 * ���ܣ���Ӧ�÷��뱳��������װ���������ȣ��ڱ�������ʾ*/

	int[] TianYuWeapons =new int[100];//ÿ�����������������װ�������һ����װ���Ƿ��Ѿ�������(0: δ������

	//RectangleShape[] picture_numbers=new RectangleShape[10];

	public boolean addWeaponOfTianYuToLinkedList_WeaponInBackpack(ArrayList tianYuArrayListForWeapons)
		/*�������ṩ��ArrayList�����ȡӦ���ڱ�������ʾ��ͼƬ�����RectangleShape�����������װ����������
		 * 2020.5.5*/
	{
		for(int i=0;i<tianYuArrayListForWeapons.size();i++)
		{
			//����ArrayList��i���ڵ�
			ArrayList arrayList_i=(ArrayList) tianYuArrayListForWeapons.get(i);

			if((boolean) arrayList_i.get(1)&&(TianYuWeapons[i]==0))//��Ӧ�÷��뱳��
			{
				//�뱳�������е�װ��һһ�ȶԡ�
				//���¼��뱳����װ���뱳�������е�װ��ͬ�ࣨ�����ͬ�����򲻼������װ����������ֻ����Ӧ�ڵ��װ���������Լ�1
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
				RectangleShape_i.setOrigin(0,0);//��ͼƬ��ԭ������ΪͼƬ���Ͻ�
				//�������ĩβ�����ͼƬ�Ĳ���
				LinkedList_WeaponInBackpack.add(new WeaponOnGround(0,0,100,100,(int) arrayList_i.get(0),RectangleShape_i ));
				TianYuWeapons[i]=1;
				return true;
			}
		}
		return false;
	}

	/*װ����ʰ�㷨03
	 * ���ļ��ж�ȡ������Ӧ��ʾ��װ��������������ڱ�������ʾ
	 * 2020.5.7
	 * ��������е�װ������ʹ��һ����װ�����������Ըı�
	 * 5.16 ��Ϊ�����ݿ��ȡ����*/

	//��װ����1-11�������캯���м��أ�
	RectangleShape[] picture_weapons=new RectangleShape[100];

	public void addWeaponFromFileToLinkedList_WeaponInBackpack()
	//�����ݿ⣨ԭΪ�ļ����ж�ȡ������Ӧ��ʾ��װ��������������������װ������
	{
		//ÿ���ַ�������һ��װ��
		if(OperateDatabase.getPackInfo().length()>0&&!OperateDatabase.getPackInfo().equals("empty"))//(PackFileModifier.readFile().length()>0)
		{
			String weaponInBackpack_string[]= OperateDatabase.getPackInfo().split("\n"); //PackFileModifier.readFile().split("\n");
			//���������
			LinkedList_WeaponInBackpack.clear();

			for(int i=0;i<weaponInBackpack_string.length;i++)
			{
				//�������ĩβ�����װ���Ĳ���

				//�ж�װ����Ӧ��RectangleShape����
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


	//���������
	int HP=0;
	int MP=0;
	int EXP=0;

	/*�ոձ�ʹ�õ�װ���ı�ţ��ӵ��ĳ��װ����ʼά�ִ�װ����ţ�ֱ���������ͷţ�
	 * ����ʱ��weaponNumustUsed=-1*/
	public int weaponNum_JustUsed =-1;

	private boolean releaseMouse=true;//true:�����������ڵ��һ��װ�����ͷŹ������ڷ�ֹ�������������װ��
	public int useWeaponInPacket(RenderWindow window,Mouse mouse)
		/*��������е�װ����һ��װ����ʹ�ã����İ���װ��������������װ�������ļ���Actors���п����������Եľ�̬������
		 * ���ظոձ�ʹ�õ�װ���ı��
		 * ��δ�������װ��������-1
		 * 5.16���ļ�������Ϊ���ݿ����*/
	{
		//System.out.println("@"+ weaponNum_JustUsed);
		if(!mouse.isButtonPressed(Mouse.Button.LEFT))//������ͷ�
		{
			releaseMouse=true;
			weaponNum_JustUsed =-1;
			clickSound = false;
		}
		if((backpackOpen==true)&&mouse.isButtonPressed(Mouse.Button.LEFT)&&releaseMouse==true)
		{
			for(int i=0;i<LinkedList_WeaponInBackpack.size();i++)//���������и�װ����λ�ò����λ���
			{
				//�������i��(���һ�Σ���ִ��һ���·����룩
				if((mouse.getPosition(window).x>gridX0+(i%4)*gridD)
						&&(mouse.getPosition(window).x<gridX0+(i%4)*gridD+weaponInGrid_D)
						&&(mouse.getPosition(window).y>gridY0+(i/4)*gridD)
						&&(mouse.getPosition(window).y<gridY0+(i/4)*gridD+weaponInGrid_D))
				{
					releaseMouse=false;//�ͷ����ǰ���޷��ٴ�ʹ��װ��
					LinkedList_WeaponInBackpack.get(i).amount--;//����-1
					weaponNum_JustUsed =LinkedList_WeaponInBackpack.get(i).Number;//����ոձ�ʹ�õ�װ���ı��
					OperateDatabase.updateItems(LinkedList_WeaponInBackpack.get(i).Number,0,"Use");//PackFileModifier.update("Use", LinkedList_WeaponInBackpack.get(i).Number);//�����ļ�
					if(LinkedList_WeaponInBackpack.get(i).amount==0)
					{
						LinkedList_WeaponInBackpack.remove(i);//������Ϊ0��ɾ����װ��
					}
					if(!clickSound)
					{
						clickSound = true;
						TDMusicPlay.clickSound.play();
					}
					//����ʹ�õ�װ���ı�Ÿ���Actors���п����������Եľ�̬����
					switch(weaponNum_JustUsed)
					{

						case 1:
							if((Actors.hpHero+80)<Actors.hp)//��δ��������������
								Actors.hpHero+=80;
							else
								Actors.hpHero=Actors.hp;//�������
							break;
						case 2:
							if((Actors.hpHero+120)<Actors.hp)
								Actors.hpHero+=120;
							else
								Actors.hpHero=Actors.hp;//�������
							break;
						case 3:
							if((Actors.manaHero+20)<Actors.mana)
								Actors.manaHero+=20;
							else
								Actors.manaHero=Actors.mana;//�������
							break;
						case 4:
							if((Actors.manaHero+40)<Actors.mana)
								Actors.manaHero+=40;
							else
								Actors.manaHero=Actors.mana;//�������
							break;
						case 5:
							if((Actors.manaHero+50)<Actors.mana)
								Actors.manaHero+=50;
							else
								Actors.manaHero=Actors.mana;//�������
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
								Actors.level = 20;//�������
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
								Actors.level = 20;//�������
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
							Actors.mana+=30;//�������
							Blood.update();
							break;
						case 11:
							Actors.hp+=50;//�������
							Blood.update();
							break;



					}
					return weaponNum_JustUsed;//���һ��װ����ֻ����һ��װ�����
				}
			}
		}
		return -1;
	}

	public static RectangleShape createImage( String image, int Image_x, int Image_y, int position_x, int position_y)
	{   //���ߣ�Ф��ΰ
		//���ر���ͼƬ
		Texture imgTexture= new Texture();
		try {
			imgTexture.loadFromFile(Paths.get(image));//�ӱ��ؼ���ͼƬ
		} catch (IOException e) {
			e.printStackTrace(); }
		imgTexture.setSmooth(true); //һ��Ҫ�ӣ�����ǿ���ݵķ�����Ҳ���ǽ�ͼƬ���ƽ���ķ���

		//����������Ϸ��ʼ����ͼͼƬ�� ����ͼƬ��������СЭ��������ͼƬ����ʾ��С�ĳ��Ϳ�
		RectangleShape img = new RectangleShape(new Vector2f(Image_x, Image_y));
		img.setTexture(imgTexture);
		img.setPosition(position_x, position_y); //����������������꣬���Ͻ�Ϊ��׼
		return img;
	}

	/*public static Text creatText( String content,int fontType, int fontSize, Color c, int text_x, int text_y)
	{   //���ߣ�Ф��ΰ
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
		Text text = new Text (content, font,fontSize);//���ݣ��������С
		text.setColor(c);//���õ�ǰ�ı���������ɫΪ͸��TRANSPARENT
		text.setPosition(text_x, text_y);//�����ı����λ��
		return text;
	}*/
}
