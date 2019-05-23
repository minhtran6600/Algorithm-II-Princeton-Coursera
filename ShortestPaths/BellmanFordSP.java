import java.util.Stack;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
/*
 * Implementation of Bellman-Ford Shortest Paths Algorithm
 */
public class BellmanFordSP {
	private int s;						// The source vertex
	private DirectedEdge edgeTo[];		// The Edge to each vertex in shortest known paths
	private double distTo[];			// The distance to each vertex in shortest known paths
	
	/*
	 * Edge Relaxation
	 */
	private void relax(DirectedEdge e)
	{
		int v = e.from();
		int w = e.to();
		
		if (distTo[w] > distTo[v] + e.weight())
		{
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}
	
	/*
	 * Constructor using Bellman-Ford Shortest Paths Algorithm
	 */
	public BellmanFordSP(EdgeWeightedDigraph G, int s)
	{
		this.s = s;
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		
		for (int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		
		for (int i = 0; i < G.V(); i++)
			for (DirectedEdge e: G.edges())
				relax(e);
	}
	
	/*
	 * Return the distance from source s to vertex v
	 */
	public double distTo(int v)
	{
		return distTo[v];
	}
	
	/*
	 * Return the path from source s to vertex v, null if there is no such path
	 */
	public Iterable<DirectedEdge> pathTo(int v)
	{
		if (!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		
		int current = v;
		while (edgeTo[current].from() != s)
		{
			path.push(edgeTo[current]);
			current = edgeTo[current].from();
		}
		path.push(edgeTo[current]);
		return path;
	}
	
	/*
	 * Return if there is a path to vertex v
	 */
	public boolean hasPathTo(int v)
	{
		return edgeTo[v] != null;
	}
}
