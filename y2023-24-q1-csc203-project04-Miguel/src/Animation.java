public class Animation implements Action {
    private Moving entity;
    private int repeatCount;

    public Animation(Moving entity, int repeatCount) {
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    @Override
    public void execute(EventScheduler scheduler) {
        this.entity.nextImage();

        if (this.repeatCount != 1) {
            scheduler.scheduleEvent(this.entity, new Animation(this.entity, Math.max(this.repeatCount - 1, 0)), this.entity.getAnimationPeriod());
        }
    }
}