package subject;

import main.RaccoonGame;

public class Character extends Subject{
    //Constructor
    public Character(RaccoonGame raccoonGame) {
        super(raccoonGame);
    }

    //Instantiate a KeyHandler, then handle movement itself and drawing with
    KeyHandler keyH = new KeyHandler();


}
