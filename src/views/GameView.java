package views;

import models.EnemyModel;
import models.GameModel;

import java.awt.*;

/**
 * Created by AnhLe on 2/28/2017.
 */
public class GameView {
    protected Image image;

    public GameView(Image image) {
        this.image = image;
    }
    public void draw(Graphics graphics, GameModel model){
        graphics.drawImage(image, model.getX(),model.getY(),model.getWidth(),model.getHeight(),null);
    }
}
