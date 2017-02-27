package views;

import models.BulletEnemyModel;
import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class BulletEnemyView {
    private Image image;

    public BulletEnemyView(Image image) {
        this.image = image;
    }
    public void draw(Graphics graphics, BulletEnemyModel model){
        graphics.drawImage(image, model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }

}
