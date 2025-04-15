package main.java.graph;

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
            for (T edge : adjList.get(i)) {
                result.append(edge).append(" ");
            }
        }
        return result.toString();
    }
}