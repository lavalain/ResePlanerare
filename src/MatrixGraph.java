import java.util.*;
public class MatrixGraph {
		private Edge [] [] data;
		private Map<Node, Integer> tabell = new HashMap<Node, Integer>();
		
		public MatrixGraph(int maxAntal){
			data = new Edge[maxAntal][maxAntal];
		}
		public void addMatrix (Node ny){
			if(tabell.size() == data.length || tabell.containsKey(ny)){
				throw new IllegalStateException("Stad finns redan eller för många städer");
			}
			tabell.put(ny, tabell.size());
		}
		public void connectMatrix(Node fromNode, Node toNode, String name, int weight){
			int from = tabell.get(fromNode);
			int to = tabell.get(toNode);
			data [from][to] = new Edge (toNode, name, weight);
			data [to][from] = new Edge (fromNode, name, weight); 
		}
}
