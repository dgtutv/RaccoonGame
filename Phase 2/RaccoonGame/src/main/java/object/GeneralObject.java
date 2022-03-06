package object;

import main.RaccoonGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GeneralObject {

    public BufferedImage objectImage;
    public String objectName;
    public boolean collidable = false;
    public int x, y;

    public void drawObject(Graphics2D graphics, RaccoonGame raccoonGame) {
        graphics.drawImage(objectImage, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);
    }

}
