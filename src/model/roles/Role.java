package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public abstract class Role {

    String name ;

    public abstract void move(GamePanel gp , Player player , CollisonChecker cChecker);



}
