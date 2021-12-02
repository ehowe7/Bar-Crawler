package tests;


import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.InventoryView;
import static org.junit.Assert.*;

public class SalM5Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testInventoryNotNull() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        InventoryView inventoryView = controller.getInventoryView();
        assertTrue(inventoryView != null);
    }

    @Test
    public void testInitialInventoryEmpty() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        push(KeyCode.TAB);
        InventoryView inventoryView = controller.getInventoryView();
        for (int i = 0; i < 4; i++) {
            assertTrue(inventoryView.getItem(i) == null);
        }
    }

}
