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
    URL soundPaths[] = new URL[30];

    public Sound() {
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

    //copy constructor
    public Sound(Sound sound){
        this.clip = sound.clip;
        this.soundPaths = sound.soundPaths;
    }

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

    public void playSound() {
        clip.start();
    }

    public void loopSound() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stopSound() {
        clip.stop();
    }

    public void flushSound() { clip.flush(); }

    public void music(int i, Sound sound) {
        sound.setSound(i);
        sound.playSound();
        sound.loopSound();
    }

    public void effect(int i, Sound sound) {
        sound.setSound(i);
        sound.playSound();
    }

    public void stop(Sound sound) {
        sound.stopSound();
    }
}
