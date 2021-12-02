package tests;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import src.controller.Main;
import static org.testfx.api.FxAssert.verifyThat;


public class ConfigurationScreenSceneTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main controller = new Main();
        controller.start(primaryStage);
    }

    @Test
    public void testPlay() {
        clickOn("Start");
        verifyThat("Bar Crawler", NodeMatchers.isNotNull());
    }

    @Test
    public void testWhitespace() {
        clickOn("Start");
        write("  ");
        clickOn("Let's go!");
        verifyThat("cannot have empty / whitespace only username",
                NodeMatchers.isNotNull());
    }

    @Test
    public void testFrontWhitespace() {
        clickOn("Start");
        write("  my tests");
        clickOn("Let's go!");
        verifyThat("my tests", NodeMatchers.isNotNull());
    }

    @Test
    public void testBackWhitespace() {
        clickOn("Start");
        write("my tests  ");
        clickOn("Let's go!");
        verifyThat("my tests", NodeMatchers.isNotNull());
    }

    @Test
    public void testSurroundWhitespace() {
        clickOn("Start");
        write("  my tests  ");
        clickOn("Let's go!");
        verifyThat("my tests", NodeMatchers.isNotNull());
    }
  
    @Test
    public void testDifficulty1() {
        clickOn("Start");
        write("username");
        clickOn("Let's go!");
        verifyThat("$500", NodeMatchers.isNotNull());
    }

    @Test
    public void testDifficulty2() {
        clickOn("Start");
        write("username");
        clickOn("Tipsy");
        clickOn("Let's go!");
        verifyThat("$250", NodeMatchers.isNotNull());
    }
    @Test
    public void testDifficulty3() {
        clickOn("Start");
        write("username");
        clickOn("Drunk");
        clickOn("Let's go!");
        verifyThat("$166", NodeMatchers.isNotNull());
    }
    @Test
    public void selectBrokenBottle() {
        clickOn("Start");
        write("Scoops");
        clickOn("Broken Bottle");
        clickOn("Let's go!");
        verifyThat("Broken Bottle", NodeMatchers.isNotNull());
    }

    @Test
    public void selectGun() {
        clickOn("Start");
        write("Scoops");
        clickOn("Gun");
        clickOn("Let's go!");
        verifyThat("Bow", NodeMatchers.isNotNull());
    }

    @Test
    public void selectSword() {
        clickOn("Start");
        write("Scoops");
        clickOn("Let's go!");
        verifyThat("Sword", NodeMatchers.isNotNull());
    }

    @Test
    public void testNullUsername() {
        clickOn("Start");
        clickOn("Let's go!");
        verifyThat("Bar Crawler", NodeMatchers.isNotNull());
        verifyThat("cannot have empty / "
                + "whitespace only username", NodeMatchers.isNotNull());
    }

    @Test
    public void testValidUsername() {
        clickOn("Start");
        write("Bella");
        clickOn("Let's go!");
        verifyThat("Go Back", NodeMatchers.isNotNull());
        verifyThat("Bella", NodeMatchers.isNotNull());
    }

    @Test
    public void testStart() {
        clickOn("Start");
        verifyThat("Let's go!", NodeMatchers.isNotNull());
    }

    @Test
    public void testLetsGo() {
        clickOn("Start");
        write("ethan");
        clickOn("Let's go!");
        verifyThat("Go Back", NodeMatchers.isNotNull());
    }

    @Test
    public void testGoBack() {
        clickOn("Start");
        write("ethan");
        clickOn("Let's go!");
        clickOn("Go Back");
        verifyThat("Let's go!", NodeMatchers.isNotNull());
    }
}
