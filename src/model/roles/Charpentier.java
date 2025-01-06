package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class Charpentier extends Role{
    NormalRole normal;
    private int built;
    private int lastX;


    public Charpentier(){
        normal = new NormalRole();
        built = 0;
    }

    public boolean canbuild( int x){
        return lastX != x;
    }
    @Override
    public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
        if (built == 5 ){
            player.setRole(new NormalRole());
        }

        int x = (player.getPlayerX()+ gp.halfTileSize) / gp.getTileSize();
        int y = (player.getPlayerY() + gp.halfTileSize) / gp.getTileSize();


        if (player.getDirection().equals("left")){
            if (!gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x-1][y]].isCollision()  &&canbuild(x-1)){
                gp.getTileM().getMapTileNum()[x-1][y]=1;
                lastX=x-1;
                built ++;
            } else normal.move(gp, player, cChecker);
        } else if (player.getDirection().equals("right")){
            if (!gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x+1][y]].isCollision() &&canbuild(x+1)){
                gp.getTileM().getMapTileNum()[x+1][y]=1;
                lastX=x+1;
                built ++;
            } else normal.move(gp, player, cChecker);
        } else normal.move(gp, player, cChecker);



    }
}


