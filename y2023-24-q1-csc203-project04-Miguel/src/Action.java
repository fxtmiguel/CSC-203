/**
 * An action that can be taken by an entity
 */
public interface Action {

    void execute(EventScheduler scheduler);
}
