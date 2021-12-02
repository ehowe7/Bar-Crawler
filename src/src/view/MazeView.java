package src.view;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import src.model.*;

import java.util.ArrayList;
import java.util.Random;

public class MazeView {
    private ArrayList<Integer> removal = new ArrayList<Integer>();
    private RoomView current;
    private RoomView[][] maze;
    private int width;
    private int height;
    private int rows;
    private int cols;

    private int row;
    private int col;

    private GameModel gameModel;

    /**
     * Construct a visual Maze of RoomViews.
      * @param width width of a room.
     * @param height height of a room.
     * @param rows number of rows in a maze.
     * @param cols number of cols in a maze.
     * @param gameModel the gamemodel with game data.
     * @param playerView the visual player data.
     * @param inventoryView the visual inventory object.
     */
    public MazeView(int width,
                    int height,
                    int rows,
                    int cols,
                    GameModel gameModel,
                    PlayerView playerView, InventoryView inventoryView) {

        this.width = width;
        this.height = height;
        this.rows = rows;
        this.cols = cols;
        this.gameModel = gameModel;

        int maxEnemies = 3;
        int minEnemies = 1;
        maze = new RoomView[rows][cols];
        Random rng = new Random();
        int randNum = rng.nextInt(maxEnemies - minEnemies) + minEnemies;
        System.out.println("enemies: " + randNum);
        Pane layer = new Pane();
        EnemySpriteModel esm = new EnemySpriteModel();
        ArrayList<MonsterView> monsters =
                MonsterView.generateMonsterViews(
                        rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                        layer);

        ArrayList<MonsterView> noMonsters = new ArrayList<>();
        BackgroundModel bgModel = new BackgroundModel(2);
        // corners
        Image im = bgModel.getTopLeftBackground();
        RoomView rm = new RoomView(
                width, height, gameModel, im, playerView, noMonsters, inventoryView);
        maze[0][0] = rm;

        monsters = MonsterView.generateMonsterViews(
                rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                layer);

        //FINAL ROOM MONSTER WEE WOO
        MonsterView finalBoss = new MonsterView(layer,
                new Image("file:assets/FinalBoss.gif"),
                new Image("file:assets/FinalBoss.gif"),
                50, 50, true);

        ArrayList<MonsterView> monsters2 = new ArrayList<>();
        monsters2.add(finalBoss);
        im = bgModel.getTopRightBackground();
        rm = new RoomView(width, height, gameModel, im, playerView, monsters2, inventoryView);
        maze[0][cols - 1] = rm;

        monsters = MonsterView.generateMonsterViews(
                rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                layer);

        im = bgModel.getBottomRightBackground();
        rm = new RoomView(width, height, gameModel, im, playerView, noMonsters, inventoryView);
        maze[rows - 1][cols - 1] = rm;

        monsters = MonsterView.generateMonsterViews(
                rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                layer);

        im = bgModel.getBottomLeftBackground();
        rm = new RoomView(width, height, gameModel, im, playerView, noMonsters, inventoryView);
        maze[rows - 1][0] = rm;


        //generate left/right side
        for (int c = 1; c < cols - 1; c++) {
            monsters = MonsterView.generateMonsterViews(
                    rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                    layer);
            Image leftImage = bgModel.getTopBackgrounds().get(c - 1);
            RoomView leftRoom = new RoomView(width,
                    height, gameModel, leftImage, playerView, monsters, inventoryView);
            maze[0][c] = leftRoom;

            monsters = MonsterView.generateMonsterViews(
                    rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                    layer);
            Image rightImage = bgModel.getBottomBackgrounds().get(c - 1);
            RoomView rightRoom = new RoomView(width,
                    height, gameModel, rightImage, playerView, monsters, inventoryView);
            maze[rows - 1][c] = rightRoom;

        }

        //generate top/bottom
        for (int r = 1; r < rows - 1; r++) {
            monsters = MonsterView.generateMonsterViews(
                    rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                    layer);
            Image topImage = bgModel.getLeftSideBackgrounds().get(r - 1);
            RoomView topRoom = new RoomView(width,
                    height, gameModel, topImage, playerView, monsters, inventoryView);
            maze[r][0] = topRoom;

            monsters = MonsterView.generateMonsterViews(
                    rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                    layer);
            Image bottomImage = bgModel.getRightSideBackgrounds().get(r - 1);
            RoomView bottomRoom = new RoomView(width,
                    height, gameModel, bottomImage, playerView, monsters, inventoryView);
            maze[r][cols - 1] = bottomRoom;

        }

        //generate middle images
        int backgroundIndex = 0;
        for (int r = 1; r <= rows - 2; r++) {
            for (int c = 1; c <= cols - 2; c++) {
                monsters = MonsterView.generateMonsterViews(
                        rng.nextInt(maxEnemies - minEnemies) + minEnemies,
                        layer);
                Image bg = bgModel.getMiddleBackgrounds().get(backgroundIndex);
                RoomView room = new RoomView(width,
                        height, gameModel, bg, playerView, monsters, inventoryView);
                maze[r][c] = room;
                backgroundIndex++;
            }
        }
        this.row = 3;
        this.col = 1;
        current = maze[this.row][this.col];
        current.setVisited(true);
    }

    public void damageMonster(MonsterView currentMonster, int index, InventoryView inventoryView) {
        removal.clear();
        //MonsterView currentMonster = (getCurrent().getMonsterViews()).get(i);
        double damageMultiplier = getCurrent().getPlayerView().getModel().getDamageMultiplier();
        switch (getCurrent().getPlayerView().getWeapon()) {
        case GUN:
            currentMonster.currentModel().setMonsterHP(
                    currentMonster.currentModel().getMonsterHP() - .3 * damageMultiplier);
            System.out.println("you hit me for 3 health");
            break;
        case SWORD:
            currentMonster.currentModel().setMonsterHP(
                    currentMonster.currentModel().getMonsterHP() - .2 * damageMultiplier);
            System.out.println("you hit me for 2 health");
            break;
        case BOTTLE:
            currentMonster.currentModel().setMonsterHP(
                    currentMonster.currentModel().getMonsterHP() - .1 * damageMultiplier);
            System.out.println("you hit me for 1 health");
            break;
        case KNIFE:
            currentMonster.currentModel().setMonsterHP(
                    currentMonster.currentModel().getMonsterHP() - .7 * damageMultiplier);
            System.out.println("you hit me for 7 health");
            break;
        default:
            break;
        }
        if (currentMonster.currentModel().getMonsterHP() <= 0) {
            //die method
            //currentMonster.setSprite();
            Image ghosty = new Image("file:assets/deadenemy/transparentghost.png");
            currentMonster.setSprite(ghosty);
            currentMonster.updateUI();
            removal.add(index);
            //getCurrent().getMonsterViews().remove(currentMonster);  //remove from the monsterview
            System.out.println("DEAD X_X");
            inventoryView.addToInventory(currentMonster.getKnifeView());
            //random potion rate
            Random random = new Random();
            int i = random.nextInt(3);
            switch (i) {
            case (0) :
                //health
                HealthPotionView healthPotion = new HealthPotionView(
                        new Image("file:assets/inventory_items/health.png"));
                inventoryView.addToInventory(healthPotion);
                break;
            case (1) :
                //speed
                SpeedPotionView speedPotion = new SpeedPotionView(
                        new Image("file:assets/inventory_items/speed.png"));
                inventoryView.addToInventory(speedPotion);
                break;
            case (2) :
                //attack
                AttackPotionView attackPotionView = new AttackPotionView(
                        new Image("file:assets/inventory_items/attack.png"));
                inventoryView.addToInventory(attackPotionView);
                break;
            default:
                break;
            }
        }
        for (int i : removal) {
            getCurrent().getMonsterViews().remove(i);
        }
    }

    public RoomView getCurrent() {
        return this.current;
    }

    public boolean canMoveRight() {
        return !current.isLocked() && col + 1 < cols;
    }
    public boolean canMoveLeft() {
        return !current.isLocked() && col - 1 >= 0;
    }
    public boolean canMoveUp() {
        return !current.isLocked() && row - 1 >= 0;
    }
    public boolean canMoveDown() {
        return !current.isLocked() && row + 1 < rows;
    }
    public RoomView moveRight() {
        // System.out.println("(" + row + "," + col + ")");
        if (col + 1 < cols) {
            col++;
            current = maze[row][col];
        }
        // System.out.println("(" + row + "," + col + ")");
        return current;
    }

    public RoomView moveLeft() {
        // System.out.println("(" + row + "," + col + ")");
        if (col - 1 >= 0) {
            col--;
            current = maze[row][col];
        }
        // System.out.println("(" + row + "," + col + ")");
        return current;
    }

    public RoomView moveUp() {
        // System.out.println("(" + row + "," + col + ")");
        if (row - 1 >= 0) {
            row--;
            current = maze[row][col];
        }
        // System.out.println("(" + row + "," + col + ")");
        return current;
    }

    public RoomView moveDown() {
        // System.out.println("(" + row + "," + col + ")");
        if (row + 1 < rows) {
            row++;
            current = maze[row][col];
        }
        // System.out.println("(" + row + "," + col + ")");
        return current;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public RoomView getRight() {
        return maze[row][col + 1];
    }

    public RoomView getLeft() {
        return maze[row][col - 1];
    }
    public RoomView getUp() {
        return maze[row - 1][col];
    }
    public RoomView getDown() {
        return maze[row + 1][col];
    }

    public int getNumRows() {
        return rows;
    }

    public int getNumCols() {
        return cols;
    }
}
