package views;

import models.EnemyModel;
import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class EnemyView {
    private Image image;

    public EnemyView(Image image) {
        this.image = image;
    }
    public void draw(Graphics graphics, EnemyModel model){
        graphics.drawImage(image, model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }
}
