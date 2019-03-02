import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasContainer {
    private JFrame frame;
    private Canvas canvas; //is static, cannot reference nonstatic perspectives
    private ArrayList<String> tags;
    private Score score;

    public CanvasContainer(String name, QGImage image) {

        this.canvas = new Canvas(image);
        frame = canvas.getFrame();
        frame.setName(name);

        tags = canvas.getTags();
        score = canvas.getScoreObject();

        frame.setContentPane(canvas.getMainPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void display() {
        frame.setVisible(true);
    }

    public void setLocation(int x, int y) {
        frame.setLocation(new Point(x, y));
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Score getScore() {
        return score;
    }
}
