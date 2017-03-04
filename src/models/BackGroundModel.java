package models;

/**
 * Created by AnhLe on 3/3/2017.
 */
public class BackGroundModel extends  GameModel
{
    protected int  x2;
    protected int y2;
    public BackGroundModel(int x, int y, int height, int width) {
        super(x, y, height, width);

        x2 = 0;
        y2 = y-600;
    }
    public void Update() {
        y+=1;
        y2+=1;
        if (y2 >600) {
            y2 = y - 600;
        }
        if (y>600) {
            y = y2 -600;
        }
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
