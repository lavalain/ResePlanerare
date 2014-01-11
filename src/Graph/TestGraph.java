package Graph;


public class TestGraph {

	public static void  main(String[] args) {
		ListGraph <Node>  g = new ListGraph <Node>();
		
		Node n1 = new Node("1: Oslo");
		Node n2 = new Node("2: Stockholm");
		Node n3 = new Node("3: �bo");
		Node n4 = new Node("4: K�penhamn");
		Node n5 = new Node("5: Reykjavik");
		Node n6 = new Node("6: Grisslehamn");
		
		g.add(n1);
		g.add(n2);
		g.add(n3);
		g.add(n4);
		g.add(n5);
		g.add(n6);
		
		g.connect(n1, n6, "T�g", 2);
		g.connect(n1, n2, "B�t", 10);
		g.connect(n2, n3, "T�g", 8);
		g.connect(n3, n5, "Flyg", 4);
		g.connect(n5, n4, "b�t", 8);
		g.connect(n4, n2, "tanke", 1);
		g.connect(n4, n6, "bil", 9);
		
		//g.setConnectionWeight(n1, n2, 5);
		
		//g.bestWay(n1,n3);
		System.out.println(g.fastPath(n2, n6));
	}

}
