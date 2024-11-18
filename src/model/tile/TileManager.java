package model.tile;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import view.GamePanel;

public class TileManager {
    private GamePanel gp ;
    private Tile[] tiles;
    private int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[10];
        mapTileNum = new int[gp.getMaxScreenCol()][gp.getMaxScreenRow()];
        getTileColor();
        loadMap("/maps/map02.txt");
    }

    public void getTileColor (){
        tiles[0] = new Tile();
        tiles[0].setColor(Color.BLACK);

        tiles[1] = new Tile();
        tiles[1].setColor(Color.darkGray);
        tiles[1].setCollision(true);

        tiles[2] = new Tile();
        tiles[2].setColor(Color.RED);
        tiles[2].setCollision(true);

        tiles[3] = new Tile();
        tiles[3].setColor(Color.PINK);


        tiles[4] = new Tile();
        tiles[4].setColor(Color.cyan);

        tiles[5] = new Tile();
        tiles[5].setColor(Color.MAGENTA);
    }


    public void loadMap(String mapName){

        try {
            InputStream is = getClass().getResourceAsStream(mapName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.getMaxScreenCol() && row < gp.getMaxScreenRow()) {
                String line = br.readLine();
                while (col < gp.getMaxScreenCol()){
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]) ;
                    mapTileNum[col][row] = num;
                    col ++ ;
                }
                if (col == gp.getMaxScreenCol()) {
                    col = 0;
                    row ++;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public void setMapTileNum(int[][] mapTileNum) {
        this.mapTileNum = mapTileNum;
    }
}
