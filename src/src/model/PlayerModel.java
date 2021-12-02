package src.model;

public class PlayerModel {

    private double hp;
    private Weapon weapon;
    private double maxHp;

    private double dx;
    private double dy;
    private double originalDx;
    private double originalDy;
    private double damageMultiplier;

    private int numberAttacks;
    private int numberPotionsUsed;
    /**
     * Player stat and attribute data constructor
     * @param hp the starting health points of the player
     * @param weapon the player's chosen weapon
     * @param dx x velocity
     * @param dy y velocity
     */
    public PlayerModel(double hp, Weapon weapon, double dx, double dy) {
        this.hp = hp;
        this.maxHp = hp;
        this.weapon = weapon;
        this.dx = dx;
        this.dy = dy;
        this.originalDx = dx;
        this.originalDy = dy;
        this.damageMultiplier = 1;
    }

    /**
     * Getter for the player's HP
     * @return the double value for the player's HP
     */
    public double getPlayerHP() {
        return this.hp;
    }

    /**
     * Setter for the player's HP
     * @param newHP the player's new HP value
     */
    public void setPlayerHP(double newHP) {
        this.hp = newHP;
    }

    public double getMaxHp() {
        return this.maxHp;
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

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getOriginalDx() {
        return originalDx;
    }

    public void setOriginalDx(double originalDx) {
        this.originalDx = originalDx;
    }

    public double getOriginalDy() {
        return originalDy;
    }

    public void setOriginalDy(double originalDy) {
        this.originalDy = originalDy;
    }

    public void setDamageMultiplier(double damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }
    public double getDamageMultiplier() {
        return this.damageMultiplier;
    }

    public int getNumberAttacks() {
        return numberAttacks;
    }

    public int getNumberPotionsUsed() {
        return numberPotionsUsed;
    }

    public void incrementNumberAttacks() {
        numberAttacks++;
    }

    public void incrementNumberPotionsUsed() {
        numberPotionsUsed++;
    }
}

