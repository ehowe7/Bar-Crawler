package tests;


import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.MonsterView;
import src.view.PlayerView;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmersonM6Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testPlayerDamagesBoss() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        controller.getMazeView().moveUp();
        controller.getMazeView().moveUp();
        controller.getMazeView().moveUp();
        controller.getMazeView().moveRight();
        controller.getMazeView().moveRight();
        controller.getMazeView().moveRight();           //moves to final boss room
        ArrayList<MonsterView> mV = controller.getMazeView().getCurrent().getMonsterViews();
        MonsterView monster = mV.get(0);
        PlayerView player = controller.getPlayerView();
        player.setX(monster.getX());
        player.setY(monster.getY());
        double prevHP = monster.currentModel().getMonsterHP();
        press(KeyCode.SPACE);
        assertTrue(prevHP > monster.currentModel().getMonsterHP());
    }

    @Test
    public void testPlayerNoRoomChangeInChallenge() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        controller.getMazeView().moveDown();
        controller.getMazeView().moveLeft();
        push(KeyCode.C);
        assertTrue(!controller.getMazeView().canMoveDown() && !controller.getMazeView().canMoveUp()
                && !controller.getMazeView().canMoveLeft()
                && !controller.getMazeView().canMoveRight());
    }
}
