import processing.core.PImage;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public abstract class Dude extends Moving {
    private static final String DUDE_KEY = "dude";
    private static final int DUDE_ACTION_PERIOD = 0;
    private static final int DUDE_ANIMATION_PERIOD = 1;
    private static final int DUDE_LIMIT = 2;
    private static final int DUDE_NUM_PROPERTIES = 3;

    private int resourceLimit;
    private int resourceCount;


    public Dude(String id, Point position, List<PImage> images, int resourceLimit, int resourceCount, double actionPeriod, double animationPeriod) {
        super(DUDE_KEY, id, position, images, actionPeriod, animationPeriod);
        this.resourceLimit = resourceLimit;
        this.resourceCount = resourceCount;
    }

    public static void parseDude(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == DUDE_NUM_PROPERTIES) {
            Entity entity = new DudeNotFull(id, pt, Entity.getImageList(imageStore, DUDE_KEY), Integer.parseInt(properties[DUDE_LIMIT]), 0, Double.parseDouble(properties[DUDE_ACTION_PERIOD]), Double.parseDouble(properties[DUDE_ANIMATION_PERIOD]));
            entity.tryAddEntity(world);
        } else {
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", DUDE_KEY, DUDE_NUM_PROPERTIES));
        }
    }
    public int getResourceLimit() {
        return resourceLimit;
    }


    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    public abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);


    public abstract boolean moveTo(WorldModel world, Entity target, EventScheduler scheduler);

    @Override
    public double getActionPeriod() {
        return this.actionPeriod;
    }


    @Override
    public double getAnimationPeriod() {
        return this.animationPeriod;
    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        AStarPathingStrategy pathingStrategy = new AStarPathingStrategy();

        Predicate<Point> canPassThrough = p -> (world.withinBounds(p)) && ( (!world.isOccupied(p) || world.getOccupancyCell(p) instanceof Stump));
        BiPredicate<Point, Point> withinReach = super::adjacent;
        Function<Point, Stream<Point>> potentialNeighbors = p -> PathingStrategy.CARDINAL_NEIGHBORS.apply(p);
        List<Point> path = pathingStrategy.computePath(this.position, destPos, canPassThrough, withinReach, potentialNeighbors);

        if (path.isEmpty()){
            return this.position;

        }
        return path.get(0);
    }


}