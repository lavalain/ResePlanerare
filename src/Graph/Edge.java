package Graph;


public class Edge {
	private Node destination;
	private String name;
	private int weight;
	
	public Edge (Node destination, String name, int weight){
		if (weight < 0)
			throw new IllegalArgumentException("negativ vikt");
		this.destination = destination;
		this.name = name;
		this.weight = weight;
		
	}
	public Node getDestination(){
		return destination;
	}
	public String getName(){
		return name;
	}
	public int getWeight(){
		return weight;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}
	public String toString(){
		return " till " + destination + " "+ " med " + name + ": " + weight;
	}
}
