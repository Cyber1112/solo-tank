package sample;

import java.awt.*;

public class PlayerBullet {
    private double x, y;
    Brick brick;
    public PlayerBullet(double x, double y){
        brick = new Brick();
        this.x = x;
        this.y = y;
    }
    public void move(String dir){
        if(dir.equals("up")){
            for(int i = 0; i < brick.sizeBricks(); i++){
                if(new Rectangle((int)x, (int)y, 10, 10).
                        intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                    y += 3;
                }
            }
            y -= 5;
        }else if(dir.equals("right")){
            for(int i = 0; i < brick.sizeBricks(); i++){
                if(new Rectangle((int)x, (int)y, 10, 10).
                        intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                    x -= 3;
                }
            }
            x += 5;
        }else if(dir.equals("down")){
            for(int i = 0; i < brick.sizeBricks(); i++){
                if(new Rectangle((int)x, (int)y, 10, 10).
                        intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                    y -= 3;
                }
            }
            y += 5;
        }else if(dir.equals("left")){
            for(int i = 0; i < brick.sizeBricks(); i++){
                if(new Rectangle((int)x, (int)y, 10, 10).
                        intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                    x += 3;
                }
            }
            x -= 5;
        }
    }
    public void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillOval((int) x, (int) y, 10, 10);
    }
    public int getBulletX(){
        return (int) x;
    }
    public int getBulletY(){
        return (int) y;
    }

}
