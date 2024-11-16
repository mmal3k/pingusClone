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
    public void checkTile (Entity entity){

        canGoUp(entity);
        if (entity.canGoUp){
            return;
        }

        canFall(entity);
        if (entity.isFalling){
            return;
        }

        checkCollision(entity);
        if (entity.collisonOn){
            return;
        }
    }

    public void canGoUp(Entity entity){
        int leftPosX = entity.getPlayerX() + entity.solidArea.x;
        int rightPosX = entity.getPlayerX() + entity.solidArea.x + entity.solidArea.width ;
        int topPosY = entity.getPlayerY() + entity.solidArea.y;
        int bottomPosY = entity.getPlayerY() + entity.solidArea.y + entity.solidArea.height ;

        int leftCol = leftPosX / gp.tileSize ;
        int rightCol = rightPosX / gp.tileSize ;
        int topRow = topPosY / gp.tileSize ;
        int bottomRow = bottomPosY / gp.tileSize ;

        int tileNum1 , tileNum2 ;
        switch (entity.direction) {
            case "left" :
                int tileNum0 = gp.tileM.mapTileNum[leftCol][bottomRow - 1] ;
                if (!gp.tileM.tiles[tileNum0].collision){
                    leftCol =( leftPosX - entity.speed) / gp.tileSize ;

                    tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                    tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                    if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                        int tileNum3 = gp.tileM.mapTileNum[leftCol ][bottomRow - 1] ;
                        if (!gp.tileM.tiles[tileNum3].collision) {
                            entity.canGoUp = true ;
                            return;
                        }
                    }
                }
                entity.canGoUp = false;
                break ;
            case "right" :
                int tileNum = gp.tileM.mapTileNum[rightCol][bottomRow - 1] ;
                if (!gp.tileM.tiles[tileNum].collision){
                    rightCol = (rightPosX + entity.speed) / gp.tileSize ;
                    tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                    tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                    if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                        int tileNum3 = gp.tileM.mapTileNum[rightCol ][bottomRow - 1] ;
                        if (!gp.tileM.tiles[tileNum3].collision) {
                            entity.canGoUp = true ;
                            return;
                        }
                    }
                }
                entity.canGoUp = false;
                break ;
        }



    }

    public void canFall (Entity entity){
        int leftPosX = entity.getPlayerX() + entity.solidArea.x;
        int rightPosX = entity.getPlayerX() + entity.solidArea.x + entity.solidArea.width ;
        int topPosY = entity.getPlayerY() + entity.solidArea.y;
        int bottomPosY = entity.getPlayerY() + entity.solidArea.y + entity.solidArea.height ;

        int leftCol = leftPosX / gp.tileSize ;
        int rightCol = rightPosX / gp.tileSize ;
        int topRow = topPosY / gp.tileSize ;
        int bottomRow = bottomPosY / gp.tileSize ;

        bottomRow = (bottomPosY + entity.speed) / gp.tileSize ;
        int tileNum1 = gp.tileM.mapTileNum[rightCol][bottomRow];
        int tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];

        if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
            entity.isFalling = false ;
        }else {
            entity.isFalling = true;
        }
    }


    public void checkCollision(Entity entity){
        int leftPosX = entity.getPlayerX() + entity.solidArea.x;
        int rightPosX = entity.getPlayerX() + entity.solidArea.x + entity.solidArea.width ;
        int topPosY = entity.getPlayerY() + entity.solidArea.y;
        int bottomPosY = entity.getPlayerY() + entity.solidArea.y + entity.solidArea.height ;

        int leftCol = leftPosX / gp.tileSize ;
        int rightCol = rightPosX / gp.tileSize ;
        int topRow = topPosY / gp.tileSize ;
        int bottomRow = bottomPosY / gp.tileSize ;

        int tileNum1 , tileNum2 ;
        switch (entity.direction) {
            case "left" :
                leftCol =( leftPosX - entity.speed) / gp.tileSize ;
                tileNum1 = gp.tileM.mapTileNum[leftCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[leftCol][bottomRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisonOn = true ;
                }else {
                    entity.collisonOn = false;
                }
                break ;
            case "right" :
                rightCol = (rightPosX + entity.speed) / gp.tileSize ;
                tileNum1 = gp.tileM.mapTileNum[rightCol][topRow];
                tileNum2 = gp.tileM.mapTileNum[rightCol][bottomRow];
                if(gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision){
                    entity.collisonOn = true ;
                }else {
                    entity.collisonOn = false;
                }
                break ;
        }

    }

}
