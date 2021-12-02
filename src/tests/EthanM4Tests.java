package tests;

import org.junit.Test;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.MonsterView;
import src.view.PlayerView;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class EthanM4Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testDoorsMonstersAlive() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        PlayerView player = controller.getPlayerView();
        ArrayList<MonsterView> mV = controller.getMazeView().getCurrent().getMonsterViews();
        MonsterView prev = mV.get(0);
        player.setX(467);
        player.setY(462);
        MonsterView after = mV.get(0);
        assertTrue(after == prev);
    }

    @Test
    public void testMonsterDamagesPlayer() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        ArrayList<MonsterView> mV = controller.getMazeView().getCurrent().getMonsterViews();
        MonsterView monster = mV.get(0);
        PlayerView player = controller.getPlayerView();
        player.setX(monster.getX());
        player.setY(monster.getY());
        double prevHP = player.getModel().getPlayerHP();
        monster.currentModel().attack(player, monster);
        assertTrue(prevHP > player.getModel().getPlayerHP());
    }
}
