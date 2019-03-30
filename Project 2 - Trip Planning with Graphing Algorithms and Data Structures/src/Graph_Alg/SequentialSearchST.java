package Graph_Alg;

// code strictly belongs to https://algs4.cs.princeton.edu/34hash/SequentialSearchST.java.html

public class SequentialSearchST<Key, Value> {
	private Node first;
	private int size = 0;
	
	private class Node{
		Key key;
		Value val;
		Node next;
		
		public Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	public Value get(Key key) {
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				return x.val;
			}
		}
		return null;
	}
	
	public Key getKey(Value val) {
		for(Node x = first; x != null; x = x.next) {
			if(val.equals(x.val)) {
				return x.key;
			}
		}
		return null;
	}
	
	public void put(Key key, Value val) {
		for(Node x = first; x != null; x = x.next) {
			if(key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
		size++;
	}
	
	public int Size() {
		return size;
	}
}
