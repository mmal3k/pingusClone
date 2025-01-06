package view;

import model.Player;

import java.awt.*;
import java.util.ArrayList;

public class PlayersView implements Runnable {

    private int playerNum; // Number of players to add
    private final int playerNumber;
    private GamePanel gp;  // Reference to GamePanel
    private long lastTime; // Tracks the last time a player was added
    private final long addInterval = 2000; // Interval in milliseconds (2 seconds)
    public ArrayList<Player> players;
    public int nbDiedPlayers = 0;
    private Thread thread ;
    private volatile boolean running = false; // Flag to control the thread
    private int i ;

    public PlayersView(GamePanel gp, int playerNum) {
        this.gp = gp;
        this.playerNum = playerNum;
        this.playerNumber = playerNum;
        this.players = gp.getPlayers();
    }

    @Override
    public void run() {
        running = true; // Mark the thread as running
        lastTime = System.currentTimeMillis(); // Initialize the last time
        i = 0;
        while (running && playerNum > 0) { // Check the running flag
            long currentTime = System.currentTimeMillis(); // Get current time
            if (currentTime - lastTime >= addInterval) { // Check if 2 seconds have passed
                gp.getPlayers().add(new Player(this.gp, i)); // Add a new player
                i++;
                playerNum--; // Decrease the number of players to add
                lastTime = currentTime; // Reset the last time
            }
            try {
                Thread.sleep(100); // Prevent busy-waiting, sleep for a short time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startAddThread() {
        playerNum = playerNumber ;
        thread = new Thread(this);
        thread.start();
    }

    public void stopAddThread() {
        running = false; // Stop the thread

    }


    public void restartPlayers() {
        nbDiedPlayers = 0;
        i = 0;
        playerNum = playerNumber;
        players.removeAll(players);
    }


    public void draw(Graphics2D g2) {
        for (Player player : players) {
            if (player == null) continue;
            // Set player color (Bright Yellow)
//            g2.setColor(new Color(255, 223, 0));
            g2.setColor(player.getRole().getColor());

            // Get player position and size
            int posX = player.getPlayerX() + player.getSolidArea().x;
            int posY = player.getPlayerY() + player.getSolidArea().y;
            int circleWidth = player.getSolidArea().width;
            int circleHeight = player.getSolidArea().height;

            // Draw the bottom circle (body)
            g2.fillOval(posX, posY, circleWidth, circleHeight);

            // Draw the top circle (head)
            int headPosX = (int) (posX + circleWidth / 4);
            int headPosY = (int) (posY - circleHeight + 14);
            int headWidth = (int) (circleWidth / 1.8);
            g2.fillOval(headPosX, headPosY, headWidth, headWidth);

            // Draw the nose (triangle)
//            g2.setColor(new Color(255, 223, 0));
            g2.setColor(player.getRole().getColor());
            int noseX = headPosX + (headWidth / 2); // X-coordinate for nose center
            int noseY = headPosY + (headWidth / 2); // Y-coordinate for nose center
            int[] xPoints;
            // Define the triangle vertices for the nose
            if (player.getDirection().equals("left")) {
                xPoints = new int[]{noseX, noseX, noseX - 15};
            } else {
                xPoints = new int[]{noseX, noseX, noseX + 15}; // Centered vertically
            }
            int[] yPoints = {noseY - 5, noseY + 5, noseY};

            // Draw the nose as a filled polygon
            g2.fillPolygon(xPoints, yPoints, 3);
        }
    }

    public int getPlayerNumber() {
        return playerNumber;
    }
}