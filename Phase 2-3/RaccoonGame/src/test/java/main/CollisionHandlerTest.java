package main;

import org.junit.*;

public class CollisionHandlerTest {

    public RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
        raccoonGame.startThread();
    }

    @Test
    public void noObject() {

        //standard position, no object
        Assert.assertEquals(999, raccoonGame.collisionHandler.checkObject(raccoonGame.player, true));
    }

    @Test
    public void isObject() {

        //Change player position an object
        raccoonGame.player.x = 1*raccoonGame.blockSize;
        raccoonGame.player.y = 10*raccoonGame.blockSize;
        Assert.assertNotEquals(999, raccoonGame.collisionHandler.checkObject(raccoonGame.player, true));
    }

    @Test
    public void noEnemy() {

        //standard position, no enemy
        Assert.assertFalse(raccoonGame.collisionHandler.checkEnemy(raccoonGame.player, true));
    }

    @Test
    public void isEnemy() {

        //Change player position an enemy
        raccoonGame.player.x = 17*raccoonGame.blockSize;
        raccoonGame.player.y = 11*raccoonGame.blockSize;
        Assert.assertTrue(raccoonGame.collisionHandler.checkEnemy(raccoonGame.player, true));
    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }
}
