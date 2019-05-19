import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/*
 * This is the representation of a directed graph using adjacency-lists implementations and integer to
 * represent the vertices.
 * 
 * This graph uses Bag abstract data type to implement the adjacency-list. The adjacency of the vertices
 * will be represented as an array of Bag.
 */

public class Digraph { 
	private int v, e;				// Number of vertices and edges, respectively
	private Bag<Integer>[] adj;		// Adjacency of the vertices
	
	/*
	 * Create an empty Graph with V number of vertices
	 */
	@SuppressWarnings("unchecked")
	public Digraph(int V)
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
	public Digraph(In in)
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
		e++;
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
	
	/*
	 * Compute the reverse of the Graph
	 */
	public Digraph reverse()
	{
		Digraph reverse = new Digraph(this.v);
		for (int i = 0; i < this.v; i++)
			for (int v: adj(i))
				reverse.addEdge(v, i);
		return reverse;
	}
}
