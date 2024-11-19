public class Activity implements Action {
    private Moving entity;
    private WorldModel world;
    private ImageStore imageStore;

    public Activity(Moving entity, WorldModel world, ImageStore imageStore) {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
    }

    @Override
    public void execute(EventScheduler scheduler) {
        this.entity.execute(this.world, this.imageStore, scheduler);
    }
}