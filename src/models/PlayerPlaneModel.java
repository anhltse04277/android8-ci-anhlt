package models;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class PlayerPlaneModel {

    private int x;
    private int y;
    private static final int speed = 5;
    private int height;
    private int width;


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



    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }




    public PlayerPlaneModel( int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

}
