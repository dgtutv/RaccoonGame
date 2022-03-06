package main;

import object.Garbage;
import object.Trap;

public class ObjectHandler {

    RaccoonGame raccoonGame;

    public ObjectHandler(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;
    }

    public void setObject() {

        int numGarbageObjects = 2;
        int numTrapObjects = 1;

        for(int i=0; i < numGarbageObjects; i++) {
            raccoonGame.objects[i] = new Garbage();
        }

        for(int i=numGarbageObjects; i < numGarbageObjects + numTrapObjects; i++) {
            raccoonGame.objects[i] = new Trap();
        }

        //set garbage object locations
        raccoonGame.objects[0].x = 10 * raccoonGame.blockSize;
        raccoonGame.objects[0].y = 10 * raccoonGame.blockSize;
        raccoonGame.objects[1].x = 5 * raccoonGame.blockSize;
        raccoonGame.objects[1].y = 15 * raccoonGame.blockSize;

        //set trap object locations
        raccoonGame.objects[2].x = 15 * raccoonGame.blockSize;
        raccoonGame.objects[2].y = 5 * raccoonGame.blockSize;
    }

}
