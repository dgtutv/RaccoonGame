package subject;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    //Some basic booleans to track keys being currently pressed
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    //We don't need this yet, but is necessary to implement KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();       //keyCode is the ASCII value of a key on the keyboard
        //now scan to check which key has been pressed
        switch(keyCode){
            case KeyEvent.VK_W:     //Case for "W" key
                upPressed = true;
                break;
            case KeyEvent.VK_S:     //Case for "S" key
                leftPressed = true;
                break;
            case KeyEvent.VK_A:     //Case for "A" key
                downPressed = true;
                break;
            case KeyEvent.VK_D:     //Case for "D" key
                rightPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();       //keyCode is the ASCII value of a key on the keyboard
        //now scan to check which key has been released
        switch(keyCode){
            case KeyEvent.VK_W:     //Case for "W" key
                upPressed = false;
                break;
            case KeyEvent.VK_S:     //Case for "S" key
                leftPressed = false;
                break;
            case KeyEvent.VK_A:     //Case for "A" key
                downPressed = false;
                break;
            case KeyEvent.VK_D:     //Case for "D" key
                rightPressed = false;
                break;
        }
    }
}
