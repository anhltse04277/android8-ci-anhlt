package controllers;

import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.PlayerBulletView;
import views.PlayerPlaneView;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class PlayerPlaneController {
    private PlayerPlaneModel model;
    private PlayerPlaneView view;

    public PlayerPlaneController(PlayerPlaneModel model, PlayerPlaneView view) {
        this.model = model;
        this.view = view;
    }
    public PlayerPlaneController(int x, int y){

        this( new PlayerPlaneModel(x,y,40,50),new PlayerPlaneView(Utils.loadImages("plane2.png")));

    }

    public PlayerPlaneModel getModel() {
        return model;
    }

    public PlayerPlaneView getView() {
        return view;
    }


    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
}
