package Factory;

import CollectableObject.ExitDoor;
import CollectableObject.Garbage;
import CollectableObject.RedRaccoon;
import CollectableObject.Trap;

//Abstract factory for objects
public class CollectableObjectFactory{
    public static ExitDoor createExitDoor(){
        return new ExitDoor();
    }
    public static Trap createTrap(){
        return new Trap();
    }
    public static Garbage createGarbage(){
        return new Garbage();
    }
    public static RedRaccoon createRedRaccoon(){
        return new RedRaccoon();
    }
}
