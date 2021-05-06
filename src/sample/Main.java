package sample;


import javax.swing.JFrame;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        JFrame game=new JFrame();
        GamePlay gamePlay = new GamePlay();
        game.setBounds(10, 10, 800, 600);
        game.setTitle("Tank 2D");
        game.setBackground(Color.gray);
        game.setResizable(false);
        game.toBack();

        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.add(gamePlay);
        game.setVisible(true);
    }
}
