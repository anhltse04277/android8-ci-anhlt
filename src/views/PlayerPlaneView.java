package views;

import models.PlayerBulletModel;
import models.PlayerPlaneModel;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class PlayerPlaneView extends  GameView {
    private Animation explosion;

    public PlayerPlaneView(Image image) {
        super(image);
        explosion = new Animation(15, 100, "expl");
    }
    public boolean explode() {
        Image temp = explosion.getImage();
        if (temp != null) {
            image = temp;
            return true;
        }
        return false;
    }
}
