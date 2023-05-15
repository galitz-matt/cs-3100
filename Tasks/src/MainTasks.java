import java.util.ArrayList;
import java.util.Scanner;

public class MainTasks {
	
	static ArrayList<Node> topoList = new ArrayList<Node>();
	
	public static void organize(Graph g, Node u) {
		u.color = 'g';
		for (Node v : g.adj.get(u)) {
			if (v.color == 'w') {
				organize(g, v);
			}
		}
		u.color = 'b';
		if (!topoList.contains(u)) {
			topoList.add(0, u);
		}
	}
	
	public static void main(String[] args) {
		Graph g = new Graph();
		Scanner in = new Scanner(System.in);
		String[] ints = in.nextLine().split(" ");
		int n = Integer.parseInt(ints[0]);
		int e = Integer.parseInt(ints[1]);
		
		for (int i = 0; i < n; i++) {
			g.addNode(in.nextLine());
		}
		
		for (int i = 0; i < e; i++) {
			String[] nodes = in.nextLine().split(" ");
			g.addEdge(nodes[0], nodes[1]);
		}
		in.close();
		
		for (Node start : g.adj.keySet()) {
			if (topoList.size() == n) {
				break;
			}
			organize(g, start);
		}
		
		if (topoList.size() == n) {
			for (int i = 0; i < topoList.size() - 1; i++) {
				System.out.print(topoList.get(i) + " ");
			}
			System.out.print(topoList.get(topoList.size() - 1));
		}
		else {
			System.out.println("IMPOSSIBLE");
		}
	}
	
}