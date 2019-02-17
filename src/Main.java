import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        runCollectionsAnalysis();
        runUserInteractiveGradingSystem();
    }

    /**
     *
     */
    public static void runUserInteractiveGradingSystem() throws InterruptedException {
        try {
            UserInteractiveGrading userInteractiveGrading = new UserInteractiveGrading();
            userInteractiveGrading.run();
        } catch (IOException e) {
            System.err.println("Unhandled IOException!");
        }

//        EasyImage easyImage = new EasyImage("blankTest.jpg");
//        easyImage.display(easyImage.scaleImage(400, 500)); //random numbers to test
    }
}
