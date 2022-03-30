package subject;

import main.RaccoonGame;
import object.Trap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles enemies as a group. Also contains the spawning info.
 */
public class EnemyHandler {
    //Lists of enemies, should also contain coordinates of such enemies.
    public List<Enemy> EnemyList = new ArrayList<>();
    RaccoonGame raccoonGame;
    Player player;
    int[][] mapEnemyArr;

    //constructor
    public EnemyHandler(RaccoonGame raccoonGame, Player player){
        this.raccoonGame = raccoonGame;
        this.player = player;
        mapEnemyArr = new int[raccoonGame.windowCol][raccoonGame.windowRow];

        raccoonGame.mapLoader.loadMap(mapEnemyArr, "/map/raccoonEnemyMap.txt");


        spawnEnemies();


    }


    //spawn enemies based off our loaded map
    private void spawnEnemies(){
        int currentCol = 0;
        int currentRow = 0;
        int currentX = 0;
        int currentY = 0;

        //iterate through our array, spawn an enemy when we have a 1, do nothing with a 0
        while(currentCol < raccoonGame.windowCol && currentRow < raccoonGame.windowRow) {
            int blockNum = mapEnemyArr[currentCol][currentRow];

            if(blockNum == 1) { //if we want an enemy here
                EnemyList.add(new Enemy(raccoonGame, currentX, currentY, player));
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
    }

    public void deleteEnemies() {
        EnemyList.clear();
    }
}

