
import controllers.EnemyController;
import controllers.PlayerBulletController;
import controllers.PlayerPlaneController;
import utils.Utils;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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

    PlayerBullet playerBullet;





    Random r;


    PlayerPlaneController playerPlaneController;
    PlayerBulletController playerBulletController;
    ArrayList<PlayerBulletController> listPlayerBulletController;
    ArrayList<PlayerBullet> listBulletPlayer;

    ArrayList<EnemyController> listEnemyController;
    ArrayList<PlayerBullet> listBulletEneny;

    public GameWindow() {

        listPlayerBulletController = new ArrayList<>();
        playerPlaneController = new PlayerPlaneController(180,550);
        listBulletPlayer = new ArrayList<>();

        listEnemyController = new ArrayList<>();
        listBulletEneny = new ArrayList<>();

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
                        playerBulletController = new PlayerBulletController(playerPlaneController.getModel().getX()+10,playerPlaneController.getModel().getY());
                        listPlayerBulletController.add(playerBulletController);
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



                        // enemyY += 1;
                        // Player ban ra dan
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

                        if (listBulletEneny != null) {
                            for (PlayerBullet eb : listBulletEneny) {
                                eb.y += 4;
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

                        EnemyController ec = new EnemyController(r.nextInt(360),0);
                        listEnemyController.add(ec);
                        for (EnemyController e : listEnemyController) {
                            PlayerBullet enemyBullet = new PlayerBullet();
                            enemyBullet.image = Utils.loadImages("enemy_bullet.png");
                            enemyBullet.x = e.getModel().getX() + 5;
                            enemyBullet.y = e.getModel().getY();
                            listBulletEneny.add(enemyBullet);
                        }
                        int count = 1000 * (1 + r.nextInt(2));
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
        ;
    }

    private void movePlane() {
        //move plane to right
        if (isKeyRight) {
            playerPlaneController.getModel().moveRight();
        }
        //move plane to left
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
        //shotting bullet


    }



    void enemyIsDie() {
        Iterator itr = listEnemyController.iterator();
        while (itr.hasNext()) {
            EnemyController e = (EnemyController) itr.next();
            Iterator itr2 = listPlayerBulletController.iterator();
            while (itr2.hasNext()) {
                PlayerBulletController pb = (PlayerBulletController) itr2.next();
                if ((pb.getModel().getY() - e.getModel().getY()) <= 50 && (pb.getModel().getY() - e.getModel().getY()) >= -20) {
                    if ((0 <= pb.getModel().getX() - e.getModel().getX()) && (pb.getModel().getX() - e.getModel().getX() <= 30)) {
                        itr.remove();
                        itr2.remove();
                    }
                }
            }
        }
    }


    @Override
    public void update(Graphics g) {
        if (backBufferImage != null) {

            backGraphics = backBufferImage.getGraphics();
            backgroup.Paint(backGraphics);
            // backGraphics.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            //backGraphics.drawImage(plane.getImage(), plane.getX(), plane.getY(), plane.getWidth(), plane.getHeight(), null);
            //playerBulletController.draw(backGraphics);
            playerPlaneController.draw(backGraphics);
            Iterator itr = listPlayerBulletController.iterator();
            while (itr.hasNext()) {
                PlayerBulletController element = (PlayerBulletController) itr.next();
                if (element.getModel().getY() <= 0) {
                    itr.remove();
                } else {
                    element.draw(backGraphics);
                }
            }

            //System.out.println(listBulletEneny.size());

            Iterator itr2 = listBulletEneny.iterator();
            while (itr2.hasNext()) {
                PlayerBullet element = (PlayerBullet) itr2.next();
                if (element.y >= 600) {
                    itr2.remove();
                } else {
                    backGraphics.drawImage(element.image, element.x, element.y, 30, 30, null);
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

//            for(PlayerBullet pb: listBulletPlayer){
//                backGraphics.drawImage(pb.image,pb.x,pb.y,10,20,null);
//            }
//
//            for(Enemy e: listEnemy){
//                backGraphics.drawImage(e.image,e.x,e.y,40,50,null);
//            }
//            for(PlayerBullet e: listBulletEneny){
//                    backGraphics.drawImage(e.image,e.x,e.y,30,30,null);
//             }


            g.drawImage(backBufferImage, 0, 0, null);
        }

        // x = (400-w)  /2
        // y = 600 -25

    }
}
