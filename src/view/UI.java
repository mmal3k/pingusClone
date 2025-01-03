package view;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp ;

    Font arial_15 ,arial_40 , arial_60;

    int messageCount = 0 ;

    public boolean gameFinished = false ;

    public boolean messageOn = false ;
    public String message = "";

    double playTime  ;

    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI (GamePanel gp) {
        this.gp = gp;
        arial_15 = new Font("Arial" , Font.PLAIN , 15);
        arial_40 = new Font("Arial" , Font.PLAIN , 40);
        arial_60 = new Font("Arial" , Font.BOLD , 60);
    }


    public void draw(Graphics2D g2) {

        if (gameFinished) {

            String text ;
            int textLength ;
            int x ;
            int y ;

            g2.setFont(arial_40);
            text = "all players left";
            textLength = (int) g2.getFontMetrics().getStringBounds(text , g2).getWidth();
            x=gp.getScreenWidth() / 2 - textLength/ 2;
            y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 3);
            g2.drawString(text, x, y);


            text = "your time is : "+dFormat.format(playTime) + " ! " ;
            textLength = (int) g2.getFontMetrics().getStringBounds(text , g2).getWidth();
            x=gp.getScreenWidth() / 2 - textLength/ 2;
            y = gp.getScreenHeight() / 2 - (gp.getTileSize() * 2);
            g2.drawString(text, x, y);

            g2.setFont(arial_60);
            g2.setColor(Color.yellow);
            text = "Congratulations";
            textLength = (int) g2.getFontMetrics().getStringBounds(text , g2).getWidth();
            x= gp.getScreenWidth() / 2 - textLength/ 2;
            y = gp.getScreenHeight() / 2 ;
            g2.drawString(text, x, y);


            gp.setGameThread(null);

        }else {
            g2.setFont(arial_15);
            g2.setColor(Color.white);
            //Time

            playTime += (double) 1 /60 ;
            g2.drawString("Time : "+dFormat.format(playTime) , 24 , 24);

            // Split the string into lines
            String[] lines = {
                    "Num 1 : Parachutiste",
                    "Num 2 : Blocker",
                    "Num 3 : Tunnelier",
                    "Num 4 : Grimpeur",
                    "Num 5 : not defined",
                    "Num 6 : not defined",
                    "Num 7 : not defined"
            };

            // Starting position for the text
            int x = gp.getScreenWidth() - 150;
            int y = 20; // Initial y position

            // Line height adjustment
            int lineHeight = g2.getFontMetrics().getHeight() + 5;

            // Draw each line
            for (String line : lines) {
                g2.drawString(line, x, y);
                y += lineHeight; // Move down for the next line
            }

            if (messageOn) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.getScreenWidth() / 2, gp.getTileSize() * 5);
                messageCount++;

                if (messageCount == 90) {
                    messageOn = false;
                    messageCount = 0;
                }
            }
        }

    }


    public void showNotif (String text) {
        messageOn = true;
        message = text;
        messageCount = 0;
    }

}
