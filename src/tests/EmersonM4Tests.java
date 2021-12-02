package tests;


import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.MonsterView;
import src.view.RoomView;
import src.view.MazeView;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class EmersonM4Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testMonstersPlaced() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        ArrayList<MonsterView> mV = controller.getMazeView().getCurrent().getMonsterViews();
        assertTrue(mV.size() >= 1);
    }

    @Test
    public void testNextRoomMonsterPlaced() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        ArrayList<MonsterView> mV = controller.getMazeView().moveUp().getMonsterViews();
        assertTrue(mV.size() >= 1);
    }

    @Test
    public void testRoomView() {

        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        RoomView mV = controller.getMazeView().getCurrent();
        assertNotNull(mV);
    }

    @Test
    public void testMazeView() {

        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        MazeView mV = controller.getMazeView();
        assertNotNull(mV);
    }
}
