package object;

import CollectableObject.Garbage;
import Factory.CollectableObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class GarbageTest {

    @Test
    public void createGarbage() {
        Garbage garbage = CollectableObjectFactory.createGarbage();


        Assert.assertEquals("Garbage", garbage.objectName);
        Assert.assertNotNull(garbage.Image);
        Assert.assertFalse(garbage.collidable);
    }
}
