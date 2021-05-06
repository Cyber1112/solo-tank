package sample;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener  {
    //Player's image
    private ImageIcon player;
    //set bricks on map
    Brick brick;
    //Player's position
    private int playerX = 200;
    private int playerY = 500;
    //Player's movements direction
    private boolean playerRight = false;
    private boolean playerLeft = false;
    private boolean playerDown = false;
    private boolean playerUp = true;
    //Set movements
    private final PlayerListener playerListener;
    //FPS
    private final Timer timer;
    private final int delay=8;

    private final boolean play = true;
    //Bullet
    private PlayerBullet playerBullet = null;
    private boolean playerShoot = false;
    private String bulletShootDir = "";
    GamePlay(){
        brick = new Brick();
        playerListener = new PlayerListener();
        setFocusable(true);
        addKeyListener(playerListener);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);
        //Breakable Bricks
        brick.draw(this, g);
        //Solid bricks
        brick.drawSolidBricks(this, g);
        //Tree
        brick.drawTreeBricks(this, g);

        //Water
        brick.drawWater(this, g);
        if(play){
            //Draw players
            if(playerUp){
                player = new ImageIcon("player_tank_up.png");
            }else if(playerDown){
                player = new ImageIcon("player_tank_down.png");
            }else if(playerRight){
                player = new ImageIcon("player_tank_right.png");
            }else if(playerLeft){
                player = new ImageIcon("player_tank_left.png");
            }else{
                player = new ImageIcon("");
            }
            player.paintIcon(this, g, playerX, playerY);

            //Player Shoot
            if(playerBullet != null && playerShoot){
                if(bulletShootDir.equals("")){
                    if(playerUp){
                        bulletShootDir = "up";
                    }else if(playerRight){
                        bulletShootDir = "right";
                    }else if(playerDown){
                        bulletShootDir = "down";
                    }
                    else if(playerLeft){
                        bulletShootDir = "left";
                    }
                }else{
                    playerBullet.move(bulletShootDir);
                    playerBullet.draw(g);
                }

                if(playerBullet.getBulletX() < 1 ||
                    playerBullet.getBulletY() < 1 || playerBullet.getBulletX() > 800
                        || playerBullet.getBulletY() > 600){
                    playerBullet = null;
                    playerShoot = false;
                    bulletShootDir = "";
                }else if(brick.checkBreakableCollision(playerBullet.getBulletX(), playerBullet.getBulletY())){
                    playerBullet = null;
                    playerShoot = false;
                    bulletShootDir = "";
                }else if(brick.checkSolidCollision(playerBullet.getBulletX(), playerBullet.getBulletY())){
                    playerBullet = null;
                    playerShoot = false;
                    bulletShootDir = "";
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        repaint();
    }
    private class PlayerListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {

            if(e.getKeyCode()== KeyEvent.VK_W)
            {
                playerRight = false;
                playerLeft = false;
                playerDown = false;
                playerUp = true;

                if(!(playerY < 10)){
                    for(int i = 0; i < brick.sizeBricks(); i++){
                        if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(
                                        new Rectangle(brick.bricksXPos[i] + 5, brick.bricksYPos[i] + 50, 50, 10))
                            || new Rectangle(playerX, playerY, 50, 50).intersects(
                                new Rectangle(brick.solidBricksX[i] + 5, brick.solidBricksY[i] + 50, 50, 10)
                        )){
                            System.out.println("Intersected");
                            playerY += 10;
                        }else if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                            playerY += 5;
                            playerUp = false;
                        }else if(new Rectangle(playerX, playerY, 50, 50).
                                intersects(new Rectangle(brick.waterX[i] + 5, brick.waterY[i] + 50, 50, 10))){
                            playerY+=10;
                        }
                    }

                    playerY-=10;
                }


            }
            if(e.getKeyCode()== KeyEvent.VK_A)
            {
                playerRight = false;
                playerLeft = true;
                playerDown = false;
                playerUp = false;

                if(!(playerX < 10)){
                    for(int i = 0; i < brick.sizeBricks(); i++){
                        if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(
                                        new Rectangle(brick.bricksXPos[i] + 50, brick.bricksYPos[i] + 5, 10, 50))
                                || new Rectangle(playerX, playerY, 50, 50).intersects(
                                new Rectangle(brick.solidBricksX[i] + 50, brick.solidBricksY[i] + 5, 10, 50)
                        )){
                            System.out.println("Intersected");
                            playerX += 10;
                        }else if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                            playerX += 5;
                            playerLeft = false;
                        }else if(new Rectangle(playerX, playerY, 50, 50).
                                intersects(new Rectangle(brick.waterX[i] + 50, brick.waterY[i] + 5, 10, 50))){
                            playerX += 10;
                        }
                    }
                    playerX-=10;
                }

            }
            if(e.getKeyCode()== KeyEvent.VK_S)
            {
                playerRight = false;
                playerLeft = false;
                playerDown = true;
                playerUp = false;

                if(!(playerY > 550)){
                    for(int i = 0; i < brick.sizeBricks(); i++){
                        if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(
                                        new Rectangle(brick.bricksXPos[i] + 5, brick.bricksYPos[i], 50, 10))
                                || new Rectangle(playerX, playerY, 50, 50).intersects(
                                new Rectangle(brick.solidBricksX[i] + 5, brick.solidBricksY[i], 50, 10)
                        )){
                            System.out.println("Intersected");
                            playerY -= 10;
                        }else if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                            playerY -= 5;
                            playerDown = false;
                        }else if(new Rectangle(playerX, playerY, 50, 50).
                                intersects(new Rectangle(brick.waterX[i] + 5, brick.waterY[i], 50, 10))){
                            playerY -= 10;
                        }
                    }
                    playerY+=10;
                }

            }
            if(e.getKeyCode()== KeyEvent.VK_D)
            {
                playerRight = true;
                playerLeft = false;
                playerDown = false;
                playerUp = false;

                if(!(playerX > 740)){
                    for(int i = 0; i < brick.sizeBricks(); i++){
                        if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(
                                        new Rectangle(brick.bricksXPos[i], brick.bricksYPos[i] + 5, 10, 50))
                                || new Rectangle(playerX, playerY, 50, 50).intersects(
                                new Rectangle(brick.solidBricksX[i], brick.solidBricksY[i] + 5, 10, 50)
                        )){
                            System.out.println("Intersected");
                            playerX -= 10;
                        }else if(new Rectangle(playerX, playerY, 50, 50)
                                .intersects(new Rectangle(brick.treeX[i], brick.treeY[i], 60, 60))){
                            playerX -= 5;
                            playerRight = false;
                        }else if(new Rectangle(playerX, playerY, 50, 50).
                                intersects(new Rectangle(brick.waterX[i], brick.waterY[i]+5, 10, 50))){
                            playerX -= 10;
                        }
                    }
                    playerX+=10;
                }

            }
            if(e.getKeyCode()== KeyEvent.VK_K){
                if(!playerShoot){
                    if(playerUp){
                        playerBullet = new PlayerBullet(playerX + 20, playerY);
                    }else if(playerRight){
                        playerBullet = new PlayerBullet(playerX + 40, playerY+20);
                    }else if(playerDown){
                        playerBullet = new PlayerBullet(playerX + 20, playerY+40);
                    }
                    else if(playerLeft){
                        playerBullet = new PlayerBullet(playerX, playerY+20);
                    }
                    playerShoot = true;
                }
            }
        }
    }
}
