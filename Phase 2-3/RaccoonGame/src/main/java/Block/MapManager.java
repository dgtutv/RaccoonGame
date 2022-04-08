package Block;

import main.RaccoonGame;
import java.awt.*;

public class MapManager {
    RaccoonGame raccoonGame;
    public int[][] mapBlockArr;
    public BlockList blockList;

    /**
     * Constructs a new ObjectHandler class and sets its raccoonGame attribute to the parameter passed.
     * Also initializes the ObjectHandler's MapBlock object array "blocks" to an initial size of 11 and
     * 2D integer array "mapBlockArr" to a size of windowCol*windowRow.
     */
    public MapManager(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;
        mapBlockArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];
        blockList = new BlockList();
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
                graphics.drawImage(blockList.getImage(0), currentX, currentY, raccoonGame.blockSize, raccoonGame.blockSize, null);
            }

            graphics.drawImage(blockList.getImage(blockNum), currentX, currentY, raccoonGame.blockSize, raccoonGame.blockSize, null);
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
