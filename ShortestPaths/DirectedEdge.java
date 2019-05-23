/*
 * Implementation of Weighted Directed Edge
 */
public class DirectedEdge {
	private final int v, w;			// Edge's vertices
	private final double weight;	// Edge's weight
	
	/*
	 * Constructor that create a weighted edge e -> w
	 */
	
	public DirectedEdge(int v, int w, double weight)
	{
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	/*
	 * Return the head of the edge
	 */
	public int from()
	{
		return v;
	}
	
	/*
	 * Return the tail of the edge
	 */
	public int to()
	{
		return w;
	}
	
	/*
	 * Return the weight of the edge
	 */
	public double weight()
	{
		return weight;
	}
}
