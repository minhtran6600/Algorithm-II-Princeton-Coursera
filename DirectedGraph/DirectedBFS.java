import java.util.LinkedList;
import java.util.Stack;

/*
 * Implementation of the Breadth-first Search for Directed Graph
 */
public class DirectedBFS {
	private boolean mark[];				// Marks visited vertex
	private int edgeTo[];				// Marks the edges
	private int distTo[];				// The distance from the vertex s
	private int s;						// The source vertex
	
	/*
	 * Constructor that implements the BFS
	 */
	public DirectedBFS(Digraph G, int s)
	{
		mark = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		
		this.s = s;
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		queue.add(s);
		mark[s] = true;
		while (!queue.isEmpty())
		{
			int v = queue.removeFirst();
			for (int w: G.adj(v))
				if (!mark[v])
				{
					mark[v] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
		}
	}
	
	/*
	 * return if there is a path from the source vertex
	 */
	public boolean hasPathTo(int v)
	{
		return mark[v];
	}
	
	/*
	 * Return the distance from the source
	 */
	public int distTo(int v)
	{
		return distTo[v];
	}
	
	/*
	 * return the shortest path from the source vertex
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
