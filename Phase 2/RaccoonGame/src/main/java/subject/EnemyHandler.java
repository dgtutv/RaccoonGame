package subject;

import main.RaccoonGame;
import object.Trap;

import java.util.ArrayList;
import java.util.List;

//A class to handle the spawning of NPCs
public class EnemyHandler {
    //Lists of enemies, should also contain coordinates of such enemies.
    public List<Enemy> EnemyList = new ArrayList<>();
    RaccoonGame raccoonGame;
    Player player;
    public EnemyHandler(RaccoonGame raccoonGame, Player player){
        this.raccoonGame = raccoonGame;
        this.player = player;
        EnemyList.add(new Enemy(raccoonGame, 40, 60, player));
    }

}
