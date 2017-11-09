
public class Edge {
	
	private int first_vertex;
	private int second_vertex;
	private int weight;
	
	public Edge () {
		this.first_vertex = -1;
		this.second_vertex = -1;
		this.weight = -1;
	}
	
	public Edge (int first_vertex, int second_vertex, int weight) {
		this.first_vertex = first_vertex;
		this.second_vertex = second_vertex;
		this.weight = weight;
	}
	
	public int getFirstVertex () {
		return first_vertex;
	}
	
	public int getSecondVertex () {
		return second_vertex;
	}
	
	public int getWeight () {
		return weight;
	}
	
	public void setFirstVertex (int newFirstVertex) {
		this.first_vertex = newFirstVertex;
	}
	
	public void setSecondVertex (int newSecondVertex) {
		this.second_vertex = newSecondVertex;
	}
	
	public void setWeight (int newWeight) {
		this.weight = newWeight;
	}
	
}
