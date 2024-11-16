package controller;

import model.entity.Player;
import view.GamePanel;

public class GameController {

    Player player ;
    CollisonChecker cCheker ;


    public GameController (GamePanel gp) {
        this.player = gp.player;
        this.cCheker = gp.cCheker ;
    }

    public void movePlayer(){
        cCheker.checkTile(player);
        if (player.isFalling){
            System.out.println("is Falling");
           goDown();
           return;
        }
        if (player.canGoUp) {
            System.out.println("going up , player direction : "+ player.direction);
            goUp();
            return;
        }
        if (player.collisonOn) {
            System.out.println("switch direction");
            switchDirection();
            return;
        }
        System.out.println("walking normally");
        moveNormally();
    }
    public void switchDirection(){
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
    public void moveNormally(){
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
    public void goDown(){
        int posY = player.getPlayerY();
        player.setPlayerY(posY + player.speed);
    }
    public void goUp(){

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
