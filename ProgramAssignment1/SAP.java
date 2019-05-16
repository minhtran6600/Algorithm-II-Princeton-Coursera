import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/*
 * Implementation of Shortest Ancestral Paths (SAP) using BFS for directed graphs.
 */
public class SAP {
	private final Digraph G;		// Directed Graph needs processing
	
	/*
	 * Check if the input is within range
	 */
	private void validate(int v)
	{
		if (v < 0 || v >= G.V()) 
			throw new IllegalArgumentException();
	}
	
	private void validate(Iterable<Integer> v)
	{
		if (v == null) throw new IllegalArgumentException();
		for (Integer w: v)
		{
			if (w == null) throw new IllegalArgumentException();
			if (w < 0 || w >= G.V())
				throw new IllegalArgumentException();
		}
	}
	
	/*
	 * Constructor takes a directed graph (not necessarily a DAG)
	 */
	public SAP(Digraph G)
	{
		if (G == null) throw new IllegalArgumentException();
		this.G = G;
	}
	
	/*
	 * Length of SAP between vertex v and vertex w, -1 if no such paths
	 */
	public int length(int v, int w)
	{
		validate(v);
		validate(w);
		
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
		
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < G.V(); i++)
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
				if (bfsV.distTo(i) + bfsW.distTo(i) < minDist)			// Find the minimum distance
					minDist = bfsV.distTo(i) + bfsW.distTo(i);
		if (minDist == Integer.MAX_VALUE) return -1;
		return minDist;

	}
	
	/*
	 * A common ancestor of vertex v and vertex w participating in the SAP, -1 if no such paths
	 */
	public int ancestor(int v, int w)
	{
		validate(v);
		validate(w);
		
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
		
		int minDist = Integer.MAX_VALUE;
		int ancestor = -1;
		for (int i = 0; i < G.V(); i++)
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
				if (bfsV.distTo(i) + bfsW.distTo(i) < minDist)	
				{
					minDist = bfsV.distTo(i) + bfsW.distTo(i);
					ancestor = i;
				}
		return ancestor;
	}
	
	/*
	 * Length of SAP between any vertex in v and any vertex in w, -1 if no such paths
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w)
	{
		validate(v);
		validate(w);
		
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
		
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < G.V(); i++)
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
				if (bfsV.distTo(i) + bfsW.distTo(i) < minDist)			// Find the minimum distance
					minDist = bfsV.distTo(i) + bfsW.distTo(i);
		if (minDist == Integer.MAX_VALUE) return -1;
		return minDist;
	}
	
	/*
	 * A common ancestor of any vertex in v and any vertex in w participating in the SAP, -1 if 
	 * no such paths
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	{
		validate(v);
		validate(w);
		
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(G, w);
		
		int minDist = Integer.MAX_VALUE;
		int ancestor = -1;
		for (int i = 0; i < G.V(); i++)
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
				if (bfsV.distTo(i) + bfsW.distTo(i) < minDist)	
				{
					minDist = bfsV.distTo(i) + bfsW.distTo(i);
					ancestor = i;
				}
		return ancestor;
	}
	
	/*
	 * Test Client
	 */
	public static void main(String[] args)
	{
		In in = new In(args[0]);
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
	}
}