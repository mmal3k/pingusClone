package view;

import controller.CollisonChecker;
import controller.GameController;
import controller.ObjectController;
import model.entity.Player;
import model.object.SuperObject;
import model.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 24;
    private final int maxScreenRow = 18;
    private final int screenWidth = tileSize * maxScreenCol;
    private final int screenHeight = tileSize * maxScreenRow;
    private final int FPS = 60;
    private ArrayList<Player> players = new ArrayList<>();
    private TileManager tileM = new TileManager(this);
    private TileView tileView  = new TileView(this);
    private CollisonChecker cCheker = new CollisonChecker(this);
    private Thread gameThread;
    private PlayersView playersView = new PlayersView(this , 2) ;
    private GameController gc = new GameController(this,playersView);
    private SuperObject[] obj = new SuperObject[10];
    private ObjectView objectView = new ObjectView(this);
    private ObjectController objectController = new ObjectController(this);
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.RED);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    public void setupGame(){
        objectView.setObject();
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
        playersView.startAddThread();
    }
    @Override
    public void run() {
        double drawIntervale = 1000000000/FPS;  // 1 seconds devisee par le fps
        double delta = 0 ; //
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
        for (Player player : players) {
            gc.movePlayer(player);
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g ;
        tileView.draw(g2);
        objectView.draw(g2,this);
        playersView.draw(g2);

    }


    public int getTileSize() {
        return tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public TileManager getTileM() {
        return tileM;
    }

    public CollisonChecker getcCheker() {
        return cCheker;
    }

    public SuperObject[] getObj() {
        return obj;
    }

    public ObjectController getObjectController() {
        return objectController;
    }

}
