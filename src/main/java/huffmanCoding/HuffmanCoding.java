import java.util.*;
public class HuffmanCoding {
    // 허프만 트리의 노드 클래스
    static class Node implements Comparable<Node> {
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
    
    // 문자열의 각 문자 빈도수 계산
    private static HashMap<Character, Integer> calculateFrequency(String text) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }
    
    // 허프만 트리 생성
    private static Node buildHuffmanTree(HashMap<Character, Integer> freq) {
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
        return pq.poll();
    }
    
    // 허프만 트리를 사용하여 코드 테이블 생성
    private static HashMap<Character, String> generateCodes(Node root) {
        HashMap<Character, String> codes = new HashMap<>();
        generateCodesRecursive(root, "", codes);
        return codes;
    }
    
    // 재귀적으로 코드 생성 (왼쪽=0, 오른쪽=1)
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
    
    // 텍스트 인코딩
    public static String encode(String text, HashMap<Character, String> codes) {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(codes.get(c));
        }
        return encoded.toString();
    }
    
    // 텍스트 디코딩
    public static String decode(String encoded, Node root) {
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
    
    // 허프만 코딩 압축률 계산
    private static double calculateCompressionRatio(String original, String encoded) {
        int originalBits = original.length() * 8; // ASCII 기준 8비트
        int encodedBits = encoded.length();
        return (double)encodedBits / originalBits * 100.0;
    }
    
    public static void main(String[] args) {
        String text = "허프만 코딩 알고리즘은 데이터 압축에 유용합니다.";
        System.out.println("원본 텍스트: " + text);
        
        // 문자 빈도수 계산
        HashMap<Character, Integer> freq = calculateFrequency(text);
        System.out.println("\n문자 빈도수:");
        for (HashMap.Entry<Character, Integer> entry : freq.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
        
        // 허프만 트리 구축
        Node root = buildHuffmanTree(freq);
        
        // 코드 테이블 생성
        HashMap<Character, String> codes = generateCodes(root);
        System.out.println("\n허프만 코드:");
        for (HashMap.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
        
        // 인코딩
        String encoded = encode(text, codes);
        System.out.println("\n인코딩된 텍스트: " + encoded);
        
        // 디코딩
        String decoded = decode(encoded, root);
        System.out.println("\n디코딩된 텍스트: " + decoded);
        
        // 압축률 계산
        double ratio = calculateCompressionRatio(text, encoded);
        System.out.println("\n압축률: " + String.format("%.2f%%", ratio));
        System.out.println("압축 효율: " + String.format("%.2f%%", 100 - ratio));
    }
} 