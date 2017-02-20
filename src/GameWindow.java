import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Created by AnhLe on 2/19/2017.
 */
public class GameWindow extends Frame {
    Image backgroundImage;
    Image planeImage;
    int planeX = 180;
    int planeY =  550;


    public GameWindow(){
        setVisible(true);
        setSize(400,600);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.out.println("WindowClosing");
                System.exit(0);
            }
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.out.println("WindowClosed");
            }
        });
        // 1: load image
        try {
            backgroundImage = loadImages("background.png");
            planeImage = loadImages("plane3.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // dung repaint(); cung on
        repaint();
       // update(getGraphics());
        // 2: draw image
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:{
                        if(planeX +10 <= 360 ){
                            planeX += 10;
                            repaint();
                        }
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        if(planeX-10 >= 0){
                            planeX -=10;
                            repaint();
                        }
                        break;
                    }
                    case KeyEvent.VK_UP:{
                        if(planeY -10 >= 0 ){
                            planeY -= 10;
                            repaint();
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        if(planeY +10 <= 550 ){
                            planeY += 10;
                            repaint();
                        }
                        break;
                    }
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
    }
    private Image loadImages(String url ){
        try {
            Image image = ImageIO.read(new File("resourses/" + url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Graphics g) {
        g.drawImage(backgroundImage,0,0,400,600,null);
        g.drawImage(planeImage,planeX,planeY,40,50,null);
        // x = (400-w)  /2
        // y = 600 -25

    }
}
