package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class ParachutisteRole extends NormalRole{
    public int defaultSpeed ;


    public ParachutisteRole ( Player player ) {
        defaultSpeed = player.getSpeed() ;
    }

    @Override
    public void goDown(Player player , GamePanel gp){
        int posY = player.getPlayerY();
        player.setSpeed(defaultSpeed - 2);
        player.setPlayerY(posY + player.getSpeed());
        player.setSpeed(defaultSpeed);
    }


}
