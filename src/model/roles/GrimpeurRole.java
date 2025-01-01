package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class GrimpeurRole extends Role{
    private boolean goUpNormally = false ;

    @Override
    public void move(GamePanel gp , Player player, CollisonChecker cChecker) {
        cChecker.checkTile(player);
        int[] indexes = cChecker.checkObject(player,true);
        gp.getObjectController().interactWithObject(indexes[0],indexes[1]);

        if (player!= null && player.isFalling()){
            player.goDown = player.goDown + player.getSpeed();
            goDown(player);
            if (player.goDown == gp.getTileSize()*5){
                int playerInd = gp.getPlayers().indexOf(player);
                System.out.println("Player "+player.getId()+"died due to the fall");
                gp.getPlayers().set(playerInd,null);
            }
            return;

        }

        if (player!= null && isGoUpNormally()) {
            player.goDown=0;
            normalGoUp(player);
            return;
        }

        if (player!= null && player.isCanGoUp()) {
            player.goDown=0;
            goUp(player);
            return;
        }


        if (player!= null &&player.isCollisonOn()) {
            player.goDown=0;
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

//        switch (player.getDirection()){
//            case "right" :
//                player.setPlayerX(posX  + player.getSpeed());
//                break;
//            case "left" :
//                player.setPlayerX(posX  - player.getSpeed());
//                break;
//        }


    }

    public void normalGoUp (Player player) {
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


    public boolean isGoUpNormally() {
        return goUpNormally;
    }

    public void setGoUpNormally(boolean goUpNormally) {
        this.goUpNormally = goUpNormally;
    }
}
