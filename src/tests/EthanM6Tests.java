package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.MonsterView;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EthanM6Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testPlayerSkipBoss() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");

        //Traverse to boss room
        controller.getMazeView().moveUp();
        controller.getMazeView().moveUp();
        controller.getMazeView().moveUp();
        controller.getMazeView().moveRight();
        controller.getMazeView().moveRight();
        controller.getMazeView().moveRight();

        ArrayList<MonsterView> mV = controller.getMazeView().getCurrent().getMonsterViews();
        MonsterView monster = mV.get(0);

        monster.currentModel().setMonsterHP(0);

        assertFalse(controller.getMazeView().canMoveUp());
    }

    @Test
    public void testBossStartHealth() {
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
        assertTrue(monster.currentModel().getMonsterHP() > 0);
    }
}
