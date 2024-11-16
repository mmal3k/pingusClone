package view;

import model.entity.Player;

import java.awt.*;

public class PlayerView {
    private Player player;
    GamePanel gp ;
    public PlayerView(GamePanel gp){
        this.gp = gp;
        this.player = gp.player ;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.GREEN);
//        g2.fillRect(player.getPlayerX() + player.solidArea.x, player.getPlayerY() + player.solidArea.y, player.solidArea.width, player.solidArea.height );
        g2.fillRect(player.getPlayerX() , player.getPlayerY() , gp.tileSize, gp.tileSize) ;

//        g2.fillOval(player.getPlayerX() , player.getPlayerY() , gp.tileSize,  gp.tileSize);

    }

}
