/*
 * This is a implementation of the process to check whether a Graph is bipartite or not (a graph is
 * bipartite when the vertices can be colored into black and white and no edges connect two vertices
 * with the same color
 */
public class Bipartite {
	private boolean[] mark;				// Store if the search have passed the vertex
	private boolean[] color;			// Store the color of the vertex
	private boolean isBipartite;		// Store if the Graph is bipartite
	
	/*
	 * A constructor that parse in a Graph and check whether if the graph is bipartite
	 */
	public Bipartite(Graph G)
	{
		mark = new boolean[G.V()];		// Initialize the mark array
		color = new boolean[G.V()];		// Initialize the color array
		
		for (int i = 0; i < G.V(); i++)
		{
			if (!mark[i])
				dfs(G, i);
		}
	}
	
	/*
	 * Return whether the Graph is bipartite
	 */
	public boolean isBipartite() 
	{
		return isBipartite;
	}
	
	/*
	 * A modified version of Depth-first Search that include the coloring of the vertices
	 */
	private void dfs(Graph G, int s)
	{
		mark[s] = true;
		for (int v: G.adj(s))
			if (!mark[v])
			{
				color[v] = !color[s];
				dfs(G, v);
			}
			else if (color[v] == color[s]) isBipartite = false;
	}
	
	
}
