package CollectableObject;

import object.GeneralCollectableObject;
import object.GeneralObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

/**
 * Our main collectible: garbage.
 */
public class Garbage extends GeneralCollectableObject {

    public Garbage() {
        objectName = "Garbage";
        try {
            Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/garbage.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collidable = false;
    }
}
