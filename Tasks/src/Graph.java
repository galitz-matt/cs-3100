import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	
	public HashMap<Node, ArrayList<Node>> adj;
	
	public Graph() {
		adj = new HashMap<Node, ArrayList<Node>>();
	}
	
	public void addNode(String s) {
		adj.put(new Node(s), new ArrayList<Node>());
	}
	
	public void addEdge(String st, String f) {
		adj.get(new Node(st)).add(new Node(f));
	}
	
	public String toString() {
		return adj.toString();
	}
}