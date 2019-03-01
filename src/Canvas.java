import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canvas {
    private JComboBox menu;
    private JButton submit;
    private JTextField labels;
    private JTextField score;
    private JLabel image;
    private JPanel mainPanel;

    public Canvas() {

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(labels.getText());
            }
        });
    }

    public void setImage(QGImage image) {
        this.image = new JLabel(image.getIcon());
        this.image.setVisible(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
