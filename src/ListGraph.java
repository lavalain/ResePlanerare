import java.util.*;
public class ListGraph {

	private Map <Node, List<Edge>> nodes = new HashMap<Node, List<Edge>>();//edges byt namn på listan eftersom den representerar edges

	public void add(Node ny){
		if(nodes.containsKey(ny))
			throw new IllegalArgumentException("Stad finns redan vid add");

		nodes.put(ny, new ArrayList<Edge>());
	}
	public void connect(Node from, Node to, String name, int weight){
		if (!nodes.containsKey(from)|| !nodes.containsKey(to))
			throw new NoSuchElementException("det finns ingen stad");

		Edge e1 = new Edge(to, name, weight);
		List<Edge> fromList = nodes.get(from);
		fromList.add(e1);

		Edge e2 = new Edge(from, name, weight);
		List<Edge> toList = nodes.get(to);
		toList.add(e2);	
	}
	public String toString(){
		String str = " ";
		for (Map.Entry<Node, List<Edge>> me: nodes.entrySet()) 
			str += me.getKey() +" " + me.getValue() + "\n";
		return str;
	}
	public boolean pathExists (Node from, Node to){
		Set<Node> visited = new HashSet<Node>();
		depthFirstSearch(from, visited);
		return visited.contains(to);
	}
	private void depthFirstSearch(Node n, Set<Node> visited){
		visited.add(n);
		List<Edge> edges = nodes.get(n);
		for (Edge e :edges){
			Node to = e.getDestination();
			if(!visited.contains(to))
				depthFirstSearch(to, visited);
		}
	}
	public Set<Node> getNodes(){
		return new HashSet<Node>(nodes.keySet());
	}

	public Edge getEdgeBetween (Node from, Node to) {
		if(!nodes.containsKey(from) || !nodes.containsKey(to)){
			throw new NoSuchElementException("Det finns ingen Nod");
		}
		for(Edge e :nodes.get(from)){
			if(e.getDestination().equals(to))
				return  e;
		}
		return null;
	}

	public Set<Edge> getEdgesFrom (Node from){
		if(!nodes.containsKey(from)){
			throw new NoSuchElementException("det finns inte någon sådan Nod");
		}
		return new HashSet<Edge>(nodes.get(from));			
	}

	public int setConnectionWeight(Node from, Node to, int weight){
		if(!pathExists(from, to) ||!nodes.containsKey(from) || !nodes.containsKey(to)){
			throw new NoSuchElementException("Någon av noderna finns inte, eller så finns ingen väg mellan dessa");
		}
		return 0;
			
				
			
		
	}
}

