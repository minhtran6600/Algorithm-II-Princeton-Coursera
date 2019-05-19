import java.util.Stack;

/*
 * Implementation of Depth-first Search for Directed Graph
 */

public class DirectedDFS {
	private boolean[] mark;				// Marks visited vertices
	private int[] edgeTo;				// Marks the nearest edge to each vertex
	private int s;						// The source vertex
	
	/*
	 * Implementation of DFS
	 */
	private void dfs(Digraph G, int v)
	{
		mark[v] = true;
		for (int w: G.adj(v))
			if (!mark[v])
			{
				edgeTo[w] = v;
				dfs(G, w);
			}
	}
	
	/*
	 * Constructor marks vertices reachable from s
	 */
	public DirectedDFS(Digraph G, int s)
	{
		this.s = s;
		mark = new boolean[G.V()];
		edgeTo = new int[G.V()];
		dfs(G,s);
	}
	
	/*
	 * Return if there is a path from source vertex
	 */
	public boolean hasPathTo(int v)
	{
		return mark[v];
	}
	
	/*
	 * Return one path (not the shortest) from the source to the vertex
	 */
	public Iterable<Integer> pathTo(int v)
	{
		if (!mark[v]) return null;
		Stack<Integer> path = new Stack<Integer>();
		int current = v;
		path.push(v);
		
		while (edgeTo[current] != this.s)
		{
			current = edgeTo[current];
			path.push(current);
		}
		
		path.push(this.s);
		return path;
	}
}
