package subject;

import junit.framework.TestCase;
import main.RaccoonGame;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TreeMakerTest {

    RaccoonGame raccoonGame;

    /*

    private RaccoonGame testFactory(){
        raccoonGame = new RaccoonGame();
        for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size()-1; i++) {
            raccoonGame.enemyHandler.EnemyList.get(i).speed = 0;
        }
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.player.x = 476;
        raccoonGame.player.y = 480;
        return  raccoonGame;
    }

    @Test
    public void MakeTree(){
        raccoonGame = testFactory();
        Assert.assertEquals(5 ,raccoonGame.enemyHandler.EnemyList.size());
        try {
            for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size()-1; i++){
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree);
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.graph);
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.enemy);
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.player);
                raccoonGame.enemyHandler.EnemyList.get(i).speed = 0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTest(){
        raccoonGame = testFactory();
        Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(1).x, raccoonGame.enemyHandler.EnemyList.get(1).tree.root.x);
        Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(1).y, raccoonGame.enemyHandler.EnemyList.get(1).tree.root.y);
        Assert.assertEquals(raccoonGame.enemyHandler.player.x, raccoonGame.enemyHandler.EnemyList.get(1).tree.playerNode.x);
        Assert.assertEquals(raccoonGame.enemyHandler.player.y, raccoonGame.enemyHandler.EnemyList.get(1).tree.playerNode.y);
        Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(1).tree.root);
        Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(1).tree.playerNode);
        Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(1).tree.tree);
        Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(1).tree.print(raccoonGame.enemyHandler.EnemyList.get(1).tree.tree), "(17 ,11)(17 ,12)(17 ,13)(16 ,13)(15 ,13)(15 ,14)(15 ,15)(14 ,15)");

    }

    @Test
    public void testPrint() {
        raccoonGame = testFactory();
        try {
            for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size()-1; i++){
                List<GraphMaker.Node> list = raccoonGame.enemyHandler.EnemyList.get(i).tree.update();
                Assert.assertNotEquals("", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                if(i==1){
                    Assert.assertEquals("(17 ,11)(17 ,12)(17 ,13)(16 ,13)(15 ,13)(15 ,14)(15 ,15)(14 ,15)", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                }
            }
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void blockUpdate(){
        raccoonGame = testFactory();
    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame = null;
    }

     */
}