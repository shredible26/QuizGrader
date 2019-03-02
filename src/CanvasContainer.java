import javax.swing.*;
import java.awt.*;

public class CanvasContainer {
    public JFrame frame;
    public Canvas canvas; //is static, cannot reference nonstatic perspectives

    public CanvasContainer(String name, QGImage image) {

        frame = new JFrame(name);
        this.canvas = new Canvas(image);

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
