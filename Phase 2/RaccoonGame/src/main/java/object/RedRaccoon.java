package object;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class RedRaccoon extends GeneralObject {

    public boolean visible;

    public RedRaccoon() {
        objectName = "RedRaccoon";
        try {
            objectImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/raccoon_red_dress.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collidable = false;
        visible = false;
    }

    public void drawObject(Graphics2D graphics, RaccoonGame raccoonGame) {
        if(visible) {
            graphics.drawImage(objectImage, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);
        }
    }


}
