import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class AStarPathingStrategy implements PathingStrategy {

    private List<Point> pathReconstruction(Node curr, Node startNode) {
        List<Point> shortestPath = new LinkedList<>();
        while (!curr.equals(startNode)) {
            shortestPath.add(curr.point);
            curr = curr.parentNode;

        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }
    private int calculateHDistance(Point point1, Point endingNode) {
        return Math.abs(point1.x - endingNode.x) + Math.abs(point1.y - endingNode.y);
    }


    @Override
    public List<Point> computePath(Point start, Point end, Predicate<Point> canPassThrough, BiPredicate<Point, Point> withinReach, Function<Point, Stream<Point>> potentialNeighbors) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node startingNode = new Node(start, null);
        Point endingNode = end;
        startingNode.setfVal(calculateHDistance(startingNode.point, endingNode));
        startingNode.setgVal(0);
        openList.add(startingNode);

        while (!openList.isEmpty()) {
            Node curr = openList.get(0);
            for (int i = 1; i < openList.size(); i++) {
                Node pointNode = openList.get(i);

                if (curr.getgVal() + calculateHDistance(curr.point, endingNode) > (pointNode.getgVal()) + calculateHDistance(pointNode.point, endingNode)) {
                    curr = pointNode;
                }
            }
            openList.remove(curr);
            closedList.add(curr);
            if (withinReach.test(curr.point, end)) {
                return pathReconstruction(curr, startingNode);
            }

            Stream<Point> neighborStream = potentialNeighbors.apply(curr.point);
            Node finalNode = curr;
            neighborStream
                    .filter(canPassThrough)
                    .forEach(neighbor -> {

                        Node neighboringNode = new Node(neighbor, finalNode);
                        if (!openList.contains(neighboringNode)) {
                            openList.add(neighboringNode);
                        }
                        double GVal = finalNode.getgVal() + 1;
                        double fValue = GVal + calculateHDistance(neighbor, end);

                        if (GVal < neighboringNode.getgVal()) {

                            neighboringNode.setfVal( fValue);
                            neighboringNode.setgVal(GVal);
                            neighboringNode.parentNode = finalNode;
                        }


                    });

        }
        return Collections.emptyList();
    }

}   
