package src.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.model.GameModel;

import java.util.ArrayList;


public class RoomView {
    private final int moneyConstant = 500;
    private int money;
    private Stage mainWindow;
    private GameModel gameModel;
    private Font textFont = Font.loadFont(
        "file:assets/rainyhearts.ttf", 22);
    //do we want all the rooms to have the same font or different?
    //I set this to the same screen size as before but we can change it.

    private int width = 1920 / 2;
    private int height = 1080 / 2;
    private int enemyCount;
    private boolean openable;
    //do we even need these 4?  seems like only maze controller would be concerned with this info
    private boolean hasLeft;
    private boolean hasRight;
    private boolean hasUp;
    private boolean hasDown;
    private PlayerView playerView;
    private Image background;
    private boolean visited;

    private ArrayList<MonsterView> monsterViews;
    private InventoryView inventoryView;

    private Pane monsterLayer;
    private boolean isLocked;
    private boolean challengeActivated;
    /**
     * initialize the variables for the Room screen.
     * @param width width of screen
     * @param height height of screen
     * @param gameModel player data (weapon, difficulty, etc)
     * @param background the background of the room
     * @param playerView the visual data of the player to display
     * @param monsterViews the array of visual monsterviews to display
     * @param inventoryView the visual inventory
     */
    public RoomView(int width,
                    int height,
                    GameModel gameModel,
                    Image background,
                    PlayerView playerView,
                    ArrayList<MonsterView> monsterViews, InventoryView inventoryView) {
        //if we want to keep the 4 directional door buttons then instantiate them here
        this.width = width;
        this.height = height;
        //set money based on difficulty
        this.money = moneyConstant / (gameModel.getDifficultyIndex() + 1);
        this.gameModel = gameModel;
        this.enemyCount = 2; //for now
        this.background = background;
        this.playerView = playerView;
        this.visited = false;
        this.monsterViews = monsterViews;
        this.inventoryView = inventoryView;
        this.isLocked = false;

        this.monsterLayer = new Pane();
    }

    public RoomView(int width,
                    int height,
                    GameModel gameModel,
                    String backgroundLocation,
                    PlayerView playerView,
                    ArrayList<MonsterView> monsterViews, InventoryView inventoryView) {
        this(width, height, gameModel,
                new Image(backgroundLocation), playerView, monsterViews, inventoryView);
    }


    /**
     * Returns the scene of the room
     * @return the generated scene
     */
    public Scene getScene() {
        ImageView barSceneViewer = new ImageView(background);
        barSceneViewer.setFitWidth(this.width);
        barSceneViewer.setFitHeight(this.height);
        VBox background = new VBox(barSceneViewer);

        //main container:
        VBox newScreen = new VBox();

        //money display
        Text moneyText = new Text("$" + this.money);
        moneyText.setFont(textFont);
        moneyText.setFill(Color.DARKGOLDENROD);

        //Username display
        Text usernameText = new Text(gameModel.getUsername());
        usernameText.setFont(textFont);
        usernameText.setFill(Color.GHOSTWHITE);

        //Weapon Display
        Text weaponText = new Text(gameModel.getWeapon());
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
        StackPane inventoryBox = inventoryView.getStack();
        bottomBar.getChildren().addAll(inventoryBox, bottomSpace, weaponText);
        bottomBar.setAlignment(Pos.BOTTOM_CENTER);
        borderPane.setBottom(bottomBar);
        newScreen.getChildren().addAll(
                new Text("username: " + gameModel.getUsername()),
                new Text("difficulty: " + gameModel.getDifficulty()),
                new Text("weapon: " + gameModel.getWeapon())
        );



        StackPane stackScreen = new StackPane();

        // generate layer of monsters
        this.monsterLayer = new Pane();
        for (MonsterView m : monsterViews) {
            this.monsterLayer.getChildren().add(m.getImageView());
        }
        stackScreen.getChildren().addAll(background,
                playerView.getLayer(),
                this.monsterLayer,
                borderPane);


        return new Scene(stackScreen, width, height);
    }
    public boolean hasVisited() {

        return visited;
    }

    public PlayerView getPlayerView() {
        return this.playerView;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<MonsterView> getMonsterViews() {
        return monsterViews;
    }

    public void setMonsterViews(ArrayList<MonsterView> monsterViews) {
        this.monsterViews = monsterViews;
    }

    public Pane getMonsterLayer() {
        return this.monsterLayer;
    }

    public void lockDoors() {
        isLocked = true;
    }

    public void unlockDoors() {
        isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isChallengeActivated() {
        return challengeActivated;
    }

    public void setChallengeActivated(boolean b) {
        this.challengeActivated = b;
    }
}

