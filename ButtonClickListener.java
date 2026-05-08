import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ButtonClickListener implements ActionListener {
    private JLabel text;
    private FontManager manager;
    private ColourManager colour;
    private StateManager stateManager;

    public ButtonClickListener(FontManager fontManager, JLabel text, ColourManager color, StateManager stateManager) {
        this.manager = fontManager;
        this.text = text;
        this.colour = color;
        this.stateManager = stateManager; // Initialize StateManager
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (source.getText().equals("Colours")) {
            Color newtextColor = JColorChooser.showDialog(null, "Choose Font Color", text.getForeground());
            if (newtextColor != null) {
                text.setForeground(newtextColor);
                stateManager.addState(text.getBackground(), text.getFont(), newtextColor, text.getFont().getSize());
                JOptionPane.showMessageDialog(null, "Font colour changed");
            }
        } else if (source.getText().equals("Download")) {
            downloadAsPNG(); 
        } else if (source.getText().equals("Next")) {
            StateManager.State nextState = stateManager.getNextState();
            if (nextState != null) {
                applyState(nextState);
                JOptionPane.showMessageDialog(null, "Moved to next state.");
            } else {
                JOptionPane.showMessageDialog(null, "No next state available.");
            }
        } else if (source.getText().equals("Previous")) {
            StateManager.State previousState = stateManager.getPreviousState();
            if (previousState != null) {
                applyState(previousState);
                JOptionPane.showMessageDialog(null, "Moved to previous state.");
            } else {
                JOptionPane.showMessageDialog(null, "No previous state available.");
            }
        } else if (source.getText().equals("Background")) {
            if (!colour.isColorListEmpty()) {
                Color newColor = colour.getCurrentColor();
                text.setBackground(newColor);
                colour.moveToNextColor();
                stateManager.addState(newColor, text.getFont(), text.getForeground(), text.getFont().getSize());
                JOptionPane.showMessageDialog(null, "Changing color to: " + newColor);
            } else {
                JOptionPane.showMessageDialog(null, "No colors available.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source.getText().equals("Size")) {
            String input = JOptionPane.showInputDialog(null, "Enter font size (8-400):");
            try {
                int newSize = Integer.parseInt(input);
                if (newSize >= 8 && newSize <= 400) {
                    text.setFont(text.getFont().deriveFont((float) newSize));
                    stateManager.addState(text.getBackground(), text.getFont(), text.getForeground(), newSize);
                    JOptionPane.showMessageDialog(null, "Font size changed to: " + newSize);
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a number in the valid range (8-400).");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
            }
        } else if (source.getText().equals("Font")) {
            String[] fontOptions = manager.getAvailableFontNames();
            if (fontOptions.length > 0) {
                String selectedFont = (String) JOptionPane.showInputDialog(null, "Select a font:", "Font Selection", JOptionPane.QUESTION_MESSAGE, null, fontOptions, fontOptions[0]);
                if (selectedFont != null) {
                    manager.setFont(selectedFont);
                    Font font = manager.getFont(); 
                    text.setFont(font);
                    // Add the current state to the state manager
                    stateManager.addState(text.getBackground(), font, text.getForeground(), text.getFont().getSize());
                    JOptionPane.showMessageDialog(null, "Font changed to: " + selectedFont);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No fonts available.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void downloadAsPNG() {
        BufferedImage image = new BufferedImage(text.getWidth(), text.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        
        text.paint(g2d);
        g2d.dispose();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");   
        fileChooser.setSelectedFile(new File("output.png")); // Default file name
    
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                ImageIO.write(image, "PNG", fileToSave);
                JOptionPane.showMessageDialog(null, "Image saved successfully: " + fileToSave.getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    private void applyState(StateManager.State state) {
        text.setBackground(state.backgroundColor);
        text.setFont(state.font);
        text.setForeground(state.fontColor);
        text.setFont(text.getFont().deriveFont((float) state.textSize)); // Apply text size
    }
}