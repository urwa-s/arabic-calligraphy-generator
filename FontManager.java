import javax.swing.JOptionPane;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class FontManager {
    private CustomHashMap<String, Font> fonts;
    private Font selectedFont;

    public FontManager() {
        fonts = new CustomHashMap<>();
        loadFontsFromDirectory();
        setFont(fonts.defaultFont());
    }

    private void loadFontsFromDirectory() {
        File fontDir = new File("Fonts");
        if (fontDir.exists() && fontDir.isDirectory()) {
            File[] fontFiles = fontDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".otf") || name.toLowerCase().endsWith(".ttf"));
            if (fontFiles != null) {
                for (File fontFile : fontFiles) {
                    String fontName = fontFile.getName().replaceFirst("\\.(otf|ttf)$", "");
                    loadFont(fontName, fontFile, 30f);
                }
            }
        } else {
            System.out.println("Invalid directory: " + fontDir.getAbsolutePath());
        }
    }

    private void loadFont(String fontName, File fontFile, float size) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            fonts.put(fontName, font.deriveFont(size));
        } catch (FontFormatException | IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading font: " + fontName + "\n" + e.getMessage());
        }
    }

    public void setFont(String fontName) {
        Font fontValue = fonts.get(fontName);
        if (fontValue != null) {
            selectedFont = fontValue;
        } else {
            System.out.println("Font not found: " + fontName);
        }
    }

    public Font getFont() {
        return selectedFont;
    }

    public String[] getAvailableFontNames() {
        Object[] keys = fonts.keySet();
        String[] fontNames = new String[keys.length];
    
        for (int i = 0; i < keys.length; i++) {
            fontNames[i] = keys[i].toString();
        }
    
        return fontNames;
    }
}