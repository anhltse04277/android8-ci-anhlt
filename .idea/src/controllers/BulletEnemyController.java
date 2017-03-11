package controllers;

import models.BulletEnemyModel;
import models.GameModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.BulletEnemyView;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class BulletEnemyController extends GameController {

    public static final int width = 30;
    public static final int height = 30;
    public BulletEnemyController(GameView view, GameModel mode) {
        super(view, mode);
    }


    public BulletEnemyController(int x, int y){
        this(new BulletEnemyView(Utils.loadImages("enemy_bullet.png")), new BulletEnemyModel(x,y,height,width));
    }
    public BulletEnemyModel getModel() {
        return (BulletEnemyModel) model;
    }

    public BulletEnemyView getView() {
        return (BulletEnemyView)view;
    }

    public void run(){
        ( (BulletEnemyModel) model).fly();
    }
    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
}
