import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class MainBoardGames {
	
	public static HashMap<Node, Boolean> dangers = new HashMap<Node, Boolean>();
	
	public static void findPathHelp(Graph g, Node s, Node u, ArrayList<Node> localPath) {
		System.out.println(localPath);
		if (dangers.get(s)) {
			return;
		}
		if (s.name == u.name) {
			System.out.println(getPathString(localPath));
			return;
		}
		for (Node v : g.adj.get(s)) {
			if (!localPath.contains(v)) {
				localPath.add(v);
				findPathHelp(g, v, u, localPath);
				localPath.remove(v);
			}
		}
	}
	
	public static void findPath(Graph g, Node start, Node end) {
		ArrayList<Node> path = new ArrayList<Node>();
		path.add(start);
		findPathHelp(g, start, end, path);
	}
	
	public static String getPathString(ArrayList<Node> arr) {
		String s = "";
		
		for (int i = 0; i < arr.size() - 1; i++) {
			s += arr.get(i).name + "-";
		}
		s += arr.get(arr.size() - 1).name;
		
		return s;
	}
	
	public static void main(String[] args) {
		Graph g = new Graph();
		Scanner inInt = new Scanner(System.in);
		int numInters = inInt.nextInt();
		int numRoads = inInt.nextInt();
		
		for (int i = 0; i < numRoads; i++) {
			int start = inInt.nextInt();
			int end = inInt.nextInt();
			g.addNode(start);
			g.addNode(end);
			g.addEdge(start, end);
			g.addEdge(end, start);
			dangers.put(new Node(start), false);
			dangers.put(new Node(end), false);
		}
		
		g.sortAllEdges();
		
		int numDangers = inInt.nextInt();
		for (int i = 0; i < numDangers; i++) {
			int dangerName = inInt.nextInt();
			if (dangerName == numInters - 1) {
				inInt.close();
				return;
			}
			dangers.put(new Node(dangerName), true);
		}
		
		if (numInters <= 1) {
			inInt.close();
			return;
		}
		
		findPath(g, new Node(0), new Node(numInters - 1));
		
		inInt.close();
	}
}
