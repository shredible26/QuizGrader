import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class IndividualVisualizer {

    private JFrame frame;

    private JPanel mainPanel;
    private JPanel names;
    private JPanel scores;
    private JPanel tags;
    private JComboBox students;
    private JTextField namesTextField;
    private JTextField numberTextField;
    private JComboBox namesMenu;
    private JComboBox problemNumMenu;
    private JPanel namesMenuPanel;
    private JPanel numberMenuPanel;
    private JTextArea tagsTextArea;
    private JTextArea scoreTextArea;


    private HashMap<String, HashMap<Integer, ArrayList<String>>> tagsMap;
    private HashMap<String, HashMap<Integer, Score>> scoreMap;

    private boolean nameClicked;
    private boolean numberClicked;

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getNames() {
        return names;
    }

    public JPanel getScores() {
        return scores;
    }

    public JPanel getTags() {
        return tags;
    }

    public JComboBox getStudents() {
        return students;
    }

    public JTextField getNamesTextField() {
        return namesTextField;
    }

    public JTextField getNumberTextField() {
        return numberTextField;
    }

    public JComboBox getNamesMenu() {
        return namesMenu;
    }

    public JComboBox getProblemNumMenu() {
        return problemNumMenu;
    }

    public JPanel getNamesMenuPanel() {
        return namesMenuPanel;
    }

    public JPanel getNumberMenuPanel() {
        return numberMenuPanel;
    }

    public JTextArea getTagsTextArea() {
        return tagsTextArea;
    }

    public JTextArea getScoreTextArea() {
        return scoreTextArea;
    }

    public HashMap<String, HashMap<Integer, ArrayList<String>>> getTagsMap() {
        return tagsMap;
    }

    public HashMap<String, HashMap<Integer, Score>> getScoreMap() {
        return scoreMap;
    }

    public boolean isNameClicked() {
        return nameClicked;
    }

    public boolean isNumberClicked() {
        return numberClicked;
    }

    public IndividualVisualizer(HashMap<String, HashMap<Integer, ArrayList<String>>> tags, HashMap<String, HashMap<Integer, Score>> scores, int numProblems) {

        frame = new JFrame("Individual Reports");
        frame.add(mainPanel);

        frame.setPreferredSize(new Dimension(300, 200));

        this.tagsMap = tags;
        this.scoreMap = scores;

        namesTextField.setEditable(false);
        numberTextField.setEditable(false);
        tagsTextArea.setEditable(false);
        scoreTextArea.setEditable(false);

        for (String student : tags.keySet()) {
            namesMenu.addItem(student);
        }

        for (Integer i = 1; i < numProblems + 1; i++) {
            problemNumMenu.addItem(i);
        }

        namesMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameClicked = true;
                namesTextField.setText(namesMenu.getSelectedItem().toString());
                if (nameClicked && numberClicked) {
                    System.out.println("both clicked");
                    updateInfo(namesMenu.getSelectedItem().toString(), problemNumMenu.getSelectedIndex());
                }
            }
        });


        problemNumMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberClicked = true;
                numberTextField.setText(problemNumMenu.getSelectedItem().toString());
                if (nameClicked && numberClicked) {
                    System.out.println("both clicked");
                    updateInfo(namesMenu.getSelectedItem().toString(), problemNumMenu.getSelectedIndex());
                }
            }
        });
    }

    public void display() {
        frame.pack();
        frame.setVisible(true);
    }

    private void updateInfo(String student, Integer num) {
        String tagText = "Tags: \n";
        for (int i = 0; i < tagsMap.get(student).get(num + 1).size(); i++) {
            if (i == tagsMap.get(student).get(num + 1).size() - 1) {
                tagText += tagsMap.get(student).get(num + 1).get(i) + "\n";
            } else {
                tagText += tagsMap.get(student).get(num + 1).get(i) + ", \n";
            }
        }
        tagsTextArea.setText(tagText);
        scoreTextArea.setText("Score: " + scoreMap.get(student).get(num + 1).toString());
    }
}
