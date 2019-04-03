import java.io.IOException;

/**
 * @author Michael Barreiros
 *
 * 
 */
public class RedBlackTree<String extends Comparable<String>, States> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private Node root;
	
	//BST helper node data type
	private class Node{
		private String key;
		private States state;
		private Node left, right;
		private boolean colour;
		private int size;
		
		public Node(String key,States state, boolean colour, int size) {
			this.key= key;
			this.state = state;
			this.colour = colour;
			this.size = size;
		}
	}
	/**
	 * Used To initialize an empty symbol table.
	 */
	public RedBlackTree() {}
	
	/******************************************************************************
	 * Node helper methods
	 ******************************************************************************/
	
	/**
	 * 
	 * @param x Node being checked
	 * @return boolean True if x is red, false if x is either null or black
	 */
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.colour == RED;
	}
	
	/**
	 * 
	 * @param x Node being checked
	 * @return x.size The size of the node rooted at x. 0 if x is null
	 */
	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	}
	
	/**
	 * 
	 * @return root.size The size of the whole tree. Also the number of key-state
	 * pairs.
	 */
	private int size() {
		return size(root);
	}
	
	/**
	 * 
	 * @return boolean True if the symbol table is empty and False otherwise
	 */
	private boolean isEmpty() {
		return root == null;
	}
	
	/******************************************************************************
	 * Standard BST search
	 ******************************************************************************/
	
	/**
	 * 
	 * @param key the name of the node we are looking for 
	 * @return States the state that is associated with the key
	 */
	public States get(String key) {
		//Potentially add exception handling for null keys
		return get(root, key);
	}
	
	/**
	 * 
	 * @param x the root of the subtree we are looking in
	 * @param key the key we are searching for
	 * @return States the state that is associated with the key, null if key not found
	 */
	private States get(Node x, String key) {
		while(x != null) {
			int cmp = key.compareTo(x.key);
			if (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else return x.state;
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param key the key we are looking for
	 * @return True if the BST contains the key and False otherwise
	 */
	public boolean contains(String key) {
		return get(key) != null;
	}
	
	/******************************************************************************
	 * Red-black tree insertion.
	 ******************************************************************************/
	
	public void put(String key, States state) {
		//if (key == null) throw new IOException("state not initiated");
		
		root = put(root, key, state);
		root.colour = BLACK;
		
	}
	
	private Node put(Node h, String key, States state) {
		if (h == null) return new Node(key, state, RED, 1);
		
		int cmp = key.compareTo(h.key);
		
		if (cmp < 0) h.left = put(h.left, key, state);
		else if (cmp > 0) h.right = put(h.right, key, state);
		else h.state = state;
		
		if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right)) flipColours(h);
		h.size = size(h.left) + size(h.right) + 1; 
		
		return h;
	}
	
	/******************************************************************************
	 * Red-black tree helper functions
	 ******************************************************************************/
	
	/**
	 * 
	 * @param h Node being rotated
	 * @return Node after rotations
	 */
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.colour = x.right.colour;
		x.right.colour = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;  
		return x;
	}
	
	/**
	 * 
	 * @param h Node being rotated
	 * @return Node after rotations
	 */
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.colour = x.left.colour;
		x.left.colour = RED;
		x.size = h.size;
		h.size = size(h.left)+ size(h.right) + 1;  
		return x;
	}
	
	/**
	 * 
	 * @param h Node who's colours are to be flipped
	 */
	private void flipColours(Node h) {
		h.colour = !h.colour;
		h.left.colour = !h.left.colour;
		h.right.colour = !h.right.colour;
	}
	
}
