import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canvas {
    private JPanel mainPanel;
    private JLabel imageLabel;
    private JComboBox menu;
    private JTextField customTags;
    private JTextField score;
    private JButton Submit;

    QGImage image;
    String tags;

    public Canvas(QGImage image) {

        this.image = image;
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tags = customTags.getText();
                System.out.println(tags);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        this.imageLabel = new JLabel(image.getIcon());
    }
}
