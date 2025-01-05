package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

// Le Foreur ya7fer 7ata yekemel l count c pas une ligne droite

public class Foreur extends  Role{
    int creuser = 0 ;
    NormalRole normal;

    public Foreur(){
        normal = new NormalRole();
    }
    @Override
    public void move(GamePanel gp , Player player , CollisonChecker cChecker){
        if (creuser == 5){
            player.setRole(new NormalRole());
            return;
        }

        int mapx = (player.getPlayerX() + gp.halfTileSize) / gp.getTileSize() ;
        int mapy = (player.getPlayerY() +  gp.halfTileSize) / gp.getTileSize() ;


        if (gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[mapx][mapy+1]].isDestructible() && !player.isFalling()){
            gp.playSE(2);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            creuser++;
            System.out.println("Forage");
            gp.getTileM().getMapTileNum()[mapx][mapy+1] = 0;


            normal.move(gp, player, cChecker);
        } else normal.move(gp, player, cChecker);



    }
}
