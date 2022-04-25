package Enemy;

import main.RaccoonGame;
import object.Node;

/**
 * GraphMaker uses a map ADT to generate as a graph-theory based graph, upon which the TreeMaker class acts
 */
public class GraphMaker {
    //Needed variables
    static RaccoonGame raccoonGame;
    public int mapBlockArr[][];
    public static Node[][] mapNodeArr;

    //constructor
    public GraphMaker(RaccoonGame raccoonGame) {
        //Set variables based off constructor input
        this.raccoonGame = raccoonGame;
        this.mapBlockArr = raccoonGame.blockManager.mapBlockArr;
        mapNodeArr = new Node[raccoonGame.windowCol][raccoonGame.windowRow];
        graphGenerate();
        graphDirectionFill();
    }

    //Create our linked list based-graph using the mapArray from MapManager.
    //We go from top left to bottom right
    private void graphGenerate() {
        //The loop itself
        for(int currentRow = 0; currentRow< raccoonGame.windowRow; currentRow++){
            for(int currentCol = 0; currentCol< raccoonGame.windowCol; currentCol++){
                //Initialize node and add to array
                Node currentNode = new Node(currentCol, currentRow, raccoonGame.blockManager.blockList.isCollidable(mapBlockArr[currentCol][currentRow]));
                mapNodeArr[currentCol][currentRow] = currentNode;
                //if block is a 0 (i.e, not collidable), then set isZero to true. isZero is false by default
            }
        }
    }

    //Here we assign the direction variables to each node from a completed map
    public void graphDirectionFill(){
        for(int row = 0; row< raccoonGame.windowRow; row++){
            for(int col = 0; col< raccoonGame.windowCol; col++){
                if(0 < row){
                    mapNodeArr[col][row].up = mapNodeArr[col][row-1];
                }
                if(0 < col){
                    mapNodeArr[col][row].left = mapNodeArr[col-1][row];
                }
                if(row < raccoonGame.windowRow-1) {
                    mapNodeArr[col][row].down = mapNodeArr[col][row+1];
                }
                if(col < raccoonGame.windowCol-1){
                    mapNodeArr[col][row].right = mapNodeArr[col+1][row];
                }
            }
        }
    }

    //Method used to find a node, returns such node. Returns null if no such node was found
    public static Node find(int x, int y) {
        try{
            return mapNodeArr[x][y];
        }
        catch(Exception e){
            return null;
        }
    }

    //Print the map
    public String print() {
        String printString = "";

        for(int row = 0; row< raccoonGame.windowRow; row++){
            printString+="\n";
            for(int col = 0; col< raccoonGame.windowCol; col++){
                if(!mapNodeArr[col][row].collidable){
                    printString+= "0 ";
                } else {
                    printString+= "! ";
                }
            }
        }
        return printString;
    }
}
