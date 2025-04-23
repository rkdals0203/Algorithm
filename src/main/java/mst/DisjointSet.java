package main.java.mst;

/**
 * DisjointSet class implements the Union-Find data structure with path compression.
 * It is used to efficiently manage a collection of disjoint sets, supporting operations
 * like finding the representative of a set and merging two sets.
 */
public class DisjointSet {
    int[] parent, rank;

    /**
     * Constructor initializes the parent and rank arrays for n elements.
     * Each element is initially in its own set.
     *
     * @param n Number of elements
     */
    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;  // Each element is its own parent initially
        }
    }

    /**
     * Finds the representative (root) of the set to which element 'a' belongs.
     * Path compression is applied to make future queries faster.
     *
     * @param a The element whose set representative is to be found
     * @return The representative of the set containing 'a'
     */
    public int find(int a) {
        if (parent[a] != a) {
            parent[a] = find(parent[a]);  // Path compression
        }
        return parent[a];
    }

    /**
     * Unites the sets containing elements 'a' and 'b'.
     * Uses union by rank to attach the smaller tree under the larger tree.
     *
     * @param a An element in the first set
     * @param b An element in the second set
     */
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;  // Attach smaller rank tree under the larger rank tree
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;  // Increase the rank if both have the same rank
            }
        }
    }
}
