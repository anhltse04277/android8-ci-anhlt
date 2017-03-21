package controllers;

import utils.Utils;
import views.EnemyView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by AnhLe on 3/9/2017.
 */
public class PoolControllerManager extends ControllerManager {
    private PlayerPlaneController playerPlaneController ;
    private int timerCountEnemy = 0;
    private int timeCountBulletEnemy = 0;
    private int timeCountIsLand = 0;
    private int timeCountPower = 0;
   // private int timCountLevel = 0;
    private int timeCountBomb = 0;
    Vector<GameController> listPlayerBullet;
    ArrayList<IsLandController> listislandController = new ArrayList<>();

    public PoolControllerManager() {
        listPlayerBullet = new Vector<>();
        playerPlaneController = new PlayerPlaneController(240,650,listPlayerBullet);
        gameControllers.add(playerPlaneController);
    }
    public  PlayerPlaneController getPlayerPlaneController() {
        return playerPlaneController;
    }

    @Override
    public  void run() {
        if(listPlayerBullet != null){
            for(GameController gameController: listPlayerBullet){
                gameControllers.add(gameController);
            }
            listPlayerBullet.clear();
        }
        super.run();
        spawnEnemies();
    }

    private void spawnEnemies() {
        if(playerPlaneController.isCheckGetBomb()){
            for(GameController gc :gameControllers ){
                if(gc instanceof EnemyController){
                    gc.model.explosive();
                    if(gc.model.isExplosive()){
                        if (!((EnemyView)gc.view).explode()) {
                            gc.model.setDie();
                        }
                    }


                }
            }
            playerPlaneController.setCheckGetBomb(false);
        }

//        int levelPlayerPlane = playerPlaneController.getLevel();
//        if(levelPlayerPlane ==2){
//            timCountLevel++;
//            if(timCountLevel > 180){
//                playerPlaneController.setLevel(1);
//                timCountLevel = 0;
//            }
//        }
        timerCountEnemy++;
        timeCountBulletEnemy++;
        timeCountIsLand++;
        timeCountPower++;
        timeCountBomb++;
        if (timerCountEnemy > 60) {
            timerCountEnemy = 0;

            EnemyController enemyPlaneController = null;
            int typeEnemy = Utils.randowNumber(1,3);
            if(typeEnemy == 1){
                enemyPlaneController =    EnemyController.create(EnemyController.EnemyType.YELLOW);
            } else if(typeEnemy == 2){
                enemyPlaneController =   EnemyController.create(EnemyController.EnemyType.GREEN);
            } else if(typeEnemy == 3){
                enemyPlaneController =   EnemyController.create(EnemyController.EnemyType.BLUE);
            }
            this.gameControllers.add(enemyPlaneController);



        }

        if(timeCountBulletEnemy > 120){
            timeCountBulletEnemy = 0;
            int size = gameControllers.size();
            for(int i=0; i< size; i++){

                if(gameControllers.get(i) instanceof  EnemyController){
                    GameController gameTemp = gameControllers.get(i);
                    gameControllers.add(((EnemyController)gameTemp).shoot(((EnemyController) gameTemp).getEnemyType()));
                }


            }

        }
        if(timeCountIsLand > 240){
            timeCountIsLand = 0;
            String s = "";
            int i = Utils.randowNumber(1,2);
            if(i==1){
                s = "island.png";
            } else {
                s = "island-2.png";
            }
            gameControllers.add(new IsLandController(Utils.randowNumber(0,450),0, s));
           // gameControllers.add(new PowerUpController(Utils.randowNumber(0,660),0));
        }
        int numberCount = Utils.randowNumber(300,420);
                if(timeCountPower > numberCount){
                    timeCountPower = 0;
                    gameControllers.add(new PowerUpController(Utils.randowNumber(0,660),0));
                }

                if(timeCountBomb > 600){
                    timeCountBomb = 0;
                    gameControllers.add(new BombController(Utils.randowNumber(0,660),0));
                }

    }
}
