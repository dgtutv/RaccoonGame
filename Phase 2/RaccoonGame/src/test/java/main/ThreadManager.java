package main;

public class ThreadManager {

    public static void startThread(RaccoonGame raccoonGame) {
        raccoonGame.startThread();
    }

    public static void doTick(int numTick, RaccoonGame raccoonGame) throws InterruptedException {
        for(int i=0; i< numTick; i++) {
            raccoonGame.update();
            raccoonGame.repaint();
        }
    }
}
