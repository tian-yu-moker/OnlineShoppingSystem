package Actors;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.system.Vector2f;

import DatabaseManager.OperateDatabase;
import GUI.HeroEquipments;
import GameDriver.Driver;
import GameDriver.staticVariable;

public class Blood extends Actors
{
	public static HeroEquipments equipments;
	static String path ="heroInfo/"+heroType+"Info.txt";
	static File file = new File(path);
	 //length of hero hp
    public static float hplengthHero;
    //length of hero mana 
    public static float manalengthHero;  
	//length of enemy hp
    public static float hplengthEnemy;
    //Determine whether the hero is dead.
    private static boolean deadState = false;
    public boolean isfilled = false;

    //Buffer
    private static float buffferAttack = 0;
    private static float bufferMana = 0;
    private static float bufferHp = 0;
    private static float bufferSp = 0;
    private static boolean first = true;
    
    
	 public static float GethpLength_hero() {
		 return hplengthHero=hpHero;	 
	 }
	 
	 public static float GetmanaLength_hero() {
			return manalengthHero= manaHero;	
			
	 }
    
	//hero attack enemy
	 public static float Attack_Enemy(float damageHero)
	 {
	 	if(damageHero<30)
	 	{
	 		manaHero-=damageHero/2;  //hero mana value will decrease when using skill
			    // System.out.println(manaHero); 
			GetmanaLength_hero();
	 	}
	 	else if(damageHero>=30)
	 	{
	 		manaHero-=damageHero/3;  //hero mana value will decrease when using skill
				   // System.out.println(manaHero); 
			GetmanaLength_hero();
	 	}
	 	if(manaHero<0)
	 	{
	 		manaHero=0;
	 		//System.out.println(String.format("hero mana is 0"));
	 	}
	 	return manaHero;

	 }
		
	
	//enemy attack hero 
	 public static void Attack_Hero(float damageEnemy,float armorHero)
	 {
		
		 float realDamage = damageEnemy- armorHero; // the real damage of enemy to hero
	        hpHero = hpHero - realDamage;
	         //System.out.println(hpHero);
	       
	        if(hpHero <0)
	        {
	           hpHero = 0;
	           deadState = true;
	           return;
	        }
	      GethpLength_hero();	 		 
	 }	 
	 public static boolean getDeathState()
	 {
	 	if(hpHero < 0)
		{
			hpHero = 0;
			deadState = true;
		}
	 	return deadState;
	 }
	//the hp and mana of hero by increasing level 


	 public static void equihero()
	 {		    
	 	//First read
	 	if(first)
		{
			totalHp = hp + equiHp;
			totalHp = mana + equiMana;
			totalDamage = damageHero + equiDamage;
			movespeed = movespeed -equiSpeed;
	
			//Set buffers
			bufferHp = equiHp;
			bufferMana = equiMana;
			bufferSp = equiSpeed;
			buffferAttack = equiDamage;
			first = false;
		}
       
	 	//If the equipments do not update
	 	if(bufferHp == equiHp) {
	 		bufferHp=equiHp;
	 		equiHp = 0;
	 		totalHp = totalHp + equiHp;
	 		
	 	}
	 	//If update, delete the old ones and add new ones
	 	else if(bufferHp != equiHp) {
	 		totalHp -= bufferHp;
	 		totalHp = totalHp + equiHp;
	 		 bufferHp=equiHp;
	 	}

	 	if(bufferMana == equiMana) { 		
	 		bufferMana=equiMana;
	 		equiMana = 0;
	 		totalMana = totalMana + equiMana;
	 	}
	 	else if(bufferMana != equiMana) {
	 		totalMana -= bufferMana;
	 		totalMana =totalMana + equiMana;
			bufferMana=equiMana;
	 	}

	 	if(buffferAttack == equiDamage) {
	 		buffferAttack=equiDamage;
	 		equiDamage = 0;
	 		totalDamage= totalDamage + equiDamage;
	 	}
	 	else if(buffferAttack != equiDamage) {
             totalDamage -= buffferAttack;
             totalDamage =totalDamage + equiDamage;
			 buffferAttack = equiDamage;
	 	}

	 	if(bufferSp == equiSpeed) { 		
	 		bufferSp=equiSpeed;
	 		equiSpeed = 0;
	 		totalSpeed= totalSpeed- equiSpeed;	
	 	}
	 	else if(bufferSp != equiSpeed) {
	 		totalSpeed -= bufferSp;
	 		totalSpeed = totalSpeed -equiSpeed;	
	 		 bufferSp = equiSpeed;
	 	}
	
	 	GethpLength_hero();
	 	GetmanaLength_hero();
	 	//System.out.println("the damagehero is"+damageHero);
	 	//System.out.println("the hphero is"+hpHero);
	 	//System.out.println("the manahero is"+manaHero);
	 	//System.out.println("the movespeed is"+movespeed);
	 }
		 
	public static void setFullBlood()
	{
		hpHero = hp;
		manaHero=mana;
		GethpLength_hero();
        GetmanaLength_hero();
	}
	
	//get the hero info from database
		public static void getHeroInfo() {
				ArrayList herolist=OperateDatabase.getHeroInfo();
				if(herolist.size()!=0) {
				Actors.level=(int) herolist.get(0);
				Actors.hp=(float) herolist.get(1);
				Actors.damageHero=(float) herolist.get(2);
				Actors.mana=(float) herolist.get(3);	        
				Actors.movespeed=(float) herolist.get(4);
		        Actors.exp=(int) herolist.get(5);
			    Actors.hpHero=Actors.hp;
			    Actors.manaHero=Actors.mana;
			    GethpLength_hero();
			    GetmanaLength_hero();
			}
			
		}
	//update the information of hero
		 public static void update()
		    {
			 
			 OperateDatabase.updateHero(Actors.level, Actors.hp, Actors.damageHero, Actors.mana, Actors.movespeed, Actors.exp);
		       
		    }

	public static void setDeathState()
	{
		deadState = false;
	}

	@Override
	public void moveX(String state)
	{

	}

	@Override
	public void moveY(String state) {

	}

	@Override
	public void controller() {

	}
}
		
	