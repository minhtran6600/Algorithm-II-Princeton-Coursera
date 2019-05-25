/*
 * Implementation of Directed Flow-edge
 */
public class FlowEdge {
	private final int v, w;
	private final double capacity;
	private double flow;
	
	/*
	 * Flow-edge constructor
	 */
	public FlowEdge(int v, int w, double capacity)
	{
		this.v = v;
		this.w = w;
		this.capacity = capacity;
	}
	
	/*
	 * Return the origin vertex
	 */
	public int from()
	{
		return v;
	}
	
	/*
	 * Return the end vertex
	 */
	public int to()
	{
		return w;
	}
	
	/*
	 * Return the capacity of the vertex
	 */
	public double capacity()
	{
		return capacity;
	}
	
	/*
	 * Return the flow of the vertex
	 */
	public double flow()
	{
		return flow;
	}
	
	/*
	 * Return the other vertex
	 */
	public int other(int v)
	{
		if (v == this.v) return this.w;
		else 			 return this.v;
	}
	
	/*
	 * Return the residual flow capacity
	 */
	public double residualCapacityTo(int v)
	{
		if (v == this.v) return flow;
		else if (v == this.w) return capacity - flow;
		else throw new IllegalArgumentException();
	}
	
	/*
	 * Add the residual flow
	 */
	public void addResidualFlowTo(int v, double delta)
	{
		if (v == this.v) flow -= delta;
		else if (v == this.w) flow += delta;
		else throw new IllegalArgumentException();
	}
}
