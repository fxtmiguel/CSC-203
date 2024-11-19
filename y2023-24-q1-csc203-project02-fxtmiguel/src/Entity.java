import java.util.*;

import jdk.internal.org.objectweb.asm.tree.InsnList;
import processing.core.PImage;

/**
 * An entity that exists in the world. See EntityKind for the
 * different kinds of entities that exist.
 */
public abstract class Entity {
    private String key;

    public String id;
    public Point position;
    public List<PImage> images;
    public int imageIndex;
    public double actionPeriod;
    public double animationPeriod;
    private static final int ENTITY_NUM_PROPERTIES = 4;
    private static final int PROPERTY_KEY = 0;
    private static final int PROPERTY_ID = 1;
    private static final int PROPERTY_COL = 2;
    private static final int PROPERTY_ROW = 3;
    public Entity(String key,String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod) {
        this.key = key;
        this.id = id;
        this.position = position;
        this.images = images;
        this.imageIndex = 0;
        this.actionPeriod = actionPeriod;
        this.animationPeriod = animationPeriod;

    }


    public String log() {
        return this.id.isEmpty() ? null :
                String.format("%s %d %d %d", this.id, this.position.x, this.position.y, this.imageIndex);
    }

    public static List<PImage> getImageList(ImageStore imagestore, String key) {
        return imagestore.getImages().getOrDefault(key, imagestore.defaultImages);
    }
    public Optional<Entity> findNearest(WorldModel world, Point pos, List<String> keys) {
        List<Entity> ofType = new LinkedList<>();
        for (String key : keys){
            for (Entity entity : world.entities) {
                if (entity.getKey().equals(key)) {
                    ofType.add(entity);
                }
            }
        }

        return nearestEntity(ofType, pos);
    }

    public String getKey() {
        return this.key;
    }

    public static Optional<Entity> nearestEntity(List<Entity> entities, Point pos) {
        if (entities.isEmpty()) {
            return Optional.empty();
        } else {
            Entity nearest = entities.get(0);
            int nearestDistance = Point.distanceSquared(nearest.position, pos);

            for (Entity other : entities) {
                int otherDistance = Point.distanceSquared(other.position, pos);

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }

    public void addEntity(WorldModel world) {
        if (world.withinBounds(this.position)) {
            world.setOccupancyCell(this.position, this);
            world.getEntities().add(this);
        }
    }

    public void moveEntity(EventScheduler scheduler,WorldModel world, Point pos) {
        Point oldPos = this.position;
        if (world.withinBounds(pos) && !pos.equals(oldPos)) {
            world.setOccupancyCell(oldPos, null);
            Optional<Entity> occupant = world.getOccupant(pos);
            occupant.ifPresent(target -> target.removeEntity(world, scheduler));
            world.setOccupancyCell(pos, this);
            this.position = pos;
        }
    }

    public void removeEntity(WorldModel world, EventScheduler scheduler) {
        scheduler.unscheduleAllEvents(this);
        removeEntityAt(world, this.position);
    }

    public void removeEntityAt(WorldModel world, Point pos) {
        if (world.withinBounds(pos) && world.getOccupancyCell(pos) != null) {
            Entity entity = world.getOccupancyCell(pos);

            /* This moves the entity just outside of the grid for
             * debugging purposes. */
            entity.position = new Point(-1,-1);
            world.entities.remove(entity);
            world.setOccupancyCell(pos, null);
        }
    }

    public double getAnimationPeriod() {
        if (this instanceof Tree) {
            return this.animationPeriod;
        } else {
            throw new UnsupportedOperationException(String.format("getAnimationPeriod not supported for %s", this.getClass()));
        }
    }
    public static void parseEntity(WorldModel world, String line, ImageStore imageStore) {
        String[] properties = line.split(" ", Entity.ENTITY_NUM_PROPERTIES + 1);
        if (properties.length >= ENTITY_NUM_PROPERTIES) {
            String key = properties[Entity.PROPERTY_KEY];
            String id = properties[Entity.PROPERTY_ID];
            Point pt = new Point(Integer.parseInt(properties[Entity.PROPERTY_COL]), Integer.parseInt(properties[Entity.PROPERTY_ROW]));

            properties = properties.length == Entity.ENTITY_NUM_PROPERTIES ?
                    new String[0] : properties[Entity.ENTITY_NUM_PROPERTIES].split(" ");

            switch (key) {
                case "obstacle" -> Obstacle.parseObstacle(world,properties, pt, id, imageStore);
                case "dude"-> Dude.parseDude(world, properties, pt, id, imageStore);
                case "fairy" -> Fairy.parseFairy(world, properties, pt, id, imageStore);
                case "house" -> House.parseHouse(world, properties, pt, id, imageStore);
                case "tree" -> Tree.parseTree(world, properties, pt, id, imageStore);
                case "sapling" -> Sapling.parseSapling(world, properties, pt, id, imageStore);
                case "stump" -> Stump.parseStump(world, properties, pt, id, imageStore);
                default -> throw new IllegalArgumentException("Entity key is unknown");
            }
        }else{
            throw new IllegalArgumentException("Entity must be formatted as [key] [id] [x] [y] ...");
        }
    }

    public void tryAddEntity(WorldModel world) {
        if (world.isOccupied(this.position)) {
            // arguably the wrong type of exception, but we are not
            // defining our own exceptions yet
            throw new IllegalArgumentException("position occupied");
        }

        this.addEntity(world);
    }
    public double getActionPeriod(){
        return this.actionPeriod;
    }

    public abstract void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    public abstract void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore);

    public abstract boolean transform(WorldModel world, EventScheduler scheduler, ImageStore imageStore);


    public abstract Point nextPosition(WorldModel world, Point destPos);



    public String getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }


}