package controller;

import view.GamePanel;

public class ObjectController {
    private CollisonChecker cCheker ;
    public ObjectController (GamePanel gp) {
        this.cCheker = gp.getcCheker();
    }
}
