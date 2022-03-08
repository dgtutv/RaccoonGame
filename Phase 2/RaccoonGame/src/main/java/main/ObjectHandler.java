package main;

import object.ExitDoor;
import object.Garbage;
import object.Trap;
import subject.Enemy;

public class ObjectHandler {

    RaccoonGame raccoonGame;

    final protected int numDoors = 2;
    public int numRewards = 2;
    public int numTraps = 1;

    public ObjectHandler(RaccoonGame raccoonGame) {
        this.raccoonGame = raccoonGame;
    }

    public void setObject() {

        for(int i=0; i < numDoors; i++) {
            raccoonGame.objects[i] = new ExitDoor();
        }

        for(int i=numDoors; i < numRewards+numDoors; i++) {
            raccoonGame.objects[i] = new Garbage();
        }

        for(int i=numDoors+numRewards; i < numRewards + numTraps + numDoors; i++) {
            raccoonGame.objects[i] = new Trap();
        }


        //set exitDoor locations
        raccoonGame.objects[0].x = 29 * raccoonGame.blockSize;
        raccoonGame.objects[0].y = 9 * raccoonGame.blockSize;
        raccoonGame.objects[1].x = 29 * raccoonGame.blockSize;
        raccoonGame.objects[1].y = 10 * raccoonGame.blockSize;

        //set garbage object locations
        raccoonGame.objects[2].x = 10 * raccoonGame.blockSize;
        raccoonGame.objects[2].y = 10 * raccoonGame.blockSize;
        raccoonGame.objects[3].x = 0 * raccoonGame.blockSize;
        raccoonGame.objects[3].y = 0 * raccoonGame.blockSize;

        //set trap object locations
        raccoonGame.objects[4].x = 15 * raccoonGame.blockSize;
        raccoonGame.objects[4].y = 5 * raccoonGame.blockSize;
    }

}
