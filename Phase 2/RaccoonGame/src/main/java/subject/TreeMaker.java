package subject;

import main.RaccoonGame;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        //Generate the tree
        BFS();
        //System.out.println("Generated Tree");
        //Find the player's node
        playerNode = GraphMaker.find(playerBlockX, playerBlockY);
        //System.out.println("Enemy X: "+root.x+"\nEnemy Y: "+root.y);
        //System.out.println("Player X: "+playerNode.x+"\nPlayer Y: "+playerNode.y);
        //System.out.println("Player: "+playerNode);
        //Find the path from the enemy to the player, and save as an arrayList of nodes
        path.clear();
        getPath(root);
        //System.out.println("Generated Path");
        //System.out.println("Path Size: "+path.size());
        //printPath();

    }

    //Method to print the path found from the enemy to the player
    private void printPath(){
        for(int i=path.size()-1; i>-1; i--){
            System.out.print("("+path.get(i).x+" ,"+path.get(i).y+")");
        }
        System.out.println();
    }
    //Recursive method to search the BFS tree for the player and record its path
    private Boolean getPath(GraphMaker.Node current){
        if(current == playerNode){
            path.add(current);
            return true;
        }
        else{
            for(int i=0; i < current.children.size(); i++){
                if(getPath(current.children.get(i))){
                    //System.out.println("HERE");
                    path.add(current);
                    return true;
                }
            }
            return false;
        }
    }

    //BFS traversal for enemy pathing. Returns a root to the tree
    private void BFS(){
        //set the current node to the root, create an empty queue
        GraphMaker.Node current = root;
        Queue<GraphMaker.Node> queue = new LinkedList<>();

        //mark all nodes unvisited
        for(GraphMaker.Node i = current; i != null; i = i.down){
            for(GraphMaker.Node j = i; j != null; j = j.right){
                j.visited = false;
            }
        }

        //Insert the root into the queue
        queue.add(current);
        current.visited = true;

        //Do the search
        while(!queue.isEmpty()){
            //Let current be the vertex and the front of the queue and remove current from the queue, also mark current as visited
            current = queue.poll();
            current.visited = true;
            //System.out.println("X: "+current.x+" Y: "+current.y+" Reference: "+current);
            //for each unvisited neighbor i of current (do this with 4 directional if statements);
            //insert i at the end of the queue and mark i as visited;
            //make i a child of current

            //left
            if(current.left != null) {
                if (!current.left.visited && current.left.isZero) {
                    queue.add(current.left);
                    current.children.add(current.left);
                }
            }
            //right
            if(current.right != null) {
                if (!current.right.visited && current.right.isZero) {
                    queue.add(current.right);
                    current.children.add(current.right);
                }
            }
            //up
            if(current.up != null) {
                if (!current.up.visited && current.up.isZero) {
                    queue.add(current.up);
                    current.children.add(current.up);
                }
            }
            //down
            if(current.down != null) {
                if (!current.down.visited && current.down.isZero) {
                    queue.add(current.down);
                    current.children.add(current.down);
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
