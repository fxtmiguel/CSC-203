import processing.core.PImage;
import java.util.List;

public class Grave extends Entity {
    public static final String GRAVE_KEY = "grave";
    public static final int GRAVE_NUM_PROPERTIES = 0;

    public Grave(String id, Point position, List<PImage> images) {
        super(id, position, images);
    }
}