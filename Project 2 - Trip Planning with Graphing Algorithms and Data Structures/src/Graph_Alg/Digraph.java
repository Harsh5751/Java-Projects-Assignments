package Graph_Alg;
import java.util.ArrayList;

import RequiredDataStructures.Bag;

//from https://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/Graph.java.html

public class Digraph {
	private final int V; //Number of nodes
	private Bag<Integer>[] adj; //Nodes adjacent to reference node
	private int E; //number of edges
	
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	
	//add an edge in one direction
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	//get number of Vertices
	public int V() {
		// TODO Auto-generated method stub
		return V;
	}
	
	//get number of edges
	public int E() {
		return E;
	}
		
}
