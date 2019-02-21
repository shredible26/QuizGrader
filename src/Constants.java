import java.io.File;
import java.util.ArrayList;

public class Constants {
    public static final String separator = File.separator;
    public static final String imagePath = "src" + separator + "ScannedImageSources" + separator;
    public static final String StudentResponsePath = imagePath + "StudentResponses";

    public static String[] toArray(ArrayList<String> arr) {
        String[] list = new String[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            list[i] = arr.get(i);
        }
        return list;
    }
}
