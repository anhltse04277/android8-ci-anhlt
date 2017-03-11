package models;

/**
 * Created by AnhLe on 2/26/2017.
 */
public class PlayerBulletModel extends GameModel {

    private static final int SPEED = 10;

    public PlayerBulletModel(int x, int y, int height, int width) {
        super(x, y, height, width);
    }
    public void fly(){
        y -=SPEED;
    }
}
