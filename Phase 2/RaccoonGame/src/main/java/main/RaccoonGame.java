package main;

import block.MapManager;

import javax.swing.*;
import java.awt.*;
import subject.Character;

public class RaccoonGame extends JPanel implements Runnable{

    //set screen settings and map block size
    final int pixelBlockSize = 16; //16 pixel by 16 pixel map blocks
    final int blockSizeScale = 3; //scale this by 3, so that it displays well on modern monitors
    public final int blockSize = pixelBlockSize * blockSizeScale;

    //set our map size to be 20 by 20 blocks
    public final int windowCol = 30;
    public final int windowRow = 20;
    public final int windowWidth = windowCol * blockSize;
    public final int windowHeight = windowRow * blockSize;

    //initialize game thread
    Thread gameThread;
    MapManager mapManager = new MapManager(this);
    Character character = new Character(this);


    //create main game method
    public RaccoonGame() {
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    //hopefully should not need to change anything here, modular design so our changes can
    //go in update() and paintComponent()
    @Override
    public void run() {
        //implement delta-style game loop
        long previousTime = System.nanoTime();
        long currentTime;
        int ticks = 60;
        double delta = 0;
        //calculate running interval (nano-sec per sec / ticks)
        double loopInterval = 1000000000/ticks;

        //gameThread loop
        while(gameThread != null) {
            currentTime = System.nanoTime();

            //delta is equal to the difference between the currentTime and the last time the loop was run
            //divided by the loop interval
            delta += (currentTime - previousTime) / loopInterval;
            previousTime = currentTime;

            if(delta >= 1) {
                //update information
                update();
                //draw with new information
                repaint();
                delta--;
            }
        }
    }

    //IMPORTANT METHODS, THIS IS WHERE WE WILL IMPLEMENT ALL OUR METHODS
    //update method, update all information here such as keystrokes from a key handler class
    protected void update() {
        //Update everything here
        character.update();

    }

    //paint characters, items, map, etc...
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        //Draw everything here:
        character.draw(graphics);

        //draw map
        mapManager.drawMap(graphics);


        //clean up
        graphics.dispose();
    }
}
