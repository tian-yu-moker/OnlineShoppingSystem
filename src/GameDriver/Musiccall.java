package GameDriver;
import org.jsfml.audio.Sound;

import Actors.Music;

public class Musiccall {
	
	//background music
			public static Sound backMusic1 = Music.backMusic("/Sound//background1.wav");	
			public static Sound backMusic3 = Music.backMusic("/Sound//background3.wav");
			public static Sound backMusic4 = Music.backMusic("/Sound//background4.wav");
			public static Sound backMusic5 = Music.backMusic("/Sound//background5.wav");
			public static Sound backMusic7 = Music.backMusic("/Sound//background7.wav");
			public static Sound backMusic8 = Music.backMusic("/Sound//background8.wav");
			public static Sound backMusic9 = Music.backMusic("/Sound//background9.wav");
			public static Sound backMusic10 = Music.backMusic("/Sound//background10.wav");
	
	public static Sound gameover = Music.startMusic("/Sound//gameover.ogg");
	//run
	public static Sound run = Music.loopMusic("/Sound//run4.wav");

	//Saber music
	public  Sound Saber_attack=Music.startMusic("/Sound//Saber_attack.wav");
	public  Sound Saber_skill1=Music.startMusic("/Sound//Saber_skill1.wav");
	public  Sound Saber_skill2=Music.startMusic("/Sound//Saber_skill2.wav");
	public  Sound Saber_skill3=Music.startMusic("/Sound//Saber_skill3.wav");
	public  Sound Saber_skill4=Music.startMusic("/Sound//Saber_skill4.wav");
	public  Sound Saber_died=Music.startMusic("/Sound//herodied.ogg");
	public  Sound Saber_sound=Music.startMusic("/Sound//Saber_sound.wav");

	//Lancer music
	public  Sound Lancer_attack=Music.startMusic("/Sound//Lancer_attack.wav");
	public  Sound Lancer_skill1=Music.startMusic("/Sound//Lancer_skill1.wav");
	public  Sound Lancer_skill2=Music.startMusic("/Sound//Lancer_skill2.wav");
	public  Sound Lancer_skill3=Music.startMusic("/Sound//Lancer_skill3.wav");
	public  Sound Lancer_skill4=Music.startMusic("/Sound//Lancer_skill4.wav");
	public  Sound Lancer_died=Music.startMusic("/Sound//herodied.ogg");
	public  Sound Lancer_sound=Music.startMusic("/Sound//Lancer_sound.wav");
	
	//Knight music
	public Sound Knight_attack=Music.startMusic("/Sound//Knight_attack.wav");
	public Sound Knight_skill1=Music.startMusic("/Sound//Knight_skill1.wav");
	public Sound Knight_skill2=Music.startMusic("/Sound//Knight_skill2.wav");
	public Sound Knight_skill3=Music.startMusic("/Sound//Knight_skill3.wav");
	public Sound Knight_skill4=Music.startMusic("/Sound//Knight_skill4.wav");
	public Sound Knight_died=Music.startMusic("/Sound//herodied.ogg");
	public Sound Knight_sound=Music.startMusic("/Sound//Knight_sound.wav");
    
	//enemies music
	public static Sound enemies_attack=Music.startMusic("/Sound//enemies_attack1.wav");
	public static Sound enemies_died=Music.startMusic("/Sound//enemies_died.wav");
	public static Sound boss_died=Music.startMusic("/Sound//boss_died.wav");
	public static Sound boss_attack=Music.startMusic("/Sound//boss_attack.wav");
}
