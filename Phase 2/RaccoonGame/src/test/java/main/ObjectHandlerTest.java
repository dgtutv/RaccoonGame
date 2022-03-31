package main;

import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.event.WindowEvent;

public class ObjectHandlerTest {

    @Test
    public void test_spawnItems() {
        RaccoonGame raccoonGame = new RaccoonGame();

        Assert.assertEquals(2, raccoonGame.objectHandler.numDoors);
        Assert.assertEquals(1, raccoonGame.objectHandler.numRaccoon);
        Assert.assertEquals(31, raccoonGame.objectHandler.numRewards);
        Assert.assertEquals(19, raccoonGame.objectHandler.numTraps);

        raccoonGame.gameWindow.dispose();

    }
}
