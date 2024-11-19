import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy {
    List<Point> openSet;
    List<Point> closedSet;
    Map<Point, Point> cameFrom;
    Map<Point, Integer> gScore;
    Map<Point, Integer> hScore;
    Map<Point, Integer> fScore;

    public AStarPathingStrategy() {
        this.openSet = new ArrayList<>();
        this.closedSet = new ArrayList<>();
        this.cameFrom = new HashMap<>();
        this.gScore = new HashMap<>();
        this.hScore = new HashMap<>();
        this.fScore = new HashMap<>();
    }
    /**
     * Return a list containing a single point representing the next step toward a goal
     * If the start is within reach of the goal, the returned list is empty.
     *
     * @param start the point to begin the search from
     * @param end the point to search for a point within reach of
     * @param canPassThrough a function that returns true if the given point is traversable
     * @param withinReach a function that returns true if both points are within reach of each other
     * @param potentialNeighbors a function that returns the neighbors of a given point, as a stream
     */
    public List<Point> computePath(
        Point start,
        Point end,
        Predicate<Point> canPassThrough,
        BiPredicate<Point, Point> withinReach,
        Function<Point, Stream<Point>> potentialNeighbors
    ) {
        openSet.clear();
        closedSet.clear();
        cameFrom.clear();
        gScore.clear();
        hScore.clear();
        fScore.clear();

        if (withinReach.test(start, end)) {
            return new ArrayList<>();
        }

        else {
            // Add start to open set and calculate values
            openSet.add(start);
            cameFrom.put(start, null);
            hScore.put(start, start.manhattanDistance(end));
            gScore.put(start, 0);
            fScore.put(start, start.manhattanDistance(end));

            // Check if there are possible neighbors or path is found
            while (!openSet.isEmpty()) {
                Comparator<Point> fScoreComparator = Comparator.comparingInt(p -> fScore.getOrDefault(p, Integer.MAX_VALUE));
                Point currNode = openSet.stream().min(fScoreComparator).orElse(null);
                if (currNode == null) {
                    break;
                }

                // Move Current Node from Open set to closed set
                closedSet.add(currNode);
                openSet.remove(currNode);

                if (withinReach.test(currNode, end)) {
                    return reconstructPath(currNode);
                }

                List<Point> neighbors = potentialNeighbors.apply(currNode)
                        .filter(canPassThrough)
                        // Skip neighbors in the closed set
                        .filter(neighbor -> !closedSet.contains(neighbor))
                        .toList();

                // Adding potential neighbors to Open Set
                for (Point neighbor : neighbors) {
                    int nextGScore = gScore.get(currNode) + 1;

                    if (!openSet.contains(neighbor) || nextGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                        openSet.add(neighbor);
                        cameFrom.put(neighbor, currNode);
                        hScore.put(neighbor, neighbor.manhattanDistance(end));
                        gScore.put(neighbor, nextGScore);
                        fScore.put(neighbor, nextGScore + hScore.get(neighbor));
                    }
                }

            }
        }
        return new ArrayList<>();
    }

    public List<Point> reconstructPath(Point currNode) {
        List<Point> path = new ArrayList<>();
        while (currNode != null) {
            path.add(currNode);
            currNode = cameFrom.get(currNode);
        }
        Collections.reverse(path);
        path.remove(0);
        return path;
    }
}
