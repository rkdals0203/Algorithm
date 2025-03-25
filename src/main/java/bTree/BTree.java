public class BTree<K extends Comparable<K>, V> {
    
    // B-Tree의 최소 차수 (모든 노드는 최소 t-1개의 키를 가져야 함)
    private final int t;
    
    // 루트 노드
    private Node root;
    
    // 노드 클래스
    private class Node {
        // 현재 노드에 저장된 키의 개수
        int n;
        
        // 키 배열
        K[] keys;
        
        // 값 배열
        V[] values;
        
        // 자식 노드 배열
        Node[] children;
        
        // 리프 노드 여부
        boolean leaf;
        
        @SuppressWarnings("unchecked")
        public Node(boolean leaf) {
            this.leaf = leaf;
            this.keys = (K[]) new Comparable[2 * t - 1];
            this.values = (V[]) new Object[2 * t - 1];
            this.children = new Node[2 * t];
            this.n = 0;
        }
    }
    
    /**
     * B-Tree 생성자
     * @param t 최소 차수 (모든 노드는 최소 t-1개의 키를 가져야 함)
     */
    public BTree(int t) {
        this.t = t;
        this.root = new Node(true);
    }
    
    /**
     * 키에 해당하는 값을 검색
     * @param key 검색할 키
     * @return 키에 해당하는 값, 없으면 null
     */
    public V search(K key) {
        return search(root, key);
    }
    
    /**
     * 지정된 노드에서 키에 해당하는 값을 검색
     * @param node 검색을 시작할 노드
     * @param key 검색할 키
     * @return 키에 해당하는 값, 없으면 null
     */
    private V search(Node node, K key) {
        // 키를 찾을 인덱스
        int i = 0;
        
        // 현재 노드에서 키를 찾기
        while (i < node.n && key.compareTo(node.keys[i]) > 0) {
            i++;
        }
        
        // 키를 찾은 경우
        if (i < node.n && key.compareTo(node.keys[i]) == 0) {
            return node.values[i];
        }
        
        // 리프 노드에 도달했지만 키를 찾지 못한 경우
        if (node.leaf) {
            return null;
        }
        
        // 자식 노드로 검색을 계속
        return search(node.children[i], key);
    }
    
    /**
     * B-Tree에 키-값 쌍을 삽입
     * @param key 삽입할 키
     * @param value 삽입할 값
     */
    public void insert(K key, V value) {
        Node r = root;
        
        // 루트 노드가 가득 찬 경우, 트리의 높이를 증가
        if (r.n == 2 * t - 1) {
            Node s = new Node(false);
            root = s;
            s.children[0] = r;
            splitChild(s, 0);
            insertNonFull(s, key, value);
        } else {
            insertNonFull(r, key, value);
        }
    }
    
    /**
     * 가득 차지 않은 노드에 키-값 쌍을 삽입
     * @param node 삽입할 노드
     * @param key 삽입할 키
     * @param value 삽입할 값
     */
    private void insertNonFull(Node node, K key, V value) {
        int i = node.n - 1;
        
        // 리프 노드인 경우, 키를 직접 삽입
        if (node.leaf) {
            // 키를 삽입할 위치를 찾고 오른쪽 키들을 이동
            while (i >= 0 && key.compareTo(node.keys[i]) < 0) {
                node.keys[i + 1] = node.keys[i];
                node.values[i + 1] = node.values[i];
                i--;
            }
            
            // 키와 값 삽입
            node.keys[i + 1] = key;
            node.values[i + 1] = value;
            node.n++;
        } else {
            // 내부 노드인 경우, 키가 들어갈 자식 노드를 찾음
            while (i >= 0 && key.compareTo(node.keys[i]) < 0) {
                i--;
            }
            i++;
            
            // 자식 노드가 가득 찬 경우, 분할
            if (node.children[i].n == 2 * t - 1) {
                splitChild(node, i);
                
                // 분할 후, 키가 어느 자식으로 가야 할지 다시 결정
                if (key.compareTo(node.keys[i]) > 0) {
                    i++;
                }
            }
            
            // 알맞은 자식 노드에 키 삽입
            insertNonFull(node.children[i], key, value);
        }
    }
    
    /**
     * 가득 찬 자식 노드를 분할
     * @param parent 부모 노드
     * @param index 분할할 자식 노드의 인덱스
     */
    private void splitChild(Node parent, int index) {
        Node child = parent.children[index];
        Node newChild = new Node(child.leaf);
        newChild.n = t - 1;
        
        // 키와 값을 새 노드로 이동
        for (int j = 0; j < t - 1; j++) {
            newChild.keys[j] = child.keys[j + t];
            newChild.values[j] = child.values[j + t];
        }
        
        // 리프 노드가 아닌 경우, 자식 포인터도 이동
        if (!child.leaf) {
            for (int j = 0; j < t; j++) {
                newChild.children[j] = child.children[j + t];
            }
        }
        
        // 자식 노드의 키 개수 업데이트
        child.n = t - 1;
        
        // 부모 노드에 자리 마련
        for (int j = parent.n; j > index; j--) {
            parent.children[j + 1] = parent.children[j];
        }
        
        // 새 자식 노드를 부모에 연결
        parent.children[index + 1] = newChild;
        
        // 부모 노드에 키 이동을 위한 자리 마련
        for (int j = parent.n - 1; j >= index; j--) {
            parent.keys[j + 1] = parent.keys[j];
            parent.values[j + 1] = parent.values[j];
        }
        
        // 중간 키를 부모 노드로 이동
        parent.keys[index] = child.keys[t - 1];
        parent.values[index] = child.values[t - 1];
        parent.n++;
    }
    
    /**
     * B-Tree에서 키-값 쌍을 삭제
     * @param key 삭제할 키
     * @return 삭제된 값, 키가 없으면 null
     */
    public V delete(K key) {
        if (root == null) {
            return null;
        }
        
        V result = delete(root, key);
        
        // 루트 노드가 비어있고 자식이 있는 경우, 자식을 루트로 만듦
        if (root.n == 0 && !root.leaf) {
            root = root.children[0];
        }
        
        return result;
    }
    
    /**
     * 지정된 노드에서 키-값 쌍을 삭제
     * @param node 삭제 작업을 수행할 노드
     * @param key 삭제할 키
     * @return 삭제된 값, 키가 없으면 null
     */
    private V delete(Node node, K key) {
        int idx = findKeyIndex(node, key);
        V result = null;
        
        // 현재 노드에서 키를 찾은 경우
        if (idx < node.n && node.keys[idx].compareTo(key) == 0) {
            result = node.values[idx];
            
            // 리프 노드인 경우, 단순히 삭제
            if (node.leaf) {
                removeFromLeaf(node, idx);
            } else {
                removeFromNonLeaf(node, idx);
            }
        } else {
            // 키가 현재 노드에 없는 경우
            
            // 리프 노드인 경우, 키가 트리에 없음
            if (node.leaf) {
                return null;
            }
            
            // 마지막 자식에 대한 플래그
            boolean isLastChild = (idx == node.n);
            
            // 자식 노드에 최소 t개의 키가 있도록 보장
            if (node.children[idx].n < t) {
                fill(node, idx);
            }
            
            // 마지막 자식이 병합된 경우
            if (isLastChild && idx > node.n) {
                result = delete(node.children[idx - 1], key);
            } else {
                result = delete(node.children[idx], key);
            }
        }
        
        return result;
    }
    
    /**
     * 노드에서 키를 찾는 위치(인덱스)를 반환
     * @param node 검색할 노드
     * @param key 찾을 키
     * @return 키의 인덱스 또는 키보다 큰 첫 번째 키의 인덱스
     */
    private int findKeyIndex(Node node, K key) {
        int idx = 0;
        while (idx < node.n && node.keys[idx].compareTo(key) < 0) {
            idx++;
        }
        return idx;
    }
    
    /**
     * 리프 노드에서 키-값 쌍을 삭제
     * @param node 삭제할 노드
     * @param idx 삭제할 키의 인덱스
     */
    private void removeFromLeaf(Node node, int idx) {
        // 삭제할 키의 오른쪽에 있는 모든 키를 왼쪽으로 이동
        for (int i = idx + 1; i < node.n; i++) {
            node.keys[i - 1] = node.keys[i];
            node.values[i - 1] = node.values[i];
        }
        
        // 키 개수 감소
        node.n--;
    }
    
    /**
     * 내부 노드에서 키-값 쌍을 삭제
     * @param node 삭제할 노드
     * @param idx 삭제할 키의 인덱스
     */
    private void removeFromNonLeaf(Node node, int idx) {
        K key = node.keys[idx];
        
        // 왼쪽 자식의 가장 큰 키로 대체
        if (node.children[idx].n >= t) {
            K pred = getPredecessor(node, idx);
            V predValue = getPredecessorValue(node, idx);
            node.keys[idx] = pred;
            node.values[idx] = predValue;
            delete(node.children[idx], pred);
        }
        // 오른쪽 자식의 가장 작은 키로 대체
        else if (node.children[idx + 1].n >= t) {
            K succ = getSuccessor(node, idx);
            V succValue = getSuccessorValue(node, idx);
            node.keys[idx] = succ;
            node.values[idx] = succValue;
            delete(node.children[idx + 1], succ);
        }
        // 왼쪽, 오른쪽 자식 모두 최소 키 개수를 가짐. 두 자식을 병합
        else {
            merge(node, idx);
            delete(node.children[idx], key);
        }
    }
    
    /**
     * idx번째 키의 선행자(predecessor)를 찾음
     * @param node 기준 노드
     * @param idx 키 인덱스
     * @return 선행자 키
     */
    private K getPredecessor(Node node, int idx) {
        Node cur = node.children[idx];
        while (!cur.leaf) {
            cur = cur.children[cur.n];
        }
        return cur.keys[cur.n - 1];
    }
    
    /**
     * idx번째 키의 선행자(predecessor) 값을 찾음
     * @param node 기준 노드
     * @param idx 키 인덱스
     * @return 선행자 값
     */
    private V getPredecessorValue(Node node, int idx) {
        Node cur = node.children[idx];
        while (!cur.leaf) {
            cur = cur.children[cur.n];
        }
        return cur.values[cur.n - 1];
    }
    
    /**
     * idx번째 키의 후속자(successor)를 찾음
     * @param node 기준 노드
     * @param idx 키 인덱스
     * @return 후속자 키
     */
    private K getSuccessor(Node node, int idx) {
        Node cur = node.children[idx + 1];
        while (!cur.leaf) {
            cur = cur.children[0];
        }
        return cur.keys[0];
    }
    
    /**
     * idx번째 키의 후속자(successor) 값을 찾음
     * @param node 기준 노드
     * @param idx 키 인덱스
     * @return 후속자 값
     */
    private V getSuccessorValue(Node node, int idx) {
        Node cur = node.children[idx + 1];
        while (!cur.leaf) {
            cur = cur.children[0];
        }
        return cur.values[0];
    }
    
    /**
     * 자식 노드가 최소 개수 이상의 키를 가지도록 보장
     * @param node 부모 노드
     * @param idx 자식 노드 인덱스
     */
    private void fill(Node node, int idx) {
        // 왼쪽 형제에서 빌림
        if (idx > 0 && node.children[idx - 1].n >= t) {
            borrowFromPrev(node, idx);
        }
        // 오른쪽 형제에서 빌림
        else if (idx < node.n && node.children[idx + 1].n >= t) {
            borrowFromNext(node, idx);
        }
        // 병합
        else {
            if (idx < node.n) {
                merge(node, idx);
            } else {
                merge(node, idx - 1);
            }
        }
    }
    
    /**
     * 왼쪽 형제 노드에서 키를 빌림
     * @param node 부모 노드
     * @param idx 자식 노드 인덱스
     */
    private void borrowFromPrev(Node node, int idx) {
        Node child = node.children[idx];
        Node sibling = node.children[idx - 1];
        
        // 자식 노드의 키들을 오른쪽으로 이동
        for (int i = child.n - 1; i >= 0; i--) {
            child.keys[i + 1] = child.keys[i];
            child.values[i + 1] = child.values[i];
        }
        
        // 자식이 리프 노드가 아닌 경우, 자식 포인터도 이동
        if (!child.leaf) {
            for (int i = child.n; i >= 0; i--) {
                child.children[i + 1] = child.children[i];
            }
        }
        
        // 부모의 키를 자식의 첫 번째 키로 이동
        child.keys[0] = node.keys[idx - 1];
        child.values[0] = node.values[idx - 1];
        
        // 형제의 마지막 자식을 자식의 첫 번째 자식으로 이동
        if (!child.leaf) {
            child.children[0] = sibling.children[sibling.n];
        }
        
        // 형제의 마지막 키를 부모로 이동
        node.keys[idx - 1] = sibling.keys[sibling.n - 1];
        node.values[idx - 1] = sibling.values[sibling.n - 1];
        
        // 키 개수 조정
        child.n++;
        sibling.n--;
    }
    
    /**
     * 오른쪽 형제 노드에서 키를 빌림
     * @param node 부모 노드
     * @param idx 자식 노드 인덱스
     */
    private void borrowFromNext(Node node, int idx) {
        Node child = node.children[idx];
        Node sibling = node.children[idx + 1];
        
        // 부모의 키를 자식의 마지막 키로 이동
        child.keys[child.n] = node.keys[idx];
        child.values[child.n] = node.values[idx];
        
        // 형제의 첫 번째 자식을 자식의 마지막 자식으로 이동
        if (!child.leaf) {
            child.children[child.n + 1] = sibling.children[0];
        }
        
        // 형제의 첫 번째 키를 부모로 이동
        node.keys[idx] = sibling.keys[0];
        node.values[idx] = sibling.values[0];
        
        // 형제의 키와 자식들을 왼쪽으로 이동
        for (int i = 1; i < sibling.n; i++) {
            sibling.keys[i - 1] = sibling.keys[i];
            sibling.values[i - 1] = sibling.values[i];
        }
        
        // 형제의 자식들도 왼쪽으로 이동
        if (!sibling.leaf) {
            for (int i = 1; i <= sibling.n; i++) {
                sibling.children[i - 1] = sibling.children[i];
            }
        }
        
        // 키 개수 조정
        child.n++;
        sibling.n--;
    }
    
    /**
     * idx번째 자식과 idx+1번째 자식을 병합
     * @param node 부모 노드
     * @param idx 왼쪽 자식 인덱스
     */
    private void merge(Node node, int idx) {
        Node child = node.children[idx];
        Node sibling = node.children[idx + 1];
        
        // 부모의 키를 자식의 중간으로 이동
        child.keys[t - 1] = node.keys[idx];
        child.values[t - 1] = node.values[idx];
        
        // 형제의 키와 값을 자식으로 복사
        for (int i = 0; i < sibling.n; i++) {
            child.keys[i + t] = sibling.keys[i];
            child.values[i + t] = sibling.values[i];
        }
        
        // 형제의 자식 포인터도 복사
        if (!child.leaf) {
            for (int i = 0; i <= sibling.n; i++) {
                child.children[i + t] = sibling.children[i];
            }
        }
        
        // 부모에서 키 제거하고 자식 포인터 조정
        for (int i = idx + 1; i < node.n; i++) {
            node.keys[i - 1] = node.keys[i];
            node.values[i - 1] = node.values[i];
        }
        
        for (int i = idx + 2; i <= node.n; i++) {
            node.children[i - 1] = node.children[i];
        }
        
        // 키 개수 업데이트
        child.n += sibling.n + 1;
        node.n--;
    }
    
    /**
     * B-Tree를 중위 순회하여 모든 키-값 쌍을 출력
     */
    public void inorder() {
        if (root != null) {
            inorder(root);
        }
        System.out.println();
    }
    
    /**
     * 지정된 노드부터 중위 순회
     * @param node 순회를 시작할 노드
     */
    private void inorder(Node node) {
        int i;
        for (i = 0; i < node.n; i++) {
            if (!node.leaf) {
                inorder(node.children[i]);
            }
            System.out.print(node.keys[i] + ":" + node.values[i] + " ");
        }
        
        if (!node.leaf) {
            inorder(node.children[i]);
        }
    }
} 