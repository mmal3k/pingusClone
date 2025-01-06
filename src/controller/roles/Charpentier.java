package controller.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

import java.awt.*;

public class Charpentier extends NormalRoleDecorator implements Role{

    private int built;
    private int lastX;


    public Charpentier(NormalRole normalRole){
        super(normalRole);
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
            } else normalRoleDecorator.move(gp, player, cChecker);
        } else if (player.getDirection().equals("right")){
            if (!gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x+1][y]].isCollision() &&canbuild(x+1)){
                gp.getTileM().getMapTileNum()[x+1][y]=1;
                lastX=x+1;
                built ++;
            } else normalRoleDecorator.move(gp, player, cChecker);
        } else normalRoleDecorator.move(gp, player, cChecker);



    }
    @Override
    public Color getColor() {
        return Color.GREEN;
    }
}