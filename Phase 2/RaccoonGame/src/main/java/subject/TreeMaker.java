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

    //Default constructor
    TreeMaker(Player player, Enemy enemy){
        //variable initialization from input
        this.player = player;
        this.enemy = enemy;
        this.raccoonGame = player.raccoonGame;
        this.graph = raccoonGame.graphMaker;
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
        ArrayList<GraphMaker.Node> path = BFS();
        //more testing stuff
        printPath(path);
        System.out.println("Generated Tree");
        System.out.println("Path Size: "+path.size());
    }

    //Method to print the path found from the enemy to the player
    private void printPath(ArrayList<GraphMaker.Node> path){
        for(int i=0; i<path.size(); i++){
            System.out.print("("+path.get(i).x+" ,"+path.get(i).y+")");
        }
        System.out.println();
    }

    //BFS traversal for enemy pathing. Returns a root to the tree
    private ArrayList<GraphMaker.Node> BFS() {
        //set the current node to the root, create an empty queue, and a list for storing path
        GraphMaker.Node current;
        Queue<GraphMaker.Node> queue = new LinkedList<>();
        ArrayList<GraphMaker.Node> path = new ArrayList<GraphMaker.Node>();

        //mark all nodes unvisited
        for (int row = 0; row < raccoonGame.windowRow; row++) {
            for (int col = 0; col < raccoonGame.windowCol; col++) {
                mapNodeArr[col][row].visited = false;
            }
        }

        //Insert the root into the queue
        queue.add(root);
        while(!queue.isEmpty()) {
            //Let current be the vertex and the front of the queue and remove current from the queue, also mark current as visited
            current = queue.poll();
            path.add(current);
            current.visited = true;

            if (current == playerNode) {
                return path;
            }

            //Make a list of adjacent nodes to current
            ArrayList<GraphMaker.Node> adjacent = new ArrayList<>();
            adjacent.add(current.left);
            adjacent.add(current.right);
            adjacent.add(current.up);
            adjacent.add(current.down);

            //Iterate through, check if visited, if not add to queue
            for (GraphMaker.Node neighbor : adjacent) {
                if (neighbor != null && !neighbor.visited) {
                    queue.add(neighbor);
                }
            }
        }
        //No path found at this point, so return null
        return null;
    }


    //Find which blocks the enemy and player are in
    private void blockUpdate(){
        enemyBlockX = enemy.x / raccoonGame.blockSize;
        enemyBlockY = enemy.y / raccoonGame.blockSize;
        playerBlockX = player.x / raccoonGame.blockSize;
        playerBlockY = player.y / raccoonGame.blockSize;
    }
}
