package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


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
                break;
            case KeyEvent.VK_2 :
                role = "Role 2";
                break;
            case KeyEvent.VK_3 :
                role = "Role 3";
                break;
            case KeyEvent.VK_4 :
                role = "Role 4";
                break;
            case KeyEvent.VK_5 :
                role = "Role 5";
                break;
            case KeyEvent.VK_6 :
                role = "Role 6";
                break;
            case KeyEvent.VK_7 :
                role = "Role 7";
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
