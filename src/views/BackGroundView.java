package views;

import models.BackGroundModel;
import models.GameModel;

import java.awt.*;

/**
 * Created by AnhLe on 3/3/2017.
 */
public class BackGroundView extends GameView {
    public BackGroundView(Image image) {
        super(image);
    }
    public void Paint(Graphics graphics , BackGroundModel bgv) {
        graphics.drawImage(image, bgv.getX(), bgv.getY(),400,600, null);
        graphics.drawImage(image, bgv.getX2(), bgv.getY2(),400,600, null);
    }
}
