package controllers;

import models.PlayerBulletModel;
import utils.Utils;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by AnhLe on 2/26/2017.
 */
public class PlayerBulletController {
    private PlayerBulletModel model;
    private PlayerBulletView view;

    public PlayerBulletController(PlayerBulletModel model, PlayerBulletView view) {
        this.model = model;
        this.view = view;
    }
    public PlayerBulletController(int x, int y){
         this( new PlayerBulletModel(x,y,10,20),new PlayerBulletView(Utils.loadImages("bullet.png")));
    }
    public PlayerBulletModel getModel() {
        return model;
    }

    public PlayerBulletView getView() {
        return view;
    }

    public void run(){
            model.fly();
    }
    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
}
