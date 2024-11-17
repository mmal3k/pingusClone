package controller;

import model.entity.Entity;
import model.entity.Player;
import view.GamePanel;

import java.util.ArrayList;

public class GameController {
    CollisonChecker cCheker ;
    public GameController (GamePanel gp) {
        this.cCheker = gp.cCheker ;
    }
    public void movePlayer(Entity player){
            cCheker.checkTile(player);
            if (player.isFalling){
                goDown(player);
                return;
            }
            if (player.canGoUp) {
                goUp(player);
                return;
            }
            if (player.collisonOn) {
                switchDirection(player);
                return;
            }
            moveNormally(player);
    }
    public void switchDirection(Entity player){
        int posX = player.getPlayerX();
        switch (player.direction) {
            case "left" :
                player.direction = "right";
                player.setPlayerX(posX + player.speed);
                break;
            case "right" :
                player.direction = "left";
                player.setPlayerX(posX - player.speed);
                break;
        }
    }
    public void moveNormally(Entity player){
        int posX = player.getPlayerX();
        switch (player.direction) {
            case "left" :
                player.setPlayerX(posX - player.speed);
                break;
            case "right" :
                player.setPlayerX(posX + player.speed);
                break;
        }
    }
    public void goDown(Entity player){
        int posY = player.getPlayerY();
        player.setPlayerY(posY + player.speed);
    }
    public void goUp(Entity player){
        int posX = player.getPlayerX();
        int posY = player.getPlayerY();
        player.setPlayerY(posY - player.speed);
        switch (player.direction){
            case "right" :
                player.setPlayerX(posX  + player.speed);
                break;
            case "left" :
                player.setPlayerX(posX  - player.speed);
                break;
        }
    }


}
