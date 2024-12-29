package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class Bombeur extends NormalRole  {
    int pas ;
    public Bombeur(){
        pas = 0;
    }

    @Override
    public void move(GamePanel gp , Player player, CollisonChecker cChecker){
        super.move(gp, player, cChecker);
        pas = pas + player.getSpeed();
        if (pas == gp.getTileSize()*2){
            int playerInd = gp.getPlayers().indexOf(player);
            gp.getPlayers().set(playerInd,null);
            System.out.println("BOOM!!!");
            int mapx = (player.getPlayerX()  / gp.getTileSize()) +1;
            int mapy = player.getPlayerY() / gp.getTileSize();



            int[] tileNums = new int[9];
//            tileNums[0] =  gp.getTileM().getMapTileNum()[mapx][mapy];
//            tileNums[1] = gp.getTileM().getMapTileNum()[mapx-1][mapy];
//            tileNums[2] = gp.getTileM().getMapTileNum()[mapx-2][mapy];
//            tileNums[3] = gp.getTileM().getMapTileNum()[mapx+1][mapy];
//            tileNums[4] = gp.getTileM().getMapTileNum()[mapx+2][mapy];
//            tileNums[5] = gp.getTileM().getMapTileNum()[mapx][mapy-1];
//            tileNums[6] = gp.getTileM().getMapTileNum()[mapx][mapy-2];
//            tileNums[7] = gp.getTileM().getMapTileNum()[mapx][mapy+1];
//            tileNums[8] = gp.getTileM().getMapTileNum()[mapx][mapy+2];

//            gp.getTileM().getMapTileNum()[mapx][mapy] = 0;

            gp.getTileM().getMapTileNum()[mapx-1][mapy+1] = 0;
            gp.getTileM().getMapTileNum()[mapx-2][mapy+1] = 0;
            gp.getTileM().getMapTileNum()[mapx+1][mapy+1] = 0;
            gp.getTileM().getMapTileNum()[mapx+2][mapy+1] = 0;
            gp.getTileM().getMapTileNum()[mapx][mapy-1] = 0;
            gp.getTileM().getMapTileNum()[mapx][mapy] = 0;
            gp.getTileM().getMapTileNum()[mapx][mapy+2] = 0;
            gp.getTileM().getMapTileNum()[mapx][mapy+3] = 0;
            gp.getTileM().getMapTileNum()[mapx+1][mapy+2] = 0;
            gp.getTileM().getMapTileNum()[mapx-1][mapy+2] = 0;
            gp.getTileM().getMapTileNum()[mapx-1][mapy] = 0;
            gp.getTileM().getMapTileNum()[mapx+1][mapy] = 0;

//            for (int i = 0 ; i < 9 ; i++){
//                if (gp.getTileM().getTiles()[tileNums[i]].isDestructible()){
//                    gp.getTileM().getMapTileNum()[mapx][mapy]= 0;
//                }
//
//            }
        }
    }

}
