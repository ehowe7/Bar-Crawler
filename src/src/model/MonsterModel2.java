package src.model;

public class MonsterModel2 extends MonsterModel {

    /**
     * Stat and attribute constructor for Monster0, uses parent constructor
     * @param hp the starting health points of the monster
     * @param damage the monster's damage output
     * @param speed the monster's speed
     */
    public MonsterModel2(double hp, double damage, double speed) {
        super(hp, damage, speed);
    }

    /**
     * Getter for the monster's HP
     * @return the double value for the monster's HP
     */
    public double getMonsterHP() {
        return super.getMonsterHP();
    }

    /**
     * Setter for the monster's HP
     * @param newHP the monster's new HP value
     */
    public void setMonsterHP(double newHP) {
        super.setMonsterHP(newHP);
    }

    /**
     * Getter for monster's damage
     * @return damage of monster
     */
    public double getMonsterDamage() {
        return super.getMonsterDamage();
    }

    /**
     * Setter for monster damage
     * @param damage new damage for monster
     */
    public void setMonsterDamage(double damage) {
        super.setMonsterDamage(damage);
    }

    /**
     * Getter for monster's speed
     * @return speed value of monster
     */
    public double getSpeed() {
        return super.getSpeed();
    }

    /**
     * Setter for monster's speed
     * @param speed new speed of monster
     */
    public void setSpeed(double speed) {
        super.setSpeed(speed);
    }
}
