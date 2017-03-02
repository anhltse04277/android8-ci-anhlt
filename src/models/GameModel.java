package models;

import controllers.GameController;

import java.awt.*;

/**
 * Created by AnhLe on 2/28/2017.
 */
public class GameModel {
    protected int x;
    protected int y;
    protected int height;
    protected int width;

    public GameModel(int x, int y, int height, int width) {
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

    public boolean checkRectangle(GameModel gc){
        Rectangle r1 = new Rectangle(getX() , getY(), getWidth(), getHeight());
        Rectangle r2 = new Rectangle(gc.getX(), gc.getY() , gc.getWidth(), gc.getHeight());
        return r1.intersects(r2);
    }

}

