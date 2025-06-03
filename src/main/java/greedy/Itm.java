package main.java.greedy;
import java.util.*;

public class Itm {
    public int maxWeight(int[] ropes){
        Arrays.sort(ropes);
        int max = 0;

        // i번째 로프부터 끝까지 사용한다 치기
        for(int i = 0; i < ropes.length; i++){
            int capacity = ropes[i];
            int weight = capacity * (ropes.length - i);
            if(weight > max) max = weight;
        }
        return max;



    }
}