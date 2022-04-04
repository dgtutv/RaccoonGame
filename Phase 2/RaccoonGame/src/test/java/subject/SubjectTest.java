package subject;

import main.KeyHandler;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class SubjectTest {

    @Test
    public void createSubject() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;



        try {
            Assert.assertNotNull(raccoonGame.player);
            Assert.assertFalse(raccoonGame.player.moving);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void update() {
        RaccoonGame raccoonGame = new RaccoonGame();
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

        raccoonGame.gameWindow.dispose();
    }


}