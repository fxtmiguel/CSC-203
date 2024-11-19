import processing.core.PImage;

import java.util.List;

public abstract class Moving extends Entity{
    private double animationPeriod;

    public Moving(String id, Point position, List<PImage> images, double animationPeriod) {
        super(id, position, images);
        this.animationPeriod = animationPeriod;
    }

    public abstract void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler);

    public double getAnimationPeriod() { return this.animationPeriod; }

}