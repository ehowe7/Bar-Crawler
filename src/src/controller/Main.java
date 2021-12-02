package src.controller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.model.Direction;
import src.model.GameModel;
import src.view.*;
import src.model.Weapon;

public class Main extends Application {
    private Stage mainWindow;
    private GameModel gameModel;
    private final int width = 1920 / 2;
    private final int height = 1080 / 2;
    private final String[] difficulties = {"Sober", "Tipsy", "Drunk"};
    private final String[] weapons = {"Sword", "Gun", "Broken Bottle"};
    private PlayerView pV;
    private MazeView mV;
    private InventoryView iV;
    private HowToPlayScreen playScreen;


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainWindow = primaryStage;
        this.gameModel = new GameModel();
        initWelcomeScreen();
    }

    private void initWelcomeScreen() {
        WelcomeScreen welcomeScreen = new WelcomeScreen(width, height);
        // Welcome Button goes from welcome screen to config scene
        welcomeScreen.getStartButton().setOnAction(actionEvent -> {
            goToConfigScreen();
        });
        Scene scene = welcomeScreen.getWelcomeScreen();
        mainWindow.setTitle("Welcome Screen");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    private void goToConfigScreen() {
        ConfigurationScreenScene configScene = new ConfigurationScreenScene(
                width,
                height,
                difficulties,
                weapons);
        configScene.getNextScreenButton().setOnAction(actionEvent ->
                goToInitialScreen(configScene));
        mainWindow.setTitle("Config Screen");
        mainWindow.setScene(configScene.getConfigScene());
        mainWindow.show();
    }

    private void goToInitialScreen(ConfigurationScreenScene configScene) {
        //show How to Play
        playScreen = new HowToPlayScreen(width, height, gameModel, getPlayerView());
        mainWindow.setScene(playScreen.getHowToPlayScreen());
        mainWindow.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.TAB) {
                if (configScene.validateUsernameString()) {
                    gameModel.setUsername(configScene.getUsername());
                    gameModel.setDifficulty(difficulties[
                            (Integer) configScene.getDifficultyIndex()]);
                    gameModel.setWeapon(weapons[
                            (Integer) configScene.getWeaponIndex()]);
                    gameModel.setDifficultyIndex(
                            (Integer) configScene.getDifficultyIndex());

                    Pane playerLayer = new Pane();
                    //starting sprites
                    Image playerImage = new Image("file:assets/AlexFWD.png");
                    Weapon weapon = Weapon.SWORD;

                    switch (weapons[(Integer) configScene.getWeaponIndex()]) {
                    case "Sword":
                        playerImage = new Image(
                                "file:assets/alex_sprites/sword/facing_front/"
                                        + "standing/sword_front_standing.png");
                        weapon = Weapon.SWORD;
                        break;
                    case "Gun":
                        playerImage = new Image(
                                "file:assets/alex_sprites/gun/facing_front/"
                                        + "standing/gun_front_standing.png");
                        weapon = Weapon.GUN;
                        break;
                    case "Broken Bottle" :
                        playerImage = new Image(
                                "file:assets/alex_sprites/broken_bottle/facing_front/"
                                        + "standing/bottle_front_standing.png");
                        weapon = Weapon.BOTTLE;
                        break;
                    default:
                        break;
                    }

                    PlayerView playerView = new PlayerView(playerLayer, (double) width / 2,
                            (double) height / 2, width, height, weapon, Direction.FRONT);
                    this.pV = playerView; //for testing purposes
                    Image inventoryImage = new Image("file:assets/inventory.png");
                    InventoryView inventoryView = new InventoryView(inventoryImage);
                    MazeView maze =
                            new MazeView(width, height, 5, 5, gameModel, playerView, inventoryView);
                    this.mV = maze;
                    this.iV = inventoryView;
                    PlayerController playerController =
                            new PlayerController(mainWindow, playerView, maze, inventoryView);
                    EndScreen endScreen =
                        new EndScreen(1920 / 2,
                            1080 / 2,
                            gameModel,
                            playerView.getModel());
                    endScreen.getGoBackButton().setOnAction(actionEvent1 -> {
                        goToConfigScreen();
                    });
                    MazeController mazeController =
                        new MazeController(mainWindow, maze, playerView, gameModel, endScreen);
            
                    MonsterController monsterController
                            = new MonsterController(mainWindow, playerView, maze);
                    mainWindow.setScene(maze.getCurrent().getScene());
                    mainWindow.show();
                    AnimationTimer timer = new AnimationTimer() {
                        @Override
                        public void handle(long now) {
                            //checking if player has died
                            if (playerView.getModel().getPlayerHP() <= 0) {
                                goToDieScreen(configScene, this);
                            }
                        }
                    };
                    timer.start();
                }
            }
        });
    }

    private void goToDieScreen(ConfigurationScreenScene configScene, AnimationTimer timer) {
        timer.stop();
        if (configScene.validateUsernameString()) {
            gameModel.setUsername(configScene.getUsername());
            gameModel.setDifficulty(difficulties[
                    (Integer) configScene.getDifficultyIndex()]);
            gameModel.setWeapon(weapons[
                    (Integer) configScene.getWeaponIndex()]);
            gameModel.setDifficultyIndex(
                    (Integer) configScene.getDifficultyIndex());

            DieScreen screen = new DieScreen(
                    width, height, gameModel, pV.getModel());


            screen.getGoBackButton().setOnAction(actionEvent1 -> {
                goToConfigScreen();
            });
            mainWindow.setTitle("YOU Lose! Sad!");
            mainWindow.setScene(screen.getEndScene());
            mainWindow.show();
        }
    }

    public PlayerView getPlayerView() {
        return this.pV;
    }

    public MazeView getMazeView() {
        return this.mV;
    }
    public InventoryView getInventoryView() {
        return this.iV;
    }
    /**
     * Main method.
     * @param args parameters to main method.
     */
    public static void main(String[] args) {
        launch(args);
    }

    public HowToPlayScreen getPlayScreen() {
        return playScreen;
    }
}
