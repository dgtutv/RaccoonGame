package object;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GarbageTest {

    @Test
    public void createGarbage() {
        Garbage garbage = new Garbage();


        Assert.assertEquals("Garbage", garbage.objectName);
        Assert.assertNotNull(garbage.objectImage);
        Assert.assertFalse(garbage.collidable);
    }
}
