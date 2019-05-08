import java.util.Stack;

/*
 * This is the implementation of the depth-first search (DFS) algorithm to find if the two vertices  
 * are connected and show the paths connecting these two vertices
 * 
 * The algorithm uses an boolean array to store if the search have already passed the vertex, and
 * another array (integer) in order to store the vertex leading to the current vertex
 * 
 */
public class Paths {
	private Graph G;				// The Graph needs processing
	private int s;					// The source vertex
	private boolean[] mark;			// The array to store if the search  have passed the vertex
	private int[] edgeTo;			// The array to store the edge leading to the vertex
	
	/*
	 * The constructor uses DFS to trace all paths from the source vertex and store them inside arrays
	 */
	public Paths(Graph G, int s)
	{
		this.G = G;
		this.s = s;
		
		mark = new boolean[G.V()];	
		edgeTo = new int[G.V()];
		
		for (int i = 0; i < G.V(); i++)
		{
			mark[i] = false;		// Initialize the marker to false
			edgeTo[i] = 0;			// Initialize the edgeTo array to 0
		}
		
		dfs(this.s);				// Depth-first search the source vertex
	}
	
	/*
	 * Return if there is a path connecting the source vertex to this vertex
	 */
	public boolean hasPathTo(int v)
	{
		return mark[v];
	}
	
	/*
	 * Return the path connecting the source vertex to vertex v
	 */
	public Iterable<Integer> pathTo(int v)
	{
		if (hasPathTo(v) == false) return null;
		
		Stack<Integer> path = new Stack<Integer>();
		
		path.push(v);
		while (edgeTo[v] != s)
		{
			v = edgeTo[v];
			path.push(v);
		}
		path.push(s);
		
		return path;
	}
	
	/*
	 * DFS recursive function
	 */
	private void dfs(int s)
	{
		mark[s] = true;
		for (int v: G.adj(s))
			if (!mark[v])
			{
				edgeTo[v] = s;
				dfs(v);
			}
	}
}
