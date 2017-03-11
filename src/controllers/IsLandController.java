package controllers;

import models.GameModel;
import models.GameVector;
import models.IsLandModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.GameView;
import views.IslandView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by AnhLe on 3/2/2017.
 */
public class IsLandController extends GameController {
    public IsLandController(GameView view, GameModel mode) {
        super(view, mode);
    }
    public IsLandController(int x, int y , String s) {

        this(new IslandView(Utils.loadImages(s)),new IsLandModel(x, y, 40, 40) );
    }

    @Override
    public void draw(Graphics g) {

        view.draw(g,model);
    }
    public void run(){
        ((IsLandModel)model).move(new GameVector(0,1));
    }
    public GameModel getModel(){
        return model;
    }

}
