import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Topological;
import java.util.HashMap;
import java.util.ArrayList;

/*
 * Directed Graph implementation of WordNet. To satisfy the running time requirements, the class also
 * contains a hash table of nouns (key = nouns, value = list of ID of synsets of the noun)
 */

public class WordNet {
	private final Digraph G;									// Directed-graph representation of WordNet
	private final ArrayList<String> synsets;
	private final HashMap<String, ArrayList<Integer>> nouns;	// Hash table of nouns
	
	/*
	 * Validate the noun - check if it is WordNet noun
	 */
	private void validate(String noun)
	{
		if (!nouns.containsKey(noun)) throw new IllegalArgumentException();
	}
	/*
	 * Constructor takes the name of two input files: synsets and hypernyms
	 */
	public WordNet(String synsets, String hypernyms)
	{
		if (synsets == null || hypernyms == null) throw new IllegalArgumentException();
		
		In synsetsFile = new In(synsets);				
		In hypernymsFile = new In(hypernyms);
		
		nouns = new HashMap<String, ArrayList<Integer>>();
		this.synsets = new ArrayList<String>();
		int v = 0;										// Number of synsets
		while (synsetsFile.hasNextLine())				
		{
			String inLine = synsetsFile.readLine();
			String[] input = inLine.split(","); 
			
			int id = Integer.parseInt(input[0]);
			String synset = input[1];
			this.synsets.add(synset);					// Add the synset to the synsets list
			String[] nounSet = synset.split(" ");
			
			for (String noun: nounSet)
			{
				if (!nouns.containsKey(noun))			// Create a new key if the noun is not in Table
					nouns.put(noun, new ArrayList<Integer>());
				nouns.get(noun).add(id);				// Add the synset ID into the ID list of the noun
			}
			
			v++;										// Increase the number of WordNet vertices
		}
		
		G = new Digraph(v);		
		while (hypernymsFile.hasNextLine())
		{
			String inLine = hypernymsFile.readLine();
			String[] input = inLine.split(",");
			
			int id = Integer.parseInt(input[0]);
			for (int i = 1; i < input.length; i++)
				G.addEdge(id, Integer.parseInt(input[i]));	// Create an edge (hypernym)
		}
		
		int root = -1;
		for (int i = 0; i < G.V(); i++)
			if (G.outdegree(i) == 0)						// Check if the WordNet is rooted
			{	
				if (root != -1) throw new IllegalArgumentException();
				root = i;
			}			
		if (root == -1) throw new IllegalArgumentException();
		
		Topological tp = new Topological(G);
		if (!tp.isDAG()) throw new IllegalArgumentException();	// Check if it is a DAG
	}
	
	/*
	 * Returns all WordNet nouns
	 */
	public Iterable<String> nouns()
	{
		return nouns.keySet();
	}
	
	/*
	 * is the word a WordNet noun
	 */
	public boolean isNoun(String word)
	{
		if (word == null) throw new IllegalArgumentException();
		return nouns.containsKey(word);
	}
	
	/*
	 * distance between nounA and nounB
	 */
	public int distance(String nounA, String nounB) {
		validate(nounA);
		validate(nounB);
		
		BreadthFirstDirectedPaths bfsA = new BreadthFirstDirectedPaths(G, nouns.get(nounA));	// Create a BFS for the nounA
		BreadthFirstDirectedPaths bfsB = new BreadthFirstDirectedPaths(G, nouns.get(nounB));	// Create a BFS for the nounB
		
		int minDist = Integer.MAX_VALUE;
		for (int i = 0; i < G.V(); i++)
			if (bfsA.hasPathTo(i) && bfsB.hasPathTo(i))
				if (bfsA.distTo(i) + bfsB.distTo(i) < minDist)			// Find the minimum distance
					minDist = bfsA.distTo(i) + bfsB.distTo(i);
		
		return minDist;
	}
	
	/*
	 * a synset that is the common ancestor of nounA and nounB in a shortest ancestral path
	 */
	public String sap(String nounA, String nounB)
	{
		validate(nounA);
		validate(nounB);
		
		BreadthFirstDirectedPaths bfsA = new BreadthFirstDirectedPaths(G, nouns.get(nounA));	// Create a BFS for the nounA
		BreadthFirstDirectedPaths bfsB = new BreadthFirstDirectedPaths(G, nouns.get(nounB));	// Create a BFS for the nounB
		
		int minDist = Integer.MAX_VALUE;
		String sap = null;
		for (int i = 0; i < G.V(); i++)
			if (bfsA.hasPathTo(i) && bfsB.hasPathTo(i))
				if (bfsA.distTo(i) + bfsB.distTo(i) < minDist)	// Find the minimum distance
				{
					minDist = bfsA.distTo(i) + bfsB.distTo(i);
					sap = synsets.get(i);
				}
		return sap;	
	}
}