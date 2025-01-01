package controller;

import model.Player;
import model.roles.GrimpeurRole;
import view.GamePanel;

import java.awt.*;

public class CollisonChecker {

    private GamePanel gp ;

    public CollisonChecker(GamePanel gp ){
        this.gp = gp ;
    }
    public void checkTile (Player entity){
        if (entity != null) {
            if (! (entity.getRole() instanceof GrimpeurRole)) {
                canGoUp(entity);
            }else {
                canGoUp2(entity);
            }
            if (entity.isCanGoUp()) {
                return;
            }
            canFall(entity);
            if (entity.isFalling()) {
                return;
            }
            checkCollision(entity);
            collisionWithPlayer(entity);
            if (entity.isCollisonOn()) {
                return;
            }
        }
    }
    public void canGoUp(Player entity){
        if (entity != null) {
            int leftPosX = entity.getPlayerX() + entity.getSolidArea().x;
            int rightPosX = entity.getPlayerX() + entity.getSolidArea().x + entity.getSolidArea().width;
            int topPosY = entity.getPlayerY() + entity.getSolidArea().y;
            int bottomPosY = entity.getPlayerY() + entity.getSolidArea().y + entity.getSolidArea().height;

            int leftCol = leftPosX / gp.getTileSize();
            int rightCol = rightPosX / gp.getTileSize();
            int topRow = topPosY / gp.getTileSize();
            int bottomRow = bottomPosY / gp.getTileSize();

            int tileNum1, tileNum2;
            switch (entity.getDirection()) {
                case "left":
                    int tileNum0 = gp.getTileM().getMapTileNum()[leftCol][bottomRow - 1];
                    if (!gp.getTileM().getTiles()[tileNum0].isCollision()) {
                        leftCol = (leftPosX - entity.getSpeed()) / gp.getTileSize();
                        tileNum1 = gp.getTileM().getMapTileNum()[leftCol][topRow];
                        tileNum2 = gp.getTileM().getMapTileNum()[leftCol][bottomRow];
                        if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                            int tileNum3 = gp.getTileM().getMapTileNum()[leftCol][bottomRow - 1];
                            if (!gp.getTileM().getTiles()[tileNum3].isCollision()) {
                                entity.setCanGoUp(true);

                                return;
                            }
                        }
                    }
                    entity.setCanGoUp(false);
                    break;
                case "right":
                    int tileNum = gp.getTileM().getMapTileNum()[rightCol][bottomRow - 1];
                    if (!gp.getTileM().getTiles()[tileNum].isCollision()) {
                        rightCol = (rightPosX + entity.getSpeed()) / gp.getTileSize();
                        tileNum1 = gp.getTileM().getMapTileNum()[rightCol][topRow];
                        tileNum2 = gp.getTileM().getMapTileNum()[rightCol][bottomRow];
                        if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                            int tileNum3 = gp.getTileM().getMapTileNum()[rightCol][bottomRow - 1];
                            if (!gp.getTileM().getTiles()[tileNum3].isCollision()) {
                                entity.setCanGoUp(true);

                                return;
                            }
                        }
                    }
                    entity.setCanGoUp(false);
                    break;
            }
        }
    }

    public void canGoUp2(Player entity){
        if (entity != null) {
            int leftPosX = entity.getPlayerX() + entity.getSolidArea().x;
            int rightPosX = entity.getPlayerX() + entity.getSolidArea().x + entity.getSolidArea().width;
            int topPosY = entity.getPlayerY() + entity.getSolidArea().y;
            int bottomPosY = entity.getPlayerY() + entity.getSolidArea().y + entity.getSolidArea().height;

            int leftCol = leftPosX / gp.getTileSize();
            int rightCol = rightPosX / gp.getTileSize();
            int topRow = topPosY / gp.getTileSize();
            int bottomRow = bottomPosY / gp.getTileSize();

            int tileNum1, tileNum2;
            switch (entity.getDirection()) {
                case "left":
                    int tileNum0 = gp.getTileM().getMapTileNum()[leftCol][bottomRow - 1];
                    if (!gp.getTileM().getTiles()[tileNum0].isCollision()) {
                        leftCol = (leftPosX - entity.getSpeed()) / gp.getTileSize();
                        tileNum1 = gp.getTileM().getMapTileNum()[leftCol][topRow];
                        tileNum2 = gp.getTileM().getMapTileNum()[leftCol][bottomRow];
                        if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                                int tileNum3 = gp.getTileM().getMapTileNum()[leftCol][bottomRow - 1];
                                if (!gp.getTileM().getTiles()[tileNum3].isCollision()){
                                    ((GrimpeurRole) entity.getRole()).setGoUpNormally(true);
                                    return;
                                }
                                entity.setCanGoUp(true);
                                return;
                        }
                    }
                    ((GrimpeurRole) entity.getRole()).setGoUpNormally(false);
                    entity.setCanGoUp(false);
                    break;
                case "right":
                    int tileNum = gp.getTileM().getMapTileNum()[rightCol][bottomRow - 1];
                    if (!gp.getTileM().getTiles()[tileNum].isCollision()) {
                        rightCol = (rightPosX + entity.getSpeed()) / gp.getTileSize();
                        tileNum1 = gp.getTileM().getMapTileNum()[rightCol][topRow];
                        tileNum2 = gp.getTileM().getMapTileNum()[rightCol][bottomRow];
                        if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                                int tileNum3 = gp.getTileM().getMapTileNum()[rightCol][bottomRow - 1];
                                if (!gp.getTileM().getTiles()[tileNum3].isCollision()){
                                    ((GrimpeurRole) entity.getRole()).setGoUpNormally(true);
                                    return;
                                }
                                entity.setCanGoUp(true);
                                return ;
                        }
                    }
                    ((GrimpeurRole) entity.getRole()).setGoUpNormally(false);
                    entity.setCanGoUp(false);
                    break;
            }
        }
    }


    public void canFall (Player entity){
        if (entity != null) {
            int leftPosX = entity.getPlayerX() + entity.getSolidArea().x;
            int rightPosX = entity.getPlayerX() + entity.getSolidArea().x + entity.getSolidArea().width;
            int topPosY = entity.getPlayerY() + entity.getSolidArea().y;
            int bottomPosY = entity.getPlayerY() + entity.getSolidArea().y + entity.getSolidArea().height;

            int leftCol = leftPosX / gp.getTileSize();
            int rightCol = rightPosX / gp.getTileSize();
            int topRow = topPosY / gp.getTileSize();
            int bottomRow = bottomPosY / gp.getTileSize();

            bottomRow = (bottomPosY + entity.getSpeed()) / gp.getTileSize();
            int tileNum1 = gp.getTileM().getMapTileNum()[rightCol][bottomRow];
            int tileNum2 = gp.getTileM().getMapTileNum()[leftCol][bottomRow];

            if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                entity.setFalling(false);
            } else {
                entity.setFalling(true);
            }

        }
    }

    public void checkPlayerUnder(Player player) {
        if (player.isFalling()){
            int leftPosX = player.getPlayerX() + player.getSolidArea().x;
            int rightPosX = player.getPlayerX() + player.getSolidArea().x + player.getSolidArea().width;
            int topPosY = player.getPlayerY() + player.getSolidArea().y;
            int bottomPosY = player.getPlayerY() + player.getSolidArea().y + player.getSolidArea().height;

            int leftCol = leftPosX / gp.getTileSize();
            int rightCol = rightPosX / gp.getTileSize();
            int topRow = topPosY / gp.getTileSize();
            int bottomRow = bottomPosY / gp.getTileSize();

            int bottom = player.getPlayerY() + player.getSolidArea().y + player.getSolidArea().height ;
            int left = player.getPlayerX()  ;
            int right = player.getPlayerX() + gp.getTileSize() ;
            for (Player p : gp.getPlayers()){
                if (p != null && p != player && p.colliddable){
                    if (p.getPlayerY()+ p.getSolidArea().y == bottom && (p.getPlayerX() >= left && p.getPlayerX() <= right) ){
                        player.setFalling(false);
                    }else{
                        player.setFalling(true);
                    }
                }
            }
        }
    }

    public void checkCollision(Player player){
        if (player != null) {
            int leftPosX = player.getPlayerX() + player.getSolidArea().x;
            int rightPosX = player.getPlayerX() + player.getSolidArea().x + player.getSolidArea().width;
            int topPosY = player.getPlayerY() + player.getSolidArea().y;
            int bottomPosY = player.getPlayerY() + player.getSolidArea().y + player.getSolidArea().height;

            int leftCol = leftPosX / gp.getTileSize();
            int rightCol = rightPosX / gp.getTileSize();
            int topRow = topPosY / gp.getTileSize();
            int bottomRow = bottomPosY / gp.getTileSize();

            int tileNum1, tileNum2;
            switch (player.getDirection()) {
                case "left":
                    leftCol = (leftPosX - player.getSpeed()) / gp.getTileSize();
                    tileNum1 = gp.getTileM().getMapTileNum()[leftCol][topRow];
                    tileNum2 = gp.getTileM().getMapTileNum()[leftCol][bottomRow];
                    if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                        player.setCollisonOn(true);
                    } else {
                        player.setCollisonOn(false);
                    }
                    break;
                case "right":
                    rightCol = (rightPosX + player.getSpeed()) / gp.getTileSize();
                    tileNum1 = gp.getTileM().getMapTileNum()[rightCol][topRow];
                    tileNum2 = gp.getTileM().getMapTileNum()[rightCol][bottomRow];
                    if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                        player.setCollisonOn(true);
                    } else {
                        player.setCollisonOn(false);

                    }
                    break;
            }
        }
    }

    public void collisionWithPlayer (Player player) {
        int leftPosX = player.getPlayerX() - player.getSolidArea().x ;
        int rightPosX = player.getPlayerX() + player.getSolidArea().x ;
        int topPosY = player.getPlayerY() ;

        for (Player p : gp.getPlayers()) {
            if (p!= null && p != player && p.colliddable) {
                if ((leftPosX == p.getPlayerX() || rightPosX == p.getPlayerX()) && (topPosY == (p.getPlayerY()))) {
                    player.setCollisonOn(true);
                }
            }
        }
    }

    public int[] checkObject(Player entity , boolean player){
        if(entity != null) {

            int playerIndex = 999;
            int index = 999;
            for (int i = 0; i < gp.getObj().length; i++) {
                if (gp.getObj()[i] != null) {

                    // get entity's solid area position
                    int x = entity.getPlayerX() + entity.getSolidArea().x;
                    int y = entity.getPlayerY() + entity.getSolidArea().y;
                    Rectangle testSolidArea = new Rectangle(x, y, entity.getSolidArea().width, entity.getSolidArea().height);

                    //get the object solid area position

                    Rectangle objectSolidArea = new Rectangle(gp.getObj()[i].getObjX() + gp.getObj()[i].getSolidArea().x, gp.getObj()[i].getObjY() + gp.getObj()[i].getSolidArea().y, gp.getObj()[i].getSolidArea().width, gp.getObj()[i].getSolidArea().height);


                    switch (entity.getDirection()) {
                        case "left":
                            testSolidArea.x -= entity.getSpeed();

                            if (testSolidArea.intersects(objectSolidArea)) {
                                if (gp.getObj()[i].isCollison()) {
                                    entity.setCollisonOn(true);
                                }
                                if (player) {
                                    index = gp.getPlayers().indexOf(entity);
                                }
                            }
                            break;
                        case "right":
                            testSolidArea.x += entity.getSpeed();
                            if (testSolidArea.intersects(objectSolidArea)) {
                                if (gp.getObj()[i].isCollison()) {
                                    entity.setCollisonOn(true);
                                }
                                if (player) {
                                    index = i;
                                    playerIndex = entity.getId();
                                }
                            }
                            break;
                    }
                }
            }
            return new int[]{index,playerIndex};
        }
        return new int[]{999,999};
    }
}