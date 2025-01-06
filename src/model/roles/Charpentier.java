package model.roles;

import controller.CollisonChecker;
import model.Player;
import view.GamePanel;

public class Charpentier extends NormalRoleDecorator implements Role{

    private int built;
    private int lastX;


    public Charpentier(NormalRole normalRole){
        super(normalRole);
        built = 0;
    }

    public boolean canbuild( int x){
        return lastX != x;
    }
    @Override
    public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
        if (built == 5 ){
            player.setRole(new NormalRole());
        }

        int x = (player.getPlayerX()+ gp.halfTileSize) / gp.getTileSize();
        int y = (player.getPlayerY() + gp.halfTileSize) / gp.getTileSize();


        if (player.getDirection().equals("left")){
            if (!gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x-1][y]].isCollision()  &&canbuild(x-1)){
                gp.getTileM().getMapTileNum()[x-1][y]=1;
                lastX=x-1;
                built ++;
            } else normalRoleDecorator.move(gp, player, cChecker);
        } else if (player.getDirection().equals("right")){
            if (!gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x+1][y]].isCollision() &&canbuild(x+1)){
                gp.getTileM().getMapTileNum()[x+1][y]=1;
                lastX=x+1;
                built ++;
            } else normalRoleDecorator.move(gp, player, cChecker);
        } else normalRoleDecorator.move(gp, player, cChecker);



    }
}

// HADA LCODE LOUWEL li Derto

//        private NormalRole normale;
//        private int numPas;
//        private GamePanel gp;
//        private Player player;
//        private boolean moved ;
//        public Charpentier(GamePanel gp,Player player){
//            this.gp=gp;
//            this.player=player;
//            normale = new NormalRole();
//            numPas = 0;
//        }
//
//        public boolean construire(int x,int y){
//            if (player.getDirection().equals("right")
//                    && !gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x+1][y]].isCollision()
//                    && !player.isGoingUp ){
//                gp.getTileM().getMapTileNum()[x+1][y]=1;
//
//                return true;
//            } else if (player.getDirection().equals("left")
//                    &&!gp.getTileM().getTiles()[gp.getTileM().getMapTileNum()[x-1][y]].isCollision()
//                    && !player.isGoingUp){
//                gp.getTileM().getMapTileNum()[x-1][y]=1;
//
//                return true;
//            }
//            else return false;
//        }
//
//
//
//        @Override
//        public void move(GamePanel gp, Player player, CollisonChecker cChecker) {
//            int mapx = (player.getPlayerX() + gp.halfTileSize )  / gp.getTileSize() ;
//            int mapy = (player.getPlayerY() + gp.halfTileSize) / gp.getTileSize();
//
//            if (numPas==5){
//                player.setRole(new NormalRole());
//                System.out.println("new Role");
//                return;
//            }
//            if (construire(mapx,mapy)){
//                System.out.println("construire");
//                numPas+=1;
//
//            }
//            else {
//                normale.move(gp, player, cChecker);
//            }

