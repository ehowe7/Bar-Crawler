package tests;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.MonsterView;
import src.view.PlayerView;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ShaneM4Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testMonstersCanBeDamaged() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
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
    public void testMonsterSpriteChangeOnDeath() {
        clickOn("Start");
        write("username");
        clickOn("Gun");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        ArrayList<MonsterView> mV = controller.getMazeView().getCurrent().getMonsterViews();
        MonsterView monster = mV.get(0);
        PlayerView player = controller.getPlayerView();
        player.setX(monster.getX());
        player.setY(monster.getY());
        Image alive = monster.getImageView().getImage();
        monster.currentModel().setMonsterHP(0.0);
        press(KeyCode.SPACE);
        assertTrue(alive != monster.getImageView().getImage());
    }

}