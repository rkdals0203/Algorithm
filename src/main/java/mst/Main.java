package main.java.mst;
import java.util.*;
public class Main {
	public static void main(String[] args) {
        IncMatrix graph = new IncMatrix(4, 5);
        graph.addEdge(0, 1, 1.0);
        graph.addEdge(0, 2, 3.0);
        graph.addEdge(1, 2, 1.5);
        graph.addEdge(1, 3, 2.0);
        graph.addEdge(2, 3, 4.0);

        System.out.println("Incidence Matrix:");
        System.out.println(graph);

        List<Edge> mst = graph.kruskalMST();
        System.out.println("MST using Kruskal Algorithm:");
        for (Edge e : mst) {
            System.out.println(e);
        }
    }
}
