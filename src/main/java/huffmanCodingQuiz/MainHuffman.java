package main.java.huffmanCodingQuiz;

/**
 * Main class to test Huffman encoding and decoding.
 */
public class MainHuffman {
    public static void main(String[] args) {
        // Step 1: Build the Huffman tree
        HuffmanCodeTree ht = buildHuffmanTree();
        ht.encoding(); // Compute Huffman codes
        System.out.println("Huffman Encoding Table:\n" + ht); // Print Huffman codes
        
        // Step 2: Decode a Huffman-encoded binary string using Itm
        Itm itm = new Itm();
        String encodedString = "011011110"; // Example encoded bit sequence
        System.out.println("Decoding '011011110': " + itm.decoder(ht, encodedString));
    }

    /**
     * Constructs a predefined Huffman Tree structure for testing.
     * @return HuffmanCodeTree with encoded characters.
     */
    private static HuffmanCodeTree buildHuffmanTree() {
        HuffmanCodeTree ht = new HuffmanCodeTree();

        // Create Huffman tree structure
        HNode nodeA = new HNode('A', null, null, ht.root);
        ht.root.left = nodeA;

        // Internal node representing (B, C, D)
        HNode nodeBCD = new HNode('\0', null, null, ht.root);
        ht.root.right = nodeBCD;

        // B goes to the left of BCD
        HNode nodeB = new HNode('B', null, null, nodeBCD);
        nodeBCD.left = nodeB;

        // Internal node representing (C, D)
        HNode nodeCD = new HNode('\0', null, null, nodeBCD);
        nodeBCD.right = nodeCD;

        // C and D added to the tree
        HNode nodeC = new HNode('C', null, null, nodeCD);
        nodeCD.left = nodeC;
        HNode nodeD = new HNode('D', null, null, nodeCD);
        nodeCD.right = nodeD;

        return ht;
    }
}