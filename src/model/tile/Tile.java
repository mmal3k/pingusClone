package model.tile;

import java.awt.*;

public class Tile {
    private Color color ;
    private boolean collision ;
    private boolean destructible = true;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isCollision() {
        return collision;
    }

    public boolean isDestructible(){ return this.destructible;}


    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
