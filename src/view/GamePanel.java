package view;

import controller.CollisonChecker;
import controller.KeyHandler;
import controller.ObjectController;
import model.Player;
import model.object.SuperObject;
import model.tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale;
    private final int maxScreenCol = 24;
    private final int maxScreenRow = 18;
    private final int screenWidth = tileSize * maxScreenCol; // Original resolution
    private final int screenHeight = tileSize * maxScreenRow; // Original resolution
    private int screenWidth2; // Fullscreen resolution
    private int screenHeight2; // Fullscreen resolution
    private final int FPS = 60;
    private ArrayList<Player> players = new ArrayList<>();
    private TileManager tileM = new TileManager(this);
    private TileView tileView = new TileView(this);
    private CollisonChecker cCheker = new CollisonChecker(this);

    private Thread gameThread;
    private PlayersView playersView = new PlayersView(this, 1);

    private SuperObject[] obj = new SuperObject[10];
    private ObjectView objectView = new ObjectView(this);
    private ObjectController objectController = new ObjectController(this);

    // Key handler
    public KeyHandler keyH = new KeyHandler(this);

    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    // UI
    public UI ui = new UI(this);

    // Scaling factors
    private double scaleX;
    private double scaleY;

    public GamePanel() {
        // Initialize fullscreen resolution
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        DisplayMode dm = gd.getDisplayMode();
        screenWidth2 = dm.getWidth();
        screenHeight2 = dm.getHeight();

        // Calculate scaling factors
        scaleX = (double) screenWidth2 / screenWidth;
        scaleY = (double) screenHeight2 / screenHeight;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        this.addKeyListener(keyH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Adjust mouse coordinates for scaling
                int adjustedX = (int) (e.getX() / scaleX);
                int adjustedY = (int) (e.getY() / scaleY);
                Point clickPoint = new Point(adjustedX, adjustedY);

                for (Player player : players) {
                    if (player != null && player.getBounds().contains(clickPoint)) {
                        player.performClickAction();
                    }
                }
            }
        });
    }

    public void setupGame() {
        objectView.setObject();
        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        playersView.startAddThread();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 1 second divided by FPS
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime(); // Get the current time
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (gameState == playState) {
            // Start the player-adding thread only if it hasn't been started yet
            if (!playersView.isThreadRunning()) {
                playersView.startAddThread();
            }

            // Update player movements
            for (Player player : players) {
                if (player != null) {
                    player.movePlayer(this, player, cCheker);
                }
            }
        } else if (gameState == pauseState) {
            // Pause logic (if needed)
        } else {
            // Stop the player-adding thread if the game is not in playState
            if (playersView.isThreadRunning()) {
                playersView.stopAddThread();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Scale the graphics to fit the fullscreen resolution
        g2.scale(scaleX, scaleY);

        // Title Screen
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // DEBUG
            long drawStart = 0;
            if (keyH.checkDrawTime) {
                drawStart = System.nanoTime();
            }

            tileView.draw(g2);
            objectView.draw(g2, this);
            playersView.draw(g2);
            // UI
            ui.draw(g2);

            if (keyH.checkDrawTime) {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time : " + passed, 10, 400);
                System.out.println("draw time : " + passed);
            }
        }
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

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public PlayersView getPlayersView() {
        return playersView;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }
}