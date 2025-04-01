package main.java.huffmanCoding;

import java.util.HashMap;

public class Itm {
    // 문자열의 각 문자 빈도수 계산
    public static HashMap<Character, Integer> calculateFrequency(String text) {
        // TODO: 문자열의 각 문자 빈도수를 계산하여 HashMap에 저장하고 반환하세요.
        // 예시: "hello" -> {'h':1, 'e':1, 'l':2, 'o':1}
        return null;
    }
    
    // 텍스트 인코딩
    public static String encode(String text, HashMap<Character, String> codes) {
        // TODO: 주어진 텍스트를 허프만 코드를 사용하여 인코딩하세요.
        // 예시: "hello" -> "0100110011" (실제 코드는 허프만 트리에 따라 다를 수 있음)
        return null;
    }
    
    // 허프만 코딩 압축률 계산
    public static double calculateCompressionRatio(String original, String encoded) {
        // TODO: 원본 텍스트와 인코딩된 텍스트의 압축률을 계산하세요.
        // 압축률 = (인코딩된 비트 수 / 원본 비트 수) * 100
        // 원본 비트 수는 ASCII 기준 8비트 * 문자열 길이
        return 0.0;
    }
} 