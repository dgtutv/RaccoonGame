package object;

import main.RaccoonGame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * General type for drawable objects, inherits from GeneralObject, mainly used for game blocks
 */
public class GeneralDrawableObject extends GeneralObject {
    public BufferedImage Image;
    public void drawObject(Graphics2D graphics, RaccoonGame raccoonGame) {
        graphics.drawImage(Image, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);
    }
}