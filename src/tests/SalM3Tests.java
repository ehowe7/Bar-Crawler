package tests;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.PlayerView;
import static org.junit.Assert.*;


public class SalM3Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testPlayerMovement() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        PlayerView pV = controller.getPlayerView();
        pV.setX(100);
        pV.setY(100);
        assertEquals(pV.getX(), 100, 0);
        assertEquals(pV.getY(), 100, 0);
    }

    @Test
    public void testInvalidPlayerMovement() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        PlayerView pV = controller.getPlayerView();
        pV.setX(100); //set location to known value
        pV.setY(100);

        pV.setX(-1);
        pV.setY(-1); //set location to invalid location
        //location should be last valid value
        assertEquals(pV.getX(), 100, 0);
        assertEquals(pV.getY(), 100, 0);
    }

    @Test
    public void testPlayerMovementToBoarder() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        PlayerView pV = controller.getPlayerView();
        pV.setX(100); //set location to known value
        pV.setY(100);

        pV.setX(1920);
        pV.setY(1920); //set location to invalid location -> boarder
        //location should be last valid value
        assertEquals(pV.getX(), 100, 0);
        assertEquals(pV.getY(), 100, 0);
    }
}
