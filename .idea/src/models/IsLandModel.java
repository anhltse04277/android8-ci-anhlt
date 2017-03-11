package models;

/**
 * Created by AnhLe on 3/2/2017.
 */
public class IsLandModel extends GameModel {
    private static final int SPEED = 1;
    public IsLandModel(int x, int y, int height, int width) {
        super(x, y, height, width);
    }
    public void run(){
        y +=SPEED;
    }
}
