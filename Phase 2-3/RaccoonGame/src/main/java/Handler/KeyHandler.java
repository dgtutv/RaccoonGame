package Handler;

import main.RaccoonGame;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

/**
 * Delegates certain keys into in-game commands.
 */
public class KeyHandler implements KeyListener {
    //Some basic booleans to track keys being currently pressed
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    RaccoonGame raccoonGame;

    /**
     * Constructs a new KeyHandler class and sets its raccoonGame attribute to the parameter passed.
     */
    public KeyHandler(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;
    }

    //We don't need this yet, but is necessary to implement KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }


    /**
     * Calls various sub-methods depending on the current gameState of RaccoonGame. Also calculates
     * the integer keyCode from the given KeyEvent and passes this to the sub-methods for processing.
     * @param e: takes in a KeyEvent object e.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode(); //keyCode is the ASCII value of a key on the keyboard

        if(raccoonGame.gameState == raccoonGame.titleState) {
            keyPressedTitle(keyCode);
        }

        else if(raccoonGame.gameState == raccoonGame.endState) {
            keyPressedEnd(keyCode);
        }

        else if(raccoonGame.gameState == raccoonGame.pauseState) {
            keyPressedPause(keyCode);
        }

        else if(raccoonGame.gameState == raccoonGame.playState) {
            keyPressedPlay(keyCode);
        }
    }

    /**
     * Controls the game for the title screen. Checks for input of W or S keys to move the cursor
     * or ENTER key to either start or quit the game depending on the current cursor placement.
     * @param keyCode: integer keyCode corresponding to the key pressed.
     */
    public void keyPressedTitle(int keyCode ) {
        if(keyCode == KeyEvent.VK_W){      //If w pressed
            if(raccoonGame.gui.cursorNum > 0) {
                raccoonGame.gui.cursorNum--;
            }
        }
        if(keyCode == KeyEvent.VK_S){      //if s pressed
            if(raccoonGame.gui.cursorNum < 1) {
                raccoonGame.gui.cursorNum++;
            }
        }

        if(keyCode == KeyEvent.VK_ENTER) {
            switch (raccoonGame.gui.cursorNum) {
                case 0:
                    raccoonGame.gameState = raccoonGame.playState;
                    raccoonGame.sound.stop(raccoonGame.sound);
                    raccoonGame.sound.music(0, raccoonGame.sound);
                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Controls the game for the pause screen. Checks for input of W or S keys to move the cursor
     * or ENTER key to either resume or quit the game depending on the current cursor placement.
     * @param keyCode: integer keyCode corresponding to the key pressed.
     */
    public void keyPressedPause(int keyCode) {
        if(keyCode == KeyEvent.VK_ESCAPE){      //if esc pressed
            raccoonGame.gameState = raccoonGame.playState;
        }
        if(keyCode == KeyEvent.VK_W){      //If w pressed
            if(raccoonGame.gui.cursorNum > 0) {
                raccoonGame.gui.cursorNum--;
            }
        }
        if(keyCode == KeyEvent.VK_S){      //if s pressed
            if(raccoonGame.gui.cursorNum < 1) {
                raccoonGame.gui.cursorNum++;
            }
        }

        if(keyCode == KeyEvent.VK_ENTER) {
            switch (raccoonGame.gui.cursorNum) {
                case 0:
                    raccoonGame.gameState = raccoonGame.playState;
                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }

    /**
     * Controls the game for the play screen. Checks for input of W, A, S, and D keys and set's the
     * keyHandler's upPressed, downPressed, leftPressed, and rightPressed boolean attributes to true
     * respectively. Also check's for ESCAPE in order to pause the game.
     * @param keyCode: integer keyCode corresponding to the key pressed.
     */
    public void keyPressedPlay(int keyCode) {
        //now scan to check which key has been pressed
        if(keyCode == KeyEvent.VK_W){      //If w pressed
            upPressed = true;
        }
        if(keyCode == KeyEvent.VK_S){      //if s pressed
            downPressed = true;
        }
        if(keyCode == KeyEvent.VK_A){      //if a pressed
            leftPressed = true;
        }
        if(keyCode == KeyEvent.VK_D){      //if d pressed
            rightPressed = true;
        }
        if(keyCode == KeyEvent.VK_ESCAPE){      //if esc pressed
            raccoonGame.gameState = raccoonGame.pauseState;
        }
    }

    /**
     * Controls the game for the end screen. Monitors for ENTER which exits the program.
     * @param keyCode: integer keyCode corresponding to the key pressed.
     */
    public void keyPressedEnd(int keyCode) {
        if(keyCode == KeyEvent.VK_ENTER) {
            System.exit(0);

        }
    }

    /**
     * In playState this method will set the KeyHandler's upPressed, downPressed, leftPressed,
     * or rightPressed attributes to false if they had recently been pressed and set true
     * by the keyPressed method, else it does nothing.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();       //keyCode is the ASCII value of a key on the keyboard

        if(raccoonGame.gameState == raccoonGame.playState) {
            //now scan to check which key has been released
            if(keyCode == KeyEvent.VK_W){      //If w pressed
                upPressed = false;
            }
            if(keyCode == KeyEvent.VK_S){      //if s pressed
                downPressed =false;
            }
            if(keyCode == KeyEvent.VK_A){      //if a pressed
                leftPressed =false;
            }
            if(keyCode == KeyEvent.VK_D){      //if d pressed
                rightPressed =false;
            }
        }

    }
}
