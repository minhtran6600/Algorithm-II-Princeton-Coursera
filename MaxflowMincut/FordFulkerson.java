import java.util.LinkedList;
/*
 * Implementation of Ford-Fulkerson algorithm to find the maximum flow (and minimum cut)
 */
public class FordFulkerson {
	private boolean[] marked; 				// marked if the vertex is in the Residual Network
	private FlowEdge[] edgeTo;				// last edge that connect to the vertex
	private double value;					// The value of the maximum flow
	
	/*
	 * Check and find the augmenting path in the flow network
	 */
	private boolean hasAugmentingPath(FlowNetwork G, int s, int t)
	{
		marked = new boolean[G.V()];
		edgeTo = new FlowEdge[G.V()];
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		marked[s] = true;
		queue.add(s);
		
		while (!queue.isEmpty())
		{
			int v = queue.removeFirst();
			for (FlowEdge e: G.adj(v))
			{
				int w = e.other(v);
				if (e.residualCapacityTo(w) > 0 && !marked[v])
				{
					edgeTo[w] = e;
					marked[w] = true;
					queue.addFirst(w);
				}
			}
		}
		return marked[t];
	}
	
	/*
	 * Ford-Fulkerson algorithm to find the maximum flow
	 */
	public FordFulkerson(FlowNetwork G, int s, int t)
	{
		value = 0.0;
		while (hasAugmentingPath(G, s, t))
		{
			double bottle = Double.POSITIVE_INFINITY;
			for (int v = t; v != s; v = edgeTo[v].other(v))
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
			for (int v = t; v != s; v = edgeTo[v].other(v))
				edgeTo[v].addResidualFlowTo(v, bottle);
			value += bottle;
		}
	}
	
	/*
	 * Return the value of the maximum flow
	 */
	public double value()
	{
		return value;
	}
	
	/*
	 * Return if v connect to s in the residual network
	 */
	public boolean inCut(int v)
	{
		return marked[v];
	}
}
