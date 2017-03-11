package controllers;

import models.GameModel;
import models.GameVector;
import models.PlayerBulletModel;
import models.PlayerPlaneModel;
import utils.Utils;
import views.GameView;
import views.PlayerBulletView;
import views.PlayerPlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by AnhLe on 2/27/2017.
 */
public class PlayerPlaneController extends GameController implements KeyListener {
    public static final   int SPEED = 5;
    public int attack_Shot = 5;
    private KeyInputManage keyInputManage;
    boolean checkGetBomb =false;
    int level =1;
    int count ;
    Vector<GameController> listPlayerBulletController ;
    public PlayerPlaneController(GameView view, GameModel model  ) {
        super(view, model);
        keyInputManage = new KeyInputManage();
    }

    public void setView(int level){

        switch (level){
            case 1:{
                view.setImage(Utils.loadImages("plane2.png"));
                break;
            }
            case 2:{
                view.setImage(Utils.loadImages("plane4.png"));
                break;
            }
            case 3:{
                view.setImage(Utils.loadImages("plane3.png"));
                break;
            }
        }

    }

    public boolean isCheckGetBomb() {
        return checkGetBomb;
    }

    public void setCheckGetBomb(boolean checkGetBomb) {
        this.checkGetBomb = checkGetBomb;
    }

    public PlayerPlaneController(int x, int y, Vector<GameController> listGameController ){

        this(new PlayerPlaneView(Utils.loadImages("plane2.png")),
                new PlayerPlaneModel(x,y,50,40));
        listPlayerBulletController =  listGameController;
    }

    public PlayerPlaneModel getModel() {
        return (PlayerPlaneModel) model;
    }



    public PlayerPlaneView getView() {
        return (PlayerPlaneView)view;
    }
    public void draw(Graphics graphics){
       view.draw(graphics,model);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.keyInputManage.keyUp = true;
                break;
            case KeyEvent.VK_DOWN:
                this.keyInputManage.keyDown = true;
                break;
            case KeyEvent.VK_LEFT:
                this.keyInputManage.keyLeft = true;
                break;
            case KeyEvent.VK_RIGHT:
                this.keyInputManage.keyRight = true;
                break;
            case KeyEvent.VK_SPACE:
                this.keyInputManage.keySpace = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.keyInputManage.keyUp = false;

                break;
            case KeyEvent.VK_DOWN:
                this.keyInputManage.keyDown = false;
                break;
            case KeyEvent.VK_LEFT:
                this.keyInputManage.keyLeft = false;
                break;
            case KeyEvent.VK_RIGHT:
                this.keyInputManage.keyRight = false;
                break;
            case KeyEvent.VK_SPACE:
                this.keyInputManage.keySpace = false;
                break;
        }
    }
    @Override
    public void onContact(GameController gameController){
        if(gameController instanceof  BulletEnemyController){
            if(level > 1){
                level =1;
                setView(level);
            } else {
                this.hp -=20;
                if(hp <= 0){
                    this.getModel().setDie();
                }
            }
        } else if(gameController instanceof PowerUpController){
            if(level <3){
                level++;
                setView(level);
            }


        } else if(gameController instanceof EnemyController){
            if(level > 1){
                level =1;
                setView(level);
            } else {
                this.hp -=50;
                if(hp <= 0){
                    this.getModel().setDie();
                }
            }

        } else if(gameController instanceof BombController){
                checkGetBomb = true;
        }
    }

    @Override
    public void run() {
        count++;
        if (keyInputManage.keyDown && !keyInputManage.keyUp && ((model.getY() + SPEED) < (Utils.HEIGHT_SCREEN - model.getHeight()))) {

            this.getModel().move(new GameVector(0,SPEED));
        } else if (!keyInputManage.keyDown && keyInputManage.keyUp && ((model.getY() - SPEED) > model.getHeight() / 2)) {
            this.getModel().move(new GameVector(0,-SPEED));
        }

        if (keyInputManage.keyLeft && !keyInputManage.keyRight && ((model.getX() - SPEED) > 0)) {
            this.getModel().move(new GameVector(-SPEED,0));
        } else if (!keyInputManage.keyLeft && keyInputManage.keyRight && ((model.getX() + SPEED) < Utils.WIDTH_SCREEN - model.getWidth())) {
            this.getModel().move(new GameVector(SPEED,0));
        }


        if(keyInputManage.keySpace) {
            if (count > attack_Shot) {
                switch (level) {
                    case 1: {
                        listPlayerBulletController.add(PlayerBulletController.create(PlayerBulletController.PlayerBulletType.UP, (PlayerPlaneModel )model));
                        System.out.println(listPlayerBulletController.size());
                        break;
                    }
                    case 2: {
                        listPlayerBulletController.add(PlayerBulletController.create(PlayerBulletController.PlayerBulletType.UPDOUBLE, (PlayerPlaneModel )model));
                        listPlayerBulletController.add(PlayerBulletController.create(PlayerBulletController.PlayerBulletType.UP, (PlayerPlaneModel )model));


                        break;
                    }
                    case 3:{

                        listPlayerBulletController.add(PlayerBulletController.create(PlayerBulletController.PlayerBulletType.UP, (PlayerPlaneModel )model));
                        listPlayerBulletController.add(PlayerBulletController.create(PlayerBulletController.PlayerBulletType.UPDOUBLE, (PlayerPlaneModel )model));
                        listPlayerBulletController.add(PlayerBulletController.create(PlayerBulletController.PlayerBulletType.UPLEFT, (PlayerPlaneModel )model));
                        listPlayerBulletController.add(PlayerBulletController.create(PlayerBulletController.PlayerBulletType.UPRIGHT, (PlayerPlaneModel )model));
                    }
                }
            }
            count = 0;
        }
    }




}
