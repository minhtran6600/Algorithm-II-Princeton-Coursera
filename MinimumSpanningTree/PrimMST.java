import java.util.LinkedList;
import edu.princeton.cs.algs4.IndexMinPQ;

/*
 * Implementation of Prim's Minimum Spanning Trees using the eager implementation: Using
 * Indexed priority queue to dynamically change the key
 */
public class PrimMST {
	private LinkedList<Edge> edges;			// The MST's edges
	private double weight;					// Weight of the MST
	
	/*
	 * Constructor that use Index Minimum Priority Queue to change the key
	 */
	public PrimMST(EdgeWeightedGraph G)
	{
		edges = new LinkedList<Edge>();
		Edge[] edgeTo = new Edge[G.V()];
		double[] distTo = new double[G.V()];
		
		for (int i = 0; i < G.V(); i++)
		{
			edgeTo[i] = null;
			distTo[i] = 0;
		}
		
		IndexMinPQ<Edge> pq = new IndexMinPQ<Edge>(G.V());
		
		for (Edge e: G.adj(0))
		{
			pq.insert(e.other(0), e);
			distTo[e.other(0)] = e.weight();
			edgeTo[e.other(0)] = e;
		}
		while ((edges.size() < G.V() - 1) && !pq.isEmpty())
		{
			int minIndex = pq.delMin();
			edges.add(edgeTo[minIndex]);
			weight += edgeTo[minIndex].weight();
			for (Edge e: G.adj(minIndex))
				if (!pq.contains(e.other(minIndex)))
				{
					pq.insert(e.other(minIndex), e);
					distTo[e.other(minIndex)] = e.weight();
					edgeTo[e.other(minIndex)] = e;
				}
				else if (e.weight() < distTo[e.other(minIndex)])
				{
					pq.decreaseKey(e.other(minIndex), e);
					distTo[e.other(minIndex)] = e.weight();
					edgeTo[e.other(minIndex)] = e;
				}
		}
	}
	
	/*
	 * Return the edges of the MST
	 */
	public Iterable<Edge> edges()
	{
		return edges;
	}
	
	/*
	 * Return the Weight of the MST
	 */
	public double weight()
	{
		return weight;
	}
}
