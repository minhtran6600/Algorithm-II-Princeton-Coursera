import edu.princeton.cs.algs4.Bag;
/*
 * Implementation of Flow Network using Flow-edges
 */
public class FlowNetwork {
	private final int V;
	private Bag<FlowEdge>[] adj;
	private Bag<FlowEdge> edges;
	
	/*
	 * Create an empty Flow Network with V vertices
	 */
	@SuppressWarnings("unchecked")
	public FlowNetwork(int V)
	{
		this.V = V;
		edges = new Bag<FlowEdge>();
		adj = new Bag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new Bag<FlowEdge>();
	}
	
	/*
	 * Add a flow-edge
	 */
	public void addEdge(FlowEdge e)
	{
		int v = e.from();
		int w = e.to();
		adj[v].add(e);
		adj[w].add(e);
		edges.add(e);
	}
	
	/*
	 * Return the adjacency of a vertex
	 */
	public Iterable<FlowEdge> adj(int v)
	{
		return adj[v];
	}
	
	/*
	 * Return the Flow-edges of the Flow Network
	 */
	public Iterable<FlowEdge> edges()
	{
		return edges;
	}
	
	/*
	 * Return the number of vertex
	 */
	public int V()
	{
		return V;
	}
	
	/*
	 * Return the number of edges
	 */
	public int E()
	{
		return edges.size();
	}
}
