package src.model;
import src.view.MonsterView;
import src.view.PlayerView;

public class MonsterModel0 {

    private double hp;
    private double damage;
    private double speed;


    /**
     * Monster stat and attribute data constructor
     * @param hp the starting health points of the monster
     * @param damage the monster's damage output
     * @param speed the monster's speed
     */
    public MonsterModel0(double hp, double damage, double speed) {
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
    }

    public void attack(PlayerView player, MonsterView monster) {
        player.getModel().setPlayerHP(player.getModel().getPlayerHP()
                - monster.currentModel().getMonsterDamage());  //reduce HP
        System.out.println("reduced your health");
        if (player.getModel().getPlayerHP() <= 0) {
            System.out.println("You died D:");
        } else {
            player.getProgressBar()
                    .setProgress(player.getModel().getPlayerHP() / player.getModel().getMaxHp());
        }
    }


    /**
     * Getter for the monster's HP
     * @return the double value for the monster's HP
     */
    public double getMonsterHP() {
        return this.hp;
    }

    /**
     * Setter for the monster's HP
     * @param newHP the monster's new HP value
     */
    public void setMonsterHP(double newHP) {
        this.hp = newHP;
    }

    /**
     * Getter for monster's damage
     * @return damage of monster
     */
    public double getMonsterDamage() {
        return damage;
    }

    /**
     * Setter for monster damage
     * @param damage new damage for monster
     */
    public void setMonsterDamage(double damage) {
        this.damage = damage;
    }

    /**
     * Getter for monster's speed
     * @return speed value of monster
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Setter for monster's speed
     * @param speed new speed of monster
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
