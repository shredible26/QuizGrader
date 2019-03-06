import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Canvas {

    private JFrame frame;

    private JPanel mainPanel;
    private JLabel imageLabel;
    private JComboBox menu;
    private JTextField customTags;
    private JTextField score;
    private JButton submit;

    private QGImage image;
    private ArrayList<String> tags;
    private Score scoreObject;

    public Canvas(QGImage image, String name, int problemNum) {

        this.image = image;
        this.tags = new ArrayList<>();

        frame = new JFrame();
        frame.add(mainPanel);

        menu.setBackground(Color.LIGHT_GRAY);
        customTags.setBackground(Color.LIGHT_GRAY);
        score.setBackground(Color.LIGHT_GRAY);
        submit.setBackground(new Color(147, 163, 188));

        mainPanel.setBackground(Color.DARK_GRAY);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String[] customTagsCollected = customTags.getText().split(", ");

                for (String label : customTagsCollected) {
                    tags.add(label);
                    if (!UserInteractiveGrading.menuLabels.contains(label)) UserInteractiveGrading.menuLabels.add(label);
                }

                scoreObject = new Score(Double.parseDouble(score.getText().split("/")[0]), Double.parseDouble(score.getText().split("/")[1]));
                frame.setVisible(false);

                for (String tag : tags) {
                    UserInteractiveGrading.tags.get(name).get(problemNum).add(tag);
                }
                UserInteractiveGrading.scores.get(name).put(problemNum, scoreObject);
                UserInteractiveGrading.updateCanvi();

                System.out.println("tags (specified): ");
                System.out.println(UserInteractiveGrading.tags);
                System.out.println("scores: ");
                System.out.println(UserInteractiveGrading.scores);
                System.out.println("all tags: ");
                System.out.println(UserInteractiveGrading.menuLabels);
            }
        });
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (customTags.getText().equals("")) customTags.setText(menu.getSelectedItem().toString());
                else customTags.setText(customTags.getText() + ", " + menu.getSelectedItem().toString());
                menu.removeItem(menu.getSelectedItem());
            }
        });
    }

    private void createUIComponents() {
        this.imageLabel = new JLabel(image.getIcon());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Score getScoreObject() {
        return scoreObject;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void addLabel(String label) {
        this.menu.addItem(label);
    }

    public boolean contains(String label) {
        for (int i = 0; i < menu.getItemCount(); i++) {
            if (menu.getItemAt(i).equals(label)) return true;
        }
        return false;
    }
}