package src.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class WelcomeScreen {
    private Font font = Font.loadFont("file:assets/rainyhearts.ttf", 30);
    private Font titleFont = Font.loadFont("file:assets/04B_30__.ttf", 60);
    private Button startButton;
    private int width;
    private int height;
    /**
     * Create a WelcomeScreen object.
     *
     * @param width the width of the screen.
     * @param height the height of the screen.
     */
    public WelcomeScreen(int width, int height) {
        this.width = width;
        this.height = height;
        startButton = new Button("Start");
    }

    /**
     * return the welcome screen.
     * @return the welcome screen scene object.
     */
    public Scene getWelcomeScreen() {
        Font textFont = javafx.scene.text.Font.loadFont("file:assets/rainyhearts.ttf", 30);
        startButton.setFont(textFont);
        startButton.setAlignment(Pos.BOTTOM_CENTER);
        Text gameTitle = new Text("Bar Crawler");
        gameTitle.setFont(titleFont);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(gameTitle, startButton);
        vBox.setAlignment(Pos.CENTER);
        Scene welcomeScreen = new Scene(vBox, width, height);
        return welcomeScreen;
    }

    public Button getStartButton() {
        return startButton;
    }

}
