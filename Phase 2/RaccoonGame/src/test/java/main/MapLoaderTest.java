package main;

import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.event.WindowEvent;

public class MapLoaderTest {

    @Test
    public void test_loadMap() {
        RaccoonGame raccoonGame = new RaccoonGame();
        int[][] testArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];
        String testMap = "/map/raccoonGameMap.txt";

        int[][] correctArr = {{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {2,9,6,0,6,0,0,6,1,2,0,0,0,0,0,0,0,0,0,0,1,7,8,0,9,0,0,9,0,1},
                {2,10,6,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,1,7,8,0,10,0,0,10,0,1},
                {2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1},
                {2,9,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,1},
                {2,10,6,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,10,0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,2,0,0,1},
                {2,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,2,0,0,1,1,1,2,0,0,0,0,0,1},
                {2,0,9,0,0,0,0,0,0,0,0,0,0,0,1,2,9,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,10,0,0,9,0,0,0,0,0,0,0,0,1,2,10,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,10,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,2,0,0,1,1,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {7,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {7,8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,9,0,0,0,0,1},
                {7,8,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,2,0,0,1,1,1,2,10,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,9,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {1,1,1,1,1,2,0,0,0,0,0,0,0,0,1,2,10,0,0,0,0,0,1,2,9,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,1,2,10,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,2,0,0,1,1,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,7,8,0,0,0,0,1},
                {2,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,7,8,0,0,0,0,1},
                {2,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,6,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,1,2,9,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,0,0,0,0,0,0,1,2,10,0,0,0,0,1},
                {2,6,0,0,6,0,0,6,1,2,0,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        };

        raccoonGame.mapLoader.loadMap(testArr, testMap);;

        Assert.assertArrayEquals(correctArr, testArr);

        raccoonGame.gameWindow.dispose();
    }
}
