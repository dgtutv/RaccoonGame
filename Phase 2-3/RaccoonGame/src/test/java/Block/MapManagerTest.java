package Block;

import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

public class MapManagerTest {


    @Test
    public void test_getBlockImage() {
        RaccoonGame raccoonGame = new RaccoonGame();
        MapManager mapManager = raccoonGame.mapManager;

        for(int i=0; i<mapManager.blockList.size(); i++) {
            //check for null blockImage
            Assert.assertNotEquals(mapManager.blockList.getImage(i), null);
            //check for collidable variable
            if(i == 0) {
                Assert.assertFalse(mapManager.blockList.isCollidable(i));
            }
            else {
                Assert.assertTrue(mapManager.blockList.isCollidable(i));
            }
        }

    }


}
