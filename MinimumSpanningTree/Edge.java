/*
 * Implementation of weighted edge
 */
public class Edge implements Comparable<Edge> {
	private final int v, w;					// The vertices of the edge
	private final double weight;			// The weight of the edge
	
	/*
	 * Constructor of the Edge
	 */
	public Edge(int v, int w, double weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	/*
	 * Return either of the vertices
	 */
	public int either()
	{
		return v;
	}
	
	/*
	 * Return the other vertex
	 */
	public int other(int v)
	{
		if (v == this.v) return this.w;
		else return this.v;
	}
	
	/*
	 * Return the weight of the vertex
	 */
	public double weight()
	{
		return weight;
	}
	
	/*
	 * Compare the weight to another Edge
	 */
	public int compareTo(Edge that)
	{
		if (this.weight > that.weight)	    return +1;
		else if (this.weight < that.weight) return -1;
		else return 0;
	}
}
