package main;

import javax.print.attribute.standard.PrinterIsAcceptingJobs;
import javax.swing.*;

public class Main {

    //main method, will create window and create game panel here
    public static void main(String[] args) {

        RaccoonGame raccoonGame = new RaccoonGame();
        raccoonGame.startThread();


    }


}
