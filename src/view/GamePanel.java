package view;

import controller.CollisonChecker;
import controller.GameController;
import model.entity.Player;
import model.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 18;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int FPS = 60;


    public Player player = new Player(this);
    PlayerView playerView = new PlayerView(this );
    public TileManager tileM = new TileManager(this);
    TileView tileView  = new TileView(this);
    public CollisonChecker cCheker = new CollisonChecker(this);
    GameController gc = new GameController(this);

    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.RED);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawIntervale = 1000000000/FPS;  // 1 seconds devisee par le fps
        double delta = 0 ; // ??
        long lastTime = System.nanoTime(); //
        long currentTime ;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null){
            currentTime = System.nanoTime(); // getting the current time
            delta += (currentTime - lastTime) / drawIntervale ;
            timer += (currentTime - lastTime) ;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta -= 1;
                drawCount ++ ;
            }
            if (timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        gc.movePlayer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
        tileView.draw(g2);
        playerView.draw(g2);
    }
}
