import java.util.Comparator;


public class Room {
	
	public int K;
	public int C;
	public int NC;
	
	public Room(int c, int nc) {
		K = c;
		C = c;
		NC = nc;
	}
	
	public int decCompareTo(Room o) {
		if (NC > o.NC) {
			return -1;
		}
		else if (NC < o.NC) {
			return 1;
		}
		return 0;
	}
	
	public int incCompareTo(Room o) {
		if (C < o.C) {
			return -1;
		}
		else if (C > o.C) {
			return 1;
		}
		return 0;
	}
	
	public String toString() {
		return "(" + K + "," + C + "," + NC + ")";
	}
	
}

class DecComparator implements Comparator<Room> {
	
	public int compare(Room r1, Room r2) {
		return r1.decCompareTo(r2);
	}
}

class IncComparator implements Comparator<Room> {
	
	public int compare(Room r1, Room r2) {
		return r1.incCompareTo(r2);
	}
}
