package object;

import org.junit.Assert;
import org.junit.Test;

public class TrapTest {

    @Test
    public void createTrap() {
        Trap trap = new Trap();


        Assert.assertEquals("Trap", trap.objectName);
        Assert.assertNotNull(trap.Image);
        Assert.assertFalse(trap.collidable);
    }
}
