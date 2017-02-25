import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by AnhLT on 2/25/2017.
 */
public class BackGround {
    private Image backgroundImage;

    private int x1, y1, x2, y2;


    public BackGround() {

        try {
            backgroundImage = ImageIO.read(new File("resourses/" + "background.png"));
        } catch (IOException e) {

        }

        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = y1-600;
    }


    public void Update() {
        y1+=1;
        y2+=1;
        if (y2 >600) {
            y2 = y1 - 600;
        }
        if (y1>600) {
            y1 = y2 -600;
        }
    }

    public void Paint(Graphics graphics) {
        graphics.drawImage(backgroundImage, x1, y1,400,600, null);
        graphics.drawImage(backgroundImage, x2, y2,400,600, null);
    }
    public int getXGround(){
        return x1;
    }


}
