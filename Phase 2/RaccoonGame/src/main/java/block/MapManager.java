package block;

import main.mapLoader;
import main.RaccoonGame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class MapManager {
    RaccoonGame raccoonGame;
    mapLoader loadMap;
    public MapBlock[] blocks;
    public int[][] mapBlockArr;

    public MapManager(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;

        blocks = new MapBlock[11]; //storage for different block images
        mapBlockArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];

        getBlockImage();
        raccoonGame.mapLoader.loadMap(mapBlockArr, "/map/raccoonGameMap.txt");
    }

    //this method gets the different block images from file
    public void getBlockImage() {
        try {
            for(int i=0; i<11; i++){
                blocks[i] = new MapBlock();
            }
            blocks[0].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wood_floor.png")));
            blocks[1].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_top.png")));
            blocks[1].collidable = true;
            blocks[2].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall.png")));
            blocks[2].collidable = true;
            blocks[3].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/exit.png")));
            blocks[3].collidable = true;
            blocks[4].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_right.png")));
            blocks[4].collidable = true;
            blocks[5].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_single.png")));
            blocks[5].collidable = true;
            blocks[6].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/chair.png")));
            blocks[6].collidable = true;
            blocks[7].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/vending_top.png")));
            blocks[7].collidable = true;
            blocks[8].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/vending_bottom.png")));
            blocks[8].collidable = true;
            blocks[9].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/table_left.png")));
            blocks[9].collidable = true;
            blocks[10].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/table_right.png")));
            blocks[10].collidable = true;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


    //this method must be called from the draw function of the main game loop
    //it draws the map from the 2D array mapBlockArr[][]
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
                graphics.drawImage(blocks[0].blockImage, currentX, currentY, raccoonGame.blockSize, raccoonGame.blockSize, null);
            }

            graphics.drawImage(blocks[blockNum].blockImage, currentX, currentY, raccoonGame.blockSize, raccoonGame.blockSize, null);
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
