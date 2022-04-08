package object;

import main.RaccoonGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GeneralDrawableObject extends GeneralObject {
    public BufferedImage Image;
    public void drawObject(Graphics2D graphics, RaccoonGame raccoonGame) {
        graphics.drawImage(Image, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);
    }
}