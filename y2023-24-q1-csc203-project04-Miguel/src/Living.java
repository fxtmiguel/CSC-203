import processing.core.PImage;
import java.util.List;

public abstract class Living extends Moving {
    private double actionPeriod;
    private int health;
    private int healthLimit;

    // Check Order
    public Living (String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(id, position, images, animationPeriod);
        this.actionPeriod = actionPeriod;
        this.health = health;
        this.healthLimit = healthLimit;
    }

    public int getHealth() { return health; }

    public void setHealth(int health) {
        this.health = health;
    }

    public double getActionPeriod() {
        return actionPeriod;
    }

    public int getHealthLimit() {
        return healthLimit;
    }
}