package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {

    public String getRole() {
        return role;
    }

    private String role ;
    GamePanel gp ;
    public MenuPanel(GamePanel gp) {
        this.role = "";
        this.gp = gp;
        // Set the layout for the menu panel
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 1 row, 10 buttons (adjust as needed)
        setBackground(Color.DARK_GRAY);  // Optional: Set background color for the menu panel

        // Create and add buttons to the menu panel
        for (int i = 0; i < 5; i++) {
            JButton button = new JButton("Role " + (i + 1)); // Button text
            button.setPreferredSize(new Dimension(gp.getTileSize() * 2, gp.getTileSize()));   // Set button size (width x height)
            button.setFocusPainted(false);                    // Remove focus outline
            button.setBackground(Color.LIGHT_GRAY);           // Optional: Set button color
            button.setForeground(Color.BLACK);                // Optional: Set text color

            // Add an ActionListener to handle button clicks
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    role = button.getText();
                    System.out.println(button.getText() + " clicked!");
                }
            });

            // Add the button to the panel
            add(button);
        }
    }
}
