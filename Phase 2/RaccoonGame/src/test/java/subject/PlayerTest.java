package subject;

import main.RaccoonGame;
import main.ThreadManager;
import object.Garbage;
import object.RedRaccoon;
import object.Trap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerTest {

    RaccoonGame raccoonGame;
    ThreadManager threadManager;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
        threadManager = new ThreadManager();
    }

    @Test
    public void createPlayer() {
        raccoonGame.startThread();
        raccoonGame.gameState = raccoonGame.playState;



        try {
            Assert.assertNotNull(raccoonGame.player);
            Assert.assertFalse(raccoonGame.player.moving);
            Assert.assertFalse(raccoonGame.player.hasEscaped);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void customUpdate() {
        raccoonGame.startThread();
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.enemyHandler.deleteEnemies();


        try {

            raccoonGame.player.score = -10;
            threadManager.doTick(30, raccoonGame);
            Assert.assertTrue(raccoonGame.player.GameOver);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void moveUpdate() {
        raccoonGame.startThread();
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.enemyHandler.deleteEnemies();
        Robot robot = null;

        try {
            robot = new Robot();
            int preX = raccoonGame.player.x;

            robot.keyPress(KeyEvent.VK_D);
            threadManager.doTick(30, raccoonGame);
            Assert.assertTrue(preX < raccoonGame.player.x);
            robot.keyRelease(KeyEvent.VK_D);

            preX = raccoonGame.player.x;

            robot.keyPress(KeyEvent.VK_A);
            threadManager.doTick(30, raccoonGame);
            Assert.assertTrue(preX > raccoonGame.player.x);
            robot.keyRelease(KeyEvent.VK_A);

            int preY = raccoonGame.player.y;

            robot.keyPress(KeyEvent.VK_W);
            threadManager.doTick(30, raccoonGame);
            Assert.assertTrue(preY > raccoonGame.player.y);
            robot.keyRelease(KeyEvent.VK_W);

            preY = raccoonGame.player.y;
            robot.keyPress(KeyEvent.VK_S);
            threadManager.doTick(30, raccoonGame);
            Assert.assertTrue(preY < raccoonGame.player.y);
            robot.keyRelease(KeyEvent.VK_S);

            threadManager.doTick(30, raccoonGame);
            Assert.assertFalse(raccoonGame.player.moving);
            Assert.assertEquals(0, raccoonGame.player.pixelCounter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void directionUpdate() {
        raccoonGame.startThread();
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;

        try {
            robot = new Robot();
            raccoonGame.player.x = 1* raccoonGame.blockSize;
            raccoonGame.player.y = 10* raccoonGame.blockSize;

            // We tested directions in the previous function, so here let's just test whether the collision
            // condition (only move if the block you're moving to is 0) is true

            robot.keyPress(KeyEvent.VK_W);
            threadManager.doTick(30, raccoonGame);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.keyRelease(KeyEvent.VK_W);

            robot.keyPress(KeyEvent.VK_A);
            threadManager.doTick(30, raccoonGame);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.keyRelease(KeyEvent.VK_A);

            raccoonGame.player.y = 28* raccoonGame.blockSize;
            raccoonGame.player.x = 43* raccoonGame.blockSize;

            robot.keyPress(KeyEvent.VK_D);
            threadManager.doTick(30, raccoonGame);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.keyRelease(KeyEvent.VK_D);

            robot.keyPress(KeyEvent.VK_S);
            threadManager.doTick(30, raccoonGame);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.keyRelease(KeyEvent.VK_S);



            raccoonGame.enemyHandler.EnemyList.add(new Enemy(raccoonGame, raccoonGame.player.x, raccoonGame.player.y, raccoonGame.player));

            threadManager.doTick(30, raccoonGame);
            Assert.assertTrue(raccoonGame.player.GameOver);

//            int objectsSize = raccoonGame.objects.length;
//            raccoonGame.objects[objectsSize] = new Garbage();
//            raccoonGame.objectHandler.numRewards++;
//            raccoonGame.objects[objectsSize].x = raccoonGame.player.x;
//            raccoonGame.objects[objectsSize].y = raccoonGame.player.y;
//
//            Assert.assertTrue(raccoonGame.player.obj);


        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void collectObject() {
        raccoonGame.gameState = raccoonGame.playState;

        Robot robot = null;

        try {

            Assert.assertFalse(raccoonGame.player.collectObject(999));





            robot = new Robot();
            int objectsSize = raccoonGame.objectHandler.itemCount + 1;
            raccoonGame.objects[objectsSize] = new Garbage();
            raccoonGame.objectHandler.numRewards++;
            raccoonGame.objects[objectsSize].x = raccoonGame.player.x + raccoonGame.blockSize;
            raccoonGame.objects[objectsSize].y = raccoonGame.player.y;

            robot.keyPress(KeyEvent.VK_D);
            threadManager.doTick(30, raccoonGame);
            robot.keyRelease(KeyEvent.VK_D);

            Assert.assertNull(raccoonGame.objects[objectsSize]);
            //Assert.assertEquals(raccoonGame.player.score + 10, raccoonGame.player.score);

            objectsSize++;

            raccoonGame.objects[objectsSize] = new Trap();
            raccoonGame.objectHandler.numRewards++;
            raccoonGame.objects[objectsSize].x = raccoonGame.player.x - raccoonGame.blockSize;
            raccoonGame.objects[objectsSize].y = raccoonGame.player.y;

            robot.keyPress(KeyEvent.VK_A);
            threadManager.doTick(30, raccoonGame);
            robot.keyRelease(KeyEvent.VK_A);

            Assert.assertNull(raccoonGame.objects[objectsSize]);

            objectsSize++;

            raccoonGame.objects[objectsSize] = new RedRaccoon();
            raccoonGame.objectHandler.numRewards++;
            raccoonGame.objects[objectsSize].x = raccoonGame.player.x + raccoonGame.blockSize;
            raccoonGame.objects[objectsSize].y = raccoonGame.player.y;

            robot.keyPress(KeyEvent.VK_D);
            threadManager.doTick(30, raccoonGame);
            robot.keyRelease(KeyEvent.VK_D);

            Assert.assertNull(raccoonGame.objects[objectsSize]);

            // Case ExitDoor is unused

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void changeScoreAndReward() {
        raccoonGame.startThread();
        raccoonGame.gameState = raccoonGame.playState;



        try {

            int preTestScore = raccoonGame.player.score;
            raccoonGame.player.changeScore(10);
            Assert.assertEquals(preTestScore + 10, raccoonGame.player.score);

            int preTestReward = raccoonGame.player.reward;
            raccoonGame.player.rewardUpdate(10);
            Assert.assertEquals(preTestReward + 10, raccoonGame.player.reward);

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