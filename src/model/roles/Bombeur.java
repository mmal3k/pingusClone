package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class Bombeur extends Role {
    int pas ;
    NormalRole normal;
    public Bombeur(){
        pas = 0;
        normal = new NormalRole();
    }

    private void boom(int x, int y, GamePanel gp){
        if (gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x][y]].isDestructible()){
            gp.getTileM().getMapTileNum()[x][y]=0;
        }
    }


    @Override
    public void move(GamePanel gp , Player player, CollisonChecker cChecker){
        normal.move(gp, player, cChecker);
        pas = pas + player.getSpeed();
        if (pas == gp.getTileSize()*2){
            gp.playSE(0);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int playerInd = gp.getPlayers().indexOf(player);
            gp.getPlayers().set(playerInd,null);
            System.out.println("BOOM!!!");
            int mapx = (player.getPlayerX() + gp.halfTileSize)  / gp.getTileSize();
            int mapy = (player.getPlayerY() +gp.halfTileSize) / gp.getTileSize();



            if (mapx==0){
                if (mapy == 0){
                    boom(mapx,mapy+1,gp );
                    boom(mapx,mapy+2,gp);
                    boom(mapx+1,mapy+1,gp);
                    boom(mapx+1,mapy,gp);
                    boom(mapx+2,mapy,gp);
                } else if (mapy ==17){
                    boom(mapx+1,mapy,gp);
                    boom(mapx+2,mapy,gp);
                    boom(mapx,mapy-1,gp);
                    boom(mapx,mapy-2,gp);
                    boom(mapx+1,mapy-1,gp);
                } else if (mapy == 1){
                    boom(mapx,mapy-1,gp);
                    boom(mapx,mapy+1,gp);
                    boom(mapx,mapy+2,gp);
                    boom(mapx+1,mapy+1,gp);
                    boom(mapx+1,mapy-1,gp);
                    boom(mapx+1,mapy,gp);
                    boom(mapx+2,mapy,gp);
                } else if (mapy ==16){
                    boom(mapx,mapy-1,gp);
                    boom(mapx,mapy-2,gp);
                    boom(mapx,mapy+1,gp);
                    boom(mapx+1,mapy+1,gp);
                    boom(mapx+1,mapy-1,gp);
                    boom(mapx+1,mapy,gp);
                    boom(mapx+2,mapy,gp);
                } else {
                    boom(mapx,mapy-1,gp);
                    boom(mapx,mapy-2,gp);
                    boom(mapx,mapy+1,gp);
                    boom(mapx,mapy+2,gp);
                    boom(mapx + 1,mapy+1,gp);
                    boom(mapx+1,mapy-1, gp);
                    boom(mapx+1,mapy, gp);
                    boom(mapx+2,mapy, gp);
                }
            }

            else if (mapx ==1){
                if (mapy == 0){
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx+1,mapy+1, gp);
                    boom(mapx+1,mapy, gp);
                    boom(mapx+2,mapy, gp);
                    boom(mapx - 1,mapy+1,gp );
                    boom(mapx - 1,mapy,gp );
                } else if (mapy ==17){
                    boom(mapx+1,mapy,gp );
                    boom(mapx+2,mapy,gp );
                    boom(mapx,mapy-1,gp );
                    boom(mapx,mapy-2,gp );
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx-1,mapy,gp );

                } else if (mapy == 1){
                    boom(mapx,mapy-1,gp );
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx+1,mapy,gp );
                    boom(mapx+2,mapy,gp );
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx-1,mapy,gp );

                } else if (mapy ==16){
                    boom(mapx,mapy-1,gp );
                    boom(mapx,mapy-2,gp );
                    boom(mapx,mapy+1,gp );
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx+1,mapy,gp );
                    boom(mapx+2,mapy,gp );
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx-1,mapy,gp );

                } else {
                    boom(mapx,mapy-1,gp );
                    boom(mapx,mapy-2, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx + 1,mapy+1,gp );
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx+1,mapy,gp );
                    boom(mapx+2,mapy,gp );
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx-1,mapy,gp );
                }
            }

            else if (mapx ==22){
                if (mapy == 0){
                    boom(mapx,mapy+1,gp );
                    boom(mapx,mapy+2,gp );
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy,gp );
                    boom(mapx - 1,mapy+1,gp );
                    boom(mapx - 1,mapy,gp );
                    boom(mapx-2,mapy,gp );

                } else if (mapy ==17){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy-2, gp);
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx-1,mapy-1, gp);
                    boom(mapx+1,mapy, gp);


                } else if (mapy == 1){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1, gp);
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx+1,mapy, gp);




                } else if (mapy ==16){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1, gp);
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy-2, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx+1,mapy,gp );



                } else {
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy-2, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1,gp );
                    boom(mapx+1,mapy,gp );

                }
            } else if (mapx==23){
                if (mapy == 0){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx,mapy+1,gp );
                    boom(mapx,mapy+2, gp);


                } else if (mapy ==17){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy-2, gp);

                } else if (mapy == 1){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1, gp);
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);

                } else if (mapy ==16){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy-2, gp);
                    boom(mapx,mapy+2, gp);


                } else {
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1, gp);
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx,mapy-2, gp);

                }
            }

            else {
                if (mapy == 0){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx+1,mapy, gp);
                    boom(mapx+2,mapy, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx+1,mapy+1,gp );

                } else if (mapy ==17){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx+1,mapy,gp );
                    boom(mapx+2,mapy, gp);
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy-2, gp);
                    boom(mapx+1,mapy-1, gp);


                } else if (mapy == 1){
                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1, gp);
                    boom(mapx+1,mapy,gp );
                    boom(mapx+2,mapy,gp );
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx,mapy+2, gp);
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1, gp);


                } else if (mapy ==16){

                    boom(mapx-1,mapy, gp);
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1, gp);
                    boom(mapx+1,mapy, gp);
                    boom(mapx+2,mapy,gp );
                    boom(mapx,mapy-1, gp);
                    boom(mapx,mapy-2, gp);
                    boom(mapx,mapy+1, gp);
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1,gp );


                } else {
                    boom(mapx-1,mapy,gp );
                    boom(mapx-2,mapy, gp);
                    boom(mapx-1,mapy+1,gp );
                    boom(mapx-1,mapy-1,gp );
                    boom(mapx+1,mapy,gp );
                    boom(mapx+2,mapy,gp );
                    boom(mapx,mapy-1,gp );
                    boom(mapx,mapy-2,gp );
                    boom(mapx,mapy+1,gp );
                    boom(mapx,mapy+2,gp );
                    boom(mapx+1,mapy+1,gp );
                    boom(mapx+1,mapy-1, gp);

                }
            }
//            gp.stopMusic();
        }
    }

}
