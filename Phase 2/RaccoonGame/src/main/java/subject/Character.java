package subject;

import main.KeyHandler;
import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Character extends Subject{
    //Unique constants to character
    KeyHandler keyH;
    int health;
    int reward = 0;
    
    //Constructor
    public Character(RaccoonGame raccoonGame, KeyHandler keyH) {
        super(raccoonGame);
        //default values that can easily be changed
        x = 100;
        y = 100;
        speed = 4;
        health = 100;
        direction = "down";
        //Add the key listener to raccoonGame to handle key movement
        this.keyH = keyH;
    }

    //Method called every update to check on the character's health
    @Override
    public void customUpdate(){
        //If character should be dead
        if(health<=0){
            GameOver = true;
        }
        //Store extra health from rewards in an int
        if(health>100){
               reward+= health-100;
        }
    }

    //Direction updater class
    @Override
    public void directionUpdate(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            atRest = false;
            if(keyH.upPressed) {
                direction = "up";
            }
            else if(keyH.downPressed) {
                direction = "down";
            }
            else if(keyH.rightPressed) {
                direction = "right";
            }
            else{
                direction = "left";
            }
        }
        else{
            atRest = true;
        }
    }
    
    //Load player frames class
    public void loadCharacterFrames(){
        try{
            moving1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/raccoon_move_1.png")));
            moving2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/raccoon_move_2.png")));
            still = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/raccoon.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //Movement drawing
    public void draw(Graphics2D g){
        loadCharacterFrames();
        BufferedImage frame = still;
        if(!atRest) {
            if (spriteNum == 1) {
                frame = moving1;
            }
            else if(spriteNum == 2){
                frame = moving2;
            }
        }
        g.drawImage(frame, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);       //Image Observer
    }
    public void changeHealth(int health){
        this.health += health;
    }
}
