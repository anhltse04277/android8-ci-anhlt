package controllers;

import models.EnemyModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.EnemyView;
import views.PlayerPlaneView;

import java.awt.*;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class EnemyController {
    private EnemyModel model;
    private EnemyView view;

    public EnemyController(EnemyModel model, EnemyView view) {
        this.model = model;
        this.view = view;
    }
    public EnemyController(int x, int y){

        this( new EnemyModel(x,y,50,40),new EnemyView(Utils.loadImages("plane1.png")));
    }

    public EnemyModel getModel() {
        return model;
    }
    public void run(){
        model.move();
    }

    public EnemyView getView() {
        return view;
    }


    public void draw(Graphics graphics){
        view.draw(graphics,model);
    }
}
