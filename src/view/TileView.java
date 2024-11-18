package view;

import model.tile.Tile;

import java.awt.*;

public class TileView {

    private int[][] mapTileNum;
    private GamePanel gp ;
    private Tile[] tiles ;


    public TileView(GamePanel gp){
        this.gp = gp;
        this.mapTileNum = gp.getTileM().getMapTileNum();
        this.tiles = gp.getTileM().getTiles();
    }
    public void draw(Graphics2D g2){
        int worldCol = 0 ;
        int worldRow = 0 ;
        while (worldCol < gp.getMaxScreenCol() && worldRow < gp.getMaxScreenRow()){
            int tileNum = mapTileNum[worldCol][worldRow ] ;

            int worldX = worldCol  * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();

            g2.setColor(tiles[tileNum].getColor());
            g2.fillRect(worldX , worldY , gp.getTileSize(), gp.getTileSize());
            worldCol ++ ;
            if (worldCol == gp.getMaxScreenCol()) {
                worldCol = 0;
                worldRow ++ ;
            }
        }
    }
}
