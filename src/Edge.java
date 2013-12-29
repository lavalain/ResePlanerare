
public class Edge {
	private Node destination;
	private String namn;
	private int vikt;
	
	public Edge (Node destination, String namn, int vikt){
		if (vikt < 0)
			throw new IllegalArgumentException("negativ vikt");
		this.destination = destination;
		this.namn = namn;
		this.vikt = vikt;
		
	}
	public Node getDestination(){
		return destination;
	}
	public String getNamn(){
		return namn;
	}
	public int getVikt(){
		return vikt;
	}
	public String toString(){
		return " till " + destination + " "+ " med " + namn + ": " + vikt;
	}
}
