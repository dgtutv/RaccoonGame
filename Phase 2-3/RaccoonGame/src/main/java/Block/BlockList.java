package Block;

import object.GeneralDrawableObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class BlockList{
    private GeneralDrawableObject[] blocks;
    public BlockList(){
        initializeList(11);
        fillList();
    }
    private void fillList(){
        try {
            blocks[0].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wood_floor.png")));
            blocks[1].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_top.png")));
            blocks[1].collidable = true;
            blocks[2].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall.png")));
            blocks[2].collidable = true;
            blocks[3].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/exit.png")));
            blocks[3].collidable = true;
            blocks[4].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_right.png")));
            blocks[4].collidable = true;
            blocks[5].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/wall_single.png")));
            blocks[5].collidable = true;
            blocks[6].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/chair.png")));
            blocks[6].collidable = true;
            blocks[7].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/vending_top.png")));
            blocks[7].collidable = true;
            blocks[8].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/vending_bottom.png")));
            blocks[8].collidable = true;
            blocks[9].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/table_left.png")));
            blocks[9].collidable = true;
            blocks[10].Image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/blocks/table_right.png")));
            blocks[10].collidable = true;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    private void initializeList(int max){
        blocks = new GeneralDrawableObject[max];
        for(int i=0; i<max; i++){
            blocks[i] = new GeneralDrawableObject();
        }
    }
    public Image getImage(int i){
        return blocks[i].Image;
    }
    public boolean isCollidable(int i){
        return blocks[i].collidable;
    }
    public int size(){
        return blocks.length;
    }
}
