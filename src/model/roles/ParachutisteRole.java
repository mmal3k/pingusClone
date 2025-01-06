package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class ParachutisteRole extends NormalRole implements Role{

    @Override
    public void goDown(Player player , GamePanel gp){
        int posY = player.getPlayerY();
        int defaults = player.getSpeed();
        player.setSpeed(defaults - 2);
        player.setPlayerY(posY + player.getSpeed());
        player.setSpeed(defaults);
    }


}
