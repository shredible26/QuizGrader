import javax.swing.*;

public class Canvas {
    private QGImage image;
    private JOptionPane pane;

    public Canvas(QGImage image) {
        this.image = image;
        pane = new JOptionPane(image.getIcon());
    }

    public String showInputDialog() {
        return pane.showInputDialog(image.getIcon());
    }
}
