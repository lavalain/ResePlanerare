import java.util.*;

import Graph.Edge;
import Graph.Node;
public class MatrixGraph {
		private Edge [] [] network;
		private Map<Node, Integer> tabell = new HashMap<Node, Integer>();
		
		public MatrixGraph(int maxAntal){
			network = new Edge[maxAntal][maxAntal];
		}
		public void add(Node ny){
			if(tabell.size() == network.length || tabell.containsKey(ny)){
				throw new IllegalStateException("Stad finns redan eller för många städer");
			}
			tabell.put(ny, tabell.size());
		}
		public void connect(Node fromNode, Node toNode, String name, int weight){
			int from = tabell.get(fromNode);
			int to = tabell.get(toNode);
			network [from][to] = new Edge (toNode, name, weight);
			network [to][from] = new Edge (fromNode, name, weight); 
		}
}
