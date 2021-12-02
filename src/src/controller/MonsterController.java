package src.controller;

import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import src.view.MazeView;
import src.view.MonsterView;
import src.view.PlayerView;



public class MonsterController {
    private PlayerView player;
    private Stage stage;
    private MazeView maze;
    private double dx = 1;
    private double dy = 1;

    public MonsterController(Stage stage, PlayerView player, MazeView maze) {
        this.stage = stage;
        this.player = player;
        this.maze = maze;



        final long[] startTime = {System.currentTimeMillis()};
        final long[] lastAttackTime = {0};
        final long[] startMoveTime = {0};
        final long[] lastMoveTime = {0};
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                startTime[0] = System.currentTimeMillis();
                startMoveTime[0] = System.currentTimeMillis();
                for (MonsterView monster : maze.getCurrent().getMonsterViews()) {
                    monster.stopHit();
                    if (player.getModel().getPlayerHP() > 0
                            && Math.abs(player.getCenterX() - monster.getCenterX()) < 55
                            && Math.abs(player.getCenterY() - monster.getCenterY()) < 60
                            && startTime[0] - lastAttackTime[0] > 1000) {
                        monster.currentModel().attack(player, monster);
                        monster.hit();
                        System.out.println("attacked player!");
                        lastAttackTime[0] = startTime[0];
                    } else if (Math.abs(player.getCenterX() - monster.getCenterX()) < 200
                            && Math.abs(player.getCenterY() - monster.getCenterY()) < 200
                            && (Math.abs(player.getCenterX() - monster.getCenterX()) > 20
                            || Math.abs(player.getCenterY() - monster.getCenterY()) > 20)
                            && startMoveTime[0] - lastMoveTime[0] > 40) {

                        double xDelta = (monster.getX() - player.getX()) * -1;
                        double yDelta = (monster.getY() - player.getY()) * -1;
                        double len = Math.sqrt(xDelta * xDelta + yDelta * yDelta);

                        double scalingConstant = 10;
                        xDelta /= len;
                        yDelta /= len;
                        monster.setDx(xDelta * scalingConstant);
                        monster.setDy(yDelta * scalingConstant);
                        monster.move();
                        monster.updateUI();
                        lastMoveTime[0] = startMoveTime[0];
                    }
                }
            }
        };
        timer.start();

    }

}
