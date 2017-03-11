package controllers;

import controllers.stagegy.MoveBehavior;
import controllers.stagegy.MoveDownBehavaior;
import controllers.stagegy.MoveDownLeft;
import controllers.stagegy.MoveDownRightBehavior;
import models.EnemyModel;
import models.GameModel;
import utils.Utils;
import views.EnemyView;
import views.GameView;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class EnemyController  extends GameController{

    MoveBehavior moveBehavior;
    String enemyType;
    public EnemyController(GameView view, GameModel mode ) {
        super(view, mode );

    }

    public enum EnemyType{
        GREEN,
        YELLOW,
        BLUE
    }

    public String getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    public static  EnemyController create(EnemyType type){

        if(type == EnemyType.GREEN){
            EnemyController enemyPlaneController = new EnemyController(
                    new EnemyView(Utils.loadImages("plane1.png")),
                    new EnemyModel(Utils.randowNumber(0,360),0, 34, 30)
            );
            enemyPlaneController.setMoveBehavior(new MoveDownBehavaior());
            enemyPlaneController.setEnemyType("GREEN");
            return enemyPlaneController;
        } else if(type == EnemyType.YELLOW){

            EnemyController enemyPlaneController = new EnemyController(
                    new GameView(Utils.loadImages("enemy_plane_yellow_1.png")),
                    new EnemyModel(Utils.randowNumber(0,360),0, 30, 24)
            );
            enemyPlaneController.setMoveBehavior(new MoveDownRightBehavior());
            enemyPlaneController.setEnemyType("YELLOW");
            return  enemyPlaneController;
        }  else if(type == EnemyType.BLUE){

            EnemyController enemyPlaneController = new EnemyController(
                    new GameView(Utils.loadImages("enemy-green-2.png")),
                    new EnemyModel(Utils.randowNumber(0,360),0, 30, 24)
            );
            enemyPlaneController.setMoveBehavior(new MoveDownLeft());
            enemyPlaneController.setEnemyType("BLUE");
            return  enemyPlaneController;
        }
        return  null;
    }
    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public BulletEnemyController shoot(String enemyType){
        BulletEnemyController bulletEnemyController = null;
        if(enemyType.equalsIgnoreCase("GREEN")){
             bulletEnemyController = BulletEnemyController.create(BulletEnemyController.BulletType.DOWN , (EnemyModel) model);
        } else if(enemyType.equalsIgnoreCase("YELLOW")){
            bulletEnemyController = BulletEnemyController.create(BulletEnemyController.BulletType.DOWNRIGHT ,(EnemyModel) model);
        } else if(enemyType.equalsIgnoreCase("BLUE")){
            bulletEnemyController = BulletEnemyController.create(BulletEnemyController.BulletType.DOWNLEFT ,(EnemyModel) model);
        }
        return bulletEnemyController;
    }
    @Override
    public void run() {
        if(moveBehavior != null){
            moveBehavior.move(this.model);
        }
    }

    @Override
    public void onContact(GameController gameController) {
        if(gameController instanceof  PlayerBulletController){
            this.hp -=50;
        }

        if(this.hp <= 0){
            this.model.setDie();
        }
    }
}
