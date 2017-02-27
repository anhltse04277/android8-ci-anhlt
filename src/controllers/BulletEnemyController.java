package controllers;

import models.BulletEnemyModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.BulletEnemyView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class BulletEnemyController {
    private BulletEnemyModel model;
    private BulletEnemyView view;

    public BulletEnemyController(BulletEnemyModel model, BulletEnemyView view) {
        this.model = model;
        this.view = view;
    }
    public BulletEnemyController(int x, int y){
        this( new BulletEnemyModel(x,y,30,30),new BulletEnemyView(Utils.loadImages("enemy_bullet.png")));
    }
    public BulletEnemyModel getModel() {
        return model;
    }

    public BulletEnemyView getView() {
        return view;
    }

    public void run(){
        model.fly();
    }
    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
}
