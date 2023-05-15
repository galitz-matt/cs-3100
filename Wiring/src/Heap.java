import java.util.ArrayList;
import java.util.HashMap;

public class Heap {

	private ArrayList<Edge> heap;
	private HashMap<String, Integer> lookup;
	
	public Heap() { 
		heap = new ArrayList<Edge>();
		heap.add(null);
		lookup = new HashMap<String, Integer>();
		lookup.put("box", 0);
		lookup.put("outlet", 0);
		lookup.put("breaker", 0);
		lookup.put("light", 1);
		lookup.put("switch", 1);
	}
	
	private int priority(int i) {
		return lookup.get(heap.get(i).tail().type);
	}
	
	private boolean compare(int i1, int i2) {
		return (priority(i1) < priority(i2) || (priority(i1) == priority(i2) && heap.get(i1).weight < (heap.get(i2)).weight));
	}
	
	private void percolateUp(int index) {
		if (index <= 1) { 
			return;
		}
		int si = index / 2;
		
		if (compare(index, si)) {
			swap(si, index);
			percolateUp(si);
		}
	}
	
	private void percolateDown(int index) {
		if (index > size() / 2 && index <= size()) { 
			return;
		}
		
		int si;
		int leftIndex = index * 2;
		int rightIndex = (index * 2) + 1;
		
		if (leftIndex <= size() && rightIndex <= size()){
			if (compare(leftIndex, rightIndex)) {
				si = leftIndex;
			}
			else {
				si = rightIndex;
			}
			if (compare(si, index)) {
				swap(si, index);
				percolateDown(si);
			}
		}
		else if (leftIndex > size()) {
			return;
		}
		else {
			if (compare(leftIndex, index)) {
				swap(leftIndex, index);
				percolateDown(leftIndex);
			}
		}
	}
	
	public void push(Edge data) {
		heap.add(data);
		percolateUp(size());
	}

	public Edge poll() {
		if (size() == 0) { 
			return null;
		}
		Edge polling = peek();
		heap.set(1, heap.get(size()));
		heap.remove(size());
		percolateDown(1);
		return polling;
	}

	public Edge peek() {
		return heap.get(1);
	}
	
	public int size() {
		return heap.size() - 1;
	}
	
	public String toString() {
		return heap.toString();
	}
	
	private void swap(int index1, int index2) {
		Edge temp;
		temp = heap.get(index1);
		heap.set(index1, heap.get(index2));
		heap.set(index2, temp);
	}
	
}
