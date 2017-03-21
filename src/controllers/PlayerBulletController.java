package controllers;

import controllers.stagegy.*;
import models.BulletEnemyModel;
import models.GameModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.BulletEnemyView;
import views.EnemyView;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class PlayerBulletController extends GameController {

    public static final int width = 15;
    public static final int height = 15;
    MoveBehavior moveBehavior;
    public PlayerBulletController(GameView view, GameModel mode) {
        super(view, mode);
    }


    public PlayerBulletController(int x, int y){

        this(new PlayerBulletView(Utils.loadImages("enemy_bullet.png")), new PlayerBulletModel(x,y,height,width));
    }
    public GameModel getModel() {
        return  model;
    }

    public enum PlayerBulletType{
        UP,
        UPRIGHT,
        UPLEFT,
        UPDOUBLE
    }
    public static PlayerBulletController create(PlayerBulletType type , PlayerPlaneModel playerPlaneModel){
        if(type == PlayerBulletType.UPRIGHT){
            PlayerBulletController playerBulletController = new PlayerBulletController(
                    new GameView(Utils.loadImages("bullet-left.png")),
                    new GameModel(Utils.getXBulletPlane(playerPlaneModel,width,height) , Utils.getYBulletPlane(playerPlaneModel,width,height), width, height)
            );
            playerBulletController.setMoveBehavior(new BulletUpRight());
            return playerBulletController;
        } else if(type == PlayerBulletType.UP){

            PlayerBulletController playerBulletController = new PlayerBulletController(
                    new GameView(Utils.loadImages("bullet.png")),
                    new GameModel(Utils.getXBulletPlane(playerPlaneModel,width,height) , Utils.getYBulletPlane(playerPlaneModel,width,height), width, height)
            );
            playerBulletController.setMoveBehavior(new BulletUp());
            return playerBulletController;
        } else if(type == PlayerBulletType.UPLEFT){

            PlayerBulletController playerBulletController = new PlayerBulletController(
                    new GameView(Utils.loadImages("bullet-right.png")),
                    new GameModel(Utils.getXBulletPlane(playerPlaneModel,width,height) , Utils.getYBulletPlane(playerPlaneModel,width,height), width, height)
            );
            playerBulletController.setMoveBehavior(new BulletUpLeft());
            return playerBulletController;
        } else if(type == PlayerBulletType.UPDOUBLE){

            PlayerBulletController playerBulletController = new PlayerBulletController(
                    new GameView(Utils.loadImages("bullet-double.png")),
                    new GameModel(Utils.getXBulletPlane(playerPlaneModel,width,height) , Utils.getYBulletPlane(playerPlaneModel,width,height), width, height)
            );
            playerBulletController.setMoveBehavior(new BulletUp());
            return playerBulletController;
        }
        return  null;
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

        if(gameController instanceof EnemyController){
            if(!gameController.model.isExplosive()){
                this.getModel().setDie();
            }

        }
    }
}
