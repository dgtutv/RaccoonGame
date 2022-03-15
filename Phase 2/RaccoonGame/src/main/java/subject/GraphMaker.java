package subject;

import main.RaccoonGame;

import java.awt.color.ICC_ColorSpace;

public class GraphMaker {
    //Needed variables
    RaccoonGame raccoonGame;
    public int mapBlockArr[][];
    public int mapNodeArr[][];
    Node head;

    //Node class for a linked list-based map
    private class Node{
        public Node up, down, left, right = null;
        public int blockX, blockY;
        public boolean isZero;
        Node(int x, int y){
            this.blockX = x;
            this.blockY = y;
        }
    }
    //constructor
    public GraphMaker(RaccoonGame raccoonGame) {
        //Set variables based off constructor input
        this.raccoonGame = raccoonGame;
        this.mapBlockArr = raccoonGame.mapManager.mapBlockArr;
        graphGenerate();
        wallCleanup();
    }


    //This method gets rid of the walls in the graph
    public void wallCleanup(){
        Node currentNode = head;
        Node prevNode = null;

        //If the head is a wall
        while(currentNode != null && !currentNode.isZero){
            if(currentNode.right != null){
                head = currentNode.right;
                currentNode = currentNode.right;
            }
            else if(currentNode.down != null){
                //go to the far left
                while(currentNode.left != null){
                    currentNode = currentNode.left;
                }
                //then go down
                head = currentNode.down;
                currentNode = head;
            }
        }

        //remove walls that are not the head
        while(currentNode != null){
            while(currentNode != null && !currentNode.isZero){
                prevNode = currentNode;
                currentNode =  currentNode.right;
            }
            //if not present in this row
            if(currentNode == null){
                //go to the far left
                while(currentNode.left != null){
                    currentNode = currentNode.left;
                }
                //then go down if possible
                if(currentNode.down != null){
                    currentNode = currentNode.down;
                }
                //If not possible, all walls have been removed
                else{
                    break;
                }
            }
            //Remove the node, and update the
            else{
                //detach from the left
                prevNode.right = null;
                //detach from the right
                currentNode.right.left = null;
                //detach from above
                if(currentNode.up != null){
                    currentNode.up.down = null;
                }
                //detach from below
                if(currentNode.down != null){
                    currentNode.down.up = null;
                }
                currentNode = currentNode.right;
            }
        }
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
                currentNode.left = new Node(currentNode.blockX - 1, currentNode.blockY);
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
                currentNode.right = new Node(currentNode.blockX + 1, currentNode.blockY);
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
                currentNode.down = new Node(currentNode.blockX, currentNode.blockY + 1);
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
                currentNode.up = new Node(currentNode.blockX, currentNode.blockY - 1);
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
