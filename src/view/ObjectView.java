package view;

import model.object.OBJ_DOOR;
import model.object.OBJ_PORTAL;

import java.awt.*;

public class ObjectView {
    GamePanel gp ;
    public ObjectView(GamePanel gp){
        this.gp= gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_DOOR();
        gp.obj[0].objX = 4 * gp.getTileSize() ;
        gp.obj[0].objY = 9 * gp.getTileSize();

        gp.obj[1] = new OBJ_PORTAL();
        gp.obj[1].objX = 20* gp.getTileSize();
        gp.obj[1].objY = 13 * gp.getTileSize();
    }


    public void draw(Graphics2D g2, GamePanel gp){
        for (int i =0 ; i < gp.obj.length ; i++){
            if (gp.obj[i] !=null){
                int [] x;
                int [] y;
                switch (gp.obj[i].name){

                    case"door":
                        g2.setColor(gp.obj[i].color);
                        x = new int[]{gp.obj[i].objX  , gp.obj[i].objX +gp.getTileSize() ,gp.obj[i].objX +(int)(gp.getTileSize()/ 2)  };
                        y = new int[]{gp.obj[i].objY , gp.obj[i].objY , gp.obj[i].objY + gp.getTileSize()};
                        g2.fillPolygon(x,y,3);
                        break;
                    case"portal":
                        g2.setColor(gp.obj[i].color);
                        x = new int[]{gp.obj[i].objX  , gp.obj[i].objX +gp.getTileSize() ,gp.obj[i].objX +(int)(gp.getTileSize()/ 2)  };
                        y = new int[]{gp.obj[i].objY + gp.getTileSize()  ,gp.obj[i].objY + gp.getTileSize(), gp.obj[i].objY };
                        g2.fillPolygon(x,y,3);
                        break;
                }

            }
        }
    };
}
