package view;

import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;

public class FontManager {
    private static Font customFont;
    private static Font fallbackFont = new Font("Arial", Font.PLAIN, 15); // Fallback font

    // Font sizes
    public static final int SIZE_SMALL = 20;
    public static final int SIZE_MEDIUM = 45;
    public static final int SIZE_LARGE = 80;

    // Load the custom font
    static {
        try (InputStream is = FontManager.class.getResourceAsStream("/font/x12y16pxMaruMonica.ttf")) {
            if (is == null) {
                throw new IOException("Font file not found: /font/x12y16pxMaruMonica.ttf");
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception e) {
            System.err.println("Failed to load custom font. Using fallback font: " + e.getMessage());
            customFont = fallbackFont;
        }
    }

    // Get the custom font with the specified size and style
    public static Font getCustomFont(int style, int size) {
        return customFont.deriveFont(style, size);
    }

    // Get the fallback font with the specified size and style
    public static Font getFallbackFont(int style, int size) {
        return fallbackFont.deriveFont(style, size);
    }

    // Use this method to switch between custom and fallback fonts easily
    public static Font getFont(boolean useCustomFont, int style, int size) {
        return useCustomFont ? getCustomFont(style, size) : getFallbackFont(style, size);
    }
}