import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Fairy extends Moving{

    private static final String FAIRY_KEY = "fairy";
    private static final int FAIRY_ANIMATION_PERIOD = 0;
    private static final int FAIRY_ACTION_PERIOD = 1;
    private static final int FAIRY_NUM_PROPERTIES = 2;
  ;


    public Fairy(String id, Point position, List<PImage> images,  double actionPeriod, double animationPeriod) {
        super(FAIRY_KEY, id, position, images, actionPeriod, animationPeriod);
    }

    // need resource count, though it always starts at 0


    public double getAnimationPeriod() {
        return animationPeriod;
    }

    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        Optional<Entity> fairyTarget = findNearest(world, this.position, new ArrayList<>(List.of(Stump.getStumpKey())));

        if (fairyTarget.isPresent()) {
            Point tgtPos = fairyTarget.get().position;

            if (this.moveTo(world, fairyTarget.get(), scheduler)) {

                Sapling sapling = new Sapling(Sapling.getSaplingKey() + "_" + fairyTarget.get().id, tgtPos, getImageList(imageStore, Sapling.getSaplingKey()), 0);

                sapling.addEntity(world);
                sapling.scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
    }


    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());

    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        AStarPathingStrategy pathingStrategy = new AStarPathingStrategy();

        Predicate<Point> canPassThrough = p -> world.withinBounds(p) && !world.isOccupied(p);
        BiPredicate<Point, Point> withinReach = super::adjacent;
        Function<Point, Stream<Point>> potentialNeighbors = p -> PathingStrategy.CARDINAL_NEIGHBORS.apply(p);

        List<Point> path = pathingStrategy.computePath(this.position, destPos, canPassThrough, withinReach, potentialNeighbors);
        if (path.isEmpty()){
            return this.position;
        } else{
            return path.get(0);
        }
    }

    public static void parseFairy(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == FAIRY_NUM_PROPERTIES) {
            Fairy fairy = new Fairy(id, pt, Entity.getImageList(imageStore, FAIRY_KEY), Double.parseDouble(properties[FAIRY_ACTION_PERIOD]), Double.parseDouble(properties[FAIRY_ANIMATION_PERIOD]));
            fairy.tryAddEntity(world);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", FAIRY_KEY, FAIRY_NUM_PROPERTIES));
        }
    }


    @Override
    public boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler) {
        if (adjacent(this.position, target.position)) {
            target.removeEntity(world, scheduler);
            return true;
        } else {
            Point nextPos = nextPosition(world, target.position);

            if (!this.position.equals(nextPos)) {
                super.moveEntity(scheduler, world, nextPos);
            }
            return false;
        }
    }
}
