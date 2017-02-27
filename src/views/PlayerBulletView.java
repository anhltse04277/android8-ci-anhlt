package views;

import models.PlayerBulletModel;

import java.awt.*;

/**
 * Created by AnhLe on 2/26/2017.
 */
public class PlayerBulletView {
    private Image image;

    public PlayerBulletView(Image image) {
        this.image = image;
    }
    public void draw(Graphics graphics, PlayerBulletModel model){
        graphics.drawImage(image, model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }
}
