package object;

import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ExitDoorTest {

    @Test
    public void createExitDoor() {
        ExitDoor exitDoor = new ExitDoor();


        Assert.assertEquals("ExitDoor", exitDoor.objectName);
        Assert.assertNotNull(exitDoor.objectImage);
        Assert.assertTrue(exitDoor.collidable);
    }
}
