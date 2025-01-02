package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class BlockerRole implements Role{
    @Override
    public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
        player.colliddable = true ;
    }
}
