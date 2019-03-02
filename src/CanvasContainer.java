import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasContainer {

    private JFrame frame;
    private Canvas canvas; //is static, cannot reference nonstatic perspectives
    private ArrayList<String> tags;
    private Score score;

    private int width;
    private int height;

    public CanvasContainer(String name, QGImage image, int problemNum) {

        this.canvas = new Canvas(image, name, problemNum);
        frame = canvas.getFrame();
        frame.setName(name);

        tags = canvas.getTags();
        score = canvas.getScoreObject();

        frame.setContentPane(canvas.getMainPanel());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        width = frame.getWidth();
        height = frame.getHeight();
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
