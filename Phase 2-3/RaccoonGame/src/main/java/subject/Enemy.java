package subject;

import main.RaccoonGame;
import object.Node;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.*;

/**
 * A class for dynamic enemy types. Contains pathing information.
 */
public class Enemy extends Subject{
    //Unique constants to all enemies
    Player player;
    int patrolTopBound;
    int patrolBottomBound;
    int patrolLeftBound;
    int patrolRightBound;
    int targetX;
    int targetY;
    boolean topLeft;
    boolean topRight;
    boolean bottomLeft;
    boolean bottomRight;
    List<Node> path;
    TreeMaker tree;

    public boolean collidable;

    //variables to change for enemy behaviour, in blocks
    int range = 7;
    int patrolHorizontalDistance = 2;
    int patrolVerticalDistance = 2;


    //Constructor for Enemy
    public Enemy(RaccoonGame raccoonGame, int x, int y, Player player) {
        //Default values from constructor parameters
        super(raccoonGame);
        loadEnemyFrames();
        this.setX(x);
        this.setY(y);
        this.player = player;
        patrolTopBound = y;
        patrolBottomBound = y - patrolVerticalDistance * raccoonGame.blockSize;
        patrolLeftBound = x;
        patrolRightBound = x + patrolHorizontalDistance * raccoonGame.blockSize;

        //Default values for enemy
        speed = player.speed/2;
        direction = "down";
        collidable = true;

        //collidable area values
        collidableArea = new Rectangle();
        collidableArea.x = 1;
        collidableArea.y = 1;
        collidableArea.width = raccoonGame.blockSize-2;
        collidableArea.height = raccoonGame.blockSize-2;
        collidableAreaX = collidableArea.x;
        collidableAreaY = collidableArea.y;

        //Tree for enemy pathing
        tree = new TreeMaker(player, this);
    }

    @Override
    //update method for enemy direction (i.e, pathing)
    public void directionUpdate() {
        //follow the player when within range
        if(!moving){
            if(rangeCheck()) {
                path = tree.update();
                //get direction based on the node we want to follow
                try{
                    direction = path.get(1).direction;
                    targetX = path.get(1).x * raccoonGame.blockSize;
                    targetY = path.get(1).y * raccoonGame.blockSize;
                }
                catch (Exception e){
                    direction = path.get(0).direction;
                    targetX = path.get(0).x * raccoonGame.blockSize;
                    targetY = path.get(0).y * raccoonGame.blockSize;
                }

            }
            //patrol an area when not in sight of player, randomly
            else{
                if(x == patrolLeftBound && y == patrolTopBound){
                    topLeft = false;
                    bottomLeft = true;
                }
                else if(x == patrolLeftBound && y == patrolBottomBound){
                    bottomLeft = false;
                    bottomRight = true;
                }
                else if(x == patrolRightBound && y == patrolBottomBound){
                    bottomRight = false;
                    topRight = true;
                }
                else if(x == patrolRightBound && y == patrolTopBound){
                    topRight = false;
                    topLeft = true;
                }
                if(bottomLeft){
                    targetX = patrolLeftBound;
                    targetY = patrolBottomBound;
                }
                if(bottomRight){
                    targetX = patrolRightBound;
                    targetY = patrolBottomBound;
                }
                if(topRight){
                    targetX = patrolRightBound;
                    targetY = patrolTopBound;
                }
                if(topLeft){
                    targetX = patrolLeftBound;
                    targetY = patrolTopBound;
                }
            }
            moving = true;
        }
    }

    @Override
    //enemy specific update method
    public void customUpdate() {}

    @Override
    //update method for enemy movement
    public void moveUpdate() {
        //check if collision is on
        if(moving) {
            //Move towards the target
            if (getX() < targetX) {
                if (targetX - getX() < speed) {
                    setX(targetX);
                } else {
                    setX(getX() + speed);
                }
            }
            else if (getY() < targetY) {
                if (targetY - getY() < speed) {
                    setY(targetY);
                } else {
                    setY(getY() + speed);
                }
            }
            else if (getX() > targetX) {
                if (getX() - targetX < speed) {
                    setX(-targetX);
                } else {
                    setX(getX() - speed);
                }
            }
            else if (getY() > targetY) {
                if (getY() - targetY < speed) {
                    setY(-targetY);
                } else {
                    setY(getY() - speed);
                }
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

    //check if the enemy is within an appropriate range to chase the player
    private boolean rangeCheck(){
        //use pythagorean theorem to get the distance of a line to the player, then compare to range * blocksize
        //this in turn calculates if a player is within a range given in blocks to the enemy
        return (pow((player.getX() - getX()), 2) + pow((player.getY() - getY()), 2) <=
                pow(range * raccoonGame.blockSize, 2));

    }


    //method for loading in the enemy's sprites
    public void loadEnemyFrames(){
        try{
            moving1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/enemy.png")));
            moving2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characters/enemy_move.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    //method for movement drawing/animation
    public void draw(Graphics2D g){
        //loadEnemyFrames();
        BufferedImage frame = moving1;
        if (spriteNum == 1) {
            frame = moving1;
        }
        else if(spriteNum == 2){
            frame = moving2;
        }
        g.drawImage(frame, getX(), getY(), raccoonGame.blockSize, raccoonGame.blockSize, null);       //Image Observer
    }
}
