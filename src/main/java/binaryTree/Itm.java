package main.java.binaryTree;

public class Itm {


    public boolean searchTST(Node node, String word) {
        //루트 부터가 없는 경우 -> false 반환
        if (node == null) {
            return false;
        }

        if (word.charAt(0) < node.data) {
            return searchTST(node.left, word);
        }
        else if (word.charAt(0) > node.data) {
            return searchTST(node.right, word);
        }
        else {
            if (word.length() > 1) {
                return searchTST(node.eq, word.substring(1));
            }
            else {
                return node.isEndOfString;
            }
        }
    }

    public boolean searchTST(TSTree tree, String word){
        Node rootNode = tree.root;
        return searchTST(rootNode, word);
    }
}
