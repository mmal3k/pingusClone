package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

import java.awt.*;

public interface Role {

    public abstract void move(GamePanel gp , Player player , CollisonChecker cChecker);

    public Color getColor () ;

}
