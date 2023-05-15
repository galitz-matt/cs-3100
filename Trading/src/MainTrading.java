import java.util.Scanner;
import java.util.Arrays;
import java.lang.Math;
import java.util.ArrayList;

public class MainTrading {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<String> results = new ArrayList<String>();
		
		while (true) {
			int numPoints = in.nextInt();
			if (numPoints == 0) {
				break;
			}
			
			Point[] points = new Point[numPoints];
			for (int i = 0; i < numPoints; i++) {
				double xCoord = in.nextDouble();
				double yCoord = in.nextDouble();
				Point p = new Point(xCoord, yCoord);
				points[i] = p;
			}
			
			Arrays.sort(points, new XPointComparator());
			
			double smallDist = closest(points);
			if (smallDist < 10001) {
				results.add(String.format("%.4f", smallDist));
			}
			else {
				results.add("infinity");
			}
		}
		
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i));
		}
		
		in.close();
	}
	
	public static double closest(Point[] arr) {
		return Math.min(lrClosest(arr, 0, arr.length - 1, Double.MAX_VALUE), midClosest(arr));
	}
	
	public static double lrClosest(Point[] arr, int i, int j, double min) {
		if (j - i <= 2) {
			double potMin = sortFindMin(arr, i, j, min);
			min = Math.min(min, potMin);
			return min;
		}
		int cut = (i + j) / 2;
		return Math.min(lrClosest(arr, i, cut, min), lrClosest(arr, cut + 1, j, min));
	}
	
	public static double midClosest(Point[] arr) {
		double min = Double.MAX_VALUE;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j <= i + 7 && j < arr.length; j++) {
				min = Math.min(min, arr[i].distance(arr[j]));
			}
		}
		return min;
	}
	
	public static double sortFindMin(Point[] arr, int start, int stop, double min) {
		for (int i = start + 1; i <= stop; i++) {
			for (int j = i - 1; j >= start; j--) {
				Point temp = arr[j + 1];
				double dist = temp.distance(arr[j]);
				if (dist < min) {
					min = dist;
				}
				if (temp.y < arr[j].y) {
					arr[j + 1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		return min;
	}
}
