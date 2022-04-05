package block;

import block.MapManager;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class MapManagerTest {


    @Test
    public void test_getBlockImage() {
        MapManager mapManager = new MapManager(new RaccoonGame());
        mapManager.getBlockImage();

        for(int i=0; i<mapManager.blocks.length; i++) {
            //check for null blockImage
            Assert.assertNotEquals(mapManager.blocks[i].blockImage, null);
            //check for collidable variable
            if(i == 0) {
                Assert.assertFalse(mapManager.blocks[i].collidable);
            }
            else {
                Assert.assertTrue(mapManager.blocks[i].collidable);
            }
        }

    }


}
