package Graph;

import java.util.*;
public class ListGraph <T> {

	private Map <T , List<Edge <T>>> network = new HashMap<T, List<Edge <T>>>();
	private T minNode;
	
	public void add(T ny){
		if(network.containsKey(ny))
			throw new IllegalArgumentException("Stad finns redan vid add");

		network.put(ny, new ArrayList<Edge <T>>());
	}
	
	public void connect(T from, T to, String name, int weight){
		if (!network.containsKey(from)|| !network.containsKey(to))
			throw new NoSuchElementException("det finns ingen stad");

		Edge <T> e1 = new Edge <T> (to, name, weight);
		List<Edge <T>> fromList = network.get(from);
		fromList.add(e1);

		Edge <T> e2 = new Edge <T> (from, name, weight);
		List<Edge <T>> toList = network.get(to);
		toList.add(e2);	
	}
	public String toString(){//???
		String str = " ";
		for (Map.Entry<T, List<Edge <T>>> me: network.entrySet()) 
			str += me.getKey() +" " + me.getValue() + "\n";
		return str;
	}
	public boolean pathExists (T from, T to){
		Set<T> visited = new HashSet<T>();
		depthFirstSearch(from, visited);
		return visited.contains(to);
	}
	private void depthFirstSearch(T n, Set<T> visited){//????
		visited.add(n);
		for (Edge <T> e : network.get(n))
			if(!visited.contains(e.getDestination()))
				depthFirstSearch(e.getDestination(), visited);
	}
	public Set<T> getNodes(){//????
		return new HashSet<T>(network.keySet());
	}
	public Edge <T> getEdgeBetween (T from, T to) {
		if(!network.containsKey(from) || !network.containsKey(to)){
			throw new NoSuchElementException("Det finns ingen Nod");
		}
		for(Edge <T> e :network.get(from)){
			if(e.getDestination().equals(to))
				return  e;
		}
		return null;
	}

	public Set<Edge <T>> getEdgesFrom (T from){
		if(!network.containsKey(from)){
			throw new NoSuchElementException("det finns inte någon sådan Nod");
		}
		return new HashSet<Edge <T>> (network.get(from));			
	}
	public void depthFirstSearch2(T where, T fromWhere, Set<T>visited, Map<T,T>via){
		visited.add(where);
		via.put(where, fromWhere);
		for(Edge <T> e : network.get(where))
			if(!visited.contains(e.getDestination()))
				depthFirstSearch2(e.getDestination(),where, visited, via);
	}
	public LinkedList<Edge <T>> getPath(T from, T to){//?????
		Set<T> visited = new HashSet<T>();
		Map<T, T> via = new HashMap<T, T>();
		depthFirstSearch2(from, null, visited, via);

		LinkedList<Edge <T>> path = new LinkedList<Edge <T>>();
		T whereTo = to;
		while(whereTo != from){
			T whereFrom = via.get(whereTo);
			Edge <T> e = getEdgeBetween(whereFrom, whereTo);
			path.addFirst(e);
			whereTo = whereFrom;
		}
		return path;	
	}
	public void setConnectionWeight(T from, T to, int weight){
		if(!pathExists(from, to) ||!network.containsKey(from) || !network.containsKey(to)){
			throw new NoSuchElementException("Någon av noderna finns inte, eller så finns ingen väg mellan dessa");
		}
		Edge <T> e = getEdgeBetween(from, to);
		e.setWeight(weight);
		e = getEdgeBetween(to, from);
		e.setWeight(weight);
	}
	public boolean isEmpty(){
		return network.isEmpty();
	}
	public void clear(){
		network.clear();
	}

	public LinkedList<Edge <T>> bestWay(T from, T to){// används inte men blev fel när jag raderade.
		Map<T, Integer> tid = new HashMap<T, Integer>();
		Map<T, Boolean> snabbast = new HashMap <T, Boolean>();
		Map<T, T> viadest = new HashMap <T, T>();
		LinkedList<Edge <T>> path = new LinkedList<Edge <T>>();
		Set<T> s = getNodes();
		
		for (T temp : s ){
			tid.put(temp, Integer.MAX_VALUE);
			snabbast.put(temp, false);
			viadest.put(temp, null);
		}
		viadest.put(from, from);
		tid.put(from, 0);
		snabbast.put(from, true);
		T aktuell = from;


		while(snabbast.get(to) != true){

			minNode = null;
			for (T temp : s){

				if(minNode == null && !snabbast.get(temp)){
					minNode = temp;
					
				}
				for(Edge <T> e : getEdgesFrom(aktuell)){
					if(!snabbast.get(e.getDestination())){
						tid.put(e.getDestination(), e.getWeight() + tid.get(aktuell));
					}
				}
				if((minNode != null) && tid.get(temp) < (tid.get(minNode)) && !snabbast.get(temp)){
					System.out.println("Detta är temp: " + temp + "Detta är minNode: "+ minNode);
					minNode = temp;

					System.out.println( "Temp " + temp +"efter den jobbiga ifsatsen kommer min nod: " + minNode);

				}
			}

			System.out.println("Den nya minNode är = " + minNode);
			snabbast.put(minNode, true);
			viadest.put(minNode, aktuell);
			aktuell = minNode; 

			//sätt i slutet av loopen

			for (Edge <T> e : getEdgesFrom(aktuell)){
				if(!snabbast.get(e.getDestination())){	
					tid.put(e.getDestination(), e.getWeight() + tid.get(aktuell));
					



					System.out.println("Aktuella noden: " + aktuell + " " + e.getDestination()+ "  " + tid.get(e.getDestination()));
				}
			}

		}
		for ( T n : s){//utskriftsloop
			System.out.println(n + "\t\t\t" + tid.get(n) + "\t" + snabbast.get(n) + "\t" + viadest.get(n));
		}
		aktuell = to;
		T nästa;
		while(aktuell != viadest.get(aktuell)){
			nästa = viadest.get(aktuell);
			System.out.println(" nästa " + nästa + " Aktuell " + aktuell);
			Edge <T> e = getEdgeBetween(nästa, aktuell);
			path.addFirst(e);
			aktuell = nästa;
		}
		return path ;
	}
	public LinkedList<Edge <T>> fastPath(T from, T to){
	
		if(!pathExists(from, to)){
			throw new NoSuchElementException("Ingen väg finns mellan dessa platser");
		}
			Map< T, Integer> kortasteTiden = new HashMap< T, Integer>();
			Map< T, Boolean> omKortaste = new HashMap <T, Boolean>();
			Map< T, T> viaDest = new HashMap <T, T>();
			Set<T> städer = getNodes();


			for( T temp : städer){
				kortasteTiden.put(temp, Integer.MAX_VALUE);
				omKortaste.put(temp, false);
				viaDest.put(temp, null);
			}
			kortasteTiden.put(from, 0);
			omKortaste.put(from, true);
			
			dijkstra(from, to, kortasteTiden, omKortaste, viaDest);
			
			LinkedList <Edge <T>> path = new LinkedList <Edge <T>>();
			T goTo = to;
			while(goTo != from){
				T goFrom = viaDest.get(goTo);
				Edge <T> e = getEdgeBetween(goFrom, goTo);
				System.out.println(" Hej edge");
				path.addFirst(e);
				goTo = goFrom;
			}
			return path;
		}
	
	private void dijkstra(T start, T slut, Map<T,Integer> costMap, Map<T,Boolean> decidedMap, Map<T,T> viaMap){
		
		T currentNode = start;
		
		if(!decidedMap.get(slut)){
			for(Edge <T> e : getEdgesFrom(currentNode)){
				if( (costMap.get(currentNode)+e.getWeight())< costMap.get(e.getDestination())){
					costMap.put(e.getDestination(), costMap.get(currentNode)+e.getWeight());
					viaMap.put(e.getDestination(),currentNode);
				}
			}
			Integer lowestCost = Integer.MAX_VALUE;
			T lowNode = null;
			
			for( T node: costMap.keySet()){
				if(costMap.get(node)< lowestCost && !decidedMap.get(node)){
				lowestCost = costMap.get(node);
				lowNode = node;
				}
			}
			if(lowNode != null){
				decidedMap.put(lowNode, true);
				dijkstra(lowNode, slut, costMap, decidedMap, viaMap);
			}
			
		}
		
		
	}
	
	

}

	