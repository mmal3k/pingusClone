package view;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    private final GamePanel gp;

    // Use this flag to switch between custom and fallback fonts
    private static final boolean USE_CUSTOM_FONT = true; // Set to false to use Arial

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
    public boolean lost = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        // Precompute static line dimensions
        staticLinesX = gp.getScreenWidth() - 150;
        staticLinesY = 20;

        // Calculate line height using a temporary Graphics object
        Graphics g = gp.getGraphics();
        if (g != null) {
            FontMetrics fm = g.getFontMetrics(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_SMALL));
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

            drawPlayTime(g2);
            drawStaticLines(g2);

            if (messageOn) {
                drawMessage(g2);
            }

        } else if (gp.gameState == gp.pauseState) {
            showPauseScreen(g2);
        }
    }

    private void drawPlayTime(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_SMALL));
        g2.setColor(Color.WHITE);
        playTime += (1.0 / 60); // Increment playtime
        g2.drawString("Time: " + dFormat.format(playTime), 24, 24);
    }

    private void drawStaticLines(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_SMALL));
        g2.setColor(Color.WHITE);

        int x = staticLinesX;
        int y = staticLinesY;

        for (String line : staticLines) {
            g2.drawString(line, x, y);
            y += lineHeight;
        }
    }

    private void drawMessage(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_MEDIUM));
        g2.setColor(Color.WHITE);

        int textLength = g2.getFontMetrics().stringWidth(message);
        int x = (gp.getScreenWidth() - textLength) / 2;
        int y = gp.getTileSize() * 3;

        g2.drawString(message, x, y);

        if (++messageCount == 90) {
            messageOn = false;
            messageCount = 0;
        }
    }

    private void drawGameOverScreen(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_MEDIUM));
        g2.setColor(Color.WHITE);

        String text = "All players left";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 - (gp.getTileSize() * 3));

        text = "Your time is: " + dFormat.format(playTime) + "!";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 - (gp.getTileSize() * 2));

        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
        g2.setColor(Color.YELLOW);
        text = "Congratulations";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2);

        gp.setGameThread(null); // Stop the game thread
    }

    private void drawLosingScreen(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_MEDIUM));
        g2.setColor(Color.WHITE);

        String text = "Your time is: " + dFormat.format(playTime) + "!";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 - (gp.getTileSize() * 2));

        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
        g2.setColor(Color.BLUE);
        text = "Lost! Try again";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2);

        gp.setGameThread(null); // Stop the game thread
    }

    private void showPauseScreen(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, "PAUSED", gp.getScreenHeight() / 2);
    }

    private void drawCenteredText(Graphics2D g2, String text, int y) {
        int textLength = g2.getFontMetrics().stringWidth(text);
        int x = (gp.getScreenWidth() - textLength) / 2;
        g2.drawString(text, x, y);
    }

    public void showNotif(String text) {
        messageOn = true;
        message = text;
        messageCount = 0;
    }
}