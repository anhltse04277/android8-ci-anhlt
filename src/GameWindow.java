import sun.awt.image.PixelConverter;

import javax.imageio.ImageIO;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by AnhLe on 2/19/2017.
 */
public class GameWindow extends Frame {
    Image backgroundImage;
    Image planeImage;
    private static  final int SCREEN_WIDTH = 400;
    private static  final int SCREEN_HEIGHT = 600;
    private static  final int SPEED = 10;
    private BufferedImage backBufferImage;
    private Graphics backGraphics;
    int planeX = 180;
    int planeY =  550;
    Thread thread;


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
                        if(planeX +SPEED <= 360 ){
                            planeX += SPEED;
                            //repaint();
                        }
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        if(planeX-SPEED >= 0){
                            planeX -=SPEED;
                            //repaint();
                        }
                        break;
                    }
                    case KeyEvent.VK_UP:{
                        if(planeY -SPEED >= 0 ){
                            planeY -= SPEED;
                            //repaint();
                        }
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        if(planeY +SPEED <= 550 ){
                            planeY += SPEED;
                           // repaint();
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
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        });
        backBufferImage = new BufferedImage(SCREEN_WIDTH,SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
    public void start(){
        thread.start();
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
        if(backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();
            backGraphics.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            backGraphics.drawImage(planeImage, planeX, planeY, 40, 50, null);
            g.drawImage(backBufferImage, 0, 0, null);
        }

        // x = (400-w)  /2
        // y = 600 -25

    }
}
