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
    public List<GraphMaker.Node> update(){
        //Get block locations of the enemy and player
        blockUpdate();
        //Initialize a new root for current position of the enemy
        root = GraphMaker.find(enemyBlockX, enemyBlockY);
        //Find the player's Node
        playerNode = GraphMaker.find(playerBlockX, playerBlockY);
        if(!playerNode.nonCollidable){
            switch(player.direction){
                case "up" -> playerBlockY++;
                case "down" -> playerBlockY--;
                case "left" -> playerBlockX++;
                case "right" -> playerBlockX--;
            }
            playerNode = GraphMaker.find(playerBlockX, playerBlockY);
        }
        //reset the path and generate the tree
        return BFS();
    }

    //Method to print the path found from the enemy to the player
    public void print(List<GraphMaker.Node> path){
        for(int i=0; i<path.size(); i++){
            System.out.print("("+path.get(i).x+" ,"+path.get(i).y+")");
        }
        System.out.println();
    }

    //BFS traversal for enemy pathing. Returns a root to the tree
    private List<GraphMaker.Node> BFS() {
        //Make a queue for bfs and a list for path tracking
        Queue<GraphMaker.Node> queue = new LinkedList<>();
        List<GraphMaker.Node> path = new ArrayList<>();

        //mark all nodes unvisited
        for (int row = 0; row < raccoonGame.windowRow; row++) {
            for (int col = 0; col < raccoonGame.windowCol; col++) {
                mapNodeArr[col][row].visited = false;
            }
        }

        //Add the root to the queue and mark it as visited
        queue.add(root);
        root.visited = true;

        //Create a hashmap for tracking the node's parents
        Map<GraphMaker.Node, GraphMaker.Node> parentMap = new HashMap<>();

        //Initialize a current node, and note that the root's parent is null
        GraphMaker.Node current = root;
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

            //Make a list of nodes adjacent to current
            ArrayList<GraphMaker.Node> adjacent = new ArrayList<>();
            adjacent.add(current.left);
            adjacent.add(current.right);
            adjacent.add(current.up);
            adjacent.add(current.down);

            //Iterate through each neighbor checking both if theyre not visited and non-collidable
            for (GraphMaker.Node neighbor : adjacent) {
                //if so, note the parent of the neighbor and add the neighbor to the queue
                if (!neighbor.visited && neighbor.nonCollidable) {
                    queue.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        //Once the loop is complete, we should end at playerNode
        //So, backtrack through it's parents, and record each parent in path
        GraphMaker.Node currIter = current;
        while (currIter != null) {
            path.add(0, currIter);
            currIter = parentMap.get(currIter);
        }
        //return path from enemy to player
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
