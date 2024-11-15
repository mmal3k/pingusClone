package controller;

import model.entity.Entity;
import model.tile.TileManager;
import view.GamePanel;

import java.sql.SQLOutput;

public class CollisonChecker {
    GamePanel gp ;
    TileManager tileM;
    public CollisonChecker(GamePanel gp ){
        this.gp = gp ;
    }
    public void checkTile(Entity entity){

        int entityLeftWorldX = (entity.getPlayerX()) + entity.solidArea.x;
        int entityRightWorldX = (entity.getPlayerX()) + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY =  (entity.getPlayerY()) + entity.solidArea.y;
        int entityBottomWorldY = (entity.getPlayerY()) + entity.solidArea.y + entity.solidArea.height;



        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol  = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1 ;
        int tileNum2 ;

        if (entity.isFalling) {
            entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
                entity.isFalling = false;
            }else{
                entity.isFalling = true;
            }
        } else {
            if (entity.collisonOn){

                switch (entity.direction) {
                    case "left" :
                        tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow -1] ;
//                        System.out.println(gp.tileM.tiles[tileNum1].collision);
                        if (gp.tileM.tiles[tileNum1].collision) {
                            entity.canGoUp = false ;
                        }else {
                            entity.canGoUp = true;
                        }

                        break;

                    case "right" :
                        tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow -1] ;

                        if (gp.tileM.tiles[tileNum1].collision) {
                            entity.canGoUp = false ;
                        }else {
                            entity.canGoUp = true;
                        }
                        break;

                }
            }
            if (!entity.canGoUp){
                switch (entity.direction) {
                    case "right":
                        entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize;
                        tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                        tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                        if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
                            entity.collisonOn = true;
                        } else {
                            entity.collisonOn = false;
                        }
                        break;
                    case "left":
                        entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                        tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                        tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                        if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
                            entity.collisonOn = true;
                        } else {
                            entity.collisonOn = false;
                        }
                        break;
                }
            }

        }
    }

    public boolean canGoUp(Entity entity){
        int entityLeftWorldX = (entity.getPlayerX()) + entity.solidArea.x;
        int entityRightWorldX = (entity.getPlayerX()) + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY =  (entity.getPlayerY()) + entity.solidArea.y;
        int entityBottomWorldY = (entity.getPlayerY()) + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol  = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        switch(entity.direction) {
            case  "left" :
                break;
            case  "right" :
                entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize ;
                break;
        }
        return  true ;
    }

}
