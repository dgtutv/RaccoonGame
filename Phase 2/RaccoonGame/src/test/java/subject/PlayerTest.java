package subject;

import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerTest {

    @Test
    public void createPlayer() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;



        try {
            Assert.assertNotNull(raccoonGame.player);
            Assert.assertFalse(raccoonGame.player.moving);
            Assert.assertFalse(raccoonGame.player.hasEscaped);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void customUpdate() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.enemyHandler.deleteEnemies();
        Robot robot = null;

        try {
            robot = new Robot();
            raccoonGame.player.score = -10;
            robot.delay(200);
            Assert.assertTrue(raccoonGame.player.GameOver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void moveUpdate() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.enemyHandler.deleteEnemies();
        Robot robot = null;

        try {
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_D);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.player.moving);
            Assert.assertTrue(raccoonGame.player.direction == "right");
            robot.keyRelease(KeyEvent.VK_D);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_A);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.player.direction == "left");
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_A);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_W);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.player.direction == "up");
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_W);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_S);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.player.direction == "down");
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_S);

            robot.delay(200);
            Assert.assertFalse(raccoonGame.player.moving);
            Assert.assertTrue(raccoonGame.player.pixelCounter == 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void directionUpdate() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;

        try {
            raccoonGame.enemyHandler.EnemyList.add(new Enemy(raccoonGame, raccoonGame.player.x, raccoonGame.player.y, raccoonGame.player));
            robot = new Robot();
            robot.delay(100);
            Assert.assertTrue(raccoonGame.player.GameOver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }


}