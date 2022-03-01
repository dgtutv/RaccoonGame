package subject;

import main.RaccoonGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Subject {
    //Game Awareness
    RaccoonGame raccoonGame;

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

    //Constructor
    public Subject(RaccoonGame raccoonGame){
        this.raccoonGame = raccoonGame;
        atRest = true;
    }

    //direction updater needs to be implemented different for characters and enemies
    public abstract void direction();

    //Subject Movement
    public void update(){
        this.direction();
        if(!atRest){
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
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
