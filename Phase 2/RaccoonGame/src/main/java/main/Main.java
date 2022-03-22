package main;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.swing.*;

public class Main {

    //main method, will create window and create game panel here
    public static void main(String[] args) {

        //create new window
        JFrame gameWindow = new JFrame();

        //set window properties
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setTitle("Raccoon Robber");

        //create new game panel
        //this is where we will have to implement a UI class possibly for the main menu, which then
        //would have a button to call the below raccoonGame initiation
        RaccoonGame raccoonGame = new RaccoonGame();
        //setup game
        raccoonGame.setupGame();
        gameWindow.add(raccoonGame);



        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);



        //start the game
        raccoonGame.startThread();


    }

}
