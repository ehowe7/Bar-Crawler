package tests;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.PlayerView;
import static org.junit.Assert.*;


public class BellaM6Tests extends ApplicationTest {
    private Main controller;
    private PlayerView playerView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testHowToPlayScreenAppears() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        assertEquals(controller.getPlayScreen().getFileName(), "file:assets/HowToPlay.png");
    }

    @Test
    public void testHowToPlayScreenTab() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        press(KeyCode.TAB);
        assertEquals(controller.getPlayerView().getFileName(),
                "file:assets/alex_sprites/sword/facing_front/standing/sword_front_standing.png");
    }

}
