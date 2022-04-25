package object;

import java.awt.*;

/**
 * Abstract parent for all collectable object types
 */
public abstract class GeneralCollectableObject extends GeneralDrawableObject{
    public Rectangle collidableArea = new Rectangle(0, 0, 32, 32);
    public int collidableAreaX = 0;
    public int collidableAreaY = 0;
    public String objectName;

}
