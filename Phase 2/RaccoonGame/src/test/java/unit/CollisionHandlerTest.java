package unit;

import main.CollisionHandler;
import main.KeyHandler;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;
import subject.Player;

public class CollisionHandlerTest {

    @Test
    public void test_checkObject() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.setupGame();

        //standard position, no object
        Assert.assertEquals(999, raccoonGame.collisionHandler.checkObject(raccoonGame.player, true));

        //Change player position an object
        raccoonGame.player.x = 1*raccoonGame.blockSize;
        raccoonGame.player.y = 10*raccoonGame.blockSize;
        Assert.assertNotEquals(999, raccoonGame.collisionHandler.checkObject(raccoonGame.player, true));
    }

    @Test
    public void test_checkEnemy() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.setupGame();

        //standard position, no enemy
        Assert.assertFalse(raccoonGame.collisionHandler.checkEnemy(raccoonGame.player, true));

        //Change player position an enemy
        raccoonGame.player.x = 17*raccoonGame.blockSize;
        raccoonGame.player.y = 11*raccoonGame.blockSize;
        Assert.assertTrue(raccoonGame.collisionHandler.checkEnemy(raccoonGame.player, true));
    }
}
