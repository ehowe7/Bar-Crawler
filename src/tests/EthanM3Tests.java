package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import static org.junit.Assert.assertNotNull;



public class EthanM3Tests extends ApplicationTest {
    private Main controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testPlayerTipsy() {
        clickOn("Start");
        write("username");
        clickOn("Tipsy");
        clickOn("Let's go!");
        assertNotNull(controller.getPlayerView().getImageView());
    }

    @Test
    public void testPlayerDrunk() {
        clickOn("Start");
        write("username");
        clickOn("Drunk");
        clickOn("Let's go!");
        assertNotNull(controller.getPlayerView().getImageView());
    }

    @Test
    public void testPlayerBow() {
        clickOn("Start");
        write("username");
        clickOn("Bow");
        clickOn("Let's go!");
        assertNotNull(controller.getPlayerView().getImageView());
    }

    @Test
    public void testPlayerBrokenBottle() {
        clickOn("Start");
        write("username");
        clickOn("Broken Bottle");
        clickOn("Let's go!");
        assertNotNull(controller.getPlayerView().getImageView());
    }
}
