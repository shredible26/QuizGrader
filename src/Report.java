import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class Report {

    JFrame frame;

    private JPanel mainPanel;
    private JTextField title;
    private JTextArea classData;
    private JButton sendInformationButton;

    public Report(HashMap<String, HashMap<Integer, Score>> scores, int numOfProblems) {

        frame = new JFrame("Class Report");
        frame.setPreferredSize(new Dimension(800, 700));

        String writeable = "";
        for (String student : scores.keySet()) {
            int suggestedTotal = 0;
            int suggestedEarned = 0;
            writeable += student + ": ";
            writeable += "\n" + "       ";
            for (int i = 1; i <= numOfProblems; i++) {
                suggestedEarned += scores.get(student).get(i).getEarned();
                suggestedTotal += scores.get(student).get(i).getPossible();
                writeable += i + ": " + scores.get(student).get(i).toString();
                writeable += "\n" + "       ";
            }
            Score score = new Score(suggestedEarned, suggestedTotal);
            writeable += "  total: " + score.toString() + ", " + findGrade(score);
            writeable += "\n";
        }

        classData.setBorder(BorderFactory.createCompoundBorder(
                mainPanel.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 15, 15)));
        classData.setText(writeable);

        sendInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void display() {
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private Character findGrade(Score score) {
        if (score.getPercent() > 90) return 'A';
        int count = 0;
        for (double i = 0; i <= 100; i += 10) {
            if (score.getPercent() <= i) {
                return Constants.grades[count];
            }
            count++;
        }
        return 'F';
    }

}
