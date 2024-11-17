package model.entity;

import view.GamePanel;

import java.awt.*;

public class Player extends Entity {
    GamePanel gp ;

    public Player (GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(10,18,28,30);
        setDefaultValues();
    }

    public void setDefaultValues(){
        playerX = gp.tileSize * 3 ;
        playerY = gp.tileSize * 8 ;
        speed = 3;
        direction = "left";
    }

    @Override
    public int getPlayerX() {
        return playerX;
    }

    @Override
    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    @Override
    public int getPlayerY() {
        return playerY;
    }

    @Override
    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
}
