import java.util.*;
public class ListGraph {
	
	private Map <Node, List<Edge>> nodes = new HashMap<Node, List<Edge>>();
	
	public void add(Node ny){
		if(nodes.containsKey(ny))
			throw new IllegalArgumentException("Stad finns redan vid add");
		
			nodes.put(ny, new ArrayList<Edge>());
	}
	public void connect(Node from, Node to, String namn, int vikt){
		if (!nodes.containsKey(from)|| !nodes.containsKey(to))
			throw new NoSuchElementException("det finns ingen stad");
		
		Edge e1 = new Edge(to, namn, vikt);
		List<Edge> fromList = nodes.get(from);
		fromList.add(e1);
		
		Edge e2 = new Edge(from, namn, vikt);
		List<Edge> toList = nodes.get(to);
		toList.add(e2);	
	}
	public String toString(){
		String str = " ";
		for (Map.Entry<Node, List<Edge>> me: nodes.entrySet()) 
			str += me.getKey() +" " + me.getValue() + "\n";
		return str;
	}
}
