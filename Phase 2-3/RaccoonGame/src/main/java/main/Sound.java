package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Sound implementation and functionality.
 */
public class Sound {

    Clip clip;
    public URL[] soundPaths;

    /**
     * Constructs a new Sound class which initializes its URL array to a capacity of 9 and also
     * fills that array with the correct resource path's for the .WAV sound files.
     */
    public Sound() {
        soundPaths = new URL[9];
        soundPaths[0] = getClass().getResource("/sound/raccoon.wav");
        soundPaths[1] = getClass().getResource("/sound/power up.wav");
        soundPaths[2] = getClass().getResource("/sound/cage trap.wav");
        soundPaths[3] = getClass().getResource("/sound/lady spawn.wav");
        soundPaths[4] = getClass().getResource("/sound/swoon.wav");
        soundPaths[5] = getClass().getResource("/sound/door.wav");
        soundPaths[6] = getClass().getResource("/sound/winning.wav");
        soundPaths[7] = getClass().getResource("/sound/death.wav");
        soundPaths[8] = getClass().getResource("/sound/music.wav");
    }

    /**
     * Takes in an integer that corresponds to an index for soundPaths URL array, uses AudioInputStream
     * and Clip object to open this sound file.
     */
    public void setSound(int i) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPaths[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts the current clip set by setSound.
     */
    public void playSound() {
        clip.start();
    }

    /**
     * Loops the current clip set by setSound.
     */
    public void loopSound() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the current clip set by setSound.
     */
    public void stopSound() {
        clip.stop();
    }

    /**
     * Flushes the current clip set by setSound.
     */
    public void flushSound() { clip.flush(); }


    /**
     * Takes in an integer that corresponds to an index for soundPaths URL array and a Sound object,
     * opens the sound file and sets it to clip, plays the clip, and loops the clip so that the sound
     * is continually played (music).
     */
    public void music(int i, Sound sound) {
        sound.setSound(i);
        sound.playSound();
        sound.loopSound();
    }

    /**
     * Takes in an integer that corresponds to an index for soundPaths URL array and a Sound object,
     * opens the sound file and sets it to clip, and plays the clip so that the sound is played once
     * (sound effect).
     */
    public void effect(int i, Sound sound) {
        sound.setSound(i);
        sound.playSound();
    }

    /**
     * Takes in a Sound object and stops a sound if one is currently playing.
     */
    public void stop(Sound sound) {
        sound.stopSound();
    }
}
