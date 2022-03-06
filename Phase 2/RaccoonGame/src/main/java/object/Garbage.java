package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Garbage extends GeneralObject {

    public Garbage() {
        objectName = "Garbage";
        try {
            objectImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/garbage.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collidable = true;
    }
}
