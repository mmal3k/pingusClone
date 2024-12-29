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

        if (gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[mapx][mapy+1]].isDestructible() && !player.isFalling()){
            creuser++;
            System.out.println("Forage");
            gp.getTileM().getMapTileNum()[mapx][mapy+1] = 0;


            super.move(gp, player, cChecker);

            System.out.println("apres move");
        } else super.move(gp, player, cChecker);


        if (creuser == 5){
            player.setRole(new NormalRole());
        }

    }
}
