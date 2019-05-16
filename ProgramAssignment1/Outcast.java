import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Implementation of the Algorithm to find the outcast of a list of nouns in a WordNet
 */
public class Outcast {
	private final WordNet wordnet;
	
	/*
	 * Constructor take a WordNet object
	 */
	public Outcast(WordNet wordnet) 
	{
		this.wordnet = wordnet;
	}
	
	/*
	 * Given an array of WordNet nouns, return an outcast
	 */
	public String outcast(String[] nouns)
	{
		int maxDist = -1;
		int max = 0;
		int[][] dists = new int[nouns.length][nouns.length];
		for (int i = 0; i < nouns.length; i++)
		{
			int dist = 0;
			for (int j = 0; j < nouns.length; j++)
			{
				if (dists[i][j] == 0 && i != j)
				{
					dists[i][j] = wordnet.distance(nouns[i], nouns[j]);
					dists[j][i] = dists[i][j];
				}
				dist += dists[i][j];
			}
			if (dist > maxDist)
			{
				maxDist = dist;
				max = i;
			}
		}
		return nouns[max];
	}
	
	/*
	 * Test client
	 */
	public static void main(String[] args) {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}

}
