package tests;


import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;

import static org.junit.Assert.*;

public class SalM6Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testChallengeRoomEmpty() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        controller.getMazeView().moveDown();
        controller.getMazeView().moveLeft();
        System.out.println("size: "
                + controller.getMazeView().getCurrent().getMonsterViews().size());
        assertTrue(controller.getMazeView().getCurrent().getMonsterViews().size() == 0);
    }

    @Test
    public void testChallengeRoomActivationKey() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        controller.getMazeView().moveDown();
        controller.getMazeView().moveLeft();
        push(KeyCode.C);
        System.out.println("size: "
                + controller.getMazeView().getCurrent().getMonsterViews().size());
        assertTrue(controller.getMazeView().getCurrent().getMonsterViews().size() != 0);
    }

}
