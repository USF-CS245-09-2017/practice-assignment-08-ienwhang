import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collections;


public class GraphAdjMatrix implements Graph {
	// Directed and unweighted

	int[][] edges;
	
	public GraphAdjMatrix(int vertices) {
		edges = new int[vertices][vertices];
	}
	
	public void addEdge(int src, int tar) {
		edges[src][tar] = 1; 
	}
	
	public int[] neighbors(int vertex) {
		int d = outDegree(vertex);  // Get size of array
		int j=0;
		int[] neighbors = new int[d];  
		for (int i=0; i<edges.length; i++) {
			if (edges[vertex][i] == 1) {
				neighbors[j++] = i;
			}
		}
		return neighbors;
	}
	
	public int outDegree(int vertex) {
		int out = 0;
		for (int i=0; i<edges.length; i++) {
			if (edges[vertex][i] == 1) {
				out++;
			}
		}
		return out;
	}
	
	public int inDegree(int vertex) {
		int in = 0;
		for (int i=0; i<edges.length; i++) {
			if (edges[i][vertex] == 1) {
				in++;
			}
		}
		return in;
	}
	
	public void topologicalSort() {
		ArrayList<Integer> sort = new ArrayList<Integer>();
		ArrayList<Integer> inDegreeArr = new ArrayList<Integer>();
		for (int i=0; i<edges.length; i++)
			inDegreeArr.add(inDegree(i));
		while (Collections.max(inDegreeArr) >= 0) {
			if (inDegreeArr.contains(0)) {
				sort.add(inDegreeArr.indexOf(0));
				for (int j=0; j<inDegreeArr.size(); j++) {
					int replace = inDegreeArr.get(j) - 1;
					inDegreeArr.set(j, replace);
				}
			}
			else {
				System.out.println("Error in graph");
				break;
			}
		}
		System.out.print("Topological sort: ");
		for (int i: sort) 
			System.out.print(i + " ");
		System.out.println();
	}
	
}
