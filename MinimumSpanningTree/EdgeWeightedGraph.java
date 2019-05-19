import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

/*
 * Implementation of Undirected Graph that contains weighted-edge
 */
public class EdgeWeightedGraph {
	private int V, E;					// number of vertices and edges, respectively
	private Bag<Edge>[] adj;			// the adjacency-list representation
	private Bag<Edge> edges;			// the edges of the Bag
	
	/*
	 * Constructor that takes in the number of vertex
	 */
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V)
	{
		this.V = V;
		adj = new Bag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new Bag<Edge>();
	}
	
	/*
	 * Constructor that takes in an input file
	 */
	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(In in)
	{
		this.V = in.readInt();
		
		adj = new Bag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new Bag<Edge>();
		
		while (!in.isEmpty())
			addEdge(new Edge(in.readInt(), in.readInt(), in.readDouble()));
	}
	
	/*
	 * Add an edge to the Graph
	 */
	public void addEdge(Edge e)
	{
		this.E++;
		int v = e.either();
		int w = e.other(v);
		edges.add(e);
		adj[v].add(e);
		adj[w].add(e);
	}
	
	/*
	 * Return all the edges incident to vertex v
	 */
	public Iterable<Edge> adj(int v)
	{
		return adj[v];
	}
	
	/*
	 * Return all the edges in the Graph
	 */
	public Iterable<Edge> edges()
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
