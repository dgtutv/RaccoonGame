package CollectableObject;

import object.GeneralCollectableObject;
import object.GeneralObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * A cardboard box with a stick underneath it.
 */
public class Trap extends GeneralCollectableObject {
    public Trap() {
        objectName = "Trap";
        try {
            Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/trap.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        collidable = false;
    }

}
