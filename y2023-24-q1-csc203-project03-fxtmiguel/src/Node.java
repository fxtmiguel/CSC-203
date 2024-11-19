import java.util.Objects;

public class Node implements Comparable<Node> {

    private final Point point;

    private  Node parentNode;

    private double gval;

    private double fval;

    public Node(Point point, Node parentNode, double pathCost, double valueLevel){
        this.point = point;
        this.parentNode = parentNode;
        this.gval = pathCost;
        this.fval = valueLevel;
    }

    public Node(Point point){
        this(point, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }


    public Point getPoint() {
        return point;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setGval(double pathCost) {
        this.gval = pathCost;
    }
    public double getGval() {
        return gval;
    }

    public void setFval(double valueLevel) {
        this.fval = valueLevel;
    }
    public double getFval() {
    return fval;
    }


    @Override
    public int compareTo(Node o) {
        return Double.compare(gval, o.getGval());
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return point.equals(node.point);
    }


}
