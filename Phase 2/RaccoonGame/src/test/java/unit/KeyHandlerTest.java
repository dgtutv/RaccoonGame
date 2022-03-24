package unit;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class KeyHandlerTest {

    @Test
    public void test_upPress() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_W);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.keyHandler.upPressed);
            robot.keyRelease(KeyEvent.VK_W);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.keyHandler.upPressed);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_downPress() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_S);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.keyHandler.downPressed);
            robot.keyRelease(KeyEvent.VK_S);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.keyHandler.downPressed);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_rightPress() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_D);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.keyHandler.rightPressed);
            robot.keyRelease(KeyEvent.VK_D);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.keyHandler.rightPressed);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_leftPress() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_A);
            robot.delay(100);
            Assert.assertTrue(raccoonGame.keyHandler.leftPressed);
            robot.keyRelease(KeyEvent.VK_A);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.keyHandler.leftPressed);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_escPress() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            robot.delay(100);
            Assert.assertEquals(raccoonGame.pauseState, raccoonGame.gameState);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_ESCAPE);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_ESCAPE);
            robot.delay(100);
            Assert.assertEquals(raccoonGame.playState, raccoonGame.gameState);
        }
        catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
