package object;

import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class RedRaccoon extends GeneralObject {

    private BufferedImage objectImage2;
    private int timer;
    private Random randomNumGenerator;
    private int row, col, spawnBlock;

    public RedRaccoon() {
        objectName = "RedRaccoon";
        try {
            objectImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/raccoon_red_dress.png")));
            objectImage2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/items/raccoon_red_dress_flash.png")));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        collidable = true;
        timer = 0;
        randomNumGenerator = new Random();
    }

    public void drawObject(Graphics2D graphics, RaccoonGame raccoonGame) {
        //increment timer
        timer++;
        //after raccoon has been invisible for 10 seconds, spawn at a random location
        if(timer == 60*10) {
            do {
                row = randomNumGenerator.nextInt(raccoonGame.windowCol);
                col = randomNumGenerator.nextInt(raccoonGame.windowRow);
                spawnBlock = raccoonGame.mapManager.mapBlockArr[row][col];
                this.x = row*raccoonGame.blockSize;
                this.y = col*raccoonGame.blockSize;
            }
            while(raccoonGame.mapManager.blocks[spawnBlock].collidable);
        }
        if(timer > 60*10) {
            //flip between drawing regular and "flash" red raccoon every 5/6 ths of a second
            if(timer%100 > 0 && timer%100 < 50) {
                graphics.drawImage(objectImage, this.x, this.y, raccoonGame.blockSize, raccoonGame.blockSize, null);
            }
            else {
                graphics.drawImage(objectImage2, this.x, this.y, raccoonGame.blockSize, raccoonGame.blockSize, null);
            }

        }
        //after racoon has been visible for 5 seconds, remove
        if(timer > 60*10) {
            timer = 0;
        }
    }


}
