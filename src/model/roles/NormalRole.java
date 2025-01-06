package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

import java.awt.*;

public class NormalRole implements Role{

    int up=0;


    @Override
    public void move(GamePanel gp , Player player, CollisonChecker cChecker) {

//        if (cChecker.checkCollisionWithBounds(player.getPlayerX(), player.getPlayerY())) {
//            return; // Stop movement if collision with padding
//        }

        cChecker.checkTile(player);
        int[] indexes = cChecker.checkObject(player,true);
        gp.getObjectController().interactWithObject(indexes[0],indexes[1]);

        if (player!= null && player.isFalling()){
            goDown(player , gp);
            return;
        }
        if (player!= null &&player.isCanGoUp()) {
            player.fallen =0;
            goUp(player);
            return;
        }
        if (player!= null &&player.isCollisonOn() && !player.isCanGoUp()) {
            player.fallen =0;
            switchDirection(player);
            return;
        }
        if (player!= null ){
            player.fallen = 0;
            moveNormally(gp ,player);
        }
    }

    @Override
    public Color getColor() {
        return new Color(255, 223, 0);
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
    public void moveNormally(GamePanel gp , Player player){
        int posX = player.getPlayerX();
        switch (player.getDirection()) {
            case "left" :
                if (posX - player.getSpeed() < 0) {
                    switchDirection(player);
                    return;
                }
                player.setPlayerX(posX - player.getSpeed());
                break;
            case "right" :
                if (posX + player.getSpeed() >gp.getScreenWidth() ) {
                    switchDirection(player);
                    return;
                }
                player.setPlayerX(posX + player.getSpeed());
                break;
        }
    }
    public void goDown(Player player , GamePanel gp){
        player.fallen = player.fallen + player.getSpeed();
        int posY = player.getPlayerY();

        player.setPlayerY(posY + player.getSpeed());

        if (player.fallen == gp.getTileSize()*5){
            int playerInd = gp.getPlayers().indexOf(player);
            System.out.println("Player "+player.getId()+" died due to the fall");
            gp.getPlayers().set(playerInd,null);
            gp.getPlayersView().nbDiedPlayers ++ ;
            System.out.println("players died "+ gp.getPlayersView().nbDiedPlayers +" , players number : " + gp.getPlayersView().getPlayerNumber());
            if (gp.getPlayersView().nbDiedPlayers ++ >= gp.getPlayersView().getPlayerNumber() - 4) {
                gp.playSE(2);
                gp.gameState = gp.gameOverState ;
            }
        }

    }
    public void goUp(Player player){
        int posX = player.getPlayerX();
        int posY = player.getPlayerY();

        player.setPlayerY(posY - player.getSpeed());


        up+= player.getSpeed();
        if ( up!= 0&&up %16==0){

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
}
