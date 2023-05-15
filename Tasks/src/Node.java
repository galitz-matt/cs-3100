import java.util.Objects;

public class Node {
	
	public String name;
	public char color;
	
	public Node(String n) {
		name = n;
		color = 'w';
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
		return name;
	}
}