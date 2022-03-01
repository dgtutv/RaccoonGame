package subject;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Character extends Subject{
    //Constructor
    public Character(RaccoonGame raccoonGame) {
        super(raccoonGame);
        //default values that can easily be changed
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        atRest = true;
    }

    //Instantiate a KeyHandler, then handle movement itself and drawing with
    KeyHandler keyH = new KeyHandler();

    //Direction updater class
    public void direction(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else {
                direction = "left";
            }
        }
    }
    
    //Load player frames class
    public void loadPlayerFrames(){
        try{
            moving1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player-temp/up1.png")));
            moving2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player-temp/up2.png")));
            still = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player-temp/down1.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //Movement drawing
    public void draw(Graphics2D g){
        BufferedImage frame;
        loadPlayerFrames();
        if(!atRest) {
            if (spriteNum == 1) {
                frame = moving1;
            }
            else{
                frame = moving2;
            }
        }
        else {
            frame = still;
        }
        g.drawImage(frame, x, y, raccoonGame.blockSize, raccoonGame.blockSize, null);       //Image Observer
    }
}
