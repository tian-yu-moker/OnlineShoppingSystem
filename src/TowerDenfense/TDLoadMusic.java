package TowerDenfense;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

import java.io.IOException;
import java.nio.file.Paths;

/*
Load the music
 */

public class TDLoadMusic
{
    public static Sound run;
    public static float volume = 30.0f;

    public static Sound startMusic(String path)
    {
        Sound sound;
        SoundBuffer soundBuffer = new SoundBuffer();
        try {
            soundBuffer.loadFromFile(Paths.get(path));
        } catch(IOException ex) {
            //Something went wrong
            System.err.println("Failed to load the sound:");
            ex.printStackTrace();
        }

        //Create a sound and set its buffer
        sound = new Sound();
        sound.setBuffer(soundBuffer);
        if(path.equals("Images/TD_Images/Music/hurt.wav"))
        {
            sound.setVolume(300.0f);
        }
        else {
            //Create a sound and set its buffer
            sound.setVolume(70.0f);
        }
        return sound;
    }

    /*load and then loop special music*/
    public static Sound loopMusic(String path) {
        Sound sound;
        SoundBuffer soundBuffer = new SoundBuffer();

        try {
            soundBuffer.loadFromFile(Paths.get(path));
            //System.out.println("Sound duration: " + soundBuffer.getDuration().asSeconds() + " seconds");
        } catch(IOException ex) {
            //Something went wrong
            System.err.println("Failed to load the sound:");
            ex.printStackTrace();
        }
        //Create a sound and set its buffer
        sound = new Sound();
        sound.setBuffer(soundBuffer);
        sound.setVolume(30.0f);
        sound.setLoop(true);

        return sound;
    }
}
