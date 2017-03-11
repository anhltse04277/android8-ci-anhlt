package models;

import controllers.GameController;
import utils.Utils;

import java.awt.*;

/**
 * Created by AnhLe on 2/28/2017.
 */
public class GameModel {
    protected int x;
    protected int y;
    protected int height;
    protected int width;

    protected  boolean isAlive = true ;

    public GameModel(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean checkIntersects(GameModel gc){
        Rectangle r1 = getRectangle(this);
        Rectangle r2 = getRectangle(gc);
        return r1.intersects(r2);
    }
    public Rectangle getRectangle(GameModel gc){
        return new Rectangle(gc.getX(), gc.getY() , gc.getWidth(), gc.getHeight());
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void setDie(){
        this.isAlive = false;
    }

    public void move (GameVector gameVector){
        this.x +=gameVector.getX();
        this.y +=gameVector.getY();
        if((this.x> Utils.WIDTH_SCREEN || this.x+this.width <0) || (this.y> Utils.HEIGHT_SCREEN || this.y+this.height <0) ){
            this.isAlive = false;
        }

    }


}

