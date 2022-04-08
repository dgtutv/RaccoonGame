package Enemy;

import main.RaccoonGame;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GraphMakerTest {

    RaccoonGame raccoonGame;

    @Before
    public void setupTest() {
        raccoonGame = new RaccoonGame();
        raccoonGame.startThread();
    }

    @Test
    public void makeGraph() {
        raccoonGame.gameState = raccoonGame.playState;

        try {
            Assert.assertNotNull(raccoonGame.graphMaker);
            Assert.assertNotNull(raccoonGame.graphMaker.mapBlockArr);
            Assert.assertNotNull(raccoonGame.graphMaker.mapNodeArr);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void find() {
        raccoonGame.gameState = raccoonGame.playState;

        for(int row = 0; row< raccoonGame.windowRow; row++){
            for(int col = 0; col< raccoonGame.windowCol; col++){
                try{
                    Assert.assertNotNull(raccoonGame.graphMaker.find(col, row));
                    Assert.assertEquals(col, raccoonGame.graphMaker.find(col, row).x);
                    Assert.assertEquals(row, raccoonGame.graphMaker.find(col, row).y);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void print() {
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
            Assert.assertNotEquals("", testString);
            Assert.assertEquals(testString, correctString);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void graphDirectionFill(){
        raccoonGame.gameState = raccoonGame.playState;

        for(int row = 0; row< raccoonGame.windowRow; row++){
            for(int col = 0; col< raccoonGame.windowCol; col++){
                if(0 < row){
                    try {
                        Assert.assertEquals(raccoonGame.graphMaker.mapNodeArr[col][row].up, (raccoonGame.graphMaker.mapNodeArr[col][row - 1]));
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                if(0 < col){
                    try {
                        Assert.assertEquals(raccoonGame.graphMaker.mapNodeArr[col][row].left, raccoonGame.graphMaker.mapNodeArr[col - 1][row]);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                if(row < raccoonGame.windowRow-1) {
                    try {
                        Assert.assertEquals(raccoonGame.graphMaker.mapNodeArr[col][row].down, raccoonGame.graphMaker.mapNodeArr[col][row + 1]);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                if(col < raccoonGame.windowCol-1){
                    try {
                        Assert.assertEquals(raccoonGame.graphMaker.mapNodeArr[col][row].right, raccoonGame.graphMaker.mapNodeArr[col + 1][row]);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }

}