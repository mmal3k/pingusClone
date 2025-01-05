package model.object;

import java.awt.*;

public abstract class SuperObject {
    private String name;
    private Color color;
    private boolean collison = false;
    private int objX;
    private int objY ;
    private Rectangle solidArea = new Rectangle(0,0,48,48);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isCollison() {
        return collison;
    }

    public void setCollison(boolean collison) {
        this.collison = collison;
    }

    public int getObjX() {
        return objX;
    }

    public void setObjX(int objX) {
        this.objX = objX;
    }

    public int getObjY() {
        return objY;
    }

    public void setObjY(int objY) {
        this.objY = objY;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

}
