package Actors;

import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.audio.Sound;
import org.jsfml.audio.SoundBuffer;

public class Music
{
	public static Sound run;
	public static String imagePath = System.getProperty("user.dir");
	public static float volume = 17.0f;

	/*load and start music*/
	public static Sound startMusic(String path)
	{
		Sound sound = new Sound();
		SoundBuffer soundBuffer = new SoundBuffer();
		try {
			soundBuffer.loadFromFile(Paths.get(imagePath+path));
			//System.out.println("Sound duration: " + soundBuffer.getDuration().asSeconds() + " seconds");
		} catch(IOException ex) {
			//Something went wrong
			System.err.println("Failed to load the sound:");
			ex.printStackTrace();
		}

		//Create a sound and set its buffer
		sound = new Sound();
		sound.setBuffer(soundBuffer);
		sound.setVolume(17.0f);
		// sound.play();
		return sound;
	}

	/*load and then loop special music*/
	public static Sound loopMusic(String path) {
		Sound sound = new Sound();
		SoundBuffer soundBuffer = new SoundBuffer();

		try {
			soundBuffer.loadFromFile(Paths.get(imagePath+path));
			//System.out.println("Sound duration: " + soundBuffer.getDuration().asSeconds() + " seconds");
		} catch(IOException ex) {
			//Something went wrong
			System.err.println("Failed to load the sound:");
			ex.printStackTrace();
		}
		//Create a sound and set its buffer
		sound = new Sound();
		sound.setBuffer(soundBuffer);
		sound.setVolume(200.0f);
		sound.setLoop(true);
		//sound.play();
		return sound;

	}
	/*load and then loop special music*/
	public static Sound backMusic(String path) {
		Sound sound = new Sound();
		SoundBuffer soundBuffer = new SoundBuffer();

		try {
			soundBuffer.loadFromFile(Paths.get(imagePath+path));
			//System.out.println("Sound duration: " + soundBuffer.getDuration().asSeconds() + " seconds");
		} catch(IOException ex) {
			//Something went wrong
			System.err.println("Failed to load the sound:");
			ex.printStackTrace();
		}
		//Create a sound and set its buffer
		sound = new Sound();
		sound.setBuffer(soundBuffer);
		sound.setVolume(17.0f);
		sound.setLoop(true);
		//sound.play();
		return sound;

	}




}