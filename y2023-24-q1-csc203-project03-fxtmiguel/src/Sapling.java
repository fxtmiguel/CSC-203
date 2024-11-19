import processing.core.PImage;

import java.util.*;

public class Sapling extends Health {
    public static final double SAPLING_ACTION_ANIMATION_PERIOD = 1.000; // have to be in sync since grows and gains health at same time
    public static final int SAPLING_HEALTH_LIMIT = 5;
    public static final String SAPLING_KEY = "sapling";
    public static final int SAPLING_HEALTH = 0;
    public static final int SAPLING_NUM_PROPERTIES = 1;

    public Sapling(String id, Point position, List<PImage> images, int health) {
        super(SAPLING_KEY, id, position, images, SAPLING_ACTION_ANIMATION_PERIOD,SAPLING_ACTION_ANIMATION_PERIOD, health,SAPLING_HEALTH_LIMIT);
    }


    public static String getSaplingKey() {
        return SAPLING_KEY;
    }

    public static void parseSapling(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == SAPLING_NUM_PROPERTIES) {
            int health = Integer.parseInt(properties[SAPLING_HEALTH]);
            Sapling sapling = new Sapling(id, pt, Entity.getImageList(imageStore, SAPLING_KEY), health);
            sapling.tryAddEntity(world);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", SAPLING_KEY, SAPLING_NUM_PROPERTIES));
        }
    }

    public boolean transformSapling(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (super.getHealth() <= 0) {
            Stump stump = new Stump(Stump.getStumpKey() + "_" + this.id, this.position, getImageList(imageStore, Stump.getStumpKey()));

            this.removeEntity(world, scheduler);

            stump.addEntity(world);

            return true;
        } else if (super.getHealth() >= super.getHealthLimit()) {
            getImageList(imageStore, Tree.getTreeKey());
            Tree tree = new Tree(Tree.getTreeKey() + "_" + this.id, this.position, getImageList(imageStore, Tree.getTreeKey()), getNumFromRange(Tree.getTreeActionMax(), Tree.getTreeActionMin()), getNumFromRange(Tree.getTreeAnimationMax(), Tree.getTreeAnimationMin()), getIntFromRange(Tree.getTreeHealthMax(), Tree.getTreeHealthMin()), getHealthLimit());

            this.removeEntity( world, scheduler);

            tree.addEntity(world);
            tree.scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }
    public static double getNumFromRange(double max, double min) {
        Random rand = new Random();
        return min + rand.nextDouble() * (max - min);
    }
    public static int getIntFromRange(int max, int min) {
        Random rand = new Random();
        return min + rand.nextInt(max-min);
    }


    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {
        this.setHealth(this.getHealth()+1);
        if (!this.transform(world, scheduler, imageStore)) {
            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), super.getActionPeriod());
        }
    }


    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
                scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
                scheduler.scheduleEvent(this, new Animation(this, 0), getAnimationPeriod());

    }

    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        return null;
    }
}
