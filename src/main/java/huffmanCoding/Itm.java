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
            Node parent = new Node('', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        
        return pq.poll();
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