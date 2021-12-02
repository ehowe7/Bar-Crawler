package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.PlayerView;
import javafx.scene.input.KeyCode;

import static org.junit.Assert.*;


public class BellaM3Tests extends ApplicationTest {
    private Main controller;
    private final double bufferTop = 20;
    private final double bufferBottom = 20;
    private final double bufferLeft = 20;
    private final double bufferRight = 20;

    private final double rightX = 902;
    private final double rightY = 427;
    private final double leftX = 57;
    private final double leftY = 418;
    private final double topX = 470;
    private final double topY = 130;
    private final double bottomX = 467;
    private final double bottomY = 462;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
    }
    // verify the right door works
    @Test
    public void verifyRightDoor() {
        System.out.println("verifyRightDoor");
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        PlayerView pV = controller.getPlayerView();
        // right door
        pV.setX(rightX - bufferRight); //set location to known right door
        pV.setY(rightY - bufferRight);
        System.out.println(pV.getX() + ", " + pV.getY());
        push(KeyCode.D);
        assertEquals(pV.getCenterX(), 136, 20);
        assertEquals(pV.getCenterY(), 457, 20);
    }
    //verify the left door works
    @Test
    public void verifyLeftDoor() {
        System.out.println("verifyLeftDoor");
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        PlayerView pV = controller.getPlayerView();
        // //set location to known left door
        pV.setX(leftX + bufferRight);
        pV.setY(leftY + bufferRight);
        push(KeyCode.A);
        System.out.println(pV.getX() + ", " + pV.getY());
        assertEquals(pV.getCenterX(), 81, 20);
        assertEquals(pV.getCenterY(), 309, 20);
    }
    // verify the bottom door works
    @Test
    public void verifyBottomDoor() {
        System.out.println("verifyBottomDoor");
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        PlayerView pV = controller.getPlayerView();
        // right door
        pV.setX(bottomX - bufferRight); //set location to known right door
        pV.setY(bottomY - bufferRight);
        System.out.println(pV.getX() + ", " + pV.getY());
        push(KeyCode.S);
        assertEquals(pV.getCenterX(), 466, 20);
        assertEquals(pV.getCenterY(), 329, 20);
    }
    //verify the left door works
    @Test
    public void verifyTopDoor() {
        System.out.println("verifyTopDoor");
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        PlayerView pV = controller.getPlayerView();
        // //set location to known left door
        pV.setX(topX + bufferRight);
        pV.setY(topY + bufferRight);
        push(KeyCode.A);
        System.out.println(pV.getX() + ", " + pV.getY());
        assertEquals(pV.getCenterX(), 489, 20);
        assertEquals(pV.getCenterY(), 189, 20);
    }
}
