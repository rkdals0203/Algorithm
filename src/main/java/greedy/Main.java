package main.java.greedy;

public class Main {
    public static void main(String[] args) {
        GreedyCoinChange gcg = new GreedyCoinChange();

        // 예제 1: 일반적인 경우
        Integer[] denominations1 = {500, 100, 50, 10, 1};
        int amount1 = 678;
        int result1 = gcg.findMinimumCoins(denominations1, amount1);
        System.out.println("Amount: " + amount1 + ", Coins: " + result1);
        // 예상 결과: (500*1) + (100*1) + (50*1) + (10*2) + (1*8) => 1+1+1+2+3 = 8 (앗, 1*3이므로 1+1+1+2+3=8이 맞음. 수정) => 1+1+1+2+8=13. 계산 실수, 500*1 + 100*1 + 50*1 + 10*2 + 1*8 => 1 + 1+ 1+ 2+ 8 = 13
        // 실제로는: 500*1 (남은금액 178), 100*1 (남은금액 78), 50*1 (남은금액 28), 10*2 (남은금액 8), 1*8 (남은금액 0) => 1+1+1+2+8 = 13 coins

        // 예제 2: 작은 동전만 사용해야 하는 경우
        Integer[] denominations2 = {100, 50, 10};
        int amount2 = 30;
        int result2 = gcg.findMinimumCoins(denominations2, amount2);
        System.out.println("Amount: " + amount2 + ", Coins: " + result2);
        // 예상 결과: (10*3) => 3 coins

        // 예제 3: 만들 수 없는 경우 (그리디 방식의 한계)
        // 그리디 알고리즘은 특정 동전 구성에서 최적해를 보장하지 않거나, 해를 못 찾을 수 있음.
        // 이 예제는 그리디로 해를 찾을 수 없는 경우입니다.
        Integer[] denominations3 = {6, 4, 1}; // 정렬 후: 6, 4, 1
        int amount3 = 8;
        int result3 = gcg.findMinimumCoins(denominations3, amount3);
        System.out.println("Amount: " + amount3 + ", Coins: " + result3);
        // 예상 결과: (6*1, 남은금액 2), 1*2 => 1+2 = 3 coins.  (실제 최적해는 4*2 = 2 coins)
        // 하지만 현재 구현은 6을 쓰고 남은 2를 1로 채우므로 3을 반환합니다.
        // 만약 동전이 {6,4} 이고 amount가 8이면 6*1 -> 남은금액 2. 만들 수 없으므로 -1 반환.

        // 예제 4: 딱 떨어지지 않아 -1을 반환해야 하는 경우
        Integer[] denominations4 = {5, 2};
        int amount4 = 11;
        int result4 = gcg.findMinimumCoins(denominations4, amount4);
        System.out.println("Amount: " + amount4 + ", Coins: " + result4);
        // 예상 결과: (5*2, 남은금액 1). 1을 만들 수 없으므로 -1

        System.out.println("\n--- 추가 테스트 --- ");
        // 교재 예시: N=1260원, 동전 {500, 100, 50, 10}
        Integer[] denominations_book = {500, 100, 50, 10};
        int amount_book = 1260;
        int result_book = gcg.findMinimumCoins(denominations_book, amount_book);
        System.out.println("Amount: " + amount_book + ", Coins: " + result_book);
        // 예상: 500*2 (남은 260), 100*2 (남은 60), 50*1 (남은 10), 10*1 (남은 0) => 2+2+1+1 = 6
    }
} 