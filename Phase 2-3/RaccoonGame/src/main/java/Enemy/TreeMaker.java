package Enemy;

import main.RaccoonGame;
import object.Node;
import subject.Enemy;
import subject.Player;

import java.util.*;

import static Enemy.GraphMaker.mapNodeArr;

public class TreeMaker {
    //Some needed variables
    Player player;
    Enemy enemy;
    int enemyBlockX, enemyBlockY, playerBlockX, playerBlockY;
    RaccoonGame raccoonGame;
    public GraphMaker graph;
    public Node root;
    public Node playerNode;
    public List<Node> tree;

    //Default constructor
    public TreeMaker(Player player, Enemy enemy){
        //variable initialization from input
        this.player = player;
        this.enemy = enemy;
        this.raccoonGame = player.raccoonGame;
        this.graph = raccoonGame.graphMaker;
    }

    //Update method called by enemy each update
    public List<Node> update(){
        //Get Block locations of the enemy and player
        blockUpdate();
        //Initialize a new root for current position of the enemy
        root = GraphMaker.find(enemyBlockX, enemyBlockY);
        //Find the player's Node
        playerNode = GraphMaker.find(playerBlockX, playerBlockY);
        //reset the path and generate the tree
        tree = BFS();
        return tree;
    }

    //Method to print the path found from the enemy to the player
    public String print(List<Node> path){
        String printString = "";
        for(int i=0; i<path.size(); i++){
            printString += "("+path.get(i).x+" ,"+path.get(i).y+")";
        }
        return printString;
    }

    //BFS traversal for enemy pathing. Returns a root to the tree
    private List<Node> BFS() {
        //Make a queue for bfs and a list for path tracking
        Queue<Node> queue = new LinkedList<>();
        List<Node> path = new ArrayList<>();

        //mark all nodes unvisited, and set their direction to ""
        for (int row = 0; row < raccoonGame.windowRow; row++) {
            for (int col = 0; col < raccoonGame.windowCol; col++) {
                mapNodeArr[col][row].visited = false;
                mapNodeArr[col][row].direction = "";
            }
        }

        //Add the root to the queue and mark it as visited
        queue.add(root);
        root.visited = true;

        //Create a hashmap for tracking the node's parents
        Map<Node, Node> parentMap = new HashMap<>();

        //Initialize a current node, and note that the root's parent is null
        Node current = root;
        parentMap.put(root, null);

        //Main BFS loop
        while(!queue.isEmpty()){
            //pop current from the queue and mark it as visited
            current = queue.poll();
            current.visited = true;

            //If this node is the playerNode we break to stop noting parents
            if(current == playerNode){
                break;
            }

            //Make a list of nodes adjacent to current, and set each direction
            ArrayList<Node> adjacent = new ArrayList<>();
            adjacent.add(current.left);
            adjacent.add(current.right);
            adjacent.add(current.up);
            adjacent.add(current.down);
            current.left.direction = "left";
            current.right.direction = "right";
            current.up.direction = "down";
            current.down.direction = "up";

            //Iterate through each neighbor checking both if theyre not visited and non-collidable
            for (Node neighbor : adjacent) {
                //if so, note the parent of the neighbor and add the neighbor to the queue
                if (!neighbor.visited && !neighbor.collidable) {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        //Once the loop is complete, we should end at playerNode
        //So, backtrack through it's parents, and record each parent in path
        Node currIter = current;
        while (currIter != null) {
            path.add(0, currIter);
            currIter = parentMap.get(currIter);
        }
        //return path from enemy to player
        return path;
    }


    //Find which blocks the enemy and player are in
    public void blockUpdate(){
        enemyBlockX = enemy.getX() / raccoonGame.blockSize;
        enemyBlockY = enemy.getY() / raccoonGame.blockSize;
        playerBlockX = player.getX() / raccoonGame.blockSize;
        playerBlockY = player.getY() / raccoonGame.blockSize;
    }
}
