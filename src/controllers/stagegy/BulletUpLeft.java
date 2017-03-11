package controllers.stagegy;

import models.GameModel;
import models.GameVector;

/**
 * Created by AnhLe on 3/10/2017.
 */
public class BulletUpLeft extends MoveBehavior {
    @Override
    public void move(GameModel gameModel) {
        gameModel.move(new GameVector(-5,-8));
    }
}
