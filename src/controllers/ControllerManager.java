package controllers;

import models.GameModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by AnhLe on 3/2/2017.
 */
public class ControllerManager {

    protected   Vector<GameController> gameControllers;

    BackGroundController backGroundController;



    public ControllerManager() {
        gameControllers = new Vector<>();
        backGroundController = new BackGroundController(0,0);

    }

    public void add(GameController gameController) {
        this.gameControllers.add(gameController);
    }

    public void run() {
        backGroundController.run();
        removeDeadGameControllers();
        for(int i=0; i < gameControllers.size()-1; i++){
            for(int j=i+1; j< gameControllers.size(); j++){
                if(gameControllers.get(i).model.checkIntersects(gameControllers.get(j).model)){
                    gameControllers.get(i).onContact(gameControllers.get(j));
                    gameControllers.get(j).onContact(gameControllers.get(i));
                }
            }
        }
        for (GameController gameController: this.gameControllers) {
            gameController.run();
        }


    }

    private void removeDeadGameControllers() {
        Iterator<GameController> gameControllerIterator = this.gameControllers.iterator();
        while(gameControllerIterator.hasNext()) {
            GameController gameController = gameControllerIterator.next();
            if(!gameController.isAlive()) {
                gameControllerIterator.remove();
            }
        }
    }

    public void draw(Graphics g) {
        backGroundController.draw(g);
        for (GameController gameController: this.gameControllers) {
            if(gameController instanceof IsLandController){
                gameController.draw(g);
            }
        }

        for (GameController gameController: this.gameControllers) {
            if(!(gameController instanceof IsLandController))
            gameController.draw(g);
        }
    }
}