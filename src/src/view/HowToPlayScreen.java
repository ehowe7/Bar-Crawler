package src.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import src.model.GameModel;

public class HowToPlayScreen {
    private int width;
    private int height;
    private GameModel gameModel;
    private PlayerView playerView;

    public HowToPlayScreen(int width, int height, GameModel gameModel, PlayerView playerView) {
        this.width = width;
        this.height = height;
        this.gameModel = gameModel;
        this.playerView = playerView;
    }

    public Scene getHowToPlayScreen() {
        //background image:
        Image playScene = new Image("file:assets/HowToPlay.png");
        ImageView playSceneViewer = new ImageView(playScene);
        playSceneViewer.setFitWidth(width);
        playSceneViewer.setFitHeight(height);
        VBox background = new VBox(playSceneViewer);

        return new Scene(background);
    }

    public String getFileName() {
        return "file:assets/HowToPlay.png";
    }
}
