package object;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class RedRaccoonTest {

    @Test
    public void createTrap() {
        RedRaccoon redRaccoon = new RedRaccoon();


        Assert.assertEquals("RedRaccoon", redRaccoon.objectName);
        Assert.assertNotNull(redRaccoon.objectImage);
        Assert.assertFalse(redRaccoon.collidable);
    }
}
