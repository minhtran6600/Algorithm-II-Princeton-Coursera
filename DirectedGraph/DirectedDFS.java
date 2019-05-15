/*
 * Implementation of Depth-first Search for Directed Graph
 */
public class DirectedDFS {
	private boolean[] mark;				// Marks visited vertices
	
	/*
	 * Constructor marks vertices reachable from s
	 */
	DirectedDFS(Digraph G, int s)
	{
		mark = new boolean[G.V()];
	}
}
