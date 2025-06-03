import java.util.*;

 

publicclass Main {

    publicstaticvoid main(String[] args) {

        // Create an instance of the Itm class to use the getWays method

        Itm itm = new Itm();

 

        // Define the available coin denominations

        int[] coins = {1, 2, 3};

 

        // Call the getWays method to compute the number of ways to make change for amount 4

        intresult = itm.getWays(4, coins);

 

        // Print the result with expected output comparison

        System.out.println("Amount: " + 4 + ", Coins: " + Arrays.toString(coins)

            + " => Ways: " + result + " (Expected: " + 4 + ") "

            + (result == 4 ? "✅" : "❌"));

    }

 

}