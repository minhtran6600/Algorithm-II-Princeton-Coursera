import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
/*
 * Implementation of directed Graph using weighted edges
 */
public class EdgeWeightedDigraph {
	private int V, E;							// Number of vertices and edges, respectively
	private Bag<DirectedEdge> edges, adj[];		// The edges and adjacency-list of each vertex
	
	/*
	 * Constructor that build an empty Graph with V vertices
	 */
	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V)
	{
		this.V = V;
		this.E = 0;
		edges = new Bag<DirectedEdge>();
		adj = new Bag[V];
		
		for (int i = 0; i < V; i++)
			adj[i] = new Bag<DirectedEdge>();
	}
	
	/*
	 * Constructor that build a Graph from an input file
	 */
	public EdgeWeightedDigraph(In in)
	{
		this.V = in.readInt();
		this.E = 0;
		
		int e = in.readInt();
		for (int i = 0; i < e; i++)
			addEdge(new DirectedEdge(in.readInt(), in.readInt(), in.readDouble()));
	}
	
	/*
	 * Add a directed edge into the graph
	 */
	public void addEdge(DirectedEdge e)
	{
		adj[e.from()].add(e);
		edges.add(e);
		this.E++;
	}
	
	/*
	 * Return the adjacency-list of vertex v
	 */
	public Iterable<DirectedEdge> adj(int v)
	{
		return adj[v];
	}
	
	/*
	 * Return all the edges of the Graph
	 */
	public Iterable<DirectedEdge> edges()
	{
		return edges;
	}
	
	/*
	 * Return the number of vertices
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
		return E;
	}
}
