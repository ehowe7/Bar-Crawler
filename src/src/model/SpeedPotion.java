package src.model;

public class SpeedPotion {
    private double mult;
    private int duration;

    /**
     * Constructor for a speed potion.
     * @param mult the speed multiplier.
     * @param duration the duration of the speed potion.
     */
    public SpeedPotion(double mult, int duration) {
        this.mult = mult;
        this.duration = duration;
    }

    /**
     * Getter for the speed multiplier.
     * @return the speed multiplier.
     */
    public double getMult() {
        return this.mult;
    }

    /**
     * Getter for the duration of the speed potion.
     * @return the duration of the speed potion.
     */
    public int getDuration() {
        return this.duration;
    }
}
