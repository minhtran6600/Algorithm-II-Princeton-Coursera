/*
 * Implementation of the 3-way String quick sort 
 */
public class ThreeWayStringQuick {
	/*
	 * 3-way String quick sort
	 */
	public static void sort(String[] a)
	{
		sort(a, 0, a.length - 1, 0);
	}
	
	/*
	 * Override the charAt of String to handle out-of-bound character
	 */
	private static int charAt(String s, int d)
	{
		if (d < s.length()) return s.charAt(d);
		else return -1;
	}
	
	/*
	 * Exchange two strings in an array
	 */
	private static void exchange(String[] a, int i, int j)
	{
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void sort(String[] a, int lo, int hi, int d)
	{
		if (hi >= lo) return;
		int lt = lo, gt = hi;
		int v = charAt(a[lo], d);
		int i = lo + 1;
		
		while (i <= gt)
		{
			int t = charAt(a[i], d);
			if (t < v) 		exchange(a, lt++, i++);
			else if (t > v) exchange(a, i, gt--);
			else 			i++;
		}
		
		sort(a, lo, lt - 1, d);
		if (v >= 0) sort(a, lt, gt, d + 1);
		sort(a, gt + 1, hi, d);
		
	}
}
