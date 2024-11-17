package model.tile;

import view.GamePanel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public GamePanel gp ;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileColor();
        loadMap("/maps/map02.txt");
    }
    public void getTileColor (){
        tiles[0] = new Tile();
        tiles[0].color = Color.BLACK;

        tiles[1] = new Tile();
        tiles[1].color = Color.darkGray;
        tiles[1].collision  = true;

        tiles[2] = new Tile();
        tiles[2].color = Color.RED;
        tiles[2].collision = true ;

        tiles[3] = new Tile();
        tiles[3].color = Color.PINK;

        tiles[4] = new Tile();
        tiles[4].color = Color.cyan;

        tiles[5] = new Tile();
        tiles[5].color = Color.MAGENTA;
    }
    public void loadMap(String mapName){
        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col ++;
                }
                if (col == gp.maxScreenCol) {
                    col = 0;
                    row ++;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
