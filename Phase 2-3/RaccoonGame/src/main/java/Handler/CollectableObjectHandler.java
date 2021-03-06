package Handler;

import Factory.CollectableObjectFactory;
import main.RaccoonGame;

/**
 * Handler used to spawn items.
 */
public class CollectableObjectHandler {

    RaccoonGame raccoonGame;

    public int numDoors = 0;
    public int numRaccoon = 0;
    public int numRewards = 0;
    public int numTraps = 0;
    public int[][] mapItemArr;
    public int itemCount = 0;

    /**
     * Constructs a new ObjectHandler class and sets its raccoonGame attribute to the parameter passed.
     */
    public CollectableObjectHandler(RaccoonGame raccoonGame) {
        mapItemArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];
        this.raccoonGame = raccoonGame;
    }

    /**
     * Reads ObjectHandler's 2D integer array mapItemArr and places objects in their correct places on
     * the game map. It also increments ObjectHandler's numDoors, numRaccoon, numRewards, and numTraps
     * integers which represent the total number of respective items of those types that are on the map.
     */
    public void spawnItems(){
        int currentCol = 0;
        int currentRow = 0;
        int currentX = 0;
        int currentY = 0;
        int ind = 0;

        //iterate through our array, spawn corresponding item based on number, nothing if 0
        while(currentCol < raccoonGame.windowCol && currentRow < raccoonGame.windowRow) {
            int blockNum = mapItemArr[currentCol][currentRow];

            switch(blockNum) {
                case 1:
                    raccoonGame.objects[ind] = CollectableObjectFactory.createTrap();
                    numTraps++;
                    raccoonGame.objects[ind].x = currentX;
                    raccoonGame.objects[ind].y = currentY;
                    ind++;
                    break;
                case 2:
                    raccoonGame.objects[ind] = CollectableObjectFactory.createGarbage();
                    numRewards++;
                    raccoonGame.objects[ind].x = currentX;
                    raccoonGame.objects[ind].y = currentY;
                    ind++;
                    break;
                case 3:
                    raccoonGame.objects[ind] = CollectableObjectFactory.createExitDoor();
                    numDoors++;
                    raccoonGame.objects[ind].x = currentX;
                    raccoonGame.objects[ind].y = currentY;
                    ind++;
                    break;
                case 4:
                    raccoonGame.objects[ind] = CollectableObjectFactory.createRedRaccoon();
                    numRaccoon++;
                    raccoonGame.objects[ind].x = 0;
                    raccoonGame.objects[ind].y = 0;
                    raccoonGame.objects[ind].collidableArea.width = 0;
                    raccoonGame.objects[ind].collidableArea.height = 0;
                    ind++;
                    break;
            }


            currentCol++;
            currentX += raccoonGame.blockSize;

            //row exhausted, reset col variables and check the next row
            if(currentCol == raccoonGame.windowCol) {
                currentCol = 0;
                currentX = 0;
                currentRow++;
                currentY += raccoonGame.blockSize;
            }
        }
       itemCount = ind;
    }
}
