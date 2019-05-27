import java.util.LinkedList;
/*
 * Implementation of String Symbol Table that implements the R-way trie
 */
public class StringST<Value> {
	private final int R = 256;
	private class Node {
		Value val;
		@SuppressWarnings("unchecked")
		Node[] next = (Node[]) new Object[R];	
	}
	private Node root;
	
	/*
	 * Put the key with the value 
	 */
	public void put(String key, Value val)
	{
		root = put(root, key, val, 0);
	}
	
	/*
	 * Get the value with key
	 */
	public Value get(String key)
	{
		Node x = get(root, key, 0);
		if (x == null) return null;
		return x.val;
	}
	
	/*
	 * Delete the key and value associated with key
	 */
	public void delete(String key)
	{
		Node x = get(root, key, 0);
		if (x == null) return;
		x.val = null;
	}
	/*
	 * Return the list of all the key
	 */
	public Iterable<String> keys()
	{
		LinkedList<String> list = new LinkedList<String>();
		collect(root, "", list);
		return list;
	}
	
	/*
	 * Return all the Strings with prefix
	 */
	public Iterable<String> keysWithPrefix(String prefix)
	{
		LinkedList<String> list = new LinkedList<String>();
		Node x = get(root, prefix, 0);
		collect(x, prefix, list);
		return list;
	}
	
	public String longestPrefixOf(String query)
	{
		int index = search(root, query, 0, 0);
		return query.substring(0, index);
	}
	
	private Node put(Node current, String key, Value val, int d)
	{
		if (current == null) current = new Node();
		if (d == key.length()) 
		{
			current.val = val;
			return current;
		}
		char c = key.charAt(d);
		current.next[c] = put(current.next[c], key, val, d + 1);
		return current;
	}
	
	private Node get(Node current, String key, int d)
	{
		if (current == null) return null;
		if (d == key.length()) return current;
		char c = key.charAt(d);
		return current.next[c];
	}
	
	private void collect(Node current, String prefix, LinkedList<String> q)
	{
		if (current == null) return;
		if (current.val != null) q.add(prefix);
		for (char c = 0; c < R; c++)
			collect(current.next[c], prefix + c, q);
	}
	
	private int search(Node current, String query, int d, int length)
	{
		if (current == null) return length;
		if (current.val != null) length = d;
		if (d == query.length()) return length;
		char c = query.charAt(d);
		return search(current.next[c], query, d + 1, length);
	}
}
