import java.util.Objects;

public class Node {
	
	public String label;
	public String type;
	
	public Node(String l, String t) {
		label = l;
		type = t;
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, type);
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
		return Objects.equals(label, other.label) && Objects.equals(type, other.type);
	}
	
	public String toString() {
		return label;
	}
	
}
