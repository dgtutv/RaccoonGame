package main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RaccoonGameTest {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
    }

    @Test
    public void threadTest() {
        Assert.assertFalse(raccoonGame.gameThread.isAlive());

        raccoonGame.startThread();

        Assert.assertTrue(raccoonGame.gameThread.isAlive());
    }

    @Test
    public void gameOverTest() throws InterruptedException {
        raccoonGame.gameState = raccoonGame.playState;
        int playerPrevScore = raccoonGame.player.score;
        raccoonGame.player.GameOver = true;
        raccoonGame.update();


        Assert.assertEquals(raccoonGame.endState, raccoonGame.gameState);
        Assert.assertEquals(raccoonGame.player.reward + playerPrevScore, raccoonGame.player.score);
    }

    @Test
    public void scoreUpdate() throws InterruptedException {
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.player.score = 404;
        raccoonGame.update();

        Assert.assertEquals(raccoonGame.player.score%10, raccoonGame.gui.ones);
        Assert.assertEquals((raccoonGame.player.score/10)%10, raccoonGame.gui.tens);
        Assert.assertEquals((raccoonGame.player.score/100)%10, raccoonGame.gui.hundreds);

        Assert.assertEquals(4, raccoonGame.gui.ones);
        Assert.assertEquals(0, raccoonGame.gui.tens);
        Assert.assertEquals(4, raccoonGame.gui.hundreds);
    }

    @Test
    public void gameStateTest() {
        Assert.assertEquals(raccoonGame.titleState, raccoonGame.gameState);
    }

    @Test
    public void gameStateAssignment() {
        Assert.assertEquals(0, raccoonGame.titleState);
        Assert.assertEquals(1, raccoonGame.playState);
        Assert.assertEquals(2, raccoonGame.endState);
        Assert.assertEquals(3, raccoonGame.pauseState);
    }

    @Test
    public void correctBlockSize() {
        Assert.assertEquals(raccoonGame.pixelBlockSize* raccoonGame.blockSizeScale, raccoonGame.blockSize);
    }

    @Test
    public void initializationTest() {
        Assert.assertNotNull(raccoonGame.collisionHandler);
        Assert.assertNotNull(raccoonGame.mapLoader);
        Assert.assertNotNull(raccoonGame.sound);
        Assert.assertNotNull(raccoonGame.objectHandler);
        Assert.assertNotNull(raccoonGame.mapManager);
        Assert.assertNotNull(raccoonGame.graphMaker);
        Assert.assertNotNull(raccoonGame.gui);
        Assert.assertNotNull(raccoonGame.keyHandler);
        Assert.assertNotNull(raccoonGame.player);
        Assert.assertNotNull(raccoonGame.enemyHandler);
    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }

}
