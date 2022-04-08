package object;

public class Node extends GeneralObject{
    //Additional variables that Node needs on top of what GeneralObject provides
    public boolean visited = false;
    public Node up, down, left, right = null;
    public String direction = "";

    //Default constructor
    public Node(int x, int y, boolean collidable){
        this.x = x;
        this.y = y;
        this.collidable = collidable;
    }
}
