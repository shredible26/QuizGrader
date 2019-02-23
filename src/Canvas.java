import javax.swing.*;

public class Canvas {

    private JFrame frame;
    private JPanel panel;

    private JTextField textField;

    private QGImage image;

    public Canvas(QGImage image, String name) {
        frame = new JFrame(name);
        panel = new JPanel();

        textField = new JTextField("Custom Labels");

        this.image = image;

        JLabel imageLabel = new JLabel(image.getIcon(), JLabel.CENTER);

        panel.add(imageLabel);
        panel.add(textField);

    }

    public void addLabel(String label) {
    }

    public void display() {
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}