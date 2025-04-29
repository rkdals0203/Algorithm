package main.java.dijkstra;

import java.util.*;

// Node 클래스는 우선순위 큐에서 사용되며, 정점과 해당 정점까지의 거리를 저장합니다.
// 거리를 기준으로 Comparable 인터페이스를 구현하여 우선순위 큐가 최소 거리를 가진 노드를 먼저 처리하도록 합니다.
public class Node implements Comparable<Node> {
    int vertex;
    int distance;

    public Node(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node other) {
        // 거리가 짧은 노드가 우선순위가 높도록 설정
        return Integer.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return "Node{" +
               "vertex=" + vertex +
               ", distance=" + distance +
               '}';
    }
} 