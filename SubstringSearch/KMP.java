/*
 * Implementation of the Knuth-Morris-Pratt Algorithm for substring search
 */
public class KMP {
	private String pat;
	private int[][] dfa;
	private final int R = 256;
	
	/*
	 * Construction of the Deterministic Finite state Automaton (DFA) of the pattern
	 */
	public KMP(String pat)
	{
		this.pat = pat;
		this.dfa = new int[R][pat.length()];
		dfa[pat.charAt(0)][0] = 1;
		
		for (int X = 0, j = 1; j < pat.length(); j++)
		{
			for (int r = 0; r < R; r++)
				dfa[r][j] = dfa[r][X];
			dfa[pat.charAt(j)][j] = j + 1;
			X = dfa[pat.charAt(j)][X];
		}
	}
	
	/*
	 * Search for the index of the pattern in the string
	 */
	public int search(String txt)
	{
		int j = 0, i;
		for (i = 0; i < txt.length() && j < pat.length(); i++)
		
			j = dfa[txt.charAt(i)][j];
		if (j == pat.length())	return i - j;
		else					return -1;
		
	}
}
