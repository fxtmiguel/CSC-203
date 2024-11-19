public class Node {
    Point point;
    Node parentNode;
    private double gVal;
    private double fVal;

    public Node(Point point, Node parentNode){
        this.point = point;
        this.parentNode = parentNode;
        this.gVal = Integer.MAX_VALUE;
        this.fVal = 0;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        return point.equals(other.point);
    }

    public double getgVal(){
        return gVal;
    }

    public double getfVal(){
        return fVal;
    }

    public void setgVal(double pathCost) {
        this.gVal = pathCost;
    }

    public void setfVal(double valueLevel) {
        this.fVal = valueLevel;
    }


}
