package com.hexgame;

import com.hexgame.model.WorldMap;
import com.hexgame.view.GamePanel;
import javax.swing.JFrame;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame("Battle Rites MVP");

        int hexRadius = 80;
        WorldMap worldMap = new WorldMap(51, 25, hexRadius);
        GamePanel gamePanel = new GamePanel(worldMap, hexRadius);

        window.add(gamePanel);
        window.setUndecorated(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            gd.setFullScreenWindow(window);
        } else {
            window.setExtendedState(JFrame.MAXIMIZED_BOTH);
            window.setVisible(true);
        }
    }
}