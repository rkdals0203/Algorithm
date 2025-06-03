package main.java.dc;

public class Itm {

    public int processDigits(String num){
        if(num.length()==1){
            return Character.getNumericValue(num.charAt(0));
        }

        if(num.length()==2){
            return Character.getNumericValue(num.charAt(0))*Character.getNumericValue(num.charAt(1));
        }

        if(num.length()%2==0){
            int middle = num.length()/2;
            String leftNum = num.substring(0, middle);
            String rightNum = num.substring(middle);
            return processDigits(leftNum)*processDigits(rightNum);
        }

        else {
            int middle = num.length()/2;
            String leftNum = num.substring(0, middle);
            String rightNum = num.substring(middle+1);
            return processDigits(leftNum)*processDigits(rightNum)+Character.getNumericValue(num.charAt(middle));
        }

    }

}
