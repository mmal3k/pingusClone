import view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Test Game");

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        // Enable fullscreen mode
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            frame.setUndecorated(true); // Remove window decorations (title bar, borders)
            gd.setFullScreenWindow(frame); // Set the window to fullscreen
        } else {
            System.err.println("Fullscreen mode not supported");
            frame.setSize(gamePanel.getScreenWidth(), gamePanel.getScreenHeight()); // Fallback to windowed mode
        }

        frame.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();

    }

}