package controllers.stagegy;

import models.GameModel;
import models.GameVector;

/**
 * Created by AnhLe on 3/9/2017.
 */
public class BulletDown extends MoveBehavior {
    @Override
    public void move(GameModel gameModel) {
        gameModel.move(new GameVector(0,5));
    }
}
