import edu.princeton.cs.algs4.IndexMinPQ;
import java.util.Stack;
/*
 * Implementation of the Dijkstra's Algorithm using Minimum indexed Priority Queue 
 */
public class DijkstraSP {
	private int s;							// Source vertex
	private DirectedEdge[] edgeTo;			// Store the directed edge leading to the vertex
	private double[] distTo;				// Store the minimum known distance to the vertex
	private IndexMinPQ<Double> pq;			// Minimum indexed Priority Queue
	
	/*
	 * Implementation of Edge relaxation
	 */
	private void relax(DirectedEdge e)
	{
		int v = e.from();
		int w = e.to();
		
		if (distTo[w] > distTo[v] + e.weight())
		{
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w))	pq.decreaseKey(w, distTo[w]);
			else 				pq.insert(w, distTo[w]);
		}
	}
	
	/*
	 * Constructor to build the single-source shortest paths
	 */
	public DijkstraSP(EdgeWeightedDigraph G, int s)
	{
		this.s = s;
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		
		for (int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		
		pq = new IndexMinPQ<Double>(G.V());
		pq.insert(s, 0.0);
		
		while (!pq.isEmpty())
		{
			int v = pq.delMin();
			for (DirectedEdge e: G.adj(v))
				relax(e);
		}
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
