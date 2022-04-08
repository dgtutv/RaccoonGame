package object;

import org.junit.Assert;
import org.junit.Test;

public class GarbageTest {

    @Test
    public void createGarbage() {
        Garbage garbage = new Garbage();


        Assert.assertEquals("Garbage", garbage.objectName);
        Assert.assertNotNull(garbage.Image);
        Assert.assertFalse(garbage.collidable);
    }
}
