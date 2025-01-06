package controller;

import view.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }


    public String role = "";

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Handle title screen state
        if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0) {
                switch (code) {
                    case KeyEvent.VK_DOWN:
                        gp.ui.commandNum++;
                        if (gp.ui.commandNum > 1) {
                            gp.ui.commandNum = 0;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        gp.ui.commandNum--;
                        if (gp.ui.commandNum < 0) {
                            gp.ui.commandNum = 1;
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        if (gp.ui.commandNum == 0) {

                            gp.ui.titleScreenState = 1;
                        }
                        if (gp.ui.commandNum == 1) {
                            System.exit(0);
                        }
                        resetCommand();
                        break;
                }
            } else if (gp.ui.titleScreenState == 1) {
                switch (code) {
                    case KeyEvent.VK_DOWN :
                        gp.ui.commandNum++;
                        if (gp.ui.commandNum > 2) {
                            gp.ui.commandNum = 0;
                        }
                        break;
                    case KeyEvent.VK_UP :
                        gp.ui.commandNum--;
                        if (gp.ui.commandNum < 0) {
                            gp.ui.commandNum = 2;
                        }
                        break;
                    case KeyEvent.VK_ENTER :
                        gp.getTileM().setMap("map0" + (gp.ui.commandNum + 1));
                        gp.gameState = gp.playState;
                        gp.restart();
                        gp.getObjectView().setObject();
                        gp.getPlayersView().startAddThread();
                        resetCommand();
                        break;
                }
            }
        }

        // Handle play and pause states
        if (gp.gameState == gp.playState || gp.gameState == gp.pauseState) {

            switch (code) {
                case KeyEvent.VK_ESCAPE:
                    if (gp.gameState == gp.playState) {
                        gp.gameState = gp.pauseState;
                    } else if (gp.gameState == gp.pauseState) {
                        gp.gameState = gp.playState;
                    }
                    break;
            }
            if (gp.gameState == gp.pauseState) {
                switch (code) {
                    case KeyEvent.VK_DOWN :
                        gp.ui.commandNum++;
                        if (gp.ui.commandNum > 2) {
                            gp.ui.commandNum = 0;
                        }
                        break;
                    case KeyEvent.VK_UP :
                        gp.ui.commandNum--;
                        if (gp.ui.commandNum < 0) {
                            gp.ui.commandNum = 2;
                        }
                        break;
                    case KeyEvent.VK_ENTER :
                        if (gp.ui.commandNum == 0) {
                            gp.gameState = gp.titleState;
                            gp.restart();
                            gp.ui.titleScreenState = 1;
                        }
                        if (gp.ui.commandNum == 1) {
                            // to implement later
                            
                            gp.ui.fullScreen = !gp.ui.fullScreen;
                        }
                        if (gp.ui.commandNum == 2) {
                            // to implement later
                            System.exit(0);
                        }

                        resetCommand();
                        break;
                }
            }
            if (gp.gameState == gp.playState){

                switch (code) {
                    case KeyEvent.VK_1:
                        role = "Role 1";
                        gp.ui.showNotif("you choosed Parachutiste");
                        break;
                    case KeyEvent.VK_2:
                        role = "Role 2";
                        gp.ui.showNotif("you choosed Blocker");
                        break;
                    case KeyEvent.VK_3:
                        role = "Role 3";
                        gp.ui.showNotif("you choosed Tunnerlier");
                        break;
                    case KeyEvent.VK_4:
                        role = "Role 4";
                        gp.ui.showNotif("you choosed Grimpeur");
                        break;
                    case KeyEvent.VK_5:
                        role = "Role 5";
                        gp.ui.showNotif("you choosed Foreur");
                        break;
                    case KeyEvent.VK_6:
                        role = "Role 6";
                        gp.ui.showNotif("you choosed Bomber");
                        break;
                    case KeyEvent.VK_7:
                        role = "Role 7";
                        gp.ui.showNotif("you choosed Charpentier");
                        break;
                    case KeyEvent.VK_T:
                        checkDrawTime = !checkDrawTime; // Toggle draw time debug
                        break;
                }

            }


        }




        if (gp.gameState == gp.gameOverState || gp.gameState == gp.wonState) {

                switch (code) {
                    case KeyEvent.VK_DOWN:
                        gp.ui.commandNum++;
                        if (gp.ui.commandNum > 1) {
                            gp.ui.commandNum = 0;
                        }
                        break;
                    case KeyEvent.VK_UP:
                        gp.ui.commandNum--;
                        if (gp.ui.commandNum < 0) {
                            gp.ui.commandNum = 1;
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        if (gp.ui.commandNum == 0) {
                            gp.restart();
                            gp.gameState = gp.titleState ;
                            gp.ui.titleScreenState = 1 ;

                        }
                        if (gp.ui.commandNum == 1) {
                            System.exit(0);
                        }
                        resetCommand();
                        break;
                }

        }



    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    public void resetCommand () {
        gp.ui.commandNum = 0;
    }

    public void presseKey(int j) {

    }
}