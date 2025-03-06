package main.java.generics;

import java.io.*;
import java.util.*;

public class Solution {
    static <T> void printArray(T[] array){
        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Integer[] intArr = {1,2,3};
        String[] strArr = {"Hello","World"};
        printArray(intArr);
        printArray(strArr);

    }
}