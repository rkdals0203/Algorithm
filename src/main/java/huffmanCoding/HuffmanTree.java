package main.java.huffmanCoding;

import java.util.*;

public class HuffmanTree {
    private Node root;
    
    public HuffmanTree() {
        this.root = null;
    }
    
    // 허프만 트리 생성
    public Node buildHuffmanTree(HashMap<Character, Integer> freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 모든 문자를 우선순위 큐에 추가
        for (char character : freq.keySet()) {
            int frequency = freq.get(character);
            pq.add(new Node(character, frequency));
        }
        
        // 트리 구성 (빈도수가 낮은 노드부터 병합)
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            
            // 두 노드를 병합한 새 노드 생성 (부모 노드)
            int sum = left.frequency + right.frequency;
            pq.add(new Node(sum, left, right));
        }
        
        // 최종 루트 노드 반환
        this.root = pq.poll();
        return this.root;
    }
    
    // 허프만 트리를 사용하여 코드 테이블 생성
    public HashMap<Character, String> generateCodes() {
        HashMap<Character, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }
    
    // 재귀적으로 코드 생성 (왼쪽=0, 오른쪽=1)
    private void generateCodesRecursive(Node node, String code, HashMap<Character, String> codes) {
        if (node == null) {
            return;
        }
        
        // 리프 노드인 경우 (실제 문자가 있는 노드)
        if (node.left == null && node.right == null) {
            codes.put(node.character, code);
        }
        
        // 왼쪽 서브트리 탐색 (0 추가)
        generateCodesRecursive(node.left, code + "0", codes);
        // 오른쪽 서브트리 탐색 (1 추가)
        generateCodesRecursive(node.right, code + "1", codes);
    }
    
    // 텍스트 디코딩
    public String decode(String encoded) {
        StringBuilder decoded = new StringBuilder();
        Node current = root;
        
        for (char bit : encoded.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }
            
            // 리프 노드에 도달하면 문자 추가
            if (current.left == null && current.right == null) {
                decoded.append(current.character);
                current = root; // 루트로 돌아가서 다음 문자 찾기
            }
        }
        
        return decoded.toString();
    }
} 