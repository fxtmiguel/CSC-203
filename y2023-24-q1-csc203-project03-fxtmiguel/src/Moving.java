import processing.core.PImage;

import java.util.List;
public abstract class Moving extends Entity{

    public Moving(String key, String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        super(key, id, position, images, actionPeriod, animationPeriod);
    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

    }


    @Override
    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        return false;
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        return null;
    }
    public abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);
}
