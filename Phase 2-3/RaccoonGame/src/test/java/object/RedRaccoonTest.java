package object;

import org.junit.Assert;
import org.junit.Test;

public class RedRaccoonTest {

    @Test
    public void createTrap() {
        RedRaccoon redRaccoon = new RedRaccoon();


        Assert.assertEquals("RedRaccoon", redRaccoon.objectName);
        Assert.assertNotNull(redRaccoon.Image);
        Assert.assertFalse(redRaccoon.collidable);
    }
}
