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

//    public void collisionWithPlayer (Player player) {
//        int leftPosX = player.getPlayerX() - player.getSolidArea().x  ;
//        int rightPosX = player.getPlayerX() + player.getSolidArea().x ;
//        int topPosY = player.getPlayerY()  ;
//
//        for (Player p : gp.getPlayers()) {
//            if (p!= null && p != player && p.colliddable) {
//
//                switch (player.getDirection()) {
//                    case "left":
//                        leftPosX = leftPosX - player.getSpeed() ;
//                        if ((leftPosX == p.getPlayerX() + player.getSolidArea().width) && (topPosY == p.getPlayerY()) ) {
//                            player.setCollisonOn(true);
//                            return;
//                        }
//                       break;
//                    case "right":
//                        rightPosX = rightPosX + player.getSpeed() ;
//                        if ((rightPosX == p.getPlayerX()) && (topPosY == p.getPlayerY()) ) {
//                            player.setCollisonOn(true);
//                            return;
//                        }
//                        break;
//                    }
//            }
//        }
//
//    }

    public void collisionWithPlayer(Player player) {
        if (player != null) {
            // Get the player's bounding box (solid area) adjusted for speed
            int playerLeft = player.getPlayerX() + player.getSolidArea().x;
            int playerRight = player.getPlayerX() + player.getSolidArea().x + player.getSolidArea().width;
            int playerTop = player.getPlayerY() + player.getSolidArea().y;
            int playerBottom = player.getPlayerY() + player.getSolidArea().y + player.getSolidArea().height;

            // Adjust the bounding box based on the player's direction and speed
            switch (player.getDirection()) {
                case "left":
                    playerLeft -= player.getSpeed(); // Move left by speed
                    break;
                case "right":
                    playerRight += player.getSpeed(); // Move right by speed
                    break;
                // Add cases for "up" and "down" if needed
            }

            // Check for collisions with other players
            for (Player p : gp.getPlayers()) {
                if (p != null && p != player && p.colliddable) {
                    // Get the other player's bounding box
                    int pLeft = p.getPlayerX() + p.getSolidArea().x;
                    int pRight = p.getPlayerX() + p.getSolidArea().x + p.getSolidArea().width;
                    int pTop = p.getPlayerY() + p.getSolidArea().y;
                    int pBottom = p.getPlayerY() + p.getSolidArea().y + p.getSolidArea().height;

                    // Check for overlapping bounding boxes
                    if (playerRight >= pLeft && playerLeft <= pRight && // Horizontal overlap
                            playerBottom >= pTop && playerTop <= pBottom) { // Vertical overlap
                        player.setCollisonOn(true);
                        return; // Exit early if a collision is detected
                    }
                }
            }

            // No collision detected

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

    public boolean checkCollisionWithBounds(int x, int y) {
        int padding = 1; // Define the padding size (e.g., 1 tile)
        if (x < padding || x >= gp.getMaxScreenCol() - padding ||
                y < padding || y >= gp.getMaxScreenRow() - padding) {
            return true; // Collision with invisible padding
        }
        return false; // No collision
    }
}