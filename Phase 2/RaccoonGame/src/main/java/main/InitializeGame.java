package main;

import javax.swing.*;

public class InitializeGame {

    public JFrame gameWindow;

    public InitializeGame() {
        //create new window
        gameWindow = new JFrame();
    }

    public void setupGame(JFrame window) {


        //set window properties
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Raccoon Robber");


    }

    public void startGame(JFrame window) {
        //create new game panel
        //this is where we will have to implement a UI class possibly for the main menu, which then
        //would have a button to call the below raccoonGame initiation
        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.initializedGame = this;
        window.add(raccoonGame);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        //setup game
        raccoonGame.setupGame();

        //start the game
        raccoonGame.startThread();
    }

    public void resetGame(JFrame window, RaccoonGame raccoonGame) {
        window.remove(raccoonGame);
        startGame(window);
    }
}
