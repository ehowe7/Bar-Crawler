
package tests;


import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.*;
import static org.junit.Assert.*;

public class EthanM5Tests extends ApplicationTest {
    private Main controller;
    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }
    @Test
    public void testAddDuplicateInventory() {
        HealthPotionView healthyBoy = new HealthPotionView(
                new Image("file:assets/inventory_items/health.png"));

        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        InventoryView inventoryView = controller.getInventoryView();
        inventoryView.addToInventoryCodeOnly(healthyBoy);
        inventoryView.addToInventoryCodeOnly(healthyBoy);

        assertEquals(inventoryView.getCurrentSize(), 1);

    }

    @Test
    public void testEmptyInventoryRemove() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        InventoryView inventoryView = controller.getInventoryView();
        inventoryView.removeFromInventory(0);
        assertEquals(inventoryView.getCurrentSize(), 0);
    }
}