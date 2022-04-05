package subject;

import main.KeyHandler;
import main.RaccoonGame;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class SubjectTest {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
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
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_D);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.player.spriteNum == 1);
            robot.keyRelease(KeyEvent.VK_D);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_A);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.player.spriteNum == 2);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_A);


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
    }

}