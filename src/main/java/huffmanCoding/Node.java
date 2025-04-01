package main.java.huffmanCoding;

public class Node implements Comparable<Node> {
    int frequency;  // 문자 빈도수
    char character; // 문자
    Node left;
    Node right;
    
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
    public int compareTo(Node node) {
        return this.frequency - node.frequency;
    }
} 