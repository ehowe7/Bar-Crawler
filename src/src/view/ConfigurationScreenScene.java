package src.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ConfigurationScreenScene {
    private Font font = Font.loadFont("file:assets/rainyhearts.ttf", 30);
    private Font titleFont = Font.loadFont("file:assets/04B_30__.ttf", 60);
    private Font textFont = javafx.scene.text.Font.loadFont(
            "file:assets/rainyhearts.ttf", 30);

    private Scene configScene;
    private ToggleGroup difficultyGroup;
    private ToggleGroup weaponGroup;
    private TextField usernameTextField;
    private Text errorMessage;
    private Button nextScreenButton;
    private String[] difficulties;
    private String[] weapons;
    private int width;
    private int height;

    /**
     * Initialize all the variables needed to make a Configuration Screen.
     *
     * @param width the width of the window.
     * @param height the height of the window.
     * @param difficulties all the possible difficulties.
     * @param weapons all the possible weapons.
     */
    public ConfigurationScreenScene(int width,
                                    int height,
                                    String[] difficulties,
                                    String[] weapons) {
        this.usernameTextField = new TextField();
        this.usernameTextField.setMaxWidth(300);

        this.nextScreenButton = new Button("Let's go!");
        nextScreenButton.setFont(textFont);

        //create main container:
        this.errorMessage = new Text();

        errorMessage.setFont(font);
        this.difficultyGroup = new ToggleGroup();
        this.weaponGroup = new ToggleGroup();

        this.difficulties = difficulties;
        this.weapons = weapons;

        this.width = width;
        this.height = height;
        //configuration buttons design
    }

    /**
     * create and get the scene of this object.
     *
     * @return the scene that represents this object.
     */
    public Scene getConfigScene() {
        VBox mainVBox = new VBox();
        //Create arrays for user choices of difficulties and weapons
        RadioButton[] difficultyButtons = generateToggleGroup(
                difficultyGroup,
                difficulties);
        RadioButton[] weaponButtons = generateToggleGroup(weaponGroup, weapons);

        //vertically align each set of radio buttons
        VBox difficultyBox = new VBox();
        addRadioButtonsToPane(difficultyButtons, difficultyBox);
        VBox weaponBox = new VBox();
        addRadioButtonsToPane(weaponButtons, weaponBox);

        //place groups of radio buttons next to each other horizontally
        HBox radioBox = new HBox();
        radioBox.getChildren().addAll(difficultyBox, weaponBox);
        radioBox.setAlignment(Pos.CENTER);

        //create title
        Text gameTitle = new Text("Bar Crawler");
        gameTitle.setFont(titleFont);

        //craft main alignment
        mainVBox.getChildren().addAll(gameTitle,
                usernameTextField,
                nextScreenButton,
                errorMessage,
                radioBox);
        mainVBox.setAlignment(Pos.BOTTOM_CENTER);
        Scene configScene = new Scene(mainVBox, width, height);
        this.configScene = configScene;

        //username field design
        usernameTextField.setPromptText("Enter a username");
        usernameTextField.setFont(font);
        return this.configScene;
    }

    /**
     * return the user's inputted username.
     * @return the user's username.
     */
    public String getUsername() {
        return this.usernameTextField.getText().strip();
    }

    /**
     * Get the button that redirects to the next screen.
     * @return the redirect button.
     */
    public Button getNextScreenButton() {
        return this.nextScreenButton;
    }
    /**
     * The index the user selected in the difficulties array passed into the
     * constructor.
     *
     * @return an object that can be casted to Integer that represents the
     * selected difficulty.
     */
    public Object getDifficultyIndex() {
        return ((RadioButton) difficultyGroup
                .getSelectedToggle())
                .getUserData();
    }

    /**
     * The index the user selected in the weapons array passed into the
     * constructor.
     *
     * @return an object that can be casted to Integer that represents the
     * selected weapon.
     */
    public Object getWeaponIndex() {
        return ((RadioButton) weaponGroup.getSelectedToggle()).getUserData();
    }


    /**
     *
     * Disallow null, empty, and whitespace only usernames
     * @return if the username the user has inputted is valid.
     */
    public boolean validateUsernameString() {
        String username = getUsername();
        if (username == null) {
            errorMessage.setText("cannot have null username");
            return false;
        }
        username = username.strip();
        if (username.equals("")) { //str was only whitespace characters or empty
            errorMessage.setText("cannot have empty / "
                    + "whitespace only username");
            return false;
        }
        errorMessage.setText("valid username");
        return true;
    }


    /**
     * given a string[] of options, generate a radio button for each option
     * and add them to the toggle group. Also set their UserData to the index
     * for constant time access of option index.
     *
     * @param toggleGroup the toggle group all the radio buttons belong to
     * @param options the string values of the radio buttons
     * @return an array of generated radio buttons
     */
    private RadioButton[] generateToggleGroup(ToggleGroup toggleGroup,
                                              String[] options) {
        RadioButton[] buttons = new RadioButton[options.length];
        for (int i = 0; i < options.length; i++) {
            RadioButton button = new RadioButton(options[i]);
            button.setToggleGroup(toggleGroup);
            button.setUserData(i);
            button.setFont(font);
            buttons[i] = button;
        }
        if (buttons.length != 0) {
            buttons[0].setSelected(true);
        }
        return buttons;
    }

    /**
     * All all the buttons from the buttons array to pane.
     * @param buttons the buttons to be added to pane.
     * @param pane the pane to add all the buttons to.
     */
    private void addRadioButtonsToPane(RadioButton[] buttons, Pane pane) {
        for (RadioButton button : buttons) {
            pane.getChildren().add(button);
        }
    }


}
