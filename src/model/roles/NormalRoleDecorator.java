package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public abstract class NormalRoleDecorator extends NormalRole {
    protected NormalRole normalRoleDecorator;

    public NormalRoleDecorator(NormalRole normalRole) {
        this.normalRoleDecorator = normalRole ;
    }

    @Override
    public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
        super.move(gp, player, cChecker);
    }
}
