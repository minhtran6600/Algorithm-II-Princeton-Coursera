import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import java.util.Stack;

/*
 * Implementation of Shortest Path application for DAG using Topological sort and Edge Relaxation
 */
public class DagSP {
	private int s;							// The source vertex
	private DirectedEdge edgeTo[];			// The Directed Edge to the vertex in shortest paths
	private double distTo[];				// Distance to each vertex in shortest known paths
	
	/*
	 * Implementation of Edge Relaxation
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
	 * Constructor that construct Shortest paths from source vertex
	 */
	public DagSP(EdgeWeightedDigraph G, int s)
	{
		this.s = s;
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];
		
		for (int i = 0; i < G.V(); i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		
		Topological tp = new Topological(G);
		for (int v: tp.order())
			for (DirectedEdge e: G.adj(v))
				relax(e);
	}
	
	/*
	 * Return the distance from the source vertex
	 */
	public double distTo(int v)
	{
		return distTo[v];
	}
	
	/*
	 * Return the path from the source vertex 
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
	 * Return if there is a path from the source vertex
	 */
	public boolean hasPathTo(int v)
	{
		return edgeTo[v] != null;
	}
}
