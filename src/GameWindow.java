
import controllers.*;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

/**
 * Created by AnhLe on 2/19/2017.
 */
public class GameWindow extends Frame {
    private boolean isKeyUp = false;
    private boolean iskeyDown = false;
    private boolean isKeyLeft = false;
    private boolean isKeyRight = false;
    private boolean isSpace = false;

    Image backgroundImage;
    Image planeImage;
    Image enemyImage;
    BackGround backgroup;
    private static final int SCREEN_WIDTH = 400;
    private static final int SCREEN_HEIGHT = 600;
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

    public GameWindow() {
        //cm = new ControllerManager();
        listPlayerBulletController = new Vector<>();
        listIsLandController = new Vector<>();
        playerPlaneController = new PlayerPlaneController(180,550,listPlayerBulletController);


        listEnemyController = new ArrayList<>();
        listBulletEnenyController = new Vector<>();

        backgroup = new BackGround();
        r = new Random();
        setVisible(true);
        setSize(400, 600);
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
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT: {
                        isKeyRight = true;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        isKeyLeft = true;

                        break;
                    }
                    case KeyEvent.VK_UP: {
                        isKeyUp = true;

                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        iskeyDown = true;
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        if(playerPlaneController != null){
                            playerPlaneController.shoot();
                        }

                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT: {
                        isKeyRight = false;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        isKeyLeft = false;
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        isKeyUp = false;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        iskeyDown = false;
                        break;
                    }

                }


            }
        });
        threadBullet = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                       enemyIsDie();
                        Thread.sleep(17);
                        movePlane();

                        backgroup.Update();
                        if (listEnemyController != null) {
                            for (EnemyController e : listEnemyController) {
                                e.run();
                            }
                        }

                        if (listPlayerBulletController != null) {
                            for (PlayerBulletController playerBulletController : listPlayerBulletController) {
                                playerBulletController.run();
                            }
                        }

                        if (listBulletEnenyController != null) {
                            for (BulletEnemyController eb : listBulletEnenyController) {
                                eb.run();
                            }
                        }
                        if (listIsLandController != null) {
                            for (IsLandController il : listIsLandController) {
                                il.run();
                            }
                        }
                        if(playerPlaneController != null){
                            if(isPlaneDie()){
                                playerPlaneController = null;
                            }
                        }


                        //enemyIsDie();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    repaint();
                }
            }
        });
        threadEnemy = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        enemyIsDie();
                        EnemyController ec = new EnemyController(r.nextInt(360),0,listBulletEnenyController);
                        //cm.AddNewObject(ec);


                        listEnemyController.add(ec);
                        for (EnemyController e : listEnemyController) {
                           e.shoot();
                        }

                        //System.out.println(listEnemyController.size());

                        int count = 1000 * (1 + r.nextInt(2));
                        if(count==2000){
                            int island = r.nextInt(2);
                            if(island==0){
                                listIsLandController.add(new IsLandController(  r.nextInt(560),0 ,"island-2.png" ));
                            } else {
                                listIsLandController.add(new IsLandController( r.nextInt(560),0,"island.png"   ));
                            }
                        }
                        repaint();
                        Thread.sleep(count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        backBufferImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
    public void start() {
        threadBullet.start();
        threadEnemy.start();

    }

    private void movePlane() {
        //move plane to right
        if(playerPlaneController != null){
            if (isKeyLeft) {
                playerPlaneController.getModel().moveLeft();
            }
            //move plane to up
            if (isKeyUp) {
                playerPlaneController.getModel().moveup();
            }
            //move plane to down
            if (iskeyDown) {
                playerPlaneController.getModel().moveDown();
            }
            if (isKeyRight) {
                playerPlaneController.getModel().moveRight();
            }
        }

        //move plane to left

    }

    void enemyIsDie() {
        Iterator itr = listEnemyController.iterator();
        while (itr.hasNext()) {
            EnemyController e = (EnemyController) itr.next();
            Iterator itr2 = listPlayerBulletController.iterator();
            while (itr2.hasNext()) {
                PlayerBulletController pb = (PlayerBulletController) itr2.next();
                if ((e.getModel().checkRectangle(pb.getModel()))) {
                        itr.remove();
                        itr2.remove();
                }
            }
        }
    }
    boolean isPlaneDie(){
        Iterator itr = listBulletEnenyController.iterator();
        while(itr.hasNext()){
            BulletEnemyController bec = (BulletEnemyController) itr.next();
            if(playerPlaneController.getModel().checkRectangle(bec.getModel())){
                return true;
            }
        }
        return false;
    }
    @Override
    public void update(Graphics g) {
        if (backBufferImage != null) {
            backGraphics = backBufferImage.getGraphics();
            backgroup.Paint(backGraphics);
            Iterator itr4 = listIsLandController.iterator();
            while (itr4.hasNext()) {
                IsLandController element = (IsLandController) itr4.next();
                if (element.getModel().getY() >= 600) {
                    itr4.remove();
                } else {
                    element.draw(backGraphics);
                }
            }
                if(playerPlaneController != null){
                    playerPlaneController.draw(backGraphics);
                }

                Iterator itr = listPlayerBulletController.iterator();
                while (itr.hasNext()) {
                    PlayerBulletController element = (PlayerBulletController) itr.next();
                    if (element.getModel().getY() <= 0) {
                        itr.remove();
                    } else {
                        element.draw(backGraphics);
                    }
                }
                Iterator itr2 = listBulletEnenyController.iterator();
                while (itr2.hasNext()) {
                    BulletEnemyController element = (BulletEnemyController) itr2.next();
                    if (element.getModel().getY() >= 600) {
                        itr2.remove();
                    } else {
                        element.draw(backGraphics);
                    }
                }
                Iterator itr3 = listEnemyController.iterator();
                while (itr3.hasNext()) {
                    EnemyController element = (EnemyController) itr3.next();
                    if (element.getModel().getY() >= 600) {
                        itr3.remove();
                    } else {
                        element.draw(backGraphics);
                    }
                }


                g.drawImage(backBufferImage, 0, 0, null);


        }

    }
}
