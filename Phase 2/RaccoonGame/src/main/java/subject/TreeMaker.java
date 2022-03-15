package subject;

import main.RaccoonGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TreeMaker {
    //Some needed variables
    Player player;
    Enemy enemy;
    int enemyBlockX, enemyBlockY, playerBlockX, playerBlockY;
    RaccoonGame raccoonGame;
    GraphMaker graph;

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
        blockUpdate();
        GraphMaker.Node enemyNode = new GraphMaker.Node(enemyBlockX, enemyBlockY);
        ArrayList<GraphMaker.Node> path = BFS(enemyNode);
        for(int i=0; i<path.size(); i++){
            System.out.print("("+path.get(i).blockX+", "+path.get(i).blockY+")");
        }
        System.out.print("\n");
        enemy.targetX = path.get(0).blockX;
        enemy.targetY = path.get(0).blockY;
    }

    //BFS traversal for enemy pathing.
    public ArrayList<GraphMaker.Node> BFS(GraphMaker.Node root){
        //mark all the nodes as unvisited
        GraphMaker.Node current = root;
        ArrayList<GraphMaker.Node> path = new ArrayList<GraphMaker.Node>();
        for(GraphMaker.Node i = root; i != null; i = i.down){
            for(GraphMaker.Node j = i; j != null; j = j.right){
                j.visited = false;
            }
        }

        LinkedList<GraphMaker.Node> queue = new LinkedList<GraphMaker.Node>();

        //Mark the current node as visited and add to the queue
        current.visited = true;
        queue.add(current);

        while(queue.size() != 0){
            //dequeue a vertex from queue and print it
            root = queue.poll();
            path.add(root);
            //visit all unvisited neighbors, add them to the queue
            if(current.left != null && !current.left.visited && current.left.isZero){
                queue.add(current.left);
                current.left.visited = true;
            }
            if(current.right != null && !current.right.visited && current.right.isZero){
                queue.add(current.right);
                current.right.visited = true;
            }
            if(current.up != null && !current.up.visited && current.up.isZero){
                queue.add(current.up);
                current.up.visited = true;
            }
            if(current.down != null && !current.down.visited && current.down.isZero){
                queue.add(current.down);
                current.down.visited = true;
            }
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
