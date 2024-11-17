package model.entity;

import java.awt.*;

public abstract class Entity {
    int playerX;
    int playerY;
    public String direction;
    public int speed ;
    public boolean collisonOn = false;
    public boolean isFalling = false;
    public boolean canGoUp = false;
    public Rectangle solidArea;
    public abstract int getPlayerX() ;
    public abstract void setPlayerX(int playerX) ;
    public abstract int getPlayerY() ;
    public abstract void setPlayerY(int playerY) ;
}
