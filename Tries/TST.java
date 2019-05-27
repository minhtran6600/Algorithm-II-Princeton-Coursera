/*
 * Implementation of String Symbol Table using Ternary String Trie
 */
public class TST<Value> {
	private class Node {
		Value val;
		char c;
		Node left, mid, right;
	}
	
	private Node root;
	
	public void put(String key, Value val)
	{
		root = put(root, key, val, 0);
	}
	
	public Value get(String key)
	{
		Node x = get(root, key, 0);
		if (x == null) return null;
		return x.val;
	}
	
	private Node put(Node current, String key, Value val, int d)
	{
		char c = key.charAt(d);
		if (current == null) 
		{
			current = new Node();
			current.c = c;
		}
		if (c < current.c) 				current.left = put(current.left, key, val, d);
		else if (c > current.c) 		current.right = put(current.right, key, val, d);
		else if (d < key.length() - 1) 	current.mid = put(current.mid, key, val, d + 1);
		else 							current.val = val;
		return current;
	}
	
	private Node get(Node current, String key, int d)
	{
		char c = key.charAt(d);
		if (current == null)			return null;
		if (d >= key.length()) 			return null;
		if (c < current.c)				return get(current.left, key, d);
		else if (c > current.c)			return get(current.right, key, d);
		else if (d == key.length() - 1) return current;
		else							return get(current.mid, key, d + 1);
	}
}
