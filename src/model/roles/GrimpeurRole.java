package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

import java.awt.*;

public class GrimpeurRole extends NormalRole implements Role{
    private boolean goUpNormally = false ;

    @Override
    public void move(GamePanel gp , Player player, CollisonChecker cChecker) {
        cChecker.checkTile(player);
        int[] indexes = cChecker.checkObject(player,true);
        int x = (player.getPlayerX() + gp.halfTileSize) / gp.getTileSize() ;
        int y = (player.getPlayerY() +  gp.halfTileSize) / gp.getTileSize() ;
        gp.getObjectController().interactWithObject(indexes[0],indexes[1]);

        if (player!= null && player.isFalling()){
            goDown(player,gp);
            return;
        }

        if (player!= null && isGoUpNormally() ) {
            player.fallen =0;
            goUp(player,gp,x,y);
            return;
        }

        if (player!= null && player.isCanGoUp()) {
            player.fallen =0;
            goUp(player,gp,x,y);
            return;
        }

        if (player!= null &&player.isCollisonOn() && !player.isCanGoUp() && !goUpNormally) {
            player.fallen =0;
            switchDirection(player);
            return;
        }
        if (player!= null ){
            player.fallen = 0;
            moveNormally(player);
        }
    }




    public void goUp(Player player,GamePanel gp,int x, int y){

        if (isGoUpNormally()) {
            int posX = player.getPlayerX();
            int posY = player.getPlayerY();
            player.setPlayerY(posY - player.getSpeed());
            up+= player.getSpeed();
            if ( up!= 0&&up %16==0){
                switch (player.getDirection()){
                    case "right" :
                        player.setPlayerX(posX  + player.getSpeed());
                        break;
                    case "left" :
                        player.setPlayerX(posX  - player.getSpeed());
                        break;
                }
            }
        }else if ((player.getDirection().equals("left") && gp.getTileM().getMapTileNum()[x-1][y] != 2) ||
                (player.getDirection().equals("right") && gp.getTileM().getMapTileNum()[x+1][y] != 2)){
            System.out.println(gp.getTileM().getMapTileNum()[x][y]);
            int posX = player.getPlayerX();
            int posY = player.getPlayerY();
            player.setPlayerY(posY - player.getSpeed());
        } else switchDirection( player);

    }



    public boolean isGoUpNormally() {
        return goUpNormally;
    }

    public void setGoUpNormally(boolean goUpNormally) {
        this.goUpNormally = goUpNormally;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }
}