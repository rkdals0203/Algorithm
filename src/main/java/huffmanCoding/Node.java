package main.java.huffmanCoding;

public class Node implements Comparable<Node> {
    char character;  // 문자
    int frequency;   // 빈도수
    Node left;       // 왼쪽 자식
    Node right;      // 오른쪽 자식
    
    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }
    
    public Node(int frequency, Node left, Node right) {
        this.frequency = frequency;
        this.character = '\0';
        this.left = left;
        this.right = right;
    }
    
    @Override
    public int compareTo(Node other) {
        // 빈도수를 기준으로 노드 비교
        return this.frequency - other.frequency;
    }
} 