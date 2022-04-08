package Enemy;

import junit.framework.TestCase;
import main.RaccoonGame;
import object.Node;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TreeMakerTest {

    RaccoonGame raccoonGame;


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
        for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size(); i++) {
            raccoonGame.enemyHandler.EnemyList.get(1).path = raccoonGame.enemyHandler.EnemyList.get(i).tree.update();
            Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(i).x / raccoonGame.blockSize, raccoonGame.enemyHandler.EnemyList.get(i).tree.root.x);
            Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(i).y / raccoonGame.blockSize, raccoonGame.enemyHandler.EnemyList.get(i).tree.root.y);
            Assert.assertEquals(raccoonGame.enemyHandler.player.x / raccoonGame.blockSize, raccoonGame.enemyHandler.EnemyList.get(i).tree.playerNode.x);
            Assert.assertEquals(raccoonGame.enemyHandler.player.y / raccoonGame.blockSize, raccoonGame.enemyHandler.EnemyList.get(i).tree.playerNode.y);
            Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.root);
            Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.playerNode);
            Assert.assertNotNull(raccoonGame.enemyHandler.EnemyList.get(i).tree.tree);
        }
    }

    @Test
    public void testPrint() {
        raccoonGame = testFactory();
        try {
            for (int i = 0; i < raccoonGame.enemyHandler.EnemyList.size(); i++){
                List<Node> list = raccoonGame.enemyHandler.EnemyList.get(i).tree.update();
                Assert.assertNotEquals("", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                if(i==0){
                    Assert.assertEquals("(31 ,8)(31 ,9)(31 ,10)(31 ,11)(31 ,12)(31 ,13)(30 ,13)(29 ,13)(28 ,13)(27 ,13)(26 ,13)(25 ,13)(24 ,13)(23 ,13)(22 ,13)(21 ,13)(20 ,13)(19 ,13)(18 ,13)(17 ,13)(16 ,13)(15 ,13)(15 ,14)(15 ,15)(14 ,15)", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                }
                else if(i==1) {
                    Assert.assertEquals("(17 ,11)(17 ,12)(17 ,13)(16 ,13)(15 ,13)(15 ,14)(15 ,15)(14 ,15)", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                }
                else if(i==2){
                    Assert.assertEquals("(23 ,18)(22 ,18)(21 ,18)(20 ,18)(19 ,18)(18 ,18)(17 ,18)(16 ,18)(15 ,18)(15 ,17)(15 ,16)(15 ,15)(14 ,15)", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                }
                else if(i==3){
                    Assert.assertEquals("(8 ,26)(8 ,25)(8 ,24)(8 ,23)(8 ,22)(8 ,21)(8 ,20)(8 ,19)(8 ,18)(8 ,17)(8 ,16)(8 ,15)(9 ,15)(10 ,15)(11 ,15)(12 ,15)(13 ,15)(14 ,15)", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
                }
                else if(i==4){
                    Assert.assertEquals("(35 ,27)(35 ,26)(35 ,25)(34 ,25)(33 ,25)(32 ,25)(31 ,25)(30 ,25)(29 ,25)(28 ,25)(27 ,25)(26 ,25)(26 ,24)(25 ,24)(24 ,24)(24 ,23)(24 ,22)(24 ,21)(24 ,20)(24 ,19)(24 ,18)(23 ,18)(22 ,18)(21 ,18)(20 ,18)(19 ,18)(18 ,18)(17 ,18)(16 ,18)(15 ,18)(15 ,17)(15 ,16)(15 ,15)(14 ,15)", raccoonGame.enemyHandler.EnemyList.get(i).tree.print(list));
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
        try{
            for(int i = 0; i < raccoonGame.enemyHandler.EnemyList.size(); i++){
                raccoonGame.enemyHandler.EnemyList.get(i).path = raccoonGame.enemyHandler.EnemyList.get(i).tree.update();
                Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(i).tree.playerBlockX, raccoonGame.player.x / raccoonGame.blockSize);
                Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(i).tree.playerBlockY, raccoonGame.player.y / raccoonGame.blockSize);
                Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(i).tree.enemyBlockX, raccoonGame.enemyHandler.EnemyList.get(i).x / raccoonGame.blockSize);
                Assert.assertEquals(raccoonGame.enemyHandler.EnemyList.get(i).tree.enemyBlockY, raccoonGame.enemyHandler.EnemyList.get(i).y / raccoonGame.blockSize);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void endTest() {
        raccoonGame.gameWindow.dispose();
        raccoonGame.stopThread();
    }
}
