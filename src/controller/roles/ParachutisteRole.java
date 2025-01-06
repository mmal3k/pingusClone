package controller.roles;

import model.Player;
import view.GamePanel;

import java.awt.*;

public class ParachutisteRole extends NormalRole implements Role{

    @Override
    public void goDown(Player player , GamePanel gp){
        int posY = player.getPlayerY();
        int defaults = player.getSpeed();
        player.setSpeed(defaults - 2);
        player.setPlayerY(posY + player.getSpeed());
        player.setSpeed(defaults);
    }


    @Override
    public Color getColor() {
        return Color.PINK;
    }
}
