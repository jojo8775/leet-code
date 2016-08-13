package interview.leetcode.prob;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

For example,

Given numerator = 1, denominator = 2, return "0.5".
Given numerator = 2, denominator = 1, return "2".
Given numerator = 2, denominator = 3, return "0.(6)".
Hint:

No scary math, just apply elementary math knowledge. Still remember how to perform a long division?
Try a long division on 4/9, the repeating part is obvious. Now try 4/333. Do you see a pattern?
Be wary of edge cases! List out as many test cases as you can think of and test your code thoroughly.
 * @author jojo
 *
 */
public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator;
        long den = denominator;
        
        
        StringBuilder sb = new StringBuilder();
        
        //append sign
        if((num<0 == den<0 || num == 0)){
            sb.append("");
        }
        else{
            sb.append('-');
        }
        
        num = (num < 0) ? num * -1 : num;
        den = (den < 0) ? den * -1 : den;
        
        sb.append(num/den);
        
        long remainder = num%den;
        if(remainder == 0){
            return sb.toString();
        }
        
        sb.append('.');
        
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        while(!map.containsKey(remainder)){
            map.put(remainder, sb.length());
            sb.append((remainder * 10)/den);
            remainder = (remainder * 10) % den;
        }
        
        return sb.insert(map.get(remainder), "(").append(')').toString().replace("(0)", "");
    }
	
	public static void main(String[] args){
		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(5, 2));
	}
}
