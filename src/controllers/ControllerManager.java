package controllers;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by AnhLe on 3/2/2017.
 */
public class ControllerManager {
    Vector<GameController> listGameController;

    public ControllerManager() {

    }
    public void AddNewObject(GameController gc){
        listGameController.add(gc);
    }
    public void drawUpdate(Graphics graphics){
        Iterator itr = listGameController.iterator();
        while (itr.hasNext()) {

            GameController element = (GameController) itr.next();
            if (element.model.getY() <= 0 || element.model.getY() >= 600) {
                itr.remove();
            } else {
                element.draw(graphics);
            }
        }
    }

//    public void controlCollision(){
//
//
//        Iterator itr = listGameController.iterator();
//        while (itr.hasNext()) {
//            GameController gc1 = ( GameController)itr.next();
//            while(itr.hasNext()){
//                GameController gc2 = ( GameController)itr.next();
//                if(gc1.model.checkRectangle(gc2.model)){
//                    if(gc1.getClass().isMemberClass())
//                }
//            }
//        }
//    }

}
