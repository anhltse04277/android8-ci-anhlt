package controllers;

import models.GameModel;
import views.GameView;

import java.awt.*;

/**
 * Created by AnhLe on 2/28/2017.
 */
public class GameController {
    protected GameView view;
    protected GameModel model;
    protected int hp = 100;


    public GameController(GameView view, GameModel mode) {
        this.view = view;
        this.model = mode;
    }
    public boolean isAlive(){
        return model.isAlive();
    }

    public void draw(Graphics g ){
        view.draw(g,model);
    }

    public void run(){

    }

    public void onContact(GameController gameController){

    }




}
