package subject;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

//A class for dynamic enemy types
public class Enemy extends Subject{
    //Unique constants to all enemies
    Player player;
    int range;

    //Constructor for Enemy
    public Enemy(RaccoonGame raccoonGame, int x, int y, Player player) {
        //Default values
        super(raccoonGame);
        this.x = x;
        this.y = y;
        this.player = player;
        speed = player.speed/2;
        direction = "down";
        atRest = false;
        range = 10;

        //Collidable area values
        collidableArea = new Rectangle();
        collidableArea.x = raccoonGame.blockSize/4;
        collidableArea.y = raccoonGame.blockSize/4;
        collidableArea.width = raccoonGame.blockSize - (raccoonGame.blockSize/4);
        collidableArea.height = raccoonGame.blockSize - (raccoonGame.blockSize/4);
        collidableAreaX = collidableArea.x;
        collidableAreaY = collidableArea.y;
    }

    @Override
    //update method for enemy direction
    public void directionUpdate() {
        //direction should mimic player
        direction = player.direction;
    }

    @Override
    //enemy specific update method
    public void customUpdate() {
        //check if collision is on
        collisionOn = false;
        raccoonGame.collisionHandler.checkBlock(this);

        //if the enemy is within the player's hitbox, then kill the player
//        if(playerleft <= x && x <= playerRight && playerBottom <= y && y <= playerTop) {
//            player.changeScore(-player.score);
//        }
    }

    @Override
    //update method for enemy movement
    public void moveUpdate() {
        //follow the player
        if(rangeCheck()) {
            //get rid of diagonal movement using x?>y
            if (x < player.x) {
                if (player.x - x < speed) {
                    x += player.x - x;
                } else {
                    x += speed;
                }
            }
            if (y < player.y) {
                if (player.y - y < speed) {
                    y += player.y - y;
                } else {
                    y += speed;
                }
            }
            if (x > player.x) {
                if (x - player.x < speed) {
                    x -= x - player.x;
                } else {
                    x -= speed;
                }
            }
            if (y > player.y) {
                if (y - player.y < speed) {
                    y -= y - player.y;
                } else {
                    y -= speed;
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
