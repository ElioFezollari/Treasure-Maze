package launcher;

import launcher.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setVisible(true);
        window.setTitle("Elio Fezollari - Treasure Maze");
        gamePanel.startGameThread();

    }
}
