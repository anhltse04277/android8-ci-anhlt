package utils;

import controllers.EnemyController;
import controllers.PlayerPlaneController;
import models.EnemyModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Created by AnhLe on 2/26/2017.
 */
public class Utils {

    public static final int WIDTH_SCREEN = 500;
    public static final int HEIGHT_SCREEN = 700;
    public static Image loadImages(String url) {

        try {
            Image image = ImageIO.read(new File("resourses/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int randowNumber(int min , int max){
        Random r = new Random();
        int number = min + r.nextInt(max-min+1);
        return number;
    }

    public static int getXBulletPlane(PlayerPlaneModel playerPlaneModel , int widthBulletPlayer , int heightBulletPlayer){

        return playerPlaneModel.getX()+((playerPlaneModel.getWidth()-widthBulletPlayer)/2);
    }
    public static int getYBulletPlane(PlayerPlaneModel playerPlaneModel , int widthBulletPlayer , int heightBulletPlayer){
        return playerPlaneModel.getY()-(heightBulletPlayer);
    }
    public static  int getXBulletEnemy(EnemyModel playerPlaneModel , int widthBulletPlayer , int heightBulletPlayer){
        return playerPlaneModel.getX()+((playerPlaneModel.getWidth()-widthBulletPlayer )/2);
    }
    public static  int getYBulletEnemy(EnemyModel playerPlaneModel , int widthBulletPlayer , int heightBulletPlayer){
        return playerPlaneModel.getY()+(heightBulletPlayer);
    }
}
