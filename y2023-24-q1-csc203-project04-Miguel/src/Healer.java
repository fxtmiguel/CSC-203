import processing.core.PImage;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Healer extends Living {
    public static final String HEAlER_KEY ="healer" ;


    public static final int HEALER_ANIMATION_PERIOD = 1;
    public static final int HEALER_ACTION_PERIOD = 1;
    public static final int HEALER_NUM_PROPERTIES = 2;
    public Healer(String id, Point position, double actionPeriod, double animationPeriod, List<PImage> images) {
        super(id, position, images, actionPeriod, animationPeriod, 0, 0);
    }


    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getAnimationPeriod());
        scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());
    }

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        Optional<Entity> target = world.findNearest(this.getPosition(), new ArrayList<>(List.of(Grave.class)));

        if (target.isPresent()) {
            Point tgPosition = target.get().getPosition();

            if (this.moveTo(world, target.get(), scheduler)) {
                 Dude dude = new Dude(Dude.DUDE_KEY + "_" + target.get().getId(), tgPosition, 0.787, 0.180, 5, imageStore.getImageList(Dude.DUDE_KEY), false);

                world.addEntity(dude);
                dude.scheduleActions(scheduler, world, imageStore);

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
