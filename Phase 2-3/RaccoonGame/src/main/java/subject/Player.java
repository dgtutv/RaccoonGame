package subject;

import main.KeyHandler;
import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Main player character. Contains timer score, movement handling, animation, and so forth.
 */
public class Player extends Subject{
    //Unique constants to player
    KeyHandler keyH;
    //timer for score decrementing
    public int timer;
    public int score;
    private int collectedRewards;
    public int reward;
    //bool for tracking if player won the game or lost
    public boolean hasEscaped;

    //mapBlockArr for collisions
    public int mapBlockArr[][];

    
    //Constructor
    public Player(RaccoonGame raccoonGame, KeyHandler keyH) {
        super(raccoonGame);

        loadCharacterFrames();
        //default values that can easily be changed
        setX(2* raccoonGame.blockSize);
        setY(15* raccoonGame.blockSize);
        speed = 4;
        score = 50;
        collectedRewards = 0;
        reward = 0;
        direction = "down";
        hasEscaped = false;
        moving = false;

        //collidable area values
        collidableArea = new Rectangle();
        collidableArea.x = 1;
        collidableArea.y = 1;
        collidableArea.width = raccoonGame.blockSize-2;
        collidableArea.height = raccoonGame.blockSize-2;
        collidableAreaX = collidableArea.x;
        collidableAreaY = collidableArea.y;

        //Add the key listener to raccoonGame to handle key movement
        this.keyH = keyH;

        //instantiate mapBlockArr
        this.mapBlockArr = raccoonGame.mapManager.mapBlockArr;
    }

    //Method called every update to check on the player's score
    @Override
    public void customUpdate(){
        //If player should be dead
        timerUpdate(60, 1);
        if(score <= 0){
            GameOver = true;
        }
    }

    //Custom method for updating the movement based off direction
    public void moveUpdate(){
        //If the subject is moving, keep moving until centered in block
        if(moving) {
            switch (direction) {
                case "up":
                    setY(getY() - speed);
                    break;
                case "down":
                    setY(getY() + speed);
                    break;
                case "left":
                    setX(getX() - speed);
                    break;
                case "right":
                    setX(getX() + speed);
                    break;
            }
            //Tracks how many pixels we've moved
            pixelCounter += speed;

            //Once we've moved a whole block, player can be still (!moving) and reset pixelCounter
            if (pixelCounter == raccoonGame.blockSize) {
                moving = false;
                pixelCounter = 0;
            }
        }
    }

    //Method for decreasing player score overtime
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
        //check enemy collision, if true end game
        if(raccoonGame.collisionHandler.checkEnemy(this, true)) {

            GameOver = true;
        }
        //If and only if the player is still, accept key-presses, then set moving to true
        if(!moving){
            //Direction based off key-presses, check if valid input instead of collisions
            if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
                if(keyH.upPressed) {
                    //valid input check
                    if(mapBlockArr[getX()/raccoonGame.blockSize][(getY()/raccoonGame.blockSize)-1] == 0){
                        direction = "up";
                        moving = true;
                    }
                }
                else if(keyH.downPressed) {
                    //valid input check
                    if(mapBlockArr[getX()/raccoonGame.blockSize][(getY()/raccoonGame.blockSize)+1] == 0){
                        direction = "down";
                        moving = true;
                    }
                }
                else if(keyH.rightPressed) {
                    //valid input check
                    if(mapBlockArr[(getX()/raccoonGame.blockSize)+1][getY()/raccoonGame.blockSize] == 0) {
                        direction = "right";
                        moving = true;
                    }
                }
                else if(keyH.leftPressed) {
                    //valid input check
                    if(mapBlockArr[(getX()/raccoonGame.blockSize)-1][getY()/raccoonGame.blockSize] == 0) {
                        direction = "left";
                        moving = true;
                    }
                }
            }
            //check object collision
            int objectIndex = raccoonGame.collisionHandler.checkObject(this, true);
            collectObject(objectIndex);
        }
    }

    //Method for handling objects collected by the player
    public boolean collectObject(int index) {
        //check if the index is valid
        if(index != 999) {
            String objectName = raccoonGame.objects[index].objectName;

            switch(objectName) {
                case "Garbage":
                    //play power-up sound
                    raccoonGame.sound.effect(1, raccoonGame.sound);
                    //increment player score and remove the item
                    this.changeScore(10);
                    collectedRewards++;
                    raccoonGame.objects[index] = null;

                    //check if all rewards collected
                    if(collectedRewards >= raccoonGame.objectHandler.numRewards) {
                        //load secondary map with door
                        raccoonGame.mapLoader.loadMap(raccoonGame.mapManager.mapBlockArr, "/map/raccoonGameMapEnd.txt");
                        //play door sound
                        raccoonGame.sound.effect(5, raccoonGame.sound);
                    }
                    break;
                case "Trap":
                    //play trap sound
                    raccoonGame.sound.effect(2, raccoonGame.sound);

                    //decrement player score and remove the item
                    if(this.score <= 20) {
                        this.score = 0;
                    }
                    else {
                        changeScore(-20);
                    }
                    this.raccoonGame.objects[index] = null;
                    break;
                case "ExitDoor":
                    //end the game if all rewards collected
                    if(collectedRewards >= raccoonGame.objectHandler.numRewards) {
                        //set hasEscaped (won) to true
                        hasEscaped = true;
                        //play winning sound
                        raccoonGame.sound.stop(raccoonGame.sound);
                        raccoonGame.sound.effect(6, raccoonGame.sound);
                        GameOver = true;
                    }
                    break;
                case "RedRaccoon":
                    //play swoon sound
                    raccoonGame.sound.effect(4, raccoonGame.sound);
                    //add 100 bonus points to rewards
                    rewardUpdate(100);
                    this.raccoonGame.objects[index] = null;
                    break;
            }
            return true;
        }
        return false;
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
        BufferedImage frame = still;
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

    //Store reward to be added to the score at the end of the game
    public void rewardUpdate(int increase){
        this.reward += increase;
    }
}
