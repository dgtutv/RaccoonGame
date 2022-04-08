package Block;

import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

public class BlockManagerTest {


    @Test
    public void test_getBlockImage() {
        RaccoonGame raccoonGame = new RaccoonGame();
        BlockManager blockManager = raccoonGame.blockManager;

        for(int i = 0; i< blockManager.blockList.size(); i++) {
            //check for null blockImage
            Assert.assertNotEquals(blockManager.blockList.getImage(i), null);
            //check for collidable variable
            if(i == 0) {
                Assert.assertFalse(blockManager.blockList.isCollidable(i));
            }
            else {
                Assert.assertTrue(blockManager.blockList.isCollidable(i));
            }
        }

    }


}
