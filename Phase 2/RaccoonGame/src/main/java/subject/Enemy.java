package subject;

import main.RaccoonGame;

import java.util.ArrayList;
import java.util.List;

//A superclass for the two enemy types
public class Enemy extends Subject{
    //Unique constants to all enemies
    Character character;
    int damage;
    int damageCoolDown;         //in ticks
    int damageCoolDownConstant = 30;
    boolean contact = false;        //touching the player?

    //Constructor for Enemy
    public Enemy(RaccoonGame raccoonGame, int x, int y, Character character) {
        super(raccoonGame);
        this.x = x;
        this.y = y;
        this.character = character;
        damageCoolDown = 0;
    }

    //update will call pathing and check if the enemy should be damaging the character
    public void update(){
        if(damageCoolDown>0){
            damageCoolDown--;
        }
        else if(contact){
            character.changeHealth(-damage);
            damageCoolDown = damageCoolDownConstant;
        }
    }


    @Override
    public void directionUpdate() {

    }

    @Override
    public void customUpdate() {

    }
}
