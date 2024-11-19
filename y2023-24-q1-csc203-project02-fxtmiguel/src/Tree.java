import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Tree extends Health {

    public static final String TREE_KEY = "tree";
    public static final int TREE_ANIMATION_PERIOD = 0;
    public static final int TREE_ACTION_PERIOD = 1;
    public static final int TREE_HEALTH = 2;
    public static final int TREE_NUM_PROPERTIES = 3;
    public static final double TREE_ANIMATION_MAX = 0.600;
    public static double getTreeAnimationMax(){
        return TREE_ANIMATION_MAX;
    }
    public static final double TREE_ANIMATION_MIN = 0.050;
    public static double getTreeAnimationMin(){
        return TREE_ANIMATION_MIN;
    }
    public static final double TREE_ACTION_MAX = 1.400;
    public static double getTreeActionMax(){
        return TREE_ACTION_MAX;
    }
    public static final double TREE_ACTION_MIN = 1.000;
    public static double getTreeActionMin(){
        return TREE_ACTION_MIN;
    }
    public static final int TREE_HEALTH_MAX = 3;
    public static int getTreeHealthMax(){
        return TREE_HEALTH_MAX;
    }
    public static final int TREE_HEALTH_MIN = 1;
    public static int getTreeHealthMin(){
        return TREE_HEALTH_MIN;
    }
    public Tree(String id, Point position, List<PImage> images, double actionPeriod, double animationPeriod, int health, int healthLimit) {
        super(TREE_KEY ,id, position, images, actionPeriod, animationPeriod, health, healthLimit);
    }


    public static String getTreeKey() {
        return TREE_KEY;
    }


    public boolean transformTree(WorldModel world, EventScheduler scheduler, ImageStore imageStore) {
        if (getHealth() <= 0) {
            Entity stump = new Stump(Stump.getStumpKey() + "_" + this.id, this.position, getImageList(imageStore, Stump.getStumpKey()));
            this.removeEntity(world,scheduler);
            stump.addEntity(world);

            return true;
        }

        return false;
    }

    public static void parseTree(WorldModel world, String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == TREE_NUM_PROPERTIES) {
            Tree tree = new Tree(id, pt, Entity.getImageList(imageStore ,TREE_KEY), Double.parseDouble(properties[TREE_ACTION_PERIOD]), Double.parseDouble(properties[TREE_ANIMATION_PERIOD]), Integer.parseInt(properties[TREE_HEALTH]), getTreeHealth());
            tree.addEntity(world);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Tree.getTreeKey(), TREE_NUM_PROPERTIES));
        }
    }

    private static int getTreeHealth() {
        return TREE_HEALTH;
    }

    @Override
    public void scheduleActions(EventScheduler scheduler, WorldModel world, ImageStore imageStore) {
        scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.actionPeriod);
        scheduler.scheduleEvent(this, new Animation(this, 0), this.animationPeriod);
    }
    @Override
    public void executeActivity(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        if (!this.transform(world, scheduler, imageStore)) {

            scheduler.scheduleEvent(this, new Activity(this, world, imageStore), this.getActionPeriod());
        }
    }



    @Override
    public Point nextPosition(WorldModel world, Point destPos) {
        return null;
    }
}
