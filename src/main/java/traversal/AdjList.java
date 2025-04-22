package main.java.traversal;

import java.util.ArrayList;
import java.util.List;

public class AdjList<T extends Edge> implements Graph {
    private List<List<T>> adjList;

    // Constructor to initialize the adjacency list with the number of vertices
    public AdjList(int numVertices) {
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Adds an edge to the adjacency list
    public void addEdge(int u, int v, double w) {
        adjList.get(u).add((T) new Edge(u, v, w, null));
    }

    // Returns a string representation of the adjacency list
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < adjList.size(); i++) {
            result.append("\n[").append(i).append("]: ");
            for (Edge edge : adjList.get(i)) {
                result.append(edge).append(" ");
            }
        }
        return result.toString();
    }

	@Override
	public int[] bfs(int start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] dfs(int start) {
		boolean[] visited = new boolean[adjList.size()];
		List<Integer> traversalOrder = new ArrayList<>();
		dfsHelper(start, visited, traversalOrder);
		
        int[] result = new int[traversalOrder.size()];
        for (int i = 0; i < traversalOrder.size(); i++) {
            result[i] = traversalOrder.get(i);
        }
        return result;	
        }

	private void dfsHelper(int vertex, boolean[] visited, List<Integer> traversalOrder) {
		visited[vertex] = true;
		traversalOrder.add(vertex);

		for (T edge : adjList.get(vertex)) {
			int neighbor = edge.getHead();
			if (!visited[neighbor]) {
				dfsHelper(neighbor, visited, traversalOrder);
			}
		}
	}
}