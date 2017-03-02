package controllers;

import models.GameModel;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.GameView;
import views.PlayerBulletView;
import views.PlayerPlaneView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class PlayerPlaneController extends GameController {
    Vector<PlayerBulletController> listPlayerBulletController;
    public PlayerPlaneController(GameView view, GameModel model ,Vector<PlayerBulletController> listPlayerBulletController ) {
        super(view, model);
        this.listPlayerBulletController = listPlayerBulletController;
    }


    public PlayerPlaneController(int x, int y , Vector<PlayerBulletController> listPlayerBulletController){

        this(new PlayerPlaneView(Utils.loadImages("plane2.png")), new PlayerPlaneModel(x,y,50,40) , listPlayerBulletController);
    }
    public void shoot(){
        PlayerBulletController playerBulletController = new PlayerBulletController(model.getX() + model.getWidth()/2 - PlayerBulletController.width/2, model.getY()-PlayerBulletController.height);
        listPlayerBulletController.add(playerBulletController);
    }
    public PlayerPlaneModel getModel() {
        return (PlayerPlaneModel) model;
    }

    public void isDie(){
        view = new PlayerPlaneView(Utils.loadImages("plane2.png"));
    }

    public PlayerPlaneView getView() {
        return (PlayerPlaneView)view;
    }
    public void draw(Graphics graphics){
       view.draw(graphics,model);
    }
}
