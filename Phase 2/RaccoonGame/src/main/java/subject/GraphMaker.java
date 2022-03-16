package subject;

import main.RaccoonGame;

import java.util.ArrayList;

public class GraphMaker {
    //Needed variables
    RaccoonGame raccoonGame;
    public int mapBlockArr[][];
    public int mapNodeArr[][];
    public static Node head;

    //Node class for a linked list-based map
    public static class Node{
        //general node stuff
        public int x, y;
        //graph node stuff
        public Node up, down, left, right = null;
        public boolean isRoot = false;
        public boolean visited = false;
        public boolean isZero = false;
        //tree node stuff
        public Node parent = null;
        public ArrayList<Node> children = new ArrayList<Node>();
        public boolean isParent = false;
        //Constructor
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    //constructor
    public GraphMaker(RaccoonGame raccoonGame) {
        //Set variables based off constructor input
        this.raccoonGame = raccoonGame;
        this.mapBlockArr = raccoonGame.mapManager.mapBlockArr;
        graphGenerate();
    }

    //Print the map
    public void print(){
        for(Node i = head; i != null; i = i.down){
            System.out.print("\n");
            for(Node j = i; j != null; j = j.right){
                if(j.isZero){
                    System.out.print(" O ");
                }
                else{
                    System.out.print(" ! ");
                }
            }
        }
    }

    //Method used to find a node, returns such node. Returns null if no such node was found
    public static Node find(int x, int y){
        for(Node i = head; i != null; i = i.down){
            for(Node j = i; j != null; j = j.right){
                if(j.x ==x && j.y == y){
                    return j;
                }
            }
        }
        return null;
    }

    //Create our linked list based-graph using the mapArray from MapManager.
    //We go from top left to bottom right
    public void graphGenerate(){
        //Iterator variables
        int currentCol = 0;
        int currentRow = 0;
        Node currentNode = new Node(0, 0);
        head = currentNode;

        //The loop itself
        while(currentCol < raccoonGame.windowCol && currentRow < raccoonGame.windowRow) {
            int blockNum = mapBlockArr[currentCol][currentRow];
            if (blockNum == 0){
                currentNode.isZero = true;
            }

            //Left
            if(currentCol - 1 < raccoonGame.windowCol && currentRow < raccoonGame.windowRow && currentCol -1 > -1){
                blockNum = mapBlockArr[currentCol-1][currentRow];
                currentNode.left = new Node(currentNode.x - 1, currentNode.y);
                if(blockNum == 0){
                    currentNode.left.isZero = true;

                }
                else{
                    currentNode.left.isZero = false;
                }
            }

            //Right
            if(currentCol + 1 < raccoonGame.windowCol && currentRow < raccoonGame.windowRow){
                blockNum = mapBlockArr[currentCol+1][currentRow];
                currentNode.right = new Node(currentNode.x + 1, currentNode.y);
                if(blockNum == 0){
                    currentNode.right.isZero = true;
                }
                else{
                    currentNode.right.isZero = false;
                }
            }

            //Down
            if(currentCol < raccoonGame.windowCol && currentRow + 1 < raccoonGame.windowRow){
                blockNum = mapBlockArr[currentCol][currentRow+1];
                currentNode.down = new Node(currentNode.x, currentNode.y + 1);
                if(blockNum == 0){
                    currentNode.down.isZero = true;
                }
                else{
                    currentNode.down.isZero = false;
                }
            }

            //Top
            if(currentCol< raccoonGame.windowCol && currentRow - 1 < raccoonGame.windowRow && currentRow - 1 > -1) {
                blockNum = mapBlockArr[currentCol][currentRow-1];
                currentNode.up = new Node(currentNode.x, currentNode.y - 1);
                if(blockNum == 0){
                    currentNode.up.isZero = true;
                }
                else{
                    currentNode.up.isZero = false;
                }
            }
            currentCol++;

            //row checked, reset col variables and draw the next row
            if(currentCol == raccoonGame.windowCol) {
                currentCol = 0;
                currentRow++;
                currentNode = head;
                for(int i=0; i<currentRow; i++){
                    currentNode = currentNode.down;
                }
            }
            else{
                currentNode = currentNode.right;
            }
        }
    }
}
