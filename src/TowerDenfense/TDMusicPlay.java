package TowerDenfense;

import org.jsfml.audio.Sound;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class TDMusicPlay
{
    public static Sound buttonMusic = TDLoadMusic.startMusic("Images/TD_Images/Music/button.wav");
    public static Sound selectMusic = TDLoadMusic.startMusic("Images/TD_Images/Music/select.wav");
    public static Sound buildMusic = TDLoadMusic.startMusic("Images/TD_Images/Music/build.wav");
    public static Sound addMoney = TDLoadMusic.startMusic("Images/TD_Images/Music/AddMoney.wav");

    public static Sound backgroundMusic = TDLoadMusic.loopMusic("Images/TD_Images/Music/background.ogg");
    public static Sound monsterDead = TDLoadMusic.startMusic("Images/TD_Images/Music/monsterDeath.ogg");

  //  public static Sound backgroundMain1 = TDLoadMusic.loopMusic("Sound/background1.wav");
    public static Sound dialogOpen = TDLoadMusic.startMusic("Images/TD_Images/Music/DialogOpen.wav");
    public static Sound victorySound = TDLoadMusic.startMusic("Images/TD_Images/Music/VictoryMusic.wav");
    public static Sound openTreasure = TDLoadMusic.startMusic("Images/TD_Images/Music/TreasureOpen.wav");
    public static Sound enemyHurt = TDLoadMusic.startMusic("Images/TD_Images/Music/hurt.wav");
    public static Sound normalEnemyHurt = TDLoadMusic.startMusic("Images/TD_Images/Music/birdHurt.wav");
    public static Sound birdHurt = TDLoadMusic.startMusic("Images/TD_Images/Music/NomalEnemyHurt.wav");
    public static List<Sound> hit = new ArrayList<>();
    public static List<Sound> heroMusic = new ArrayList<>();

    public static Sound endingMusic = TDLoadMusic.loopMusic("Images/TD_Images/Music/EndMusic.ogg");
    public static Sound clickSound = TDLoadMusic.startMusic("Images/TD_Images/Music/click.ogg");

    public static void loading()
    {
        for(int i = 1; i <= 5; i++)
        {
            hit.add(TDLoadMusic.startMusic("Images/TD_Images/Music/hit" + i + ".wav"));
            heroMusic.add(TDLoadMusic.startMusic("Images/TD_Images/Music/heroMusic" + i + ".wav"));
        }
    }
}
