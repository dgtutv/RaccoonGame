package main;

import GUI.GUI;
import block.MapManager;

import javax.swing.*;
import java.awt.*;

import object.GeneralObject;
import subject.EnemyHandler;
import subject.Player;

public class RaccoonGame extends JPanel implements Runnable{

    //set screen settings and map block size
    final int pixelBlockSize = 32; //16 pixel by 16 pixel map blocks
    final int blockSizeScale = 1; //scale this by 3, so that it displays well on modern monitors
    public final int blockSize = pixelBlockSize * blockSizeScale;

    //set our map size to be 20 by 20 blocks
    public final int windowCol = 30;
    public final int windowRow = 20;
    public final int windowWidth = windowCol * blockSize;
    public final int windowHeight = windowRow * blockSize;

    //Initialize a key handler and a player with it
    KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);

    //Initialize collision handler
    public CollisionHandler collisionHandler = new CollisionHandler(this);

    //Initialize object array and object handler
    public ObjectHandler objectHandler = new ObjectHandler(this);
    public GeneralObject[] objects = new GeneralObject[10];

    //initialize game thread
    Thread gameThread;
    MapManager mapManager = new MapManager(this);
    public GUI gui = new GUI(this);

    //spawn in the enemies
    public EnemyHandler enemyHandler = new EnemyHandler(this, player);

    //create main game method
    public RaccoonGame() {
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        //Necessary lines for accepting key input
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        objectHandler.setObject();
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
        int timer = 0;
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
                //increment timer
                timer++;
                //update information
                update();
                //draw with new information
                repaint();
                delta--;
            }

            if(timer >= ticks) { //every one second
                //reset timer
                timer = 0;
            }
        }
    }

    //IMPORTANT METHODS, THIS IS WHERE WE WILL IMPLEMENT ALL OUR METHODS
    //update method, update all information here such as keystrokes from a key handler class
    protected void update() {
        //Update player
        player.update();

        //update enemies
        for(int i=0; i<enemyHandler.EnemyList.size(); i++){
            enemyHandler.EnemyList.get(i).update();
        }

        // David: GUI update 3.3
        gui.update((player.score%10), (player.score/10)%10, (player.score/100)%10);

        //End the game if game over, add a game over screen here in future
        if(player.GameOver){
            player.rewardUpdate(10);    //temporary
            //We're going to want some sort of breakdown like this on the game over screen
            System.out.println("Score: " + player.score);       //temporary
            System.out.println("Reward: " + player.reward);     //temporary
            //Add the rewards to the score at the end of the game
            player.score += player.reward;
            System.out.println("Total Score: " + player.score);     //temporary
            gameThread = null;
            System.out.println("Game Over!");       //temporary
        }
    }

    //paint characters, items, map, etc...
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        //Draw everything here:

        //draw map
        mapManager.drawMap(graphics);

        //draw items
        for(int i = 0; i < objects.length; i++) {
            if(objects[i] != null) {
                objects[i].drawObject(graphics, this);
            }
        }

        //draw player
        player.draw(graphics);

        //draw enemies
        for(int i=0; i<enemyHandler.EnemyList.size(); i++){
            enemyHandler.EnemyList.get(i).draw(graphics);
        }

        // Draw GUI
        gui.drawGUI(graphics);

        //clean up
        graphics.dispose();
    }
}
