package main.java.bruteForce;

public class Itm {

    public int maxSubarraySum(int[] arr){
        int result = Integer.MIN_VALUE;
        for(int startIndex = 0; startIndex <  arr.length; startIndex++){
            int sum = 0;
            for(int endIndex = startIndex; endIndex <  arr.length; endIndex++){
                sum += arr[endIndex];
                if(sum > result){
                    result = sum;
                }
            }
        }
        return result;
    }

}
