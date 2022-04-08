package block;

import main.RaccoonGame;
import object.GeneralDrawableObject;
import object.GeneralObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class MapManager {
    RaccoonGame raccoonGame;
    public static GeneralDrawableObject[] blocks;
    public int[][] mapBlockArr;

    /**
     * Constructs a new ObjectHandler class and sets its raccoonGame attribute to the parameter passed.
     * Also initializes the ObjectHandler's MapBlock object array "blocks" to an initial size of 11 and
     * 2D integer array "mapBlockArr" to a size of windowCol*windowRow.
     */
    public MapManager(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;

        blocks = new GeneralDrawableObject[11]; //storage for different block images
        mapBlockArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];

    }

    /**
     * Sets the MapManager's blocks array to the necessary resource images located in the /blocks folder.
     * Also sets these MapBlock's collidable boolean to true if the block should have collision.
     */
    public void getBlockImage() {
        try {
            for(int i=0; i<11; i++){
                blocks[i] = new GeneralDrawableObject();
            }
            blocks[0].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wood_floor.png")));
            blocks[1].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_top.png")));
            blocks[1].collidable = true;
            blocks[2].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall.png")));
            blocks[2].collidable = true;
            blocks[3].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/exit.png")));
            blocks[3].collidable = true;
            blocks[4].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_right.png")));
            blocks[4].collidable = true;
            blocks[5].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_single.png")));
            blocks[5].collidable = true;
            blocks[6].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/chair.png")));
            blocks[6].collidable = true;
            blocks[7].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/vending_top.png")));
            blocks[7].collidable = true;
            blocks[8].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/vending_bottom.png")));
            blocks[8].collidable = true;
            blocks[9].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/table_left.png")));
            blocks[9].collidable = true;
            blocks[10].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/table_right.png")));
            blocks[10].collidable = true;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Takes in a Graphics2D object and reads from mapBlockArr to draw the currently saved map to the
     * player's screen.
     */
    public void drawMap(Graphics2D graphics) {

        int currentCol = 0;
        int currentRow = 0;
        int currentX = 0;
        int currentY = 0;

        //draw the map, block by block, row by row. top, left -> bottom, right
        while(currentCol < raccoonGame.windowCol && currentRow < raccoonGame.windowRow) {
            int blockNum = mapBlockArr[currentCol][currentRow];

            if(blockNum > 5) { //is table piece
                //render floor before rendering the table piece so that there is a background
                graphics.drawImage(blocks[0].Image, currentX, currentY, raccoonGame.blockSize, raccoonGame.blockSize, null);
            }

            graphics.drawImage(blocks[blockNum].Image, currentX, currentY, raccoonGame.blockSize, raccoonGame.blockSize, null);
            currentCol++;
            currentX += raccoonGame.blockSize;

            //row rendered, reset col variables and draw the next row
            if(currentCol == raccoonGame.windowCol) {
                currentCol = 0;
                currentX = 0;
                currentRow++;
                currentY += raccoonGame.blockSize;
            }
        }
    }
}
