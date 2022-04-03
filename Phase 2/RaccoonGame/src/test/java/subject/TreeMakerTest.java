package subject;

import junit.framework.TestCase;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TreeMakerTest {
    @Test
    public void MakeTree(){
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;
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

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void updateTest(){
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;
        raccoonGame.player.x = 476;
        raccoonGame.player.y = 480;
        try {
            for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size()-1; i++){
                raccoonGame.enemyHandler.EnemyList.get(i).speed = 0;
                List<GraphMaker.Node> list = raccoonGame.enemyHandler.EnemyList.get(i).tree.update();
                Assert.assertNotEquals("", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                if(i==1){
                    Assert.assertEquals("(18 ,11)(18 ,12)(18 ,13)(17 ,13)(16 ,13)(15 ,13)(15 ,14)(15 ,15)(14 ,15)", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                }
            }
            return;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        raccoonGame.gameWindow.dispose();

    }

    @Test
    public void testPrint() {
    }

    @Test
    public void blockUpdate(){

    }
}