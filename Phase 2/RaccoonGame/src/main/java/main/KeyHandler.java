package main;

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
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();       //keyCode is the ASCII value of a key on the keyboard
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
