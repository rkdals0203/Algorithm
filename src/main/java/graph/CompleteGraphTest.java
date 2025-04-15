package main.java.graph;

public class CompleteGraphTest {
    public static void main(String[] args) {
        int numVertices = 3;
        int numEdges = numVertices * (numVertices - 1);

        Graph<Edge> adjList = new AdjList<>(numVertices);
        Graph<Edge> adjMatrix = new AdjMatrix(numVertices);
        Graph<Edge> incMatrix = new IncMatrix(numVertices, numEdges);

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j) {
                    adjList.addEdge(i, j, 1.0);
                    adjMatrix.addEdge(i, j, 1.0);
                }
            }
        }
        
        int edgeCount = 0;
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                incMatrix.addEdge(i, j, 1.0);
                incMatrix.addEdge(j, i, 1.0);
                edgeCount += 2;
            }
        }

        System.out.println("Adjacency List Representation:");
        System.out.println(adjList.toString());
        System.out.println();

        System.out.println("Adjacency Matrix Representation:");
        System.out.println(adjMatrix.toString());
        System.out.println();

        System.out.println("Incidence Matrix Representation:");
        System.out.println(incMatrix.toString());
    }
} 