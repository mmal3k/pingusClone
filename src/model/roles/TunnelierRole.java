package model.roles;

import controller.CollisonChecker;
import model.Player;
import model.tile.Tile;
import view.GamePanel;

public class TunnelierRole extends Role {
    NormalRole normalRole = new NormalRole();

    @Override
    public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
        normalRole.move(gp , player,cChecker);

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
                    gp.getTileM().getMapTileNum()[leftCol][bottomRow] = 0;
                    gp.getTileM().getMapTileNum()[leftCol][topRow] = 0;

                    if (leftCol - 1 < 0 ) {
                        player.setRole(new NormalRole());
                    }else {
                        int tempTileNum =  gp.getTileM().getMapTileNum()[leftCol - 1][bottomRow ];
                        if (!gp.getTileM().getTiles()[tempTileNum].isCollision() ) {
                            player.setRole(new NormalRole());
                        }
                        int tempTileNum2 =  gp.getTileM().getMapTileNum()[leftCol-1][topRow];
                        if (!gp.getTileM().getTiles()[tempTileNum2].isCollision() ) {
                            player.setRole(new NormalRole());
                        }
                    }
                }
                break;
            case "right":
                rightCol = (rightPosX + player.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[rightCol][topRow];
                tileNum2 = gp.getTileM().getMapTileNum()[rightCol][bottomRow];
                if (gp.getTileM().getTiles()[tileNum1].isCollision() || gp.getTileM().getTiles()[tileNum2].isCollision()) {
                    gp.getTileM().getMapTileNum()[rightCol][bottomRow] = 0;
                    gp.getTileM().getMapTileNum()[rightCol][topRow] = 0;

                    if (rightCol + 1 > gp.getMaxScreenCol()) {
                        player.setRole(new NormalRole());
                    }else {
                        int tempTileNum =  gp.getTileM().getMapTileNum()[rightCol + 1][bottomRow ];
                        if (!gp.getTileM().getTiles()[tempTileNum].isCollision() ) {
                            player.setRole(new NormalRole());
                        }
                        int tempTileNum2 =  gp.getTileM().getMapTileNum()[rightCol+1][topRow];
                        if (!gp.getTileM().getTiles()[tempTileNum2].isCollision() ) {
                            player.setRole(new NormalRole());
                        }
                    }

                }
                break;
        }
    }
}