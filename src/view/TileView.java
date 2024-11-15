package view;

import model.tile.Tile;

import java.awt.*;

public class TileView {

    public int mapTileNum [][];
    GamePanel gp ;
    Tile[] tiles ;


    public TileView(GamePanel gp){
        this.gp = gp;
        this.mapTileNum = gp.tileM.mapTileNum;
        this.tiles = gp.tileM.tiles;
    }
    public void draw(Graphics2D g2){
        int worldCol = 0 ;
        int worldRow = 0 ;
        while (worldCol < gp.maxScreenCol  && worldRow < gp.maxScreenRow ){
            int tileNum = mapTileNum[worldCol][worldRow ] ;

            int worldX = worldCol  * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            g2.setColor(tiles[tileNum].color);
            g2.fillRect(worldX , worldY , gp.tileSize , gp.tileSize);
            worldCol ++ ;
            if (worldCol == gp.maxScreenCol ) {
                worldCol = 0;
                worldRow ++ ;
            }
        }
    }
}
