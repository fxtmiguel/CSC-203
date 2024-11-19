import processing.core.PImage;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Captain extends Living {
    public static final String CAPTAIN_KEY ="captain" ;


    public static final int CAPTAIN_ANIMATION_PERIOD = 1;
    public static final int CAPTAIN_ACTION_PERIOD = 1;
    public static final int CAPTAIN_NUM_PROPERTIES = 2;
    public Captain(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
        super(id, position, images, actionPeriod, animationPeriod, 0, 0);
    }


    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getAnimationPeriod());
        scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());
    }

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        Optional<Entity> lizardTarget = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Lizard.class)));

        if (lizardTarget.isPresent()) {
            Point position = lizardTarget.get().getPosition();

            if (this.moveTo(world, lizardTarget.get(), scheduler)) {

                Grave grave = new Grave(Grave.GRAVE_KEY + "_" + lizardTarget.get().getId(), position, imageStore.getImageList(Grave.GRAVE_KEY));

                world.addEntity(grave);
                grave.scheduleActions(scheduler, world, imageStore);

            }
        }

        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getAnimationPeriod());
    }



    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (Point.adjacent(this.getPosition(), target.getPosition())) {
            world.removeEntity(scheduler, target);
            return true;
        } else {
            Point nextPos = nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                world.moveEntity(scheduler, this, nextPos);
            }
            return false;
        }
    }


    public Point nextPosition(WorldModel world, Point destPos) {
        PathingStrategy pathingStrategy = new AStarPathingStrategy();

        Predicate<Point> canPassThrough = p -> world.withinBounds(p) && !world.isOccupied(p);
        BiPredicate<Point, Point> withinReach = (p1, p2) -> Point.adjacent(p1, p2);
        Function<Point, Stream<Point>> potentialNeighbors = PathingStrategy.CARDINAL_NEIGHBORS;

        List<Point> paths = pathingStrategy.computePath(this.getPosition(), destPos, canPassThrough, withinReach,  potentialNeighbors);
        if (paths.isEmpty()) {
            return this.getPosition();
        } else {
            return paths.get(0);
        }
    }
}
