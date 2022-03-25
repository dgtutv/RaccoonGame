package object;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TrapTest {

    @Test
    public void createTrap() {
        Trap trap = new Trap();


        Assert.assertEquals("Trap", trap.objectName);
        Assert.assertNotNull(trap.objectImage);
        Assert.assertFalse(trap.collidable);
    }
}
