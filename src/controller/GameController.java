package controller;

import model.entity.Entity;
import view.GamePanel;

public class GameController {
    GamePanel gp;
    private CollisonChecker cCheker ;
    public GameController (GamePanel gp) {
        this.cCheker = gp.getcCheker();
        this.gp = gp;
    }
    public void movePlayer(Entity player){

            cCheker.checkTile(player);
            int[] indexes = cCheker.checkObject(player,true);
            gp.getObjectController().interactWithObject(indexes[0],indexes[1]);

            if (player!= null && player.isFalling()){
                goDown(player);
                return;
            }
            if (player!= null &&player.isCanGoUp()) {
                goUp(player);
                return;
            }
            if (player!= null &&player.isCollisonOn()) {

                switchDirection(player);
                return;
            }
            if (player!= null )moveNormally(player);


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
}
