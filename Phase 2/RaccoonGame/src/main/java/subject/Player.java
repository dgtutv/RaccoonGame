package subject;

import main.KeyHandler;
import main.ObjectHandler;
import main.RaccoonGame;
import object.GeneralObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Player extends Subject{
    //Unique constants to player
    KeyHandler keyH;
    //timer for score decrementing
    public int timer;
    public int score;
    private int collectedRewards;
    int reward;

    
    //Constructor
    public Player(RaccoonGame raccoonGame, KeyHandler keyH) {
        super(raccoonGame);
        //default values that can easily be changed
        x = 100;
        y = 100;
        speed = 4;
        score = 30;
        collectedRewards = 0;
        reward = 0;
        direction = "down";

        //collidable area values
        collidableArea = new Rectangle();
        collidableArea.x = raccoonGame.blockSize / 4;
        collidableArea.y = raccoonGame.blockSize / 4;
        collidableArea.width = raccoonGame.blockSize - (raccoonGame.blockSize / 4);
        collidableArea.height = raccoonGame.blockSize - (raccoonGame.blockSize / 4);
        collidableAreaX = collidableArea.x;
        collidableAreaY = collidableArea.y;

        //Add the key listener to raccoonGame to handle key movement
        this.keyH = keyH;
    }

    //Method called every update to check on the player's score
    @Override
    public void customUpdate(){
        //If player should be dead
        timerUpdate(60, 1);
        if(score <=0){
            GameOver = true;
        }
        //Store extra score from rewards in an int
        if(score >100){
               reward+= score -100;
        }
    }

    public void timerUpdate(int ticks, int seconds) {
        if(timer >= ticks*seconds) {
            score -= 1;
            timer = 0;
        }
        //increment timer
        timer++;
    }

    //Direction updater class
    @Override
    public void directionUpdate(){
        //check is collision is on
        collisionOn = false;
        raccoonGame.collisionHandler.checkBlock(this);
        //check object collision
        int objectIndex = raccoonGame.collisionHandler.checkObject(this, true);
        collectObject(objectIndex);


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

    public void collectObject(int index) {
        //check if the index is valid
        if(index != 999) {
            String objectName = raccoonGame.objects[index].objectName;

            switch(objectName) {
                case "Garbage":
                    //increment player score and remove the item
                    this.changeScore(10);
                    collectedRewards++;
                    raccoonGame.objects[index] = null;
                    break;
                case "Trap":
                    //decrement player score and remove the item
                    changeScore(-20);
                    this.raccoonGame.objects[index] = null;
                    break;
                case "ExitDoor":
                    //end the game if all rewards collected
                    if(collectedRewards >= raccoonGame.objectHandler.numRewards) {
                        GameOver = true;
                    }

                    break;

            }

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
