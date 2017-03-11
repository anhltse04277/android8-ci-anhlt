package controllers;

import models.BombModel;
import models.GameModel;
import models.GameVector;
import models.IsLandModel;
import utils.Utils;
import views.BombView;
import views.GameView;
import views.IslandView;

import java.awt.*;

/**
 * Created by AnhLe on 3/11/2017.
 */
public class BombController extends GameController {
    public static final int width =20;
    public static final int height = 20;
    public BombController(GameView view, GameModel mode) {
        super(view, mode);
    }
    public BombController(int x, int y ) {

        this(new BombView(Utils.loadImages("bomb.png")),new BombModel(x, y, width, height) );
    }


    @Override
    public void draw(Graphics g) {

        view.draw(g,model);
    }
    @Override
    public void onContact(GameController gameController){
        if(gameController instanceof  PlayerPlaneController){
            this.model.setDie();
        }
    }
    public void run(){
        ((BombModel)model).move(new GameVector(0,1));
    }
    public GameModel getModel(){
        return model;
    }


}
