package main.java.dijkstra;

public class Main {
    public static void main(String[] args) {
        // 그래프 생성 (정점 6개)
        int numVertices = 6;
        AdjList graph = new AdjList(numVertices);

        // 간선 추가 (방향 그래프)
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 1, 1);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 3);

        System.out.println("Graph:");
        System.out.println(graph);

        // Dijkstra 알고리즘 실행 (AdjListGraph의 메소드 호출)
        int startVertex = 0;
        int[] shortestDistances = graph.dijkstra(startVertex);

        // 결과 출력
        System.out.println("\nDijkstra's Algorithm starting from vertex " + startVertex + ":");
        for (int i = 0; i < numVertices; i++) {
            System.out.println("Shortest distance to vertex " + i + ": " +
                    (shortestDistances[i] == Integer.MAX_VALUE ? "INF" : shortestDistances[i]));
        }
    }
} 