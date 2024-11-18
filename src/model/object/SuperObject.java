package model.object;

import java.awt.*;

public abstract class SuperObject {
    public String name;
    public Color color;
    public boolean collison = false;
    public int objX,objY ;
    public Rectangle solidArea = new Rectangle(0,0,48,48);

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

}
