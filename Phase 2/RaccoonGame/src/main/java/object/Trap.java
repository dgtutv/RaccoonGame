package object;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class Trap extends GeneralObject {
    public Trap() {
        objectName = "Trap";
        try {
            objectImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/trap.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        collidable = false;
    }

}
