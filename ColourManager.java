import java.awt.Color;
public class ColourManager {
    private CircularLinkedList<Color> colors;

    public ColourManager(){
        colors = new CircularLinkedList<>();
        loadColors();
    }

    private void loadColors() {
        colors.add(Color.PINK);
        colors.add(Color.cyan);
        colors.add(Color.GRAY);
        colors.add(Color.yellow);
        colors.add(Color.MAGENTA);
        colors.add(Color.DARK_GRAY);
    }

    public Color getCurrentColor() {
        return colors.getCurrent();
    }

    public void moveToNextColor() {
        colors.moveNext();
    }

    public boolean isColorListEmpty() {
        return colors.isEmpty();
    }


}
