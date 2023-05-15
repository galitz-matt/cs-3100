import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class MainWiring {

	public static ArrayList<Node> visited = new ArrayList<Node>();
	public static HashMap<String, String> lightswitch = new HashMap<String, String>();
	
	public static int rawPrim(Graph g, Node u) {
		Heap PQ = new Heap();
		int cost = 0;
		visited.add(u);
		
		for (Edge e : g.E.get(u)) {
			PQ.push(e);
		}
		
		while (visited.size() < g.V.size()) {
			Edge min = PQ.poll();
			if (!visited.contains(min.tail())) {
				cost += min.weight;
				visited.add(min.tail());
			}
			for (Edge e : g.E.get(min.tail())) {
				if (!visited.contains(e.tail())) {
					PQ.push(e);
				}
			}
		}
		return cost;
	}
	
	public static boolean checkEdge(Node head, Node tail) {
		// This should be a switch statement
		if (head.type.equals("breaker")) {
			if (tail.type.equals("light")) {
				return false;
			}
		}
		else if (head.type.equals("outlet")) {
			if (tail.type.equals("light")) {
				return false;
			}
		}
		else if (head.type.equals("box")) {
			if (tail.type.equals("light")) {
				return false;
			}
		}
		else if (head.type.equals("switch")) {
			if (!tail.type.equals("light")) {
				return false;
			}
			else if (tail.type.equals("light") && !head.label.equals(lightswitch.get(tail.label))) {
				return false;
			}
		}
		else if (head.type.equals("light")) {
			if (!tail.type.equals("light")) {
				return false;
			}
			else if(!lightswitch.get(head.label).equals(lightswitch.get(tail.label))) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Graph house = new Graph();
		Scanner in = new Scanner(System.in);
		int nNode = in.nextInt();
		int nEdge = in.nextInt();
		
		String switchParent = "";
		String start = "";
		for (int i = 0; i < nNode; i++) {
			String label = in.next();
			String type = in.next();
			if (type.equals("switch")) {
				switchParent = label;
			}
			else if (type.equals("light")) {
				lightswitch.put(label, switchParent);
			}
			else if (type.equals("breaker")) {
				start = label;
			}
			Node n = new Node(label, type);
			house.addNode(n);
		}
		
		for (int i = 0; i < nEdge; i++) {
			String headLabel = in.next();
			String tailLabel = in.next();
			int weight = in.nextInt();
			Node head = new Node(headLabel, house.getType(headLabel));
			Node tail = new Node(tailLabel, house.getType(tailLabel));
			if (checkEdge(head, tail)) {
				house.addEdge(new Edge(head, tail, weight));
			}
			if (checkEdge(tail, head)) {
				house.addEdge(new Edge(tail, head, weight));
			}
		}
		
		if (house.V.isEmpty()) {
			in.close();
			return;
		}
		
		System.out.println(rawPrim(house, new Node(start, "breaker")));
		in.close();
	}
}
