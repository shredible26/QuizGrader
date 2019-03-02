import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Canvas {
    private JPanel mainPanel;
    private JLabel imageLabel;
    private JComboBox menu;
    private JTextField customTags;
    private JTextField score;
    private JButton Submit;

    QGImage image;
    ArrayList<String> tags;

    public Canvas(QGImage image, String name) {

        this.image = image;
        this.tags = new ArrayList<>();

        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] customTagsCollected = customTags.getText().split(", ");
                for (String label : customTagsCollected) {
                    tags.add(label);
                }
            }
        });
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customTags.getText().equals("")) customTags.setText(menu.getSelectedItem().toString());
                else customTags.setText(customTags.getText() + ", " + menu.getSelectedItem().toString());
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