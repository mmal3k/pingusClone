package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

import java.awt.*;

public class Bombeur implements Role {
    private int pas; // Tracks the steps taken before exploding
    private GamePanel gp;
    private NormalRole normal;

    public Bombeur(GamePanel gp) {
        this.pas = 0;
        this.gp = gp;
        this.normal = new NormalRole();
    }

    /**
     * Destroys a tile at the specified position if it is destructible.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     */
    private void boom(int x, int y) {
        if (gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x][y]].isDestructible()) {
            gp.getTileM().getMapTileNum()[x][y] = 0; // Destroy the tile
        }
    }

    @Override
    public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
        // Delegate normal movement to the NormalRole
        normal.move(gp, player, cChecker);

        // Increment the step counter
        pas += player.getSpeed();

        // Check if it's time to explode
        if (pas == gp.getTileSize() * 2) {
            gp.playSE(0); // Play explosion sound effect
            try {
                Thread.sleep(200); // Pause for effect
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Remove the player from the game
            int playerInd = gp.getPlayers().indexOf(player);
            gp.getPlayers().set(playerInd, null);
            System.out.println("BOOM!!!");

            // Calculate the explosion center
            int mapx = (player.getPlayerX() + gp.halfTileSize) / gp.getTileSize();
            int mapy = (player.getPlayerY() + gp.halfTileSize) / gp.getTileSize();

            // Handle the explosion
            handleExplosion(mapx, mapy);
        }
    }

    /**
     * Handles the explosion logic for the Bombeur role.
     *
     * @param centerX The x-coordinate of the explosion center.
     * @param centerY The y-coordinate of the explosion center.
     */
    private void handleExplosion(int centerX, int centerY) {
        // Define the explosion pattern (relative coordinates)
        int[][] explosionPattern = {
                {0, -1}, {0, -2}, // Up
                {0, 1}, {0, 2},   // Down
                {-1, 0}, {-2, 0}, // Left
                {1, 0}, {2, 0},   // Right
                {-1, -1}, {1, -1}, // Diagonal up-left and up-right
                {-1, 1}, {1, 1}    // Diagonal down-left and down-right
        };

        // Iterate through the explosion pattern
        for (int[] offset : explosionPattern) {
            int x = centerX + offset[0];
            int y = centerY + offset[1];

            // Check if the tile is within bounds
            if (x >= 0 && x < gp.getMaxScreenCol() && y >= 0 && y < gp.getMaxScreenRow()) {
                boom(x, y); // Destroy the tile if it's destructible
            }
        }
    }

    @Override
    public Color getColor() {
        return Color.RED;
    }
}