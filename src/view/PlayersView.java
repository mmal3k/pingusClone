package view;

import model.entity.Player;

import java.awt.*;
import java.util.ArrayList;

public class PlayersView implements Runnable {
    private int playerNum; // Number of players to add
    private GamePanel gp;  // Reference to GamePanel
    private Thread thread; // Thread for adding players
    private long lastTime; // Tracks the last time a player was added
    private final long addInterval = 2000; // Interval in milliseconds (2 seconds)
    private ArrayList<Player> players ;
    public PlayersView(GamePanel gp, int playerNum) {
        this.gp = gp;
        this.playerNum = playerNum;
        this.players = gp.getPlayers();
    }
    @Override
    public void run() {
        lastTime = System.currentTimeMillis(); // Initialize the last time

        while (playerNum > 0) {
            long currentTime = System.currentTimeMillis(); // Get current time
            if (currentTime - lastTime >= addInterval) { // Check if 5 seconds have passed
                System.out.println("Added player");
                gp.getPlayers().add(new Player(this.gp)); // Add a new player
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
        thread = new Thread(this);
        thread.start();
    }
    public void draw(Graphics2D g2) {
        if (players != null){
            for(Player player : players){
                g2.setColor(Color.GREEN);
                int posX = player.getPlayerX() + player.getSolidArea().x ;
                int posY = player.getPlayerY() + player.getSolidArea().y ;
                g2.fillOval(posX, posY, player.getSolidArea().width  , player.getSolidArea().height);
                g2.fillOval((int)(posX + player.getSolidArea().width / 4), (int)(posY - player.getSolidArea().height + 14 ) , (int)(player.getSolidArea().width / 1.8)  , (int)(player.getSolidArea().width / 1.8));
            }
        }
    }
}
