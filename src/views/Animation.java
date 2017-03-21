package views;

import utils.Utils;

import java.awt.*;

/**
 * Created by QuanT on 3/11/2017.
 */
public class Animation {
    private long lastTime;
    private int index;
    private int size;
    private int delay;
    private Image image;
    private String url;

    public Animation(int size, int delay, String url) {
        this.delay = delay;
        this.delay = delay;
        this.size = size;
        this.url = url;
    }

    public Image getImage() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastTime > delay && index <= size) {
            lastTime = currentTime;
            if (index == size) {
                return null;
            }
            image = Utils.loadImages(url + "-" + index++ + ".png");
        }
        return image;
    }

    public void reload() {
        if (index == size) {
            index = 0;
        }
    }
}
