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

    public GameController(GameView view, GameModel mode) {
        this.view = view;
        this.model = mode;
    }

    public void draw(Graphics g ){
        view.draw(g,model);
    }




}
