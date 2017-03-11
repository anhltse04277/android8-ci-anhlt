package controllers;

import controllers.stagegy.*;
import models.BulletEnemyModel;
import models.EnemyModel;
import models.GameModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.BulletEnemyView;
import views.EnemyView;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class BulletEnemyController extends GameController {

    public static final int width = 10;
    public static final int height = 10;
    MoveBehavior moveBehavior;
    public BulletEnemyController(GameView view, GameModel mode) {
        super(view, mode);
    }


    public BulletEnemyController(int x, int y){
        this(new BulletEnemyView(Utils.loadImages("enemy_bullet.png")), new BulletEnemyModel(x,y,height,width));
    }
    public GameModel getModel() {
        return  model;
    }

    public enum BulletType{
        DOWN,
        DOWNRIGHT,
        DOWNLEFT
    }
    public static BulletEnemyController create(BulletType type , EnemyModel enemyModel){
        if(type == BulletType.DOWNRIGHT){
            BulletEnemyController bulletEnemyController = new BulletEnemyController(
                    new GameView(Utils.loadImages("bullet-left.png")),
                    new GameModel(Utils.getXBulletEnemy(enemyModel,width,height) , Utils.getYBulletEnemy(enemyModel,width,height), width, height)
            );
            bulletEnemyController.setMoveBehavior(new BulletDownLeft());
            return bulletEnemyController;
        } else if(type == BulletType.DOWN){

            BulletEnemyController bulletEnemyController = new BulletEnemyController(
                    new GameView(Utils.loadImages("bullet-round.png")),
                    new GameModel(Utils.getXBulletEnemy(enemyModel,width,height) , Utils.getYBulletEnemy(enemyModel,width,height), width, height)
            );
            bulletEnemyController.setMoveBehavior(new BulletDown());
            return bulletEnemyController;
        } else if(type == BulletType.DOWNLEFT){

            BulletEnemyController bulletEnemyController = new BulletEnemyController(
                    new GameView(Utils.loadImages("bullet-right.png")),
                    new GameModel(Utils.getXBulletEnemy(enemyModel,width,height) , Utils.getYBulletEnemy(enemyModel,width,height), width, height)
            );
            bulletEnemyController.setMoveBehavior(new BulletDownRight());
            return bulletEnemyController;
        }
        return  null;
    }

    public BulletEnemyView getView() {
        return (BulletEnemyView)view;
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    @Override
    public void run() {
        if(moveBehavior != null){
            moveBehavior.move(this.model);
        }
    }
    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }

    @Override
    public void onContact(GameController gameController) {
        if(gameController instanceof PlayerPlaneController ){
            this.getModel().setDie();
        }
    }
}
