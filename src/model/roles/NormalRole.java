package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class NormalRole extends Role{


    @Override
    public void handleRequest(GamePanel gp , Player player, CollisonChecker cChecker) {
        cChecker.checkTile(player);
        int[] indexes = cChecker.checkObject(player,true);
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

    public void switchDirection(Player player){
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
    public void moveNormally(Player player){
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
    public void goDown(Player player){
        int posY = player.getPlayerY();

        player.setPlayerY(posY + player.getSpeed());

    }
    public void goUp(Player player){
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
