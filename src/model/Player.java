package model;


import java.awt.*;

import controller.CollisonChecker;
import model.roles.*;
import view.GamePanel;

//public class Player extends Entity {
public class Player {


    private int id;
    private int playerX;
    public int playerY;
    private String direction;
    private int speed ;
    private boolean collisonOn = false;
    private boolean isFalling = false;
    private boolean canGoUp = false;
    public int goDown;
    private Rectangle solidArea;
    private int solidAreaDefaultX ;
    private int solidAreaDefaultY ;
    private GamePanel gp ;
    private Role role ;
    public boolean colliddable = false ;



    public Player (GamePanel gp ,int id) {
        this.gp = gp;
        this.id = id;
        this.solidArea = new Rectangle(10,18,28,30);
        this.solidAreaDefaultX  = this.solidArea.x;
        this.solidAreaDefaultY  = this.solidArea.y;
        this.role = new NormalRole();
        goDown = 0;
        setDefaultValues();
    }

    public void setDefaultValues(){
        this.playerX = gp.getTileSize() * 4 ;

        this.playerY = gp.getTileSize() * 9 ;

        this.speed = 2 ;

        this.direction = "left";

    }


    public Rectangle getBounds() {
        return new Rectangle(getPlayerX(), getPlayerY(), gp.getTileSize(), gp.getTileSize());
    }

    public void performClickAction() {
        System.out.println("Player : "+ this.id +" clicked! Performing action... : " + gp.menuPanel.getRole());

        switch (gp.menuPanel.getRole()) {
            case "Role 2" :
                this.role = new BlockerRole();
                break;
            case "Role 3" :
                this.role = new Bombeur();
                break;
            case "Role 4" :
                this.role = new Foreur();
                break;
        }
        // Add your desired action here
    }

    public void movePlayer (GamePanel gp,  Player player ,CollisonChecker cChecker) {
        role.move(gp ,player , cChecker);
    }

    public void setRole(Role role){
        this.role=role;
    }


    public boolean isFalling() {
        return isFalling;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isCanGoUp() {
        return canGoUp;
    }

    public void setCanGoUp(boolean canGoUp) {
        this.canGoUp = canGoUp;
    }

    public int getPlayerY() {
        return this.playerY ;
    }


    public void setPlayerY(int playerY) {
        this.playerY = playerY ;

    }

    public int getPlayerX() {return this.playerX ;}


    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }


    public boolean isCollisonOn() {
        return collisonOn;
    }

    public void setCollisonOn(boolean collisonOn) {
        this.collisonOn = collisonOn;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
}
