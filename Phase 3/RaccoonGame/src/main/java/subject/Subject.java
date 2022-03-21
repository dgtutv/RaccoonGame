package subject;

import main.RaccoonGame;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * General class for characters. Has movement stuff and sprite info.
 */
public abstract class Subject {
    //Game Awareness
    RaccoonGame raccoonGame;
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
