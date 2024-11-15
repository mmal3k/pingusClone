package model.entity;

import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp ;
    public BufferedImage left1, left2 , right1 , right2 , down1, down2,up1 , up2;

    public Player (GamePanel gp) {
        this.gp = gp;
        solidArea = new Rectangle(8,16,32,32);
        setDefaultValues();
    }

    public void setDefaultValues(){
        playerX = gp.tileSize * 3 ;
        playerY = gp.tileSize * 1 ;
        speed = 3;
        direction = "right";
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
