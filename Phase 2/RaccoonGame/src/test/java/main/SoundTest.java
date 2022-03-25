package main;

import main.Sound;
import org.junit.Assert;
import org.junit.Test;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundTest {

    @Test
    public void soundPathCheck() {
        Sound sound = new Sound();

        Assert.assertEquals(sound.soundPaths[0], getClass().getResource("/sound/raccoon.wav"));
        Assert.assertEquals(sound.soundPaths[1], getClass().getResource("/sound/power up.wav"));
        Assert.assertEquals(sound.soundPaths[2], getClass().getResource("/sound/cage trap.wav"));
        Assert.assertEquals(sound.soundPaths[3], getClass().getResource("/sound/lady spawn.wav"));
        Assert.assertEquals(sound.soundPaths[4], getClass().getResource("/sound/swoon.wav"));
        Assert.assertEquals(sound.soundPaths[5], getClass().getResource("/sound/door.wav"));
        Assert.assertEquals(sound.soundPaths[6], getClass().getResource("/sound/winning.wav"));
        Assert.assertEquals(sound.soundPaths[7], getClass().getResource("/sound/death.wav"));
        Assert.assertEquals(sound.soundPaths[8], getClass().getResource("/sound/music.wav"));
    }

}
