import java.util.*;
public class ListGraph {

	private Map <Node, List<Edge>> nodes = new HashMap<Node, List<Edge>>();

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
	public Edge getEdgeBetween (Node from, Node to, String name) {
			if(!nodes.containsKey(from) || !nodes.containsKey(to)){
				throw new NoSuchElementException("Det finns ingen Nod");
			}
		for(Edge e :nodes.get(from)){
			if(e.getDestination().equals(to) && e.getName().equals(name))
				return  e;
		}
		return null;
	}
	public Set<Edge> getEdgesFrom(Node from){
		return new HashSet<Edge>(nodes.entrySet());
	}
	//public int setConnectionWeight(Node n1, Node n2, int weight){
		//if (!nodes.containsKey(n1) || !nodes.containsKey(n2)|| pathExists(Node n1, Node n2)){
			//throw new NoSuchElementException("Det finns ingen Nod, eller båge");
			// hur letar man fram en båge? och bestämmer vikten på bågen, man tar två noder och sätter ut bågen. sen tar man bågen och ändrar den med setWeight metoden.
		//}
		//return e;
	//}
}
