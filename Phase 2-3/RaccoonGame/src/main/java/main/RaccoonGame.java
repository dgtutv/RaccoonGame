package main;

import GUI.GUI;
import Handler.CollectableObjectHandler;
import Handler.CollisionHandler;
import Handler.KeyHandler;
import Block.BlockManager;

import javax.swing.*;
import java.awt.*;

import Loader.MapLoader;
import object.GeneralCollectableObject;
import Enemy.EnemyHandler;
import Enemy.GraphMaker;
import subject.Player;

/**
 * Starts main game loop, constructs handlers.
 */
public class RaccoonGame extends JPanel implements Runnable{

    //game window
    public JFrame gameWindow;

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
    public MapLoader mapLoader;
    public Sound sound;
    public GeneralCollectableObject[] objects;
    public CollectableObjectHandler collectableObjectHandler;
    public BlockManager blockManager;
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
    /**
     * Constructs a new RaccoonGame object and calls the setupScreen, setupGame, and startGame methods
     * in order to start a new game and display it to a newly created window.
     */
    public RaccoonGame() {
        setupScreen();
        setupGame();
        startGame();
    }

    /**
     * Sets up a JFrame window for the game to be displayed on, sets some of its basic attributes and
     * set's the title of the window to "Raccoon Simulator". Also sets its size, background color, and
     * makes it DoubleBuffered and Focusable.
     */
    public void setupScreen() {
        //create new window
        gameWindow = new JFrame();

        //set window properties
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Raccoon Simulator");

        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

    }


    /**
     * Initializes all of RaccoonGame's object attributes to newly constructed objects that are needed
     * for gamePlay:
     * collisionHandler, MapLoader, sound, objectHandler, mapManager, MapLoader, graphMaker, gui,
     * keyHandler, enemyHandler, and player.
     * Set's the game's default gameState to be titleState.
     */
    public void setupGame() {

        //Initialize collision handler
        collisionHandler = new CollisionHandler(this);

        gameThread = new Thread(this);

        //initialize map loader
        mapLoader = new MapLoader(this);

        //initialize sound handler
        sound = new Sound();

        //Initialize object array and object handler
        objects = new GeneralCollectableObject[windowCol*windowRow];
        collectableObjectHandler = new CollectableObjectHandler(this);
        mapLoader.loadMap(collectableObjectHandler.mapItemArr, "/map/raccoonItemMap.txt");
        collectableObjectHandler.spawnItems();

        //initialize map/*
        blockManager = new BlockManager(this);
        mapLoader.loadMap(blockManager.mapBlockArr, "/map/raccoonGameMap.txt");

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

    /**
     * Pack's the window and makes it visible in the middle of the player's screen.
     */
    public void startGame() {
        gameWindow.add(this);
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }

    /**
     * Initializes the GameThread and starts it.
     */
    public void startThread() {
        gameThread.start();

    }

    public void stopThread() {
        gameThread = null;
    }


    /**
     * The main method that runs the game. Implements a delta-style game loop that runs at 60
     * frames per second (ticks). Calls the update and paint functions each tick.
     */
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
    /**
     * Updates RaccoonGame's object attributes that should be modified while the game is running.
     * Updates the player and enemy locations, checking for any collisions of any type.
     * Also updates the gui with a new score.
     * Checks if the player's gameOver boolean is true and sets the gameState to endState if this is true.
     */
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
            if(player.gameOver){

                //stop all the music and sound effects
                sound.flushSound();
                sound.stopMusic();

                //occurs when the player wins
                if(player.hasEscaped){
                    //play winning sound
                    sound.stopMusic();
                    sound.effect(6, sound);
                }
                else{
                    //wait 3 seconds so that the death music finishes playing before going to the end screen
                    sound.effect(7, sound);
                    Thread.sleep(3000);

                    //play loss sound
                    sound.music(8, sound);
                }

                //add up bonus rewards, and go to the end screen..
                player.score += player.reward;
                gameState = endState;
            }
        }

    }

    /**
     * Draws RaccoonGame's object attributes that should be modified while the game is running.
     * Draws the respective gui depending on the gameState, also draws the map, player and enemies.
     */
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


        //play state or pause state
        else {
            //draw map
            blockManager.drawMap(graphics);

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
