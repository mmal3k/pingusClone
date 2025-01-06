package controller.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

import java.awt.*;

public class BlockerRole implements Role {
    @Override
    public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
        player.colliddable = true ;
    }


    @Override
    public Color getColor() {
        return Color.ORANGE;
    }
}

