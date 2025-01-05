package view;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    private final GamePanel gp;

    // Use this flag to switch between custom and fallback fonts
    private static final boolean USE_CUSTOM_FONT = true; // Set to false to use Arial

    private final DecimalFormat dFormat = new DecimalFormat("#0.00");

    private String[] staticLines = {
            "",
            "Num 1 : Parachutiste",
            "Num 2 : Blocker",
            "Num 3 : Tunnelier",
            "Num 4 : Grimpeur",
            "Num 5 : Foreur",
            "Num 6 : Bomber",
            "Num 7 : Charpentier",
            ""
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

    public int commandNum = 0 ;

    public int titleScreenState = 0 ; // 0 : the first screen || 1 : the second screen

    public boolean fullScreen = false ;
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
        if (gp.gameState == gp.titleState) {
            drawTitleScreen(g2);
        } else if (gp.gameState == gp.playState) {
            drawPlayTime(g2);
            drawStaticLines(g2);
            if (messageOn) {
                drawMessage(g2);
            }
        } else if (gp.gameState == gp.pauseState) {
            showPauseScreen(g2);
        } else if (gp.gameState == gp.wonState){
            drawGameOverScreen(g2);
        }else if (gp.gameState == gp.gameOverState) {
            drawLosingScreen(g2);
        }
    }

    private void showPauseScreen(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
        g2.setColor(Color.WHITE);
        drawCenteredText(g2, "PAUSED", gp.getTileSize() * 3, 0);

        drawSubWindow(g2 , gp.getScreenWidth() / 2 - gp.getTileSize() * 4 ,gp.getTileSize() * 5 , gp.getTileSize() * 8 , gp.getTileSize() * 7 , 200, null);

        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_MEDIUM));
        g2.setColor(Color.white);
        String text = "MAIN MENU" ;
        int y = gp.getTileSize() * 7 ;
        drawCenteredText(g2 , text , y , 0);

        if (commandNum == 0){
            drawCenteredText(g2 , ">" , y , -130);
        }

        text = "FULL SCREEN" ;
        y = gp.getTileSize() * 9 ;
        drawCenteredText(g2 , text , y , 0);

        if (commandNum == 1){
            drawCenteredText(g2 , ">" , y , -130);
        }

        // Draw the utility rectangle (first rectangle)
        if (fullScreen) {
            drawSubRec(g2, gp.getScreenWidth() / 2 + 130, gp.getTileSize() * 9 - 25, 20, 20, 255, new Color(34, 139, 34)); // Forest Green
        }else {
            drawSubRec(g2, gp.getScreenWidth() / 2 + 130, gp.getTileSize() * 9 - 25, 20, 20, 255, new Color(255, 0, 0)); // Forest Green
        }

// Draw the margin rectangle (second rectangle) with a vibrant color
        drawSubRecStroke(g2, gp.getScreenWidth() / 2 + 130, gp.getTileSize() * 9 - 25, 20, 20, 255, new Color(255, 255, 255)); // Gold

        g2.setColor(Color.white);

        text = "QUIT" ;
        y = gp.getTileSize() * 11 ;
        drawCenteredText(g2 , text , y , 0);

        if (commandNum == 2){
            drawCenteredText(g2 , ">" , y , -130);
        }

    }

    private void drawPlayTime(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_SMALL));
        g2.setColor(Color.WHITE);
        playTime += (1.0 / 60); // Increment playtime
        g2.drawString("Time: " + dFormat.format(playTime), 24, 24);
    }

    private void drawStaticLines(Graphics2D g2) {

        int x = staticLinesX;
        int y = staticLinesY;
        drawSubStroke(g2 , x -15  , y - 10  , gp.getScreenWidth() - x +12, y * (staticLines.length ));

        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_SMALL));
        g2.setColor(Color.WHITE);

        x -= 5 ;
        y += 5;
        for (String line : staticLines) {
            g2.drawString(line, x, y);
            y += lineHeight;
        }

    }
    public void drawTitleScreen(Graphics2D g2) {
        if (titleScreenState == 0) {
            g2.setColor(new Color(70 , 120 , 80));
            g2.fillRect(0 , 0, gp.getScreenWidth() , gp.getScreenHeight());

            // Title Name
            int y  = gp.getTileSize() * 4;
            g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
            String text = "PINGUS GAME" ;

            g2.setColor(Color.BLACK);
            drawCenteredText(g2 , text , y + 5 , 5);


            g2.setColor(Color.WHITE);
            drawCenteredText(g2 , text , y , 0);


            g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_MEDIUM));

            text = "NEW GAME" ;
            y = gp.getTileSize() * 10 ;
            drawCenteredText(g2 , text , y , 0);

            if (commandNum == 0){
                drawCenteredText(g2 , ">" , y , -110);
            }

            text = "QUIT" ;
            y = gp.getTileSize() * 12 ;
            drawCenteredText(g2 , text , y , 0);

            if (commandNum == 1){
                drawCenteredText(g2 , ">" , y , -110);
            }

        } else if (titleScreenState == 1) {
            g2.setColor(Color.white);
            g2.setColor(new Color(190 , 120 , 80));
            g2.fillRect(0 , 0, gp.getScreenWidth() , gp.getScreenHeight());

            // Title Name
            int y  = gp.getTileSize() * 4;
            g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
            String text = "CHOOSE A MAP" ;

            g2.setColor(Color.BLACK);
            drawCenteredText(g2 , text , y + 5 , 5);

            g2.setColor(Color.WHITE);
            drawCenteredText(g2 , text , y , 0);

            g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_MEDIUM));

            text = "MAP 01" ;
            y = gp.getTileSize() * 10 ;
            drawCenteredText(g2 , text , y , 0);

            if (commandNum == 0){
                drawCenteredText(g2 , ">" , y , -110);
            }

            text = "MAP 02" ;
            y = gp.getTileSize() * 12 ;
            drawCenteredText(g2 , text , y , 0);

            if (commandNum == 1){
                drawCenteredText(g2 , ">" , y , -110);
            }

            text = "MAP 03" ;
            y = gp.getTileSize() * 14 ;
            drawCenteredText(g2 , text , y , 0);

            if (commandNum == 2){
                drawCenteredText(g2 , ">" , y , -110);
            }

        }

    }
    public void drawSubStroke(Graphics2D g2 , int x , int y , int width , int height) {
        g2.setColor(Color.cyan);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x , y , width  , height, 35,35);
    }

    public void drawSubWindow(Graphics2D g2, int x, int y, int width, int height, int opacity , Color c) {
        // Create a new Color object with the desired opacity (alpha value)
        Color translucentBlack = new Color(0, 0, 0, opacity); // RGB (0,0,0) is black, and opacity is the alpha value
        if (c != null) {
            translucentBlack = c ;
        }
        g2.setColor(translucentBlack);
        g2.fillRoundRect(x, y, width, height, 35, 35);
    }
    public void drawSubRec(Graphics2D g2, int x, int y, int width, int height, int opacity , Color c) {
        // Create a new Color object with the desired opacity (alpha value)
        Color translucentBlack = new Color(0, 0, 0, opacity); // RGB (0,0,0) is black, and opacity is the alpha value
        if (c != null) {
            translucentBlack = c ;
        }
        g2.setColor(translucentBlack);
        g2.fillRoundRect(x, y, width, height, 5, 5);
    }

    public void drawSubRecStroke(Graphics2D g2, int x, int y, int width, int height, int opacity , Color c) {
        // Create a new Color object with the desired opacity (alpha value)
        Color translucentBlack = new Color(0, 0, 0, opacity); // RGB (0,0,0) is black, and opacity is the alpha value
        if (c != null) {
            translucentBlack = c ;
        }
        g2.setColor(translucentBlack);


        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x , y , width  , height, 5,5);
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
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 - (gp.getTileSize() * 3) , 0);

        text = "Your time is: " + dFormat.format(playTime) + "!";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 - (gp.getTileSize() * 2) , 0);


        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
        g2.setColor(Color.YELLOW);
        text = "Congratulations";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 ,0);


        drawSubWindow(g2 , gp.getScreenWidth() / 2 - gp.getTileSize() * 4 ,gp.getTileSize() * 10 , gp.getTileSize() * 8 , gp.getTileSize() * 5 , 200 , null);

        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_MEDIUM));
        g2.setColor(Color.white);
        text = "Retry !" ;
        int y = gp.getTileSize() * 12 ;
        drawCenteredText(g2 , text , y , 0);

        if (commandNum == 0){
            drawCenteredText(g2 , ">" , y , -110);
        }

        text = "QUIT" ;
        y = gp.getTileSize() * 14 ;
        drawCenteredText(g2 , text , y , 0);

        if (commandNum == 1){
            drawCenteredText(g2 , ">" , y , -110);
        }
    }

    private void drawLosingScreen(Graphics2D g2) {
        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.PLAIN, FontManager.SIZE_MEDIUM));
        g2.setColor(Color.WHITE);

        String text = "Your time is: " + dFormat.format(playTime) + "!";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 - (gp.getTileSize() * 2) , 0);

        drawSubWindow(g2 , gp.getScreenWidth() / 2 - gp.getTileSize() * 4 ,gp.getScreenWidth() / 2 - gp.getTileSize() * 2 , gp.getTileSize() * 8 , gp.getTileSize() * 5 , 200 ,null);

        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
        g2.setColor(Color.BLUE);
        text = "LOST ! TRY AGAIN";
        drawCenteredText(g2, text, gp.getScreenHeight() / 2 , 0);

        // start

        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_MEDIUM));
        g2.setColor(Color.white);
        text = "Retry !" ;
        int y = gp.getTileSize() * 12 ;
        drawCenteredText(g2 , text , y , 0);

        if (commandNum == 0){
            drawCenteredText(g2 , ">" , y , -110);
        }

        text = "QUIT" ;
        y = gp.getTileSize() * 14 ;
        drawCenteredText(g2 , text , y , 0);

        if (commandNum == 1){
            drawCenteredText(g2 , ">" , y , -110);
        }

    }

//    private void showPauseScreen(Graphics2D g2) {
//        g2.setFont(FontManager.getFont(USE_CUSTOM_FONT, Font.BOLD, FontManager.SIZE_LARGE));
//        g2.setColor(Color.WHITE);
//        drawCenteredText(g2, "PAUSED", gp.getScreenHeight() / 2 , 0);
//    }

    private void drawCenteredText(Graphics2D g2, String text, int y , int offset) {
        int textLength = g2.getFontMetrics().stringWidth(text);
        int x = (gp.getScreenWidth() - textLength) / 2 + offset;
        g2.drawString(text, x, y);
    }

    public void showNotif(String text) {
        messageOn = true;
        message = text;
        messageCount = 0;
    }
}