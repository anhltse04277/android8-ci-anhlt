package controllers;

import models.EnemyModel;
import models.GameModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.EnemyView;
import views.GameView;
import views.PlayerPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class EnemyController  extends GameController{
    Vector<BulletEnemyController> listEnemyBulletController;
    public EnemyController(GameView view, GameModel mode ,Vector<BulletEnemyController> listEnemyBulletController) {
        super(view, mode );
        this.listEnemyBulletController = listEnemyBulletController;
    }

    public EnemyController(int x, int y ,Vector<BulletEnemyController> listEnemyBulletController){

        this(new EnemyView(Utils.loadImages("plane1.png")), new EnemyModel(x,y,50,40),listEnemyBulletController);
    }
    public EnemyModel getModel() {
        return (EnemyModel)model;
    }
    public void run(){
        ((EnemyModel) model).move();
    }

    public void shoot(){
        BulletEnemyController bulletEnemyController = new BulletEnemyController(model.getX()+model.getWidth()/2 -BulletEnemyController.width/2, model.getY()+model.getHeight());
        listEnemyBulletController.add(bulletEnemyController);
    }

    public EnemyView getView() {
        return (EnemyView)view;
    }


    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
}
