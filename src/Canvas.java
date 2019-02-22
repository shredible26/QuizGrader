import javax.swing.*;

public class Canvas {

    private JPanel panel;
    private JFrame frame;

    private QGImage image;
    private JTextField textField;
    private DropDownMenu menu;

    public Canvas(QGImage image, String name) {

        this.image = image;
        this.textField = new JTextField();
        this.menu = new DropDownMenu();

        this.frame = new JFrame(name);
        this.panel = new JPanel();

        panel.add(new JLabel(image.getIcon()));
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