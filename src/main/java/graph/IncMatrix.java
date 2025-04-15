package main.java.graph;

public class IncMatrix implements Graph {
    private double[][] m;
    private int cur;

    // Constructor initializes the incidence matrix
    public IncMatrix(int numVertices, int numEdges) {
        m = new double[numVertices][numEdges];
        cur = 0;
    }

    // Adds an edge to the incidence matrix
    public void addEdge(int u, int v, double weight) {
        m[u][cur] = -weight; // Use negative weight for the tail
        m[v][cur] = weight;  // Use positive weight for the head
        cur++;
    }

    // Returns a string representation of the incidence matrix
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double[] row : m) {
            for (double value : row) {
                sb.append(String.format("%6.2f ", value));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}