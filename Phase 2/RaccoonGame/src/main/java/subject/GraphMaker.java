package subject;

import main.RaccoonGame;

import java.util.ArrayList;
import java.util.Currency;

public class GraphMaker {
    //Needed variables
    static RaccoonGame raccoonGame;
    public int mapBlockArr[][];
    private static Node[][] mapNodeArr;
    public static Node head;

    //Node class for a linked list-based map
    public static class Node {
        //general node stuff
        public int x, y;
        //graph node stuff
        public Node up, down, left, right = null;
        public boolean visited = false;
        public boolean isZero = false;
        //tree node stuff
        public ArrayList<Node> children;

        //Constructor
        Node(int x, int y) {
            this.x = x;
            this.y = y;
            children = new ArrayList<Node>();
        }
    }

    //constructor
    public GraphMaker(RaccoonGame raccoonGame) {
        //Set variables based off constructor input
        this.raccoonGame = raccoonGame;
        this.mapBlockArr = raccoonGame.mapManager.mapBlockArr;
        mapNodeArr = new Node[raccoonGame.windowCol][raccoonGame.windowRow];
        graphGenerate();
        graphDirectionFill();
    }

    //Create our linked list based-graph using the mapArray from MapManager.
    //We go from top left to bottom right
    private void graphGenerate() {
        //The loop itself
        for(int currentRow = 0; currentRow< raccoonGame.windowRow; currentRow++){
            for(int currentCol = 0; currentCol< raccoonGame.windowCol; currentCol++){
                //Initialize node and add to array
                Node currentNode = new Node(currentCol, currentRow);
                mapNodeArr[currentCol][currentRow] = currentNode;
                //if block is a 0 (i.e, not collidable), then set isZero to true. isZero is false by default
                int blockNum = mapBlockArr[currentCol][currentRow];
                if (blockNum == 0) {
                    currentNode.isZero = true;
                }
            }
        }
    }

    //Here we assign the direction variables to each node from a completed map
    private void graphDirectionFill(){
        for(int row = 0; row< raccoonGame.windowRow; row++){
            for(int col = 0; col< raccoonGame.windowCol; col++){
                if(0<row){
                    mapNodeArr[col][row].up = mapNodeArr[col][row-1];
                }
                if(0<col){
                    mapNodeArr[col][row].left = mapNodeArr[col-1][row];
                }
                if(row<raccoonGame.windowRow-1) {
                    mapNodeArr[col][row].down = mapNodeArr[col][row+1];
                }
                if(col< raccoonGame.windowCol-1){
                    mapNodeArr[col][row].right = mapNodeArr[col+1][row];
                }
            }
        }
    }

    //Method used to find a node, returns such node. Returns null if no such node was found
    public static Node find(int x, int y) {
        return mapNodeArr[x][y];
    }

    //Print the map
    public void print() {
        for(int row = 0; row< raccoonGame.windowRow; row++){
            System.out.println();
            for(int col = 0; col< raccoonGame.windowCol; col++){
                if(mapNodeArr[col][row].isZero){
                    System.out.print("0 ");
                } else {
                    System.out.print("! ");
                }
            }
        }
    }
}
