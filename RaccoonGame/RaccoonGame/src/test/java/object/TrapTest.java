package object;

import CollectableObject.Trap;
import Factory.CollectableObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class TrapTest {

    @Test
    public void createTrap() {
        Trap trap = CollectableObjectFactory.createTrap();


        Assert.assertEquals("Trap", trap.objectName);
        Assert.assertNotNull(trap.Image);
        Assert.assertFalse(trap.collidable);
    }
}
