import java.util.*;
public class ListGraph {

	private Map <Node, List<Edge>> network = new HashMap<Node, List<Edge>>();

	public void add(Node ny){
		if(network.containsKey(ny))
			throw new IllegalArgumentException("Stad finns redan vid add");

		network.put(ny, new ArrayList<Edge>());
	}
	public void connect(Node from, Node to, String name, int weight){
		if (!network.containsKey(from)|| !network.containsKey(to))
			throw new NoSuchElementException("det finns ingen stad");

		Edge e1 = new Edge(to, name, weight);
		List<Edge> fromList = network.get(from);
		fromList.add(e1);

		Edge e2 = new Edge(from, name, weight);
		List<Edge> toList = network.get(to);
		toList.add(e2);	
	}
	public String toString(){
		String str = " ";
		for (Map.Entry<Node, List<Edge>> me: network.entrySet()) 
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
		for (Edge e : network.get(n))
			if(!visited.contains(e.getDestination()))
				depthFirstSearch(e.getDestination(), visited);
	}
	public Set<Node> getNodes(){
		return new HashSet<Node>(network.keySet());
	}
	public Edge getEdgeBetween (Node from, Node to) {
		if(!network.containsKey(from) || !network.containsKey(to)){
			throw new NoSuchElementException("Det finns ingen Nod");
		}
		for(Edge e :network.get(from)){
			if(e.getDestination().equals(to))
				return  e;
		}//behövs???
		return null;
	}

	public Set<Edge> getEdgesFrom (Node from){
		if(!network.containsKey(from)){
			throw new NoSuchElementException("det finns inte någon sådan Nod");
		}
		return new HashSet<Edge>(network.get(from));			
	}
	public void depthFirstSearch2(Node where, Node fromWhere, Set<Node>visited, Map<Node,Node>via){
		visited.add(where);
		via.put(where, fromWhere);
		for(Edge e : network.get(where))
			if(!visited.contains(e.getDestination()))
				depthFirstSearch2(e.getDestination(),where, visited, via);
	}
	public List<Edge> getPath(Node from, Node to){
		Set<Node> visited = new HashSet<Node>();
		Map<Node, Node> via= new HashMap<Node, Node>();
		depthFirstSearch2(from, null, visited, via);
		LinkedList<Edge> path = new LinkedList<Edge>();
		Node whereTo = to;
		while(whereTo != from){
			Node whereFrom = via.get(whereTo);
			Edge e = getEdgeBetween(whereFrom, whereTo);
			path.addFirst(e);
			whereTo = whereFrom;
		}
		return path;	
	}
	public Edge setConnectionWeight(Node from, Node to, int weight){
		if(!pathExists(from, to) ||!network.containsKey(from) || !network.containsKey(to)){
			throw new NoSuchElementException("Någon av noderna finns inte, eller så finns ingen väg mellan dessa");
		}
		for(Edge e : network.get(from)){
			if(e.getDestination().equals(to) && e.getWeight()== (weight))
				return e;
		}
		return null;

		//denna funkar inte men nu ger ja upp!!!!




	}
}

