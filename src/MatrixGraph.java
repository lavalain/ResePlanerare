import java.util.*;
public class MatrixGraph {
		private Edge [] [] nodes;
		private Map<Node, Integer> tabell = new HashMap<Node, Integer>();
		
		public MatrixGraph(int maxAntal){
			nodes = new Edge[maxAntal][maxAntal];
		}
		public void addMatrix (Node ny){
			if(tabell.size() == nodes.length || tabell.containsKey(ny)){
				throw new IllegalStateException("Stad finns redan eller för många städer");
			}
			tabell.put(ny, tabell.size());
		}
		public void connectMatrix(Node fromNode, Node toNode, String name, int weight){
			int from = tabell.get(fromNode);
			int to = tabell.get(toNode);
			nodes [from][to] = new Edge (toNode, name, weight);
			nodes [to][from] = new Edge (fromNode, name, weight); 
		}
}
