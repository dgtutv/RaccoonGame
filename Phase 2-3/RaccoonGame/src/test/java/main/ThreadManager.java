package main;

public class ThreadManager {

    public static void startThread(RaccoonGame raccoonGame) {
        raccoonGame.startThread();
    }

    public void waitThread(RaccoonGame raccoonGame) {
        raccoonGame.startThread();
    }

    public void doTick(int numTick, RaccoonGame raccoonGame) {
        //implement delta-style game loop
        long previousTime = System.nanoTime();
        long currentTime;
        int timer = 0;
        int genTimer = 0;
        double delta = 0;

        //calculate running interval (nano-sec per sec / ticks)
        double loopInterval = 1000000000/raccoonGame.ticks;

        //gameThread loop
        while(genTimer < numTick) {
            currentTime = System.nanoTime();

            //delta is equal to the difference between the currentTime and the last time the loop was run
            //divided by the loop interval
            delta += (currentTime - previousTime) / loopInterval;
            previousTime = currentTime;

            if(delta >= 1) {
                //increment timer
                genTimer++;
                timer++;
                //update information (in a try catch for thread.sleep
                try {
                    raccoonGame.update();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //draw with new information
                raccoonGame.repaint();
                delta--;
            }

            if(timer >= raccoonGame.ticks) { //every one second
                //reset timer
                timer = 0;
            }
        }

    }
}
