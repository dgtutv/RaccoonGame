package subject;

import main.RaccoonGame;
import object.Trap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//A class to handle enemies as a group
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

        loadMap();
        spawnEnemies();
    }

    //load the map from a text file
    private void loadMap(){
        try {
            //set up file reader
            InputStream inputStream = getClass().getResourceAsStream("/map/raccoonEnemyMap.txt");
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
                    mapEnemyArr[currentCol][currentRow] = num;
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
}

