import processing.core.PImage;
import java.util.List;

public abstract class Health extends Entity{

    private int health;
    private final int healthLimit;

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int i) {
        this.health = i;
    }
    public int getHealthLimit() {
        return healthLimit;
    }

    public Health(String key, String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(key, id, position, images, actionPeriod, animationPeriod);
        this.health = health;
        this.healthLimit = healthLimit;
    }

    @Override
    public double getActionPeriod() {
        return super.actionPeriod;
    }
    @Override
    public double getAnimationPeriod() {
        return super.animationPeriod;
    }

    @Override
    public abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore){
        if (this.getKey().equals(Tree.getTreeKey())) {
            return ((Tree) this).transformTree(world, scheduler, imageStore);
        } else if (this.getKey().equals(Sapling.getSaplingKey())) {
            return ((Sapling) this).transformSapling(world, scheduler, imageStore);
        } else {
            throw new UnsupportedOperationException(String.format("transformPlant not supported for %s", this));
        }
    }


}
