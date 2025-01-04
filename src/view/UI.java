package view;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    private final GamePanel gp;


    private Font arial15;
    private Font arial40;
    private Font arial60;

    private final DecimalFormat dFormat = new DecimalFormat("#0.00");

    private String[] staticLines = {
            "Num 1 : Parachutiste",
            "Num 2 : Blocker",
            "Num 3 : Tunnelier",
            "Num 4 : Grimpeur",
            "Num 5 : not defined",
            "Num 6 : not defined",
            "Num 7 : not defined"
    };

    private final int staticLinesX;
    private final int staticLinesY;
    private final int lineHeight;

    private int messageCount = 0;
    private boolean messageOn = false;
    private String message = "";

    private double playTime = 0;

    public boolean gameFinished = false;

    public boolean lost = false ;

    public UI(GamePanel gp) {
        this.gp = gp;

        // Initialize fonts immediately
        arial15 = new Font("Arial", Font.PLAIN, 15);
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial60 = new Font("Arial", Font.BOLD, 60);

        // Precompute static line dimensions
        staticLinesX = gp.getScreenWidth() - 150;
        staticLinesY = 20;

        // Calculate line height using a temporary Graphics object
        Graphics g = gp.getGraphics();
        if (g != null) {
            FontMetrics fm = g.getFontMetrics(arial15);
            lineHeight = fm.getHeight() + 5; // Add padding
        } else {
            lineHeight = 20; // Fallback value
        }
    }

    public void draw(Graphics2D g2) {

        if (gp.gameState == gp.playState) {


            if (lost) {
                drawLosingScreen(g2);
                return;
            }
            if (gameFinished) {
                drawGameOverScreen(g2);
                return;
            }

            // Draw time
            g2.setFont(arial15);
            g2.setColor(Color.white);
            playTime += (1.0 / 60); // Increment playtime
            g2.drawString("Time: " + dFormat.format(playTime), 24, 24);

            // Draw static lines
            drawStaticLines(g2);

            // Show notification
            if (messageOn) {
                g2.setFont(arial40); // Use preloaded font
                g2.setColor(Color.white);

                // Calculate the X position to center the text
                int textLength = g2.getFontMetrics().stringWidth(message);
                int x = (gp.getScreenWidth() - textLength) / 2; // Center the text on the X-axis

                // Calculate the Y position to place the text 3 tiles down
                int y = gp.getTileSize() * 3; // 3 tiles down from the top

                // Draw the message
                g2.drawString(message, x, y);

                // Increment message count and turn off the message after a delay
                if (++messageCount == 90) {
                    messageOn = false;
                    messageCount = 0;
                }
            }

        }else if (gp.gameState == gp.pauseState) {
            showPauseScreen(g2);
        }
    }

    private void drawGameOverScreen(Graphics2D g2) {
        g2.setFont(arial40);
        g2.setColor(Color.white);

        // Draw game over text
        String text = "All players left";
        int textLength = g2.getFontMetrics().stringWidth(text);
        int x = gp.getScreenWidth() / 2 - textLength / 2;
        int y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 3);
        g2.drawString(text, x, y);

        // Draw play time
        text = "Your time is: " + dFormat.format(playTime) + "!";
        textLength = g2.getFontMetrics().stringWidth(text);
        x = gp.getScreenWidth() / 2 - textLength / 2;
        y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 2);
        g2.drawString(text, x, y);

        // Draw congratulations
        g2.setFont(arial60);
        g2.setColor(Color.yellow);
        text = "Congratulations";
        textLength = g2.getFontMetrics().stringWidth(text);
        x = gp.getScreenWidth() / 2 - textLength / 2;
        y = gp.getScreenHeight() / 2;
        g2.drawString(text, x, y);

        gp.setGameThread(null); // Stop the game thread

        }

    public void drawLosingScreen (Graphics2D g2) {
        g2.setFont(arial40);
        g2.setColor(Color.white);

        // Draw play time
        String text = "Your time is: " + dFormat.format(playTime) + "!";
        int textLength = g2.getFontMetrics().stringWidth(text);
        int x = gp.getScreenWidth() / 2 - textLength / 2;
        int y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 2);
        g2.drawString(text, x, y);

        // Draw congratulations
        g2.setFont(arial60);
        g2.setColor(Color.BLUE);
        text = "lost ! try again";
        textLength = g2.getFontMetrics().stringWidth(text);
        x = gp.getScreenWidth() / 2 - textLength / 2;
        y = gp.getScreenHeight() / 2;
        g2.drawString(text, x, y);

        gp.setGameThread(null); // Stop the game thread
    }

    public void showPauseScreen(Graphics2D g2){


        g2.setFont(arial60);
        g2.setColor(Color.WHITE);
        String text = "PAUSED";
        int textLength = g2.getFontMetrics().stringWidth(text);
        int x = gp.getScreenWidth() / 2 - textLength / 2;
        int y = gp.getScreenHeight() / 2;
        g2.drawString(text, x, y);

    }

    private void drawStaticLines(Graphics2D g2) {
        int x = staticLinesX;
        int y = staticLinesY;

        for (String line : staticLines) {
            g2.drawString(line, x, y);
            y += lineHeight;
        }
    }

    public void showNotif(String text) {
        messageOn = true;
        message = text;
        messageCount = 0;
    }
}