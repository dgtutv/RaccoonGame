package object;

import CollectableObject.ExitDoor;
import Factory.CollectableObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ExitDoorTest {

    @Test
    public void createExitDoor() {
        ExitDoor exitDoor = CollectableObjectFactory.createExitDoor();


        Assert.assertEquals("ExitDoor", exitDoor.objectName);
        Assert.assertNotNull(exitDoor.Image);
        Assert.assertTrue(exitDoor.collidable);
    }
}
