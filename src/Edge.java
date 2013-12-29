
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
	public boolean setWeight(int weight){
		boolean hasWeight = false;
		if(this.weight == 0){
			this.weight = weight;
			hasWeight = true;
		}
		return hasWeight;
	}
	public int setNormalWeight(){
		return this.weight = weight;
	}
	public String toString(){
		return " till " + destination + " "+ " med " + name + ": " + weight;
	}
}
