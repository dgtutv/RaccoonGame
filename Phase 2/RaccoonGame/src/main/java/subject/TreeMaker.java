package subject;

import main.RaccoonGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static subject.GraphMaker.mapNodeArr;

public class TreeMaker {
    //Some needed variables
    Player player;
    Enemy enemy;
    int enemyBlockX, enemyBlockY, playerBlockX, playerBlockY;
    RaccoonGame raccoonGame;
    GraphMaker graph;
    GraphMaker.Node root, playerNode;
    ArrayList<GraphMaker.Node> path;

    //Default constructor
    TreeMaker(Player player, Enemy enemy){
        //variable initialization from input
        this.player = player;
        this.enemy = enemy;
        this.raccoonGame = player.raccoonGame;
        this.graph = raccoonGame.graphMaker;
        path = new ArrayList<GraphMaker.Node>();
    }

    //Update method called by enemy each update
    public void update(){
        //Get block locations of the enemy and player
        blockUpdate();
        //Initialize a new root for current position of the enemy
        root = GraphMaker.find(enemyBlockX, enemyBlockY);
        //Find the player's Node
        playerNode = GraphMaker.find(playerBlockX, playerBlockY);
        //Printing stuff for testing
        System.out.println("Enemy X: "+root.x+"\nEnemy Y: "+root.y);
        System.out.println("Enemy: "+root);
        System.out.println("Player X: "+playerNode.x+"\nPlayer Y: "+playerNode.y);
        System.out.println("Player: "+playerNode);
        //reset the path and generate the tree
        path.clear();
        BFS();
        //more testing stuff
        printPath();
        System.out.println("Generated Tree");
        System.out.println("Path Size: "+path.size());
    }

    //Method to print the path found from the enemy to the player
    private void printPath(){
        for(int i=path.size()-1; i>-1; i--){
            System.out.print("("+path.get(i).x+" ,"+path.get(i).y+")");
        }
        System.out.println();
    }

    //BFS traversal for enemy pathing. Returns a root to the tree
    private void BFS(){
        //set the current node to the root, create an empty queue
        GraphMaker.Node current;
        Queue<GraphMaker.Node> queue = new LinkedList<>();

        //mark all nodes unvisited
        for(int row = 0; row< raccoonGame.windowRow; row++) {
            for (int col = 0; col < raccoonGame.windowCol; col++) {
                mapNodeArr[col][row].visited = false;
            }
        }

        //Insert the root into the queue
        queue.add(root);

        //Do the search
        while(!queue.isEmpty()){
            //Let current be the vertex and the front of the queue and remove current from the queue, also mark current as visited
            current = queue.poll();
            current.visited = true;
            //System.out.println("X: "+current.x+" Y: "+current.y+" Reference: "+current);
            //for each unvisited neighbor i of current (do this with 4 directional if statements);
            //insert i at the end of the queue and mark i as visited;
            //make i a child of current

            //if we find the player, back-trace the path and write into an arrayList called path
            if(current == playerNode){
                while(current != null){
                    path.add(current);
                    current = current.parent;
                }
            }

            //left
            if(current.left != null) {
                if (!current.left.visited && current.left.isZero) {
                    queue.add(current.left);
                    current.children.add(current.left);
                    current.left.parent = current;
                }
            }
            //right
            if(current.right != null) {
                if (!current.right.visited && current.right.isZero) {
                    queue.add(current.right);
                    current.children.add(current.right);
                    current.right.parent = current;
                }
            }
            //up
            if(current.up != null) {
                if (!current.up.visited && current.up.isZero) {
                    queue.add(current.up);
                    current.children.add(current.up);
                    current.up.parent = current;
                }
            }
            //down
            if(current.down != null) {
                if (!current.down.visited && current.down.isZero) {
                    queue.add(current.down);
                    current.children.add(current.down);
                    current.down.parent = current;
                }
            }
        }
    }

    //Find which blocks the enemy and player are in
    private void blockUpdate(){
        enemyBlockX = enemy.x / raccoonGame.blockSize;
        enemyBlockY = enemy.y / raccoonGame.blockSize;
        playerBlockX = player.x / raccoonGame.blockSize;
        playerBlockY = player.y / raccoonGame.blockSize;
    }
}
