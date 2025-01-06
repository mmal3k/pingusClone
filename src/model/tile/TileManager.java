package model.tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import view.GamePanel;

public class TileManager {
    private GamePanel gp;
    private Tile[] tiles;
    public String map;
    private int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];
        getTileColor();
    }

    public void getTileColor() {
        // Tile 0: Background (Dark Gray)
        tiles[0] = new Tile();
        tiles[0].setColor(new Color(30, 30, 30)); // Dark gray

        // Tile 1: Obstacle (Dark Blue)
        tiles[1] = new Tile();
        tiles[1].setColor(new Color(0, 51, 102)); // Dark blue
        tiles[1].setCollision(true);
        tiles[1].setDestructible(true);

        // Tile 2: Ground (Dark Green)
        tiles[2] = new Tile();
        tiles[2].setColor(new Color(87, 24, 7)); // Dark green
        tiles[2].setCollision(true);

        tiles[3] = new Tile();
//        tiles[3].setColor(new Color(87, 24, 7)); //
        tiles[3].setColor(new Color(0, 102, 51)); //

        tiles[3].setCollision(true);


    }

    public void loadMap(String mapName) {
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
                String line = br.readLine();

                while (col < gp.getMaxScreenCol()) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.getMaxScreenCol()) {
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public void setMap(String map) {
        this.map = map;
        this.loadMap("/maps/" + map + ".txt");
    }
}