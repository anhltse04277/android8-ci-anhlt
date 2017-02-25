
import sun.awt.image.PixelConverter;

import javax.imageio.ImageIO;
import javax.swing.plaf.TableHeaderUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
    private static  final int SCREEN_WIDTH = 400;
    private static  final int SCREEN_HEIGHT = 600;
    private static  final int SPEED = 5;


    private BufferedImage backBufferImage;
    private Graphics backGraphics;

    Thread threadBullet;

    Thread threadEnemy;

    PlayerBullet playerBullet;
    ArrayList<PlayerBullet> listBulletPlayer ;

    Enemy enemy;
    ArrayList<Enemy> listEnemy;

    ArrayList<PlayerBullet> listBulletEneny;
    Random r;

    Plane plane;

    public GameWindow(){
        listBulletPlayer = new ArrayList<>();
        listEnemy = new  ArrayList<>();
        listBulletEneny = new ArrayList<>();
        backgroup = new BackGround();
        r = new Random();
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
            plane =  new Plane();
            backgroundImage = loadImages("background.png");
            plane.image = loadImages("plane3.png");
            plane.x = 180; plane.y = 550;
            enemyImage = loadImages(("plane1.png"));
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
                        isKeyRight = true;
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        isKeyLeft = true;

                        break;
                    }
                    case KeyEvent.VK_UP:{
                        isKeyUp = true;

                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        iskeyDown = true;
                        break;
                    }
                    case KeyEvent.VK_SPACE:{
                        PlayerBullet playerBullet = new PlayerBullet();
                        playerBullet.image = loadImages("bullet.png");
                        playerBullet.x = plane.x + plane.image.getWidth(null) / 4;
                        playerBullet.y = plane.y;
                        listBulletPlayer.add(playerBullet);
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:{
                        isKeyRight = false;
                        break;
                    }
                    case KeyEvent.VK_LEFT:{
                        isKeyLeft = false;
                        break;
                    }
                    case KeyEvent.VK_UP:{
                        isKeyUp = false;
                        break;
                    }
                    case KeyEvent.VK_DOWN:{
                        iskeyDown = false;
                        break;
                    }

                }


            }
        });
        threadBullet = new Thread(new Runnable() {
            @Override
            public void  run() {
                while(true){
                    try {
                        enemyIsDie();
                        Thread.sleep(17);
                       // enemyY += 1;
                        // Player ban ra dan
                        movePlane();

                        backgroup.Update();
                        if(listEnemy != null){
                            for(Enemy e:listEnemy){
                                e.y = e.y + e.speed;
                            }
                        }

                        if(listBulletPlayer != null){
                            for(PlayerBullet pb: listBulletPlayer){
                                pb.y -= 10;
                            }
                        }

                        if(listBulletEneny != null){
                            for(PlayerBullet eb: listBulletEneny){
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
            public  void run() {
                while(true){
                        try {

                            enemyIsDie();
                                        enemy = new Enemy();
                                        enemy.x =  r.nextInt(560);
                                        enemy.y = 0;
                                        enemy.image = loadImages("plane1.png");
                                        listEnemy.add(enemy);


                                            for (Enemy e : listEnemy) {
                                                PlayerBullet enemyBullet = new PlayerBullet();
                                                enemyBullet.image = loadImages("enemy_bullet.png");
                                                enemyBullet.x = e.x + 5;
                                                enemyBullet.y = e.y;

                                               listBulletEneny.add(enemyBullet);}



                                        int count = 1000 * (1 + r.nextInt(2));
                                        Thread.sleep(count);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        });
        backBufferImage = new BufferedImage(SCREEN_WIDTH,SCREEN_HEIGHT, BufferedImage.TYPE_INT_ARGB);
    }
    public void start(){
        threadBullet.start();
       threadEnemy.start();;
    }
    private void movePlane() {
        //move plane to right
        if (isKeyRight && (plane.x + SPEED) <= (SCREEN_WIDTH - 40)) {
            plane.x += SPEED;
        }
        //move plane to left
        if (isKeyLeft && (plane.x - SPEED) >= 0) {
            plane.x -= SPEED;
        }
        //move plane to up
        if (isKeyUp && (plane.y - SPEED) > 0) {
            plane.y -= SPEED;
        }
        //move plane to down
        if (iskeyDown && (plane.y + SPEED) < (SCREEN_HEIGHT - 50)) {
            plane.y += SPEED;
        }
        //shotting bullet
        if(isSpace){

        }

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

    void enemyIsDie(){
        Iterator itr = listEnemy.iterator();
        while(itr.hasNext()) {
            Enemy e = (Enemy) itr.next();
            Iterator itr2 = listBulletPlayer.iterator();
            while(itr2.hasNext()){
                PlayerBullet pb = (PlayerBullet) itr2.next();
                if((pb.y-e.y)<=50 && (pb.y -e.y)>=-20){
                    if((0<=pb.x-e.x) && (pb.x-e.x <=30) ){
                        itr.remove();
                        itr2.remove();
                    }
                }
            }
        }
    }


    @Override
    public  void update(Graphics g) {
        if(backBufferImage != null) {

            backGraphics = backBufferImage.getGraphics();
            backgroup.Paint(backGraphics);
           // backGraphics.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
            backGraphics.drawImage(plane.image, plane.x, plane.y, 40, 50, null);
            Iterator itr = listBulletPlayer.iterator();
            while(itr.hasNext()) {
                PlayerBullet element = (PlayerBullet) itr.next();
                if(element.y<=0){
                    itr.remove();
                }else{
                    backGraphics.drawImage(element.image,element.x,element.y,10,20,null);
                }

            }

            System.out.println(listBulletEneny.size());

            Iterator itr2 = listBulletEneny.iterator();
            while(itr2.hasNext()) {
                PlayerBullet element = (PlayerBullet) itr2.next();
                if(element.y>=600){
                    itr2.remove();
                }else{
                    backGraphics.drawImage(element.image,element.x,element.y,30,30,null);
                }
            }
            Iterator itr3 = listEnemy.iterator();
            while(itr3.hasNext()) {
                Enemy element = (Enemy) itr3.next();
                if(element.y>=600){
                    itr3.remove();
                }else{
                    backGraphics.drawImage(element.image,element.x,element.y,40,50,null);
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
