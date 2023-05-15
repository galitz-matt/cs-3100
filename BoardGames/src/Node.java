import java.util.Comparator;
import java.util.Objects;

public class Node implements Comparable<Node> {
	
	public int name;
	public char color;
	
	public Node(int n) {
		name = n;
		color = 'w';
	}
	
	public int compareTo(Node n) {
		if (name > n.name) {
			return 1;
		}
		else if (name < n.name) {
			return -1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public String toString() {
		return String.valueOf(name);
	}
}

class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		return o1.compareTo(o2);
	}
	
}
