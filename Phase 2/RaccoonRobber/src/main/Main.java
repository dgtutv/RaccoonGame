package main;

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
        RaccoonGame racoonGame = new RaccoonGame();
        gameWindow.add(racoonGame);

        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(true);
    }

}
