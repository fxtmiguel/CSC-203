import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeFull extends Dude {
    public DudeFull(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }


    // don't technically need resource count ... full

    /**
     * Helper method for testing. Preserve this functionality while refactoring.
     */

    public double getAnimationPeriod() {

        return this.animationPeriod;
    }


    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fullTarget = findNearest(world, this.position, new ArrayList<>(List.of(House.getHouseKey())));

        if (fullTarget.isPresent() && this.moveTo(world, fullTarget.get(), scheduler)) {
            this.transformFull(world, scheduler, imageStore);
        } else {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.actionPeriod);
        }
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), getActionPeriod());
        scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());
    }

    public boolean transformFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (getResourceCount() <= getResourceLimit()) {
            DudeNotFull dudeNotFull = new DudeNotFull(this.id, this.position, this.images, getResourceLimit(), getResourceCount(), getActionPeriod(), getAnimationPeriod());

            removeEntity(world, scheduler);
            scheduler.unscheduleAllEvents(this);

            dudeNotFull.addEntity(world);
            dudeNotFull.scheduleActions(scheduler, world, imageStore);

            return true;
        }
        return  false;
    }

    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (adjacent(this.position, target.position)) {
            return true;
        } else {
            Point nextPos = nextPosition(world, target.position);

            if (!this.position.equals(nextPos)) {
                this.moveEntity(scheduler, world, nextPos);
            }
            return false;
        }
    }


}

