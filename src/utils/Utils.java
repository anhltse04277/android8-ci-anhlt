package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by AnhLe on 2/26/2017.
 */
public class Utils {
    public static Image loadImages(String url) {
        try {
            Image image = ImageIO.read(new File("resourses/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
