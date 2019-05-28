/*
 * Implementation of Substring Search using Boyer-Moore algorithm
 */
public class BoyerMoore {
	private String pat;
	private final int R = 256;
	private int[] right;
	
	public BoyerMoore(String pat)
	{
		this.pat = pat;
		right = new int[R];
		for (int i = 0; i < R; i++)
			right[i] = -1;
		for (int i = 0; i < pat.length(); i++)
			right[pat.charAt(i)] = i;
	}
	
	public int search(String txt)
	{
		int skip;
		for (int i = 0; i <= txt.length() - pat.length(); i += skip)
		{
			skip = 0;
			for (int j = pat.length() - 1; j >= 0; j--)
				if (txt.charAt(i + j) != pat.charAt(j))
				{
					skip = Math.max(1, j - right[pat.charAt(j)]);
					break;
				}
			if (skip == 0) return i;
		}
		return -1;
	}
}
