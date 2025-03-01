package model;


import java.awt.*;

import controller.CollisonChecker;
import controller.RoleFactory;
import controller.roles.NormalRole;
import controller.roles.Role;
import controller.roles.*;
import view.GamePanel;

public class Player {


    private int id;
    private int playerX;
    private int playerY;
    private String direction;
    private int speed ;
    private boolean collisonOn = false;
    private boolean isFalling = false;
    private boolean canGoUp = false;
    public int fallen;
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
        fallen = 0;
        setDefaultValues();
    }



    public void setDefaultValues(){
        this.playerX = gp.getObj()[0].getObjX() ;
        this.playerY = gp.getObj()[0].getObjY() ;
        this.speed = 3 ;
        this.direction = "left";
    }


    public Rectangle getBounds() {
        return new Rectangle(getPlayerX(), getPlayerY(), gp.getTileSize(), gp.getTileSize());
    }

    public void performClickAction() {
        System.out.println("Player : "+ this.id +" clicked! Performing action... : " + gp.keyH.role);

        switch (gp.keyH.role) {
            case "Role 1" :
                this.role = RoleFactory.RoleFactory("parachutiste");
                gp.ui.showRoleNotif("Player "+getId() + " is now a Parachutiste");
                break;
            case "Role 2" :
                this.role = RoleFactory.RoleFactory("bloqueur");
                gp.ui.showRoleNotif("Player "+getId() + " is now a Blocker");
                break;
            case "Role 3" :
                this.role = RoleFactory.RoleFactory("tunnelier");
                gp.ui.showRoleNotif("Player "+getId() + " is now a Tunnelier");
                break;
            case "Role 4" :
                this.role = RoleFactory.RoleFactory("grimpeur");
                gp.ui.showRoleNotif("Player "+getId() + " is now a Grimpeur");
                break;
            case "Role 5" :
                this.role = RoleFactory.RoleFactory("foreur");
                gp.ui.showRoleNotif("Player "+getId() + " is now a Foreur");
                break;
            case "Role 6" :
                this.role = RoleFactory.RoleFactory("bombeur");
                gp.ui.showRoleNotif("Player "+getId() + " is now a Bomber");
                break;
            case "Role 7":
                this.role = RoleFactory.RoleFactory("charpentier");
                gp.ui.showRoleNotif("Player "+getId() + " is now a Charpentier");
                break;
        }
        // Add your desired action here
    }

    public void movePlayer (GamePanel gp,  Player player ,CollisonChecker cChecker) {
        role.move(gp ,player , cChecker);
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
