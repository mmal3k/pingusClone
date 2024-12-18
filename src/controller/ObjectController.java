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
                    System.out.println("poussins sortis :"+i);
                    gp.getPlayers().set(playerIndex,null);
                    break;
            }
        }
    }
    public String getName() {
        return name;
    }
}