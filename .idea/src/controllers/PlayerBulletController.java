package controllers;

import models.GameModel;
import models.PlayerBulletModel;
import utils.Utils;
import views.GameView;
import views.PlayerBulletView;

import java.awt.*;

/**
 * Created by AnhLe on 2/26/2017.
 */
public class PlayerBulletController extends GameController {
    public static final int height = 20;
    public static final int width = 10;
    public PlayerBulletController(PlayerBulletView view, PlayerBulletModel model) {
        super(view, model);
    }

    public PlayerBulletController(int x, int y) {

        this(new PlayerBulletView(Utils.loadImages("bullet.png"),new PlayerBulletModel(x, y, 20, 10)),new PlayerBulletModel(x, y, 20, 10) );
    }
    public PlayerBulletModel getModel() {
        return (PlayerBulletModel) model;
    }

    public PlayerBulletView getView() {
        return (PlayerBulletView) view;
    }

    public void run() {
        ((PlayerBulletModel) model).fly();
    }

    public void draw(Graphics graphics) {
        view.draw(graphics, model);
    }
}
