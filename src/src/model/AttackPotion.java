package src.model;

public class AttackPotion {
    private double mult;
    private int duration;

    /**
     * Constructor for AttackPotion.
     * @param mult the damage multiplier
     * @param duration the duration of the effect
     */
    public AttackPotion(double mult, int duration) {
        this.mult = mult;
        this.duration = duration;
    }

    /**
     * Getter for mult.
     * @return the damage multiplier of the potion
     */
    public double getMult() {
        return this.mult;
    }

    /**
     * Getter for duration.
     * @return the duration of the attack potion.
     */
    public int getDuration() {
        return this.duration;
    }
}
