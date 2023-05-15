import java.lang.Math;
import java.util.Comparator;

public class Point implements Comparable<Point> {
	
	public double x;
	public double y;
	
	public Point(double xCoord, double yCoord) {
		x = xCoord;
		y = yCoord;
	}
	
	public double distance(Point other) {
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2)); 
	}
	
	@Override
	public int compareTo(Point other) {
		return x == other.x ? 0 : (x > other.x ? 1 : -1);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}

class XPointComparator implements Comparator<Point> {
	
	@Override
	public int compare(Point p1, Point p2) {
		return p1.compareTo(p2);
	}
}

