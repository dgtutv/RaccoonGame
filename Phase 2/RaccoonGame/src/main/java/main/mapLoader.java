package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class mapLoader {

    RaccoonGame raccoonGame;

    public mapLoader(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;
    }

    //this method loads the map from a text file into a 2D array mapBlockArr[][]
    public void loadMap(int[][] mapArr, String fileName) {
        try {
            //set up file reader
            InputStream inputStream = getClass().getResourceAsStream(fileName);
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
                    mapArr[currentCol][currentRow] = num;
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
}
