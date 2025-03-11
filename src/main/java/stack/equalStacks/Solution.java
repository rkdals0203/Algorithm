package main.java.stack.equalStacks;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    public static int getSum(List<Integer> list ){
        int sum = 0;
        for(int i = 0; i<list.size(); i++){
            sum += list.get(i);
        }
        return sum;
    }
    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
        int height1 = getSum(h1);
        int height2 = getSum(h2);
        int height3 = getSum(h3);

        while(!(height1==height2 && height2 == height3)){
            if (h1.isEmpty() || h2.isEmpty() || h3.isEmpty()) return 0;
            if (height1 >= height2 && height1 >= height3) {
                height1 -= h1.get(0);
                h1.remove(0);
            }
            else if (height2 >= height1 && height2 >= height3) {
                height2 -= h2.get(0);
                h2.remove(0);
            }
            else {
                height3 -= h3.get(0);
                h3.remove(0);
            }

        }
        return height1;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
