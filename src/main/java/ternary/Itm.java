package main.java.ternary;

public class Itm {
    public boolean searchTST(TSTree tree, String word) {
        if (tree == null || word == null) {
            return false;
        }

        Node current = tree.root;
        int position = 0;

        while (current != null && position < word.length()) {
            char ch = word.charAt(position);

            if (ch < current.data) {
                current = current.left;
            } else if (ch > current.data) {
                current = current.right;
            } else { // 문자가 일치하는 경우
                //만약 포지션이 끝에 있다면
                if (current.isEndOfString) {
                    return true;
                }
                current = current.eq;
                position++;
            }
        }

        return false;
    }
}