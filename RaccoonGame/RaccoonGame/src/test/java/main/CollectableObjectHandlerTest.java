package main;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollectableObjectHandlerTest {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
        raccoonGame.startThread();
    }

    @Test
    public void test_spawnItems() {

        Assert.assertEquals(2, raccoonGame.collectableObjectHandler.numDoors);
        Assert.assertEquals(1, raccoonGame.collectableObjectHandler.numRaccoon);
        Assert.assertEquals(31, raccoonGame.collectableObjectHandler.numRewards);
        Assert.assertEquals(19, raccoonGame.collectableObjectHandler.numTraps);

    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }
}
