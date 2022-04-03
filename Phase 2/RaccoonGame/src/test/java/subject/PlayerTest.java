package subject;

import main.RaccoonGame;
import object.Garbage;
import object.RedRaccoon;
import object.Trap;
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
            Assert.assertSame("right", raccoonGame.player.direction);
            robot.keyRelease(KeyEvent.VK_D);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_A);
            robot.delay(100);
            Assert.assertSame("left", raccoonGame.player.direction);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_A);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_W);
            robot.delay(100);
            Assert.assertSame("up", raccoonGame.player.direction);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_W);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_S);
            robot.delay(100);
            Assert.assertSame("down", raccoonGame.player.direction);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_S);

            robot.delay(200);
            Assert.assertFalse(raccoonGame.player.moving);
            Assert.assertEquals(0, raccoonGame.player.pixelCounter);
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
            robot = new Robot();
            raccoonGame.player.x = 1* raccoonGame.blockSize;
            raccoonGame.player.y = 10* raccoonGame.blockSize;

            // We tested directions in the previous function, so here let's just test whether the collision
            // condition (only move if the block you're moving to is 0) is true

            robot.keyPress(KeyEvent.VK_W);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_W);
            robot.keyPress(KeyEvent.VK_A);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_A);

            raccoonGame.player.y = 28* raccoonGame.blockSize;
            raccoonGame.player.x = 43* raccoonGame.blockSize;

            robot.keyPress(KeyEvent.VK_D);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_D);
            robot.keyPress(KeyEvent.VK_S);
            robot.delay(100);
            Assert.assertFalse(raccoonGame.player.moving);
            robot.delay(100);
            robot.keyRelease(KeyEvent.VK_S);



            raccoonGame.enemyHandler.EnemyList.add(new Enemy(raccoonGame, raccoonGame.player.x, raccoonGame.player.y, raccoonGame.player));

            robot.delay(100);
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

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void collectObject() {
        RaccoonGame raccoonGame = new RaccoonGame();
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
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_D);

            Assert.assertNull(raccoonGame.objects[objectsSize]);
            //Assert.assertEquals(raccoonGame.player.score + 10, raccoonGame.player.score);

            objectsSize++;

            raccoonGame.objects[objectsSize] = new Trap();
            raccoonGame.objectHandler.numRewards++;
            raccoonGame.objects[objectsSize].x = raccoonGame.player.x - raccoonGame.blockSize;
            raccoonGame.objects[objectsSize].y = raccoonGame.player.y;

            robot.keyPress(KeyEvent.VK_A);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_A);

            Assert.assertNull(raccoonGame.objects[objectsSize]);

            objectsSize++;

            raccoonGame.objects[objectsSize] = new RedRaccoon();
            raccoonGame.objectHandler.numRewards++;
            raccoonGame.objects[objectsSize].x = raccoonGame.player.x + raccoonGame.blockSize;
            raccoonGame.objects[objectsSize].y = raccoonGame.player.y;

            robot.keyPress(KeyEvent.VK_D);
            robot.delay(200);
            robot.keyRelease(KeyEvent.VK_D);

            Assert.assertNull(raccoonGame.objects[objectsSize]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }


}