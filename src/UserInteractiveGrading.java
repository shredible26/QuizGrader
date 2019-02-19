import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class UserInteractiveGrading {

    private final String separator = File.separator;
    private final String imagePath = "src" + separator + "ScannedImageSources" + separator;

    private HashMap<String, ArrayList<AnswerField>> ANSWER_FIELDS;
    private int numOfProblems;


    public void run() throws InterruptedException, IOException {

        ANSWER_FIELDS = loadAllAnswerFields(); //HashMap mapping page name to list of answer fields on that page

        for (String file : ANSWER_FIELDS.keySet()) {
            for (AnswerField ans : ANSWER_FIELDS.get(file)) {
                System.out.println(ans);
            }
        }

        System.exit(0);
    }

    private HashMap<String, ArrayList<AnswerField>> loadAllAnswerFields() throws InterruptedException, IOException {

        HashMap<String, ArrayList<AnswerField>> answers = new HashMap<>();
        File[] blankTest = new File(imagePath + "AllPagesOfBlankTest" + separator).listFiles();

        int num = 0;
        for (File page : blankTest) {

            answers.put(page.getName(), new ArrayList<>());

            QGImage pageImage = new QGImage(imagePath + "AllPagesOfBlankTest" + separator + page.getName());
            pageImage.resize(750, 500);
            pageImage.display();

            int numOfAnswerFields = Integer.parseInt(JOptionPane.showInputDialog("How many answer fields on this page?"));

            for (int i = 0; i < numOfAnswerFields; i++) {
                num++;
                answers.get(page.getName()).add(recordAnswerField(pageImage, num));
            }
        }

        numOfProblems = num;
        return answers;
    }

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

        field.setHeight(Math.abs(field.getTopY() - field.getBottomY()));
        field.setWidth(Math.abs(field.getTopX() - field.getBottomX()));

        page.drawRectangleAt(field.getTopX(), field.getTopY(), field.getBottomX(), field.getBottomY());

        field.setBottomX(getLocationOfMouse()[0]);
        field.setBottomY(getLocationOfMouse()[1]);

        field.setProblemNum(problemNum);
        return field;
    }

    private int[] getLocationOfMouse() {
        int mouseX = MouseInfo.getPointerInfo().getLocation().x;
        int mouseY = MouseInfo.getPointerInfo().getLocation().y;
        return new int[]{mouseX, mouseY};
    }

}
