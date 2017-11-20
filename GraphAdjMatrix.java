import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;


public class GraphAdjMatrix implements Graph {
	// Directed and unweighted

	int[][] edges;
	
	// Initialise edges matrix
	public GraphAdjMatrix(int vertices) {
		edges = new int[vertices][vertices];
	}
	
	// Add directed edges
	public void addEdge(int src, int tar) {
		edges[src][tar] = 1; 
	}
	
	public int[] neighbors(int vertex) {
		int d = outDegree(vertex);  // Get size of array
		int j=0;
		int[] neighbors = new int[d];  // Initialise neighbors array
		for (int i=0; i<edges.length; i++) {  // If vertex is directed to another vertex, add to neighbor array
			if (edges[vertex][i] == 1) {
				neighbors[j++] = i;
			}
		}
		return neighbors;
	}
	
	// Get out-degree
	public int outDegree(int vertex) {
		int out = 0;
		for (int i=0; i<edges.length; i++) {
			if (edges[vertex][i] == 1) {
				out++;  // Increment outDegree
			}
		}
		return out;
	}
	
	// Get in-degree
	public int inDegree(int vertex) {
		int in = 0;
		for (int i=0; i<edges.length; i++) {
			if (edges[i][vertex] == 1) {
				in++;  // Increment inDegree
			}
		}
		return in;
	}
	
	public void topologicalSort() {
		ArrayList<Integer> sort = new ArrayList<Integer>();  // Initialise arraylist for sorted positions
		ArrayList<Integer> inDegreeArr = new ArrayList<Integer>();  // Initialise arraylist for in-degree at corresponding position
		for (int i=0; i<edges.length; i++)  // Populate in-degree arraylist
			inDegreeArr.add(inDegree(i));
		while (Collections.max(inDegreeArr) >= 0) {  // While loop to ensure all vertices are checked
			if (inDegreeArr.contains(0)) {  // If there exists a vertex with in-degree = 0
				for (int k=0; k<inDegreeArr.size(); k++) {
					if (inDegreeArr.get(k) == 0)
						sort.add(k);  // Add all indices where value is 0 to sorted array 
				}
				for (int j=0; j<inDegreeArr.size(); j++) {  // Decrement all values by 1 
					int replace = inDegreeArr.get(j) - 1;
					inDegreeArr.set(j, replace);  
				}
			}
			else {
				System.out.println("Error in graph");
				break;
			}
		}
		System.out.print("Topological sort: ");  // Print sorted list
		for (int i: sort) 
			System.out.print(i + " ");
		System.out.println();
	}
	
}
