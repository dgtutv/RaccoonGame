package main;
import main.RaccoonGame;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class KeyHandlerTest {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
    }

    @Test
    public void test_upPress() {
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

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }
}
