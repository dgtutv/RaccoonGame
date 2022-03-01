package subject;

import main.RaccoonGame;

public class Character extends Subject{
    //Constructor
    public Character(RaccoonGame raccoonGame) {
        super(raccoonGame);
    }

    //Instantiate a KeyHandler, then handle movement itself and drawing with
    KeyHandler keyH = new KeyHandler();

    public void direction(){
        if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.rightPressed) {
                direction = "right";
            } else {
                direction = "left";
            }
        }
    }
}
