
public class Edge {
	
	public Node[] edge = new Node[2];
	public int weight;
	
	public Edge(Node head, Node tail, int w) {
		edge = new Node[] {head, tail};
		weight = w;
	}
	
	public Node head() {
		return edge[0];
	}
	
	public Node tail() {
		return edge[1];
	}
	
	public boolean equals(Edge e) {
		return head().equals(e.head()) && tail().equals(e.tail()) && weight == e.weight;
	}
	
	@Override
	public String toString() {
		return "(" + head().label + "-" + tail().label + ")";
	}
}
