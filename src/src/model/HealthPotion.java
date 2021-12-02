package src.model;

public class HealthPotion {

    private double heals;

    /**
     * Constructor for a health potion
     * @param heals the amount the potion heals for
     */
    public HealthPotion(double heals) {
        this.heals = heals;
    }

    /**
     * Getter to access the heals of a health potion.
     * @return the heals of the potion.
     */
    public double getHeals() {
        return this.heals;
    }
}
