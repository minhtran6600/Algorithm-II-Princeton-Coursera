import java.util.LinkedList;
import java.util.PriorityQueue;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/*
 * Implementation of Kruskal algorithm to find the minimum spanning tree of a connected 
 * Edge-weighted graph (using Union-Find to check connectivity in constant time)
 */
public class KruskalMST {
	private double weight;					// the weight of the MST
	private LinkedList<Edge> tree;			// the MST's list of edges
	
	/*
	 * The constructor that parse in the Edge-weighted Graph
	 */
	public KruskalMST(EdgeWeightedGraph G)
	{
		tree = new LinkedList<Edge>();
		
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		for (Edge e: G.edges())
			pq.add(e);
		
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
		while ((!pq.isEmpty() && (tree.size() < G.V() - 1)))
		{
			Edge min = pq.poll();
			
			int v = min.either();
			int w = min.other(v);
			
			if (!uf.connected(v, w))
			{
				weight += min.weight();
				tree.add(min);
				uf.union(v, w);
			}
		}
	}
	
	/*
	 * Return the MST
	 */
	public Iterable<Edge> edges()
	{
		return tree;
	}
	
	/*
	 * Return the weight of the MST
	 */
	public double weight()
	{
		return weight;
	}
}
