import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputDialog {
    private static class ArabicKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            char typedChar = e.getKeyChar();
            if (!isArabicCharacter(typedChar)) {
                JOptionPane.showMessageDialog(null, "Please enter only Arabic characters.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                e.consume();
            }
        }
    
        private boolean isArabicCharacter(char c) {
            return (c >= '\u0600' && c <= '\u06FF') ||
                   (c >= '\u0750' && c <= '\u077F') ||
                   (c >= '\u08A0' && c <= '\u08FF') || 
                    (c >= '\uFB50' && c <= '\uFDFF') ||
                    (c >= '\uFE70' && c <= '\uFEFF') ||
                   (c == ' ' || c == '?' || c == '،' || c == '؛' || c == '؟'); // Allowed punctuation
        }

        // private boolean isArabicCharacter(char c) {
        //     return (c >= '\u0600' && c <= '\u06FF') ||
        //            (c >= '\u0750' && c <= '\u077F') ||
        //            (c >= '\u08A0' && c <= '\u08FF') || 
        //            (c == ' ' || c == '?' || c == '،' || c == '؛' || c == '؟'); // Allowed punctuation
        // }
    }
    public String getInput() {
        JTextField textField = new JTextField();
        textField.addKeyListener(new ArabicKeyListener());

        int option = JOptionPane.showConfirmDialog(null, textField, "Enter Arabic text:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option != JOptionPane.OK_OPTION) {
            System.exit(0); 
        }
        String input = textField.getText().trim(); 
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input cannot be empty. Please enter Arabic text.", "Error", JOptionPane.ERROR_MESSAGE);
            return getInput(); 
        }

        return input; 
    }

}