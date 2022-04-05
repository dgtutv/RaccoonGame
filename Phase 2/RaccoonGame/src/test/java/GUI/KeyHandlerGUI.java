package GUI;
import main.RaccoonGame;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class KeyHandlerGUI {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        RaccoonGame raccoonGame = new RaccoonGame();
    }

    @Test
    public void titleScreenKeyPress() {

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

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
    }
}
