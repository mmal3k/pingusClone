package controller;

import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp ;
    public boolean checkDrawTime = false ;
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }


    public boolean [] roles = new boolean[7];
    public String role = "";


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_1 :
                role = "Role 1";
                gp.ui.showNotif("you choosed Parachutiste");
                break;
            case KeyEvent.VK_2 :
                role = "Role 2";
                gp.ui.showNotif("you choosed Blocker");
                break;
            case KeyEvent.VK_3 :
                role = "Role 3";
                gp.ui.showNotif("you choosed Tunnerlier");
                break;
            case KeyEvent.VK_4 :
                role = "Role 4";
                gp.ui.showNotif("you choosed Grimpeur");
                break;
            case KeyEvent.VK_5 :
                role = "Role 5";
                gp.ui.showNotif("not defined");
                break;
            case KeyEvent.VK_6 :
                role = "Role 6";
                gp.ui.showNotif("not defined");
                break;
            case KeyEvent.VK_7 :
                role = "Role 7";
                gp.ui.showNotif("not defined");
                break;
            case KeyEvent.VK_T :
                if (checkDrawTime == false) {
                    checkDrawTime = true;
                }else {
                    checkDrawTime = false;
                }
                break;
            case KeyEvent.VK_ESCAPE :
                if (gp.gameState == gp.playState) {
                    System.out.println("paused");
                    gp.gameState = gp.pauseState;
                }if (gp.gameState == gp.pauseState) {
                    System.out.println("unpaused");
                    gp.gameState = gp.playState;
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



    public void presseKey (int j) {
        for (int i =0 ; i <7 ;i ++) {
            if (i == j) {
                roles[i] = true;
            }else {
                roles[i] = false ;
            }
        }
    }
}
