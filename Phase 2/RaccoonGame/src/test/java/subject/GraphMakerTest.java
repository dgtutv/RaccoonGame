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
    void find() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        for(int row = 0; row< raccoonGame.windowRow; row++){
            for(int col = 0; col< raccoonGame.windowCol; col++){
                assertNotNull(GraphMaker.find(col, row));
                assertEquals(col, GraphMaker.find(col, row).x);
                assertEquals(row, GraphMaker.find(col, row).y);
            }
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    void print() {
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        raccoonGame.graphMaker.print();


        raccoonGame.gameWindow.dispose();
    }
}