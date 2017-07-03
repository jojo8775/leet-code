package interview.leetcode.practice.round4.arrays;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
    public String fractionToDecimal(int n, int d) {
        long numerator = n, denominator = d;
        
        if(numerator == 0){
            return "0";
        }
        
        boolean negative = false;
        if(numerator < 0){
            numerator *= -1;
            negative = true;
        }
        
        if(denominator < 0){
            denominator *= -1;
            negative = !negative;
        }
        
        StringBuilder sb = new StringBuilder();
        if(negative){
            sb.append("-");
        }
        
        
        sb.append(numerator / denominator);
        numerator %= denominator;
        if (numerator == 0) {
            return sb.toString();
        }

        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (!map.containsKey(numerator)) {
            map.put(numerator, sb.length());
            numerator *= 10;
            sb.append(numerator / denominator);
            numerator %= denominator;
        }

        String str =  sb.toString().substring(0, map.get(numerator)) + "(" + sb.toString().substring(map.get(numerator))
                + ")";
        return str.replace("(0)", "");
    }

    public static void main(String[] args) {
        System.out.println(new FractionToRecurringDecimal().fractionToDecimal(22,7));
    }
}
