package src.view;

import javafx.scene.SubScene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import src.model.Direction;
import src.model.PlayerModel;
import src.model.Walking;
import src.model.Weapon;

public class PlayerView {
    private Image sprite;
    private ImageView imageView;
    private SubScene subScene;

    private Pane layer;

    private double x;
    private double y;

    private double dx;
    private double dy;

    private double width;
    private double height;

    private final double playerWidth = 76 / 2;
    private final double playerHeight = 156 / 2;
    private final double topBoarder = 76 / 2;
    private final double leftBoarder = 76 / 2;

    private Weapon weapon;
    private Weapon originalWeapon;
    private PlayerModel playerModel;
    private Direction direction;
    private String pathName1 = " ";
    private String pathName2 = " ";
    private String pathName3 = " ";
    private String fileName1 = " ";
    private String fileName2 = " ";
    private String fileName3 = " ";
    private String fileName4 = "_hit";
    private String fileName;

    private Walking[] walking = new Walking[4];
    private Walking step;
    private int walkingIdx;

    private ProgressBar progressBar;
    /**
     * Player visual data constructor
     * @param layer the layer the player belongs to
     * @param x the x location to start
     * @param y the y location to start
     * @param w the width of the game
     * @param h the height of the game
     * @param weaponPick the weapon the player selected
     * @param direction the direction the player is currently facing
     */
    public PlayerView(Pane layer, double x, double y, double w,
                      double h, Weapon weaponPick, Direction direction) {
        this.playerModel = new PlayerModel(1, weaponPick, 5, 5);

        this.weapon = weaponPick;
        this.originalWeapon = this.weapon;

        this.direction = direction;
        walking[0] = Walking.CENTER;
        walking[1] = Walking.LEFT;
        walking[2] = Walking.CENTER;
        walking[3] = Walking.RIGHT;
        walkingIdx = 0;
        step = walking[walkingIdx];

        switch (weaponPick) {
        case GUN:
            pathName1 = "file:assets/alex_sprites/gun/";
            fileName1 = "gun_";
            break;
        case SWORD:
            pathName1 = "file:assets/alex_sprites/sword/";
            fileName1 = "sword_";
            break;
        case BOTTLE:
            pathName1 = "file:assets/alex_sprites/broken_bottle/";
            fileName1 = "bottle_";
            break;
        default:
            break;
        }

        switch (direction) {
        case FRONT:
            pathName2 = "facing_front/";
            fileName2 = "front_";
            break;
        case BACK:
            pathName2 = "facing_back/";
            fileName2 = "back_";
            break;
        case RIGHT:
            pathName2 = "facing_right/";
            fileName2 = "right_";
            break;
        case LEFT:
            pathName2 = "facing_left/";
            fileName2 = "left_";
            break;
        default:
            break;
        }

        pathName3 = "standing/";
        fileName3 = "standing";
        fileName = pathName1 + pathName2 + pathName3 + fileName1 + fileName2 + fileName3 + ".png";
        System.out.println(fileName);

        this.layer = layer;

        this.x = x;
        this.y = y;

        this.sprite = new Image(fileName);
        this.imageView = new ImageView(sprite);

        VBox progBox = new VBox();
        this.progressBar = new ProgressBar(
                this.playerModel.getPlayerHP() / this.playerModel.getMaxHp());
        progBox.getChildren().addAll(progressBar);
        this.subScene = new SubScene(progBox, 200, 100);

        this.imageView.relocate(x, y);
        subScene.relocate(imageView.getX(), imageView.getY() - 50);

        this.width = w;
        this.height = h;

        addToLayer();
    }

    /**
     * Add the image to a layer.
     */
    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
        this.layer.getChildren().add(this.subScene.getRoot());
    }

    public void attackSprite() {
        fileName = pathName1 + pathName2 + pathName3
                + fileName1 + fileName2 + fileName3 + fileName4 + ".png";

        System.out.println(fileName);

        this.sprite = new Image(fileName);
        this.imageView.setImage(sprite);
        if (weapon == Weapon.GUN) {
            shoot();
        }
    }

    public void shoot() {
        System.out.println("shoot");
        Image bullet = new Image("file:assets/alex_sprites/gun/bullet/bullet.png");
        ImageView bulletView = new ImageView(bullet);
        boolean hit = false;
        double gunX = x;
        double gunY = y;
        bulletView.relocate(gunX, gunY);
        double gunDX = 0;
        double gunDY = 0;
        switch (direction) {
        case FRONT:
            gunDY = -3;
            break;
        case BACK:
            gunDY = 3;
            break;
        case RIGHT:
            gunDX = 3;
            break;
        case LEFT:
            gunDX = -3;
            break;
        default:
            break;
        }
        for (int i = 0; i < 100; i++) {
            gunX += gunDX;
            gunY += gunDY;
            bulletView.relocate(gunDX, gunDY);
        }
    }

    public Pane getLayer() {
        return this.layer;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (0 + leftBoarder <= x  && x  < width - playerWidth - leftBoarder) {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (0 + topBoarder <= y  && y  < height - playerHeight - topBoarder) {
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

    public Direction getDirection() {
        return direction;
    }

    public Weapon getWeapon() {
        return (this.weapon);
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public PlayerModel getModel() {
        return (this.playerModel);
    }

    public double getCenterX() {
        return this.x + this.playerWidth / 2;
    }

    public double getCenterY() {
        return this.y + this.playerHeight / 2;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setSprite(Image im) {
        this.imageView.setImage(im);
    }

    public void changeDirection(Direction direction) {
        if (direction == this.direction) {
            this.direction = direction;
            walkingIdx = (walkingIdx + 1) % walking.length;
            step = walking[walkingIdx];
            switch (step) {
            case CENTER:
                pathName3 = "standing/";
                fileName3 = "standing";
                break;
            case LEFT:
                pathName3 = "walking/left_step/";
                fileName3 = "walking_leftstep";
                break;
            case RIGHT:
                pathName3 = "walking/right_step/";
                fileName3 = "walking_rightstep";
                break;
            default:
                break;
            }
        } else {
            this.direction = direction;
            step = walking[0];
            pathName3 = "standing/";
            fileName3 = "standing";
            switch (direction) {
            case FRONT:
                pathName2 = "facing_front/";
                fileName2 = "front_";
                break;
            case BACK:
                pathName2 = "facing_back/";
                fileName2 = "back_";
                break;
            case RIGHT:
                pathName2 = "facing_right/";
                fileName2 = "right_";
                break;
            case LEFT:
                pathName2 = "facing_left/";
                fileName2 = "left_";
                break;
            default:
                break;
            }
        }


        fileName = pathName1 + pathName2 + pathName3 + fileName1 + fileName2 + fileName3 + ".png";
        System.out.println(fileName);
        this.sprite = new Image(fileName);
        this.imageView.setImage(sprite);

    }

    public void endStep() {
        if (step != Walking.CENTER) {
            walkingIdx = (walkingIdx + 1) % walking.length;
            step = walking[walkingIdx];
            pathName3 = "standing/";
            fileName3 = "standing";
        }
        fileName = pathName1 + pathName2 + pathName3 + fileName1 + fileName2 + fileName3 + ".png";
        System.out.println(fileName);
        this.sprite = new Image(fileName);
        this.imageView.setImage(sprite);
    }


    public void move() {
        if (0 + leftBoarder <= x  + dx && x + dx < width - playerWidth - leftBoarder) {
            x += dx;
        }
        if (0 + topBoarder <= y + dy && y + dy < height - playerHeight - topBoarder) {
            y += dy;
        }
    }

    public void updateUI() {
        imageView.relocate(x, y);
        subScene.relocate(imageView.getX(), imageView.getY() - 100);
    }

    public String getFileName() {
        return (pathName1 + pathName2 + pathName3 + fileName1 + fileName2 + fileName3 + ".png");

    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public ProgressBar getProgressBar() {
        return this.progressBar;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setOriginalWeapon(Weapon weapon) {
        this.originalWeapon = weapon;
    }

    public Weapon getOriginalWeapon() {
        return this.originalWeapon;
    }
}

