import java.util.Comparator;

public class Price implements Comparable<Price>{
	
	public String name;
	public int cost;
	
	public Price(String n, int c) {
		name = n;
		cost = c;
	}
	
	@Override
	public int compareTo(Price o) {
		if (cost > o.cost) {
			return 1;
		}
		else if (cost < o.cost) {
			return -1;
		}
		else {
			return name.compareTo(o.name);
		}
	}
	
	public String toString() {
		return name + " " + cost;
	}
}

class PriceComparator implements Comparator<Price> {
	
	@Override
	public int compare(Price p1, Price p2) {
		return p1.compareTo(p2);
	}
}