package main;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;


public class KeyHandlerMovement {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
    }

    @Test
    public void gamePlayKeyPress() {

        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        int originalX = raccoonGame.player.x;
        int originalY = raccoonGame.player.y;

        robot.keyPress(KeyEvent.VK_S);
        robot.keyRelease(KeyEvent.VK_S);
        robot.delay(500);
        Assert.assertEquals(originalY += raccoonGame.blockSize, raccoonGame.player.y);
        Assert.assertEquals(originalX, raccoonGame.player.x);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_D);
        robot.keyRelease(KeyEvent.VK_D);
        robot.delay(500);
        Assert.assertEquals(originalX += raccoonGame.blockSize, raccoonGame.player.x);
        Assert.assertEquals(originalY, raccoonGame.player.y);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_W);
        robot.delay(500);
        Assert.assertEquals(originalY -= raccoonGame.blockSize, raccoonGame.player.y);
        Assert.assertEquals(originalX, raccoonGame.player.x);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.delay(500);
        Assert.assertEquals(originalX -= raccoonGame.blockSize, raccoonGame.player.x);
        Assert.assertEquals(originalY, raccoonGame.player.y);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_ESCAPE);
        robot.delay(500);
        Assert.assertEquals(raccoonGame.pauseState, raccoonGame.gameState);
    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
    }

}
