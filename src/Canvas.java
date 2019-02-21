import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas {

    private JPanel panel;
    private JFrame frame;
    private QGImage image;

    private ArrayList<String> labels;

    private JTextField textField;
    String textInput;

    public Canvas(QGImage image, String name) {
        this.panel = new JPanel();
        this.image = image;
        this.frame = new JFrame(name);
        this.labels = new ArrayList<>();

        this.textField = new JTextField("new label");
        textInput = textField.getText();

        JLabel labelFromImage = new JLabel(image.getIcon(), JLabel.RIGHT);
        labelFromImage.setVisible(true);

        panel.add(labelFromImage);
        panel.add(textField);

        panel.setVisible(true);
    }

    public void addLabel(String label) {
    }

    public ArrayList<String> display() {
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        String[] labels = textInput.split(", ");
        for (String label : labels) {
            this.labels.add(label);
        }
        return this.labels;
    }
}