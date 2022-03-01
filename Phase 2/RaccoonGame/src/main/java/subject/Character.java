package subject;

import main.KeyHandler;
import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class Character extends Subject{
    //Need a KeyHandler to handle movement of main character
    KeyHandler keyH;
    
    //Constructor
    public Character(RaccoonGame raccoonGame, KeyHandler keyH) {
        super(raccoonGame);
        //default values that can easily be changed
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
        //Add the key listener to raccoonGame to handle key movement
        this.keyH = keyH;
    }

    //Direction updater class
    public void direction(){
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
            moving1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playerTemp/up1.png")));
            moving2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playerTemp/up2.png")));
            still = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/playerTemp/down1.png")));
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
}
