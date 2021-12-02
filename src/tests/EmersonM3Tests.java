package tests;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.RoomView;
import src.view.MazeView;
import static org.junit.Assert.*;

public class EmersonM3Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testOldRoomVisited() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        //get RoomView from MazeView
        push(KeyCode.W);
        release(KeyCode.W);
        push(KeyCode.W);
        release(KeyCode.W);
        push(KeyCode.W);
        release(KeyCode.W);
        push(KeyCode.W);
        release(KeyCode.W);
        push(KeyCode.W);
        release(KeyCode.W);
        push(KeyCode.W);
        release(KeyCode.W);
        push(KeyCode.W);
        release(KeyCode.W);
        push(KeyCode.A);
        push(KeyCode.W);
        push(KeyCode.W);
        push(KeyCode.W);
        push(KeyCode.W);
        push(KeyCode.W);
        push(KeyCode.W);
        push(KeyCode.S);
        RoomView mV = controller.getMazeView().getCurrent();
        assertTrue(mV.hasVisited());
    }

    @Test
    public void testNextRoomVisited() {

        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        //get RoomView from MazeView
        RoomView upRoom = controller.getMazeView().moveUp();
        assertFalse(upRoom.hasVisited());
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
