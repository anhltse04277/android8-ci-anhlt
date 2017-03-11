package models;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class BulletEnemyModel extends GameModel {

    private static final int SPEED = 5;

    public BulletEnemyModel(int x, int y, int height, int width) {
        super(x, y, height, width);
    }
    public void fly(){
        y +=SPEED;
    }
}
