package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class Foreur extends NormalRole{
    int creuser = 0 ;
    @Override
    public void move(GamePanel gp , Player player , CollisonChecker cChecker){
        int mapx = (player.getPlayerX() + gp.halfTileSize) / gp.getTileSize() ;
        int mapy = (player.getPlayerY() +  gp.halfTileSize) / gp.getTileSize() ;

        if (gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[mapx][mapy+2]].isDestructible()){
            creuser++;
            gp.getTileM().getMapTileNum()[mapx][mapy] = 0;
            super.move(gp, player, cChecker);
        }
//        if (creuser == 3){
//            player.setRole(new NormalRole());
//        }

    }
}
