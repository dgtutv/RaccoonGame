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
    int score = 0;
    int reward = 0;
    
    //Constructor
    public Character(RaccoonGame raccoonGame, KeyHandler keyH) {
        super(raccoonGame);
        //default values that can easily be changed
        x = 100;
        y = 100;
        speed = 4;
        score = 100;
        direction = "down";

        //collidable area values
        collidableArea = new Rectangle();
        collidableArea.x = raccoonGame.blockSize / 4;
        collidableArea.y = raccoonGame.blockSize / 4;
        collidableArea.width = raccoonGame.blockSize - (raccoonGame.blockSize / 4);
        collidableArea.height = raccoonGame.blockSize - (raccoonGame.blockSize / 4);

        //Add the key listener to raccoonGame to handle key movement
        this.keyH = keyH;
    }

    //Method called every update to check on the character's score
    @Override
    public void customUpdate(){
        //If character should be dead
        if(score <=0){
            GameOver = true;
        }
        //Store extra score from rewards in an int
        if(score >100){
               reward+= score -100;
        }
    }

    //Direction updater class
    @Override
    public void directionUpdate(){
        //check is collision is on
        collisionOn = false;
        raccoonGame.collisionHandler.checkBlock(this);


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
            else {
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
        //if(!atRest) {
        if(direction == "up" || direction == "down" || direction == "left" || direction == "right") {
            if (spriteNum == 1) {
                frame = moving1;
            }
            else if(spriteNum == 2){
                frame = moving2;
            }
        }
        g.drawImage(frame, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);       //Image Observer
    }

    //call to change score
    public void changeScore(int score){
        this.score += score;
    }
}
