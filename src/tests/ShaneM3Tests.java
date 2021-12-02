package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.PlayerView;
import javafx.scene.input.KeyCode;
import static org.junit.Assert.assertNotNull;



public class ShaneM3Tests extends ApplicationTest {
    private Main controller;

    private final double bufferRight = 20;

    private final double rightX = 902;
    private final double rightY = 427;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testPlayerInitial() {
        clickOn("Start");
        write("Scoops");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        assertNotNull(controller.getPlayerView());
    }

    @Test
    public void testPlayerInitialSprite() {
        clickOn("Start");
        write("Scoops");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        assertNotNull(controller.getPlayerView().getImageView());
    }

    @Test
    public void testPlayerRoomChange() {
        clickOn("Start");
        write("Scoops");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        PlayerView player = controller.getPlayerView();
        player.setX(rightX - bufferRight); //set location to known right door
        player.setY(rightY - bufferRight);
        push(KeyCode.D);
        assertNotNull(player);
    }

    @Test
    public void testPlayerRoomChangeSprite() {
        clickOn("Start");
        write("Scoops");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        PlayerView player = controller.getPlayerView();
        player.setX(rightX - bufferRight); //set location to known right door
        player.setY(rightY - bufferRight);
        push(KeyCode.D);
        assertNotNull(controller.getPlayerView().getImageView());
    }
}