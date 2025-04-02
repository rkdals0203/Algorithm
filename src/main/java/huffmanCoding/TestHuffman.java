package main.java.huffmanCoding;

import java.util.HashMap;

public class TestHuffman {
    public static void main(String[] args) {
        String text = "허프만 코딩 알고리즘은 데이터 압축에 유용합니다.";
        System.out.println("원본 텍스트: " + text);
        
        // 문자 빈도수 계산
        HashMap<Character, Integer> freq = Itm.calculateFrequency(text);
        System.out.println("\n문자 빈도수:");
        for (HashMap.Entry<Character, Integer> entry : freq.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
        
        // 허프만 트리 구축
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.buildHuffmanTree(freq);
        
        // 코드 테이블 생성
        HashMap<Character, String> codes = huffmanTree.generateCodes();
        System.out.println("\n허프만 코드:");
        for (HashMap.Entry<Character, String> entry : codes.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue());
        }
        
        // 인코딩
        String encoded = Itm.encode(text);
        System.out.println("\n인코딩된 텍스트: " + encoded);
        
        // 디코딩
        String decoded = huffmanTree.decode(encoded);
        System.out.println("\n디코딩된 텍스트: " + decoded);
        
        // 압축률 계산
        double ratio = Itm.calculateCompressionRatio(text, encoded);
        System.out.println("\n압축률: " + String.format("%.2f%%", ratio));
        System.out.println("압축 효율: " + String.format("%.2f%%", 100 - ratio));
    }
} 