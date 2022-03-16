package subject;

import main.RaccoonGame;

import java.util.ArrayList;
import java.util.LinkedList;

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

    }

    //Update method called by enemy each update
    public void update(){
        //Get block locations of the enemy and player
        blockUpdate();
        //Initialize a new root for current position of the enemy
        root = GraphMaker.find(enemyBlockX, enemyBlockY);
        root.parent = null;
        root.isRoot = true;
        //Generate the tree
        BFS();
        //Find the player's node
        playerNode = GraphMaker.find(playerBlockX, playerBlockY);
        //Find the path from the enemy to the player, and save as an arrayList of nodes
        ArrayList<GraphMaker.Node> path = getPath();

    }
    //function to search the BFS tree for the player and record its path
    public ArrayList<GraphMaker.Node> getPath(){
        ArrayList<GraphMaker.Node> path = new ArrayList<GraphMaker.Node>();
        return path;
    }

    //BFS traversal for enemy pathing. Returns a root to the tree
    public void BFS(){
        //set the current node to the root, create an empty queue
        GraphMaker.Node current = root;
        LinkedList<GraphMaker.Node> queue = new LinkedList<GraphMaker.Node>();

        //mark all nodes unvisited
        for(GraphMaker.Node i = root; i != null; i = i.down){
            for(GraphMaker.Node j = i; j != null; j = j.right){
                j.visited = false;
            }
        }

        //Insert the root into the queue and mark it as visited
        queue.add(root);
        root.visited = true;

        //Do the search
        while(!queue.isEmpty()){
            //Let current be the vertex and the front of the queue and remove current from the queue
            current = queue.poll();
            //for each unvisited neighbor i of current (do this with 4 directional if statements);
            //insert i at the end of the queue and mark i as visited;
            //make i a child of current

            //left
            if(!current.left.visited && current.left.isZero){
                current.children.add(current.left);
                current.left.visited = true;
            }
            //right
            if(!current.right.visited && current.right.isZero){
                current.children.add(current.right);
                current.right.visited = true;
            }
            //up
            if(!current.up.visited && current.up.isZero){
                current.children.add(current.up);
                current.up.visited = true;
            }
            //down
            if(!current.down.visited && current.down.isZero){
                current.children.add(current.down);
                current.down.visited = true;
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
