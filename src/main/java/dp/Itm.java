package main.java.dp;

public class Itm{
    public int getWays(int target, int[] coins){
        int[] coinWays = new int[target+1];
        coinWays[0] = 1;

        for (int coinValue : coins) {
            for (int j = coinValue; j <= target; j++) {
                coinWays[j] += coinWays[j - coinValue];
            }
        }
        return coinWays[target];
    }

    public int minCoinCount(int target, int[] coins) {
    int[] minCoins = new int[target + 1];
    Arrays.fill(minCoins, Integer.MAX_VALUE);
    minCoins[0] = 0;

    for (int coinValue : coins) {
        for (int j = coinValue; j <= target; j++) {
            if (minCoins[j - coinValue] != Integer.MAX_VALUE) {
                minCoins[j] = Math.min(minCoins[j], minCoins[j - coinValue] + 1);
            }
        }
    }
    return minCoins[target] == Integer.MAX_VALUE ? -1 : minCoins[target];
}