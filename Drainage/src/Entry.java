import java.util.Comparator;

public class Entry implements Comparable<Entry>{
	
	public int data;
	public Integer[] index;
	
	public Entry(int d, int row, int column) {
		data = d;
		index = new Integer[] {row, column};
	}
	
	public int compareTo(Entry o) {
		if (data < o.data) {
			return -1;
		}
		else if (data > o.data) {
			return 1;
		}
		return 0;
	}
	
	public String toString() {
		return "([" + index[0] + "][" + index[1] + "]: " + data + ")";
	}
}

class EntryComparator implements Comparator<Entry> {
	
	public int compare(Entry e1, Entry e2) {
		return e1.compareTo(e2);
	}
}