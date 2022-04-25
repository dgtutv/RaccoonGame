package subject;

import main.RaccoonGame;
import main.ThreadManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import Enemy.EnemyHandler;

public class EnemyTest {

    RaccoonGame raccoonGame;
    ThreadManager threadManager;
    List<Enemy> enemies;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
        threadManager = new ThreadManager();
        enemies = raccoonGame.enemyHandler.EnemyList;
    }

    @Test
    public void verifyAttributes() {
        Assert.assertEquals(enemies.get(0).range, 7);
        Assert.assertEquals(enemies.get(0).patrolHorizontalDistance, 2);
        Assert.assertEquals(enemies.get(0).patrolVerticalDistance, 2);
    }

    @Test
    public void createEnemy() {
        EnemyHandler enemyHandler = new EnemyHandler(raccoonGame, raccoonGame.player);
        raccoonGame.gameState = raccoonGame.playState;



        try {
            Assert.assertNotNull(enemies);
            Assert.assertEquals(enemies.get(0).x, 31*raccoonGame.blockSize);
            Assert.assertEquals(enemies.get(0).y, 8*raccoonGame.blockSize);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void enemyDirectionBase() {
        raccoonGame.gameState = raccoonGame.playState;

        Assert.assertEquals("down", enemies.get(0).direction);
    }

    @Test
    public void enemyDirectionPlayer() {
        raccoonGame.gameState = raccoonGame.playState;

        enemies.get(0).x = raccoonGame.player.x + raccoonGame.blockSize;
        enemies.get(0).y = raccoonGame.player.y;

        threadManager.doTick(15, raccoonGame);

        Assert.assertEquals("left", enemies.get(0).direction);
    }

    @Test
    public void enemyMovement() {
        raccoonGame.gameState = raccoonGame.playState;

        Assert.assertEquals(31*raccoonGame.blockSize, enemies.get(0).x);
        Assert.assertEquals(8*raccoonGame.blockSize, enemies.get(0).y);

        enemies.get(0).directionUpdate();
        enemies.get(0).moveUpdate();

        Assert.assertEquals(31*raccoonGame.blockSize, enemies.get(0).x);
        Assert.assertEquals(8*raccoonGame.blockSize - enemies.get(0).speed, enemies.get(0).y);

    }

    @Test
    public void enemyTracking() {
        raccoonGame.gameState = raccoonGame.playState;

        threadManager.doTick(2, raccoonGame);

        Assert.assertEquals(enemies.get(0).patrolLeftBound, enemies.get(0).targetX);
        Assert.assertEquals(enemies.get(0).patrolBottomBound, enemies.get(0).targetY);

    }

    @Test
    public void enemyTrackingPlayer() {
        raccoonGame.gameState = raccoonGame.playState;

        enemies.get(0).x = raccoonGame.player.x + raccoonGame.blockSize;
        enemies.get(0).y = raccoonGame.player.y;

        threadManager.doTick(15, raccoonGame);

        Assert.assertEquals(raccoonGame.player.x, enemies.get(0).targetX);
        Assert.assertEquals(raccoonGame.player.y, enemies.get(0).targetY);

    }






    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }
}
