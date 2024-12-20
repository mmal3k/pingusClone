package model.entity;


import java.awt.*;
import view.GamePanel;

public class Player extends Entity {
    private GamePanel gp ;

    public Player (GamePanel gp ,int id) {
        this.gp = gp;
        this.setId(id);
        setSolidArea(new Rectangle(10,18,28,30));
        setSolidAreaDefaultX(getSolidArea().x);
        setSolidAreaDefaultY(getSolidArea().y);
        setDefaultValues();
    }

    public void setDefaultValues(){
        super.setPlayerX(gp.getTileSize() * 4);
        super.setPlayerY(gp.getTileSize() * 9);
        setSpeed(2);
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


    public Rectangle getBounds() {
        return new Rectangle(getPlayerX(), getPlayerY(), gp.getTileSize(), gp.getTileSize());
    }

    public void performClickAction() {
        System.out.println("Player : "+ this.getId() +" clicked! Performing action... : " + gp.menuPanel.getRole());
        // Add your desired action here
    }
}
