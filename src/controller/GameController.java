package controller;

import model.entity.Entity;
import view.GamePanel;

public class GameController {
    private CollisonChecker cCheker ;
    public GameController (GamePanel gp) {
        this.cCheker = gp.getcCheker();
    }
    public void movePlayer(Entity player){
            cCheker.checkTile(player);
            if (player.isFalling()){
                goDown(player);
                return;
            }
            if (player.isCanGoUp()) {
                goUp(player);
                return;
            }
            if (player.isCollisonOn()) {
                switchDirection(player);
                return;
            }
            moveNormally(player);
    }
    public void switchDirection(Entity player){
        int posX = player.getPlayerX();
        switch (player.getDirection()) {
            case "left" :
                player.setDirection("right");
                player.setPlayerX(posX + player.getSpeed());
                break;
            case "right" :
                player.setDirection("left");
                player.setPlayerX(posX - player.getSpeed());
                break;
        }
    }
    public void moveNormally(Entity player){
        int posX = player.getPlayerX();
        switch (player.getDirection()) {
            case "left" :
                player.setPlayerX(posX - player.getSpeed());
                break;
            case "right" :
                player.setPlayerX(posX + player.getSpeed());
                break;
        }
    }
    public void goDown(Entity player){
        int posY = player.getPlayerY();
        player.setPlayerY(posY + player.getSpeed());
    }
    public void goUp(Entity player){
        int posX = player.getPlayerX();
        int posY = player.getPlayerY();
        player.setPlayerY(posY - player.getSpeed());
        switch (player.getDirection()){
            case "right" :
                player.setPlayerX(posX  + player.getSpeed());
                break;
            case "left" :
                player.setPlayerX(posX  - player.getSpeed());
                break;
        }
    }


    public CollisonChecker getcCheker() {
        return cCheker;
    }

    public void setcCheker(CollisonChecker cCheker) {
        this.cCheker = cCheker;
    }
}
