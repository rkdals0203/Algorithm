package main.java.dijkstra;

import java.util.*;

// 인접 리스트를 이용한 그래프 표현
public class AdjList {
    private List<List<Node>> adjList;
    private int numVertices;

    // 내부에서 사용할 간선 표현 클래스 (가중치만 필요)
    private static class Edge {
        int destination;
        int weight;

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    public AdjList(int numVertices) {
        this.numVertices = numVertices;
        adjList = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // 방향 그래프의 간선 추가 (u -> v, 가중치 w)
    public void addEdge(int u, int v, int weight) {
        // Node 객체 대신 내부 Edge 객체 또는 간단한 Pair 사용 가능
        // 여기서는 설명을 위해 도착 정점과 가중치를 가진 Node 객체 사용
        adjList.get(u).add(new Node(v, weight)); 
    }

    // 특정 정점의 인접 노드 리스트 반환
    public List<Node> getNeighbors(int vertex) {
        return adjList.get(vertex);
    }

    // 그래프의 정점 수 반환
    public int getNumVertices() {
        return numVertices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adjacency List:\n");
        for (int i = 0; i < numVertices; i++) {
            sb.append(i).append(": ");
            for (Node neighbor : adjList.get(i)) {
                sb.append("(").append(neighbor.vertex).append(", ").append(neighbor.distance).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Dijkstra 알고리즘을 사용하여 시작 정점으로부터 다른 모든 정점까지의 최단 경로를 계산합니다.
     *
     * @param startVertex 시작 정점의 인덱스
     * @return 시작 정점으로부터 각 정점까지의 최단 거리를 담은 배열. 도달할 수 없는 경우 Integer.MAX_VALUE가 저장됩니다.
     */
    public int[] dijkstra(int startVertex) {
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startVertex] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startVertex, 0));

        while(!pq.isEmpty()){
            Node currentNode = pq.poll();
            for(Node neighbor: adjList.get(currentNode.vertex)){
                int newDistance = dist[currentNode.vertex] + neighbor.distance;
                if(newDistance<dist[neighbor.vertex]){
                    dist[neighbor.vertex] = newDistance;
                    pq.add(new Node(neighbor.vertex,newDistance));
                }
            }
        }

        return dist;
    }
} 