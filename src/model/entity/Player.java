package model.entity;

import java.awt.*;
import view.GamePanel;

public class Player extends Entity {
    private GamePanel gp ;

    public Player (GamePanel gp) {
        this.gp = gp;
        setSolidArea(new Rectangle(10,18,28,30));
        setDefaultValues();
    }

    public void setDefaultValues(){
        super.setPlayerX(gp.getTileSize() * 3);
        super.setPlayerY(gp.getTileSize() * 8);
        setSpeed(3);
        setDirection("left");
    }


    public int getPlayerX() {return super.getPlayerX();}


    public void setPlayerX(int playerX) {
        super.setPlayerX(playerX);
    }


    public int getPlayerY() {
        return super.getPlayerY();
    }


    public void setPlayerY(int playerY) {
        super.setPlayerY(playerY);
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }
}
