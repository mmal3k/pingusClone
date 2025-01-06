package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

// Le Foreur ya7fer 7ata yekemel l count c pas une ligne droite

public class Foreur extends NormalRoleDecorator implements  Role {
    int creuser = 0 ;


    public Foreur(NormalRole normalRole){

        super(normalRole);
    }
    @Override
    public void move(GamePanel gp , Player player , CollisonChecker cChecker){
        int mapx = (player.getPlayerX() + gp.halfTileSize) / gp.getTileSize() ;
        int mapy = (player.getPlayerY() +  gp.halfTileSize) / gp.getTileSize() ;

        if (gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[mapx][mapy+1]].isDestructible() && !player.isFalling()){
            creuser++;
            System.out.println("Forage");
            gp.getTileM().getMapTileNum()[mapx][mapy+1] = 0;
            normalRoleDecorator.move(gp, player, cChecker);
            System.out.println("apres move");
        } else normalRoleDecorator.move(gp, player, cChecker);

        if (creuser == 5){
            player.setRole(new NormalRole());
        }

    }
}
