
public class TestGraph {

	public static void main(String[] args) {
		ListGraph g = new ListGraph();
		
		Node n1 = new Node("Oslo");
		Node n2 = new Node("Stockholm");
		Node n3 = new Node("Åbo");
		Node n4 = new Node("Köpenhamn");
		Node n5 = new Node("Reykjavik");
		
		g.add(n1);
		g.add(n2);
		g.add(n3);
		g.add(n4);
		g.add(n5);
		
		g.connect(n1, n2, "Tåg", 2);
		g.connect(n1, n3, "Båt", 10);
		g.connect(n2, n3, "Tåg", 8);
		g.connect(n5, n2, "Flyg", 4);
		
		System.out.println(g.getPath(n1, n3));
	}

}
