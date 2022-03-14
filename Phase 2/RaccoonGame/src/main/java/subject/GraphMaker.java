package subject;

import main.RaccoonGame;

public class GraphMaker {
    //Needed variables
    RaccoonGame raccoonGame;
    public int mapBlockArr[][];
    public int mapNodeArr[][];

    //Node class for a linked list-based map
    private class Node{
        public Node up, down, left, right = null;
        public int blockX, blockY;
    }

    //constructor
    GraphMaker(RaccoonGame raccoonGame){
        //Set variables based off constructor input
        this.raccoonGame = raccoonGame;
        this.mapBlockArr = raccoonGame.mapManager.mapBlockArr;

        //Iterator variables
        int currentCol = 0;
        int currentRow = 0;
        int currentX = 0;
        int currentY = 0;

        //Create our linked list based-graph using the mapArray from MapManager.
        //We go from top left to bottom right
        while(currentCol < raccoonGame.windowCol && currentRow < raccoonGame.windowRow) {
            int currBlockNum = mapBlockArr[currentCol][currentRow];
            int leftBlockNum, rightBlockNum, upBlockNum, downBlockNum;
            if(currentCol - 1 < raccoonGame.windowCol && currentRow < raccoonGame.windowRow && currentCol -1 > -1){
                leftBlockNum = mapBlockArr[currentCol-1][currentRow];

            }
            else{

            }
            if(currentCol + 1 < raccoonGame.windowCol && currentRow < raccoonGame.windowRow){
                rightBlockNum;
            }
            else{

            }
            if(currentCol < raccoonGame.windowCol && currentRow - 1 < raccoonGame.windowRow && currentRow - 1 > -1){
                upBlockNum;
            }
            else{

            }
            if(currentCol + 1 < raccoonGame.windowCol && currentRow < raccoonGame.windowRow) {
                downBlockNum;
            }
            else {

            }
            currentCol++;
            currentX += raccoonGame.blockSize;

            //row checked, reset col variables and draw the next row
            if(currentCol == raccoonGame.windowCol) {
                currentCol = 0;
                currentX = 0;
                currentRow++;
                currentY += raccoonGame.blockSize;
            }
        }
    }
    }
}
