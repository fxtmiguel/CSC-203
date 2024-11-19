public class Animation implements Action{
    private Entity entity;
    private int repeatCount;

    public Animation(Entity entity, int repeatCount){
        this.entity = entity;
        this.repeatCount = repeatCount;
    }

    @Override
    public void executeAction(EventScheduler scheduler) {
        nextImage(entity);

        if (repeatCount != 1) {
            this.repeatCount = Math.max(repeatCount - 1, 0);
            scheduler.scheduleEvent(entity, this, entity.getAnimationPeriod());
        }
    }
    private static void nextImage(Entity entity) {
        entity.imageIndex += 1;
    }
}
