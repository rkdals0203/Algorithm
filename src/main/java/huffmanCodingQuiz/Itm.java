package main.java.huffmanCodingQuiz;

public class Itm {
    public String decoder(HuffmanCodeTree huffmanTree, String encodedString) {
        StringBuilder decodedString = new StringBuilder();
        HNode root = huffmanTree.root;
        HNode currentNode = root;

        if (root == null) {
             // 빈 트리 처리: encodedString도 비어있으면 빈 결과, 아니면 Invalid
             return encodedString == null || encodedString.isEmpty() ? "" : "Invalid";
        }

        for (char bit : encodedString.toCharArray()) {
            if (bit == '0') {
                currentNode = currentNode.left;
            } else if (bit == '1') {
                currentNode = currentNode.right;
            } else {
                return "Invalid";
            }

            // 이동 후 경로가 없는 경우 (해당 비트에 해당하는 자식이 없음)
            if (currentNode == null) {
                return "Invalid";
            }

            // 리프 노드 확인: character가 '\0'(null 문자)가 아닌지 확인
            if (currentNode.character != '\0') {
                decodedString.append(currentNode.character);
                currentNode = root;
            }
            // 내부 노드인 경우 (character == '\0') 계속 진행
        }

        // 모든 비트 처리 후, 루트가 아니면 불완전한 코드
        if (currentNode != root) {
            return "Invalid";
        }

        return decodedString.toString();
    }
}

