package object;

import CollectableObject.RedRaccoon;
import Factory.CollectableObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class RedRaccoonTest {

    @Test
    public void createTrap() {
        RedRaccoon redRaccoon = CollectableObjectFactory.createRedRaccoon();


        Assert.assertEquals("RedRaccoon", redRaccoon.objectName);
        Assert.assertNotNull(redRaccoon.Image);
        Assert.assertFalse(redRaccoon.collidable);
    }
}
