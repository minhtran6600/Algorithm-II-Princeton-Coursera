import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/*
 * This is the representation of a undirected graph using adjacency-lists implementations and integer to
 * represent the vertices.
 * 
 * This graph uses Bag abstract data type to implement the adjacency-list. The adjacency of the vertices
 * will be represented as an array of Bag.
 */

public class Graph { 
	private int v, e;				// Number of vertices and edges, respectively
	private Bag<Integer>[] adj;		// Adjacency of the vertices
	
	/*
	 * Typical Graph-processing code - Static functions
	 */
	
	// Degree of a Vertex of a Graph
	public static int degree(Graph G, int v)
	{
		return G.adj[v].size();
	}
	
	// Maximum Degree of a Graph
	public static int maxDegree(Graph G)
	{
		int maxDegree = 0;
		for (int i = 0; i < G.V(); i++)
			if (maxDegree < G.adj[i].size())
				maxDegree = G.adj[i].size();
		return maxDegree;
	}
	
	// Average Degree of a Graph
	public static double averageDegree(Graph G)
	{
		return 2.0 * G.E() / G.V(); 
	}
	
	// Count self-loop
	public static int numberOfSelfLoops(Graph G)
	{
		int count = 0;
		for (int i = 0; i < G.V(); i++)
			for (int j : G.adj(i))
				if (i == j) count++;
		return count;
	}
	
	/*
	 * Create an empty Graph with V number of vertices
	 */
	@SuppressWarnings("unchecked")
	public Graph(int V)
	{
		this.v = V;		
		this.e = 0;
		adj = new Bag[v];
		
		for (int i = 0; i < v; i++) 		
			adj[i] = new Bag<Integer>();			// Initialize the adjacency list for each vertex
	}
	
	/*
	 * Create a Graph with an Input file
	 */
	@SuppressWarnings("unchecked")
	public Graph(In in)
	{
		int v = in.readInt();						// Read the number of vertices
		int e = in.readInt();						// Read the number of edges
		adj = new Bag[v];
		
		for (int i = 0; i < v; i++)
			adj[i] = new Bag<Integer>();			// Initialize the adjacency list for each vertex
		
		for (int i = 0; i < e; i++)
			addEdge(in.readInt(), in.readInt());	// Create an edge between two vertices in the file
	}
	
	/*
	 * Add an edge between two vertices v and w
	 */
	void addEdge(int v, int w)
	{
		adj[v].add(w);
		adj[w].add(v);
	}
	
	/*
	 * Return the adjacency-list of vertex v
	 */
	public Iterable<Integer> adj(int v)
	{
		return adj[v];
	}
	
	/*
	 * Return the number of vertices of the Graph
	 */
	public int V()
	{
		return v;
	}
	
	/*
	 * Return the number of edges of the Graph
	 */
	public int E()
	{
		return e;
	}
}
