import javax.swing.*;

public class CanvasContainer {
    public JFrame frame;

    public CanvasContainer(String name, QGImage image) {
        frame = new JFrame(name);
        frame.setContentPane(new Canvas().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void display() {
        frame.setVisible(true);
    }
}
