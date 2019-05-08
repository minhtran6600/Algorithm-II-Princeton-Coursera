/*
 * This is the implementation of the Connected Components using Depth-first Search in order to answer
 * the connectivity queries in constant time, without using adjacency-matrix
 */
public class CC {
	private Graph G;			// The Graph needs processing
	private boolean[] mark;		// Store if the search has passed the vertex
	private int[] id;			// ID of connected components
	private int count;			// Number of connected components
	
	/*
	 * Constructor uses DFS to trace all paths of every vertex and store the id of their connected components
	 */
	public CC(Graph G)
	{
		this.G = G;
		this.count = 0;
		
		mark = new boolean[G.V()];
		id = new int[G.V()];
		
		for (int i = 0; i < G.V(); i++)
		{
			mark[i] = false;
			id[i] = 0;
		}
		
		for (int i = 0; i < G.V(); i++)
			if (!mark[i])
			{
				dfs(i);
				count++;
			}
	}
	
	/*
	 * Return the number of connected components
	 */
	public int count() 
	{
		return count;
	}
	
	/*
	 * Return whether two vertices connected (in the same connected components)
	 */
	public boolean connected(int v, int w)
	{
		return id[v] == id[w];
	}
	
	/*
	 * DFS recursive function
	 */
	private void dfs(int s)
	{
		mark[s] = true;
		id[s] = count;
		for (int v: G.adj(s))
			if (!mark[v])
				dfs(v);
	}
}
