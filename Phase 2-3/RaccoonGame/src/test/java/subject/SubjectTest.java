package subject;

import main.KeyHandler;
import main.RaccoonGame;
import main.ThreadManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class SubjectTest {

    RaccoonGame raccoonGame;
    ThreadManager threadManager;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
        raccoonGame.startThread();
        threadManager = new ThreadManager();
    }

    @Test
    public void createSubject() {
        raccoonGame.gameState = raccoonGame.playState;



        try {
            Assert.assertNotNull(raccoonGame.player);
            Assert.assertFalse(raccoonGame.player.moving);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.enemyHandler.deleteEnemies();
        Robot robot = null;

        try {
            raccoonGame.player.moving = true;
            raccoonGame.player.spriteCounter = 13;
            raccoonGame.player.update();
            Assert.assertTrue(raccoonGame.player.spriteNum == 2);

            raccoonGame.player.moving = true;
            raccoonGame.player.spriteCounter = 13;
            raccoonGame.player.update();
            Assert.assertTrue(raccoonGame.player.spriteNum == 1);


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }

}