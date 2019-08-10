package interview.leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class FractionToRecurringDecimal {
	public String fractionToDecimal(int numerator, int denominator) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(numerator/denominator));
		
		int remainder = numerator % denominator;
		if(remainder != 0) {
			sb.append(".");
		}
		
		Map<String, Integer> map = new HashMap<>();
		String lastDigit = "";
		while(remainder != 0) {
			remainder *= 10;
			if(remainder < denominator) {
				map.computeIfAbsent("0", v -> sb.length());
				sb.append("0");
				continue;
			}
			
			lastDigit = String.valueOf(remainder/denominator);
			if(map.containsKey(lastDigit)) {
				break;
			}
			
			map.put(lastDigit, sb.length());
			sb.append(lastDigit);
			remainder %= denominator;
		}
		
		if(remainder != 0) {
			sb.insert(map.get(lastDigit), "(").append(")");
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
//		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(2, 5));
//		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(2, 3));
//		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(2, 2));
//		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(22, 7));
		System.out.println(new FractionToRecurringDecimal().fractionToDecimal(1, 101));
	}
}
