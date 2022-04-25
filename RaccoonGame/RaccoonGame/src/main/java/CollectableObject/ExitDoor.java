package CollectableObject;

import object.GeneralCollectableObject;
import object.GeneralObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ExitDoor extends GeneralCollectableObject {

    public ExitDoor() {
        objectName = "ExitDoor";
        try {
            Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/empty.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collidable = true;
    }

}
