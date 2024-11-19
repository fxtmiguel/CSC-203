import processing.core.PImage;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents the 2D World in which this simulation is running.
 * Keeps track of the size of the world, the background image for each
 * location in the world, and the entities that populate the world.
 */
public final class WorldModel {
    private int numRows;
    private int numCols;
    private Background[][] background;
    private Entity[][] occupancy;
    // Iterate Delete All of the entities (removeEntity)
    // Chnage back ground to game over 
    private Set<Entity> entities;

    public WorldModel() {

    }

    /**
     * Helper method for testing. Don't move or modify this method.
     */
    public List<String> log(){
        List<String> list = new ArrayList<>();
        for (Entity entity : entities) {
            String log = entity.log();
            if(log != null) list.add(log);
        }
        return list;
    }

    public void parseSapling(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Sapling.SAPLING_NUM_PROPERTIES) {
            int health = Integer.parseInt(properties[Sapling.SAPLING_HEALTH]);
            Entity entity = new Sapling(id, pt, imageStore.getImageList(Sapling.SAPLING_KEY), health);
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Sapling.SAPLING_KEY, Sapling.SAPLING_NUM_PROPERTIES));
        }
    }

    public void parseDude(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Dude.DUDE_NUM_PROPERTIES) {
            Entity entity = new Dude(id, pt, Double.parseDouble(properties[Dude.DUDE_ACTION_PERIOD]), Double.parseDouble(properties[Dude.DUDE_ANIMATION_PERIOD]), Integer.parseInt(properties[Dude.DUDE_LIMIT]), imageStore.getImageList(Dude.DUDE_KEY), false);
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Dude.DUDE_KEY, Dude.DUDE_NUM_PROPERTIES));
        }
    }
    public void parseCaptain(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Captain.CAPTAIN_NUM_PROPERTIES) {
            Entity entity = new Captain(id, pt, Double.parseDouble(properties[Captain.CAPTAIN_ACTION_PERIOD]), Double.parseDouble(properties[Captain.CAPTAIN_ANIMATION_PERIOD]), imageStore.getImageList(Captain.CAPTAIN_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Captain.CAPTAIN_KEY, Captain.CAPTAIN_NUM_PROPERTIES));
        }
    }

    public void parseHealer(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Healer.HEALER_NUM_PROPERTIES) {
            Entity entity = new Healer(id, pt, Double.parseDouble(properties[Healer.HEALER_ACTION_PERIOD]), Double.parseDouble(properties[Healer.HEALER_ANIMATION_PERIOD]), imageStore.getImageList(Healer.HEAlER_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Captain.CAPTAIN_KEY, Captain.CAPTAIN_NUM_PROPERTIES));
        }
    }

    public void parseLizard(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Lizard.Lizard_NUM_PROPERTIES) {
            Entity entity = new Lizard(id, pt, Double.parseDouble(properties[Lizard.Lizard_ACTION_PERIOD]), Double.parseDouble(properties[Lizard.Lizard_ANIMATION_PERIOD]), imageStore.getImageList(Lizard.LIZARD_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Lizard.LIZARD_KEY, Lizard.Lizard_NUM_PROPERTIES));
        }
    }

    public void parseFairy(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Fairy.FAIRY_NUM_PROPERTIES) {
            Entity entity = new Fairy(id, pt, Double.parseDouble(properties[Fairy.FAIRY_ACTION_PERIOD]), Double.parseDouble(properties[Fairy.FAIRY_ANIMATION_PERIOD]), imageStore.getImageList(Fairy.FAIRY_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Fairy.FAIRY_KEY, Fairy.FAIRY_NUM_PROPERTIES));
        }
    }

    public void parseTree(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Tree.TREE_NUM_PROPERTIES) {
            Entity entity = new Tree(id, pt, Double.parseDouble(properties[Tree.TREE_ACTION_PERIOD]), Double.parseDouble(properties[Tree.TREE_ANIMATION_PERIOD]), Integer.parseInt(properties[Tree.TREE_HEALTH]), imageStore.getImageList(Tree.TREE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Tree.TREE_KEY, Tree.TREE_NUM_PROPERTIES));
        }
    }

    public void parseObstacle(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Obstacle.OBSTACLE_NUM_PROPERTIES) {
            Entity entity = new Obstacle(id, pt, Double.parseDouble(properties[Obstacle.OBSTACLE_ANIMATION_PERIOD]), imageStore.getImageList(Obstacle.OBSTACLE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Obstacle.OBSTACLE_KEY, Obstacle.OBSTACLE_NUM_PROPERTIES));
        }
    }

    public void parseHouse(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == House.HOUSE_NUM_PROPERTIES) {
            Entity entity =  new House(id, pt, imageStore.getImageList(House.HOUSE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", House.HOUSE_KEY, House.HOUSE_NUM_PROPERTIES));
        }
    }

    public void parseStump(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Stump.STUMP_NUM_PROPERTIES) {
            Entity entity = new Stump(id, pt, imageStore.getImageList(Stump.STUMP_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Stump.STUMP_KEY, Stump.STUMP_NUM_PROPERTIES));
        }
    }

    public void parseGrave(String[] properties, Point pt, String id, ImageStore imageStore) {
        if (properties.length == Grave.GRAVE_NUM_PROPERTIES) {
            Entity entity = new Grave(id, pt, imageStore.getImageList(Grave.GRAVE_KEY));
            this.tryAddEntity(entity);
        }else{
            throw new IllegalArgumentException(String.format("%s requires %d properties when parsing", Stump.STUMP_KEY, Stump.STUMP_NUM_PROPERTIES));
        }
    }


    public void tryAddEntity(Entity entity) {
        if (this.isOccupied(entity.getPosition())) {
            // arguably the wrong type of exception, but we are not
            // defining our own exceptions yet
            throw new IllegalArgumentException("position occupied");
        }

        this.addEntity(entity);
    }

    public boolean isOccupied(Point pos) {
        return this.withinBounds(pos) && this.getOccupancyCell(pos) != null;
    }

    public boolean withinBounds(Point pos) {
        return pos.y >= 0 && pos.y < this.numRows && pos.x >= 0 && pos.x < this.numCols;
    }

    public static Optional<Entity> nearestEntity(List<Entity> entities, Point pos) {
        if (entities.isEmpty()) {
            return Optional.empty();
        } else {
            Entity nearest = entities.get(0);
            int nearestDistance = Point.distanceSquared(nearest.getPosition(), pos);

            for (Entity other : entities) {
                int otherDistance = Point.distanceSquared(other.getPosition(), pos);

                if (otherDistance < nearestDistance) {
                    nearest = other;
                    nearestDistance = otherDistance;
                }
            }

            return Optional.of(nearest);
        }
    }

    public Optional<Entity> findNearest(Point pos, List<Class<? extends Entity>> classes) {
        List<Entity> ofType = new LinkedList<>();
        for (Class<? extends Entity> classE: classes) {
            for (Entity entity : this.entities) {
                if (classE.isInstance(entity)) {
                    ofType.add(entity);
                }
            }
        }

        return nearestEntity(ofType, pos);
    }

    /*
           Assumes that there is no entity currently occupying the
           intended destination cell.
        */
    public void addEntity(Entity entity) {
        if (withinBounds(entity.getPosition())) {
            this.setOccupancyCell(entity.getPosition(), entity);
            this.entities.add(entity);
        }
    }

    public void moveEntity(EventScheduler scheduler, Entity entity, Point pos) {
        Point oldPos = entity.getPosition();
        if (withinBounds(pos) && !pos.equals(oldPos)) {
            this.setOccupancyCell(oldPos, null);
            Optional<Entity> occupant = this.getOccupant(pos);
            occupant.ifPresent(target -> removeEntity(scheduler, target));
            this.setOccupancyCell(pos, entity);
            entity.setPosition(pos);
        }
    }

    public void removeEntity(EventScheduler scheduler, Entity entity) {
        scheduler.unscheduleAllEvents(entity);
        removeEntityAt(entity.getPosition());
    }

    public void removeEntityAt(Point pos) {
        if (withinBounds(pos) && this.getOccupancyCell(pos) != null) {
            Entity entity = this.getOccupancyCell(pos);

            /* This moves the entity just outside of the grid for
             * debugging purposes. */
            entity.setPosition(new Point(-1, -1));
            this.entities.remove(entity);
            this.setOccupancyCell(pos, null);
        }
    }

    public Optional<Entity> getOccupant(Point pos) {
        if (isOccupied(pos)) {
            return Optional.of(getOccupancyCell(pos));
        } else {
            return Optional.empty();
        }
    }

    public Entity getOccupancyCell(Point pos) {
        return this.occupancy[pos.y][pos.x];
    }

    public void setOccupancyCell(Point pos, Entity entity) {
        this.occupancy[pos.y][pos.x] = entity;
    }

    public void load(Scanner saveFile, ImageStore imageStore, Background defaultBackground){
        parseSaveFile(saveFile, imageStore, defaultBackground);
        if(this.background == null){
            this.background = new Background[this.numRows][this.numCols];
            for (Background[] row : this.background)
                Arrays.fill(row, defaultBackground);
        }
        if(this.occupancy == null){
            this.occupancy = new Entity[this.numRows][this.numCols];
            this.entities = new HashSet<>();
        }
    }

    public void parseSaveFile(Scanner saveFile, ImageStore imageStore, Background defaultBackground){
        String lastHeader = "";
        int headerLine = 0;
        int lineCounter = 0;
        while(saveFile.hasNextLine()){
            lineCounter++;
            String line = saveFile.nextLine().strip();
            if(line.endsWith(":")){
                headerLine = lineCounter;
                lastHeader = line;
                switch (line){
                    case "Backgrounds:" -> this.background = new Background[this.numRows][this.numCols];
                    case "Entities:" -> {
                        this.occupancy = new Entity[this.numRows][this.numCols];
                        this.entities = new HashSet<>();
                    }
                }
            }else{
                switch (lastHeader){
                    case "Rows:" -> this.numRows = Integer.parseInt(line);
                    case "Cols:" -> this.numCols = Integer.parseInt(line);
                    case "Backgrounds:" -> parseBackgroundRow(line, lineCounter-headerLine-1, imageStore);
                    case "Entities:" -> parseEntity(line, imageStore);
                }
            }
        }
    }

    public void parseBackgroundRow(String line, int row, ImageStore imageStore) {
        String[] cells = line.split(" ");
        if(row < this.numRows){
            int rows = Math.min(cells.length, this.numCols);
            for (int col = 0; col < rows; col++){
                this.background[row][col] = new Background(cells[col], imageStore.getImageList(cells[col]));
            }
        }
    }

    public void parseEntity(String line, ImageStore imageStore) {
        String[] properties = line.split(" ", Entity.ENTITY_NUM_PROPERTIES + 1);
        if (properties.length >= Entity.ENTITY_NUM_PROPERTIES) {
            String key = properties[Entity.PROPERTY_KEY];
            String id = properties[Entity.PROPERTY_ID];
            Point pt = new Point(Integer.parseInt(properties[Entity.PROPERTY_COL]), Integer.parseInt(properties[Entity.PROPERTY_ROW]));

            properties = properties.length == Entity.ENTITY_NUM_PROPERTIES ?
                    new String[0] : properties[Entity.ENTITY_NUM_PROPERTIES].split(" ");

            switch (key) {
                case Obstacle.OBSTACLE_KEY -> parseObstacle(properties, pt, id, imageStore);
                case Dude.DUDE_KEY -> parseDude(properties, pt, id, imageStore);
                case Fairy.FAIRY_KEY -> parseFairy(properties, pt, id, imageStore);
                case House.HOUSE_KEY -> parseHouse(properties, pt, id, imageStore);
                case Tree.TREE_KEY -> parseTree(properties, pt, id, imageStore);
                case Sapling.SAPLING_KEY -> parseSapling(properties, pt, id, imageStore);
                case Stump.STUMP_KEY -> parseStump(properties, pt, id, imageStore);
                case Captain.CAPTAIN_KEY -> parseCaptain(properties, pt, id, imageStore);
                case Healer.HEAlER_KEY -> parseHealer(properties, pt, id, imageStore);
                case Lizard.LIZARD_KEY -> parseLizard(properties, pt, id, imageStore);                                                                            //new parse Captain
                default -> throw new IllegalArgumentException("Entity key is unknown");
            }
        }else{
            throw new IllegalArgumentException("Entity must be formatted as [key] [id] [x] [y] ...");
        }
    }

//new function
    public void worldChangingEventCaptain(WorldModel world, EventScheduler scheduler, ImageStore imageStore, List<Point> closePoints){
        Random randomNumber = new Random();
        closePoints = closePoints.stream().
                filter(p -> p.x >=0 && p.y >=0)
                .collect(Collectors.toList());

        if (!closePoints.isEmpty()) {
            try {
                for (int i = 0; i < 1; i++) {
                    Point point1 = closePoints.get(randomNumber.nextInt(0, closePoints.size()));
                    if (world.withinBounds(point1)) {
                        Captain captainChar = new Captain(UUID.randomUUID().toString(), point1, Double.parseDouble(String.valueOf(Captain.CAPTAIN_ACTION_PERIOD)), Double.parseDouble(String.valueOf(Captain.CAPTAIN_ANIMATION_PERIOD)), imageStore.getImageList(Captain.CAPTAIN_KEY));

                        this.addEntity(captainChar);
                        captainChar.scheduleActions(scheduler, world, imageStore);
                    } }
            } catch (Error e) {
                System.out.println("Error");
            }
        }
    }


    //new function
    public void worldChangingEventHealer(WorldModel world, EventScheduler scheduler, ImageStore imageStore, List<Point> closePoints){
        Random randomNumber = new Random();
        closePoints = closePoints.stream().
                filter(p -> p.x >=0 && p.y >=0  )
                .collect(Collectors.toList());

        if (!closePoints.isEmpty()) {
            try {
                for (int i = 0; i < 1; i++) {
                    Optional<Entity> target = world.findNearest(closePoints.get(0), new ArrayList<>(List.of(Fairy.class)));
                    Point point1 = closePoints.get(randomNumber.nextInt(0, closePoints.size()));
                    if (world.withinBounds(point1)) {
                        if (target.isPresent()) {
                            Healer healer = new Healer(UUID.randomUUID().toString(), target.get().getPosition(), Double.parseDouble(String.valueOf(Healer.HEALER_ACTION_PERIOD)), Double.parseDouble(String.valueOf(Healer.HEALER_ANIMATION_PERIOD)), imageStore.getImageList(Healer.HEAlER_KEY));
                            world.removeEntity(scheduler, target.get());

                            this.addEntity(healer);
                            healer.scheduleActions(scheduler, world, imageStore);
                        } else {

                            Healer healer = new Healer(UUID.randomUUID().toString(), point1, Double.parseDouble(String.valueOf(Healer.HEALER_ACTION_PERIOD)), Double.parseDouble(String.valueOf(Healer.HEALER_ANIMATION_PERIOD)), imageStore.getImageList(Healer.HEAlER_KEY));

                            this.addEntity(healer);
                            healer.scheduleActions(scheduler, world, imageStore);
                        }
                    } }
            } catch (Error e) {
                System.out.println("Error");
            }
        }
    }

    //using random generator
    public void worldChangingEventLizard(WorldModel world, EventScheduler scheduler, ImageStore imageStore, List<Point> closePoints){
        Random randomNumber = new Random();
        closePoints = closePoints.stream().
                filter(p -> p.x >=0 && p.y >=0  )
                .collect(Collectors.toList());

        if (!closePoints.isEmpty()) {
            try {
                for (int i = 0; i < 3; i++) {
                    Point point1 = closePoints.get(randomNumber.nextInt(0, closePoints.size()));
                    if (world.withinBounds(point1)) {
                        Lizard lizardChar = new Lizard(UUID.randomUUID().toString(), point1, Double.parseDouble(String.valueOf(Lizard.Lizard_ACTION_PERIOD)), Double.parseDouble(String.valueOf(Lizard.Lizard_ANIMATION_PERIOD)), imageStore.getImageList(Lizard.LIZARD_KEY));

                        this.addEntity(lizardChar);
                        lizardChar.scheduleActions(scheduler, world, imageStore);
                    } }
            } catch (Error e) {
                System.out.println("Error");
            }
        }
    }

    public Background getBackgroundCell(Point pos) {
        return this.background[pos.y][pos.x];
    }

    public void setBackgroundCell(Point pos, Background background) {
        this.background[pos.y][pos.x] = background;
    }

    public Optional<PImage> getBackgroundImage(Point pos) {
        if (withinBounds(pos)) {
            return Optional.of(ImageUtil.getCurrentImage(this.getBackgroundCell(pos)));
        } else {
            return Optional.empty();
        }
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

}
