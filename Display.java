import javax.swing.JButton;        
import javax.swing.JFrame;         
import javax.swing.JLabel;         
import javax.swing.JPanel;         
import javax.swing.SwingConstants; 
import java.awt.BorderLayout;    
import java.awt.FlowLayout; 

public class Display extends JFrame {
    private JLabel text;
    private JButton download;
    private JButton background;
    private JButton previous;
    private JButton next;
    private JButton colours;
    private JButton size;
    private JButton font;
    private JPanel topPanel;
    private JPanel bottomPanel;

    public Display(String arabicText) {
        this.setTitle("Calligraphy Generator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        buildPanel(arabicText);
        setVisible(true);
    }

    private void buildPanel(String arabicText) {
        StateManager st = new StateManager();
        ColourManager color = new ColourManager();
        FontManager fontManager = new FontManager();
        text = new JLabel(arabicText);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setFont(fontManager.getFont());
        text.setBackground(color.getCurrentColor());
        color.moveToNextColor();
        text.setOpaque(true);

        background = new JButton("Background");
        download = new JButton("Download");
        previous = new JButton("Previous");
        next = new JButton("Next");
        colours = new JButton("Colours");
        size = new JButton("Size");
        font = new JButton("Font");

        background.addActionListener(new ButtonClickListener(fontManager, text, color, st));
        download.addActionListener(new ButtonClickListener(fontManager, text, color, st));
        previous.addActionListener(new ButtonClickListener(fontManager, text, color, st));
        next.addActionListener(new ButtonClickListener(fontManager, text, color, st));
        colours.addActionListener(new ButtonClickListener(fontManager, text, color, st));
        size.addActionListener(new ButtonClickListener(fontManager,text, color, st));
        font.addActionListener(new ButtonClickListener(fontManager, text, color, st));

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(colours);
        topPanel.add(size);
        topPanel.add(font);
        topPanel.add(background);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(previous);
        bottomPanel.add(download);
        bottomPanel.add(next);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(text, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        st.addState(text.getBackground(), text.getFont(), text.getForeground(), text.getFont().getSize());
    }
}