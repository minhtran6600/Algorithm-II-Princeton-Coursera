
/*
 * Implementation of Strong Component that use the Kosaraju-Sharir algorithm
 * 	- phase 1: Compute the reverse postorder of the reverse of graph G
 *  - phase 2: Use DFS in the order of the reverse postorder computed earlier
 */
public class StrongComponent {
	private boolean marked[];			// marks visited vertex
	private int scc[];					// number the strong connected components
	private int count;					// count the number of strong connected components
	
	/*
	 * Implementation of dfs in search of Strong Component
	 */
	private void dfs(Digraph G, int v)
	{
		marked[v] = true;
		scc[v] = count;
		for (int w: G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}
	
	/*
	 * Constructor that compute the Strong Connected Component
	 */
	public StrongComponent(Digraph G)
	{
		Digraph G_r = G.reverse();
		Topological tp = new Topological(G_r);
		
		count = 0;
		for (int v: tp.sortedList())
			if (!marked[v])
			{
				dfs(G, v);
				count++;
			}
	}
	
	/*
	 * Check if two component are strongly connected
	 */
	public boolean isConnected(int v, int w)
	{
		return scc[v] == scc[w];
	}
	
	/*
	 * Return the number of strong components
	 */
	public int numOfComp()
	{
		return count + 1;
	}
}
