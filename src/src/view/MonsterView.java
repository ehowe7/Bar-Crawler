package src.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import src.model.MonsterModel0;

import java.util.ArrayList;
import java.util.Random;

public class MonsterView {
    private Image standingSprite;
    private Image hitSprite;
    private ImageView imageView;
    private MonsterModel0 monstermodel;
    private Pane layer;
    private Image knife = new Image("file:assets/inventory_items/knife.png");
    private KnifeView knifeView = new KnifeView(knife);

    private double x;
    private double y;

    private double dx;
    private double dy;

    private static double width = 1920 / 2;
    private static double height = 1080 / 2;

    private static final double PLAYER_WIDTH = 76 / 2;
    private static final double PLAYER_HEIGHT = 156 / 2;
    private static final double TOP_BOARDER = 76 / 2;
    private static final double LEFT_BOARDER = 76 / 2;

    private boolean movable;

    /**
     * Player visual data constructor
     * @param layer the layer the player belongs to\
     * @param x the x location to start
     * @param y the y location to start
     * @param w the width of the game
     * @param h the height of the game
     */
    /**
     * Player visual data constructor
     * @param layer the layer the player belongs to
     * @param hitSprite hitting sprite Image
     * @param standingSprite default sprite Image
     * @param x the x location to start
     * @param y the y location to start
     * @param w the width of the game
     * @param h the height of the game
     */
    public MonsterView(Pane layer,
                       Image standingSprite,
                       Image hitSprite,
                       double x,
                       double y,
                       double w,
                       double h) {
        this.monstermodel = new MonsterModel0(.3, .05, 1);
        this.layer = layer;
        this.standingSprite = standingSprite;
        this.hitSprite = hitSprite;

        this.x = x;
        this.y = y;

        this.imageView = new ImageView(standingSprite);
        this.imageView.relocate(x, y);

        this.width = w;
        this.height = h;

        this.movable = false;
        addToLayer();
    }

    public MonsterView(Pane layer,
                       Image standingSprite,
                       Image hitSprite,
                       double x,
                       double y,
                       boolean isFinalBoss) {
        this(layer, standingSprite, hitSprite, x, y, 1980 / 2, 1080 / 2);
        if (isFinalBoss) {
            this.monstermodel = new MonsterModel0(1, .2, 1.5);

        }
    }

    /**
     * Add the image to a layer.
     */
    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public Pane getLayer() {
        return this.layer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (0 + LEFT_BOARDER <= x  && x  < width - PLAYER_WIDTH - LEFT_BOARDER) {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (0 + TOP_BOARDER <= y  && y  < height - PLAYER_HEIGHT - TOP_BOARDER) {
            this.y = y;
        }
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public MonsterModel0 currentModel() {
        return (monstermodel);
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getCenterX() {
        return this.x + this.PLAYER_WIDTH / 2;
    }

    public double getCenterY() {
        return this.y + this.PLAYER_HEIGHT / 2;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setSprite(Image im) {
        this.imageView.setImage(im);
    }

    public boolean isMovable() {
        return movable;
    }
    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public void move() {
        if (0 + LEFT_BOARDER <= x + dx && x + dx < width - PLAYER_WIDTH - LEFT_BOARDER) {
            x += dx;
        }
        if (0 + TOP_BOARDER <= y + dy && y + dy < height - PLAYER_HEIGHT - TOP_BOARDER) {
            y += dy;
        }
    }

    public void updateUI() {
        imageView.relocate(x, y);
    }

    public static ArrayList<MonsterView> generateMonsterViews(
            int num,
            Pane layer) {

        // can edit to add in dmg and weapon parameters, or a monster model object.
        MonsterView monster;
        ArrayList<MonsterView> monsters = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            int x = (int)
                    (rand.nextInt((int) (width - 2 * LEFT_BOARDER - PLAYER_WIDTH)) + LEFT_BOARDER);
            int y = (int)
                    (rand.nextInt((int) (height - 2 * TOP_BOARDER - PLAYER_HEIGHT)) + TOP_BOARDER);

            int sprite = rand.nextInt(3);
            System.out.println(sprite);
            switch (sprite) {
            case 0:
                monster = new MonsterView(layer,
                        new Image("file:assets/enemies/standing/drunkguy.png"),
                        new Image("file:assets/enemies/hit/drunkguy_hit.png"),
                        x, y, width, height);
                break;
            case 1:
                monster = new MonsterView(layer,
                        new Image("file:assets/enemies/standing/biker.png"),
                        new Image("file:assets/enemies/hit/biker_hit.png"),
                        x, y, width, height);
                break;
            case 2:
                System.out.println("bouncer");
                monster = new MonsterView(layer,
                        new Image("file:assets/enemies/standing/bouncer.png"),
                        new Image("file:assets/enemies/hit/bouncer_hit.png"),
                        x, y, width, height);
                break;
            default:
                monster = new MonsterView(layer,
                        new Image("file:assets/enemies/standing/drunkguy.png"),
                        new Image("file:assets/enemies/hit/drunkguy_hit.png"),
                        x, y, width, height);
                break;
            }
            monsters.add(monster);
        }

        return monsters;
    }

    public void hit() {
        imageView.setImage(hitSprite);
    }

    public void stopHit() {
        imageView.setImage(standingSprite);
    }

    public KnifeView getKnifeView() {
        return this.knifeView;
    }
}
