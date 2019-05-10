/*
 * This is an implementation to check whether there is a cycle in the Graph
 */
public class Cycle {
	private boolean[] mark;				// Store if the search have passed the vertex
	private boolean hasCycle;			// Store if the Graph has a cycle
	
	/*
	 * A constructor that find a cycle in the Graph using Depth-first Search
	 */
	public Cycle(Graph G) 
	{
		mark = new boolean[G.V()];
		
		for (int i = 0; i < G.V(); i++)
			if (!mark[i])	
				dfs(G, i, i);
	}
	
	/*
	 * Return whether there exist a cycle in a Graph
	 */
	public boolean hasCycle()
	{
		return hasCycle;
	}
	
	/*
	 * A modified Depth-first Search that check a cycle by checking if a neighbor have already been marked
	 * and whether that is not the original connecting vertex 
	 */
	private void dfs(Graph G, int s, int v)
	{
		mark[s] = true;
		
		for (int w: G.adj(s))
			if (!mark[w])
				dfs(G, w, s);
			else if (w != v) hasCycle = true;
	}
}
