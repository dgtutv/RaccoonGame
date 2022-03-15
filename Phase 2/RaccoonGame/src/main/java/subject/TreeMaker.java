package subject;

import main.RaccoonGame;

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
    }

    //BFS traversal
    void BFS(int root){
        //mark all the nodes as unvisited
        GraphMaker.Node head = GraphMaker.head;
        GraphMaker.Node current = head;
        for(GraphMaker.Node i = head; i != null; i = i.down){
            for(GraphMaker.Node j = i; j != null; j = j.right){
                j.visited = false;
            }
        }

        LinkedList<GraphMaker.Node> queue = new LinkedList<GraphMaker.Node>();

        //Mark the current node as visited and add to the queue

    }

    //Find which blocks the enemy and player are in
    private void blockUpdate(){
        enemyBlockX = enemy.x / raccoonGame.blockSize;
        enemyBlockY = enemy.y / raccoonGame.blockSize;
        playerBlockX = player.x / raccoonGame.blockSize;
        playerBlockY = player.y / raccoonGame.blockSize;
    }
}
