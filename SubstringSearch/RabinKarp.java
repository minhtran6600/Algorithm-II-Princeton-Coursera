/*
 * Implementation of Rabin-Karp algorithm for substring search
 */
public class RabinKarp {
	private final int R = 256;
	private final int Q = 997;
	private String pat;
	private long patHash;
	private long RM;
	
	/*
	 * Construction that compute that hash value of the pattern
	 */
	public RabinKarp(String pat)
	{
		this.pat = pat;
		this.patHash = hash(pat);
		this.RM = (long) Math.pow(R, pat.length() - 1);
	}
	
	public int search(String txt)
	{
		if (txt.length() < pat.length()) return -1;
		long txtHash = hash(txt.substring(0, pat.length()));
		if (txtHash == patHash) return 0;
		for (int i = pat.length(); i < txt.length(); i++)
		{
			txtHash = (txtHash + Q - RM * txt.charAt(i - pat.length()) % Q) % Q;
			txtHash = (txtHash * R + txt.charAt(i)) % Q;
			if (patHash == txtHash) return i - pat.length() + 1;
		}
		return -1;
	}
	
	/*
	 * hash function
	 */
	private long hash(String txt)
	{
		long hash = 0;
		for (int i = 0; i < txt.length(); i++)
			hash += txt.charAt(i) * (long) (Math.pow(R, txt.length() - 1 - i));
		hash = hash % Q;
		return hash;
	}
	
	
}
