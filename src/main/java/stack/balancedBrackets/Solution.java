package main.java.stack.balancedBrackets;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static String isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        char[] brackets = s.toCharArray();
        for (char bracket: brackets){
            if (bracket == '{' || bracket == '[' || bracket == '('){
                stack.push(bracket);
            } else {
                if(stack.isEmpty()) return "NO";
                char top = stack.pop();
                if(!((top =='{' && bracket == '}') ||
                        (top =='[' && bracket == ']') ||
                        (top =='(' && bracket == ')')
                )) return "NO";
            }
        }
        if (stack.isEmpty()) return "YES";
        else return "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                String s = bufferedReader.readLine();

                String result = Result.isBalanced(s);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
