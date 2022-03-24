package unit;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;


public class KeyHandlerTest {

    @Test
    public void test_keyPressed_titleState() {
        //create new window
        RaccoonGame raccoonGame = new RaccoonGame();

        Robot robot = null;
        try {
            robot = new Robot();
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_S);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_S);
            robot.delay(500);
            Assert.assertEquals(1, raccoonGame.gui.cursorNum);

            robot.keyPress(KeyEvent.VK_W);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_W);
            robot.delay(500);
            Assert.assertEquals(0, raccoonGame.gui.cursorNum);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(500);
            Assert.assertEquals(raccoonGame.playState, raccoonGame.gameState);

        } catch (AWTException e) {
            e.printStackTrace();
        }



    }

    @Test
    public void test_keyPressed_gameState() {
        //create new game
        RaccoonGame raccoonGame = new RaccoonGame();

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
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_S);
        robot.delay(500);
        Assert.assertEquals(originalY += raccoonGame.blockSize, raccoonGame.player.y);
        Assert.assertEquals(originalX, raccoonGame.player.x);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_D);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_D);
        robot.delay(500);
        Assert.assertEquals(originalX += raccoonGame.blockSize, raccoonGame.player.x);
        Assert.assertEquals(originalY, raccoonGame.player.y);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_W);
        robot.delay(100);
        robot.keyRelease(KeyEvent.VK_W);
        robot.delay(500);
        Assert.assertEquals(originalY -= raccoonGame.blockSize, raccoonGame.player.y);
        Assert.assertEquals(originalX, raccoonGame.player.x);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_A);
        robot.delay(100);
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


}
