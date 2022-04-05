package main;

import main.RaccoonGame;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.WindowEvent;

public class ObjectHandlerTest {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
    }

    @Test
    public void test_spawnItems() {

        Assert.assertEquals(2, raccoonGame.objectHandler.numDoors);
        Assert.assertEquals(1, raccoonGame.objectHandler.numRaccoon);
        Assert.assertEquals(31, raccoonGame.objectHandler.numRewards);
        Assert.assertEquals(19, raccoonGame.objectHandler.numTraps);

    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }
}
