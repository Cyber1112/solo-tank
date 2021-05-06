package sample;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Arrays;

import javax.swing.ImageIcon;
public class Brick {
    int[] bricksXPos = {50, 110};
    int[] bricksYPos = {300, 300};
    int[] brickON = new int[bricksXPos.length];
    private final ImageIcon breakBrickImage;
    private final ImageIcon solidBrickImage;
    private final ImageIcon waterImage;
    private final ImageIcon treeImage;
    int[] solidBricksX = {180, 240};
    int[] solidBricksY = {300, 300};
    int[] treeX = {310, 370};
    int[] treeY = {300, 300};
    int[] waterX = {440, 500};
    int[] waterY = {300, 300};
    int[] healthBreakableBrick = {4, 4};

    public Brick(){
        breakBrickImage = new ImageIcon("Battle_City_bricks.png");
        solidBrickImage = new ImageIcon("Battle_City_wall.png");
        waterImage = new ImageIcon("water.png");
        treeImage = new ImageIcon("Battle_City_trees.png");
        Arrays.fill(brickON, 1);
    }
    public void draw(Component c, Graphics g){
        for(int i = 0; i < bricksXPos.length; i++){
            if(brickON[i] == 1){
                breakBrickImage.paintIcon(c, g, bricksXPos[i],bricksYPos[i]);
            }
        }
    }
    public void drawSolidBricks(Component c, Graphics g){
        for(int i=0; i< solidBricksX.length;i++) {
            solidBrickImage.paintIcon(c, g, solidBricksX[i],solidBricksY[i]);
        }
    }
    public void drawTreeBricks(Component c, Graphics g){
        for(int i=0; i< treeX.length;i++) {
            treeImage.paintIcon(c, g, treeX[i],treeY[i]);
        }
    }
    public void drawWater(Component c, Graphics g){
        for(int i=0; i< waterX.length;i++) {
            waterImage.paintIcon(c, g, waterX[i], waterY[i]);
        }
    }

    public boolean checkBreakableCollision(int x, int y)
    {
        boolean collided = false;
        for(int i=0; i< brickON.length;i++) {
            if(brickON[i] == 1){

                if(new Rectangle(x, y, 10, 10).
                        intersects(new Rectangle(bricksXPos[i], bricksYPos[i], 60, 60))) {
//                    healthBreakableBrick = healthBreakableBrick - 1;
//                    if(healthBreakableBrick == 0 || healthBreakableBrick == -4){
//                        brickON[i] = 0;
//                    }
                    healthBreakableBrick[i] -= 1;
                    System.out.println(healthBreakableBrick[i]);
                    if(healthBreakableBrick[i] == 0){
                        brickON[i] = 0;
                    }

                    collided = true;
                    break;
                }
            }
        }
        return collided;
    }
    public boolean checkSolidCollision(int x, int y){
        boolean collided = false;
        for(int i=0; i< solidBricksX.length;i++)
        {
            if(new Rectangle(x, y, 10, 10).
                    intersects(new Rectangle(solidBricksX[i], solidBricksY[i], 60, 60)))
            {
                collided = true;
                break;
            }
        }
        return collided;
    }
    public int sizeBricks(){
        return bricksXPos.length;
    }
}
