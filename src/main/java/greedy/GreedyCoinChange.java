package main.java.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GreedyCoinChange {

    /**
     * 그리디 알고리즘을 사용하여 목표 금액을 만들기 위한 각 동전의 사용 개수와 총 개수를 반환합니다.
     *
     * @param coinDenominations 사용 가능한 동전의 종류 배열 (예: [1, 5, 10, 100, 500])
     * @param targetAmount      목표 금액
     * @return 각 동전의 사용 개수를 담은 Map. 목표 금액을 만들 수 없는 경우 null을 반환합니다.
     *         Map의 Key는 동전 금액, Value는 해당 동전의 사용 개수입니다.
     */
    public Map<Integer, Integer> findCoinUsage(Integer[] coinDenominations, int targetAmount) {
        Arrays.sort(coinDenominations, Collections.reverseOrder());

        Map<Integer, Integer> coinUsage = new HashMap<>();
        int remainingAmount = targetAmount;
        
        if (targetAmount == 0) {
            return coinUsage;
        }

        for (int coin : coinDenominations) {
            if (remainingAmount <= 0) {
                break;
            }

            if (coin <= remainingAmount) {
                int count = remainingAmount / coin;
                if (count > 0) {
                    coinUsage.put(coin, count);
                }
                remainingAmount -= count * coin;
            }
        }

        if (remainingAmount == 0) {
            return coinUsage;
        } else {
            return null;
        }
    }

    /**
     * 그리디 알고리즘을 사용하여 목표 금액을 만들기 위한 최소 동전 개수를 반환합니다.
     *
     * @param coinDenominations 사용 가능한 동전의 종류 배열 (예: [1, 5, 10, 100, 500])
     * @param targetAmount      목표 금액
     * @return 최소 동전 개수. 목표 금액을 만들 수 없는 경우 -1을 반환합니다.
     */
    public int findMinimumCoins(Integer[] coinDenominations, int targetAmount) {
        Map<Integer, Integer> usage = findCoinUsage(coinDenominations, targetAmount);
        if (usage == null) {
            return -1;
        }
        if (targetAmount == 0) {
            return 0;
        }
        int totalCoins = 0;
        for (int count : usage.values()) {
            totalCoins += count;
        }
        return totalCoins;
    }
} 