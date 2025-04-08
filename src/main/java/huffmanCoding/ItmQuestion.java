package main.java.huffmanCoding;

import java.util.*;

public class ItmQuestion {
    /**
     * 문자열에서 각 문자의 빈도수를 계산하는 메서드
     * 
     * @param text 입력 문자열
     * @return 각 문자와 그 빈도수를 담은 HashMap
     */
    public static HashMap<Character, Integer> calculateFrequency(String text) {
        // TODO: 구현하세요
        // 각 문자의 빈도수를 계산하여 HashMap에 저장
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char ch : text.toCharArray()){
            freq.put(ch, freq.getOrDefault(ch, 0)+1);
        }
        return null;
    }
    
    /**
     * 빈도수 정보를 바탕으로 허프만 트리를 구성하는 메서드
     * 
     * @param freq 문자 빈도수 정보
     * @return 허프만 트리의 루트 노드
     */
    public static Node buildHuffmanTree(HashMap<Character, Integer> freq) {
        // TODO: 구현하세요
        // 1. 빈도수 기반으로 우선순위 큐 구성
        // 2. 가장 빈도수가 낮은 두 노드를 선택하여 새로운 부모 노드 생성
        // 3. 모든 노드가 처리될 때까지 반복
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for(Map.Entry<Character,Integer> entry : freq.entrySet()){
            Node node = new Node(entry.getKey(),entry.getValue());
            pq.add(node);
        }

        while(pq.size()>1){
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.frequency+ right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        return pq.poll();
    }
    
    /**
     * 허프만 트리를 사용하여 각 문자의 이진 코드를 생성하는 메서드
     * 
     * @param root 허프만 트리의 루트 노드
     * @return 각 문자와 그에 대응하는 이진 코드를 담은 HashMap
     */
    public static HashMap<Character, String> generateCodes(Node root) {
        HashMap<Character, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }
    
    private static void generateCodesRecursive(Node node, String code, HashMap<Character, String> codes) {
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
    
    /**
     * 주어진 문자열을 허프만 코딩으로 인코딩하는 메서드
     * 
     * @param text 인코딩할 문자열
     * @return 인코딩된 이진 문자열
     */
    public static String encode(String text) {
        // 1. 빈도수 계산
        HashMap<Character, Integer> freq = calculateFrequency(text);
        
        // 2. 허프만 트리 생성
        Node root = buildHuffmanTree(freq);
        
        // 3. 직접 트리를 순회하며 인코딩
        StringBuilder encoded = new StringBuilder();
        for(char ch : text.toCharArray()) {
            String code = findCode(root, ch, "");
            encoded.append(code);
        }
        return encoded.toString();
    }
    
    private static String findCode(Node node, char target, String path) {
        if (node == null) {
            return "";
        }
        
        // 찾는 문자인 경우
        if (node.character == target) {
            return path;
        }
        
        // 왼쪽 서브트리 탐색 (0 추가)
        String leftPath = findCode(node.left, target, path+"0");
        if(!leftPath.isEmpty()){
            return leftPath;
        }

        String rightPath = findCode(node.right, target, path+"1");
        if(!rightPath.isEmpty()){
            return rightPath;
        }
        
        return "";
    }

    /**
     * 압축률을 계산하는 메서드
     * 압축률(%) = (인코딩된 비트 수 / 원본 비트 수) * 100
     * 
     * @param original 원본 문자열
     * @param encoded 인코딩된 이진 문자열
     * @return 압축률(%)
     */
    public static double calculateCompressionRatio(String original, String encoded) {
        // TODO: 구현하세요
        // 1. 원본 문자열의 비트 수 계산 (문자당 8비트)
        // 2. 인코딩된 문자열의 비트 수 계산
        // 3. 압축률 계산 및 반환
        int originalBit = original.length()*8;
        int encodedBit = encoded.length();
        return (double)(encodedBit/originalBit)*100;
    }
} 