package controller;

import model.entity.Entity;
import view.GamePanel;
import view.PlayersView;

public class GameController {
    GamePanel gp;
    PlayersView pv;
    private CollisonChecker cCheker ;

    int hbat=0;

    public GameController (GamePanel gp,PlayersView pv) {
        this.cCheker = gp.getcCheker();
        this.pv=pv;
        this.gp = gp;
    }

    public void movePlayer(Entity player){

            cCheker.checkTile(player);
            int[] indexes = cCheker.checkObject(player,true);
            gp.getObjectController().interactWithObject(indexes[0],indexes[1]);

            if (player==null){
                System.out.println("ya rien a dessiner merci");
                return;
            }

            if (player!= null && player.isFalling()){
                hbat = hbat +player.getSpeed();
                goDown(player);
                if (hbat == gp.getTileSize()*5){
                    System.out.println("Player "+player.getId()+" died du to the fall");
                    pv.players.remove(player);
                    return;
                }
                return;
            }
            if (player!= null &&player.isCanGoUp()) {
                hbat = 0;
                goUp(player);
                return;
            }
            if (player!= null &&player.isCollisonOn()) {
                hbat = 0;
                switchDirection(player);
                return;
            }
            if (player!= null ){
                hbat = 0;
                moveNormally(player);
            }



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
