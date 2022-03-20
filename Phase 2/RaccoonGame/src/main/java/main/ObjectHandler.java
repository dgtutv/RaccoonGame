package main;

import object.*;
import subject.Enemy;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler used to spawn items.
 */
public class ObjectHandler {

    RaccoonGame raccoonGame;

    protected int numDoors = 0;
    protected int numRaccoon = 0;
    public int numRewards = 0;
    public int numTraps = 0;
    int[][] mapItemArr;

    public ObjectHandler(RaccoonGame raccoonGame) {
        mapItemArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];
        this.raccoonGame = raccoonGame;
        raccoonGame.mapLoader.loadMap(mapItemArr, "/map/raccoonItemMap.txt");
        spawnItems();
    }


    private void spawnItems(){
        System.out.print(("spawning items"));
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
                    raccoonGame.objects[ind] = new Trap();
                    numTraps++;
                    raccoonGame.objects[ind].x = currentX;
                    raccoonGame.objects[ind].y = currentY;
                    break;
                case 2:
                    raccoonGame.objects[ind] = new Garbage();
                    numRewards++;
                    raccoonGame.objects[ind].x = currentX;
                    raccoonGame.objects[ind].y = currentY;
                    break;
                case 3:
                    raccoonGame.objects[ind] = new ExitDoor();
                    numDoors++;
                    raccoonGame.objects[ind].x = currentX;
                    raccoonGame.objects[ind].y = currentY;
                    break;
                case 4:
                    raccoonGame.objects[ind] = new RedRaccoon();
                    numRaccoon++;
                    raccoonGame.objects[ind].x = 0;
                    raccoonGame.objects[ind].y = 0;
                    raccoonGame.objects[ind].collidableArea.width = 0;
                    raccoonGame.objects[ind].collidableArea.height = 0;
                    break;
            }

            ind++;
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
    }
}
