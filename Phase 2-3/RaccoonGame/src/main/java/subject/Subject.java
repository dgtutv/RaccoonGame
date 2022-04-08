package subject;

import main.RaccoonGame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * General class for characters. Has movement stuff and sprite info.
 */
public abstract class Subject {
    //Game Awareness
    public RaccoonGame raccoonGame;
    public boolean GameOver = false;

    //Speed and spacial awareness
    public int x, y;
    public int speed;
    boolean moving = false;      //track if the player is moving or not
    int pixelCounter = 0;       //track the amount of pixels a subject has travelled between blocks

    //Sprite awareness
    public BufferedImage moving1,moving2 ,still;
    public String direction;

    //Animation variables
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int animSpeed = 12;  //speed of frame switching



    //Collidable area
    public Rectangle collidableArea;
    public int collidableAreaX, collidableAreaY;
    public boolean collisionOn = false;

    //Constructor
    public Subject(RaccoonGame raccoonGame){
        this.raccoonGame = raccoonGame;
        moving = false;
    }


    /**
     * Sets subject's x coordinate to the value passed in to the integer parameter x. returns nothing.
     * @param x: the value which calling subject's x coordinate will be set too.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets subject's y coordinate to the value passed in to the integer parameter y. returns nothing.
     * @param y: the value which calling subject's y coordinate will be set too.
     */
    public void setY(int y) {
        this.y = y;
    }


    /**
     * Gets the calling subject's x coordinate value from it's integer x attribute.
     * @return integer value corresponding to the subject's x coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the calling subject's u coordinate value from it's integer u attribute.
     * @return integer value corresponding to the subject's u coordinate.
     */
    public int getY() {
        return this.y;
    }


    //direction updater needs to be implemented different for characters and enemies
    public abstract void directionUpdate();

    //abstract customUpdate for subject specific attributes
    public abstract void customUpdate();

    //abstract moveUpdate for subject specific movement
    public abstract void moveUpdate();

    //Subject Movement
    public void update(){
        customUpdate();
        if(GameOver) return;
        directionUpdate();

        moveUpdate();
        //if subject is trying to move
        if(moving){
            //flip image/animate
            spriteCounter++;
            if(spriteCounter > animSpeed) {
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }


    }
}
