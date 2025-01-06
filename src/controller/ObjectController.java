    package controller;

    import view.GamePanel;

    public class ObjectController {


        private int i =0;
        private String name;
        private GamePanel gp;
        private CollisonChecker cCheker ;
        public ObjectController (GamePanel gp) {
            this.gp = gp;
            this.cCheker = gp.getcCheker();
        }


        public void interactWithObject(int index, int playerIndex){
            if (index != 999 && playerIndex != 999){
                String name = gp.getObj()[index].getName();
                switch (name){
                    case "door":

                        break;
                    case "portal" :
                        i++;
                        gp.playSE(7);
                        System.out.println("poussins sortis :"+i + " player num " +gp.getPlayersView().getPlayerNumber() );
                        gp.getPlayers().set(playerIndex,null);
                        if (i == gp.getPlayersView().getPlayerNumber() - 1) {
                            gp.playSE(6);
                            gp.gameState = gp.wonState ;
                        }

                        break;
                }
            }
        }
        public String getName() {
            return name;
        }

        public void restartObject () {
            i = 0;
        }


    }
