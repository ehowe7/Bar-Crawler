package tests;


import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import src.controller.Main;
import src.view.AttackPotionView;
import src.view.HealthPotionView;
import src.view.InventoryView;

import static org.junit.Assert.*;

public class BellaM5Tests extends ApplicationTest {
    private Main controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Main();
        controller.start(primaryStage);
        primaryStage.show();
    }

    @Test
    public void testInitialMultipler() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        double multiplier = controller.getPlayerView().getModel().getDamageMultiplier();
        assertTrue(multiplier == 1.0);
    }

    @Test
    public void testAttackMultiplier() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        press(KeyCode.TAB);

        AttackPotionView hp = new AttackPotionView(
                new Image("file:assets/inventory_items/health.png"));
        hp.useItem(controller.getPlayerView());
        double multiplier = controller.getPlayerView().getModel().getDamageMultiplier();
        System.out.println("The damage multipler is: " + multiplier);
        assertTrue(multiplier != 1.0);
    }

    @Test
    public void testInventoryRemove() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");

        HealthPotionView healthyBoy = new HealthPotionView(
                new Image("file:assets/inventory_items/health.png"));
        InventoryView inventoryView = controller.getInventoryView();
        inventoryView.addToInventoryCodeOnly(healthyBoy);
        inventoryView.removeFromInventory(0);
        assertEquals(inventoryView.getItem(0), (null));
    }
}
