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
            raccoonGame.player.spriteCounter = 13;
            threadManager.doTick(30, raccoonGame);
<<<<<<< HEAD:Phase 2/RaccoonGame/src/test/java/subject/SubjectTest.java
            Assert.assertTrue(raccoonGame.player.spriteNum == 2);
=======
            //Assert.assertTrue(raccoonGame.player.spriteNum == 1);
            robot.keyRelease(KeyEvent.VK_D);
>>>>>>> 551675b48f95d0fe77ff84f16e94ceb257537b6a:Phase 2-3/RaccoonGame/src/test/java/subject/SubjectTest.java

            raccoonGame.player.spriteCounter = 13;
            threadManager.doTick(30, raccoonGame);
<<<<<<< HEAD:Phase 2/RaccoonGame/src/test/java/subject/SubjectTest.java
            Assert.assertTrue(raccoonGame.player.spriteNum == 1);

=======
           // Assert.assertTrue(raccoonGame.player.spriteNum == 2);
            robot.keyRelease(KeyEvent.VK_A);
>>>>>>> 551675b48f95d0fe77ff84f16e94ceb257537b6a:Phase 2-3/RaccoonGame/src/test/java/subject/SubjectTest.java


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