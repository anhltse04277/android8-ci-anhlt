package models;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class EnemyModel {
    private int x;
    private int y;
    private static final int speed = 2;
    private int height;
    private int width;


    public void move(){
        this.y = this.y +speed;
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

    public EnemyModel( int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

}
