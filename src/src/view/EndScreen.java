package src.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import src.model.GameModel;
import src.model.PlayerModel;


public class EndScreen {

    private Font textFont = Font.loadFont(
            "file:assets/rainyhearts.ttf", 22);
    private int money;
    private final int moneyConstant = 500;
    private Button goBack;
    private GameModel gameModel;
    private int width;
    private int height;
    private PlayerModel playerModel;

    /**
     * Initialize all the variables needed to make an exit screen.
     *
     * @param width width of the window
     * @param height height of the window
     * @param gameModel the game model containing player data
     * @param playerModel playerModel of the player
     */
    public EndScreen(int width, int height, GameModel gameModel, PlayerModel playerModel) {
        this.width = width;
        this.height = height;
        //ability to return to previous scene:
        this.goBack = new Button("Do it all again :D");
        goBack.setFont(textFont);

        //set money based on difficulty
        this.money = moneyConstant / (gameModel.getDifficultyIndex() + 1);

        this.gameModel = gameModel;
        this.playerModel = playerModel;

    }

    /**
     * Return the initialGameScene.
     * @return the initialGameScene.
     */
    public Scene getEndScene() {
        //background image:
        Image barScene = new Image("file:assets/WinScreen.png");
        ImageView barSceneViewer = new ImageView(barScene);
        barSceneViewer.setFitWidth(this.width);
        barSceneViewer.setFitHeight(height);
        VBox background = new VBox(barSceneViewer);

        //main container:
        VBox newScreen = new VBox();

        //money display
        Text moneyText = new Text(" ");
        moneyText.setFont(textFont);
        moneyText.setFill(Color.DARKGOLDENROD);

        //Username display
        Text usernameText = new Text(gameModel.getUsername());
        usernameText.setFont(textFont);
        usernameText.setFill(Color.GHOSTWHITE);

        //Weapon Display
        Text weaponText = new Text(" ");
        weaponText.setFont(textFont);
        weaponText.setFill(Color.GHOSTWHITE);

        //add to BorderPane
        BorderPane borderPane = new BorderPane();

        //top bar
        HBox topBar = new HBox();
        HBox usernameBox = new HBox();
        HBox moneyBox = new HBox();
        Region topSpace = new Region();
        HBox.setHgrow(topSpace, Priority.ALWAYS);
        topBar.setPrefWidth(width);
        topBar.getChildren().setAll(usernameBox, topSpace, moneyBox);
        topBar.setAlignment(Pos.TOP_CENTER);
        usernameBox.getChildren().add(usernameText);
        usernameBox.setAlignment(Pos.CENTER_LEFT);
        moneyBox.getChildren().add(moneyText);
        moneyBox.setAlignment((Pos.TOP_RIGHT));
        borderPane.setTop(topBar);

        //bottom bar
        HBox bottomBar = new HBox();
        Region bottomSpace = new Region();
        HBox.setHgrow(bottomSpace, Priority.ALWAYS);
        bottomBar.getChildren().addAll(goBack, bottomSpace, weaponText);
        bottomBar.setAlignment(Pos.BOTTOM_CENTER);
        borderPane.setBottom(bottomBar);

        VBox textHolder = new VBox();
        textHolder.getChildren().addAll(
                new Text("username: " + gameModel.getUsername()),
                new Text("difficulty: " + gameModel.getDifficulty()),
                new Text("weapon: " + gameModel.getWeapon()),
                new Text("health points remaining: " + (int) (Math.round(playerModel.getPlayerHP() * 100))),
                new Text("number of attacks dealt: "
                        + playerModel.getNumberAttacks()),  //player has died :(
                new Text("number of potions used: " + playerModel.getNumberPotionsUsed())
        );

        StackPane layerTextOnBackground = new StackPane();
        layerTextOnBackground.getChildren().addAll(background, textHolder);
        StackPane stackScreen = new StackPane();
        stackScreen.getChildren().addAll(layerTextOnBackground, borderPane);

        return new Scene(stackScreen, width, height);
    }

    /**
     * Return the button that returns to the previous screen.
     * @return the back button.
     */
    public Button getGoBackButton() {
        return this.goBack;
    }
}