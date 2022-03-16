package subject;

import main.RaccoonGame;

import java.util.*;

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
        List<GraphMaker.Node> path = BFS();
        //more testing stuff
        printPath(path);
        System.out.println("Generated Tree");
        System.out.println("Path Size: "+path.size());
    }

    //Method to print the path found from the enemy to the player
    private void printPath(List<GraphMaker.Node> path){
        for(int i=0; i<path.size(); i++){
            System.out.print("("+path.get(i).x+" ,"+path.get(i).y+")");
        }
        System.out.println();
    }

    //BFS traversal for enemy pathing. Returns a root to the tree
    private List<GraphMaker.Node> BFS() {

        Queue<GraphMaker.Node> queue = new LinkedList<>();
        List<GraphMaker.Node> path = new ArrayList<>();

        queue.add(root);
        root.visited = true;

        Map<GraphMaker.Node, GraphMaker.Node> parentMap = new HashMap<>();

        GraphMaker.Node current = root;
        parentMap.put(root, null);
        while(!queue.isEmpty()){
            current = queue.poll();
            current.visited = true;

            if(current == playerNode){
                System.out.println("HERE");
                break;
            }

            //Make a list of adjacent nodes to current
            ArrayList<GraphMaker.Node> adjacent = new ArrayList<>();
            adjacent.add(current.left);
            adjacent.add(current.right);
            adjacent.add(current.up);
            adjacent.add(current.down);

            //Iterate through, check if visited, if not add to queue
            for (GraphMaker.Node neighbor : adjacent) {
                if (!neighbor.visited && neighbor.isZero) {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current); // mark current node as parent to neighbor
                }
            }
        }
        GraphMaker.Node currIter = current;
        while (currIter != null) {
            path.add(0, currIter);
            currIter = parentMap.get(currIter);
        }
        return path;
    }


    //Find which blocks the enemy and player are in
    private void blockUpdate(){
        enemyBlockX = enemy.x / raccoonGame.blockSize;
        enemyBlockY = enemy.y / raccoonGame.blockSize;
        playerBlockX = player.x / raccoonGame.blockSize;
        playerBlockY = player.y / raccoonGame.blockSize;
    }
}
