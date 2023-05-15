import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	
	public HashMap<Node, ArrayList<Node>> adj;
	
	public Graph() {
		adj = new HashMap<Node, ArrayList<Node>>();
	}
	
	public void addNode(int i) {
		adj.putIfAbsent(new Node(i), new ArrayList<Node>());
	}
	
	public void addEdge(int st, int f) {
		Node start = new Node(st);
		Node finish = new Node(f);
		if (!adj.get(start).contains(finish)) {
			adj.get(start).add(finish);
		}
	}
	
	public void removeEdge(int id) {
		Node d = new Node(id);
		for (Node n : adj.keySet()) {
			for(int i = 0; i < adj.get(n).size(); i++) {
				if (d.name == adj.get(n).get(i).name) {
					adj.get(n).remove(i);
				}
			}
		}
	}
	
	public void sortAllEdges() {
		for (Node n : adj.keySet()) {
			adj.get(n).sort(new NodeComparator());
		}
	}
	
	public String toString() {
		return adj.toString();
	}
	
	public int size() {
		return adj.size();
	}
}