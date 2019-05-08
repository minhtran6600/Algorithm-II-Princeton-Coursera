import java.util.LinkedList;
import java.util.Stack;

/*
 * This is the implementation of the Breadth-first search (BFS) algorithm to find if the two vertices
 * are connected and show the paths connecting the two vertices
 * 
 * This algorithm uses a queue in order to store the vertices visited, as well as a boolean array to 
 * mark these vertices and a integer array to store the edge leading to each of these vertices
 */

public class PathsBFS {
	private int s;					// The source vertex
	private boolean[] mark;			// The array to store if the search  have passed the vertex
	private int[] edgeTo;			// The array to store the edge leading to the vertex
	
	
	/*
	 * The constructors use BFS to trace all shortest paths from the source vertex and store them in 
	 * the arrays
	 */
	public PathsBFS(Graph G, int s)
	{
		this.s = s;
		
		mark = new boolean[G.V()];
		edgeTo = new int[G.V()];
		
		for (int i = 0; i < G.V(); i++)
		{
			mark[i] = false;		// Initialize the marker to false
			edgeTo[i] = 0;			// Initialize the edge to 0
		}
		
		LinkedList<Integer> visited = new LinkedList<Integer>();	// The Queue to store the vertices visited
		visited.add(this.s);
		
		while (!visited.isEmpty())
		{
			int v = visited.poll();
			for (int w: G.adj(v))
				if (!mark[w])
				{
					mark[w] = true;
					edgeTo[w] = v;
					visited.add(w);
				}
		}
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
}
