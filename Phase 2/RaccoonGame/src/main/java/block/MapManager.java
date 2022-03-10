package block;

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
    public MapBlock[] blocks;
    public int[][] mapBlockArr;

    public MapManager(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;

        blocks = new MapBlock[10]; //storage for different block images
        mapBlockArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];

        getBlockImage();
        loadMap();
    }

    //this method gets the different block images from file
    public void getBlockImage() {
        try {
            for(int i=0; i<7; i++){
                blocks[i] = new MapBlock();
            }
            blocks[0].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wood_floor.png")));
            blocks[1].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_top.png")));
            blocks[1].collidable = true;
            blocks[2].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_mid.png")));
            blocks[2].collidable = true;
            blocks[3].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_left.png")));
            blocks[3].collidable = true;
            blocks[4].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_right.png")));
            blocks[4].collidable = true;
            blocks[5].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_single.png")));
            blocks[5].collidable = true;
            blocks[6].blockImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_top_end.png")));
            blocks[6].collidable = true;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //this method loads the map from a text file into a 2D array mapBlockArr[][]
    public void loadMap() {
        try {
            //set up file reader
            InputStream inputStream = getClass().getResourceAsStream("/map/raccoonGameMap.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int currentCol = 0;
            int currentRow = 0;

            //read the map text file. top, left -> bottom, right
            while(currentCol < raccoonGame.windowCol && currentRow < raccoonGame.windowRow) {
                //read one line at a time (should be map width per line) and store numbers in string array
                String line = bufferedReader.readLine();
                String numbers[] = line.split(" ");

                //add each block in the current row to mapBlockArr as an int
                while(currentCol < raccoonGame.windowCol) {
                    int num = Integer.parseInt(numbers[currentCol]);
                    mapBlockArr[currentCol][currentRow] = num;
                    currentCol++;
                }
                //increment row (reset column tracker)
                if(currentCol == raccoonGame.windowCol) {
                    currentCol = 0;
                    currentRow++;
                }
            }

            //close the file reader
            bufferedReader.close();
        }
        catch(Exception e) {
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
