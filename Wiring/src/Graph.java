import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	
	public ArrayList<Node> V;
	public HashMap<Node,  ArrayList<Edge>> E;
	
	public Graph() {
		V = new ArrayList<Node>();
		E = new HashMap<Node, ArrayList<Edge>>();
	}
	
	public void addNode(Node n) {
		if (!V.contains(n)) {
			V.add(n);
		}
		E.putIfAbsent(n, new ArrayList<Edge>());
	}
	
	public void addEdge(Edge e) {
		for (Edge ed : E.get(e.head())) {
			if (e.equals(ed)) {
				return;
			}
		}
		E.get(e.head()).add(e);
	}
	
	public String getType(String s) {
		for (Node n : V) {
			if (s.equals(n.label)) {
				return n.type;
			}
		}
		return "";
	}
	
	public void print() {
		System.out.println("----");
		for (Node n : V) {
			String s = n.toString();
			s += " = ";
			s += E.get(n).toString();
			System.out.println(s);
		}
		System.out.println("----");
	}
	
}
