package main.java.greedy;
public class Test {

    public static void main(String[] args) {

        Itm itm = new Itm(); // Create an instance of the Itm class


        // Define an integer array containing the maximum weight each rope can endure

        int[] ropes = {10, 20, 20};


        // Compute the maximum weight that can be lifted

        int result = itm.maxWeight(ropes);


        // Print the result, expected output is 40

        System.out.println("Maximum weight that can be lifted: " + result);

    }
}

