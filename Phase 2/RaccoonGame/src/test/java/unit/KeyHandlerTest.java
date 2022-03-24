package unit;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;


public class KeyHandlerTest {

    @Test
    public void test_keyPressed_titleState() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.setupGame();
        raccoonGame.gameState = raccoonGame.titleState;

        Robot robot = null;
        try {
            robot = new Robot();

        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.delay(100);
        robot.keyPress(KeyEvent.VK_S);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_S);
        robot.delay(500);
        Assert.assertEquals(1, raccoonGame.gui.cursorNum);

        robot.keyPress(KeyEvent.VK_W);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_W);
        robot.delay(500);
        Assert.assertEquals(0, raccoonGame.gui.cursorNum);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(500);
        Assert.assertEquals(raccoonGame.playState, raccoonGame.gameState);
    }

    @Test
    public void test_keyPressed_gameState() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.setupGame();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        int originalX = raccoonGame.player.x;
        int originalY = raccoonGame.player.y;

        for(int i=0; i<raccoonGame.blockSize; i+= raccoonGame.player.speed) robot.keyPress(KeyEvent.VK_S);
        Assert.assertEquals(originalY += raccoonGame.blockSize, raccoonGame.player.y);
        Assert.assertEquals(originalX, raccoonGame.player.x);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        for(int i=0; i<raccoonGame.blockSize; i+= raccoonGame.player.speed) robot.keyPress(KeyEvent.VK_D);
        Assert.assertEquals(originalX += raccoonGame.blockSize, raccoonGame.player.x);
        Assert.assertEquals(originalY, raccoonGame.player.y);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        for(int i=0; i<raccoonGame.blockSize; i+= raccoonGame.player.speed) robot.keyPress(KeyEvent.VK_W);
        Assert.assertEquals(originalY - raccoonGame.blockSize, raccoonGame.player.y);
        Assert.assertEquals(originalX, raccoonGame.player.x);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        for(int i=0; i<raccoonGame.blockSize; i+= raccoonGame.player.speed) robot.keyPress(KeyEvent.VK_A);
        Assert.assertEquals(originalX - raccoonGame.blockSize, raccoonGame.player.x);
        Assert.assertEquals(originalY, raccoonGame.player.y);
        raccoonGame.player.x = originalX;
        raccoonGame.player.y = originalY;

        robot.keyPress(KeyEvent.VK_ESCAPE);
        Assert.assertEquals(raccoonGame.playState, raccoonGame.pauseState);
    }


}
