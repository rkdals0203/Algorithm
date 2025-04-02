package main.java.huffmanCoding;

import java.util.*;

public class Itm {
    public static HashMap<Character, Integer> calculateFrequency(String text) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for(char ch : text.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }
        return freq;
    }
    
    public static Node buildHuffmanTree(HashMap<Character, Integer> freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(HashMap.Entry<Character, Integer> entry : freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while(pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        
        return pq.poll();
    }

    public static HashMap<Character, String> generateCodes(Node root) {
        // TODO: 구현하세요
        // 1. 트리를 순회하며 각 문자의 코드 생성
        // 2. 왼쪽 자식으로 이동할 때는 '0' 추가
        // 3. 오른쪽 자식으로 이동할 때는 '1' 추가
        return null;
    }
    
    public static String encode(String text) {
        HashMap<Character, Integer> freq = calculateFrequency(text);
        
        Node tree = buildHuffmanTree(freq);
        
        HashMap<Character, String> codes = generateCodes(tree);
        
        String encoded = "";
        for(char ch : text.toCharArray()) {
            encoded += codes.get(ch);
        }
        return encoded;
    }
    
    public static double calculateCompressionRatio(String original, String encoded) {
        int originalBits = original.length() * 8;
        int encodedBits = encoded.length();
        return (double)encodedBits / originalBits * 100;
    }
} 