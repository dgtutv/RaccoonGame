package subject;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Math.*;

//A class for dynamic enemy types
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
    List<GraphMaker.Node> path;
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
        this.x = x;
        this.y = y;
        this.player = player;
        patrolTopBound = y;
        patrolBottomBound = y - patrolVerticalDistance * raccoonGame.blockSize/2;
        patrolLeftBound = x;
        patrolRightBound = x + patrolHorizontalDistance * raccoonGame.blockSize/2;

        //Default values for enemy
        speed = player.speed/2;
        direction = "down";
        collidable = true;
        atRest = false;

        //Collidable area values
        collidableArea = new Rectangle();
        collidableArea.x = raccoonGame.blockSize/4;
        collidableArea.y = raccoonGame.blockSize/4;
        collidableArea.width = raccoonGame.blockSize - (raccoonGame.blockSize/4);
        collidableArea.height = raccoonGame.blockSize - (raccoonGame.blockSize/4);
        collidableAreaX = collidableArea.x;
        collidableAreaY = collidableArea.y;
        tree = new TreeMaker(player, this);
    }

    @Override
    //update method for enemy direction (i.e, pathing)
    public void directionUpdate() {
        //follow the player when within range
        if(rangeCheck()) {
            path = tree.update();
            //get direction based on the node we want to follow
            direction = path.get(1).direction;

            try{
                targetX = path.get(1).x * raccoonGame.blockSize;
                targetY = path.get(1).y * raccoonGame.blockSize;
            }
            catch (Exception e){
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
    }

    @Override
    //enemy specific update method
    public void customUpdate() {
        //check if collision is on
        collisionOn = false;
        raccoonGame.collisionHandler.checkBlock(this, false);
    }

    @Override
    //update method for enemy movement
    public void moveUpdate() {
        //check if collision is on
        if(!this.collisionOn) {
            //Move towards the target
            //get rid of diagonal movement using x?>y
            if (x < targetX) {
                if (targetX - x < speed) {
                    x += targetX - x;
                } else {
                    x += speed;
                }
            }
            else if (y < targetY) {
                if (targetY - y < speed) {
                    y += targetY - y;
                } else {
                    y += speed;
                }
            }
            else if (x > targetX) {
                if (x - targetX < speed) {
                    x -= x - targetX;
                } else {
                    x -= speed;
                }
            }
            else if (y > targetY) {
                if (y - targetY < speed) {
                    y -= y - targetY;
                } else {
                    y -= speed;
                }
            }
        }
        else {
            if(Objects.equals(direction, "up") || Objects.equals(direction, "down")){
                if (x < targetX) {
                    if (targetX - x < speed) {
                        x += targetX - x;
                    } else {
                        x += speed;
                    }
                }
                else if (x > targetX) {
                    if (x - targetX < speed) {
                        x -= x - targetX;
                    } else {
                        x -= speed;
                    }
                }
            }
            else{
                if (y < targetY) {
                    if (targetY - y < speed) {
                        y += targetY - y;
                    } else {
                        y += speed;
                    }
                }
                else if (y > targetY) {
                    if (y - targetY < speed) {
                        y -= y - targetY;
                    } else {
                        y -= speed;
                    }
                }
            }
            collisionOn = false;
            raccoonGame.collisionHandler.checkBlock(this, false);
            if(collisionOn){
                switch(direction) {
                    case "up" -> y += speed;
                    case "down" -> y -= speed;
                    case "left" -> x += speed;
                    case "right" -> x -= speed;
                }
            }
        }
    }

    //check if the enemy is within an appropriate range to chase the player
    private boolean rangeCheck(){
        //use pythagorean theorem to get the distance of a line to the player, then compare to range * blocksize
        //this in turn calculates if a player is within a range given in blocks to the enemy
        return (pow((player.x - x), 2) + pow((player.y - y), 2) <= pow(range * raccoonGame.blockSize, 2));

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
        loadEnemyFrames();
        BufferedImage frame = moving1;
        if (spriteNum == 1) {
            frame = moving1;
        }
        else if(spriteNum == 2){
            frame = moving2;
        }
        g.drawImage(frame, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);       //Image Observer
    }
}
