import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Dude extends Living {
    public static final String DUDE_KEY = "dude";
    public static final int DUDE_ACTION_PERIOD = 0;
    public static final int DUDE_ANIMATION_PERIOD = 1;
    public static final int DUDE_LIMIT = 2;
    public static final int DUDE_NUM_PROPERTIES = 3;
    private boolean full;
    private int resourceLimit;
    private int resourceCount;

    public Dude(String id, Point position, double actionPeriod, double animationPeriod, int resourceLimit, List<PImage> images, boolean full) {
        super(id, position, images, actionPeriod, animationPeriod, 0, 0);
        this.resourceLimit = resourceLimit;
        this.full = full;
    }

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        if (!full) {
            Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(Arrays.asList(Tree.class, Sapling.class)));


            if (target.isEmpty() || !this.moveToNotFull(world, (Living) target.get(), scheduler) || !this.transform(world, scheduler, imageStore)) {
                scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
            }
        }

        // Full Dude
        else {
            Optional<Entity> fullTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(House.class)));

            if (fullTarget.isPresent() && this.moveToFull(world, fullTarget.get(), scheduler)) {
                this.transform(world, scheduler, imageStore);
            } else {
                scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
            }
        }

    }

    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());
    }

    public Point nextPosition(WorldModel world, Point destPos) {
        PathingStrategy pathingStrategy = new AStarPathingStrategy();

        // Dude can trample stump
        Predicate<Point> canPassThrough = p -> world.withinBounds(p) && (!world.isOccupied(p) || world.getOccupancyCell(p).getClass() == Stump.class);
        BiPredicate<Point, Point> withinReach = (p1, p2) -> Point.adjacent(p1, p2);
        Function<Point, Stream<Point>> potentialNeighbors = PathingStrategy.CARDINAL_NEIGHBORS;

        List<Point> paths = pathingStrategy.computePath(this.getPosition(), destPos, canPassThrough, withinReach,  potentialNeighbors);
        if (!paths.isEmpty()) {
            return paths.get(0);
        } else {
            return this.getPosition();
        }
    }

    public boolean moveToNotFull(WorldModel world, Living target, EventScheduler scheduler) {
        if (Point.adjacent(this.getPosition(), target.getPosition())) {
            this.resourceCount += 1;
            int currHealth = target.getHealth();
            target.setHealth(currHealth - 1 );
            return true;
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    public boolean moveToFull(WorldModel world, Entity target, EventScheduler scheduler) {
        if (Point.adjacent(this.getPosition(), target.getPosition())) {
            return true;
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }

    public boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (full) {
            Moving dude = new Dude(this.getId(), this.getPosition(), this.getActionPeriod(), this.getAnimationPeriod(), this.resourceLimit, this.getImages(), false);

            world.removeEntity(scheduler, this);

            world.addEntity(dude);
            dude.scheduleActions(scheduler, world, imageStore);

            return true;
        } else {
            if (this.resourceCount >= this.resourceLimit) {
                Moving dude = new Dude(this.getId(), this.getPosition(), this.getActionPeriod(), this.getAnimationPeriod(), this.resourceLimit, this.getImages(), true);

                world.removeEntity(scheduler, this);
                scheduler.unscheduleAllEvents(this);

                world.addEntity(dude);
                dude.scheduleActions(scheduler, world, imageStore);

                return true;
            }

            return false;
        }
    }
}