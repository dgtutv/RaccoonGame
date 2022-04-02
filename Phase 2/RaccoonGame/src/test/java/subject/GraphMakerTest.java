package subject;

import main.RaccoonGame;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static subject.GraphMaker.mapNodeArr;
import static subject.GraphMaker.raccoonGame;

class GraphMakerTest {

    @Test
    public void makeGraph() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        try {
            assertNotNull(raccoonGame.graphMaker);
            assertNotNull(raccoonGame.graphMaker.mapBlockArr);
            assertNotNull(mapNodeArr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void find() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        for(int row = 0; row< raccoonGame.windowRow; row++){
            for(int col = 0; col< raccoonGame.windowCol; col++){
                try{
                    assertNotNull(GraphMaker.find(col, row));
                    assertEquals(col, GraphMaker.find(col, row).x);
                    assertEquals(row, GraphMaker.find(col, row).y);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void print() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        String testString = raccoonGame.graphMaker.print();

        String correctString = "\n" +
                "! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! \n" +
                "! ! ! 0 0 ! ! ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! 0 ! ! 0 ! 0 0 0 0 0 0 ! ! 0 0 0 0 ! ! \n" +
                "! ! ! 0 0 ! ! ! 0 ! ! 0 0 ! ! 0 0 ! ! 0 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 0 0 0 ! 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 0 0 0 ! 0 0 0 0 0 0 ! \n" +
                "! ! 0 0 0 0 ! ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 ! ! ! ! ! 0 0 0 0 ! ! \n" +
                "! 0 0 0 0 0 0 ! 0 0 0 0 0 0 ! ! 0 0 ! ! 0 0 ! ! 0 0 0 0 0 0 ! 0 0 0 ! ! ! ! 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! ! \n" +
                "! ! ! ! ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! ! ! ! ! \n" +
                "! ! ! ! ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! ! ! ! ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! ! ! ! ! 0 0 0 0 0 0 0 ! ! ! ! ! 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! ! ! ! ! 0 0 0 0 0 0 0 ! ! ! ! ! 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! ! ! 0 ! 0 0 0 0 0 0 0 ! ! ! 0 ! 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 ! 0 0 0 0 0 0 0 ! 0 0 0 ! 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! ! ! ! ! ! 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 ! 0 0 0 0 0 0 0 ! 0 0 0 ! 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! ! ! ! ! ! 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 ! 0 0 0 0 0 0 0 ! 0 0 0 ! 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! ! ! 0 0 0 0 0 0 0 0 0 0 0 ! ! ! ! ! ! ! ! 0 0 0 ! ! ! ! ! ! ! ! ! ! ! 0 0 0 ! ! ! ! ! ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 ! ! ! ! ! ! ! ! 0 0 0 ! ! ! ! ! ! ! ! ! ! ! 0 0 0 ! ! ! ! ! ! \n" +
                "! ! ! 0 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 0 0 0 0 0 0 0 0 0 ! ! 0 ! ! 0 ! ! 0 0 0 0 0 0 ! ! 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! ! ! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 ! \n" +
                "! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ";
        try{
            assertNotEquals("", testString);
            assertEquals(testString, correctString);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        raccoonGame.gameWindow.dispose();
    }
}