package subject;

import junit.framework.TestCase;
import main.RaccoonGame;
import org.junit.Assert;
import org.junit.Test;

public class TreeMakerTest extends TestCase {
    @Test
    public void MakeTree(){
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.gameState = raccoonGame.playState;

        try {
            for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size(); i++){
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree);
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.graph);
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.enemy);
                Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.player);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        raccoonGame.gameWindow.dispose();
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testPrint() {
    }

    @Test
    public void blockUpdate(){

    }
}