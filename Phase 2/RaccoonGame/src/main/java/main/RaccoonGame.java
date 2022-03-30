package main;

import GUI.GUI;
import block.MapManager;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.awt.*;

import object.GeneralObject;
import subject.EnemyHandler;
import subject.GraphMaker;
import subject.Player;

/**
 * Starts main game loop, constructs handlers.
 */
public class RaccoonGame extends JPanel implements Runnable{

    //game window
    JFrame gameWindow;

    //set screen settings and map block size
    final int pixelBlockSize = 32; //32 pixel by 32 pixel map blocks
    final int blockSizeScale = 1; //scale this by 1, so that it displays well on modern monitors
    public final int blockSize = pixelBlockSize * blockSizeScale;
    int ticks = 60; //FPS

    //set our map size to be 20 by 20 blocks
    public final int windowCol = 45;
    public final int windowRow = 30;
    public final int windowWidth = windowCol * blockSize;
    public final int windowHeight = windowRow * blockSize;

    //initialize game thread
    Thread gameThread;

    public CollisionHandler collisionHandler;
    public main.mapLoader mapLoader;
    public Sound sound;
    public GeneralObject[] objects;
    public ObjectHandler objectHandler;
    public MapManager mapManager;
    public GraphMaker graphMaker;
    public GUI gui;
    public KeyHandler keyHandler;
    public Player player;
    public EnemyHandler enemyHandler;

    //game states
    public int gameState;
    public int titleState = 0;
    public int playState = 1;
    public int endState = 2;
    public int pauseState = 3;


    //create main game method
    public RaccoonGame() {
        setupScreen();
        setupGame();
        startGame();
    }

    public void setupScreen() {
        //create new window
        gameWindow = new JFrame();

        //set window properties
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Raccoon Robber");

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }


    //setup game
    public void setupGame() {

        //Initialize collision handler
        collisionHandler = new CollisionHandler(this);

        //initialize map loader
        mapLoader = new mapLoader(this);

        //initialize sound handler
        sound = new Sound();

        //Initialize object array and object handler
        objects = new GeneralObject[windowCol*windowRow];
        objectHandler = new ObjectHandler(this);
        mapLoader.loadMap(objectHandler.mapItemArr, "/map/raccoonItemMap.txt");
        objectHandler.spawnItems();

        //initialize map
        mapManager = new MapManager(this);
        mapManager.getBlockImage();
        mapLoader.loadMap(mapManager.mapBlockArr, "/map/raccoonGameMap.txt");

        //initialize graphMaker
        graphMaker = new GraphMaker(this);

        //Initialize GUI
        gui = new GUI(this);

        //Initialize KeyHandler and player
        keyHandler = new KeyHandler(this);
        this.addKeyListener(keyHandler);
        player = new Player(this, keyHandler);

        //Initialize enemyHandler
        enemyHandler = new EnemyHandler(this, player);

        gameState = titleState;
        //start the title/end screen music
        sound.music(8, sound);
    }

    public void startGame() {
        gameWindow.add(this);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);

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
                //update information (in a try catch for thread.sleep
                try {
                    update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
    protected void update() throws InterruptedException {
        if(gameState == playState) {

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
                //stop the music for the main game
                //cloneSound.stopSound();

                //wait 3 seconds so that the death music finishes playing before going to the end screen
                sound.stop(sound);
                sound.flushSound();
                sound.effect(7, sound);
                Thread.sleep(3000);

                //play loss sound
                sound.music(8, sound);

                player.score += player.reward;
                gameState = endState;
            }
        }

    }

    //paint characters, items, map, etc...
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
        //Draw everything here:

        //title screen
        if(gameState == titleState) {
            gui.drawGUI(graphics);
        }

        else if(gameState == endState) {
            gui.drawGUI(graphics);
        }


        //play state
        else {
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

        }



        //clean up
        graphics.dispose();
    }



}
