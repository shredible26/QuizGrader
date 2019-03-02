import javax.swing.*;
import java.awt.*;

public class CanvasContainer {
    public JFrame frame;
    public Canvas canvas;

    public CanvasContainer(String name, QGImage image) {

        frame = new JFrame(name);
        this.canvas = new Canvas(image, name);

        frame.setContentPane(canvas.getMainPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void display() {
        frame.setVisible(true);
    }

    public void setLocation(int x, int y) {
        frame.setLocation(new Point(x, y));
    }
}
