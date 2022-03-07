package subject;

import main.RaccoonGame;

//A superclass for the two enemy types
public class Enemy extends Subject{
    //Unique constants to all enemies
    Player player;
    int damage;
    int damageCoolDown;         //in ticks
    int damageCoolDownConstant = 30;
    boolean contact = false;        //touching the player?

    //Constructor for Enemy
    public Enemy(RaccoonGame raccoonGame, int x, int y, Player player) {
        super(raccoonGame);
        this.x = x;
        this.y = y;
        this.player = player;
        damageCoolDown = 0;
    }

    //update will call pathing and check if the enemy should be damaging the player
    public void update(){
        if(damageCoolDown>0){
            damageCoolDown--;
        }
        else if(contact){
            player.changeScore(-damage);
            damageCoolDown = damageCoolDownConstant;
        }
    }

    public void timerUpdate() {

    }


    @Override
    public void directionUpdate() {

    }

    @Override
    public void customUpdate() {

    }


}
