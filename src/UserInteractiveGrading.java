import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInteractiveGrading {

    public static int submittedProblems = 0;
    private int numOfStudents = new File(Constants.StudentResponsePath).listFiles().length;

    private final double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    private HashMap<String, ArrayList<AnswerField>> ANSWER_FIELDS;
    private int numOfProblems;

    private final int scaleWidth = 500; //scale all images to this width
    private final int scaleHeight = 750; //scale all images to this height

    public static ArrayList<String> menuLabels = new ArrayList<>();

    public static ArrayList<CanvasContainer> canvi = new ArrayList<>();

    public static HashMap<String, HashMap<Integer, ArrayList<String>>> tags = new HashMap<>();
    public static HashMap<String, HashMap<Integer, Score>> scores = new HashMap<>();

    private int previousLineY = 0;

    public void run() throws InterruptedException, IOException {

        ANSWER_FIELDS = loadAllAnswerFields(); //HashMap mapping page name to list of answer fields on that page

        setup();

        int newX = 0;
        int newY = 0;

        boolean newLine = false;

        for (int i = 1; i <= numOfProblems; i++) {

            String page = getPageForNum(i);
            AnswerField ans = getAnswerFieldForNum(i);

            for (File student : new File(Constants.StudentResponsePath).listFiles()) { //student will be the name of the student

                QGImage image = new QGImage(student.getAbsolutePath() + Constants.separator + page);
                image.resize(scaleHeight, scaleWidth);
                CanvasContainer container = new CanvasContainer(student.getName(), image.getRegion(ans), ans.getProblemNum());
                canvi.add(container);

                if (newX + container.getWidth() > screenWidth) {
                    newX = 0;
                    newLine = true;
                }

                if (newLine) {
                    newY += container.getHeight() + 20;
                    newLine = false;
                }

                container.setLocation(newX, newY);
                newX += container.getWidth();

                container.display();
            }
        }

        while ((numOfProblems) * numOfStudents > submittedProblems) System.out.print("");

        new Report(scores, numOfProblems).display();
        new IndividualVisualizer(tags, scores, numOfProblems).display();

        Thread.sleep(10000000);
        System.exit(0);
    }

    /**
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    private HashMap<String, ArrayList<AnswerField>> loadAllAnswerFields() throws InterruptedException, IOException {

        HashMap<String, ArrayList<AnswerField>> answers = new HashMap<>();
        File[] blankTest = new File(Constants.imagePath + "AllPagesOfBlankTest" + Constants.separator).listFiles();

        int num = 0;
        for (File page : blankTest) {

            answers.put(page.getName(), new ArrayList<>());

            QGImage pageImage = new QGImage(Constants.imagePath + "AllPagesOfBlankTest" + Constants.separator + page.getName());
            pageImage.resize(scaleHeight, scaleWidth);
            pageImage.display();

            int numOfAnswerFields = Integer.parseInt(JOptionPane.showInputDialog("How many answer fields on this page?"));

            for (int i = 0; i < numOfAnswerFields; i++) {
                num++;
                answers.get(page.getName()).add(recordAnswerField(pageImage, num));
            }

            pageImage.close();
        }

//        Thread.sleep(1000);
        numOfProblems = num;
        return answers;
    }

    /**
     * @param page
     * @param problemNum
     * @return
     * @throws InterruptedException
     */
    private AnswerField recordAnswerField(QGImage page, int problemNum) throws InterruptedException {

        //AnswerField where we append mouse locations
        AnswerField field = new AnswerField();

        //makes sure mouse is clicked
        while (!page.mouseIsPressed()) {
            Thread.sleep(10);
            continue;
        }

        field.setTopX(getLocationOfMouse()[0]);
        field.setTopY(getLocationOfMouse()[1]);

        //allows time for the user to drag the mouse
        while (page.mouseIsPressed()) {
            Thread.sleep(10);
            continue;
        }

        field.setBottomX(getLocationOfMouse()[0]);
        field.setBottomY(getLocationOfMouse()[1]);

        field.setHeight(Math.abs(field.getTopY() - field.getBottomY()));
        field.setWidth(Math.abs(field.getTopX() - field.getBottomX()));

        field.setProblemNum(problemNum);

        page.drawRectangleAt(field.getTopX(), field.getTopY(), field.getBottomX(), field.getBottomY());
        Thread.sleep(100);
        page.closeRectangleView();

        return field;
    }

    /**
     * @return
     */
    private int[] getLocationOfMouse() {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        return new int[]{mouseX, mouseY};
    }

    private String getPageForNum(int num) {
        for (String page : ANSWER_FIELDS.keySet()) {
            for (AnswerField ans : ANSWER_FIELDS.get(page)) {
                if (ans.getProblemNum() == num) {
                    return page;
                }
            }
        }
        return null;
    }

    private AnswerField getAnswerFieldForNum(int num) {
        for (String page : ANSWER_FIELDS.keySet()) {
            for (AnswerField ans : ANSWER_FIELDS.get(page)) {
                if (ans.getProblemNum() == num) {
                    return ans;
                }
            }
        }
        return null;
    }

    private void setup() {
        for (File student : new File(Constants.StudentResponsePath).listFiles()) {
            tags.put(student.getName(), new HashMap<>());
            for (int i = 0; i < numOfProblems; i++) {
                tags.get(student.getName()).put(i + 1, new ArrayList<>());
            }
            scores.put(student.getName(), new HashMap<>());
        }
    }

    public static void updateCanvi() {
        for (CanvasContainer container : canvi) {
            for (String label : menuLabels) {
                if (!container.contains(label)) {
                    container.addLabel(label);
                }
            }
        }
    }
}
