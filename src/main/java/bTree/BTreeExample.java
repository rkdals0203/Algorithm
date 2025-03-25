package bTree;
public class BTreeExample {
    
    public static void main(String[] args) {
        // B-Tree 생성 (최소 차수 t=3)
        // 모든 노드는 최소 2개, 최대 5개의 키를 가질 수 있음
        BTree<Integer, String> bTree = new BTree<>(3);
        
        System.out.println("B-Tree 예제 (t=3)");
        System.out.println("----------------------------------");
        
        // 키-값 쌍 삽입
        System.out.println("1. 키-값 쌍 삽입");
        bTree.insert(10, "값10");
        bTree.insert(20, "값20");
        bTree.insert(5, "값5");
        bTree.insert(6, "값6");
        bTree.insert(12, "값12");
        bTree.insert(30, "값30");
        bTree.insert(7, "값7");
        bTree.insert(17, "값17");
        
        // 중위 순회로 트리 출력
        System.out.println("\n현재 트리 상태(중위 순회):");
        bTree.inorder();
        
        // 키 검색
        System.out.println("\n2. 키 검색");
        System.out.println("키 10 검색 결과: " + bTree.search(10));
        System.out.println("키 20 검색 결과: " + bTree.search(20));
        System.out.println("키 50 검색 결과: " + bTree.search(50)); // 존재하지 않는 키
        
        // 키 삭제
        System.out.println("\n3. 키 삭제");
        System.out.println("키 6 삭제 전 트리:");
        bTree.inorder();
        
        String removed = bTree.delete(6);
        System.out.println("\n키 6 삭제 (삭제된 값: " + removed + ")");
        System.out.println("키 6 삭제 후 트리:");
        bTree.inorder();
        
        // 추가 키 삽입
        System.out.println("\n4. 추가 키 삽입");
        bTree.insert(1, "값1");
        bTree.insert(2, "값2");
        bTree.insert(3, "값3");
        bTree.insert(4, "값4");
        bTree.insert(8, "값8");
        bTree.insert(9, "값9");
        
        System.out.println("\n추가 삽입 후 트리:");
        bTree.inorder();
        
        // 내부 노드의 키 삭제
        System.out.println("\n5. 내부 노드의 키 삭제");
        System.out.println("키 10 삭제 전 트리:");
        bTree.inorder();
        
        removed = bTree.delete(10);
        System.out.println("\n키 10 삭제 (삭제된 값: " + removed + ")");
        System.out.println("키 10 삭제 후 트리:");
        bTree.inorder();
        
        // 존재하지 않는 키 삭제 시도
        System.out.println("\n6. 존재하지 않는 키 삭제 시도");
        removed = bTree.delete(100);
        System.out.println("키 100 삭제 시도 (삭제된 값: " + removed + ")");
        
        System.out.println("\n최종 트리 상태:");
        bTree.inorder();
    }
} 