package models;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class PlayerPlaneModel extends GameModel {


    private static final int speed = 5;

    public PlayerPlaneModel(int x, int y, int height, int width) {
        super(x, y, height, width);
    }


    public void moveLeft() {
        if ((x - speed) >= 0) {
            x -= speed;
        }
    }

    public void moveRight() {
        if ((x + speed) <= 360) {
            x = x + speed;
        }
    }

    public void moveup() {
        if ((y - speed) >= 0) {
            y -= speed;
        }
    }

    public void moveDown() {
        if ((y + speed) <= 550) {
            y += speed;
        }
    }









}
