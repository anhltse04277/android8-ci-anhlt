
import controllers.*;
import utils.SoundPlayer;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by AnhLe on 2/19/2017.
 */
public class GameWindow extends Frame {


    Image backgroundImage;
    Image planeImage;
    Image enemyImage;
    BackGroundController backgroup;
    private static final int SCREEN_WIDTH = Utils.WIDTH_SCREEN;
    private static final int SCREEN_HEIGHT = Utils.HEIGHT_SCREEN;
    private static final int SPEED = 5;



    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Thread threadBullet;

    Thread threadEnemy;

    Random r;


    PlayerPlaneController playerPlaneController;

    Vector<PlayerBulletController> listPlayerBulletController;


    ArrayList<EnemyController> listEnemyController;
    Vector<BulletEnemyController> listBulletEnenyController;

    Vector<IsLandController> listIsLandController;

    // ControllerManager cm ;

    ControllerManager poolgameControlManager;
    SoundPlayer soundPlayer;

    public GameWindow() {
        //cm = new ControllerManager();
        soundPlayer = new SoundPlayer("Strikers_1945_II.wav");
        soundPlayer.playLoop();
        poolgameControlManager = new PoolControllerManager();
        this.addKeyListener(((PoolControllerManager)poolgameControlManager).getPlayerPlaneController());

        backgroup = new BackGroundController(0, 0);
        r = new Random();

        setVisible(true);
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        addKeyListener(((PoolControllerManager)poolgameControlManager).getPlayerPlaneController());
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
            backgroundImage = Utils.loadImages("background.png");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // dung repaint(); cung on
        repaint();
        // update(getGraphics());
        // 2: draw image





        threadBullet = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {

                        Thread.sleep(17);
                        poolgameControlManager.run();

                        //enemyIsDie();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        });

        backBufferImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }

    public void start() {
        threadBullet.start();


    }




    @Override
    public synchronized void update(Graphics g) {
        if (backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();
            backgroup.draw(backGraphics);

            poolgameControlManager.draw(backGraphics);


            g.drawImage(backBufferImage, 0, 0, null);

        }

    }
}
