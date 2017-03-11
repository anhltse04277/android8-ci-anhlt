package controllers;

import models.GameModel;
import models.GameVector;
import utils.Utils;
import views.GameView;

import java.awt.*;

/**
 * Created by AnhLe on 3/10/2017.
 */
public class PowerUpController extends GameController {

    public PowerUpController(GameView view, GameModel mode) {
        super(view, mode);
    }
    public PowerUpController(int x , int y){
        super( new GameView(Utils.loadImages("power-up.png")),new GameModel(x,y,20,20));
    }

    @Override
    public void run() {
        this.model.move(new GameVector(0,2));

    }

    @Override
    public void onContact(GameController gameController) {
        if(gameController instanceof  PlayerPlaneController){
            this.model.setDie();
        }
    }

    @Override
    public void draw(Graphics g) {
        this.view.draw(g,this.model);
    }
}
