import edu.princeton.cs.algs4.Stack;

/*
 * Implementation of Topological sort, taking that the input graph is DAG
 */
public class Topological {
	private boolean[] marked;		// marks visited nodes
	private Stack<Integer> tp;		// Topologically-sorted list
	
	/*
	 * Implementation of Depth-first Search
	 */
	private void dfs(Digraph G, int v)
	{
		marked[v] = true;
		for (int w: G.adj(v))
			if (!marked[w])
				dfs(G, w);
		tp.push(v);
	}
	
	/*
	 * Constructor that use DFS to sort the Graph
	 */
	public Topological(Digraph G)
	{
		marked = new boolean[G.V()];
		tp = new Stack<Integer>();
		
		for (int i = 0; i < G.V(); i++)
			if (!marked[i]) dfs(G, i);
	}
	
	/*
	 * Return the List of topologically-sorted list
	 */
	public Iterable<Integer> sortedList() 
	{
		return tp;
	}
}
