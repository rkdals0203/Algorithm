package main.java.graph;
import java.util.*;

public class AdjMatrix implements Graph<Edge> {
    private double[][] matrix; // Matrix to store edge weights
    private int numVertices;   // Number of vertices in the graph

    // Constructor to initialize the adjacency matrix with the number of vertices
    public AdjMatrix(int numVertices) {
        this.numVertices = numVertices;
        matrix = new double[numVertices][numVertices];
        // Initialize the matrix with zeros indicating no edges
        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(matrix[i], 0.0);
        }
    }

    // Adds a weighted edge between two vertices
    @Override
    public void addEdge(int u, int v, double weight) {
        // Check if vertices are within bounds
        if (u < 0 || u >= numVertices || v < 0 || v >= numVertices) {
            throw new IllegalArgumentException("Vertex number out of bounds");
        }
        matrix[u][v] = weight; // Set weight for the directed edge
    }

    // Checks if there is an edge between two vertices
    public boolean hasEdge(int u, int v) {
        return matrix[u][v] != 0;
    }

    // Returns the weight of the edge between two vertices
    public double getEdgeWeight(int u, int v) {
        return matrix[u][v];
    }

    // Returns a string representation of the adjacency matrix
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                sb.append(String.format("%8.2f", matrix[i][j])).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}