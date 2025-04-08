package main.java.huffmanCodingQuiz;

/**
 * Represents a node in the Huffman tree.
 */
class HNode {
    char character; // Character stored in this node (or '\0' for internal nodes)
    HNode left, right, parent; // Tree pointers
    String encode = ""; // Huffman encoding for this node

    /**
     * Constructor for leaf and internal nodes.
     * @param character Character stored (or '\0' for internal nodes)
     * @param left Left child node
     * @param right Right child node
     * @param parent Parent node reference
     */
    public HNode(char character, HNode left, HNode right, HNode parent) {
        this.character = character;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /**
     * Default constructor for internal nodes.
     */
    public HNode() {
        this('\0', null, null, null); // Initializes as an empty internal node
    }

    /**
     * Computes and stores the Huffman encoding for this node.
     * Traverses from the current node to the root.
     */
    public void encoding() {
        if (character == '\0') return; // Skip internal nodes
        
        StringBuilder code = new StringBuilder();
        for (HNode p = this; p.parent != null; p = p.parent) {
            if (p == p.parent.left) code.append('0'); // Left child = '0'
            else code.append('1'); // Right child = '1'
        }
        encode = code.reverse().toString(); // Reverse to get correct encoding
    }
}

/**
 * HuffmanCodeTree class that builds the Huffman encoding.
 */
public class HuffmanCodeTree {
    HNode root = new HNode(); // Root of the Huffman tree

    /**
     * Initializes encoding for all characters in the Huffman tree.
     */
    public void encoding() {
        encodeNodes(root);
    }

    /**
     * Recursively computes the Huffman encoding for all nodes.
     * @param node Current node being processed.
     */
    private void encodeNodes(HNode node) {
        if (node == null) return;
        if (node.character != '\0') node.encoding(); // Encode only leaf nodes
        encodeNodes(node.left);
        encodeNodes(node.right);
    }

    /**
     * Returns the Huffman encoding as a formatted string.
     * @return A string representation of character encodings.
     */
    @Override
    public String toString() {
        return buildEncoding(root, new StringBuilder());
    }

    /**
     * Recursively constructs the string representation of Huffman encodings.
     * @param node The current node being processed.
     * @param output StringBuilder to build the output.
     * @return A string containing Huffman encodings.
     */
    private String buildEncoding(HNode node, StringBuilder output) {
        if (node == null) return "";
        if (node.character != '\0') output.append(node.character).append(": ").append(node.encode).append("\n");
        buildEncoding(node.left, output);
        buildEncoding(node.right, output);
        return output.toString();
    }
}
