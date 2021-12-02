package tests;

import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;

import static org.junit.Assert.*;


public class BellaM4Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void verifyGunSprite() {
        clickOn("Start");
        write("Beebo");
        clickOn("Gun");
        clickOn("Let's go!");
        assertEquals(controller.getPlayerView().getFileName(),
                "file:assets/alex_sprites/gun/facing_front/standing/gun_front_standing.png");
    }

    @Test
    public void verifySwordSprite() {
        clickOn("Start");
        write("Beebo");
        clickOn("Sword");
        clickOn("Let's go!");
        assertEquals(controller.getPlayerView().getFileName(),
                "file:assets/alex_sprites/sword/facing_front/standing/sword_front_standing.png");
    }

    @Test
    public void verifyBottleSprite() {
        clickOn("Start");
        write("Beebo");
        clickOn("Broken Bottle");
        clickOn("Let's go!");
        assertEquals(controller.getPlayerView().getFileName(),
                "file:assets/alex_sprites/broken_bottle/"
                        + "facing_front/standing/bottle_front_standing.png");
    }

    @Test
    public void verifyLeftSprite() {
        clickOn("Start");
        write("Beebo");
        clickOn("Gun");
        clickOn("Let's go!");
        push(KeyCode.A);
        assertEquals(controller.getPlayerView().getFileName(),
                "file:assets/alex_sprites/gun/facing_left/standing/gun_left_standing.png");
    }

    @Test
    public void verifyRightSprite() {
        clickOn("Start");
        write("Beebo");
        clickOn("Gun");
        clickOn("Let's go!");
        push(KeyCode.D);
        assertEquals(controller.getPlayerView().getFileName(),
                "file:assets/alex_sprites/gun/facing_right/standing/gun_right_standing.png");
    }

    @Test
    public void verifyBackSprite() {
        clickOn("Start");
        write("Beebo");
        clickOn("Gun");
        clickOn("Let's go!");
        push(KeyCode.W);
        assertEquals(controller.getPlayerView().getFileName(),
                "file:assets/alex_sprites/gun/facing_back/standing/gun_back_standing.png");
    }

}