package subject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Subject {

    //Speed and spacial awareness
    public int x, y;
    public int speed;

    //Sprite awareness
    public BufferedImage moving1,moving2 ,still;
    public String direction;

    //Animation variables
    public int spriteCounter = 0;
    public int spriteNum = 1;

    //Movement drawing
    public void draw(Graphics2D g){
        BufferedImage frame = null;
        switch(direction){
            case "moving":
                if(spriteNum == 1){
                    frame = moving1;
                }
                else{
                    frame = moving2;
                }
                break;
            case "still":
                frame = still;
        }
    }

}
