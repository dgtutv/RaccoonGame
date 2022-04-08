package object;

import org.junit.Assert;
import org.junit.Test;

public class ExitDoorTest {

    @Test
    public void createExitDoor() {
        ExitDoor exitDoor = new ExitDoor();


        Assert.assertEquals("ExitDoor", exitDoor.objectName);
        Assert.assertNotNull(exitDoor.Image);
        Assert.assertTrue(exitDoor.collidable);
    }
}
