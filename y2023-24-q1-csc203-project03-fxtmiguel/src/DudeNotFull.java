import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DudeNotFull extends Dude {
    public DudeNotFull(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }


    // need resource count, though it always starts at 0
    public double getAnimationPeriod() {

        return this.animationPeriod;
    }


    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> target = findNearest(world, this.position, new ArrayList<>(Arrays.asList(Tree.getTreeKey(), Sapling.getSaplingKey())));

        if (target.isEmpty() || !this.moveTo(world, target.get(), scheduler) || !this.transformNotFull(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.actionPeriod);
        }
    }

    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this,new Activity( this, world, imageStore), getActionPeriod());
        scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());
    }

    public boolean transformNotFull(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (getResourceCount() >= getResourceLimit()) {
            DudeFull dude = new DudeFull(this.id, this.position,this.images, getResourceLimit(), getResourceCount(), this.actionPeriod, this.animationPeriod);

            removeEntity(world, scheduler);
            scheduler.unscheduleAllEvents(this);

            dude.addEntity(world);
            dude.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (adjacent(this.position, target.position)) {
            this.setResourceCount(this.getResourceCount()+1);
            ((Health) target).setHealth(((Health) target).getHealth()-1);
            return true;
        } else {
            Point nextPos = super.nextPosition(world, target.position);

            if (!this.position.equals(nextPos)) {
                moveEntity( scheduler, world ,nextPos);
            }
            return false;
        }
    }



}