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
        int posX = player.getPlayerX();
        int posY = player.getPlayerY();
        if (player.isFalling){
            player.setPlayerY(posY + player.speed);

        } else if (player.collisonOn) {
            if (player.canGoUp){
                player.setPlayerY(posY - player.speed);
                if (player.direction  == "right") {
                    player.setPlayerX(posX - player.speed);
                } else {
                    player.setPlayerX(posX + player.speed);
                }
                player.isFalling = true;
            }else{
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

        } else {
            switch (player.direction) {
                case "left" :
                    player.setPlayerX(posX - player.speed);
                    break;
                case "right" :
                    player.setPlayerX(posX + player.speed);
                    break;
            }
        }

    }
}
