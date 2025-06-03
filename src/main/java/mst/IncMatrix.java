package main.java.mst;
import java.util.*;
public class IncMatrix implements Graph {
    private int[][] m;          // Incidence Matrix (Tail: -1, Head: 1)
    private double[] weights;   // Array to store weights of edges
    private int cur;            // Current edge index

    // Constructor initializes the incidence matrix and weights array
    public IncMatrix(int numVertices, int numEdges) {
        m = new int[numVertices][numEdges];
        weights = new double[numEdges];
        cur = 0;
    }

    // Adds an edge with its weight stored separately
    public void addEdge(int u, int v, double weight) {
        m[u][cur] = -1;          // Tail vertex marked with -1
        m[v][cur] = 1;           // Head vertex marked with 1
        weights[cur] = weight;   // Store the weight in a separate array
        cur++;
    }

    // Returns a string representation of the incidence matrix and weights
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Incidence Matrix:\n");
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < cur; j++) {
                sb.append(String.format("%4d ", m[i][j]));
            }
            sb.append("\n");
        }
        sb.append("Weights:\n");
        for (int j = 0; j < cur; j++) {
            sb.append(String.format("%6.2f ", weights[j]));
        }
        sb.append("\n");
        return sb.toString();
    }
    @Override
    public int[] bfs(int start) {
         return null;
         }
    @Override
    public int[] dfs(int start) {
        return null;
        }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (int j = 0; j < cur; j++) {
            int u = -1, v = -1;
            for (int i = 0; i < m.length; i++) {
                if (m[i][j] == -1) u = i;
                else if (m[i][j] == 1) v = i;
            }
            edges.add(new Edge(u, v, weights[j], null));
        }
        return edges;
    }


        public List<Edge> kruskalMST() {
        List<Edge> mst = new ArrayList<>();
        List<Edge> edges = getEdges();
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(m.length);

        for (Edge edge: edges){
            int currentU = edge.u;
            int currentV = edge.v;

            if(ds.find(currentU) != ds.find(currentV)){
                mst.add(edge);
                ds.union(currentU, currentV);
            }
            
        }

        return mst;
    }
}