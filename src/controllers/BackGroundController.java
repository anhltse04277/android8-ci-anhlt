package controllers;

import models.BackGroundModel;
import models.EnemyModel;
import models.GameModel;
import utils.Utils;
import views.BackGroundView;
import views.EnemyView;
import views.GameView;

import java.awt.*;
import java.util.Vector;

/**
 * Created by AnhLe on 3/3/2017.
 */
public class BackGroundController extends GameController {
    public BackGroundController(GameView view, GameModel mode) {
        super(view, mode);
    }

    public BackGroundController(int x, int y) {

        this(new BackGroundView(Utils.loadImages("background.png")), new BackGroundModel(x, y, 600, 400));
    }

    @Override
    public void draw(Graphics g) {
        ((BackGroundView) view).Paint(g, (BackGroundModel) model);
    }

    public void run() {
        ((BackGroundModel) model).Update();
    }
}
