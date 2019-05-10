/*
 * This is the implementation of the process to check whether the Graph has an Euler Cycle using 
 * Depth-first Search. A Graph has an Euler Cycle if it satisfies two conditions (assuming that
 * there are no self-loop or parallel edges):
 * - All the graph's non-zero degree vertices are connected
 * - All the graph's vertices have even degree
 */
public class EulerCycle {
	private boolean[] mark;						// Mark the visited vertices
	private boolean hasEulerCycle;				// Store whether an Euler Cycle exist in the Graph
	
	/*
	 * Constructor that use DFS to check if the non-zero degree vertices are connected and check
	 * if the vertices' degrees are even
	 */
	public EulerCycle(Graph G) 
	{
		hasEulerCycle = true;
		
		// Check if the degrees are even
		for (int i = 0; i < G.V(); i++)
			if (Graph.degree(G, i) % 2 != 0)
				hasEulerCycle = false;
		
		int s = 0;
		while (Graph.degree(G, s) == 0)
			s++;
		
		dfs(G, s);
		for (int i = 0; i < G.V(); i++)
			if (!mark[i] && Graph.degree(G, i) != 0)
				hasEulerCycle = false;
	}
	
	/*
	 * Return whether the Graph has a Euler Cycle 
	 */
	public boolean hasEulerCycle() 
	{
		return hasEulerCycle;
	}
	
	/*
	 * Depth-first Search used to check whether the non-zero degree vertices are connected
	 */
	private void dfs(Graph G, int s)
	{
		mark[s] = true;
		for (int v: G.adj(s))
			if (!mark[v])
				dfs(G, v);
	}
}
