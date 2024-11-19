/**
 * A simple class representing a location in 2D space.
 */
public final class Point {
    public final int x;
    public final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public static boolean adjacent(Point p1, Point p2) {
        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) || (p1.y == p2.y && Math.abs(p1.x - p2.x) == 1);
    }

    public static int distanceSquared(Point p1, Point p2) {
        int deltaX = p1.x - p2.x;
        int deltaY = p1.y - p2.y;

        return deltaX * deltaX + deltaY * deltaY;
    }

    public int distanceSquared(Point point) {
        int deltaX = x - point.x;
        int deltaY = y - point.y;

        return deltaX * deltaX + deltaY * deltaY;
    }

    public int manhattanDistance(Point point) {
        int deltaX = x - point.x;
        int deltaY = y - point.y;

        return Math.abs(deltaX) + Math.abs(deltaY);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null || other.getClass() != getClass()) return false;
        Point point = (Point) other;
        return this.x == point.x && this.y == point.y;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 31 + x;
        result = result * 31 + y;
        return result;
    }
}
