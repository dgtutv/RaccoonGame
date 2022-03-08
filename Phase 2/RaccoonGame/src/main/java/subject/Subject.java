package subject;

import main.RaccoonGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Subject {
    //Game Awareness
    RaccoonGame raccoonGame;
    public boolean GameOver = false;

    //Speed and spacial awareness
    public int x, y;
    public int speed;
    public boolean atRest;      //track if the player is moving or not

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
        atRest = true;
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

        //if subject is trying to move
        if(!atRest){
            //if player collision is off
            if(!collisionOn) {
                moveUpdate();
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
}
