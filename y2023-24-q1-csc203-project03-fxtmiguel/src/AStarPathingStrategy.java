import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy {
//    /**
//     * Return a list containing a single point representing the next step toward a goal
//     * If the start is within reach of the goal, the returned list is empty.
//     *
//     * @param start the point to begin the search from
//     * @param end the point to search for a point within reach of
//     * @param canPassThrough a function that returns true if the given point is traversable
//     * @param withinReach a function that returns true if both points are within reach of each other
//     * @param potentialNeighbors a function that returns the neighbors of a given point, as a stream
//     *
//     */

    // Euclidean distance functions
    //constructing shortest path function
    //Implement structures in compute path

    private double calculateEuclideanDistance(Point startP, Point endP){
        return Math.sqrt(Math.pow(endP.x - startP.x, 2) + Math.pow(endP.y - startP.y, 2));
    }

    private List<Point> pathReconstruction(Node goal) {
        List<Point> shortestPath = new LinkedList<>();
        Node currNode = goal;

        while (currNode.getParentNode() != null) {
            shortestPath.add(0, currNode.getPoint());
            currNode = currNode.getParentNode();
        }

        return shortestPath;
    }



    public List<Point> computePath(
        Point start,
        Point end,
        Predicate<Point> canPassThrough,
        BiPredicate<Point, Point> withinReach,
        Function<Point, Stream<Point>> potentialNeighbors
    ) {
        PriorityQueue<Node> openQueue = new PriorityQueue<>(Comparator.comparing(Node::getFval));
        Map<Point, Node> pointNodeMap = new HashMap<>();
        List<Node> closedList = new ArrayList<>();

        Node startingNode = new Node(start, null, 0, calculateEuclideanDistance(start,end) );
        openQueue.add(startingNode);
        pointNodeMap.put(start, startingNode);

        while (!openQueue.isEmpty()) {
            Node currNode = openQueue.poll(); //lowest path cost
            Point currPoint = currNode.getPoint();

            if (closedList.contains(currNode)) {
                continue;
            }
            closedList.add(currNode);

//            System.out.println("Current Point: " + currPoint);
//            System.out.println("OpenQueue Size: " + openQueue.size());
//            System.out.println("closedlist " + closedList.size());

            if (withinReach.test(currPoint, end)) {
                List<Point> shortestPath = pathReconstruction(currNode);
                                                                                //checking for end of path
                return shortestPath;
            }

            Stream<Point> neighborStream = potentialNeighbors.apply(currNode.getPoint());
                    neighborStream
                    .filter(canPassThrough)
                    .forEach(neighbor -> {
                        Node neighborNode = new Node(neighbor);
                        if (!openQueue.contains(neighborNode)) {
                            openQueue.add(neighborNode);

                        }
                        double possibleGVal = currNode.getGval() + 1;
                        double fVal = possibleGVal + calculateEuclideanDistance(neighbor, end);

                        if (possibleGVal < neighborNode.getGval()) {
                            neighborNode.setFval(fVal);
                            neighborNode.setGval(possibleGVal);
                            neighborNode.setParentNode(currNode);

                        }


                    }
                    );

        }



        return Collections.emptyList();
    }
}
