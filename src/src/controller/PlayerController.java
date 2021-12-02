package src.controller;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import src.model.Direction;
import src.model.Weapon;
import src.view.AttackPotionView;
import src.view.HealthPotionView;
import src.view.InventoryView;
import src.view.ItemView;
import src.view.MazeView;
import src.view.MonsterView;
import src.view.PlayerView;
import src.view.RoomView;
import src.view.SpeedPotionView;

import java.util.ArrayList;

public class PlayerController {
    private PlayerView player;
    private MazeView maze;
    private Stage stage;
    private double dx = 1;
    private double dy = 1;
    private InventoryView inventoryView;
    public PlayerController(Stage stage,
                            PlayerView player,
                            MazeView maze,
                            InventoryView inventoryView) {
        this.maze = maze;
        this.stage = stage;
        this.player = player;
        this.inventoryView = inventoryView;
        double dx = 5;
        double dy = 5;
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                event.consume();
                switch (event.getCode()) {
                case W:
                    player.changeDirection(Direction.BACK);
                    player.setDx(0);
                    player.setDy(-player.getModel().getDy());
                    System.out.println("up");
                    break;
                case S:
                    player.changeDirection(Direction.FRONT);
                    player.setDx(0);
                    player.setDy(player.getModel().getDy());
                    System.out.println("down");
                    break;
                case A:
                    player.changeDirection(Direction.LEFT);
                    player.setDx(-player.getModel().getDx());
                    player.setDy(0);
                    System.out.println("left");
                    break;
                case D:
                    player.changeDirection(Direction.RIGHT);
                    player.setDx(player.getModel().getDx());
                    player.setDy(0);
                    System.out.println("right");
                    break;
                case C: //CHALLENGE ROOM ACCEPTANCE
                    int r = maze.getRow();
                    int c = maze.getCol();
                    int nr = maze.getNumRows();
                    int nc = maze.getNumCols();
                    if ((r == 0 && c == 0) || (r == nr - 1 && c == 0)
                        || (r == nr - 1 && c == nc - 1)) {
                        RoomView curr = maze.getCurrent();
                        if (!curr.isChallengeActivated()) {
                            Pane layer = new Pane();
                            curr.setMonsterViews(
                                    MonsterView.generateMonsterViews(10, curr.getMonsterLayer()));
                            curr.setChallengeActivated(true);
                            curr.lockDoors(); //NO ESCAPE FROM CHALLENGE ROOM
                        }
                    }
                    break;
                case SPACE:
                    player.getModel().incrementNumberAttacks();
                    player.attackSprite();
                    Direction direct = player.getDirection();
                    ArrayList<MonsterView> monsterList = (maze.getCurrent().getMonsterViews());
                    for (int i = 0; i < monsterList.size(); i++) {
                        MonsterView monster = monsterList.get(i);
                        switch (direct) {
                            case FRONT:
                                if (((player.getX() - monster.getX() < 30)
                                        && (player.getY() - monster.getY() < 60))
                                        && ((monster.getX() - player.getX() < 30)
                                        && (monster.getY() - player.getY() < 60))) {
                                    maze.damageMonster(monster, i, inventoryView);
                                    System.out.println(player.getDx());
                                    System.out.println(monster.getX());
                                }
                                break;
                            case BACK:
                                if (((player.getX() - monster.getX() < 30)
                                        && (player.getY() - monster.getY() < 60))
                                        && ((monster.getX() - player.getX() < 30)
                                        && (monster.getY() - player.getY() < 60))) {
                                    maze.damageMonster(monster, i, inventoryView);
                                }
                                break;
                            case RIGHT:
                                if (((player.getX() - monster.getX() < 60)
                                        && (player.getY() - monster.getY() < 30))
                                        && ((monster.getX() - player.getX() < 60)
                                        && (monster.getY() - player.getY() < 30))) {
                                    maze.damageMonster(monster, i, inventoryView);
                                }
                                break;
                            case LEFT:
                                if (((player.getX() - monster.getX() < 60)
                                        && (player.getY() - monster.getY() < 31))
                                        && ((monster.getX() - player.getX() < 61)
                                        && (monster.getY() - player.getY() < 30))) {
                                    maze.damageMonster(monster, i, inventoryView);
                                }
                                break;
                            default:
                                break;
                            }
                        }
                        System.out.println("attack");
                        break;
                    case DIGIT1:
                        handleInventory(0);
                        break;
                    case DIGIT2:
                        handleInventory(1);
                        break;
                    case DIGIT3:
                        handleInventory(2);
                        break;
                    case DIGIT4:
                        handleInventory(3);
                        break;
                    default:
                        break;
                }
            }
        });
        stage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                player.setDy(0);
                player.setDx(0);
                player.endStep();
            }
        });



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                player.move();
                player.updateUI();
            }
        };
        timer.start();

        //AnimationTimer timerEnemy = new AnimationTimer() {
        //@Override
        //public void handle(long now) {
        //ArrayList<MonsterView> monsterList = (maze.getCurrent().getMonsterViews());
        //for (MonsterView monster : monsterList) {
        //monster.currentModel().attack(player, monster);
        //}
        //}
        //};
        //timer.start();
    }
    private void handleInventory(int index) {
        ItemView itemView = inventoryView.getItem(index);
        inventoryView.printItems();
        if (itemView != null) {
            player.getModel().incrementNumberPotionsUsed();
            if (itemView.getType().equals("knife")) {
                System.out.println("KNIFE");
                if (player.getWeapon() != Weapon.KNIFE) {
                    System.out.println("WEAPON IS NOW KNIFE");
                    player.setWeapon(Weapon.KNIFE);
                } else {
                    System.out.println("WEAPON IS NOT KNIFE");
                    player.setWeapon(player.getOriginalWeapon());
                }
            } else if (itemView.getType().equals("health")) {
                System.out.println("HEALTH");
                ((HealthPotionView) itemView).useItem(player);
            } else if (itemView.getType().equals("speed")) {
                System.out.println("SPEED");
                ((SpeedPotionView) itemView).useItem(player);
            } else if (itemView.getType().equals("attack")) {
                System.out.println("ATTACK");
                ((AttackPotionView) itemView).useItem(player);
            }

            if (!itemView.getType().equals("knife")) {
                inventoryView.removeFromInventory(index);
            }
        }
    }
}
