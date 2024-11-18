package controller;

import view.GamePanel;

public class ObjectController {
    int i =0;
    String name;
    GamePanel gp;
    private CollisonChecker cCheker ;
    public ObjectController (GamePanel gp) {
        this.gp = gp;
        this.cCheker = gp.getcCheker();
    }


    public void interactWithObject(int index, int playerIndex){
        if (index != 999 && playerIndex != 999){
            String name = gp.obj[index].name;
//            System.out.println("player num  : "+ i);
            switch (name){
                case "door":

                    break;
                case "portal" :
                    i++;
                    System.out.println("poussins sortis :"+i);
                    gp.getPlayers().set(playerIndex,null);
                    break;
            }
        }
    }
}
