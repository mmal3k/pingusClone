package view;

import model.object.OBJ_DOOR;
import model.object.OBJ_PORTAL;

import java.awt.*;

public class ObjectView {
    private GamePanel gp ;
    public ObjectView(GamePanel gp){
        this.gp= gp;
    }

    public void setObject(){
        switch (gp.getTileM().map) {
            case "map01" -> {
                gp.getObj()[0] = new OBJ_DOOR();
                gp.getObj()[0].setObjX(4 * gp.getTileSize());
                gp.getObj()[0].setObjY(9 * gp.getTileSize());

                gp.getObj()[1] = new OBJ_PORTAL();
                gp.getObj()[1].setObjX(20 * gp.getTileSize());
                gp.getObj()[1].setObjY(13 * gp.getTileSize());
            }
            case "map02" -> {
                gp.getObj()[0] = new OBJ_DOOR();
                gp.getObj()[0].setObjX(5 * gp.getTileSize());
                gp.getObj()[0].setObjY(9 * gp.getTileSize());
                gp.getObj()[1] = new OBJ_PORTAL();
                gp.getObj()[1].setObjX(20 * gp.getTileSize());
                gp.getObj()[1].setObjY(13 * gp.getTileSize());
            }
            case "map03" -> {
                gp.getObj()[0] = new OBJ_DOOR();
                gp.getObj()[0].setObjX(6 * gp.getTileSize());
                gp.getObj()[0].setObjY(9 * gp.getTileSize());
                gp.getObj()[1] = new OBJ_PORTAL();
                gp.getObj()[1].setObjX(20 * gp.getTileSize());
                gp.getObj()[1].setObjY(13 * gp.getTileSize());
            }
        }

    }


    public void draw(Graphics2D g2, GamePanel gp){
        for (int i = 0; i < gp.getObj().length ; i++){
            if (gp.getObj()[i] !=null){
                int [] x;
                int [] y;
                switch (gp.getObj()[i].getName()){

                    case"door":
                        g2.setColor(gp.getObj()[i].getColor());
                        x = new int[]{gp.getObj()[i].getObjX(), gp.getObj()[i].getObjX() +gp.getTileSize() , gp.getObj()[i].getObjX() +(int)(gp.getTileSize()/ 2)  };
                        y = new int[]{gp.getObj()[i].getObjY(), gp.getObj()[i].getObjY(), gp.getObj()[i].getObjY() + gp.getTileSize()};
                        g2.fillPolygon(x,y,3);
                        break;
                    case"portal":
                        g2.setColor(gp.getObj()[i].getColor());
                        x = new int[]{gp.getObj()[i].getObjX(), gp.getObj()[i].getObjX() +gp.getTileSize() , gp.getObj()[i].getObjX() +(int)(gp.getTileSize()/ 2)  };
                        y = new int[]{gp.getObj()[i].getObjY() + gp.getTileSize()  , gp.getObj()[i].getObjY() + gp.getTileSize(), gp.getObj()[i].getObjY()};
                        g2.fillPolygon(x,y,3);
                        break;
                }

            }
        }
    };
}
